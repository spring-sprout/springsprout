package springsprout.modules.term;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.Member;
import springsprout.domain.TermVote;

@Repository
public class TermVoteRepositoryImpl extends HibernateGenericDao<TermVote> implements TermVoteRepository {

    public boolean isVoted(Member currentMember, int korTermId) {
        TermVote termVote = find(currentMember, korTermId);
        if(termVote != null)
            return true;
        return false;                                               
    }

    public TermVote find(Member currentMember, int korTermId) {
        return (TermVote) getCurrentSession().createCriteria(TermVote.class)
            .add(Restrictions.eq("member", currentMember))
            .add(Restrictions.eq("korTerm.id", korTermId))
            .setCacheable(true)
            .uniqueResult();
    }
}
