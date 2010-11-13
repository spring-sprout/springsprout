package springsprout.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class SeminarDetailSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    @Column
    private int orderCount;

    @ManyToMany
    private Set<Member> speakers;

    @Column(length = 100)
    private String topic;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Transient
    private int duration;

    @ManyToOne
    private Resource resource;

    @ManyToOne
    private Seminar seminar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Member> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<Member> speakers) {
        this.speakers = speakers;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getDuration() {
        long time = getEndTime().getTime() - getStartTime().getTime();
        return (int)time/1000/60;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public Seminar getSeminar() {
        return seminar;
    }

    public void setSeminar(Seminar seminar) {
        this.seminar = seminar;
    }
}
