package springsprout.modules.study.post;

import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.study.board.Post;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 10. 14
 * Time: 오후 2:48:44
 */
@Repository
public class PostRespositoryImpl extends HibernateGenericDao<Post> implements PostResposiroty {
}
