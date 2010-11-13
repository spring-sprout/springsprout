package springsprout.domain.security;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Acl 테스트 도메인 클래스
 * @author June
 *
 */
@Entity(name = "AclBbs")
public class AclBbs {

	@Id
	public long id;
	@Column
	public String username;
	@Column
	public String title;
	@Column
	public String contents;
	@Column
	public Timestamp createdAt;
	@Column
	private boolean isRocked;

	public AclBbs() {}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setIsRocked(boolean isRocked) {
		this.isRocked = isRocked;
	}
	public boolean getIsRocked() {
		return isRocked;
	}
	
	@Override
	public String toString() {
		return "SimpleBbs [ID=" + id + ", contents=" + contents
		+ ", createdAt=" + createdAt + ", isRocked=" + isRocked
		+ ", title=" + title + ", username="
		+ username + "]";
	}
}
