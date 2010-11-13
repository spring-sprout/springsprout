package springsprout.service.notification;

import springsprout.service.notification.exception.NotificationException;
import springsprout.service.notification.message.SpringSproutMessage;

public interface NotificationService {
	
	void sendMessage(SpringSproutMessage ssm) throws NotificationException ;

}
