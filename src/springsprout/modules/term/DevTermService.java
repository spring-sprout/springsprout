package springsprout.modules.term;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.util.StringUtils;
import springsprout.domain.*;
import springsprout.modules.comment.CommentRepository;
import springsprout.modules.tag.TagRepository;
import springsprout.modules.term.support.DevTermContext;
import springsprout.modules.term.support.DevTermMessage;
import springsprout.modules.term.support.KorTermMailMessage;
import springsprout.modules.term.support.TermAddDTO;
import springsprout.service.notification.mail.SendMailService;
import springsprout.service.notification.twitter.TwitterService;
import springsprout.service.security.SecurityService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 1
 * Time: 오전 6:29:43
 */
@Service
@Transactional
public class DevTermService {
    
    @Autowired DevTermRepository repository;
    @Autowired KorTermRepository korTermRepository;
    @Autowired TagRepository tagRepository;
    @Autowired SecurityService securityService;
    @Autowired CommentRepository commentRepository;
    @Autowired TermVoteRepository termVoteRepository;

    @Autowired SendMailService sendMailService;
    @Autowired @Qualifier("devTermTwitterService") TwitterService twitterService;

    public List<DevTerm> getAll() {
        return repository.getAll();     
    }

    public void add(DevTerm devTerm) {
        Member currentMember = securityService.getCurrentMember();

        devTerm.setCreated(new Date());
        devTerm.setMember(currentMember);
        repository.add(devTerm);

        currentMember.changeNoti(devTerm.isNotifiable());
        twitterService.sendMessage(new DevTermMessage(devTerm, DevTermMessage.Status.NEW));
    }

    public DevTerm getById(int id) {
        return getById(id, false);
    }

    public DevTerm getById(int id, Boolean isAjax) {
        DevTerm devTerm = repository.getById(id);
        if(isAjax == null || !isAjax)
            devTerm.upViewCount();
        return devTerm;
    }

    public void update(int id, DevTerm newDevTerm) {
        DevTerm oldDevTerm = repository.getById(id);
        oldDevTerm.setPhrase(newDevTerm.getPhrase());
        oldDevTerm.setDetails(newDevTerm.getDetails());
        oldDevTerm.setNotifiable(newDevTerm.isNotifiable());
        oldDevTerm.setTags(newDevTerm.getTags());

        twitterService.sendMessage(new DevTermMessage(oldDevTerm, DevTermMessage.Status.UPDATED));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public void addKorTerm(int termId, KorTerm korTerm) {
        DevTerm devTerm = repository.getById(termId);
        korTerm.setDevTerm(devTerm);
        korTerm.setMember(securityService.getCurrentMember());
        korTerm.setCreated(new Date());
        devTerm.addKorTerm(korTerm);
        korTermRepository.add(korTerm);

        KorTermMailMessage msg = new KorTermMailMessage(devTerm.getMember(), devTerm, korTerm);
        if(devTerm.isNotifiable())
            sendMailService.sendMessage(msg);
        twitterService.sendMessage(msg);
    }

    public void addComment(int termId, Comment comment) {
        comment.setWriter(securityService.getCurrentMember());
        comment.setCreated(new Date());
        commentRepository.add(comment);
        DevTerm term = repository.getById(termId);
        term.addComment(comment);
    }

    public String voteUp(int korTermId) {
        boolean isVoted = termVoteRepository.isVoted(securityService.getCurrentMember(), korTermId);
        if(isVoted){
            TermVote termVote = termVoteRepository.find(securityService.getCurrentMember(), korTermId);
            if(termVote.isUp()){
                termVoteRepository.delete(termVote);
                KorTerm korTerm = korTermRepository.getById(korTermId);
                korTerm.cancleVoteUp();
                return "Up 취소합니다.";
            }
            else {
                termVoteRepository.delete(termVote);
                KorTerm korTerm = korTermRepository.getById(korTermId);
                korTerm.cancleVoteDown();
            }
        }

        KorTerm korTerm = korTermRepository.getById(korTermId);
        korTerm.voteUp();
        TermVote vote = new TermVote(securityService.getCurrentMember(), korTerm, true);
        termVoteRepository.add(vote);
        return "Up 추가합니다!";
    }

    public String voteDown(int korTermId) {
        boolean isVoted = termVoteRepository.isVoted(securityService.getCurrentMember(), korTermId);
        if(isVoted){
            TermVote termVote = termVoteRepository.find(securityService.getCurrentMember(), korTermId);
            if(termVote.isUp()) {
                termVoteRepository.delete(termVote);
                KorTerm korTerm = korTermRepository.getById(korTermId);
                korTerm.cancleVoteUp();
            }
            else {
                termVoteRepository.delete(termVote);
                KorTerm korTerm = korTermRepository.getById(korTermId);
                korTerm.cancleVoteDown();
                return "Down 취소합니다!";
            }
        }

        KorTerm korTerm = korTermRepository.getById(korTermId);
        korTerm.voteDown();
        TermVote vote = new TermVote(securityService.getCurrentMember(), korTerm, false);
        termVoteRepository.add(vote);
        return "Down 추가합니다!";
    }

    public List<DevTerm> searchTerms(DevTermContext context) {
        context.setTotalRowsCount(repository.getTotalRowsCount(context.getSearchParam()));
        return repository.searchByContext(context);
    }

    public void add(TermAddDTO term) {
        DevTerm devTerm = term.getDevTerm();
        KorTerm korTerm = term.getKorTerm();

        add(devTerm);
        if(korTerm != null && !StringUtils.isEmpty(korTerm.getKorPhrase())){
            addKorTerm(devTerm.getId(), korTerm);
        }
    }

    public List<DevTerm> findMyTerms(DevTermContext context) {
        context.getSearchParam().setCreatedBy(securityService.getCurrentMember());
        return searchTerms(context);
    }

    public List<KorTerm> findMyKorTerms(DevTermContext context) {
        context.getSearchParam().setKorTermCreatedBy(securityService.getCurrentMember());
        context.setTotalRowsCount(korTermRepository.getTotalRowsCount(context.getSearchParam()));
        return korTermRepository.searchTerms(context);
    }

    public KorTerm getKorTermById(int korTermId) {
        return korTermRepository.getById(korTermId);
    }

    public void addKorTermComment(int korTermId, Comment comment) {
        comment.setWriter(securityService.getCurrentMember());
        comment.setCreated(new Date());
        commentRepository.add(comment);
        KorTerm term = korTermRepository.getById(korTermId);
        term.addComment(comment);    
    }

    public boolean isTermManagerOrAdmin(int id) {
        Member currentUser = securityService.getCurrentMember();
        if(currentUser.isAnonymous())
            return false;

        boolean isManager = repository.isUserTheTermManager(currentUser, id);
        boolean isAdmin = securityService.isAdmin();
        return isManager || isAdmin;
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    public boolean favorite(int termId) {
        Member currentUser = securityService.getPersistentMember();
        DevTerm devTerm = repository.getById(termId);
        return currentUser.favorite(devTerm);
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    public List<DevTerm> favoriteTerms(DevTermContext context) {
        context.getSearchParam().setFavMember(securityService.getPersistentMember());
        return searchTerms(context);
    }

    public Map<KorTerm, String> korTermVoteMap(int termId) {
        Map<KorTerm, String> korTermVoteMap = new HashMap<KorTerm, String>();
        Member currentMember = securityService.getCurrentMember();
        for(KorTerm korTerm : repository.getById(termId).getKorTerms()){
            TermVote termVote = termVoteRepository.find(currentMember, korTerm.getId());
            if(termVote != null){
                korTermVoteMap.put(korTerm, termVote.isUp() ? "up" : "down");
            }
        }
        return korTermVoteMap;
    }

    @PreAuthorize("(#korTerm.member.email == principal.Username) or hasRole('ROLE_ADMIN')")
    public void deleteKorTerm(KorTerm korTerm, int termId) {
        repository.getById(termId).delete(korTerm);
        korTermRepository.delete(korTerm);
    }
}
