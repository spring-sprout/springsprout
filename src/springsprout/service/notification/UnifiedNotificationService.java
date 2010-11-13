package springsprout.service.notification;

import springsprout.service.notification.exception.NotificationException;
import springsprout.service.notification.jabber.JabberService;
import springsprout.service.notification.mail.SendMailService;
import springsprout.service.notification.message.NotificationExceptionMail;
import springsprout.service.notification.message.SpringSproutMessage;
import springsprout.service.notification.twitter.TwitterService;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 10
 * Time: 오후 1:15:42
 */
public class UnifiedNotificationService implements NotificationService {

    SendMailService sendMailService;
    JabberService jabberService;
    TwitterService twitterService;

    public SendMailService getSendMailService() {
        return sendMailService;
    }

    public void setSendMailService(SendMailService sendMailService) {
        this.sendMailService = sendMailService;
    }

    public JabberService getJabberService() {
        return jabberService;
    }

    public void setJabberService(JabberService jabberService) {
        this.jabberService = jabberService;
    }

    public TwitterService getTwitterService() {
        return twitterService;
    }

    public void setTwitterService(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    /**
     * TODO : noti 시twitterService 때문에 에러남.
     */
    public void sendMessage(SpringSproutMessage ssm) {
    	try {
            sendMailService.sendMessage(ssm);
            jabberService.sendMessage(ssm);
            //twitterService.sendMessage(ssm);
        } catch (NotificationException e) {
            sendMailService.sendMessage(new NotificationExceptionMail(e));        
        }
    }

}
