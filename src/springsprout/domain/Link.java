package springsprout.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 7. 8
 * Time: 오전 10:12:15
 */
@Entity
public class Link {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column
    @NotNull
    @Size(min = 1, message = "입력해 주세요.")
    String title;

    @Column
    @NotNull
    @Size(min = 1, message = "입력해 주세요.")
    String url;

    @Column
    @Type(type = "text")
    String descr;

    @ManyToOne
    Member writer;

    @Temporal(TemporalType.TIMESTAMP)
    Date created;

    @Temporal(TemporalType.TIMESTAMP)
    Date updated;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Member getWriter() {
        return writer;
    }

    public void setWriter(Member writer) {
        this.writer = writer;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
