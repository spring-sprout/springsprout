package springsprout.service.notification.message;

import org.junit.Test;
import springsprout.domain.Member;

import static org.junit.Assert.assertThat;
import static springsprout.service.notification.message.MailMatcher.isAbout;

public class ConfirmMailTest {

	@Test
	public void create() throws Exception {
		Member member = new Member();
		member.setEmail("keesun@mail.com");
		member.setName("keesun");
		ConfirmMail mail = new ConfirmMail(member);
		assertThat(mail, isAbout(member));
	}

}