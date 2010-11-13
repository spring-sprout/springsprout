package springsprout.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import springsprout.common.annotation.DomainInfo;
import springsprout.domain.enumeration.SeminarStatus;

import javax.persistence.*;
import java.util.*;

@Entity
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Seminar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    @Column(length = 100)
	private String title;

    @Column
    @Type(type = "text")
	private String description;

	@ManyToOne(cascade = { CascadeType.ALL })
	@DomainInfo(value = "지도위치")
	private Location location;
    
	@Column
    private Integer maximum;

    @DomainInfo("세미나 시작 일")
    @Temporal(TemporalType.TIMESTAMP)
    private Date seminarOpenDate;

    @Temporal(TemporalType.TIME)
	@DomainInfo("세미나 시작 시간")
	private Date seminarOpenTime;

    @DomainInfo("세미나 종료 일")
    @Temporal(TemporalType.TIMESTAMP)
    private Date seminarCloseDate;

    @Temporal(TemporalType.TIME)
	@DomainInfo("세미나 종료 시간")
	private Date seminarCloseTime;

	@DomainInfo("세미나 신청 시작 시간")
	@Temporal(TemporalType.TIMESTAMP)
    private Date entryStartDate;

	@DomainInfo("세미나 신청 마감 시간")
	@Temporal(TemporalType.TIMESTAMP)
    private Date entryEndDate;

	@DomainInfo("세미나 상태")
    @Column
    @Type(type = "springsprout.domain.usertype.SeminarStatusType")
	private SeminarStatus status;

    @DomainInfo("참가비")
    @Column
    private int entryFee;

    @DomainInfo("세미나 유/무료 여부")
    @Transient
    private boolean isCharged;

    @DomainInfo("세미나 생성 시간")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @DomainInfo("세미나 등록자")
	@ManyToOne
    private Member creator;

    @DomainInfo("세미나 신청자")
	@OneToMany(cascade={CascadeType.ALL}, mappedBy = "seminar")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private Set<SeminarComer> seminarComers;

    @DomainInfo("세미나 세부 일정")
    @OneToMany(cascade={CascadeType.ALL}, mappedBy = "seminar")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private Set<SeminarDetailSchedule> detailSchedules;

    @DomainInfo("세미나 댓글")
    @OneToMany(cascade={CascadeType.ALL})
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private List<Comment> comments;

    @DomainInfo("참석자 수")
	@Column
    private long totalActiveComer;

	
    public Seminar(){
        this.created = new Date();
        this.status = SeminarStatus.READY;
    }

    public Date getSeminarOpenTime() {
		return seminarOpenTime;
	}

	public void setSeminarOpenTime(Date seminarOpenTime) {
		this.seminarOpenTime = seminarOpenTime;
	}

	public Date getSeminarCloseTime() {
		return seminarCloseTime;
	}

	public void setSeminarCloseTime(Date seminarCloseTime) {
		this.seminarCloseTime = seminarCloseTime;
	}

	public int getEntryFee() {
		return entryFee;
	}

	public void setEntryFee(int entryFee) {
		this.entryFee = entryFee;
        if(entryFee != 0)
            this.isCharged = true;
        else
            this.isCharged = false;
	}
	
	public Date getEntryStartDate() {
		return entryStartDate;
	}

	public void setEntryStartDate(Date entryStartDate) {
		this.entryStartDate = entryStartDate;
	}

	public Date getEntryEndDate() {
		return entryEndDate;
	}

	public void setEntryEndDate(Date entryEndDate) {
		this.entryEndDate = entryEndDate;
	}

	public Set<SeminarDetailSchedule> getDetailSchedules() {
        if(this.detailSchedules == null)
            this.detailSchedules = new HashSet<SeminarDetailSchedule>();
		return detailSchedules;
	}

	public void setDetailSchedules(Set<SeminarDetailSchedule> detailSchedules) {
		this.detailSchedules = detailSchedules;
	}

	public Set<SeminarComer> getSeminarComers() {
        if(this.seminarComers == null)
            this.seminarComers = new HashSet<SeminarComer>();
		return seminarComers;
	}

	public void setSeminarComers(Set<SeminarComer> seminarComers) {
		this.seminarComers = seminarComers;
	}

	public boolean isActive() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date yesterDay = cal.getTime();
		if(yesterDay.compareTo(this.seminarOpenDate) <= 0)  return true;
		return false;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaximum() {
		return maximum;
	}

	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}

	public Member getCreator() {
		return creator;
	}

	public void setCreator(Member creator) {
		this.creator = creator;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public long getTotalActiveComer() {
		return totalActiveComer;
	}

	public void setTotalActiveComer(long totalActiveComer) {
		this.totalActiveComer = totalActiveComer;
	}

	public void setStatus(SeminarStatus status) {
		this.status = status;
	}

	public SeminarStatus getStatus() {
		return status;
	}

	public Date getSeminarOpenDate() {
		return seminarOpenDate;
	}

	public void setSeminarOpenDate(Date seminarOpenDate) {
		this.seminarOpenDate = seminarOpenDate;
	}

	public Date getSeminarCloseDate() {
		return seminarCloseDate;
	}

	public void setSeminarCloseDate(Date seminarCloseDate) {
		this.seminarCloseDate = seminarCloseDate;
	}
	public boolean isCharged() {
		return isCharged;
	}

	public void setCharged(boolean isCharged) {
		this.isCharged = isCharged;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Comment> getComments() {
        if(this.comments == null)
            this.comments = new ArrayList<Comment>();
		return comments;
	}

    public void startEnrollment() {
        this.status = SeminarStatus.ENROLLMENT_STARTED;
    }

    public void endEnrollment() {
        this.status = SeminarStatus.ENROLLMENT_ENDED;
    }

    public void startSeminar() {
        this.status = SeminarStatus.SEMINAR_STARTED;
    }

    public void endSeminar() {
        this.status = SeminarStatus.SEMINAR_ENDED;
    }

    public void addSeminarCommer(SeminarComer seminarComer) {
        getSeminarComers().add(seminarComer);
        seminarComer.setSeminar(this);
    }

    public void removeSeminarCommer(SeminarComer seminarComer) {
        getSeminarComers().remove(seminarComer);
        seminarComer.setSeminar(null);
    }

    public void addSeminarDetailSchedule(SeminarDetailSchedule schedule) {
        getDetailSchedules().add(schedule);
        schedule.setSeminar(this);
    }

    public void removeSeminarDetailSchedule(SeminarDetailSchedule schedule) {
        getDetailSchedules().remove(schedule);
        schedule.setSeminar(null);
    }

    public void addComment(Comment comment) {
        getComments().add(comment);
    }

    public void removeComment(Comment comment) {
        getComments().remove(comment);
    }


    public boolean isReady() {
        return getStatus() == SeminarStatus.READY;
    }

    public boolean isEnrollmentStarted() {
        return getStatus() == SeminarStatus.ENROLLMENT_STARTED;
    }

    public boolean isEnrollmentEnded() {
        return getStatus() == SeminarStatus.ENROLLMENT_ENDED;
    }

    public boolean isSeminarStated() {
        return getStatus() == SeminarStatus.SEMINAR_STARTED;
    }

    public boolean isSeminarEnded() {
        return getStatus() == SeminarStatus.SEMINAR_ENDED;
    }
}
