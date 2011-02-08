package springsprout.modules.seminar.support;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import springsprout.domain.Seminar;

@Component
public class SeminarValidator {
	
	public void onSave(Seminar seminar, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title","required", "input title");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "seminarOpenDate", "required","select seminarOpenDate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "seminarCloseDate", "required","select seminarCloseDate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "seminarOpenTime", "required","select seminarOpenTime");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "seminarCloseTime", "required","select seminarCloseTime");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "entryStartDate", "required","select entryStartDate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "entryEndDate", "required","select entryEndDate");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location.name","required", "input location_name");
/*		
 		if (seminar.getMaximum()  == null || seminar.getMaximum() < 0) {
            errors.rejectValue("maximum", "invalid", "input positive integer");
	    }
*/
		
	}
}