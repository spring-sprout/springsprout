package springsprout.common.conversion.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeToWebStringConverter implements Converter<Date, String>{

	public String convert(Date source) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(source);
		
		StringBuilder sb = new StringBuilder();
		sb.append(cal.getDisplayName(Calendar.AM_PM, Calendar.LONG, Locale.KOREAN));
		sb.append(" ");
		sb.append(cal.get(Calendar.HOUR));
		sb.append("시 ");
		sb.append(cal.get(Calendar.MINUTE));
		sb.append("분");
		return sb.toString();
	}

}
