package springsprout.service.notification.message;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import springsprout.domain.Member;


public class PasswordMailTest {
	
	@Test
	public void create() throws Exception {
		Member member = new Member();
		member.setEmail("keesun@mail.com");
		PasswordMail mail = new PasswordMail(member);
		assertThat(mail.getMailReceivers()[0], is("keesun@mail.com"));
		assertThat(mail.getFrom(), is(SpringSproutMail.SENDER_MAIL));
		assertThat(mail.getTitle(), containsString("비밀번호 안내"));
	}

}
