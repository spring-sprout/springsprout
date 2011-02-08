package springsprout.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import springsprout.domain.enumeration.ResourceType;

import javax.persistence.*;

@Entity
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length=150)
	private String url;
	@Transient
	private String uploadingFile;
	@OneToOne
	private UploadFile uploadFile;
	@Column(length=100)
	private String title;
	@Column
	@Type(type="springsprout.domain.usertype.ResourceTypeUserType")
	private ResourceType resourceType;
	@ManyToOne
	private Member owner;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Member getOwner() {
		return owner;
	}
	public void setOwner(Member owner) {
		this.owner = owner;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setUploadingFile(String uploadingFile) {
		this.uploadingFile = uploadingFile;
	}
	public String getUploadingFile() {
		return uploadingFile;
	}
	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public UploadFile getUploadFile() {
		return uploadFile;
	}
	
	public String getFileDownUrl(){
		if(getUploadFile() != null)
			return "/file/download.do?id=" + getUploadFile().getId();
		return "";
	}
}
