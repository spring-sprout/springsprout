package springsprout.modules.study.meeting.presentation;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.Presentation;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 2
 * Time: 오후 8:47:26
 */
@Repository
public class PresentationRepositoryImpl extends HibernateGenericDao<Presentation> implements PresentationRepository{
 
	@SuppressWarnings("unchecked")
	public List<Presentation> getPresentationListSortByActivity( int meetingId) {
    	return getCurrentSession().createCriteria(Presentation.class).add( Restrictions.eq("meeting.id", meetingId))
    		.addOrder( Order.asc( "status")).list();
    }
}
