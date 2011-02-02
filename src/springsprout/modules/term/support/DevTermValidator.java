package springsprout.modules.term.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import springsprout.common.util.StringUtils;
import springsprout.domain.Comment;
import springsprout.domain.DevTerm;
import springsprout.domain.KorTerm;
import springsprout.modules.term.DevTermRepository;
import springsprout.modules.term.KorTermRepository;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 2
 * Time: 오전 9:26:11
 */
@Component
public class DevTermValidator {

    @Autowired DevTermRepository repository;
    @Autowired KorTermRepository korTermRepository;

    public void validate(int termId, DevTerm devTerm, BindingResult result) {
        ValidationUtils.rejectIfEmptyOrWhitespace(result, "phrase", "required", "용어를 입력해 주세요.");
        ValidationUtils.rejectIfEmptyOrWhitespace(result, "details", "required", "설명을 입력해 주세요.");

        if(result.hasErrors()) {
            devTerm.setTags(null);
            return;
        }

        String phrase = devTerm.getPhrase();
        DevTerm existingDevTerm = repository.getById(termId);

        if(!existingDevTerm.getPhrase().equals(phrase)){
            DevTerm oldDevTerm = repository.searchByPhrase(phrase);
            if(oldDevTerm != null)
                result.rejectValue("phrase", "duplicated", "이미 등록된 용어입니다.");
        }

        repository.clear();
    }

    public void validate(KorTerm korTerm, int termId, BindingResult result) {
        ValidationUtils.rejectIfEmptyOrWhitespace(result, "korPhrase", "required", "용어를 입력해 주세요.");
        ValidationUtils.rejectIfEmptyOrWhitespace(result, "details", "required", "설명을 입력해 주세요.");
        if(result.hasErrors())
            return;

        KorTerm oldKorTerm = korTermRepository.searchByPhraseAndDevTerm(termId, korTerm.getKorPhrase());
        if(oldKorTerm != null)
            result.rejectValue("korPhrase", "duplicated", "이미 등록된 용어입니다.");
    }

    public void validate(Comment comment, BindingResult result) {
        ValidationUtils.rejectIfEmptyOrWhitespace(result, "comment", "required", "댓글을 입력해 주세요.");
    }

    public void validate(TermAddDTO term, BindingResult result) {
        ValidationUtils.rejectIfEmptyOrWhitespace(result, "devTerm.phrase", "required", "용어를 입력해 주세요.");
        ValidationUtils.rejectIfEmptyOrWhitespace(result, "devTerm.details", "required", "설명을 입력해 주세요.");

        if(result.hasErrors()) {
            term.getDevTerm().setTags(null);
            return;
        }

        DevTerm devTerm = term.getDevTerm();
        String phrase = devTerm.getPhrase();
        DevTerm oldDevTerm = repository.searchByPhrase(phrase);
        if(oldDevTerm != null)
            result.rejectValue("devTerm.phrase", "duplicated", "이미 등록된 용어입니다.");

        KorTerm korTerm = term.getKorTerm();
        if(!StringUtils.isEmpty(korTerm.getKorPhrase()))
            ValidationUtils.rejectIfEmptyOrWhitespace(result, "korTerm.details", "required", "설명을 입력해 주세요.");

    }
}
