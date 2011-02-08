package springsprout.modules.study.meeting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import springsprout.domain.*;
import springsprout.modules.location.LocationRepository;
import springsprout.modules.study.StudyRepository;
import springsprout.modules.study.exception.JoinMeetingException;
import springsprout.modules.study.exception.MeetingMaximumOverException;
import springsprout.modules.study.meeting.attendance.AttendanceRepository;
import springsprout.modules.study.meeting.presentation.PresentationRepository;
import springsprout.modules.study.meeting.resource.ResourceRepository;
import springsprout.service.notification.UnifiedNotificationService;
import springsprout.service.security.SecurityService;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MeetingServiceImplTest {

	MeetingServiceImpl service;
    
	@Mock SecurityService securityService;
	@Mock StudyRepository studyRepository;
    @Mock PresentationRepository presentationRepository;
    @Mock AttendanceRepository attendanceRepository;
    @Mock MeetingRepository meetingRepository;
    @Mock ResourceRepository resourceRepository;
    @Mock UnifiedNotificationService notiService;
    @Mock LocationRepository locationRepository;

    @Before
	public void setUp() throws Exception {
		service = new MeetingServiceImpl();
		service.securityService = securityService;
		service.studyRepository = studyRepository;
        service.presentationRepository = presentationRepository;
        service.attendanceRepository = attendanceRepository;
        service.meetingRepository = meetingRepository;
        service.resourceRepository = resourceRepository;
        service.notiService = notiService;
        service.locationRepository = locationRepository;
	}

    @Test
    public void createMeeting(){
        int studyId = 1;
        int maxium = 10;
        Study study = new Study();
        study.setId(studyId);
        study.setMaximum(maxium);

        when(studyRepository.getById(studyId)).thenReturn(study);

        Meeting meeting = service.createMeeting(studyId);
        assertNotNull(meeting);
        assertThat(meeting.getStudy(), is(study));
        assertThat(meeting.getMaximum(), is(maxium));
    }

    @Test
    public void deleteResourceFromMeeting(){
        int meetingId = 1;
        int resourceId = 1;
        Meeting meeting = new Meeting();
        meeting.setId(meetingId);
        Resource resource = new Resource();
        resource.setId(resourceId);
        meeting.addResource(resource);

        when(meetingRepository.getById(meetingId)).thenReturn(meeting);
        when(resourceRepository.getById(resourceId)).thenReturn(resource);

        assertThat(meeting.getResources().size(), is(1));
        service.deleteResourceFromMeeting(meetingId, resourceId);
        assertThat(meeting.getResources().size(), is(0));
    }

    @Test
    public void deleteMeeting(){
        Study study = new Study();
        Meeting meeting = new Meeting(study);
        study.addMeeting(meeting);

        assertThat(study.getMeetingCount(), is(1));
        service.deleteMeeting(meeting);
        assertThat(study.getMeetingCount(), is(0));
    }


    @Test
    public void addUrlAndFileResource(){
        Meeting meeting = new Meeting();
        Member member = new Member();
        UploadFile file = new UploadFile();

        when(securityService.getCurrentMember()).thenReturn(member);

        assertThat(meeting.getResources().size(), is(0));
        service.addUrlResource(meeting, new Resource());
        assertThat(meeting.getResources().size(), is(1));

        Resource resource = new Resource();
        service.addFileResource(meeting, resource, file);
        assertThat(meeting.getResources().size(), is(2));
        assertThat(resource.getUploadFile(), is(file));
    }
    
    @Test
	public void confirmAndRejectAttendanceById() throws Exception {
		Attendance attendance = new Attendance();
		attendance.setId(1);
		attendance.setAttended(false);
		when(attendanceRepository.getById(1)).thenReturn(attendance);

		service.confirmAttendanceById(1);
		assertTrue(attendance.isAttended());

		service.rejectAttendanceById(1);
		assertFalse(attendance.isAttended());
	}
	
	@Test
	public void addMeeting() throws Exception {
		int studyId = 1;
		Study study = new Study();
		study.setId(studyId);
		study.setMaximum(10);
		
		Meeting meeting = new Meeting();
		Member member = new Member();
		when(studyRepository.getById(studyId)).thenReturn(study);
		when(securityService.getPersistentMember()).thenReturn(member);

		service.addMeeting(studyId, meeting);
		assertThat(study.getMeetingCount(), is(1));
		assertThat(meeting.getCnt(), is(1));
		assertThat(meeting.getMaximum(), is(10));
		
		meeting = new Meeting();
		
		service.addMeeting(studyId, meeting);
		assertThat(study.getMeetingCount(), is(2));
		assertThat(meeting.getCnt(), is(2));
		assertThat(meeting.getMaximum(), is(10));
	}

	@Test
	public void joinMeetingMember() throws Exception {
		Member currentMember = new Member();
		Meeting meeting = new Meeting();
		Study study = new Study();
		study.addMember(currentMember);
		meeting.setStudy(study);
		meeting.setMaximum(2);

		when(securityService.getPersistentMember()).thenReturn(currentMember);
		service.joinMeetingMember(meeting);
		assertThat(meeting.getAttendances().size(), is(1));

		service.joinMeetingMember(meeting);
		assertThat(meeting.getAttendances().size(), is(1));

		try {
			currentMember = new Member("nije2@email.com");
			when(securityService.getPersistentMember()).thenReturn(currentMember);
			service.joinMeetingMember(meeting);
			fail();
		} catch (JoinMeetingException e) {
			assertThat(e.getMessage(), containsString(currentMember.getName() + "님은 " + study.getStudyName() + " 스터디에 참가 신청하지 않으셨습니다."));
		}

		currentMember = new Member("keesun@email.com");
		study.addMember(currentMember);
		when(securityService.getPersistentMember()).thenReturn(currentMember);
		service.joinMeetingMember(meeting);
		assertThat(meeting.getAttendances().size(), is(2));


		try {
			currentMember = new Member("helols@email.com");
			when(securityService.getPersistentMember()).thenReturn(currentMember);
			service.joinMeetingMember(meeting);
			fail();
		} catch (MeetingMaximumOverException e) {
			assertThat(e.getMessage(), containsString("2 제한 인원을 확인하세요"));
		}
	}

    @Test
	public void leaveMeetingMember() throws Exception {
		Meeting meeting = new Meeting();
		Member member = new Member("keesun@email.com");
		Study study = new Study();
		study.addMember(member);
		meeting.setStudy(study);
		meeting.setMaximum(2);

		when(securityService.getPersistentMember()).thenReturn(member);
		service.joinMeetingMember(meeting);
		assertThat(meeting.getAttendances().size(), is(1));
		assertThat(member.getAttendances().size(), is(1));

		service.leaveMeetingMember(meeting);
		assertThat(meeting.getAttendances().size(), is(0));
		assertThat(member.getAttendances().size(), is(0));
	}

    @Test
    public void updateMeetingAndNotNotify(){
    	int studyId = 1;
		Study study = new Study();
		study.setId(studyId);
		study.setMaximum(10);
    	
        int meetingId = 1;
        Meeting oldMeeting = new Meeting();
        oldMeeting.setId(meetingId);
        oldMeeting.setTitle("oldMeeting");
        study.addMeeting(oldMeeting);
        oldMeeting.setStudy(study);
        
        Meeting newMeeting = new Meeting();
        newMeeting.setTitle("newMeeting");
        when(meetingRepository.getById(meetingId)).thenReturn(oldMeeting);

        boolean isGoingToBeNotified = false;
        service.updateMeeting(studyId, meetingId, newMeeting, isGoingToBeNotified);
        
        assertThat(oldMeeting.getTitle(), is("newMeeting"));
        assertThat(oldMeeting.getId(), is(1));
    }

}
