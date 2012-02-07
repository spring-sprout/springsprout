package springsprout.modules.term;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import springsprout.common.util.ModelAndViewUtils;
import springsprout.domain.Comment;
import springsprout.domain.DevTerm;
import springsprout.domain.KorTerm;
import springsprout.modules.term.support.DevTermContext;
import springsprout.modules.term.support.DevTermValidator;
import springsprout.modules.term.support.TagsPropertyEditor;
import springsprout.modules.term.support.TermAddDTO;
import springsprout.service.security.SecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 1
 * Time: 오전 6:32:26
 */
@Controller
@RequestMapping("/term/")
public class DevTermController {

    private static final String TERM_FORM = "term/add";

    @Autowired DevTermService service;
    @Autowired DevTermValidator validator;
    @Autowired ApplicationContext applicationContext;
    @Autowired SecurityService securityService;
    @Autowired DevTermContext context;

    @RequestMapping("index")
    public ModelMap index(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
        context.bindParams(request);
        ModelMap model = new ModelMap();
        model.addAttribute("terms", service.searchTerms(context));
        model.addAttribute("context", context);
        model.addAttribute(context.getTabName(), "active");
        if(securityService.hasLoggedInUser())
            model.addAttribute("member", securityService.getPersistentMember());
        return model;
    }

    @RequestMapping("recent")
    public String recent(){
        return "redirect:index?o_field=created&o_direction=desc";
    }

    @RequestMapping("lonely")
    public String lonely(){
        return "redirect:index?o_field=korTermCount&o_direction=asc&s_howru=lonely";
    }

    @RequestMapping("hot")
    public String hot(){
        return "redirect:index?o_field=korTermCount&o_direction=desc&s_howru=hot";
    }

    @RequestMapping("abc")
    public String abc(){
        return "redirect:index?o_field=phrase&o_direction=asc";
    }

    @RequestMapping("zyx")
    public String zyx(){
        return "redirect:index?o_field=phrase&o_direction=desc";
    }

    @RequestMapping("my")
    public ModelAndView my(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
        context.bindParams(request);
        ModelAndView mav = new ModelAndView("term/myindex");
        mav.addObject("terms", service.findMyTerms(context));
        mav.addObject("context", context);
        mav.addObject("term_my", "active");
        mav.addObject("term_url", "my");
        if(securityService.hasLoggedInUser())
            mav.addObject("member", securityService.getPersistentMember());
        return mav;
    }

    @RequestMapping("mykor")
    public ModelAndView mykor(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
        context.bindParams(request);
        ModelAndView mav = new ModelAndView("term/mykorindex");
        mav.addObject("korTerms", service.findMyKorTerms(context));
        mav.addObject("context", context);
        mav.addObject("term_mine", "active");
        if(securityService.hasLoggedInUser())
            mav.addObject("member", securityService.getPersistentMember());
        return mav;
    }

    @RequestMapping("favorite")
    public ModelAndView favorite(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
        context.bindParams(request);
        ModelAndView mav = new ModelAndView("term/myindex");
        mav.addObject("terms", service.favoriteTerms(context));
        mav.addObject("context", context);
        mav.addObject("term_fav", "active");
        mav.addObject("term_url", "favorite");
        if(securityService.hasLoggedInUser())
            mav.addObject("member", securityService.getPersistentMember());
        return mav;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addForm(Model model){
        model.addAttribute("term", new TermAddDTO());
        return TERM_FORM;
    }

    @RequestMapping(value = "addsubmt", method = RequestMethod.POST)
    public String addSubmit(@ModelAttribute("term") TermAddDTO term, BindingResult result,
			SessionStatus status, HttpSession session){
        validator.validate(term, result);
		if (result.hasErrors())
			return TERM_FORM;
		service.add(term);
		status.setComplete();
		session.setAttribute("SESSION_FLASH_MSG", term.getDevTerm().getPhrase() + " 추가했습니다.");
		return "redirect:index";
    }

    @RequestMapping("{id}")
    public String view(@RequestHeader(required = false, value = "AJAX") Boolean isAjax, @RequestParam(required = false) boolean nocount, @PathVariable int id, Model model){
        if(nocount == true)
            isAjax = true;
        else
            isAjax = false;
        model.addAttribute("term", service.getById(id, isAjax));
        model.addAttribute("isManagerOrAdmin", service.isTermManagerOrAdmin(id));
        model.addAttribute("hasLoggedInUser", securityService.hasLoggedInUser());
        model.addAttribute("korTermVoteMap", service.korTermVoteMap(id));
        return "term/view";
    }

    @RequestMapping(value="update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable int id, Model model){
        boolean nocount = true;
        model.addAttribute("term", service.getById(id, nocount));
        return "term/update";
    }

    @RequestMapping(value="update/{id}", method = RequestMethod.POST)
    public String updateSubmit(@PathVariable int id, @ModelAttribute("term") DevTerm devTerm, BindingResult result,
               SessionStatus status, HttpSession session){
        validator.validate(id, devTerm, result);
        if(result.hasErrors())
            return "term/update";
        service.update(id, devTerm);
        status.setComplete();
        session.setAttribute("SESSION_FLASH_MSG", devTerm.getPhrase() + " updated");
        return "redirect:/term/" + id + "?nocount=true";
    }

    @RequestMapping("{termId}/fav")
    public ModelAndView fav(@PathVariable int termId){
        ModelAndView mav = ModelAndViewUtils.jsonWithSuccessResult();
        mav.addObject("favadded", service.favorite(termId));
        return mav;
    }

    @RequestMapping(value = "{termId}/kor/add", method = RequestMethod.GET)
    public String addKorTermForm(@PathVariable int termId, Model model){
        model.addAttribute("termId", termId);
        model.addAttribute("korTerm", new KorTerm());
        return "korterm/add";
    }

    @RequestMapping(value = "{termId}/kor/addsubmit", method = RequestMethod.POST)
    public ModelAndView addKorTermSubmit(@PathVariable int termId, KorTerm korTerm, BindingResult result,
			SessionStatus status, HttpSession session){
        validator.validate(korTerm, termId, result);
		if (result.hasErrors())
			return ModelAndViewUtils.jsonWithError(result);
		service.addKorTerm(termId, korTerm);
		status.setComplete();
		session.setAttribute("SESSION_FLASH_MSG", korTerm.getKorPhrase() + " added");
		return ModelAndViewUtils.jsonWithSuccessResult();
    }

    @RequestMapping(value = "{termId}/kor/{korTermId}/voteup", method = RequestMethod.GET)
    public ModelAndView voteUpKorTerm(@PathVariable int termId, @PathVariable int korTermId){
        String result = service.voteUp(korTermId);
        return ModelAndViewUtils.jsonWithSuccessResult()
                .addObject("msg", result)
                .addObject("vote", service.getKorTermById(korTermId).getVote());
    }

    @RequestMapping(value = "{termId}/kor/{korTermId}/votedown", method = RequestMethod.GET)
    public ModelAndView voteDownKorTerm(@PathVariable int termId, @PathVariable int korTermId){
        String result = service.voteDown(korTermId);
        return ModelAndViewUtils.jsonWithSuccessResult()
                .addObject("msg", result)
                .addObject("vote", service.getKorTermById(korTermId).getVote());
    }

    @RequestMapping(value = "{termId}/comment/add", method = RequestMethod.GET)
    public String addCommentForm(@PathVariable int termId, Model model){
        model.addAttribute("termId", termId);
        model.addAttribute("comment", new Comment());
        return "/term/comment/add";
    }

    @RequestMapping(value = "{termId}/comment/addsubmit", method = RequestMethod.POST)
    public ModelAndView addCommentSubmit(@PathVariable int termId, Comment comment, BindingResult result,
			SessionStatus status, HttpSession session){
        validator.validate(comment, result);
		if (result.hasErrors())
			return ModelAndViewUtils.jsonWithError(result);
		service.addComment(termId, comment);
		status.setComplete();
		session.setAttribute("SESSION_FLASH_MSG", "Comment added");
        return ModelAndViewUtils.jsonWithSuccessResult();
    }

    @RequestMapping(value = "{termId}/kor/{korTermId}/comment/add", method = RequestMethod.GET)
    public String addKorCommentForm(@PathVariable int termId, @PathVariable int korTermId, Model model){
        model.addAttribute("termId", termId);
        model.addAttribute("korTermId", korTermId);
        model.addAttribute("comment", new Comment());
        return "/term/korterm/comment/add";
    }

    @RequestMapping(value = "{termId}/kor/{korTermId}/comment/addsubmit", method = RequestMethod.POST)
    public ModelAndView addKorCommentSubmit(@PathVariable int termId, @PathVariable int korTermId, Comment comment, BindingResult result,
			SessionStatus status, HttpSession session){
        validator.validate(comment, result);
		if (result.hasErrors())
			return ModelAndViewUtils.jsonWithError(result);
		service.addKorTermComment(korTermId, comment);
		status.setComplete();
		session.setAttribute("SESSION_FLASH_MSG", "Comment added");
        return ModelAndViewUtils.jsonWithSuccessResult();
    }

    @RequestMapping(value = "{termId}/kor/{korTermId}/delete")
    public String deleteKorTerm(@PathVariable int termId, @PathVariable int korTermId){
        service.deleteKorTerm(service.getKorTermById(korTermId), termId);
        return "redirect:/term/" + termId + "?nocount=true";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "devTerm.tags", new TagsPropertyEditor(applicationContext));    
        binder.registerCustomEditor(List.class, "tags", new TagsPropertyEditor(applicationContext));
    }

}
