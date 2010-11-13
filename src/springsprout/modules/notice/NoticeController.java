package springsprout.modules.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import springsprout.domain.Notice;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/notice")
@SessionAttributes("notice")
public class NoticeController {
	
	@Autowired NoticeService noticeService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public void add(Model model) {
		model.addAttribute(new Notice());
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@Valid Notice notice, BindingResult result, SessionStatus status) {
		if(result.hasErrors())
			return "notice/add";
		else {
			noticeService.add(notice);
			status.setComplete();
			return "redirect:/notice/list";
		}
	}
	
	@RequestMapping("/list")
	public ModelMap list() throws ServletRequestBindingException {
		List<Notice> list = noticeService.getAll();
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("list",list);
		return modelMap;
	}
	
	@RequestMapping("/{id}")
	public String view(Model model, @PathVariable int id) {
		model.addAttribute(noticeService.get(id));
		return "notice/view";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute(noticeService.get(id));
		return "notice/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateForm(@Valid Notice notice, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "notice/update";
		} else {
			noticeService.update(notice);
			status.setComplete();
			return "redirect:/notice/" + notice.getId() + "";
		}
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		noticeService.delete(id);
		return "redirect:/notice/list";
	}
	
}
