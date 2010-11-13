package springsprout.modules.role;

import springsprout.common.dao.GenericDao;
import springsprout.domain.Role;

public interface RoleRepository extends GenericDao<Role> {

    public final static String DEFAULT_ROLE_NAME = "member";
    public final static String ADMIN_ROLE_NAME = "admin";

	void add(Role role);

	void update(Role role);

	Role getMemberRole();

	Role getAdminRole();

}