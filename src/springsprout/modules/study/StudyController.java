package springsprout.modules.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import springsprout.domain.Study;
import springsprout.domain.study.board.TextPost;
import springsprout.modules.study.exception.StudyMaximumOverException;
import springsprout.modules.study.post.PostService;
import springsprout.service.security.SecurityService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static springsprout.common.SpringSprout2System.JSON_VIEW;
import static springsprout.modules.study.support.StudyURLRedirectionUtils.redirectStudyView;


@Controller
@RequestMapping("/study")
@SessionAttributes("study")
public class StudyController {
	Logger log = LoggerFactory.getLogger(StudyController.class);

	private static final String STUDY_FORM = "study/form";
	private static final String STUDY_UPDATE_FORM = "study/update";
	private static final String REDIRECT_STUDY_INDEX = "redirect:/study/";
	
	@Resource StudyService advancedStudyService;
	@Resource PostService<TextPost> textPostService;

    @Autowired SecurityService securityService;
    @Autowired StudyStatisticsService statisticsService;
    @Autowired StudyIndexService indexService;


	@RequestMapping
	public String newIndex(@RequestParam(required = false) String type, Model model) {
        model.addAttribute( "recentMeeting", indexService.getRecentMeeting());
        model.addAttribute( "activeStudies", indexService.getRecentStudies());
        model.addAttribute( "studyIndexInfo", indexService.makeStudyIndexInfo());
		return "study/index3";
    }

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String addForm(Model model) {
        model.addAttribute(new Study());
        model.addAttribute("title", "스터디 추가");
        model.addAttribute("backUrl", "/study/");
        model.addAttribute("isUpdate", false);
        return STUDY_FORM;
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String addForm( @Valid Study study, BindingResult result, Model model, HttpSession session, SessionStatus status) {
		model.addAttribute("title", "스더티 추가");
		if (result.hasErrors()) return STUDY_FORM;
		advancedStudyService.addStudy(study);
        status.setComplete();
		setSession(session, study.getStudyName(), " 스터디가 개설되었습니다.");
		return REDIRECT_STUDY_INDEX;
	}

    @RequestMapping("/{id}")
	public String studyView(@PathVariable int id, Model model) {
        model.addAttribute(advancedStudyService.getStudyById(id));
        return "study/view";
    }

    @RequestMapping("/{id}/summary")
	public String studySummary(@PathVariable int id, Model model) {
        model.addAttribute(advancedStudyService.getStudyById(id));
        model.addAttribute("isAlreadyJoinMember", advancedStudyService.isCurrentUserAlreadyJoinedIn(id));
        model.addAttribute("isManagerOrAdmin", advancedStudyService.isCurrentUserTheStudyManagerOrAdmin(id));
        model.addAttribute("postList", textPostService.getList(0, 5, id));
        return "study/view/summary";
    }

    @RequestMapping("/{id}/comments")
    public String studyComments(@PathVariable int id, Model model) {
        Study study = advancedStudyService.getStudyById(id);
    	model.addAttribute(study);
    	return "/study/view/comments";
    }

    @RequestMapping("/{id}/meetings")
    public String studyMeetings(@PathVariable int id, Model model) {
        Study study = advancedStudyService.getStudyById(id);
    	model.addAttribute(study);
        model.addAttribute("meetingWeekStatistics", statisticsService.getMeetingDayStatisticsOf(study.getMeetings()));
        model.addAttribute("meetingMemberStatistics", statisticsService.getMeetingMemberStatisticsOf(study.getMeetings()));
        return "/study/view/meetings";
    }

    @RequestMapping("/{id}/members")
    public String studyMembers(@PathVariable int id, Model model) {
        Study study = advancedStudyService.getStudyById(id);
    	model.addAttribute(study);
        model.addAttribute("isAlreadyJoinMember", advancedStudyService.isCurrentUserAlreadyJoinedIn(id));
        model.addAttribute("memberMeetingStatistics", statisticsService.getMemberMeetingStatisticsOf(study.getMeetings()));
        model.addAttribute("studyMemberStatistics", statisticsService.getStudyMemberStatisticesOf(study));
        return "/study/view/members";
    }

    @RequestMapping("/{id}/calendar")
    public String studyCalendar(@PathVariable int id, Model model) {
        Study study = advancedStudyService.getStudyById(id);
    	model.addAttribute(study);
        model.addAttribute("isManagerOrAdmin", advancedStudyService.isCurrentUserTheStudyManagerOrAdmin(id));
        return "/study/view/calendar";
    }

	@RequestMapping(value = "/{id}/form", method = RequestMethod.GET)
	public String updateForm(@PathVariable int id, Model model) {
		Study study = advancedStudyService.getStudyById(id);
		model.addAttribute(study);
		model.addAttribute("title", "스터디 수정");
        model.addAttribute("backUrl", "/study/" + study.getId());
        model.addAttribute("isUpdate", true);
        return STUDY_UPDATE_FORM;
	}

	@RequestMapping(value = "/{id}/form", method = RequestMethod.PUT)
	public String updateForm(boolean isGoingToBeNotified, @Valid Study study, BindingResult result, HttpSession session, SessionStatus status)
			throws ServletRequestBindingException {
		if (result.hasErrors()) return STUDY_UPDATE_FORM;
		advancedStudyService.updateStudy(study, isGoingToBeNotified);
		status.setComplete();
        setSession(session, study.getStudyName(), " 스터디가 수정되었습니다.");
		return redirectStudyView(study.getId());
	}

	@RequestMapping("/{id}/end")
	public String endStudy(HttpSession session, @PathVariable int id) {
		Study study = advancedStudyService.getStudyById(id);
		advancedStudyService.endStudy(study);
		setSession(session, study.getStudyName(), "스터디가 종료되었습니다.");
		return REDIRECT_STUDY_INDEX;
	}

	@RequestMapping("/{id}/start")
	public String startStudy(HttpSession session, @PathVariable int id) {
		Study study = advancedStudyService.getStudyById(id);
		advancedStudyService.startStudy(study);
		setSession(session, study.getStudyName(), "스터디가 시작되었습니다.");
		return redirectStudyView(id);
	}

	@RequestMapping("/{id}/join")
	public String addCurrentMember(HttpSession session, @PathVariable int id) {
		Study study = advancedStudyService.getStudyById(id);
		try {
            advancedStudyService.addCurrentMember(study);
            setSession(session, study.getStudyName(), " 스터디에 가입 하셨습니다.");
        } catch (StudyMaximumOverException e){
        	setSession(session, study.getStudyName(), " 스터디 제한 인원을 확인하세요.");
            log.debug("Check study's maximum member count");
        }

		return redirectStudyView(id);
	}

    @RequestMapping("/{id}/notify")
	public ModelAndView notify(@PathVariable int id, Model model, HttpSession session) {
    	advancedStudyService.notify(id);
        return new ModelAndView(JSON_VIEW).addObject("studyName", advancedStudyService.getStudyById(id).getStudyName());
	}

	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable int id, HttpSession session) {
		Study study = advancedStudyService.getStudyById(id);
		advancedStudyService.deleteStudy(study);
		setSession(session, study.getStudyName(), " 스터디가 폐쇄되었습니다.");
		return REDIRECT_STUDY_INDEX;
	}

	@RequestMapping("/{id}/out")
	public String removeCurrentMember(HttpSession session, @PathVariable int id) {
		Study study = advancedStudyService.getStudyById(id);
		advancedStudyService.removeCurrentMember(study);
		setSession(session, study.getStudyName(), "스터디에서 탈퇴 하셨습니다.");
		return redirectStudyView(id);
	}
	
	@RequestMapping("/find")
	public String find(@RequestParam(required=false) String keyword, Model model) {
		model.addAttribute("list", indexService.find(keyword));
		model.addAttribute("keyword", keyword);
		return "study/find";
	}
	
	@RequestMapping("/index/{type}")
	public String pastStudy(@PathVariable String type, Model model) {
		model.addAllAttributes( indexService.swichStudyView(type));
		return "study/typeView";
	}
	
	private void setSession(HttpSession session, String studyName, String msg) {
		session.setAttribute("SESSION_FLASH_MSG", studyName + msg);
	}
}
