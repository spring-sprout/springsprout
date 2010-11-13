package springsprout.service.notification.message;

import springsprout.common.util.EmailExtractUtil;
import springsprout.domain.Member;

public class PasswordMail extends SpringSproutMail{
	
	private Member member;
	
	public PasswordMail(Member member) {
		this.member = member;
	}

	public String getContents() {
		StringBuilder content = new StringBuilder("<h1>봄싹(SpringSprount) 회원 비밀번호 안내 메일입니다.</h1>");
		content.append("<p>");
		content.append(member.getName());
		content.append(" 님의 새로 생성된 비밀번호는 [");
		content.append(member.getPassword());
		content.append("] 입니다.</p>");
		content.append("<p>로그인후에 새로운 패스워드로 변경해주세요.</p>.");
		return content.toString();
	}

	public String getTitle() {
		return SpringSproutMail.SUBJECT_PREFIX + " [" + member.getName()
		+ "] 님 비밀번호 안내 메일입니다.";
	}
	
	public String[] getMailReceivers() {
		return EmailExtractUtil.extractEmailArrayForm(member);
	}

}
