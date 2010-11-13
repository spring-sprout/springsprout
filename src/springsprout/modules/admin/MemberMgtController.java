package springsprout.modules.admin;

import static springsprout.common.SpringSprout2System.JSON_VIEW;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springsprout.common.web.support.ExtJsGridPaging;
import springsprout.common.web.support.OrderParam;
import springsprout.domain.Member;
import springsprout.domain.enumeration.MemberStatus;
import springsprout.modules.member.MemberService;
import springsprout.modules.member.exception.MemberNotFoundException;
import springsprout.modules.member.support.MemberSearchParam;

@Controller
@RequestMapping("/admin/member/*")
public class MemberMgtController {

    @Autowired MemberService memberService;

    @RequestMapping
    public ModelAndView list(MemberSearchParam searchParam, OrderParam orderParam, ExtJsGridPaging paging) {
        return new ModelAndView(JSON_VIEW)
            .addObject("totalCount", memberService.getTotalMemberCount(searchParam))
    		.addObject("memberList", memberService.getTransformerMemberToMapList(searchParam, orderParam, paging));
	}

    @RequestMapping
    public ModelAndView update(@ModelAttribute Member updatedMember,
                                @RequestParam(required = false, defaultValue = "false") boolean statusEdit) {
        ModelAndView mav = new ModelAndView(JSON_VIEW);
        try {
            memberService.updateByAdmin(updatedMember, statusEdit);
            return mav.addObject("success", true);
        } catch (MemberNotFoundException e){
            return mav.addObject("success", false);
        }
	}

    @InitBinder
    public void initBinding(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("joined", "outDate");
    }
    
}
