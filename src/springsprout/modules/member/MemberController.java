package springsprout.modules.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import springsprout.domain.Member;
import springsprout.modules.member.support.MemberValidator;
import springsprout.service.security.SecurityService;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/member")
@SessionAttributes("member")
public class MemberController {

	@Autowired MemberService service;
	@Autowired MemberValidator validator;
	@Autowired SecurityService securityService;
	@Autowired PasswordEncoder encoder;

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable int id, Model model)
			throws ServletRequestBindingException {
		Member member = service.getMemberById(id);
		model.addAttribute(member);
		return "member/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateForm(Member member, BindingResult result,
			SessionStatus status, HttpSession session) throws ServletRequestBindingException, IOException {
		validator.validateUpdate(member, result);
		if (result.hasErrors()) {
			return "member/update";
		} else {
			service.update(member);
			status.setComplete();
			session.setAttribute("SESSION_FLASH_MSG", "회원정보가 수정되었습니다.");
			return "redirect:/mypage/index";
		}
	}

	@RequestMapping(value = "/out/{id}", method = RequestMethod.GET)
	public String outForm(@PathVariable int id, Model model)
			throws ServletRequestBindingException {
		model.addAttribute(service.getMemberById(id));
		return "member/out";
	}

	@RequestMapping(value = "/out/{id}", method = RequestMethod.POST)
	public String out(@PathVariable int id, Member member, HttpSession session)
			throws ServletRequestBindingException {
		service.out(member);
		session.setAttribute("SESSION_FLASH_MSG", "탈퇴 하셨습니다.");
		return "redirect:/j_spring_security_logout";
	}

	@RequestMapping(value="/passwordEncoder")
	public String encode(@RequestParam("password") String password, HttpSession session) {
		session.setAttribute("SESSION_FLASH_MSG", "[" + encoder.encodePassword(password, null) + "]");
		return "redirect:/";
	}
	
}
