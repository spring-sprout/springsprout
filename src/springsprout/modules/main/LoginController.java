package springsprout.modules.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springsprout.common.SpringSprout2System;
import springsprout.common.util.StringUtils;
import springsprout.domain.Member;
import springsprout.modules.member.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired MemberService memberService;

    @RequestMapping("/login")
    public void login(){
    }
    
    @RequestMapping("/loginpopup")
    public void loginpopup(@RequestHeader("Referer") String from, Model model){
        model.addAttribute("_to", from);
    }

	@RequestMapping("/loginSuccessProcess")
    public ModelAndView loginSuccessProcess(HttpServletRequest req,HttpServletResponse res){
    	if(isAjaxLogin(req)){
    		clearAJAXHeader(res);
    		ModelMap model = new ModelMap();
    		model.addAttribute("loginResult", "success");
    		model.addAttribute("isAdmin", isAdmin());
    		return new ModelAndView(SpringSprout2System.JSON_VIEW).addObject("loginResult", "success").addObject("isAdmin", isAdmin());
    	}
    	return new ModelAndView("redirect:/mypage/index");
    }

	@RequestMapping("/loginFailProcess")
    public ModelAndView loginFailProcess(HttpServletRequest req, HttpServletResponse res,HttpSession session){
    	Object obj = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION_KEY");
    	if(obj != null){
    		if("User is disabled".equals(((BadCredentialsException)obj).getMessage())){
    			return new ModelAndView("redirect:/signup_joinwait?email="+session.getAttribute("SPRING_SECURITY_LAST_USERNAME"));
    		}
    	}

    	if(isAjaxLogin(req)){
    		clearAJAXHeader(res);
    		return new ModelAndView(SpringSprout2System.JSON_VIEW).addObject("loginResult", "fail");
    	}

    	return new ModelAndView("redirect:/login?loginFail=true");
    }

    private boolean isAjaxLogin(HttpServletRequest req) {
    	return (req.getHeader("AJAX") != null && req.getHeader("AJAX").equals("true"));
    }

    @RequestMapping("/forgetpassword")
    public String forgetPassword(){
    	return "forget_password";
    }

    @RequestMapping(value="/forgetpassword", method=RequestMethod.POST)
    public String forgetPassword(@RequestParam("email")String email, @RequestParam("name")String name,
    		HttpSession session){
    	Member member = memberService.getMemberByEmail(email);
    	if(StringUtils.isEmpty(email) || member == null){
    		session.setAttribute("SESSION_FLASH_MSG", "등록되어 있지 않은 메일 주소입니다.");
    		return "forget_password";
    	}
    	if(!name.equals(member.getName())){
    		session.setAttribute("SESSION_FLASH_MSG", "입력하신 정보가 맞지 않습니다.");
    		return "forget_password";
    	}
    	memberService.makeNewPassword(member);
		session.setAttribute("SESSION_FLASH_MSG", "이메일을 발송하였습니다.");
    	return "redirect:/login";
    }

	private String isAdmin() {
		for(GrantedAuthority g : SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
			if(g.toString().equals("ROLE_ADMIN")) return "true";
		}
		return "false";
	}

	private void clearAJAXHeader(HttpServletResponse res) {
		res.setHeader("AJAX", "");
	}
}
