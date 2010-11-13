package springsprout.modules.tag;

import springsprout.common.dao.GenericDao;
import springsprout.domain.Tag;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 4
 * Time: 오전 6:59:14
 */
public interface TagRepository extends GenericDao<Tag>{
    Tag getByTag(String tag);
}
