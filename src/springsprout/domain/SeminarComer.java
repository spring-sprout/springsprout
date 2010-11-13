package springsprout.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import springsprout.domain.enumeration.SeminarComerStatus;

import javax.persistence.*;
import java.util.Date;


@Entity
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class SeminarComer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 100)
	private String email;

    @Column(length = 100)
	private String name;

    @Column(length = 10)
    private String password;

    @Column(length = 20)
	private String tel;

    @Column(length = 50)
    private String job;

    @Column
    @Type(type = "text")
	private String note;

    @Column
    @Type(type = "springsprout.domain.usertype.SeminarComerStatusType")
	private SeminarComerStatus status;

    @Temporal(TemporalType.TIMESTAMP)
	private Date created;

    @ManyToOne
    private Seminar seminar;

    @ManyToOne
    private Member member;

    public SeminarComer() {
        this(null);
	}

    public SeminarComer(Member currentMember) {
        this.created = new Date();
        if(currentMember != null){
            setEmail(currentMember.getEmail());
            setName(currentMember.getName());
        }
    }


    public boolean isThisMember() {
		return this.member != null ? true : false;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seminar getSeminar() {
		return seminar;
	}

	public void setSeminar(Seminar seminar) {
		this.seminar = seminar;
	    enroll();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public SeminarComerStatus getStatus() {
		return status;
	}

	public void setStatus(SeminarComerStatus status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

    public void makeFromMember(Member member) {
        setMember(member);
        setName(member.getName());
        setEmail(member.getEmail());
        setPassword(member.getPassword());
    }

    public boolean isEnrolled() {
        return getStatus() == SeminarComerStatus.ENROLLED;
    }

    public boolean isConfirmed() {
        return getStatus() == SeminarComerStatus.CONFIRMED;
    }

    public boolean isJoined() {
        return getStatus() == SeminarComerStatus.JOINED;
    }

    public void enroll() {
        setStatus(SeminarComerStatus.ENROLLED);
    }

    public void confirm() {
        setStatus(SeminarComerStatus.CONFIRMED);
    }

    public void join() {
        setStatus(SeminarComerStatus.JOINED);
    }
}
