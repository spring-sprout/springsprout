package springsprout.modules.calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class CalendarUtilTest {
	
	@Ignore @Test
	public void toGMT() throws Exception {
		// 2010년 8월 19일 오후 2시
		String defaultTimeString = "20100819T140000";
		String transformedTimeString = CalendarUtil.toGMT(defaultTimeString);
		
		assertThat(transformedTimeString, is("20100819T050000")); // 9시간 전
		
		// 2010년 8월 19일 오전 8시
		String defaultTimeString2 = "20100819T080000";
		String transformedTimeString2 = CalendarUtil.toGMT(defaultTimeString2);
		
		assertThat(transformedTimeString2, is("20100818T230000"));
		
		// 2010년 8월 19일 오후 10시
		String defaultTimeString3 = "20100819T220000";
		String transformedTimeString3 = CalendarUtil.toGMT(defaultTimeString3);
		
		assertThat(transformedTimeString3, is("20100819T130000"));
	}
	
	@Test
	public void forExactSearching() throws Exception {
		String keyword = "Hello";
		keyword = CalendarUtil.forExactSearching(keyword);
		
		assertThat("\"Hello\"", is(keyword));
		
		String keyword2 = "Java";
		keyword2 = CalendarUtil.forExactSearching(keyword2);
		
		assertThat("\"Java\"", is(keyword2));
		
		String keyword3 = "한글도 잘될까?";
		keyword3 = CalendarUtil.forExactSearching(keyword3);
		
		assertThat("\"한글도 잘될까?\"", is(keyword3));
	}
}
