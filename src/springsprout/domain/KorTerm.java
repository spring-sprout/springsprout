package springsprout.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;
import springsprout.common.util.DateUtils;
import springsprout.common.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**                 
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 1
 * Time: 오전 5:59:28
 */
@Entity
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class KorTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Member member;
    @Column(length = 100)
    private String korPhrase;
    @Column
    @Type(type = "text")
    private String details;
    @Column
    private int voteUp;
    @Column
    private int voteDown;
    @OneToMany
    @OrderBy("created ASC")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private List<Comment> comments;
    @ManyToOne
    private DevTerm devTerm;
    @Formula("voteUp - voteDown")
    private int vote;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getKorPhrase() {
        return korPhrase;
    }

    public void setKorPhrase(String korPhrase) {
        this.korPhrase = korPhrase;
    }

    public int getVoteUp() {
        return voteUp;
    }

    public void setVoteUp(int voteUp) {
        this.voteUp = voteUp;
    }

    public int getVoteDown() {
        return voteDown;
    }

    public void setVoteDown(int voteDown) {
        this.voteDown = voteDown;
    }

    public List<Comment> getComments() {
        if(this.comments == null)
            this.comments = new ArrayList<Comment>();
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public DevTerm getDevTerm() {
        return devTerm;
    }

    public void setDevTerm(DevTerm devTerm) {
        this.devTerm = devTerm;
    }

    public void voteUp() {
        this.voteUp++;
    }

    public void voteDown() {
        this.voteDown++;
    }

    public void cancleVoteDown() {
        this.voteDown--;
    }

    public void cancleVoteUp() {
        this.voteUp--;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
    public String getCreatedText(){
        return DateUtils.getShortDate(this.created);
    }

    public void addComment(Comment comment) {
        getComments().add(comment);
    }

    public String getSmallPhrase(){
        return StringUtils.cutBytes(this.korPhrase, 20);
    }
}
