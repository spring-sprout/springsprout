package springsprout.modules.seminar.enrollment;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.SeminarComer;

import java.util.List;

@Repository
public class SeminarComerRepositoryImpl extends HibernateGenericDao<SeminarComer> implements SeminarComerRepository {

    @SuppressWarnings({"unchecked"})
    public List<SeminarComer> listOfSeminar(int sid) {
        return getCurrentSession().createCriteria(SeminarComer.class)
                .add(Restrictions.eq("seminar.id", sid))
                .addOrder(Order.asc("status"))
                .addOrder(Order.asc("created"))
                .list();
    }

    public SeminarComer getByEmail(String email) {
        return (SeminarComer) getCurrentSession().createCriteria(SeminarComer.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }


}
