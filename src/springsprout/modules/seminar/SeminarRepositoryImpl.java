package springsprout.modules.seminar;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.Seminar;
import springsprout.domain.SeminarComer;
import springsprout.domain.enumeration.SeminarStatus;

import java.util.List;

@Repository
public class SeminarRepositoryImpl extends HibernateGenericDao<Seminar> implements SeminarRepository{

    /**
     * 대기자 목록
     * @param seminar 
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<SeminarComer> findWaitComersBy(Seminar seminar) {
		Criteria c = this.getCurrentSession().createCriteria(SeminarComer.class);
		c.add(Expression.eq("seminar", seminar));
		c.addOrder(Order.asc("id"));
		c.setFirstResult(seminar.getMaximum());
		return c.list();
	}

    /**
     * 참석 가능한 신청자 목록
     * @param seminar
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<SeminarComer> findAttendAvailableComersBy(Seminar seminar) {
		Criteria c = this.getCurrentSession().createCriteria(SeminarComer.class);
		c.add(Expression.eq("seminar", seminar));
		c.setMaxResults(seminar.getMaximum());
		c.addOrder(Order.asc("id"));
		return c.list();
	}


    /**
     * 진행중인 세미나
     * @return 진행중인 세미나 list
     */
	@SuppressWarnings("unchecked")
	public List<Seminar> findActiveSeminar() {
		Criteria c = this.getCurrentSession().createCriteria(Seminar.class);
		c.add(Expression.ne("status", SeminarStatus.SEMINAR_ENDED));
		c.addOrder(Order.desc("seminarOpenDate"));
		return c.list();
	}

    /**
     * 지난 세미나
     * @return 지난 세미나 list
     */
	@SuppressWarnings("unchecked")
	public List<Seminar> findPastSeminar() {
		Criteria c = this.getCurrentSession().createCriteria(Seminar.class);
		c.add(Expression.eq("status", SeminarStatus.SEMINAR_ENDED));
		c.addOrder(Order.desc("seminarOpenDate"));
		return c.list();
	}

    public Seminar getTheLatestSeminar() {
        return (Seminar) getCurrentSession().createCriteria(Seminar.class)
            .setMaxResults(1)
            .addOrder(Order.desc("created"))
            .uniqueResult();
    }


}
