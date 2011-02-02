package springsprout.service.notification.message;

import springsprout.common.util.EmailExtractUtil;
import springsprout.domain.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public abstract class SpringSproutMail implements SpringSproutMessage {
	
	public static final String SUBJECT_PREFIX = "[봄싹]";
	public static final String SENDER_MAIL = "s2cmailer@gmail.com";
	
	protected Collection<Member> members;

	public SpringSproutMail() {
	}
	
	public SpringSproutMail(Collection<Member> members) {
		this.members = members;
	}

    public SpringSproutMail(Member member) {
        this.members = new ArrayList<Member>();
        this.members.add(member);
    }
    
	
	public String getFrom() {
		return SENDER_MAIL;
	}
	
	public String[] getMailReceivers() {
		return EmailExtractUtil.extractMailAllowedEmailFrom(members);
	}

	public boolean isHTML() {
		return true;
	}

	public abstract String getTitle();
	
	public abstract String getContents();
	
	public Collection<String> getMessageReceivers() {
		throw new UnsupportedOperationException();
	}
	
	public String getMessage(){
		throw new UnsupportedOperationException();
	}

    public Map getModelObject() {
        return null;
    }
}
