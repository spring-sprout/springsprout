package springsprout.service.notification.mail;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import springsprout.common.SpringSprout2System;
import springsprout.service.notification.NotificationService;
import springsprout.service.notification.message.SpringSproutMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public class SendMailService implements NotificationService{

    private static final String FTL_PREFIX = "ftl:";

    Logger logger = LoggerFactory.getLogger( this.getClass());
	JavaMailSender mailSender;
    @Autowired Configuration configuration;

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
                if(ssm.getContents().startsWith(FTL_PREFIX)) {
                    String templateFileName = ssm.getContents().replace(FTL_PREFIX, "");
                    String contents = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(templateFileName), ssm.getModelObject());
                    helper.setText(contents, ssm.isHTML());
                } else {
                    helper.setText(ssm.getContents(), ssm.isHTML());
                }
            } catch (MessagingException e) {
                throw new MailPreparationException(e);
            } catch (TemplateException e) {
                throw new MailPreparationException(e);
            } catch (IOException e) {
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
