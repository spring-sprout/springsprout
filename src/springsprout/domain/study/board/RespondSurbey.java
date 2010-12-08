package springsprout.domain.study.board;

import springsprout.common.annotation.DomainInfo;
import springsprout.domain.Member;

@DomainInfo("설문응답결과")
public class RespondSurbey {

	@DomainInfo("설문글")
	private SurbeyPost post;
	
	@DomainInfo("응답자")
	private Member responder;

	public SurbeyPost getPost() {
		return post;
	}

	public void setPost(SurbeyPost post) {
		this.post = post;
	}

	public Member getResponder() {
		return responder;
	}

	public void setResponder(Member responder) {
		this.responder = responder;
	}
	
	
}
