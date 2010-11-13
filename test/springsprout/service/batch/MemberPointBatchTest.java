package springsprout.service.batch;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import springsprout.domain.Member;
import springsprout.domain.Meeting;
import springsprout.domain.Study;
import springsprout.modules.member.MemberRepository;
import springsprout.modules.study.meeting.attendance.AttendanceRepository;

@RunWith(MockitoJUnitRunner.class)
public class MemberPointBatchTest {
	
	MemberPointBatch memberPointBatch;
	@Mock MemberRepository mockMemberRepository;
	@Mock AttendanceRepository attendanceRepository;

	@Before
	public void setUp(){
		memberPointBatch = new MemberPointBatch();
		memberPointBatch.memberRepository = mockMemberRepository;
		memberPointBatch.attendanceRepository = attendanceRepository;
	}
	
	@Test
	public void calcRatesOf() throws Exception {
		Member member = new Member();
		Study study1 = new Study();
		Meeting meeting1 = new Meeting();
		Meeting meeting2 = new Meeting();
		Meeting meeting3 = new Meeting();
		Study study2 = new Study();

		study1.addMeeting(meeting1);
		study1.addMeeting(meeting2);
		study2.addMeeting(meeting3);
		member.join(study1);
		member.join(study2);
		member.applyAttendance(meeting1);
		member.applyAttendance(meeting2);
		
		int attendanceSize = member.getAttendances().size();
		assertThat(attendanceSize, is(2));
		assertThat(member.getStudies().size(), is(2));
		assertThat(study1.getMeetingCount(), is(2));
		assertThat(study2.getMeetingCount(), is(1));

		when(attendanceRepository.getConfirmedAttendanceCountOf(member)).thenReturn(1);
		when(attendanceRepository.getTotalAttandanceCountOf(member)).thenReturn(attendanceSize);
		when(attendanceRepository.getConrimedAttendanceCountOf(member, study1)).thenReturn(1);
		when(attendanceRepository.getConrimedAttendanceCountOf(member, study2)).thenReturn(0);
		when(attendanceRepository.getAttendanceCountOf(member, study1)).thenReturn(1);
		when(attendanceRepository.getAttendanceCountOf(member, study2)).thenReturn(0);
		memberPointBatch.calcRatesOf(member);
		
		assertThat(member.getTotalAttendanceRate(), is(33));
		assertThat(member.getTotalTrustRate(), is(50));
		assertThat(member.getStudyAttendanceRates().size(), is(2));
		assertThat(member.getStudyAttendanceRates().get(study1), is(50));
		assertThat(member.getStudyAttendanceRates().get(study2), is(0));
		assertThat(member.getStudyTrustRates().get(study1), is(100));
		assertThat(member.getStudyTrustRates().get(study2), is(0));
	}

}
