package springsprout.modules.study.meeting.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import springsprout.common.enumeration.PersistentEnumUtil;
import springsprout.common.util.BeanUtils;
import springsprout.domain.Comment;
import springsprout.domain.Meeting;
import springsprout.domain.Presentation;
import springsprout.domain.Resource;
import springsprout.domain.enumeration.ResourceType;
import springsprout.modules.study.StudyService;
import springsprout.modules.study.meeting.MeetingService;
import springsprout.modules.study.meeting.support.CommentDTO;
import springsprout.modules.study.meeting.support.CountInfoDTO;

import javax.validation.Valid;

import static springsprout.common.SpringSprout2System.JSON_VIEW;
import static springsprout.modules.study.support.StudyURLRedirectionUtils.redirectMeetingView;
import static springsprout.modules.study.support.StudyURLRedirectionUtils.redirectPresentationView;


/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 17
 * Time: 오후 11:18:52
 * @author whiteship, june
 */
@Controller
public class PresentationController {

    private static final String PRESENTATION_VIEW = "study/meeting/presentation/view";
    private static final String PRESENTATION_FORM_VIEW = "/study/meeting/presentation/form";

    @Autowired PresentationService service;
    @Autowired StudyService studyService;
    @Autowired MeetingService meetingService;

    @RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/presentation/{presentationId}/comment/add", method = RequestMethod.POST)
    public ModelAndView submitComment(Model model, @Valid Comment comment, @PathVariable int studyId, 
    		@PathVariable int meetingId, @PathVariable int presentationId) {
    	Presentation presentation = service.getById(presentationId);
        model.addAttribute("study", studyService.getStudyById(studyId));
        model.addAttribute("meeting", meetingService.getById(meetingId));
        model.addAttribute("presentation", presentation);
        
        service.addComment(presentation, comment);
        service.notifyCommentAdded(presentation, comment);

        CommentDTO commentDto = new CommentDTO();
        BeanUtils.beanCopy(commentDto ,comment ,true);
        return new ModelAndView(JSON_VIEW).addObject( "commentDto", commentDto).addObject( "title", presentation.getTitle());
    }
    
    @RequestMapping("/study/{studyId}/meeting/{meetingId}/presentation/{presentationId}/insert")
	public ModelAndView insert(@Valid Comment comment) {
		
		return new ModelAndView( "module/validation/simple/setupForm");
	}

    @RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/presentation/{presentationId}/comment/{commentId}/delete")
    public ModelAndView deleteComment(@PathVariable int studyId, @PathVariable int meetingId, @PathVariable int presentationId,  @PathVariable int commentId){
        service.deleteComment(presentationId, commentId);
        return new ModelAndView(JSON_VIEW).addObject( "result", "success");
    }

    @RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/presentation/{presentationId}")
    public String presentataionView( @PathVariable int studyId, @PathVariable int meetingId, @PathVariable int presentationId, Model model) {
        model.addAttribute("presentation", service.viewPresentation(presentationId));
        model.addAttribute("meeting", meetingService.getById(meetingId));
        model.addAttribute("study", studyService.getStudyById(studyId));
        return PRESENTATION_VIEW;
    }

    @RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/presentation/add", method = RequestMethod.GET)
    public String viewAddForm( @PathVariable int studyId, @PathVariable int meetingId, Model model) {
        Meeting meeting = meetingService.getById(meetingId);
        model.addAttribute(meeting);
        model.addAttribute("presentation", meeting.createPresentation());
        model.addAttribute("meetingTitle", meeting.getTitle());
        model.addAttribute("backUrl", "/study/"+studyId+"/meeting/"+meetingId);
        model.addAttribute("action", "추가");
        return PRESENTATION_FORM_VIEW;
    }

    @RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/presentation/add", method = RequestMethod.POST)
    public String presentataionAddSubmit( Model model, @Valid Presentation presentation, BindingResult result, @PathVariable int meetingId, @PathVariable int studyId, SessionStatus status) {
        Meeting meeting = meetingService.getById(meetingId);
        model.addAttribute("meetingTitle", meeting.getTitle());
        model.addAttribute("backUrl", "/study/"+studyId+"/meeting/"+meetingId);
        model.addAttribute("action", "추가");
        if (result.hasErrors()) return PRESENTATION_FORM_VIEW;
        status.setComplete();
        service.addPresentation(meeting, presentation);
        return redirectMeetingView(studyId, meetingId);
    }

    @RequestMapping(value="/study/{studyId}/meeting/{meetingId}/presentation/update/{presentationId}",
            method = RequestMethod.GET)
    public String viewUpdateForm( @PathVariable int studyId, @PathVariable int meetingId, @PathVariable int presentationId, Model model){
    	Presentation presentation = service.getById(presentationId);
        model.addAttribute("presentation", service.getById(presentationId));
        model.addAttribute("meetingTitle", presentation.getMeeting().getTitle());
        model.addAttribute("backUrl", "/study/"+studyId+"/meeting/"+meetingId+"/presentation/"+presentationId);
        model.addAttribute("action", "수정");
        return PRESENTATION_FORM_VIEW;
    }

    @RequestMapping(value="/study/{studyId}/meeting/{meetingId}/presentation/update/{presentationId}",
            method = RequestMethod.POST)
    public String updateSubmit(@PathVariable int studyId, @PathVariable int meetingId,
            @PathVariable int presentationId, @Valid Presentation presentation, BindingResult result, Model model){
        model.addAttribute("meetingTitle", service.getById(presentationId).getMeeting().getTitle());
        model.addAttribute("backUrl", "/study/"+studyId+"/meeting/"+meetingId+"/presentation/"+presentationId);
        model.addAttribute("action", "수정");
        if( result.hasErrors()) return PRESENTATION_FORM_VIEW;
        service.update(presentationId, presentation);
        return redirectPresentationView(studyId, meetingId, presentationId);
    }

    @RequestMapping("/study/{studyId}/meeting/{meetingId}/presentation/delete/{presentationId}")
    public String delete(@PathVariable int studyId, @PathVariable int meetingId, @PathVariable int presentationId){
        service.delete(presentationId);
        return redirectMeetingView(studyId, meetingId);
    }

    @RequestMapping("/study/{studyId}/meeting/{meetingId}/presentation/start/{presentationId}")
    public String start(@PathVariable int studyId, @PathVariable int meetingId, @PathVariable int presentationId){
        service.start(presentationId);
        return redirectPresentationView(studyId, meetingId, presentationId);
    }

    @RequestMapping("/study/{studyId}/meeting/{meetingId}/presentation/end/{presentationId}")
    public String end(@PathVariable int studyId, @PathVariable int meetingId, @PathVariable int presentationId){
        service.end(presentationId);
        return redirectPresentationView(studyId, meetingId, presentationId);
    }
    
    
    @RequestMapping("/study/{studyId}/meeting/{meetingId}/presentation/{presentationId}/viewResources")
    public String viewResources( @PathVariable int presentationId, @PathVariable int meetingId, Model model) {
    	model.addAttribute(new Resource());
    	model.addAttribute("resourceTypes", PersistentEnumUtil.sortedListOf(ResourceType.class));
    	model.addAttribute("meeting", meetingService.getById(meetingId));
    	model.addAttribute("targetObj", service.viewPresentation(presentationId));
    	return "/study/meeting/_resources";
    }
    
    @RequestMapping("/study/{studyId}/meeting/{meetingId}/presentation/{presentationId}/viewComments")
    public String viewComments( @PathVariable int presentationId, Model model) {
    	model.addAttribute("commentObj", service.viewPresentation(presentationId));
    	model.addAttribute(new Comment());
    	return "/study/meeting/_comments";
    }
    
    @RequestMapping("/study/{studyId}/meeting/{meetingId}/presentation/{presentationId}/updateTabDataCounts")
    @ResponseBody
    public CountInfoDTO updateTabDataCounts( @PathVariable int id) {
    	return new CountInfoDTO(service.getById(id));
    }

}
