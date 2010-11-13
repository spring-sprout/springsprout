package springsprout.modules.seminar.schedule.support;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import springsprout.domain.SeminarDetailSchedule;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 28
 * Time: 오후 4:00:21
 */
@Component
public class SeminarScheduleValidator {

	// TODO validation
    public void validate(SeminarDetailSchedule schedule, BindingResult result) {
        ValidationUtils.rejectIfEmptyOrWhitespace(result, "orderCount", "required", "input order");
    }
}
