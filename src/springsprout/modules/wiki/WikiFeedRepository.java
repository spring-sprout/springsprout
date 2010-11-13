package springsprout.modules.wiki;

import springsprout.common.dao.GenericDao;
import springsprout.domain.WikiFeed;

public interface WikiFeedRepository extends GenericDao<WikiFeed> {

    WikiFeed findByLink(String link);

    void deleteAll();
    
}
