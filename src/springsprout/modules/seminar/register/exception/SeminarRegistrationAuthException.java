package springsprout.modules.seminar.register.exception;

import springsprout.modules.seminar.register.SeminarRegistration;

public class SeminarRegistrationAuthException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private SeminarRegistration registration;
	public SeminarRegistrationAuthException(SeminarRegistration registration) {
		this.registration = registration;
	}
	
	public SeminarRegistration getRegistration() {
		return this.registration;
	}
	
}
