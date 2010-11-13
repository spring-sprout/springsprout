package springsprout.modules.study.meeting.support;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import springsprout.domain.Meeting;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 11. 12
 * Time: 오후 7:57:02
 * To change this template use File | Settings | File Templates.
 */
@Component
public class MeetingValidator {

    public void validate(Meeting meeting, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required", "input title");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contents", "required", "input content");
		ValidationUtils.rejectIfEmpty(errors, "openDate", "required", "select meeting openDate");
		ValidationUtils.rejectIfEmpty(errors, "openTime", "required", "select meeting openTime");
		ValidationUtils.rejectIfEmpty(errors, "closeDate", "required", "select meeting closeDate");
		ValidationUtils.rejectIfEmpty(errors, "closeTime", "required", "select meeting closeTime");
	}

}
