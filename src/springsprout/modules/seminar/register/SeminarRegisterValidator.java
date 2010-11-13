package springsprout.modules.seminar.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import springsprout.domain.Member;
import springsprout.domain.Seminar;
import springsprout.domain.SeminarComer;
import springsprout.modules.seminar.register.exception.SeminarRegistrationDoesNotExistException;

@Component
public class SeminarRegisterValidator {
	
	@Autowired private SeminarRegisterRepository repository;


	public void register(Seminar seminar,SeminarRegistration registration,Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","required", "이름을 입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","required", "이메일을  입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tel","required", "연락처를  입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "job","required", "직업을  입력해주세요.");
		
		if(registration.isMemberRegistration() == false) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","required", "수정/취소시에 사용할 비밀번호를 입력해주세요.");
			if(repository.hasRegistrationByEmail(seminar,registration.getEmail())) {
				errors.rejectValue("email", "","이미 등록된 이메일입니다.");
			}
		}

	}
	
	public void update(Seminar seminar,SeminarRegistration registration,Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","required", "이름을 입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","required", "이메일을  입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tel","required", "연락처를  입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "job","required", "직업을  입력해주세요.");
		if(registration.getComerId() != 0) {
			throw new SeminarRegistrationDoesNotExistException();
		}
		if(repository.hasRegistrationByEmail(seminar,registration.getEmail(),registration.getConfirmEmail())) {
			errors.rejectValue("email", "","이미 등록된 이메일입니다.");
		}
	}
	

	public void auth(Seminar seminar,SeminarRegistration registration,Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmEmail","required", "이메일을  입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","required", "수정/취소시에 사용할 비밀번호를 입력해주세요.");
	}

	public void validateExistedRegistration(Seminar seminar,
						SeminarRegistration registration,
						SeminarComer comer,Member curMember) 
	{
		if(comer.getSeminar().getId() != seminar.getId()) {
			throw new SeminarRegistrationDoesNotExistException();
		}
		if(curMember.isAnonymous()) {
			if(!comer.getEmail().equals(registration.getConfirmEmail())|| 
					!comer.getPassword().equals(registration.getConfirmPassword())) {
				throw new SeminarRegistrationDoesNotExistException();
			}
		}
		else {
			/*
			if(comer.getMember().getId() != curMember.getId()) {
				throw new SeminarRegistrationDoesNotExistException();
			}
			*/
		}
	}

}
