package springsprout.modules.study.meeting;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.Meeting;
import springsprout.domain.enumeration.MeetingStatus;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 2
 * Time: 오후 8:57:09
 */
@Repository
@SuppressWarnings("unchecked")
public class MeetingRepositoryImpl extends HibernateGenericDao<Meeting> implements MeetingRepository {

	public List<Meeting> findActiveMeetings() {
		return getCurrentSession().createCriteria(Meeting.class)
			.add(Restrictions.eq("status", MeetingStatus.OPEN)).list();
	}

	public List<Meeting> getMeetingListByStudyId(int studyId, int rows) {
        return getCurrentSession().createCriteria(Meeting.class)
                .setCacheable(true)
                .add(Restrictions.eq("study.id", studyId))
                .addOrder(Order.desc("id"))
                .setProjection(Projections.projectionList()
                        .add(Projections.id())
                        .add(Projections.property("title"))
                        .add(Projections.property("cnt"))
                        .add(Projections.property("status"))
                        .add(Projections.property("openDate"))
                        .add(Projections.property("openTime"))
                        .add(Projections.property("closeDate"))
                        .add(Projections.property("closeTime"))
                ).setMaxResults(rows).list();
    }

    public List<Meeting> findActiveMeetings(int count) {
        return getCurrentSession().createCriteria(Meeting.class)
			.add(Restrictions.eq("status", MeetingStatus.OPEN))
            .addOrder(Order.desc("openDate"))
            .setMaxResults(count)
            .list();
    }
}
