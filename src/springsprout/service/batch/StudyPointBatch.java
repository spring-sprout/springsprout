package springsprout.service.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.util.MathUtils;
import springsprout.domain.Meeting;
import springsprout.domain.Study;
import springsprout.modules.study.StudyRepository;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 9
 * Time: 오후 10:42:39
 */
@Service
@Transactional
public class StudyPointBatch {

    @Autowired StudyRepository studyRepository;

//	@Scheduled(fixedDelay=1000*60*60*24)
	public void calcAllStudies(){
		for(Study study : studyRepository.getStudyList()){
			study.clearAttendanceRateOfMeeting();
            for(Meeting m : study.getMeetings()){
                int rate = calcRateOf(m);
                m.setAttendanceRate(rate);
                study.addAttendanceRateOfMeeting(m, rate);
            }
		}
	}

    private int calcRateOf(Meeting meeting) {
        int totalAttendances = meeting.getAttendanceCount();
        int realAttendances = meeting.getAttendedCount();
        return MathUtils.rateByDivide(realAttendances, totalAttendances);
    }

}
