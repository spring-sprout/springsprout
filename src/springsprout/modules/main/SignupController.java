package springsprout.modules.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import springsprout.common.util.StringUtils;
import springsprout.domain.Member;
import springsprout.modules.member.MemberService;
import springsprout.modules.member.support.MemberValidator;
import springsprout.service.notification.mail.SendMailService;
import springsprout.service.notification.message.ConfirmMail;

import javax.servlet.http.HttpSession;

import static springsprout.common.SpringSprout2System.JSON_VIEW;

@Controller
public class SignupController {

	@Autowired MemberService memberService;
	@Autowired MemberValidator memberValidator;
	@Autowired SendMailService sendMailService;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		Member member = new Member();
		model.addAttribute(member);
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(String passwordConfirm, Member member,
			BindingResult result, SessionStatus status) {
		validateProperties(member, passwordConfirm, result);
		if (result.hasErrors()) {
			return "signup";
		} else {
			memberService.add(member);
			status.setComplete();
			return "redirect:/signup_joinwait?email=" + member.getEmail();
		}
	}

	@RequestMapping(value = "/signupconfirm", method = RequestMethod.GET)
	public String signupConfirm(@RequestParam("email") String email,
			@RequestParam("authCode") String authCode) {
		memberService.confimMember(email, authCode);
		return "redirect:/login";
	}

	private void validateProperties(Member member, String passwordConfirm,
			BindingResult result) {
		memberValidator.validateSignup(member, result);
		if (!member.getPassword().equals(passwordConfirm))
			result.rejectValue("password", "required",
					"password confirm failed");
	}

	@RequestMapping("/signup_joinwait")
	public void signupJoinwait(ModelMap map, String email) {
		map.addAttribute("email", email);
	}

	@RequestMapping("/confimmail")
	public ModelAndView sendConfirmMail(String email, HttpSession session) {
		Member member = memberService.getMemberByEmail(email);
		sendMailService.sendMessage(new ConfirmMail(member));
		session.setAttribute("SESSION_FLASH_MSG", "인증메일을 재발송하였습니다.");
		return new ModelAndView("signup_joinwait").addObject("email", member.getEmail());
	}

	@RequestMapping("/emailconfirm")
	public ModelAndView String(String email){
		return new ModelAndView(JSON_VIEW).addObject("isDuplicated", memberService.isDuplicated(email));
	}

	@RequestMapping("/checkmail")
	public String checkmail(String email){
		if(StringUtils.isEmpty(email))
			return "redirect:/index";
		String mailHost = email.substring(email.indexOf("@") + 1);
		return "redirect:http://www." + mailHost;
	}

}
