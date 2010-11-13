package springsprout.modules.study.meeting.resource;

import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 11. 12
 * Time: 오후 6:34:29
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ResourceRepositoryImpl extends HibernateGenericDao<Resource> implements ResourceRepository{
}
