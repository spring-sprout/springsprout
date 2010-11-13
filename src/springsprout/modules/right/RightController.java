package springsprout.modules.right;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import springsprout.domain.Right;
import springsprout.modules.right.RightController;
import springsprout.modules.right.RightService;

@Controller
@RequestMapping("/right")
@SessionAttributes("right")
public class RightController {
	
	Logger log = LoggerFactory.getLogger(RightController.class);

	@Autowired private RightService service;

	@RequestMapping("/list")
	public void list(ModelMap modelMap) throws ServletRequestBindingException {
		modelMap.addAttribute(service.getRightList());
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addForm(Model model) {
		Right right = new Right();
		model.addAttribute(right);
		return "right/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addForm(Right right, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "right/add";
		} else {
			log.debug("id is {}", right.getId());
			log.debug("right name is {}", right.getName());

			service.add(right);
			status.setComplete();
			return "redirect:/right/list";
		}
	}

	@RequestMapping("/right/{id}")
	public String view(@PathVariable int id, Model model) {
		model.addAttribute(service.getRightById(id));
		return "right/view";
	}

	@RequestMapping("/right/delete/{id}")
	public String delete(@PathVariable int id) {
		service.delete(id);
		return "redirect:/right/list";
	}

	@RequestMapping(value = "/right/update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute(service.getRightById(id));
		return "right/update";
	}

	@RequestMapping(value = "/right/update/{id}", method = RequestMethod.POST)
	public String updateForm(Right right, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "right/update";
		} else {
			service.update(right);
			status.setComplete();
			return "redirect:/right/" + right.getId() + "";
		}
	}

}
