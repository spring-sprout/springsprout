package springsprout.domain.security;

import javax.persistence.*;

@Entity(name="Acl_Sid")
public class AclSid {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(columnDefinition="bigserial")
	private int id;
	private boolean principal;
	private String sid;
	
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean getPrincipal() {
		return principal;
	}
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	
}
