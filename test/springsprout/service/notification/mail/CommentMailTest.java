package springsprout.service.notification.mail;

import javax.inject.Inject;
import javax.inject.Provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springsprout.service.notification.NotificationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
public class CommentMailTest {
	@Autowired JavaMailSender mailSender;
	@Autowired @Qualifier("sendMailService") NotificationService sendMailService;
	@Inject Provider<SampleVelocityMail> sampleMailProvider;
	
	@Test
	public void sendMail() throws Exception {
		sendMailService = new SendMailService(mailSender);
		SampleVelocityMail mail = sampleMailProvider.get();
		sendMailService.sendMessage(mail);
	}

}