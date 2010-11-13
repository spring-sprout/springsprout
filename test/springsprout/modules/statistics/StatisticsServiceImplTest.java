package springsprout.modules.statistics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springsprout.domain.Statistics;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 7
 * Time: 오후 5:54:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/testContext.xml"})
public class StatisticsServiceImplTest {

    @Autowired StatisticsService statisticsService;

    @Test
    public void testGetStudyAttendanceNumPerMeeting() throws Exception {
        Statistics studyStatistics = statisticsService.getStudyAttendanceNumPerMeeting();
//        System.out.println(studyStatistics);
    }
}
