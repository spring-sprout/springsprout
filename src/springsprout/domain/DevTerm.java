package springsprout.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import springsprout.common.util.DateUtils;
import springsprout.common.util.StringUtils;

import javax.persistence.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 1
 * Time: 오전 5:50:35
 */
@Entity
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class DevTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 50)
    private String phrase;
    @Column
    @Type(type = "text")
    private String details;
    @ManyToOne
    private Member member;
    @OneToMany(mappedBy = "devTerm")
    @OrderBy("voteUp DESC")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private List<KorTerm> korTerms;
    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("created ASC")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private List<Comment> comments;
    @ManyToMany(cascade = CascadeType.ALL)
    @OrderBy("tag ASC")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private List<Tag> tags;
    @OneToOne
    private KorTerm selectedKorTerm;
    @Column
    private int viewCount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column
    private boolean notifiable;
    @Column
    private int korTermCount;

    public DevTerm() {
        this.notifiable = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<KorTerm> getKorTerms() {
        if(this.korTerms == null){
            this.korTerms = new ArrayList<KorTerm>();
        }
        Collections.sort(korTerms, KorTermComparator.voteComparator());
        return korTerms;
    }

    public void setKorTerms(List<KorTerm> korTerms) {
        this.korTerms = korTerms;
    }

    public KorTerm getSelectedKorTerm() {
        return selectedKorTerm;
    }

    public void setSelectedKorTerm(KorTerm selectedKorTerm) {
        this.selectedKorTerm = selectedKorTerm;
    }

    public List<Comment> getComments() {
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

    public void addComment(Comment comment) {
        getComments().add(comment);
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void upViewCount() {
        this.viewCount++;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isNotifiable() {
        return notifiable;
    }

    public void setNotifiable(boolean notifiable) {
        this.notifiable = notifiable;
    }

    public int getKorTermCount() {
        return korTermCount;
    }

    public void setKorTermCount(int korTermCount) {
        this.korTermCount = korTermCount;
    }

    public void addKorTerm(KorTerm korTerm) {
        getKorTerms().add(korTerm);
        this.korTermCount = getKorTerms().size();
    }

    public String getCreatedText(){
        return DateUtils.getShortDate(this.created);
    }

    public String getSmallDetails(){
        return StringUtils.cutBytes(this.details, 130);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DevTerm devTerm = (DevTerm) o;

        if (!phrase.equals(devTerm.phrase)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return phrase.hashCode();
    }

    public void delete(KorTerm korTerm) {
        getKorTerms().remove(korTerm);
        this.korTermCount = getKorTerms().size();
    }

    static class KorTermComparator implements Comparator<KorTerm> {

        public int compare(KorTerm o1, KorTerm o2) {
            return o2.getVote() - o1.getVote();
        }

        private static Comparator<KorTerm> voteComparator() {
            return new KorTermComparator();
        }

    }
}
