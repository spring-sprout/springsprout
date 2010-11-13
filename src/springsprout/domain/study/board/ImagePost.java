package springsprout.domain.study.board;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.web.multipart.MultipartFile;

import springsprout.common.annotation.DomainInfo;
import springsprout.domain.Comment;
import springsprout.domain.Study;

@Entity
@DomainInfo("이미지글")
@DiscriminatorValue("IMAGE")
public class ImagePost extends Post {

    @DomainInfo("이미지 파일")
    @OneToOne(cascade={CascadeType.ALL})
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private ImageFile imageFile;
    
	@OneToMany(cascade={CascadeType.ALL})
	@OrderBy("created DESC")
	@DomainInfo("댓글")
    @Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
	private Set<Comment> comments;
	
	@Transient
	private MultipartFile file;
	
	public ImagePost() { }

	public ImagePost(Study study) {
		super.setRootStudy( study);
	}

	public void setFile( MultipartFile file) {
		this.file = file;
	}

	@JsonIgnore
	public MultipartFile getFile() {
		return file;
	}

	public void setImageFile( ImageFile imageFile) {
		this.imageFile = imageFile;
	}

	public ImageFile getImageFile() {
		return imageFile;
	}
	
	public void setComments(Set<Comment> comments) {
			this.comments = comments;
	}

    @JsonIgnore
	public Set<Comment> getComments() {
		if (this.comments == null)  this.comments = new HashSet<Comment>();
		return comments;
	}

	public void addComment( Comment comment) {
		getComments().add( comment);
	}
	
	public void removeCommentById( int commentId) {
		Set<Comment> comments = getComments();
		for ( Comment comment : comments) {
			if ( comment.getId() == commentId) {
				comments.remove(comment);
				break;
			}
		}
	}
	
	public int getCommentCount() {
		return getComments().size();
	}

	
}
