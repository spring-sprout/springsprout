package springsprout.service.notification.message;

import springsprout.common.util.EmailExtractUtil;
import springsprout.domain.Member;

import java.util.Collection;

public abstract class SpringSproutMailMessage extends SpringSproutMail {

	public SpringSproutMailMessage() {
	}

	public SpringSproutMailMessage(Collection<Member> members) {
		super(members);
	}
	
	public Collection<String> getMessageReceivers() {
		return EmailExtractUtil.extractMessageAllowedEmailCollectionFrom(members);
	}

	public abstract String getMessage();

}
