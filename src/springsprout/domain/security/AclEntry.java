package springsprout.domain.security;

import javax.persistence.*;

@Entity(name="Acl_Entry")
public class AclEntry {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(columnDefinition="bigserial")
	private int id;
	@Column(name="acl_Object_Identity", columnDefinition="bigint")
	private int aclObjectIdentity;
	@Column(name="ace_Order")
	private int aceOrder;
	@Column(columnDefinition="bigint")
	private int sid;
	@Column(columnDefinition="integer")
	private int mask;
	private boolean granting;
	@Column(name="audit_Success")
	private boolean auditSuccess;
	@Column(name="audit_Failure")
	private boolean auditFailure;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAclObjectIdentity() {
		return aclObjectIdentity;
	}
	public void setAclObjectIdentity(int aclObjectIdentity) {
		this.aclObjectIdentity = aclObjectIdentity;
	}
	public int getAceOrder() {
		return aceOrder;
	}
	public void setAceOrder(int aceOrder) {
		this.aceOrder = aceOrder;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getMask() {
		return mask;
	}
	public void setMask(int mask) {
		this.mask = mask;
	}
	public boolean getGranting() {
		return granting;
	}
	public void setGranting(boolean granting) {
		this.granting = granting;
	}
	public boolean getAuditSuccess() {
		return auditSuccess;
	}
	public void setAuditSuccess(boolean auditSuccess) {
		this.auditSuccess = auditSuccess;
	}
	public boolean getAuditFailure() {
		return auditFailure;
	}
	public void setAuditFailure(boolean auditFailure) {
		this.auditFailure = auditFailure;
	}
}
