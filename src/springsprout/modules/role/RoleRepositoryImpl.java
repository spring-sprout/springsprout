package springsprout.modules.role;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.Role;

import java.util.List;

@Repository
public class RoleRepositoryImpl extends HibernateGenericDao<Role> implements RoleRepository{

	public void add(Role role) {
		role.setName(role.getName().toLowerCase());
		super.add(role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoleList() {
		Criteria c = getCurrentSession().createCriteria(Role.class);
		c.addOrder(Order.asc("name"));
		return c.list();
	}

	public void update(Role role) {
		role.setName(role.getName().toLowerCase());
		super.update(role);
	}

	public Role getMemberRole() {
		return (Role) getCurrentSession().createCriteria(Role.class).add(Restrictions.ilike("name", DEFAULT_ROLE_NAME)).uniqueResult();
	}

	public Role getAdminRole() {
        return (Role) getCurrentSession().createCriteria(Role.class).add(Restrictions.ilike("name", ADMIN_ROLE_NAME)).uniqueResult();
    }


}
