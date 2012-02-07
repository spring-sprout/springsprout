package springsprout.modules.role.support;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import springsprout.domain.Right;
import springsprout.domain.Role;

import java.util.Set;

@Component
public class RoleValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public void validate(Object target, Errors errors) {
		Role role = (Role)target;
		Set<Right> rights = role.getRights();
//		for(Right r : rights)
//			System.out.println(r.getId());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "required");
	}

}
