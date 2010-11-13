package springsprout.modules.term;

import springsprout.common.dao.GenericDao;
import springsprout.domain.KorTerm;
import springsprout.modules.term.support.DevTermContext;
import springsprout.modules.term.support.DevTermSearchParam;

import java.util.List;

public interface KorTermRepository extends GenericDao<KorTerm> {

    int getTotalRowsCount(DevTermSearchParam searchParam);

    List<KorTerm> searchTerms(DevTermContext context);

    KorTerm searchByPhraseAndDevTerm(int termId, String korPhrase);
}
