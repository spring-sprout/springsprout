package springsprout.modules.term;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.common.web.support.OrderParam;
import springsprout.common.web.support.Paging;
import springsprout.domain.DevTerm;
import springsprout.domain.Member;
import springsprout.modules.term.support.DevTermContext;
import springsprout.modules.term.support.DevTermSearchParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 1
 * Time: 오전 6:28:55
 */
@Repository
public class DevTermRepositoryImpl extends HibernateGenericDao<DevTerm> implements DevTermRepository {

    @SuppressWarnings("unchecked")
	public List<DevTerm> searchByContext(DevTermContext context) {
        Criteria c = getCurrentSession().createCriteria(DevTerm.class);
        applySearchParam(context.getSearchParam(), c);
        applyPageParam(context.getPaging(), c);
        applyOrderParam(context.getOrderParam(), c);
        c.setCacheable(true);
        return c.list();
    }

    private void applyOrderParam(OrderParam orderParam, Criteria c) {
		String field = orderParam.getField();
		if (!StringUtils.hasText(field))
			c.addOrder(Order.asc("id")); // apply default
		else if (orderParam.getDirection().equals("desc"))
			c.addOrder(Order.desc(field).ignoreCase());
		else
			c.addOrder(Order.asc(field).ignoreCase());

        if(orderParam.getField() != null && !orderParam.getField().equals("created"))
            c.addOrder(Order.desc("created"));
	}

	private void applyPageParam(Paging standardPaging, Criteria c) {
		c.setFirstResult(standardPaging.getFirstRowNumber());
		c.setMaxResults(standardPaging.getSize());
	}

    private void applySearchParam(DevTermSearchParam searchParam, Criteria c) {
		if (StringUtils.hasText(searchParam.getKeyword())) {
            String keyword = searchParam.getKeyword();
            c.createAlias("tags", "tags");
            c.add(Restrictions.or(
                Restrictions.ilike("phrase", keyword, MatchMode.ANYWHERE),
                Restrictions.ilike("tags.tag", keyword, MatchMode.ANYWHERE)
            ));
		}
        if (searchParam.getCreatedBy() != null){
            c.add(Restrictions.eq("member", searchParam.getCreatedBy()));
        }
        String howRU = searchParam.getHowRU();
        if(howRU != null){
            if(howRU.equals("lonely")){
                 c.add(Restrictions.eq("korTermCount", 0));
            } else if(howRU.equals("hot")){
                 c.add(Restrictions.ge("korTermCount", 1));
            }
        }

        Integer[] devTermIds = searchParam.getFavTermKeys();
        if(searchParam.getFavMember() != null && devTermIds != null && devTermIds.length >= 0){
            if(devTermIds.length == 0)
                devTermIds = new Integer[]{-1};
            c.add(Restrictions.in("id", devTermIds));
        }

	}

    public int getTotalRowsCount(DevTermSearchParam searchParam) {
        Criteria c = getCurrentSession().createCriteria(DevTerm.class)
                .setProjection(Projections.rowCount());
        applySearchParam(searchParam, c);
        c.setCacheable(true);
		return (Integer) c.uniqueResult();
	}

    public DevTerm searchByPhrase(String phrase) {
        return (DevTerm) getCurrentSession().createCriteria(DevTerm.class)
                .setCacheable(true)
                .add(Restrictions.eq("phrase", phrase))
                .uniqueResult();
    }

    public boolean isUserTheTermManager(Member currentUser, int id) {
        DevTerm devTerm = (DevTerm) getCurrentSession()
                .createCriteria(DevTerm.class)
                .setCacheable(true)
                .add(Restrictions.eq("id", id))
                .add(Restrictions.eq("member", currentUser))
                .uniqueResult();
        return devTerm != null;
    }

}
