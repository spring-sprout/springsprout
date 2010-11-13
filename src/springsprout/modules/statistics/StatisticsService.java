package springsprout.modules.statistics;

import springsprout.domain.Statistics;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 6
 * Time: 오후 11:14:43
 */
public interface StatisticsService {

    Statistics getStudyAttendanceNumPerMeeting();

    Statistics getMemberNumPerDay();
}
