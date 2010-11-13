package springsprout.modules.study.meeting.attendance;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.Attendance;
import springsprout.domain.Member;
import springsprout.domain.Study;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 11. 12
 * Time: 오후 6:56:58
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class AttendanceRepositoryImpl extends HibernateGenericDao<Attendance> implements AttendanceRepository{

    public int getConfirmedAttendanceCountOf(Member member) {
		return (Integer)getCurrentSession().createCriteria(Attendance.class)
			.add(Restrictions.eq("member", member))
			.add(Restrictions.eq("isAttended", true))
			.setProjection(Property.forName("id").count()).uniqueResult();
	}

	public int getTotalAttandanceCountOf(Member member) {
		return (Integer)getCurrentSession().createCriteria(Attendance.class)
		.add(Restrictions.eq("member", member))
		.setProjection(Property.forName("id").count()).uniqueResult();
	}

	public int getConrimedAttendanceCountOf(Member member, Study study) {
		return (Integer)getCurrentSession().createCriteria(Attendance.class)
		.createAlias("meeting", "meeting", Criteria.LEFT_JOIN)
		.add(Restrictions.eq("member", member))
		.add(Restrictions.eq("meeting.study", study))
		.add(Restrictions.eq("isAttended", true))
		.setProjection(Property.forName("id").count()).uniqueResult();
	}

	public int getAttendanceCountOf(Member member, Study study) {
		return (Integer)getCurrentSession().createCriteria(Attendance.class)
		.createAlias("meeting", "meeting", Criteria.LEFT_JOIN)
		.add(Restrictions.eq("member", member))
		.add(Restrictions.eq("meeting.study", study))
		.setProjection(Property.forName("id").count()).uniqueResult();
	}
    
}
