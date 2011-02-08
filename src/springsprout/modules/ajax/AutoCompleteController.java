package springsprout.modules.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springsprout.common.util.JsonUtils;
import springsprout.domain.Member;
import springsprout.modules.ajax.support.AutoCompleteParams;
import springsprout.modules.member.MemberService;

import java.util.List;

import static springsprout.common.SpringSprout2System.JSON_VIEW;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 10. 29
 * Time: 오후 5:35:40
 */

@Controller
public class AutoCompleteController {

    @Autowired MemberService memberService;

    @RequestMapping("/ac/members")
	public ModelAndView members(AutoCompleteParams autoCompleteParam, Model model){
        List<Member> memberList = memberService.getMemberListByAjaxParams(autoCompleteParam);
        JsonUtils.cleanModel(model);
        model.addAttribute("memberList", memberList);
		return new ModelAndView(JSON_VIEW);
	}

}
