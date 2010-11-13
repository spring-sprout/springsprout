package springsprout.service.notification.message;

import org.springframework.mail.MailSendException;
import springsprout.common.SpringSprout2System;
import springsprout.common.convention.Convention;
import springsprout.common.util.EmailExtractUtil;
import springsprout.domain.Member;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ConfirmMail extends SpringSproutMail {
	
	private Member member;
	
	public ConfirmMail(Member member) {
		this.member = member;
	}

	public String getContents() {
		StringBuilder content = new StringBuilder();
		content.append("<h1>봄싹(SpringSprout) 회원 인증 메일 입니다.</h1>");
		appendConfirmLink(content);
		return content.toString();
	}

	private void appendConfirmLink(StringBuilder content) {
		try {
			String confirmUrl = Convention.HOME_URL + "signupconfirm?email="
				+ URLEncoder.encode(member.getEmail(), SpringSprout2System.ENCODING)
				+ "&authCode="
				+ URLEncoder.encode(member.getAuthCode(), SpringSprout2System.ENCODING);
			content.append("<a href=\"" + confirmUrl + "\"> 클릭하세요.</a>");		
		} catch (UnsupportedEncodingException e) {
			throw new MailSendException("회원 인증 메일 작성 실패", e);
		}
	}
	
	public String[] getMailReceivers() {
		return EmailExtractUtil.extractEmailArrayForm(member);
	}

	public String getTitle() {
		return SpringSproutMail.SUBJECT_PREFIX + " [" + member.getName()
		+ "] 님 인증 메일입니다.";
	}

}
