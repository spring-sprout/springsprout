package springsprout.modules.calendar;

import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.util.ServiceException;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 8. 10
 * Time: 오후 2:34:32
 */
public interface CalendarServiceCallBack<T> {
    T queryForObject() throws IOException, ServiceException;
}
