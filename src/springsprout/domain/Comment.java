package springsprout.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import springsprout.common.util.DateUtils;
import springsprout.common.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
    @Type(type = "text")
    @springsprout.common.validation.constraints.NotEmpty(message="의견을 입력하세요!")
    @Size(min = 1, max = 255)
	private String comment;
	
	@ManyToOne
	private Member writer;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	public Comment() {
		this.created = new Date();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Member getWriter() {
		return writer;
	}

	public void setWriter(Member writer) {
		this.writer = writer;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public void applyAtHtml() {
        if(StringUtils.isEmpty(comment))
            return;
        
		if(comment.contains("@")){
			for(String word : comment.split(" ")){
				if(word.startsWith("@")){
					comment = comment.replace(word, "<span id=\"at\">" + word + "</span>");
				}
			}
		}
	}
    
    public String getCreatedText(){
        return DateUtils.getShortDate(this.created);
    }
}
