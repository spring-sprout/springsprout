package springsprout.modules.study.meeting.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.domain.*;
import springsprout.modules.comment.CommentRepository;
import springsprout.modules.member.MemberService;
import springsprout.modules.study.meeting.resource.ResourceRepository;
import springsprout.service.notification.NotificationService;
import springsprout.service.notification.message.CommentMailMessage;
import springsprout.service.notification.message.CommentMailMessageTest;
import springsprout.service.security.SecurityService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 17
 * Time: 오후 11:19:42
 */
@Service
@Transactional
public class PresentationService {

    @Autowired SecurityService securityService;
    @Autowired ResourceRepository resourceRepository;
    @Autowired PresentationRepository repository;
    @Autowired CommentRepository commentRepository;
    @Autowired MemberService memberService;
    @Autowired @Qualifier("sendMailService") NotificationService notiService;
    @Inject Provider<CommentMailMessage> commentMail;

    public Presentation getById(int presentationId) {
        return repository.getById(presentationId);
    }

    public void update(int presentationId, Presentation presentation) {
        Presentation oldPresentation = repository.getById(presentationId);
        oldPresentation.update(presentation);
    }

    public void delete(int presentationId) {
        Presentation presentation = repository.getById(presentationId);
        presentation.delete();
    }

    public void start(int presentationId) {
        Presentation presentation = repository.getById(presentationId);
        presentation.start();
    }

    public void end(int presentationId) {
        Presentation presentation = repository.getById(presentationId);
        presentation.end();
    }

    public void addComment(Presentation presentation, Comment comment) {
        Member currentMember = securityService.getCurrentMember();
		comment.setWriter(currentMember);
		comment.applyAtHtml();
		presentation.addComment(comment);
		repository.update(presentation);
    }

    public void addUrlResource(Presentation presentation, Resource resource) {
        addResource(presentation, resource);
    }

    private void addResource(Presentation presentation, Resource resource) {
        resource.setOwner(securityService.getCurrentMember());
        presentation.addResource(resource);
    }

    public void addFileResource(Presentation presentation, Resource resource, UploadFile uploadFile) {
        resource.setUploadFile(uploadFile);
        addResource(presentation, resource);
    }

    public void deleteResourceFromPresentation(int presentationId, int resourceId) {
        Presentation presentation = viewPresentation(presentationId);
        Resource resource = resourceRepository.getById(resourceId);
        presentation.deleteResource(resource);
    }

	public Presentation createPresentation(Meeting meeting){
		return meeting.createPresentation();
	}

	public void addPresentation(Meeting meeting, Presentation presentation){
		presentation.setPresenter( memberService.getMemberById(presentation.getPresenter().getId()));
        presentation.setMeeting(meeting);
		meeting.addPresentation(presentation);
	}

	public void deletePresentation(Meeting meeting, int presentationHashCode){
		meeting.deletePresentationByHashCode(presentationHashCode);
	}

    public Presentation viewPresentation(int presentationId) {
        Presentation presentation = repository.getById(presentationId);
        presentation.increaseViewCount();
        return presentation;
    }

    public void deleteComment(int presentationId, int commentId) {
        Presentation presentation = repository.getById(presentationId);
        Comment comment = commentRepository.getById(commentId);
        presentation.removeComment(comment);
        commentRepository.delete(comment);
    }

    /**
     * 코멘트 등록 됬을 때, 해당 발표가 등록된 모임의 참가자들에게 메일을 보낸다.
     * @param presentation
     * @param comment
     */
	public void notifyCommentAdded(Presentation presentation, Comment comment) {
		Iterator<Attendance> Iter = presentation.getMeeting().getAttendances().iterator();
        Collection<Member> members = new ArrayList<Member>();
		while (Iter.hasNext()) members.add(((Attendance) Iter.next()).getMember());
		CommentMailMessage mail = commentMail.get();
		mail.setDatas(comment, presentation.getMeeting(), presentation, members);
		notiService.sendMessage( mail);
	}
}
