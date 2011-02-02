package springsprout.service.acl;

import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.ObjectIdentityRetrievalStrategy;

public class CustomRetrievalStrategy implements ObjectIdentityRetrievalStrategy {

	public ObjectIdentity getObjectIdentity(Object domainObject) {
		if ( domainObject instanceof Long) {
			Long domainId = (Long) domainObject;
			return new ObjectIdentityImpl( Long.class, domainId);
		} else 
			return new ObjectIdentityImpl( domainObject);
	}
}
