package springsprout.service.batch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import springsprout.domain.Meeting;
import springsprout.domain.Member;
import springsprout.domain.Study;
import springsprout.modules.member.MemberRepository;
import springsprout.modules.study.meeting.attendance.AttendanceRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

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
		/**
		 * 멤버 한명이 스터디 두개 가입.
		 * 첫번째 스터디에서는 모임 2개 진행하고 두번째 스터디에서는 모임 1개 진행함.
		 * 멤버는 이 중에서 첫번쨰 스터디의 두개 모임에 참석 신청한 다음 실제로는 한개 모임에만 참석했다.
		 *
		 */
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
		when(attendanceRepository.getAttendanceCountOf(member, study1)).thenReturn(2);
		when(attendanceRepository.getAttendanceCountOf(member, study2)).thenReturn(0);
		memberPointBatch.calcRatesOf(member);

		/**
		 * 전체참석률 33%: 총 세번의 모임 중에 한번 참석했다.
		 * 전체신뢰도 50%: 참석 신청 두번 중에 한번 참석했다.
		 * 스터디별 참석률갯수 2개: 스터디 갯수.
		 * 첫번째 스터디 참석률 50%: 두번의 모임 중에 한번 참석했다.
		 * 두번째 스터디 참석률 0%: 두번의 모임 중에 한번도 참석하지 못했다.
		 * 스터디별 신뢰도갯수 2개: 스터디 갯수.
		 * 첫번째 스터디 신뢰도 50%: 두번의 모임 신청 중에 한번 참석했다.
		 * 두번째 스터디 신뢰도 0%: 참석 신청 이력이 없음.
		 */
		assertThat(member.getTotalAttendanceRate(), is(33));
		assertThat(member.getTotalTrustRate(), is(50));
		assertThat(member.getStudyAttendanceRates().size(), is(2));
		assertThat(member.getStudyAttendanceRates().get(study1), is(50));
		assertThat(member.getStudyAttendanceRates().get(study2), is(0));
		assertThat(member.getStudyTrustRates().size(), is(2));
		assertThat(member.getStudyTrustRates().get(study1), is(50));
		assertThat(member.getStudyTrustRates().get(study2), is(0));
	}

}
