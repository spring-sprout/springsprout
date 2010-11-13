package springsprout.modules.seminar.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springsprout.domain.Member;
import springsprout.domain.Seminar;
import springsprout.domain.SeminarComer;
import springsprout.domain.enumeration.SeminarComerStatus;
import springsprout.modules.seminar.register.exception.SeminarAlreadyRegistrationException;
import springsprout.modules.seminar.register.exception.SeminarRegistrationAuthException;
import springsprout.modules.seminar.register.exception.SeminarRegistrationDoesNotExistException;
import springsprout.service.security.SecurityService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

@Service
@Transactional
public class SeminarRegisterService {
	@Autowired private SecurityService securityService;
	@Autowired private SeminarRegisterRepository repository;
	@Autowired private SeminarRegisterValidator validator;
	
	public SeminarRegistration newRegistration(Seminar seminar) {
		SeminarRegistration registration = new SeminarRegistration();
		Member curMember = this.securityService.getCurrentMember();

		if(!curMember.isAnonymous()) {
			if(this.repository.hasRegistrationByMember(seminar,curMember)) {
				throw new SeminarAlreadyRegistrationException();
			}
			registration.setName(curMember.getName());
			registration.setEmail(curMember.getEmail());
			registration.setMemberRegistration(true);
		}
		else {
			registration.setMemberRegistration(false);
		}
		
		return registration;
	}
	
	public SeminarRegistration getRegistration(Seminar seminar) {
		Member curMember = this.securityService.getCurrentMember();
		SeminarRegistration registration = new SeminarRegistration();
		if(curMember.isAnonymous()) {
			registration.setMemberRegistration(false);
			throw new SeminarRegistrationAuthException(registration);
		}
		else {
			if(!this.repository.hasRegistrationByMember(seminar, curMember)) {
				throw new SeminarRegistrationDoesNotExistException();
			}
		}
		registration.setMemberRegistration(true);
		SeminarComer comer = this.repository.getSeminarComerByMember(seminar, curMember);
		registration.setComerId(comer.getId());
		registration.setName(comer.getName());
		registration.setEmail(comer.getEmail());
		registration.setJob(comer.getJob());
		registration.setNote(comer.getNote());
		registration.setTel(comer.getTel());
		
		return registration;
	}
	
	public void register(SeminarRegistration registration,Seminar seminar,Errors result) {
			Member curMember = this.securityService.getCurrentMember();
			if(!curMember.isAnonymous()) {
				if(this.repository.hasRegistrationByMember(seminar, curMember)) {
					throw new SeminarAlreadyRegistrationException();
				}
			}
			SeminarComer comer = new SeminarComer();
			comer.setSeminar(seminar);
			comer.setName(registration.getName());
			comer.setEmail(registration.getEmail());
			comer.setJob(registration.getJob());
			comer.setNote(registration.getNote());
			comer.setPassword(registration.getPassword());
			comer.setTel(registration.getTel());
			comer.setNote(registration.getNote());
			comer.setStatus(SeminarComerStatus.ENROLLED);
			if(!curMember.isAnonymous()) {
				comer.setMember(curMember);
			}
			this.repository.save(comer);
			this.repository.flush();
	}
	
	public boolean modifyRegistration(Seminar seminar,
			SeminarRegistration registration, BindingResult result) {
		Member curMember = this.securityService.getCurrentMember();
		if(curMember.isAnonymous()) {
			this.validator.auth(seminar,registration,result);
			if(result.hasErrors()) {
				throw new SeminarRegistrationAuthException(registration);
			}
			
			if(registration.getComerId() != 0) {
				SeminarComer comer = this.repository.getSeminarComerByEmailAndPassword(
							seminar, 
							registration.getConfirmEmail(), 
							registration.getConfirmPassword());
				if(comer == null || comer.getId() != 0) {
					result.rejectValue("confirmEmail","", "이메일과 비밀번호에 해당하는 참가 신청 정보가 없습니다.");
					throw new SeminarRegistrationAuthException(registration);
				}
					
				registration.setComerId(comer.getId());
				registration.setName(comer.getName());
				registration.setEmail(comer.getEmail());
				registration.setPassword(comer.getPassword());
				registration.setJob(comer.getJob());
				registration.setNote(comer.getNote());
				registration.setTel(comer.getTel());
				return false;
			}
		}
		this.validator.update(seminar, registration, result);
		if(result.hasErrors()) return false;
		
		SeminarComer comer = this.repository.getSeminarComerById(registration.getComerId());
		this.validator.validateExistedRegistration(seminar, registration, comer, curMember);
		
		comer.setName(registration.getName());
		comer.setEmail(registration.getEmail());
		comer.setPassword(registration.getPassword());
		comer.setTel(registration.getTel());
		comer.setNote(registration.getNote());
		this.repository.update(comer);	
		return true;
	}
	
	public boolean cancelRegistration(Seminar seminar,
			SeminarRegistration registration, BindingResult result) {
		
		Member curMember = this.securityService.getCurrentMember();
		if(curMember.isAnonymous()) {
			this.validator.auth(seminar,registration,result);
			if(result.hasErrors()) {
				throw new SeminarRegistrationAuthException(registration);
			}
			
			if(registration.getComerId() != 0) {
				SeminarComer comer = this.repository.getSeminarComerByEmailAndPassword(
							seminar, 
							registration.getConfirmEmail(), 
							registration.getConfirmPassword());
				if(comer == null || comer.getId() != 0) {
					result.rejectValue("confirmEmail","", "이메일과 비밀번호에 해당하는 참가 신청 정보가 없습니다.");
					throw new SeminarRegistrationAuthException(registration);
				}
					
				registration.setComerId(comer.getId());
				return false;
			}
		}
		
		SeminarComer comer = this.repository.getSeminarComerById(registration.getComerId());
		this.validator.validateExistedRegistration(seminar, registration, comer, curMember);
		this.repository.delete(comer);
		return true;
	}

}
