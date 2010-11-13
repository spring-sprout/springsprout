package springsprout.domain.study.board;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import springsprout.common.annotation.DomainInfo;
import springsprout.domain.Comment;
import springsprout.domain.Study;

@Entity
@DomainInfo("일반글")
@DiscriminatorValue("TEXT")
public class TextPost extends Post {

	@ManyToOne
	private TextPost rootPost;

    @OneToMany(mappedBy = "rootPost")
    private Set<TextPost> branchPosts;

	@OneToMany(cascade={CascadeType.ALL})
	@OrderBy("created DESC")
	@DomainInfo("댓글")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<Comment> comments;
	public TextPost() {}
	public TextPost(Study study) {
		super.setRootStudy( study);
	}

    public TextPost(TextPost rootPost, Study rootStudy) {
		this.rootPost = rootPost;
		setRootStudy(rootStudy);
	}
	public TextPost getRootPost() {
        return rootPost;
    }

    public void setRootPost(TextPost rootPost) {
        this.rootPost = rootPost;
    }

    @JsonIgnore
    public Set<TextPost> getBranchPosts() {
        if(branchPosts == null)
            branchPosts = new HashSet<TextPost>();
        return branchPosts;
    }

    public void setBranchPosts(Set<TextPost> branchPosts) {
        this.branchPosts = branchPosts;
    }

    public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

    @JsonIgnore
	public Set<Comment> getComments() {
		if (this.comments == null)  this.comments = new HashSet<Comment>();
		return comments;
	}

    public void addBranch(TextPost branchPost) {
        branchPost.setRootPost(this);
        getBranchPosts().add(branchPost);
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
	
	public int getBranchCount() {
		return getBranchPosts().size();
	}
	
	public int getCommentCount() {
		return getComments().size();
	}
}
