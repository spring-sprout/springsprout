
package springsprout.service.batch;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springsprout.common.util.MathUtils;
import springsprout.domain.Member;
import springsprout.domain.Study;
import springsprout.modules.member.MemberRepository;
import springsprout.modules.study.meeting.attendance.AttendanceRepository;

@Service
@Transactional
public class MemberPointBatch {
	
	@Autowired MemberRepository memberRepository;
    @Autowired AttendanceRepository attendanceRepository;

	@Scheduled(fixedDelay=1000*60*60*24)
	public void calcAllMember(){
		for(Member m : memberRepository.getJoinedMemberList()){
			calcRatesOf(m);
		}
	}
	
	public void calcRatesOf(Member member) {
		member.clearAttendanceRates();
		member.clearTrustRates();
		
		Set<Study> studies = member.getStudies();
		int totalMeetingCount = 0;
		for (Study study : studies) {
			addNewStudyAttendanceRate(member, study);
			addNewStudyTrustRate(member, study);
			totalMeetingCount += study.getMeetingCount();
		}
		int confirmedAttendanceCount = attendanceRepository.getConfirmedAttendanceCountOf(member);
		int totalAttendanceCount = attendanceRepository.getTotalAttandanceCountOf(member);

		member.setTotalAttendanceRate(MathUtils.rateByDivide(confirmedAttendanceCount, totalMeetingCount));
		member.setTotalTrustRate(MathUtils.rateByDivide(confirmedAttendanceCount, totalAttendanceCount));
	}

	private void addNewStudyTrustRate(Member member, Study study) {
		int attendanceCount = attendanceRepository.getAttendanceCountOf(member, study);
		int confirmedAttendanceCount = attendanceRepository.getConrimedAttendanceCountOf(member, study);
		int rate = MathUtils.rateByDivide(confirmedAttendanceCount, attendanceCount);
		member.addStudyTrustRate(study, rate);
	}

	private void addNewStudyAttendanceRate(Member member, Study study) {
		int confirmedAttendanceCount = attendanceRepository.getConrimedAttendanceCountOf(member, study);
		int meetingCount = study.getMeetingCount();
		int rate = MathUtils.rateByDivide(confirmedAttendanceCount, meetingCount);
		member.addStudyAttendanceRate(study, rate);
	}

}
