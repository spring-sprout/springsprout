package springsprout.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;
import springsprout.common.annotation.DomainInfo;
import springsprout.common.convention.Convention;
import springsprout.domain.enumeration.MemberStatus;
import springsprout.domain.enumeration.StudyStatus;
import springsprout.modules.study.exception.StudyMaximumOverException;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Study implements Serializable{
	
	private static final long serialVersionUID = 1L;

    private static final String NO_LIMIT_MEMBER_COUNT = "무제한";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(length = 100)
	@NotEmpty( message = "스터디명을 입력하세요.")
	private String studyName;
	@Column
    @Type(type = "text")
    @NotEmpty( message = "설명을 입력하세요.")
	private String descr;
	@Column
	private Integer maximum;
	@Column
	@NotNull( message = "시작일을 입력하세요.")
	private Date startDay;
	@Column
	@NotNull( message = "종료일을 입력하세요.")
	private Date endDay;
	@ManyToOne
	private Member manager;
	@Column
	@Type(type="springsprout.domain.usertype.StudyStatusType")
	private StudyStatus status;
	@ManyToMany
    @OrderBy("name ASC")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<Member> members;
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="study")
	@OrderBy("openDate DESC")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private Set<Meeting> meetings;
	@Column
	private String logo;

    @CollectionOfElements(targetElement = Integer.class)
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    @BatchSize(size=30)
	private Map<Meeting, Integer> meetingAttendenceRates;
	
	@Column
	private Integer memberCount;
	@Column
	private Integer meetingCount;
	
	@Column(length = 50)
	@DomainInfo("구글 캘린더 ID")
	private String calendarId;
	
	public Study() {
		this.status = StudyStatus.OPEN;
		this.meetingCount = 0;
        // 스터디 최초 생성시, 관리자가 참석자로 들어가므로 멤버 카운트는 1
		this.memberCount = 1;
	}

	public Study(Member manager) {
		this();
		this.manager = manager;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	public Member getManager() {
		return manager;
	}

	public void setManager(Member manager) {
		this.manager = manager;
	}

    @JsonIgnore
	public Set<Meeting> getMeetings() {
		if(this.meetings == null)
			this.meetings = new HashSet<Meeting>();
		return meetings;
	}
	
	public void setMeetings(Set<Meeting> meetings) {
		this.meetings = meetings;
	}

	public void addMeeting(Meeting meeting) {
		getMeetings().add(meeting);
		setMeetingCount(getMeetings().size());
		meeting.setCnt(getMeetingCount());
		if(meeting.getMaximum() == null)
			meeting.setMaximum(this.getMaximum());
		meeting.setStudy(this);
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}


	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}

	public Integer getMaximum() {
		return maximum;
	}

    @JsonIgnore
	public Set<Member> getMembers() {
		if(members == null)
			members = new HashSet<Member>();
		return members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

	public void addMember(Member member){
        if(this.getMaximumCount() != NO_LIMIT_MEMBER_COUNT && this.maximum <= this.memberCount)
            throw new StudyMaximumOverException("Study's maxium member count " + this.getMaximumCount());
		getMembers().add(member);
		setMemberCount(getMembers().size());
        member.addStudy(this);
	}

    public void removeMember(Member member) {
		getMembers().remove(member);
        setMemberCount(getMembers().size());
        member.removeStudy(this);
	}

	public StudyStatus getStatus() {
		return status;
	}

	public void setStatus(StudyStatus status) {
		this.status = status;
	}

	public void openStudy() {
		this.status = StudyStatus.STARTED;
	}
	
	public void endStudy() {
		this.status = StudyStatus.ENDED;
	}
	
	public boolean getIsStarted(){
		return this.status == StudyStatus.STARTED;
	}

	public boolean getIsOpened(){
		return this.status == StudyStatus.OPEN;
	}

	public boolean getIsEnded(){
		return this.status == StudyStatus.ENDED;
	}

    @JsonIgnore
	public Set<Member> getCurrentMembers(){
		Set<Member> result = new HashSet<Member>();
		for(Member member : getMembers())
			if(member.getStatus() != MemberStatus.OUT)
				result.add(member);
		return result;
	}
	
	public void startStudy() {
		setStatus(StudyStatus.STARTED);
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public Integer getMeetingCount() {
		if(this.meetingCount == null)
			this.meetingCount = 0;
		return meetingCount;
	}

	public void setMeetingCount(Integer meetingCount) {
		this.meetingCount = meetingCount;
	}

	public String getLink() {
		return Convention.HOME_URL + "study/" + this.id + "";
	}

	public String getMaximumCount() {
		if(getMaximum() == null || getMaximum() <= 0)
			return NO_LIMIT_MEMBER_COUNT;
		return getMaximum() + "";
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogo() {
		return logo;
	}

    public void decreaseMemberCount() {
        this.memberCount -= 1;
    }

    public void remove(Member member) {
        getMembers().remove(member);
        this.decreaseMemberCount();

    }

    @JsonIgnore
    public Map<Meeting, Integer> getMeetingAttendenceRates() {
        return meetingAttendenceRates;
    }

    public void setMeetingAttendenceRates(Map<Meeting, Integer> meetingAttendenceRates) {
        this.meetingAttendenceRates = meetingAttendenceRates;
    }

    public void addAttendanceRateOfMeeting(Meeting meeting, int rateOfAttendance) {
        getMeetingAttendenceRates().put(meeting, rateOfAttendance);
    }

    public void clearAttendanceRateOfMeeting() {
        setMeetingAttendenceRates(new HashMap<Meeting, Integer>());
    }
    
    public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

    public void removeMeeting(Meeting meeting) {
        this.getMeetings().remove(meeting);
        this.setMeetingCount(getMeetings().size());
    }
}
