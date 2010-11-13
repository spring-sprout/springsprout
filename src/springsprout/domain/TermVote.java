package springsprout.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 1. 6
 * Time: 오후 11:31:20
 */
@Entity
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class TermVote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private KorTerm korTerm;
    @ManyToOne
    private Member member;
    @Column
    private boolean isUp;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public TermVote() {
    }

    public TermVote(Member member, KorTerm korTerm, boolean isUp) {
        this.member = member;
        this.korTerm = korTerm;
        this.isUp = isUp;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public KorTerm getKorTerm() {
        return korTerm;
    }

    public void setKorTerm(KorTerm korTerm) {
        this.korTerm = korTerm;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isUp() {
        return isUp;
    }

    public void setIsUp(boolean isUp) {
        this.isUp = isUp;
    }
}
