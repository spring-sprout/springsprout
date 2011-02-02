package springsprout.modules.member.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import springsprout.domain.Member;
import springsprout.modules.member.MemberService;

@Component
public class MemberUpdateValidator {

	@Autowired
	MemberService memberService;

	public void validate(Member member, Errors errors) {
		if (!StringUtils.hasLength(member.getName()))
			errors.rejectValue("name", "required", "name required!");
		if (!StringUtils.hasLength(member.getPassword()))
			errors.rejectValue("password", "required", "password required!");
		if (!isSameEmail(member)) {
			errors.rejectValue("name", "required", "email cann't change!");
		}
	}

	private boolean isSameEmail(Member member) {
		return member.getEmail().equals(
				memberService.getMemberEmailById(member.getId()));
	}

}
