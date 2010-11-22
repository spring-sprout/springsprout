package springsprout.common.util;

import org.junit.Test;
import springsprout.common.enumeration.DayOfWeek;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 21
 * Time: 오전 10:15:43
 */
public class DateUtilsTest {

    @Test
    public void jsDate(){
        String result = DateUtils.makeJsDate(new Date());
        System.out.println(result);
        System.out.println(DateUtils.make년월일(new Date()));

    }

    @Test
    public void pubDate(){
        assertThat(DateUtils.getShortDate(new Date()), is("0분전"));
//        assertThat(DateUtils.getShortDate(new GregorianCalendar(2009, Calendar.JANUARY, 10).getTime()), is("14달전"));
//        assertThat(DateUtils.getShortDate(new GregorianCalendar(2010, Calendar.JANUARY, 10).getTime()), is("2달전"));
//        assertThat(DateUtils.getShortDate(new GregorianCalendar(2010, Calendar.FEBRUARY, 10).getTime()), is("1달전"));
//        assertThat(DateUtils.getShortDate(new GregorianCalendar(2010, Calendar.MARCH, 10).getTime()), is("11일전"));
    }

    @Test
    public void dateOf(){
        Date date = DateUtils.dateOf(2010, 7, 1, 12, 20);
        assertThat(DateUtils.makeYYYYMMDD(date, "-"), is("2010-07-01"));
    }

    @Test
    public void dayOfWeek(){
        DayOfWeek dayOfWeek = DateUtils.extractDayOfMeekFrom(DateUtils.dateOf(2010, 7, 1, 12, 20));
        assertThat(dayOfWeek, is(DayOfWeek.THURSDAY));
    }
}
