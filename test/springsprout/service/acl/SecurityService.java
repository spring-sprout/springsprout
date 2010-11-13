package springsprout.service.acl;

import org.aspectj.lang.JoinPoint;

public interface SecurityService {
	public void setPermissions( JoinPoint jp);
	public void deletePermissions( JoinPoint jp);
	public void deleteAclObj( JoinPoint jp);
}
