package springsprout.service.acl;

import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.ObjectIdentityRetrievalStrategy;
import springsprout.domain.security.AclBbs;

public class ListRetrievalStrategy implements ObjectIdentityRetrievalStrategy {

	public ObjectIdentity getObjectIdentity(Object domainObject) {
		Long domainId = Long.valueOf(( (String)domainObject));
		return new ObjectIdentityImpl(AclBbs.class, domainId);
	}

}
