package springsprout.domain.study.board;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import springsprout.common.annotation.DomainInfo;
import springsprout.domain.Member;

@Entity
@DomainInfo("설문응답결과")
public class RespondSurbey {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@DomainInfo("설문글")
	@ManyToOne
	private SurbeyPost post;
	
	@DomainInfo("응답자")
	@ManyToOne
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

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	
}
