package springsprout.service.notification.message;

import springsprout.service.notification.exception.NotificationException;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 3. 19
 * Time: 오후 4:12:17
 */
public class NotificationExceptionMail extends SpringSproutMail {

    private NotificationException e;

    public NotificationExceptionMail(NotificationException e) {
        this.e = e;
    }

    public String getTitle() {
        return SpringSproutMail.SUBJECT_PREFIX + " Notification Error!!";
    }

    public String getContents() {
        return e.getLocalizedMessage();
    }

    public String[] getMailReceivers() {
		return new String[]{"whiteship2000@gmail.com"};
	}
}
