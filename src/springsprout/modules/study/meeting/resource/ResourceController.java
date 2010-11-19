package springsprout.modules.study.meeting.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import springsprout.common.enumeration.PersistentEnumUtil;
import springsprout.domain.Meeting;
import springsprout.domain.Presentation;
import springsprout.domain.Resource;
import springsprout.domain.UploadFile;
import springsprout.domain.enumeration.ResourceType;
import springsprout.modules.file.FileService;
import springsprout.modules.study.StudyService;
import springsprout.modules.study.meeting.MeetingService;
import springsprout.modules.study.meeting.presentation.PresentationService;

import javax.servlet.http.HttpSession;
import java.io.IOException;

import static springsprout.common.SpringSprout2System.JSON_VIEW;

@Controller
public class ResourceController {

	Logger log = LoggerFactory.getLogger(ResourceController.class);

	@Autowired StudyService studyService;
	@Autowired @Qualifier("fileService") FileService fileService;
    @Autowired MeetingService meetingService;
    @Autowired PresentationService presentationService;

	@RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/resource/add", method = RequestMethod.GET)
	public String addResource(Model model, @PathVariable int studyId, @PathVariable int meetingId) {
		model.addAttribute("resource", new Resource());
		model.addAttribute("resourceTypes", PersistentEnumUtil.sortedListOf(ResourceType.class));
		model.addAttribute("studyId", studyId);
		model.addAttribute("meetingId", meetingId);
		return "study/meeting/resource/add";
	}

	@RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/resource/url/add", method = RequestMethod.POST)
	public String addUrlResource(@ModelAttribute("resource")Resource resource, BindingResult result, SessionStatus status, 
			@PathVariable int studyId, @PathVariable int meetingId)
			throws IOException {
		status.setComplete();
		Meeting meeting = meetingService.getById(meetingId);
		meetingService.addUrlResource(meeting, resource);
		return "study/meeting/resource/complete";
	}

	@RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/resource/file/add", method = RequestMethod.POST)
	public String addFileResource(@ModelAttribute("resource")Resource resource, BindingResult result, SessionStatus status, 
			@RequestParam("uploadingFile")MultipartFile multipartFile,
			@PathVariable int studyId, @PathVariable int meetingId)
			throws IOException {
		status.setComplete();
		Meeting meeting = meetingService.getById(meetingId);
		UploadFile uploadFile = fileService.upload(multipartFile);
		meetingService.addFileResource(meeting, resource, uploadFile);
		return "study/meeting/resource/complete";
	}

	@RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/resource/{resourceId}/delete")
	public String deleteResource(@PathVariable int studyId, @PathVariable int meetingId, @PathVariable int resourceId, HttpSession session) {
		meetingService.deleteResourceFromMeeting(meetingId, resourceId);
		return redirectMeetingView(studyId, meetingId);
	}

	private void sendSessionMsg(HttpSession session, String msg) {
		session.setAttribute("SESSION_FLASH_MSG", msg);
	}

	private String redirectMeetingView(int studyId, int meetingId) {
		return "redirect:/study/" + studyId + "/meeting/" + meetingId + "";
	}

    @RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/presentation/{presentationId}/resource/add", method = RequestMethod.GET)
	public String addResourceToPresentation(Model model, @PathVariable int studyId, @PathVariable int meetingId, @PathVariable int presentationId) {
		model.addAttribute("resource", new Resource());
		model.addAttribute("resourceTypes", PersistentEnumUtil.sortedListOf(ResourceType.class));
		model.addAttribute("studyId", studyId);
		model.addAttribute("meetingId", meetingId);
		model.addAttribute("presentationId", presentationId);
		return "study/meeting/presentation/resource/add";
	}

    @RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/presentation/{presentationId}/resource/url/add", method = RequestMethod.POST)
	public ModelAndView addUrlResourceToPresentation(@ModelAttribute("resource")Resource resource, BindingResult result, SessionStatus status,
			@PathVariable int studyId, @PathVariable int meetingId, @PathVariable int presentationId, HttpSession session) {
		status.setComplete();
		sendSessionMsg(session, "URL을 추가했습니다.");
		Presentation presentation = presentationService.getById(presentationId);
		presentationService.addUrlResource(presentation, resource);
		return new ModelAndView(JSON_VIEW).addObject( "result", "success");
	}

	@RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/presentation/{presentationId}/resource/file/add", method = RequestMethod.POST)
	public String addFileResourceToPresentation(@ModelAttribute("resource")Resource resource, BindingResult result, SessionStatus status,
			@RequestParam("uploadingFile")MultipartFile multipartFile,
			@PathVariable int studyId, @PathVariable int meetingId, @PathVariable int presentationId, HttpSession session)
			throws IOException {
		status.setComplete();
		sendSessionMsg(session, "파일을 추가했습니다.");
		Presentation presentation = presentationService.getById(presentationId);
		UploadFile uploadFile = fileService.upload(multipartFile);
		presentationService.addFileResource(presentation, resource, uploadFile);
		return "study/meeting/presentation/resource/complete";
	}

	@RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/presentation/{presentationId}/resource/{resourceId}/delete")
	public ModelAndView deleteResourceFromPresentation(@PathVariable int studyId, @PathVariable int meetingId,
        @PathVariable int presentationId, @PathVariable int resourceId) {
		presentationService.deleteResourceFromPresentation(presentationId, resourceId);
		return new ModelAndView(JSON_VIEW).addObject( "result", "success");
	}

    private String redirectPresentationView(int studyId, int meetingId, int presentationId) {
        return "redirect:/study/" + studyId + "/meeting/" + meetingId + "/presentation/" + presentationId + "";
    }
}
