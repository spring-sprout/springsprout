package springsprout.modules.study.meeting;

import springsprout.common.dao.GenericDao;
import springsprout.domain.Meeting;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 2
 * Time: 오후 8:56:41
 */
public interface MeetingRepository extends GenericDao<Meeting>{

    List<Meeting> findActiveMeetings();
    
    List<Meeting> getMeetingListByStudyId(int studyId, int rows);

    List<Meeting> findActiveMeetings(int count);

	Meeting findRecntMeeting(int studyId);
}
