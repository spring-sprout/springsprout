package springsprout.service.notification.message;

import org.hamcrest.Matcher;
import org.mockito.ArgumentMatcher;
import springsprout.domain.Member;

import java.util.Arrays;

public class MailMatcher extends ArgumentMatcher<SpringSproutMail> {

	private String from;
	private String to;
	private String subject;
	private String message;

	public MailMatcher(String from, String to, String subject, String message) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.message = message;
	}

	@Override
	public boolean matches(Object mail) {
		SpringSproutMail smail = (SpringSproutMail) mail;
		if (from != null && !from.equals(smail.getFrom()))
			return false;
		if (!Arrays.asList(smail.getMailReceivers()).contains(to))
			return false;
		if (!smail.getTitle().contains(subject))
			return false;
		if (!smail.getContents().contains(message))
			return false;
		return true;
	}

	public static Matcher<SpringSproutMail> isAbout(Member member) {
		return new MailMatcher(SpringSproutMail.SENDER_MAIL, member.getEmail(),
				member.getName(), "회원 인증 메일");
	}

}