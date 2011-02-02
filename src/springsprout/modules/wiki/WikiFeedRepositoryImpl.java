package springsprout.modules.wiki;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.WikiFeed;

import java.util.List;

@Repository
public class WikiFeedRepositoryImpl extends HibernateGenericDao<WikiFeed> implements WikiFeedRepository {

    public WikiFeed findByLink(String link) {
        return (WikiFeed) getCurrentSession().createCriteria(WikiFeed.class).add(Restrictions.eq("link", link)).uniqueResult();
    }

    public void deleteAll() {
        for(WikiFeed wf : getAll()){
            delete(wf);
        }
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<WikiFeed> getAll() {
        return getCurrentSession().createCriteria(WikiFeed.class).addOrder(Order.desc("published")).list();
    }
}
