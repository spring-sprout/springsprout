package springsprout.service.notification.message;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;


public class SpringSproutMailTest {
	
	@Test
	public void create() throws Exception {
		TestMail mail = new TestMail();
		assertThat(mail.getFrom(), is("s2cmailer@gmail.com"));
		assertTrue(mail.isHTML());
	}

	class TestMail extends SpringSproutMail {

		@Override
		public String getContents() {
			return "hello";
		}

		@Override
		public String getTitle() {
			return "hi";
		}
		
	}
}
