package springsprout.service.notification.mail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springsprout.service.notification.NotificationService;

import javax.inject.Inject;
import javax.inject.Provider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
public class CommentMailTest {
	@Autowired JavaMailSender mailSender;
	@Autowired @Qualifier("sendMailService") NotificationService sendMailService;
	@Inject Provider<SampleVelocityMail> sampleMailProvider;
	
	@Test
	@Ignore("메일 테스트는 로컬에서만 진행 합시다.")
	public void sendMail() throws Exception {
		sendMailService = new SendMailService(mailSender);
		SampleVelocityMail mail = sampleMailProvider.get();
		sendMailService.sendMessage(mail);
	}

}