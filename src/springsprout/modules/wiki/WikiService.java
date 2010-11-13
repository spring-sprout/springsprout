package springsprout.modules.wiki;

import springsprout.domain.WikiFeed;
import springsprout.domain.WikiSpace;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 3
 * Time: 오후 10:17:01
 */
public interface WikiService {

    List<WikiSpace> spaceList();

    List<WikiFeed> wikiFeedList();

    void refresh();
}
