package springsprout.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import springsprout.common.annotation.DomainInfo;
import springsprout.domain.enumeration.PresentationStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Presentation implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
    @Column
    @DomainInfo("발표시간")
    @NotNull(message="발표시간을 입력하세요!")
    private double hour;
    @Column(length=100)
	@DomainInfo("주제")
    @NotEmpty(message="주제를 입력하세요!")
	private String topic;
    @Column(length=100)
	@DomainInfo("제목")
    @NotEmpty(message="제목을 입력하세요!")
	private String title;
    @ManyToOne
	@DomainInfo("발표자")
	@NotNull(message="발표자를 선택하세요!")
	private Member presenter;
	@ManyToOne
	@DomainInfo("모임")
	private Meeting meeting;
	@Column
    @Type(type="text")
	@DomainInfo("요약")
	private String summary;
	@Column
	@DomainInfo("평점")
	private double point;
	@OneToMany(cascade={CascadeType.ALL})
    @OrderBy("created DESC")
    @Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
	@DomainInfo("피드백")
	private List<Comment> comments;
	@OneToMany(cascade={CascadeType.ALL})
	@DomainInfo("참고자료")
	private List<Resource> resources;
    @Column
	@Type(type="springsprout.domain.usertype.PresentationStatusType")
	@DomainInfo("발표상태")
    private PresentationStatus status;

    @Column
    @DomainInfo(value="조회수", descr="발표를 조회할 때 마다 카운트를 증가")
    private int viewCount;
    @Column
    @DomainInfo(value="댓글갯수", descr="댓글을 등록할 때 마다 카운트를 증가")
    private int commentCount;
    @Column
    @DomainInfo(value="첨부자료갯수", descr="자료를 등록할 때 마다 카운트를 증가")
    private int resourceCount;

	public Presentation() {
	    this.status = PresentationStatus.OPENED;
    }
	
	public Presentation(Meeting meeting) {
        this();
        this.meeting = meeting;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getHour() {
		return hour;
	}
	public void setHour(double hour) {
		this.hour = hour;
	}
	public void setPresenter(Member presenter) {
		this.presenter = presenter;
	}
	public Member getPresenter() {
		return presenter;
	}
	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}
    @JsonIgnore
	public Meeting getMeeting() {
		return meeting;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getSummary() {
		return summary;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	public double getPoint() {
		return point;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
    @JsonIgnore
	public List<Comment> getComments() {
        if(this.comments == null)
            this.comments = new ArrayList<Comment>();
		return comments;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
    @JsonIgnore
	public List<Resource> getResources() {
		return resources;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTopic() {
		return topic;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getResourceCount() {
        return resourceCount;
    }

    public void setResourceCount(int resourceCount) {
        this.resourceCount = resourceCount;
    }

    public PresentationStatus getStatus() {
        return status;
    }

    public void setStatus(PresentationStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Presentation that = (Presentation) o;

        if (hour != that.hour) return false;
        if (meeting != null ? !meeting.equals(that.meeting) : that.meeting != null) return false;
        if (presenter != null ? !presenter.equals(that.presenter) : that.presenter != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)hour;
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    public int getHashCode(){
        return hashCode();
    }

    public void addComment(Comment comment) {
        getComments().add(comment);
        setCommentCount(getComments().size());
    }

    public void increaseViewCount() {
        setViewCount(getViewCount() + 1);
    }

    public void addResource(Resource resource) {
        List<Resource> resourceList = getResources();
        resourceList.add(resource);
        setResourceCount(resourceList.size());
    }

    public void deleteResource(Resource resource) {
        List<Resource> resourceList = getResources();
        resourceList.remove(resource);
        setResourceCount(resourceList.size());
    } 

    public void update(Presentation newPresentation) {
        this.title = newPresentation.title;
        this.topic = newPresentation.topic;
        this.hour = newPresentation.hour;
        this.presenter = newPresentation.presenter;
        this.summary = newPresentation.summary;
    }

    public void delete() {
        this.status = PresentationStatus.DELETED;
        this.meeting.getPresentations().remove(this);
    }

    public void start() {
        this.status = PresentationStatus.STARTED;
    }

    public void end() {
        this.status = PresentationStatus.ENDED;
    }

    public void removeComment(Comment comment) {
        getComments().remove(comment);
        setCommentCount(getComments().size());
    }
}
