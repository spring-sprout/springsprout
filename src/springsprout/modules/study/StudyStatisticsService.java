package springsprout.modules.study;

import springsprout.domain.Meeting;
import springsprout.modules.study.support.MeetingDayOfWeekData;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 11. 21
 * Time: 오후 9:46:03
 */
public interface StudyStatisticsService {
    List<MeetingDayOfWeekData> getMeetingDayStatisticsOf(Set<Meeting> meetings);
}
