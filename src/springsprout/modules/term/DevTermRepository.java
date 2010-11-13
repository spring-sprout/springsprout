package springsprout.modules.term;

import springsprout.common.dao.GenericDao;
import springsprout.domain.DevTerm;
import springsprout.domain.Member;
import springsprout.modules.term.support.DevTermContext;
import springsprout.modules.term.support.DevTermSearchParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 1
 * Time: 오전 6:28:31
 */
public interface DevTermRepository extends GenericDao<DevTerm>{
    List<DevTerm> searchByContext(DevTermContext devTermContext);

    int getTotalRowsCount(DevTermSearchParam searchParam);

    DevTerm searchByPhrase(String phrase);

    boolean isUserTheTermManager(Member currentUser, int id);

}
