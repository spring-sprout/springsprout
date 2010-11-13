package springsprout.domain;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Notice implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;	
	@Column(length = 100)
	@NotEmpty(message="제목을 입력하세요!")
	private String title;
	@Column
    @Type(type="text")
	@NotEmpty(message="내용을 입력하세요!")
	private String contents;
    
	@Temporal(TemporalType.DATE)
    private Date created;
	@ManyToOne
	private Member member;
	@Temporal(TemporalType.DATE)
    private Date modified;
	@ManyToOne
	private Member modifier;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getContents() {
		return contents;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getModified() {
		return modified;
	}

	public void setModifier(Member modifier) {
		this.modifier = modifier;
	}

	public Member getModifier() {
		return modifier;
	}
	
}
