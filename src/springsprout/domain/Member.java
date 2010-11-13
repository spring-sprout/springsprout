package springsprout.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.*;
import springsprout.common.SpringSprout2System;
import springsprout.common.annotation.CustomTransfer;
import springsprout.common.annotation.DomainInfo;
import springsprout.common.util.MD5Util;
import springsprout.common.util.RandomStringUtils;
import springsprout.common.util.StringUtils;
import springsprout.domain.enumeration.MemberStatus;
import springsprout.modules.admin.support.MemberMgtDTO;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import java.io.Serializable;
import java.util.*;

@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 100, unique = true)
	private String email;

	@Column(length = 100)
	private String name;

	@Column(length = 64)
	private String password;
	
	@Column
	@Type(type = "springsprout.domain.usertype.MemberStatusType")
	private MemberStatus status;

	@Column(length = 32)
	private String authCode;

	@ManyToMany
	@OrderBy("name ASC")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<Role> roles;

	@ManyToMany(mappedBy = "members")
	@OrderBy("studyName ASC")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<Study> studies;

	@OneToMany(mappedBy = "manager")
	@OrderBy("studyName ASC")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private Set<Study> managedStudies;

	@Temporal(TemporalType.DATE)
	private Date joined;

	@Column(length = 100)
	private String blog;

	@Column(length = 100)
	private String avatar;

	@Temporal(TemporalType.DATE)
	private Date outDate;

	@Column(length = 100)
	private String outReason;

	@OneToMany(mappedBy = "member")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    @BatchSize(size=30)
	private Set<Attendance> attendances;

	@Column
	@DomainInfo("스터디 참석률")
	private Integer totalAttendanceRate;

	@CollectionOfElements(targetElement = Integer.class)
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    @BatchSize(size=30)
	private Map<Study, Integer> studyAttendanceRates;

	@CollectionOfElements(targetElement = Integer.class)
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    @BatchSize(size=30)
	private Map<Study, Integer> studyTrustRates;

	@Column
	@DomainInfo("신뢰도")
	private Integer totalTrustRate;
	
	@Column
	private Boolean isAllowedEmail;

	@Column
	private Boolean isAllowedGoogleTalk;

    @CollectionOfElements(targetElement = Boolean.class)
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    @BatchSize(size=30)
    private Map<DevTerm, Boolean> favoriteTerms;

	@Transient
	private String currentPassword;
	@Transient
	private String newPassword;
	@Transient
	private String newPasswordConfirm;

    public Member(int id) {
        this.id = id;
    }

    public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getOutReason() {
		return outReason;
	}

	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}

	public Member() {
		this.status = MemberStatus.JOIN_WAIT;
		this.authCode = makeAuthCode();
	}

	public Member(String email) {
		this();
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    @JsonIgnore
	public Set<Role> getRoles() {
		if (roles == null)
			roles = new HashSet<Role>();
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isNew() {
		return (this.id == null);
	}

	public MemberStatus getStatus() {
		return status;
	}

	public void setStatus(MemberStatus status) {
		this.status = status;
	}

	public void setStatusJoin() {
		this.status = MemberStatus.JOIN;
	}

	public void setStatusOut() {
		this.status = MemberStatus.OUT;
	}

    public void setStatusCompulsoryOut() {
		this.status = MemberStatus.COMPULSORY_OUT;
	}

	public String makeAuthCode() {
		return RandomStringUtils.getRandomMD5();
	}

	public boolean isUserConfirmed() {
		return MemberStatus.JOIN.equals(status) ? true : false;
	}

    @JsonIgnore
	public String getAuthCode() {
		return authCode;
	}

	public void addRole(Role memberRole) {
		getRoles().add(memberRole);
	}

    @JsonIgnore
	public Set<Study> getStudies() {
		if (studies == null)
			studies = new HashSet<Study>();
		return studies;
	}

	public void setStudies(Set<Study> studies) {
		this.studies = studies;
	}

    @JsonIgnore
	public Set<Study> getManagedStudies() {
		if (managedStudies == null)
			managedStudies = new HashSet<Study>();
		return managedStudies;
	}

	public void setManagedStudies(Set<Study> managedStudies) {
		this.managedStudies = managedStudies;
	}

	public void addManagedStudy(Study study) {
		getStudies().add(study);
		getManagedStudies().add(study);
		study.getMembers().add(this);
		study.setManager(this);
	}

	public void removeJoinedStudy(Study study) {
		getStudies().remove(study);
		study.getMembers().remove(this);
	}

	public void join(Study study) {
		getStudies().add(study);
		study.addMember(this);
	}

	public Date getJoined() {
		return joined;
	}

	public void setJoined(Date joined) {
        if(this.joined == null)
		    this.joined = joined;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public void join() {
		setStatusJoin();
		setJoined(new Date());
	}

	public String getAvatar() {
        if(this.avatar == null) makeAvatar();
		if(!this.avatar.contains("?r=X"))
			this.avatar += "?r=X";
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void makeAvatar() {
		setAvatar(SpringSprout2System.AVATAR_URL + MD5Util.md5toHex(getEmail()) + "?r=X");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public void setAttendances(Set<Attendance> attendances) {
		this.attendances = attendances;
	}

    @JsonIgnore
	public Set<Attendance> getAttendances() {
		if (this.attendances == null)
			this.attendances = new HashSet<Attendance>();
		return attendances;
	}

	public boolean hasRole(Role role) {
		return getRoles().contains(role);
	}

	public boolean isAnonymous() {
		return false;
	}

	public Attendance applyAttendance(Meeting meeting) {
		Attendance attendance = new Attendance(meeting, this);
		meeting.addAttendance(attendance);
		getAttendances().add(attendance);
		return attendance;
	}

	public Attendance cancleAttendance(Meeting meeting) {
		Attendance attendance = meeting.getAttendanceByMember(this);
		meeting.removeAttendance(attendance);
		getAttendances().remove(attendance);
		return attendance;
	}

	public void setTotalAttendanceRate(Integer totalAttendanceRate) {
		this.totalAttendanceRate = totalAttendanceRate;
	}

	public Integer getTotalAttendanceRate() {
		return totalAttendanceRate;
	}

	public void setTotalTrustRate(Integer totalTrustRate) {
		this.totalTrustRate = totalTrustRate;
	}

	public Integer getTotalTrustRate() {
		return totalTrustRate;
	}

    @JsonIgnore
	public Map<Study, Integer> getStudyAttendanceRates() {
		return studyAttendanceRates;
	}

	public void setStudyAttendanceRates(Map<Study, Integer> studyAttendanceRates) {
		this.studyAttendanceRates = studyAttendanceRates;
	}

	public void addStudyAttendanceRate(Study study, int rate) {
		getStudyAttendanceRates().put(study, rate);
	}

	public void clearAttendanceRates() {
		setStudyAttendanceRates(new HashMap<Study, Integer>());
	}

	public void setStudyTrustRates(Map<Study, Integer> studyTrustRates) {
		this.studyTrustRates = studyTrustRates;
	}

    @JsonIgnore
	public Map<Study, Integer> getStudyTrustRates() {
		return studyTrustRates;
	}

	public void clearTrustRates() {
		setStudyTrustRates(new HashMap<Study, Integer>());
	}

	public void addStudyTrustRate(Study study, int rate) {
		getStudyTrustRates().put(study, rate);
	}

	public void setIsAllowedEmail(Boolean isAllowedEmail) {
		this.isAllowedEmail = isAllowedEmail;
	}

	public Boolean getIsAllowedEmail() {
		if(isAllowedEmail == null)
			isAllowedEmail = false;
		return isAllowedEmail;
	}

	public void setIsAllowedGoogleTalk(Boolean isAllowedGoogleTalk) {
		this.isAllowedGoogleTalk = isAllowedGoogleTalk;
	}

	public Boolean getIsAllowedGoogleTalk() {
		if(isAllowedGoogleTalk == null)
			isAllowedGoogleTalk = false;
		return isAllowedGoogleTalk;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

    @CustomTransfer
    public void newPassCheck(MemberMgtDTO mgdto){
        if(!StringUtils.isEmpty(mgdto.getPassword())){
			this.setNewPassword(mgdto.getPassword());
		}
    }

    public void leave(Study study) {
        this.getStudies().remove(study);
        study.remove(this);
    }

    public void changeNoti(boolean isNotifiable) {
        this.isAllowedEmail = isNotifiable;
    }

    @JsonIgnore
    public Map<DevTerm, Boolean> getFavoriteTerms() {
        return favoriteTerms;
    }

    public void setFavoriteTerms(Map<DevTerm, Boolean> favoriteTerms) {
        this.favoriteTerms = favoriteTerms;
    }

    public boolean favorite(DevTerm devTerm) {
        if(getFavoriteTerms().containsKey(devTerm)){
            getFavoriteTerms().remove(devTerm);
            return false;
        }
        else{
            getFavoriteTerms().put(devTerm, true);
            return true;
        }
    }

    public void setup(String encodedPassword) {
        setPassword(encodedPassword);
		makeAvatar();
        setJoined(new Date());
    }

    public void removeStudy(Study study) {
        this.getStudies().remove(study);
    }

    public void addStudy(Study study) {
        this.getStudies().add(study);
    }

    public void updateStatusWith(Member updatedMember) {
        setStatus(updatedMember.getStatus());
    }

    public void updateNotificationsWith(Member updatedMember) {
        setIsAllowedEmail(updatedMember.getIsAllowedEmail());
        setIsAllowedGoogleTalk(updatedMember.getIsAllowedGoogleTalk());
    }

    public void compulsoryOut(String outReason) {
        setStatus(MemberStatus.COMPULSORY_OUT);
        setOutReason(outReason);
        out();       
    }

    public void out(){
        long uniqueValue = Calendar.getInstance().getTimeInMillis();
        setEmail(MD5Util.md5toHex(getEmail() + uniqueValue));
        setOutDate(new Date());
    }

    public void selfOut() {
        setStatus(MemberStatus.OUT);
        out();
    }
}