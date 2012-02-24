package springsprout.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import springsprout.common.annotation.DomainInfo;
import springsprout.common.convention.Convention;
import springsprout.common.util.DateUtils;
import springsprout.common.util.StringUtils;
import springsprout.domain.enumeration.MeetingStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@DomainInfo("모임")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Meeting implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@DomainInfo(descr = "모임 추가시 자동으로 설정되어야 함.")
	private Study study;

	@Column
	@DomainInfo(value = "횟수", descr = "몇 번째 스터디 인지 알려 주는 값. 자동 생성.")
	private Integer cnt;

	@Column(length = 100)
	@DomainInfo(value = "모임명/모임주제", descr = "글제목과 비슷함.")
	@NotEmpty(message = "모임주제를 입력하세요.")
	private String title;

	@ManyToOne
	@DomainInfo(value = "모임장", descr = "기본으로 현재 사용자. 자동 생성.")
	private Member owner;

	@NotNull( message = "모임 시작일을 입력하세요.")
	@Temporal(TemporalType.DATE)
	@DomainInfo("모임 시작일")
	@DateTimeFormat( pattern = "yyyy/MM/dd")
	private Date openDate;

	@Temporal(TemporalType.TIME)
	@DomainInfo("모임 시작시")
	@NotNull( message = "모임 시작시를 입력하세요.")
	private Date openTime;

	@Temporal(TemporalType.DATE)
	@DomainInfo("모임 종료일")
	@NotNull( message = "모임 종료일을 입력하세요.")
	@DateTimeFormat( pattern = "yyyy/MM/dd")
	private Date closeDate;

	@Temporal(TemporalType.TIME)
	@DomainInfo("모임 종료시")
	@NotNull( message = "모임 종료시를 입력하세요.")
	private Date closeTime;

	@Column
	@DomainInfo(value = "최대인원", descr = "수정 화면에서 모임 최대 인원을 변경할 수도 있음")
	private Integer maximum;

	@Column
    @Type(type="text")
	@DomainInfo(value = "모임 내용")
	@NotEmpty( message = "모임 내용을 입력하세요.")
	private String contents;

	@OneToMany(cascade={CascadeType.ALL}, mappedBy="meeting")
	@DomainInfo("참석목록")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<Attendance> attendances;

	@OneToMany(cascade={CascadeType.ALL})
	@OrderBy("created DESC")
	@DomainInfo("댓글")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<Comment> comments;

	@OneToMany(cascade={CascadeType.ALL})
	@OrderBy("id ASC")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<Presentation> presentations;
	
	@OneToMany(cascade={CascadeType.ALL})
	@DomainInfo("참고자료")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<Resource> resources;
	
	@Column
	@Type(type="springsprout.domain.usertype.MeetingStatusType")
	@DomainInfo("모임상태")
	private MeetingStatus status;
	
	@ManyToOne
	@DomainInfo("모임 장소")
	private Location location;

    @Column
    private Integer attendanceRate;
    
    @Column(length = 50)
    @DomainInfo("구글 캘린더 이벤트 ID")
    private String eventId;
    
    @Column(length = 30)
    @DomainInfo("구글 캘린더 이벤트 수정 시 필요한 ID")
    private String etag;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Meeting() {
		this.openMeeting();
        this.location = new Location();
	}

	public Meeting(Study study) {
		this();
		this.study = study;
		this.maximum = study.getMaximum();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    @JsonIgnore
	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Member getOwner() {
		return owner;
	}

	public void setOwner(Member owner) {
		this.owner = owner;
	}

	public Integer getMaximum() {
		return maximum;
	}

	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

    @JsonIgnore
	public Set<Attendance> getAttendances() {
		if (this.attendances == null)
			this.attendances = new HashSet<Attendance>();
		return attendances;
	}

	public void setAttendances(Set<Attendance> attendances) {
		this.attendances = attendances;
	}

    @JsonIgnore
	public Set<Comment> getComments() {
		if (this.comments == null) {
			this.comments = new HashSet<Comment>();
		}
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

    @JsonIgnore
	public Set<Resource> getResources() {
        if(this.resources == null)
            this.resources = new HashSet<Resource>();
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}
	
	public MeetingStatus getStatus() {
		return status;
	}

	public void setStatus(MeetingStatus status) {
		this.status = status;
	}

	public void addAttendance(Attendance attendance) {
		getAttendances().add(attendance);
	}
	
	public void addComment(Comment comment) {
		getComments().add(comment);
	}

    @JsonIgnore
	public Attendance getAttendanceByMember(Member member) {
		Attendance returnAttendance = null;
		for (Attendance attendance : getAttendances()) {
			if (attendance.getMember().equals(member)) {
				returnAttendance =  attendance;
			}
		}
		return returnAttendance;
	}
	
	public void openMeeting() {
		this.status = MeetingStatus.OPEN;
	}
	
	public void endMeeting() {
		this.status = MeetingStatus.ENDED;
	}

	public String getStartDateTime() {
		if(getOpenDate() != null && getOpenTime() != null)
			return DateUtils.make년월일(getOpenDate()) + " " + DateUtils.makeHHMMA(getOpenTime());
		return "";
	}

	public String getEndDateTime() {
		if(getCloseDate() != null && getCloseTime() != null)
			return DateUtils.make년월일(getCloseDate()) + " " + DateUtils.makeHHMMA(getCloseTime());
		return "";
	}
	
	public int getAttendedCount(){
		int result = 0;
		for(Attendance attendance : getAttendances())
			if(attendance.isAttended() == true)
				result++;
		return result;
	}
	
	public int getAttendanceCount(){
		return getAttendances().size();
	}

	public void removeAttendance(Attendance attendance) {
		getAttendances().remove(attendance);
	}

	public boolean isJoinable(Member currentMember) {
		return getStudy().getMembers().contains(currentMember);
	}
	
	public boolean getIsOpened(){
		return this.status == MeetingStatus.OPEN;
	}
	
	public boolean getIsClosed(){
		return this.status == MeetingStatus.ENDED;
	}

	public void setPresentations(Set<Presentation> presentations) {
		this.presentations = presentations;
	}

    @JsonIgnore
	public Set<Presentation> getPresentations() {
		if(this.presentations == null)
			this.presentations = new HashSet<Presentation>();
		return presentations;
	}

	public void addResource(Resource resource) {
		getResources().add(resource);
	}

	public void removeResource(Resource resource) {
		getResources().remove(resource);
	}

	public String getLink() {
		return Convention.HOME_URL + "study/" + this.study.getId() + "/meeting/" + this.id + "";
	}

	public void addPresentation(Presentation presentation) {
		getPresentations().add(presentation);
	}

	public void deletePresentation(Presentation presentation) {
		getPresentations().remove(presentation);
	}

    public void deletePresentationByHashCode(int presentationHashCode) {
    	Presentation presentation = null;
		for(Presentation p : getPresentations()){
			if(p.hashCode() == presentationHashCode){
				presentation = p;
                break;
            }
        }
        if(presentation != null) {
            getPresentations().remove(presentation);
        }
    }

    public int getPresentationCount(){
        return getPresentations().size();
    }

    public Presentation createPresentation() {
        return new Presentation(this);
    }

    public void update(Meeting newMeeting) {
        this.title = newMeeting.title;
        this.location = newMeeting.location;
        this.openDate = newMeeting.openDate;
        this.openTime = newMeeting.openTime;
        this.closeDate = newMeeting.closeDate;
        this.closeTime = newMeeting.closeTime;
        this.contents = newMeeting.contents;
        this.maximum = newMeeting.maximum;
    }

    @JsonIgnore
    public Set<Attendance> getSortedAttendances() {
        TreeSet<Attendance> attds = new TreeSet<Attendance>(new AttendanceComparator());
        attds.addAll(getAttendances());
        return attds;
    }

    public void removeComment(Comment comment) {
        getComments().remove(comment);
    }

    public void delete() {
        getStudy().removeMeeting(this);
        this.setStudy(null);
    }

    private class AttendanceComparator implements Comparator<Attendance> {
        public int compare(Attendance a1, Attendance a2) {
            return a1.getMember().getName().compareTo(a2.getMember().getName());
        }
    }

    public Integer getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(Integer attendanceRate) {
        this.attendanceRate = attendanceRate;
    }
    
    public int getResourcesCount() {
    	return getResources().size();
    }
    
    public int getCommentsCount() {
    	return getComments().size();
    }
	
    public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

    public String getShortTitle() {
        return StringUtils.cutBytes(this.getTitle(), 20);   
    }
	
	public String getContentsText(){
		return StringUtils.cutBytes(StringUtils.stripHTML(this.contents),200);
	}
}
