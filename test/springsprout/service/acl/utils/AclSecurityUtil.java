package springsprout.service.acl.utils;


import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;

public interface AclSecurityUtil {
	public void addPermission(Object securedObject, Permission permission, Class<?> clazz);
    public void addPermission(Object securedObject, Sid recipient, Permission permission, Class<?> clazz);
    
    /**
     * acl_entry 테이블의 내용을 지운다.
     * @param securedObject
     * @param recipient
     * @param permission
     * @param clazz
     */
    public void deletePermission(Object securedObject, Sid recipient, Permission permission, Class<?> clazz); 
	/**
	 * acl_object_identity, acl_entry 테이블의 내용을 지운다.<br/>
	 * @param securedObject
	 * @param clazz
	 */
    public void deleteAclObject( Object securedObject, Class<?> clazz);
}