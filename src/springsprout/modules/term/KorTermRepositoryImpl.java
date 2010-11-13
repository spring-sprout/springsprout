package springsprout.modules.term;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.common.web.support.OrderParam;
import springsprout.common.web.support.Paging;
import springsprout.domain.KorTerm;
import springsprout.modules.term.support.DevTermContext;
import springsprout.modules.term.support.DevTermSearchParam;

import java.util.List;

@Repository
public class KorTermRepositoryImpl extends HibernateGenericDao<KorTerm> implements KorTermRepository {

    public int getTotalRowsCount(DevTermSearchParam searchParam) {
        Criteria c = getCurrentSession().createCriteria(KorTerm.class)
                .setCacheable(true)
                .setProjection(Projections.rowCount());
        applySearchParam(searchParam, c);
        return (Integer) c.uniqueResult();
    }

    private void applySearchParam(DevTermSearchParam searchParam, Criteria c) {
        if(searchParam.getKorTermCreatedBy() != null)
            c.add(Restrictions.eq("member", searchParam.getKorTermCreatedBy()));

        if (StringUtils.hasText(searchParam.getKeyword())) {
            String keyword = searchParam.getKeyword();
            c.createAlias("devTerm", "devTerm");
            c.createAlias("devTerm.tags", "tags");
            c.add(Restrictions.or(
                Restrictions.ilike("devTerm.phrase", "%" + keyword + "%", MatchMode.ANYWHERE),
                Restrictions.ilike("tags.tag", keyword, MatchMode.ANYWHERE)
            ));
		}
    }

    @SuppressWarnings("unchecked")
	public List<KorTerm> searchTerms(DevTermContext context) {
        Criteria c = getCurrentSession().createCriteria(KorTerm.class);
        applySearchParam(context.getSearchParam(), c);
        applyPageParam(context.getPaging(), c);
        applyOrderParam(context.getOrderParam(), c);
        c.setCacheable(true);
        return c.list();
    }

    public KorTerm searchByPhraseAndDevTerm(int termId, String korPhrase) {
        return (KorTerm) getCurrentSession().createCriteria(KorTerm.class)
                .add(Restrictions.eq("devTerm.id", termId))
                .add(Restrictions.eq("korPhrase", korPhrase))
                .setCacheable(true)
                .uniqueResult();
    }

    private void applyOrderParam(OrderParam orderParam, Criteria c) {
		String field = orderParam.getField();
		if (!StringUtils.hasText(field))
			c.addOrder(Order.asc("id")); // apply default
		else if (orderParam.getDirection().equals("desc"))
			c.addOrder(Order.desc(field));
		else
			c.addOrder(Order.asc(field));

        if(!orderParam.getField().equals("created"))
            c.addOrder(Order.desc("created"));
	}

	private void applyPageParam(Paging standardPaging, Criteria c) {
		c.setFirstResult(standardPaging.getFirstRowNumber());
		c.setMaxResults(standardPaging.getSize());
	}

}
