package springsprout.service.acl.permission;

import org.springframework.security.acls.domain.AbstractPermission;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.CumulativePermission;

@SuppressWarnings("serial")
public class CustomPermission extends AbstractPermission {

	public static final CumulativePermission ReadWrite = new CumulativePermission().set( BasePermission.READ).set( BasePermission.WRITE);
	public static final CumulativePermission CustomAdmin = new CumulativePermission().set( BasePermission.ADMINISTRATION);
	
	protected CustomPermission(int mask) {
		super(mask);
    }

    protected CustomPermission(int mask, char code) {
        super(mask, code);
    }

}
