package springsprout.modules.member.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import springsprout.domain.Member;
import springsprout.modules.member.MemberService;

@Component
public class MemberValidator {
	
	@Autowired MemberService memberService;
	@Autowired PasswordEncoder passwordEncoder;

	public void validateSignup(Member member, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "required");
		if(memberService.isDuplicated(member.getEmail())){
			errors.rejectValue("email", "duplicated", "이미 등록되어 있는 이메일입니다.");
		}
	}
	
	public void validateUpdate(Member member, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "required", "현재 비밀번호를 입력하세요.");
		if(errors.hasErrors())
			return;
		if(!member.getPassword().equals(passwordEncoder.encodePassword(member.getCurrentPassword(), null))){
			errors.rejectValue("currentPassword", "required", "비밀번호가 틀렸습니다.");
			return;
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "이름을 입력하세요.");
		if(StringUtils.hasText(member.getNewPassword())) {
			if(!member.getNewPassword().equals(member.getNewPasswordConfirm()))
				errors.rejectValue("newPassword", "required", "새 비밀 번호가 정확하지 않습니다.");
		}
	}
}
