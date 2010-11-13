package springsprout.domain;

import org.junit.Test;
import springsprout.common.util.DateUtils;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 4. 22
 * Time: 오후 4:06:10
 */
public class SeminarDetailScheduleTest {

    /**
     * 세부 일정 시간 계산
     */
    @Test
    public void duration(){
        SeminarDetailSchedule schedule = new SeminarDetailSchedule();
        schedule.setStartTime(DateUtils.dateOf(2010, 7, 1, 2, 30));
        schedule.setEndTime(DateUtils.dateOf(2010, 7, 1, 5, 00));
        assertThat(schedule.getDuration(), is(150));

        schedule.setStartTime(DateUtils.dateOf(2010, 7, 1, 2, 30));
        schedule.setEndTime(DateUtils.dateOf(2010, 7, 1, 4, 00));
        assertThat(schedule.getDuration(), is(90));
    }

}
