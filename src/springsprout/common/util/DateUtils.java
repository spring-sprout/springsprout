package springsprout.common.util;

import springsprout.common.enumeration.DayOfWeek;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	
	public static final String 년월일 = "yyyy" + "년" + " M" + "월" + " d" + "일";
	public static final String yyyyMMdd_SLASH = "yyyy/MM/dd";
	public static final String HHmm_COLON = "HH:mm";
	public static final String HHMMA = "a h:mm";
	
	private DateUtils() {}
	
	public static String makeYYYYMMDD(Date date, String delim) {
		DateFormat format = new SimpleDateFormat("yyyy" + delim + "MM" + delim + "dd");
		return format.format(date);
	}

    public static String makeJsDate(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
		return calendar.get(Calendar.YEAR) + ", " + calendar.get(Calendar.MONTH) + ", " + calendar.get(Calendar.DATE);
	}

	public static String makeHHMMA(Date time) {
		DateFormat format = new SimpleDateFormat(HHMMA);
		return format.format(time);
	}

	public static String make년월일(Date date) {
		DateFormat format = new SimpleDateFormat(년월일);
		return format.format(date);
	}

    public static String getShortDate(Date pubDate) {
        String pubDateText = "";
        if (pubDate != null) {
            long time = new Date().getTime() - pubDate.getTime();
            long day = time / 1000 / 60 / 60 / 24;

            if (day == 0) {
                long hrs = time / 1000 / 60 / 60;
                if (hrs == 0) {
                    pubDateText = (time / 1000 / 60) + "분전";
                } else {
                    pubDateText = hrs + "시간전";
                }
            } else if (day == 1) {
                pubDateText = "어제";
            } else if (day < 31) {
                pubDateText = day + "일전";
            } else {
                pubDateText = day/30 + "달전";
            }
        }
        return pubDateText;
    }

    public static Date dateOf(int year, int month, int day, int hour, int min) {
        GregorianCalendar calendar = new GregorianCalendar(year, month-1, day, hour, min);
        return calendar.getTime();
    }

    public static DayOfWeek extractDayOfMeekFrom(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.MONDAY: return DayOfWeek.MONDAY;
            case Calendar.TUESDAY: return DayOfWeek.TUESDAY;
            case Calendar.WEDNESDAY: return DayOfWeek.WEDNESDAY;
            case Calendar.THURSDAY: return DayOfWeek.THURSDAY;
            case Calendar.FRIDAY: return DayOfWeek.FRIDAY;
            case Calendar.SATURDAY: return DayOfWeek.SATURDAY;
            case Calendar.SUNDAY: return DayOfWeek.SUNDAY;
            default: throw new IllegalArgumentException("요일 판별 할 수 없음 [" + date + "]");
        }
    }
}
