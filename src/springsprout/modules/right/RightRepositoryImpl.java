package springsprout.modules.right;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.Right;

import java.util.List;

@Repository
public class RightRepositoryImpl extends HibernateGenericDao<Right> implements RightRepository{

	@SuppressWarnings("unchecked")
	public List<Right> getRightList() {
		Criteria c = getCurrentSession().createCriteria(Right.class);
		c.addOrder(Order.asc("name"));
		return c.list();
	}
	
}
