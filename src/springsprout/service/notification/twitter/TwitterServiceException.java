package springsprout.service.notification.twitter;

import springsprout.service.notification.exception.NotificationException;

public class TwitterServiceException extends NotificationException {

	private static final long serialVersionUID = 1L;

	public TwitterServiceException(String msg, Throwable e) {
		super(msg, e);
	}

}
