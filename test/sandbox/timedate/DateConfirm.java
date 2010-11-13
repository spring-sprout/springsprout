package sandbox.timedate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 8. 4
 * Time: 오전 11:27:11
 */
public class DateConfirm {

    public static void main(String[] args) {
        System.out.println(new Date().toGMTString());
        System.out.println(new Date().getTimezoneOffset());

        java.util.TimeZone tz = java.util.TimeZone.getDefault();
        System.out.println("Timezone offset from UTC reported as " +
                (tz.getRawOffset() / 1000 / 60) + " minutes");
        if (tz.getRawOffset() % (15 * 60 * 1000) != 0) {
            System.out.println("Warning: not a multiple of quarter-hours");
        }
        System.out.println(new java.util.Date());
        System.out.println(tz);

        TimeZone tz2;
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss (z Z)");

        tz2 = TimeZone.getTimeZone("Asia/Seoul");
        df.setTimeZone(tz2);
        System.out.format("%s%n%s%n%n", tz2.getDisplayName(), df.format(date));
    }

}
