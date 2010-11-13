package springsprout.modules.term;

import springsprout.common.dao.GenericDao;
import springsprout.domain.Member;
import springsprout.domain.TermVote;

public interface TermVoteRepository extends GenericDao<TermVote> {

    boolean isVoted(Member currentMember, int korTermId);

    TermVote find(Member currentMember, int korTermId);
}
