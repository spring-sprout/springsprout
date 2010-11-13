package springsprout.domain.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Acl_Object_Identity")
public class AclObjectIdentity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(columnDefinition="bigserial")
	private int id;
	@Column(name="object_Id_Class", columnDefinition="bigint")	
	private int objectIdClass;
	@Column(name="object_Id_Identity", columnDefinition="bigint")
	private int objectIdIdentity;
	@Column(name="parent_Object", columnDefinition="bigint")
	private int parentObject;
	@Column(name="owner_Sid", columnDefinition="bigint")
	private int ownerSid;
	@Column(name="entries_Inheriting")
	private boolean entriesInheriting;
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getObjectIdClass() {
		return objectIdClass;
	}
	public void setObjectIdClass(int objectIdClass) {
		this.objectIdClass = objectIdClass;
	}
	public int getObjectIdIdentity() {
		return objectIdIdentity;
	}
	public void setObjectIdIdentity(int objectIdIdentity) {
		this.objectIdIdentity = objectIdIdentity;
	}
	public int getParentObject() {
		return parentObject;
	}
	public void setParentObject(int parentObject) {
		this.parentObject = parentObject;
	}
	public int getOwnerSid() {
		return ownerSid;
	}
	public void setOwnerSid(int ownerSid) {
		this.ownerSid = ownerSid;
	}
	public boolean getEntriesInheriting() {
		return entriesInheriting;
	}
	public void setEntriesInheriting(boolean entriesInheriting) {
		this.entriesInheriting = entriesInheriting;
	}
	
	
}
