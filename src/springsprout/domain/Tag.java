package springsprout.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 1
 * Time: 오전 6:16:11
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"tag"}))
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Tag implements Serializable{

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    @Column(length = 100)
    private String tag;

    public Tag() {
    }

    public Tag(String tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
}
