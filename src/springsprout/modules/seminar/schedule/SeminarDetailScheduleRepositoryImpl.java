package springsprout.modules.seminar.schedule;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.SeminarDetailSchedule;

import java.util.List;

@Repository
public class SeminarDetailScheduleRepositoryImpl extends HibernateGenericDao<SeminarDetailSchedule> implements SeminarDetailScheduleRepository {

    @SuppressWarnings({"unchecked"})
    public List<SeminarDetailSchedule> scheduleListOfStudy(int sid) {
        return getCurrentSession().createCriteria(SeminarDetailSchedule.class)
                .add(Restrictions.eq("seminar.id", sid))
                .addOrder(Order.asc("order"))
                .list();
    }
}
