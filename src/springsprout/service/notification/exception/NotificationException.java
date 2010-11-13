package springsprout.service.notification.exception;


public class NotificationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotificationException(Throwable e) {
		super(e);
	}

	public NotificationException(String msg, Throwable e) {
		super(msg, e);
	}

}
