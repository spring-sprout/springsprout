package springsprout.modules.wiki;

import springsprout.common.dao.GenericDao;
import springsprout.domain.WikiSpace;

public interface WikiSpaceRepository extends GenericDao<WikiSpace> {

    WikiSpace findByKey(String key);
}
