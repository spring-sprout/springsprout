package springsprout.modules.main;

import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springsprout.modules.notice.NoticeService;
import springsprout.modules.study.StudyService;
import springsprout.modules.study.meeting.MeetingService;
import springsprout.service.security.SecurityService;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static springsprout.common.SpringSprout2System.JSON_VIEW;

@Controller
public class MainController {
	
	@Autowired GraffitiService graffitiService;
    @Autowired NoticeService noticeService;
    @Autowired StudyService studyService;
    @Autowired MeetingService meetingService;
    @Autowired SecurityService securityService;

	private FastDateFormat graffitiLoadDateTimeFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone("GMT+09:00"), Locale.KOREA);

    @RequestMapping("/index/new")
    public String newIndex(Model model) {
        model.addAttribute("studyList", studyService.findActiveStudies(4));
        model.addAttribute("currentUser", securityService.getCurrentMember());
		model.addAttribute("graffitiList", graffitiService.getGraffitiList());
        return "newIndex";
    }
    
    @RequestMapping("/index")
    public String index(SitePreference sitePreference, Device device, Model model){
        if(device.isMobile() && (sitePreference == SitePreference.MOBILE)) {
            return "redirect:/m";
        }
        model.addAttribute("studyList", studyService.findActiveStudies(4));
        model.addAttribute("meetingList", meetingService.findActiveMeetings(2));
        model.addAttribute("currentUser", securityService.getCurrentMember());
        return "index";
    }

    @RequestMapping("/developers")
    public void developers(){
    }

    @RequestMapping("/admin/index")
    public void admin(){
	}
    
    @RequestMapping("/main/graffitiList")
    public ModelAndView graffitiList(@RequestParam(required=false, defaultValue="") String loadDateTime){
        return new ModelAndView(JSON_VIEW)
    		.addObject("loadDateTime", graffitiLoadDateTimeFormat.format(Calendar.getInstance(TimeZone.getTimeZone("GMT+09:00"), Locale.KOREA)))
    		.addObject("graffitiList", graffitiService.getGraffitiList(loadDateTime));
    }
    
    @RequestMapping("/main/graffitiWrite")
    public ModelAndView graffitiWrite(@RequestParam(defaultValue="") String contents, @RequestParam int lastGraffitiID){
    	boolean writeResult = false;
    	if(StringUtils.hasText(contents)){
    		writeResult = graffitiService.write(contents);
    	}
    	return new ModelAndView(JSON_VIEW).addObject("writeResult", writeResult).addObject( "graffitiList", graffitiService.getRecentGraffitiList( lastGraffitiID));
    }
    
    @RequestMapping("/main/_topbarbtn")
    public void _topbarbtn(@RequestParam(defaultValue="N")String ajaxlogin_yn, ModelMap map){
        map.addAttribute("ajaxlogin_yn",ajaxlogin_yn);
    }

    @RequestMapping("/main/noticeList")
    public ModelAndView noticeList(){
        return new ModelAndView(JSON_VIEW).addObject("noticeList",noticeService.getNoticeDTOAll());
    }

    @RequestMapping("/main/meetingList")
    public ModelAndView meetingList(@RequestParam("studyId") int studyId){
        return new ModelAndView(JSON_VIEW)
                .addObject("meetingList", meetingService.getMeetingListByStudyId(studyId, 8));
    }

    @RequestMapping("/main/noticeView/{id}")
    public String noticeView(@PathVariable int id, Model model){
        model.addAttribute("notice",noticeService.get(id));
        return "/main/noticeView";
    }

}
