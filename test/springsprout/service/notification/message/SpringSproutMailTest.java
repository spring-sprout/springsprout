package springsprout.service.notification.message;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


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
