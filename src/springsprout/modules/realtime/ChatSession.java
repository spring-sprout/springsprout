package springsprout.modules.realtime;

import springsprout.domain.Member;

/**
 * @author Keesun Baik
 */
public class ChatSession {
	
	String sock;
	
	String email;
	
	Member member;

	public String getSock() {
		return sock;
	}

	public void setSock(String sock) {
		this.sock = sock;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "ChatSession{" +
				"sock='" + sock + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
