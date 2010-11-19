package springsprout.modules.study;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.Member;
import springsprout.domain.Study;
import springsprout.domain.enumeration.StudyStatus;
import springsprout.modules.study.support.StudyContext;
import springsprout.modules.study.support.StudyCriteria;
import springsprout.modules.study.support.StudySearchParam;

import java.util.List;

@Repository
public class StudyRepositoryImpl extends HibernateGenericDao<Study> implements StudyRepository{

    @SuppressWarnings("unchecked")    
    public List<Study> getStudyList() {
		Criteria c = getCurrentSession().createCriteria(Study.class);
		c.setCacheable(true);
        c.addOrder(Order.desc("id"));
        
        return c.list();
    }
    @SuppressWarnings("unchecked")
	public List<Study> getStudyList(StudyCriteria cri) {
		Criteria c = getCurrentSession().createCriteria(Study.class);
		c.setCacheable(true);
        applyPagingCriteria(cri, c);
        return c.list();
	}
        
    private void applyPagingCriteria(StudyCriteria cri, Criteria c) {
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

    public int getTotalRowsCount(StudyCriteria cri) {
        Criteria c = getCurrentSession().createCriteria(Study.class)
                .setProjection(Projections.rowCount());
        return (Integer) c.uniqueResult();
    }

	@SuppressWarnings("unchecked")
	public List<Study> getStudyListByContext(StudyContext context) {
		Criteria c = getCurrentSession().createCriteria(Study.class);
		c.setCacheable(true);
		applySearchParam(context.getSearchParam(), c);
		return c.list();
	}

	private void applySearchParam(StudySearchParam searchParam, Criteria c) {
		if (StringUtils.hasText(searchParam.getStudyName())) {
			c.add(Restrictions.like("studyName", searchParam.getStudyName(),
					MatchMode.ANYWHERE));
		}
	}

	@SuppressWarnings("unchecked")
	public List<Study> findActiveStudies() {
		return findActiveStudies(-1);
	}

	@SuppressWarnings("unchecked")
	public List<Study> findPastStudies() {
		Criteria c = getCurrentSession().createCriteria(Study.class);
		c.setCacheable(true);
		c.add(Restrictions
				.in("status", new Integer[] {
						StudyStatus.ENDED.getValue()}));
		c.addOrder(Order.asc("studyName"));
		return c.list();
	}

    public boolean isUserAlreayJoinedIn(Member user, int studyId) {
        Study study = (Study) getCurrentSession()
                .createQuery("from Study s where :user in elements(s.members) and s.id = :studyId")
                .setEntity("user", user)
                .setInteger("studyId", studyId)
                .uniqueResult();
        return study != null;
    }
    
    public boolean isUserTheStudyManager(Member user, int studyId) {
        Study study = (Study) getCurrentSession()
                .createQuery("from Study s where :user = s.manager and s.id = :studyId")
                .setEntity("user", user)
                .setInteger("studyId", studyId)
                .uniqueResult();
        return study != null;
    }

    public List<Study> findActiveStudies(int rows) {
        Criteria c = getCurrentSession().createCriteria(Study.class);
		c.setCacheable(true);
		c.add(Restrictions.in("status", new Integer[] {
				StudyStatus.OPEN.getValue(),
				StudyStatus.STARTED.getValue(),
				StudyStatus.UPDATED.getValue() }));
		c.addOrder(Order.asc("studyName"));
        if(rows > 0)
            c.setMaxResults(rows);
		return c.list();
    }

    public Member getManagerByStudyId(Integer studyId) {
        return (Member) getCurrentSession().createQuery("select s.manager from Study s where s.id = :studyId")
                .setInteger("studyId", studyId)
                .uniqueResult();
    }


}
