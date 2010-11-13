package springsprout.modules.tag;

import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Restrictions;
import springsprout.domain.Tag;
import springsprout.common.dao.HibernateGenericDao;


/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 4
 * Time: 오전 6:59:44
 */
@Repository
public class TagRepositoryImpl extends HibernateGenericDao<Tag> implements TagRepository {

    public Tag getByTag(String tag) {
        return (Tag) getCurrentSession().createCriteria(Tag.class)
                .add(Restrictions.eq("tag", tag))
                .uniqueResult();
    }
}
