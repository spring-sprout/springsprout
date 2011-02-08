package springsprout.modules.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springsprout.common.SpringSprout2System;
import springsprout.common.util.BeanUtils;
import springsprout.domain.Notice;
import springsprout.modules.main.support.NoticeDTO;
import springsprout.modules.notice.NoticeService;
import springsprout.modules.notice.support.NoticeContainer;
import springsprout.modules.notice.support.NoticeCriteria;
import springsprout.service.security.SecurityService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static springsprout.common.SpringSprout2System.JSON_VIEW;

@Controller
@RequestMapping("/admin/notice/**")
public class NoticeMgtController {

	@Autowired NoticeService noticeService;
	@Autowired SecurityService securityService;

    @RequestMapping
    public String list(@ModelAttribute NoticeCriteria cri, Model model) throws ServletRequestBindingException {
    	NoticeContainer container = noticeService.getNoticeByPaging(cri);
        
        List<Notice> noticeList = container.getList();
        List<NoticeDTO> noticeDTOs = new ArrayList<NoticeDTO>();
        
        for(Notice s : noticeList) {
        	NoticeDTO noticeDTO = new NoticeDTO();
            BeanUtils.beanCopy(noticeDTO, s);
            
            noticeDTOs.add(noticeDTO);
        }

        model.addAttribute("notices", noticeDTOs)
             .addAttribute("total", container.getTotal());
        
        return SpringSprout2System.JSON_VIEW;
	}
    
    @RequestMapping
    public ModelAndView update(
    		@RequestParam int id,
            @RequestParam String title,
            @RequestParam String contents) {
    	Notice notice = noticeService.get(id);
    	notice.setTitle(title);
    	notice.setContents(contents);
    	notice.setModified(new Date());
    	notice.setModifier(securityService.getCurrentMember());
    	noticeService.update(notice);
    	
    	return new ModelAndView(JSON_VIEW)
        	.addObject("success", true);
	}
    
    @RequestMapping
    public ModelAndView add(
    		@RequestParam String title,
    		@RequestParam String contents,
    		String notification) {
    	Notice notice = new Notice();
    	notice.setTitle(title);
    	notice.setContents(contents);
    	notice.setCreated(new Date());
    	notice.setMember(securityService.getCurrentMember());
    	noticeService.add(notice, notification);
    	
    	return new ModelAndView(JSON_VIEW)
    	.addObject("success", true);
    }
    
    @RequestMapping
    public ModelAndView del(@RequestParam int notices) {
    	noticeService.delete(notices);
    	
    	return new ModelAndView(JSON_VIEW)
    	.addObject("success", true);
    }
    
}
