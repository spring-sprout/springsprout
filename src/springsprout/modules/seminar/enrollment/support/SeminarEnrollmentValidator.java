package springsprout.modules.seminar.enrollment.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import springsprout.domain.SeminarComer;
import springsprout.modules.seminar.enrollment.SeminarEnrollmentService;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 27
 * Time: 오후 9:23:16
 */
@Component
public class SeminarEnrollmentValidator {

    @Autowired SeminarEnrollmentService service;

    public void validate(SeminarComer seminarComer, BindingResult result) {
        ValidationUtils.rejectIfEmptyOrWhitespace(result, seminarComer.getEmail(), "required", "이메일 주소를 입력해 주세요.");
        ValidationUtils.rejectIfEmptyOrWhitespace(result, seminarComer.getName(), "required", "성함을 입력해 주세요.");
        if(service.isDuplicatedEmail(seminarComer.getEmail()))
            result.rejectValue("email", "duplicated", "이미 등록되어 있는 이메일 주소입니다.");
    }

    public void validateUpdate(SeminarComer existingSeminarComer, SeminarComer seminarComer, BindingResult result) {
        ValidationUtils.rejectIfEmptyOrWhitespace(result, seminarComer.getName(), "required", "성함을 입력해 주세요.");
        if(!existingSeminarComer.getEmail().equals(seminarComer.getEmail()))
            result.rejectValue("email", "can't change", "이메일 주소는 변경할 수 없습니다.");
        if(existingSeminarComer.getId() != seminarComer.getId())
            result.rejectValue("id", "can't change", "id는 변경할 수 없습니다.");
    }
}
