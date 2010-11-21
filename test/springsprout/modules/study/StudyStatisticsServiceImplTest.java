package springsprout.modules.study;

import org.junit.Test;
import springsprout.common.enumeration.DayOfWeek;
import springsprout.common.util.DateUtils;
import springsprout.domain.Meeting;
import springsprout.modules.study.support.MeetingDayOfWeekData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 11. 21
 * Time: 오후 9:50:58
 */
public class StudyStatisticsServiceImplTest {

    @Test
    public void testGetMeetingDayStatisticsOf() throws Exception {
        StudyStatisticsService service = new StudyStatisticsServiceImpl();

        Set<Meeting> meetings = new HashSet<Meeting>();

        Meeting meeting = new Meeting();
        meeting.setOpenDate(DateUtils.dateOf(2010, 11, 1, 0, 0)); // MONDAY
        meetings.add(meeting);

        List<MeetingDayOfWeekData> list = service.getMeetingDayStatisticsOf(meetings);
        assertThat(list.size(), is(1));

        MeetingDayOfWeekData data = list.get(0);
        assertThat(data.getDayOfWeek(), is(DayOfWeek.MONDAY));
        assertThat(data.getMeetingCount(), is(1));
        assertThat(data.getPercentage(), is(100));
    }
}
