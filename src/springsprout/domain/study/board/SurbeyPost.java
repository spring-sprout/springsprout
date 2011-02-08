package springsprout.domain.study.board;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import springsprout.common.annotation.DomainInfo;
import springsprout.domain.Study;
import springsprout.domain.enumeration.SurbeyStatus;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * 설문기간 동안에 포함된 설문만 설문에 응할 수 있도록 하고, 
 * 설문 기간이 지난 설문은 그날 자정(일일 배치)에서 설문 결과를 처리하도록 한다.
 * 응답 결과를 가지고 있을 까 아니면 뷰에서 계산 해서 보여 줄 까
 * 
 * 
 * @author 김제준
 *
 */
@Entity
@DomainInfo("설문글")
@DiscriminatorValue("SURBEY")
public class SurbeyPost extends Post {

	@DomainInfo("질문 유형")
	@Type(type="springsprout.domain.usertype.SurbeyStatusType")
	private SurbeyStatus type;
	
	@DomainInfo("설문시작기간")
	private Date startDue;
	
	@DomainInfo("설문종료기간")
	private Date endDue;
	
	@DomainInfo("응답목록")
	@OneToMany(cascade={CascadeType.ALL})
	@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
	private List<RespondSurbey> responds;
	
	public SurbeyPost(Study study) {
		super.setRootStudy(study);
	}

	public SurbeyStatus getType() {
		return type;
	}

	public void setType(SurbeyStatus type) {
		this.type = type;
	}

	public Date getStartDue() {
		return startDue;
	}

	public void setStartDue(Date startDue) {
		this.startDue = startDue;
	}

	public Date getEndDue() {
		return endDue;
	}

	public void setEndDue(Date endDue) {
		this.endDue = endDue;
	}

	public void setResponds(List<RespondSurbey> responds) {
		this.responds = responds;
	}

	public List<RespondSurbey> getResponds() {
		return responds;
	}
	
}
