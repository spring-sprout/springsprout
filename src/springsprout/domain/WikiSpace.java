package springsprout.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 3
 * Time: 오후 10:15:23
 */
@Entity
public class WikiSpace {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    @Column(length = 100)
    private String name;
    @Column(length = 100)
    private String key;
    @Column(length = 200)
    private String link;
    @Column
    @Type(type = "text")
    private String descr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
