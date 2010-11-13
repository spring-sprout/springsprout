package springsprout.service.notification.mail;

import static org.mockito.Mockito.*;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;

import springsprout.service.notification.message.SpringSproutMail;

@RunWith(MockitoJUnitRunner.class)
public class SendMailServiceTest {
	
	@Mock JavaMailSender mailSender;
	@Mock MimeMessage message;
	SendMailService sendMailService;
	
	@Test
	public void sendMail() throws Exception {
		sendMailService = new SendMailService(mailSender);
		when(mailSender.createMimeMessage()).thenReturn(message);
		
		sendMailService.sendMessage(new SampleMail());
	}
	
	class SampleMail extends SpringSproutMail {

		@Override
		public String getContents() {
			return "봄싹 메일 메시지";
		}

		@Override
		public String getTitle() {
			return "봄싹 메일 메시지";
		}
		
		@Override
		public String[] getMailReceivers() {
			return new String[]{"keesun@mail.com"};
		}
	}

}
