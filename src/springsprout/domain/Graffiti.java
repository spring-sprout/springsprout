package springsprout.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Graffiti implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Member member;
	
	@Column(length = 300)
	private String contents;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date writeDate = new Date();

    public Graffiti() {
    }

    public Graffiti(String contents, Member currentMember) {
        this.contents = contents;
        this.member = currentMember;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Member getMember() {
		if(member == null){
			member = new Member();
		}
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
}
