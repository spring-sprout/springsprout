package springsprout.service.notification.message;

import org.junit.Test;
import springsprout.domain.Meeting;
import springsprout.domain.Member;
import springsprout.domain.Study;
import springsprout.domain.enumeration.MeetingStatus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class MeetingMailMessageTest {
	
	@Test
	public void create() throws Exception {
		Meeting meeting = new Meeting();
		meeting.setTitle("테스트 모임");
		meeting.setContents("방가방가 hihi");
		meeting.setOwner(new Member());
		Study study = new Study();
		meeting.setStudy(study);
		MeetingMailMessage mmm = new MeetingMailMessage(meeting, MeetingStatus.OPEN);
		assertThat(mmm.getFrom(), is("s2cmailer@gmail.com"));
		assertTrue(mmm.getTitle().contains(meeting.getTitle()));
		assertTrue(mmm.getTitle().contains(SpringSproutMail.SUBJECT_PREFIX));
		assertTrue(mmm.getContents().contains(meeting.getContents()));
	}

}
