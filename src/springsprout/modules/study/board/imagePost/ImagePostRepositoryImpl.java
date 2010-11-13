package springsprout.modules.study.board.imagePost;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.study.board.ImagePost;
import springsprout.domain.study.board.TextPost;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 10. 14
 * Time: 오후 2:50:05
 */
@Repository
public class ImagePostRepositoryImpl extends HibernateGenericDao<ImagePost> implements ImagePostRepository {

	public List<ImagePost> getRootPostList( int start, int limit) {
		return getCriteria().addOrder(Order.desc("createdAt"))
			.setFirstResult(start).setMaxResults(limit).list();
	}
}
