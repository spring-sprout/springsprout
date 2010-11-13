package springsprout.modules.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springsprout.domain.Member;
import springsprout.domain.Role;
import springsprout.modules.member.MemberService;
import springsprout.modules.role.RoleService;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 10. 1
 * Time: 오후 8:30:06
 *
 * 임시 기능을 구현할 때 사용하기..
 */

@Controller
@RequestMapping("/temp")
public class TempController {

    @Autowired MemberService memberService;
    @Autowired RoleService roleService;

    @RequestMapping("/role/form")
    public String rolesForm(Model model){
        model.addAttribute("roles", roleService.getAll());
        return "temp/form";
    }

    @RequestMapping("/role/{role}/to/{member}")
    public String addRoleToMember(@PathVariable Role role, @PathVariable Member member){
        memberService.addRoleTo(role, member);
        return "redirect:/temp/about/" + member.getId();
    }

    @RequestMapping("/about/{memberId}")
    public String rolesOf(@PathVariable int memberId, Model model) {
        model.addAttribute("member", memberService.getMemberById(memberId));
        return "temp/about";
    }
    

}
