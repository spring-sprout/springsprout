package springsprout.modules.calendar;

import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.util.ServiceException;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springsprout.domain.Meeting;
import springsprout.domain.Member;
import springsprout.domain.Study;
import springsprout.modules.study.StudyServiceImpl;
import springsprout.modules.study.meeting.MeetingServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;


/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 8. 20
 * Time: 오후 4:43:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
public class GoogleCalendarServiceImplTest {

    @Autowired GoogleCalendarService calendarService;

    @Test
    public void di(){
        assertThat(calendarService, is(notNullValue()));
    }

}


//public class GoogleCalendarServiceTest {
//
//	final Logger logger = LoggerFactory.getLogger(GoogleCalendarServiceTest.class);
//	@Autowired
//    StudyServiceImpl studyService;
//	@Autowired
//    MeetingServiceImpl meetingService;
//	@Autowired static GoogleCalendarServiceImpl service;
//	static Study study;
//	static Meeting meeting;
//	static Member manager;
//
//	@BeforeClass
//	public static void beforeClass(){
//		service = new GoogleCalendarServiceImpl();
//		service.clear();
//		dataSet();
//	}
//
//	private static void dataSet() {
//		study = new Study();
//		study.setId(100);
//		study.setStudyName("디폴트 캘린더");
//		study.setDescr("디폴트 캘린더");
//
//		manager = new Member();
//		manager.setEmail("ethdemor@gmail.com");
//		study.setManager(manager);
//
//		meeting = new Meeting();
//		meeting.setId(100);
//		meeting.setTitle("디폴트 모임");
//		meeting.setContents("디폴트 모임");
//		meeting.setStudy(study);
//
//		Calendar cal = Calendar.getInstance();
//		cal.set(2010, Calendar.AUGUST, 21, 16, 00, 00);
//		meeting.setOpenDate(cal.getTime());
//		meeting.setOpenTime(cal.getTime());
//		cal.set(2010, Calendar.AUGUST, 21, 21, 00, 00);
//		meeting.setCloseDate(cal.getTime());
//		meeting.setCloseTime(cal.getTime());
//
//		service.createNewStudyCalendar(study);
//		service.createNewMeetingEvent(meeting);
//	}
//
//	@Test
//	public void create() {
//		assertThat(service, is(notNullValue()));
//		assertThat(study.getCalendarId(), is(notNullValue()));
//		assertThat(meeting.getEventId(), is(notNullValue()));
//		assertThat(study.getManager().getEmail(), is("ethdemor@gmail.com"));
//	}
//
//	@Test
//	public void isRealStatic() throws Exception {
//		assertThat(study.getCalendarId(), is(notNullValue()));
//		assertThat(meeting.getEventId(), is(notNullValue()));
//	}
//
//	@Test
//	public void getCalendarList() throws IOException, ServiceException {
//		assertThat(service.getOwnCalendarList().size(), is(2));
//	}
//
//	@Ignore
//    @Test
//	public void createNewStudyCalendar() throws IOException, ServiceException {
//		// Setup
//		needRealGoogleService();
//
//		// Run
//		CalendarEntry calendar = service.createNewStudyCalendar(study);
//
//		// Verify
//		assertThat(calendar.getTitle().getPlainText(), is(study.getStudyName()));
//		assertThat(calendar.getId(), containsString(study.getCalendarId()));
//	}
//
//	@Ignore @Test
//	public void updateStudyCalendar(){
//		study.setStudyName("스프링 4.0");
//		study.setDescr("토비의 스프링 4로 즐겁게!!");
//		service.updateStudyCalendar(study);
//	}
//
//	@Ignore @Test
//	public void deleteStudyCalendar() throws Exception {
//		// Setup
//		needRealGoogleService();
//		service.createNewStudyCalendar(study);
//		service.createNewMeetingEvent(meeting);
//
//		// Run
//		service.deleteStudyCalendar(study);
//	}
//
//	@Ignore @Test
//	public void createNewMeetingEvent() {
//		// Setup
//		needRealGoogleService();
//		study.setId(100);
//		study.setStudyName("켄트백과 함께하는 TDD");
//		study.setDescr("TDD로 간단한 분산 네트워크 프레임워크 개발");
//		service.createNewStudyCalendar(study); // 이벤트를 등록할 Calendar 필요
//		meeting.setStudy(study);
//
//		// Run
//		CalendarEventEntry newEvent = service.createNewMeetingEvent(meeting);
//
//		// Verify
//		assertThat(newEvent.getTitle().getPlainText(), is(meeting.getTitle()));
//		assertThat(newEvent.getId(), containsString(meeting.getEventId()));
//	}
//
//	@Ignore @Test
//	public void makeOtherCalendarUrl() throws Exception {
//		// Setup
//		needRealGoogleService();
//		study.setId(200);
//		study.setStudyName("엉클 밥과 함께하는 클린코드");
//		study.setDescr("간단하면서 깔끔한 코드 만들기");
//		service.createNewStudyCalendar(study); // CalendarEntry가 필요
//
//		// Run
//		URL url = service.makeOtherCalendarUrl(meeting.getStudy());
//
//		// Verify
//		assertThat(url.toString(), containsString((study.getCalendarId())));
//	}
//
//	@Ignore @Test
//	public void removeMeetingEvent() throws Exception {
//		// Setup
//		needRealGoogleService();
//		study.setId(300);
//		study.setStudyName("엉클 밥과 함께하는 클린코드");
//		study.setDescr("간단하면서 깔끔한 코드 만들기");
//		service.createNewStudyCalendar(study); // Calendar 필요
//		service.createNewMeetingEvent(meeting); // 삭제할 Event 필요
//
//		// Run
//		service.deleteMeetingEvent(meeting);
//	}
//
//	@Ignore @Test
//	public void findEvent() throws Exception {
//		// Setup
//		needRealGoogleService();
//		study.setId(5151);
//		study.setStudyName("조슈아와 함께하는 이펙티브 자바 1주차");
//		study.setDescr("API 만들려면 이정도는 알아야지?");
//		service.createNewStudyCalendar(study);
//		service.createNewMeetingEvent(meeting);
//
//		// Run
//		CalendarEventEntry foundEvent = service.findEvent(meeting);
//
//		// Verify
//		assertThat(foundEvent.getTitle().getPlainText(), is(meeting.getTitle()));
//		assertThat(foundEvent.getId(), containsString(meeting.getEventId()));
//	}
//
//	@Ignore @Test
//	public void updateMeetingEvent() throws Exception {
//		// Setup
//		needRealGoogleService();
//		study.setId(300);
//		study.setStudyName("봄싹 개론");
//		study.setDescr("봄싹이란 무엇인가?");
//		service.createNewStudyCalendar(study);
//		service.createNewMeetingEvent(meeting);
//
//		// Run
//		meeting.setTitle("첫번째 강좌");
//		meeting.setContents("봄싹의 정의와 개요!");
//		Calendar cal = Calendar.getInstance();
//		cal.set(2010, Calendar.AUGUST, 14, 14, 30, 00);
//		meeting.setOpenDate(cal.getTime());
//		meeting.setOpenTime(cal.getTime());
//		cal.set(2010, Calendar.AUGUST, 14, 18, 00, 00);
//		meeting.setCloseDate(cal.getTime());
//		meeting.setCloseTime(cal.getTime());
//
//		CalendarEventEntry updatedEvent = service.updateMeetingEvent(meeting);
//
//		// Verify
//		assertThat(updatedEvent.getTitle().getPlainText(), is(meeting.getTitle()));
//		assertThat(updatedEvent.getId(), containsString(meeting.getEventId()));
//		assertThat(updatedEvent.getEtag(), is(meeting.getEtag()));
//
//		// Setup
//		meeting.setTitle("두 번째 강좌");
//		meeting.setContents("봄싹의 역사");
//		updatedEvent = service.updateMeetingEvent(meeting);
//
//		// Verify
//		assertThat(updatedEvent.getTitle().getPlainText(), is(meeting.getTitle()));
//		assertThat(updatedEvent.getId(), containsString(meeting.getEventId()));
//		assertThat(updatedEvent.getEtag(), is(meeting.getEtag()));
//	}
//
//	private void needRealGoogleService() {
//		service = new GoogleCalendarServiceImpl();
//	}
//}
