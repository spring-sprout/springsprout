package springsprout.modules.comment;

import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.Comment;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 11. 12
 * Time: 오후 7:20:59
 */
@Repository
public class CommentRepositoryImpl extends HibernateGenericDao<Comment> implements CommentRepository{
}
