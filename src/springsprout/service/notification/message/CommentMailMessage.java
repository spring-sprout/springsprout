package springsprout.service.notification.message;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import springsprout.domain.Comment;
import springsprout.domain.Meeting;
import springsprout.domain.Member;
import springsprout.domain.Presentation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 모임 및 발표에 Comment가 등록되었을 경우 알림 메시지를 발송 한다.
 * @author JUNE
 *
 */
@Service
public class CommentMailMessage extends SpringSproutMailMessage {
	@Autowired VelocityEngine velocityEngine;
	private Comment comment;
	private Meeting meeting;
	private Presentation presentation;
	
	public CommentMailMessage() {super();}
	
	public CommentMailMessage( Comment comment, Meeting meeting, Presentation presentation, Collection<Member> members) {
		super(members);
		this.comment = comment;
		this.meeting = meeting;
		this.presentation = presentation;
	}
	
	public void setDatas( Comment comment, Meeting meeting, Presentation presentation, Collection<Member> members) {
		super.setMembers(members);
		this.comment = comment;
		this.meeting = meeting;
		this.presentation = presentation;
	}
	
	@Override
	public String getMessage() {
		// TODO 이건 머하는 거지
		return "message";
	}

	@Override
	public String getTitle() {
		if ( presentation == null) return SpringSproutMail.SUBJECT_PREFIX + " [" + meeting.getTitle() + "] 모임에 의견이 추가 되었습니다.";
		return SpringSproutMail.SUBJECT_PREFIX + " [" + meeting.getTitle() + "] 모임의 [" + presentation.getTitle() + "] 발표에 의견이 추가 되었습니다.";
	}

	@Override
	public String getContents() {
		return makeContents();
	}

	private String makeContents() {
		Map<String, String> model = new HashMap<String, String>();
		model.put("author", comment.getWriter().getName());
		model.put("studyName", meeting.getStudy().getStudyName());
		model.put("message", comment.getComment());
		model.put("studyLink", "http://www.springsprout.org/study/" + meeting.getStudy().getId());
		model.put("createdAt", DateFormatUtils.formatUTC(comment.getCreated(), "yyyy-MM-dd, HH:mm:ss"));
		if ( presentation == null) {
			model.put("contentType", "모임");
			model.put("meetingLink", "http://www.springsprout.org/study/" + meeting.getStudy().getId() + "/meeting/" + meeting.getId());
		} else {
			model.put("contentType", "발표");
			model.put("meetingLink", "http://www.springsprout.org/study/" + meeting.getStudy().getId() + "/meeting/" + meeting.getId() + "/presentation/" + presentation.getId());
		}
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "commentNotifyMail.html", "UTF-8", model);
	}

}
