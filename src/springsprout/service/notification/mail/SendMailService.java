package springsprout.service.notification.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import springsprout.common.SpringSprout2System;
import springsprout.service.notification.NotificationService;
import springsprout.service.notification.message.SpringSproutMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SendMailService implements NotificationService{
	
	Logger logger = LoggerFactory.getLogger( this.getClass());
	JavaMailSender mailSender;

    public SendMailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMessage(SpringSproutMessage ssm) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, SpringSprout2System.ENCODING);

        if(ssm.getMailReceivers().length == 0)
            return;

        if(ssm.getMailReceivers().length > 100)
            sendOneByOne(ssm);
        else {
            try {
                helper.setTo(ssm.getMailReceivers());
                helper.setFrom(ssm.getFrom());
                helper.setSubject(ssm.getTitle());
                helper.setText(ssm.getContents(), ssm.isHTML());
            } catch (MessagingException e) {
                throw new MailPreparationException(e);
            }
            mailSender.send(message);
            logger.info("Send mail to {}", ssm.getMailReceivers());
        }
	}

    private void sendOneByOne(SpringSproutMessage ssm) {
        for(String to : ssm.getMailReceivers()){
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, SpringSprout2System.ENCODING);
            try {
                helper.setTo(to);
                helper.setFrom(ssm.getFrom());
                helper.setSubject(ssm.getTitle());
                helper.setText(ssm.getContents(), ssm.isHTML());
            } catch (MessagingException e) {
                throw new MailPreparationException(e);
            }
            mailSender.send(message);
        }
        logger.info("Send mail to {}", ssm.getMailReceivers());
    }

}
