package springsprout.modules.wiki;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.WikiSpace;

import java.util.List;

@Repository
public class WikiSpaceRepositoryImpl extends HibernateGenericDao<WikiSpace> implements WikiSpaceRepository {

    public WikiSpace findByKey(String key) {
        return (WikiSpace) getCurrentSession().createCriteria(WikiSpace.class).add(Restrictions.eq("key", key)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<WikiSpace> getAll() {
        return getCurrentSession().createCriteria(WikiSpace.class).addOrder(Order.asc("name")).list();
    }
}
