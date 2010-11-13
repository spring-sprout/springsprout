package springsprout.modules.notice;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.Notice;
import springsprout.modules.notice.support.NoticeCriteria;

@Repository
public class NoticeRepositoryImpl  extends HibernateGenericDao<Notice> implements NoticeRepository {

    @SuppressWarnings({"unchecked"})
    public List<Notice> getAllByCreatedOrderDesc() {
        return getCurrentSession().createCriteria(Notice.class)
                .addOrder(Order.desc("id"))
                .list();
    }
    
    public int getTotalRowsCount(NoticeCriteria cri) {
        Criteria c = getCurrentSession().createCriteria(Notice.class)
        .setProjection(Projections.rowCount());

        return (Integer) c.uniqueResult();
    }                                                  

    public Notice getTheLastedOne() {
        return (Notice) getCurrentSession().createCriteria(Notice.class)
                .setMaxResults(1)
                .addOrder(Order.desc("id"))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Notice> getListSorted(NoticeCriteria cri) {
        Criteria c = getCurrentSession().createCriteria(Notice.class);
        c.setCacheable(true);
        applyPagingCriteria(cri, c);
        return c.list();
    }
    
    private void applyPagingCriteria(NoticeCriteria cri, Criteria c) {
        //페이징 처리
        c.setFirstResult(cri.getStart());

        if(cri.getLimit() > 0) {
            c.setMaxResults(cri.getLimit());
        }

        //특정 필드로 정렬
        String sort = cri.getSort();
        String dir = cri.getDir();
        if(StringUtils.hasText(sort) && StringUtils.hasText(dir)) {
            if("ASC".equals(dir.toUpperCase())) {
                c.addOrder(Order.asc(sort));
            } else {
                c.addOrder(Order.desc(sort));
            }
        } else {
            c.addOrder(Order.desc("id"));
        }
    }
}
