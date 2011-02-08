package springsprout.modules.study.meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.domain.*;
import springsprout.domain.enumeration.MeetingStatus;
import springsprout.modules.comment.CommentRepository;
import springsprout.modules.location.LocationRepository;
import springsprout.modules.study.StudyRepository;
import springsprout.modules.study.exception.JoinMeetingException;
import springsprout.modules.study.exception.MeetingMaximumOverException;
import springsprout.modules.study.meeting.attendance.AttendanceRepository;
import springsprout.modules.study.meeting.presentation.PresentationRepository;
import springsprout.modules.study.meeting.resource.ResourceRepository;
import springsprout.service.notification.NotificationService;
import springsprout.service.notification.message.CommentMailMessage;
import springsprout.service.notification.message.MeetingMailMessage;
import springsprout.service.security.SecurityService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class MeetingServiceImpl implements MeetingService {

    @Autowired SecurityService securityService;
    @Autowired StudyRepository studyRepository;
    @Autowired MeetingRepository meetingRepository;
    @Autowired PresentationRepository presentationRepository;
    @Autowired ResourceRepository resourceRepository;
    @Autowired AttendanceRepository attendanceRepository;
    @Autowired LocationRepository locationRepository;
    @Autowired CommentRepository commentRepository;
    @Autowired @Qualifier("unifiedNotificationService") NotificationService notiService;
    @Autowired @Qualifier("sendMailService") NotificationService commentNotiService;

	public void addComment(Meeting meeting, Comment comment) {
		Member currentMember = securityService.getCurrentMember();
		comment.setWriter(currentMember);
		comment.applyAtHtml();
		meeting.addComment(comment);
	}

	public void addUrlResource(Meeting meeting, Resource resource) {
		addResource(meeting, resource);
	}

    private void addResource(Meeting meeting, Resource resource) {
        resource.setOwner(securityService.getCurrentMember());
        meeting.addResource(resource);
    }

    public void addFileResource(Meeting meeting, Resource resource, UploadFile uploadFile) {
		resource.setUploadFile(uploadFile);
		addResource(meeting, resource);
	}

    public List<Meeting> findActiveMeetings() {
        return meetingRepository.findActiveMeetings();
    }

    public void deleteComment(int meetingId, int commentId) {
        Meeting meeting = meetingRepository.getById(meetingId);
        Comment comment = commentRepository.getById(commentId);
        if(securityService.getCurrentMemberId() != comment.getWriter().getId())
            throw new AuthorizationServiceException("you can delete your comment only!");
        meeting.removeComment(comment);
        commentRepository.delete(comment);
    }

    public void openMeeting(Meeting meeting) {    	
        meeting.openMeeting();
    }


    public void endMeeting(Meeting meeting) {
		meeting.endMeeting();
	}

	public Attendance confirmAttendanceById(int attendanceId) {
		Attendance attendance = attendanceRepository.getById(attendanceId);
		attendance.setAttended(true);
		return attendance;
	}

	public Attendance rejectAttendanceById(int attendanceId) {
		Attendance attendance = attendanceRepository.getById(attendanceId);
		attendance.setAttended(false);
		return attendance;
	}

	public void leaveMeetingMember(Meeting meeting) {
		Member currentMember = securityService.getPersistentMember();
		Attendance attendance = currentMember.cancleAttendance(meeting);
		attendanceRepository.delete(attendance);
	}

	public void joinMeetingMember(Meeting meeting) throws MeetingMaximumOverException, JoinMeetingException {
		if(meeting.getMaximum() <= meeting.getAttendances().size())
			throw new MeetingMaximumOverException(meeting.getMaximum() + " 제한 인원을 확인하세요.");
		Member currentMember = securityService.getPersistentMember();
		if(!meeting.isJoinable(currentMember))
			throw new JoinMeetingException(currentMember.getName() + "님은 " + meeting.getStudy().getStudyName() + " 스터디에 참가 신청하지 않으셨습니다.");
		Attendance attendance = currentMember.applyAttendance(meeting);
		attendanceRepository.add(attendance);
	}

	public void updateMeeting(Integer studyId, Integer meetingId, Meeting newMeeting, Boolean isGoingToBeNotified) {
        locationRepository.save(newMeeting.getLocation());
        Meeting oldMeeting = meetingRepository.getById(meetingId);
        oldMeeting.update(newMeeting);

        if (isGoingToBeNotified) {
            notiService.sendMessage(
                new MeetingMailMessage(studyRepository.getById(studyId), oldMeeting, MeetingStatus.UPDATED));
        }
	}

	@PreAuthorize("(#meeting.owner.email == principal.Username) or (#meeting.study.manager.email == principal.Username) or hasRole('ROLE_ADMIN')")
	public void deleteMeeting(Meeting meeting) {
        meeting.delete();
    }

	public void deleteResourceFromMeeting(int meetingId, int resourceId) {
		Meeting meeting = meetingRepository.getById(meetingId);
		Resource resource = resourceRepository.getById(resourceId);
		meeting.removeResource(resource);
	}

	public Meeting createMeeting(Integer studyId) {
		Study study = studyRepository.getById(studyId);				
		return new Meeting(study);
	}

	public void addMeeting(Integer studyId, Meeting meeting){
		locationRepository.add(meeting.getLocation());
		Member currentMember = securityService.getCurrentMember();
		meeting.addAttendance(new Attendance(meeting, currentMember));
		meeting.setOwner(currentMember);
		
		Study study = studyRepository.getById(studyId);
		study.addMeeting(meeting);
		study.openStudy();
		
		studyRepository.update(study);
		studyRepository.flush();

        notiService.sendMessage(new MeetingMailMessage(study, meeting, MeetingStatus.OPEN));
	}

    public Meeting getById(int meetingId) {
        return meetingRepository.getById(meetingId);
    }
    
    public List<Meeting> getMeetingListByStudyId(int studyId, int rows) {
        return meetingRepository.getMeetingListByStudyId(studyId, rows);
    }
    
    public List<Presentation> getPresentationListByMeetingIdWithSort( int meetingId) {
    	return presentationRepository.getPresentationListSortByActivity( meetingId);
    }

    public void notify(int meetingId) {
        Meeting meeting = meetingRepository.getById(meetingId);
        notiService.sendMessage(new MeetingMailMessage(meeting.getStudy(), meeting, MeetingStatus.UPDATED));        
    }

    /**
     * Presentation에 있는 거하고 똑같은 기능이넴.
     */
	public void notifyCommentAdded(Meeting meeting, Comment comment) {
		Iterator<Attendance> Iter = meeting.getAttendances().iterator();
        Collection<Member> members = new ArrayList<Member>();
		while (Iter.hasNext()) members.add(((Attendance) Iter.next()).getMember());
		commentNotiService.sendMessage( new CommentMailMessage( comment, meeting, null, members));
	}

    public List<Meeting> findActiveMeetings(int count) {
        return meetingRepository.findActiveMeetings(count);
    }
}
