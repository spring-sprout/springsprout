package springsprout.common.conversion.formatter;

import org.springframework.format.Formatter;
import springsprout.common.util.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 2. 18
 * Time: 오후 12:34:26
 * To change this template use File | Settings | File Templates.
 */
public class DateFormatter implements Formatter<Date> {

    DateFormat dateFormat = new SimpleDateFormat(DateUtils.yyyyMMdd_SLASH);

    public Date parse(String text, Locale locale) throws ParseException {
        System.out.println("Formatter win!!!!!!!!!!!!!!!!!!!");
        return dateFormat.parse(text);
    }

    public String print(Date date, Locale locale) {
        System.out.println("Formatter win!!!!!!!!!!!!!!!!!!!");
        return dateFormat.format(date);
    }
}
