package springsprout.modules.link;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.Link;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 7. 8
 * Time: 오전 10:16:28
 */
@Repository
public class LinkRepositoryImpl extends HibernateGenericDao<Link> implements LinkRepository {

    @SuppressWarnings("unchecked")
	@Override
    public List<Link> getAll() {
        return getCurrentSession().createCriteria(Link.class).addOrder(Order.desc("updated")).list();
    }
}
