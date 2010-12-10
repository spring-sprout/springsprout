package springsprout.domain.study.board;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import springsprout.common.annotation.DomainInfo;

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
	private String type;
	
	@DomainInfo("설문시작기간")
	private Date startDue;
	
	@DomainInfo("설문종료기간")
	private Date endDue;
	
	@DomainInfo("응답목록")
	@OneToMany(cascade={CascadeType.ALL})
	@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
	private List<RespondSurbey> responds;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
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
