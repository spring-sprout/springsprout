package springsprout.domain.study.board;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import springsprout.common.annotation.DomainInfo;
import springsprout.common.conversion.JsonDateSerializer;
import springsprout.domain.Member;
import springsprout.domain.Study;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "POST_TYPE",
    discriminatorType = DiscriminatorType.STRING
)
@JsonIgnoreProperties({"rootStudy", "writer"})
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@DomainInfo("작성자")
	private Member writer;
	@DomainInfo("제목")
	private String title;
	@DomainInfo("내용")
	@Type(type = "text")
	private String content;
	@DomainInfo("작성일")
	private Date createdAt;
	@DomainInfo("수정일")
	private Date modifidedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@DomainInfo("상위스터디")
	protected Study rootStudy;
	
	public Post() { }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Member getWriter() {
		return writer;
	}
	public void setWriter(Member writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getModifidedAt() {
		return modifidedAt;
	}
	public void setModifidedAt(Date modifidedAt) {
		this.modifidedAt = modifidedAt;
	}

	public void setRootStudy(Study rootStudy) {
		this.rootStudy = rootStudy;
	}

	public Study getRootStudy() {
		return rootStudy;
	}

}
