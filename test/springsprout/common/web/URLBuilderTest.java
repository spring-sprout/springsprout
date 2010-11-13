package springsprout.common.web;

import static org.junit.Assert.*;

import org.junit.Test;

import springsprout.common.web.URLBuilder;


public class URLBuilderTest {

	@Test
	public void addParameter() throws Exception {
		URLBuilder builder = new URLBuilder();
		assertEquals("", builder.toString());

		builder.addParameter("p_page", ""+1, "0");
		assertEquals("p_page=1", builder.toString());

		builder.addParameter("p_size", null, null);
		assertEquals("p_page=1", builder.toString());

		builder.addParameter("p_size", null, "5");
		assertEquals("p_page=1&p_size=5", builder.toString());

		builder.addParameter("o_direction", "asc", "");
		assertEquals("p_page=1&p_size=5&o_direction=asc", builder.toString());
	}

	@Test
	public void encoding() throws Exception {
		URLBuilder builder = new URLBuilder();
		assertEquals("UTF-8", builder.encoding);

		builder.addParameter("test", "= #", "");
		String expectedURL = "test=%3D+%23";
		assertEquals(new String(expectedURL.getBytes(), "UTF-8"), builder.toString());
	}

}
