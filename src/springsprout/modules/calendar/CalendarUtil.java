package springsprout.modules.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class CalendarUtil {
	
	/**
	 * 문자열을 하나 받아서 GMT 시간대로 바꾼 후, 문자열로 리턴합니다.
	 * @param localTime
	 * @return
	 * @throws ParseException
	 */
	public static String toGMT(String localTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
		Date targetDate = formatter.parse(localTime);
		SimpleTimeZone stz = new SimpleTimeZone(0, "GMT");
		formatter.setTimeZone(stz);
		return formatter.format(targetDate);
	}
	
	/**
	 * 검색할 단어를 그냥 사용할 경우, 검색률이 현저히 떨어지는 현상을 발견했습니다.
	 * 구글에서 '반드시 단어가 포함된'을 나타내는 큰 따옴표를 붙여서, 정확한 검색을
	 * 할 수 있게 합니다.
	 * @param keyword 검색할 단어
	 * @return "keyword"
	 */
	public static String forExactSearching(String keyword) {
		StringBuilder sb = new StringBuilder();
		sb.append("\"");
		sb.append(keyword);
		sb.append("\"");
		return sb.toString();
	}
}
