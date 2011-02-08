package springsprout.service.acl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.acls.domain.*;
import org.springframework.security.acls.model.Sid;
import springsprout.service.acl.permission.ExtendedPermission;
import springsprout.service.acl.utils.AclSecurityUtil;

import java.lang.reflect.Method;

@Aspect
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    @Qualifier("aclSecurity")
    private AclSecurityUtil aclSecurityUtil;

    @After(value="execution(* springsprout.common.dao.HibernateGenericDao.add(..))")
    public void setPermissions( JoinPoint jp) {
		Object securedObject = jp.getArgs()[0];
		Class<?> secureClass = securedObject.getClass();
		Object identifier = getIdentifier( secureClass, securedObject);

		Sid sidAdmin = new GrantedAuthoritySid( "ROLE_ADMIN");
		Sid sidManager = new GrantedAuthoritySid( "ROLE_MANAGER");
		Sid sidUser = new GrantedAuthoritySid( "ROLE_USER");

        aclSecurityUtil.addPermission( identifier, BasePermission.ADMINISTRATION, secureClass);
        aclSecurityUtil.addPermission( identifier, sidAdmin, BasePermission.ADMINISTRATION, secureClass);
        aclSecurityUtil.addPermission( identifier, sidManager, new CumulativePermission().set( BasePermission.READ).set( BasePermission.WRITE), secureClass);
        aclSecurityUtil.addPermission( identifier, sidManager, ExtendedPermission.ROCK, secureClass);
        aclSecurityUtil.addPermission( identifier, sidUser, BasePermission.READ, secureClass);
    }

	private Object getIdentifier( Class<?> secureClass, Object securedObject) {
		Object identifier;
		Method method;
		try {
			method = secureClass.getMethod( "getId", new Class[] {});
			identifier = method.invoke( securedObject, new Object[] {});
		} catch (Exception e) {
            throw new IdentityUnavailableException("Could not extract identity from object " + securedObject, e);
        }
		return identifier;
	}
    
	@After(value="execution(* springsprout.modules.acl.AclBbsRepository.delete(..))")
    public void deletePermissions( JoinPoint jp) {

    	Object securedObject = jp.getArgs()[0];
		Class<?> secureClass = securedObject.getClass();
		Object identifier = getIdentifier( secureClass, securedObject);
    	
    	Sid sid = new PrincipalSid("test1");
        Sid sidAdmin = new GrantedAuthoritySid("ROLE_ADMIN");
        Sid sidManager = new GrantedAuthoritySid("ROLE_MANAGER");
        Sid sidUser = new GrantedAuthoritySid("ROLE_USER");
    	
    	aclSecurityUtil.deletePermission( identifier, sid, BasePermission.ADMINISTRATION, secureClass);
    	aclSecurityUtil.deletePermission( identifier, sidAdmin, BasePermission.ADMINISTRATION, secureClass);
        aclSecurityUtil.deletePermission( identifier, sidManager, new CumulativePermission().set( BasePermission.READ).set( BasePermission.WRITE), secureClass);
        aclSecurityUtil.deletePermission( identifier, sidManager, ExtendedPermission.ROCK, secureClass);
        aclSecurityUtil.deletePermission( identifier, sidUser, BasePermission.READ, secureClass);
        
    }
	@After(value="execution(* springsprout.modules.acl.AclBbsRepository.delete(..))")
    public void deleteAclObj( JoinPoint jp) {
    	Object securedObject = jp.getArgs()[0];
		Class<?> secureClass = securedObject.getClass();
		Object identifier = getIdentifier( secureClass, securedObject);
		
    	aclSecurityUtil.deleteAclObject( identifier, secureClass);
    }
}
