package springsprout.service.notification.jabber;

import static org.mockito.Mockito.*;

import java.util.Collection;

import org.jivesoftware.smack.XMPPConnection;
import org.junit.Test;

import springsprout.common.util.EmailExtractUtil;
import springsprout.domain.Member;
import springsprout.service.notification.message.SpringSproutMailMessage;

public class JabberServiceTest {
	
	JabberService service;
	XMPPConnection mockGTalk;
	
	public JabberServiceTest() {
		service = new JabberService("s2cmailer@gmail.com", "s2cadmin", false);
		mockGTalk = mock(XMPPConnection.class);
		service.connection = mockGTalk;
	}
	
	@Test
	public void sendMsg() throws Exception {
		when(mockGTalk.isConnected()).thenReturn(true);
		when(mockGTalk.isAuthenticated()).thenReturn(true);
		Member member = new Member();
		member.setEmail("whiteship2000@gmail.com");
		
		service.sendMessage(new SampleSpringSproutMessage(member));
	}
	
	class SampleSpringSproutMessage extends SpringSproutMailMessage {
		
		private Member member;
		
		public SampleSpringSproutMessage(Member member) {
			this.member = member;
		}
		
		@SuppressWarnings("unchecked")
		public Collection<String> getMessageReceivers() {
			return EmailExtractUtil.extractEmailCollectionFrom(member);
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

}
