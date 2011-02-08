package springsprout.service.acl.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class AclSecurityUtilImpl implements AclSecurityUtil {

	private static Log logger = LogFactory.getLog(AclSecurityUtil.class);

	private MutableAclService mutableAclService;

	public void addPermission(Object securedObject, Permission permission,
			Class<?> clazz) {
		addPermission(securedObject, new PrincipalSid(getUsername()),
				permission, clazz);

	}

	public void addPermission(Object identifier, Sid recipient, Permission permission, Class<?> clazz) {
		MutableAcl acl;
		ObjectIdentity oid = new ObjectIdentityImpl(clazz.getCanonicalName(), (Long) identifier);
		
		try {
			acl = (MutableAcl) mutableAclService.readAclById(oid);
		} catch (NotFoundException nfe) {
			acl = mutableAclService.createAcl(oid);
		}

		acl.insertAce(acl.getEntries().size(), permission, recipient, true);
		mutableAclService.updateAcl(acl);

		if (logger.isDebugEnabled()) {
			logger.debug("Added permission " + permission + " for Sid "
					+ recipient + " securedObject " + identifier);
		}

	}
	
	public void deletePermission(Object securedObject, Sid recipient, Permission permission, Class<?> clazz) {
		ObjectIdentity oid = new ObjectIdentityImpl(clazz.getCanonicalName(), (Long)securedObject);
		MutableAcl acl = (MutableAcl) mutableAclService.readAclById(oid);

		// Remove all permissions associated with this particular recipient
		// (string equality used to keep things simple)
		List<AccessControlEntry> entries = acl.getEntries();

		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i).getSid().equals(recipient)
					&& entries.get(i).getPermission().equals(permission)) {
				acl.deleteAce(i);

			}
		}
		mutableAclService.updateAcl(acl);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Deleted securedObject " + securedObject
					+ " ACL permissions for recipient " + recipient);
		}
	}
	
	public void deleteAclObject( Object securedObject, Class<?> clazz) {
		ObjectIdentity oid = new ObjectIdentityImpl(clazz.getCanonicalName(), (Long)securedObject);
		mutableAclService.deleteAcl( oid, true);
	}

	protected String getUsername() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		if (auth.getPrincipal() instanceof UserDetails) {
			return ((UserDetails) auth.getPrincipal()).getUsername();
		} else {
			return auth.getPrincipal().toString();
		}
	}

	/**
	 * @param mutableAclService
	 */
	public void setMutableAclService(MutableAclService mutableAclService) {
		this.mutableAclService = mutableAclService;
	}

	/**
	 * @return
	 */
	public MutableAclService getMutableAclService() {
		return mutableAclService;
	}

}
