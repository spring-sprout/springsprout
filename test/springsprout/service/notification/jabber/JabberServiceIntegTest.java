package springsprout.service.notification.jabber;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springsprout.service.notification.message.SpringSproutMailMessage;

import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
public class JabberServiceIntegTest {

	class SampleMessage extends SpringSproutMailMessage {
		
		@SuppressWarnings("unchecked")
		public Collection<String> getMessageReceivers() {
			return Arrays.asList(new String[]{"whiteship2000@gmail.com"});
		}

		public String getMessage() {
			return "hi";
		}

		public String getContents() {
			return null;
		}

		public String getTitle() {
			return null;
		}
	}

	@Autowired JabberService jabberService;
	
	@Test
	@Ignore
	public void testname() throws Exception {
		jabberService.sendMessage(new SampleMessage());
	}
	
}
