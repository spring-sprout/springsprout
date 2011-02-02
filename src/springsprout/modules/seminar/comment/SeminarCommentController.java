package springsprout.modules.seminar.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springsprout.domain.Comment;
import springsprout.modules.seminar.SeminarService;
import springsprout.modules.seminar.support.SeminarViewNames;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 26
 * Time: 오전 7:22:16
 */
@Controller
public class SeminarCommentController {

    @Autowired SeminarService seminarService;

	/**
	 * 댓글 목록
	 */
	@RequestMapping(value = "/seminar/{sid}/comments", method = RequestMethod.GET)
	public String commentFindByList(@PathVariable int sid, Model model) {
		model.addAttribute("comments", seminarService.getCommentListOfSeminar(sid));
        return SeminarViewNames.SEMINAR_INDEX;
	}


	/**
	 * 덧글 등록
     * - 봄싹 회원만 댓글 쓰기 가능.
	 */
	@RequestMapping(value = "/seminar/{sid}/comment/add", method = RequestMethod.GET)
	public String commentAdd(@PathVariable int sid, Model model) {
        model.addAttribute(new Comment());
		return SeminarViewNames.SEMINAR_COMMENT_FORM;
	}
    @RequestMapping(value = "/seminar/{sid}/comment/add", method = RequestMethod.POST)
	public String commentAdd(@PathVariable int sid, @ModelAttribute Comment comment, BindingResult result, Model model) {
        //validation
        seminarService.addComment(sid, comment);
		return SeminarViewNames.REDIRECT_SEMINAR_VIEW;
	}
}
