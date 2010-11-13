package springsprout.service.acl.permission;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.model.Permission;

/**
 * ROCK(문서 잠금) Permission extends 
 * @author JUNE
 *
 */
@SuppressWarnings("serial")
public class ExtendedPermission extends BasePermission {

	public static final Permission ROCK = new CustomPermission( 1 << 5, 'a'); // 32
	
	protected ExtendedPermission( int mask, char code) {
		super( mask, code);
	}
}
