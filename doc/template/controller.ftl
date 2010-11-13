package springsprout.modules.${module};

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

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("${domainName}")
public class ${domainClass}Controller {

	@Autowired ${domainClass}Service service;
	@Autowired ${domainClass}Validator validator;

    @RequestMapping(value="/${domainName}/list.do")
	public void list(Model model) throws ServletRequestBindingException {
		model.addAttribute("list",service.getAll());
	}

    @RequestMapping(value="/${domainName}/{id}.do")
	public String view(Model model, @PathVariable int id) {
		model.addAttribute(service.get(id));
		return "${domainName}/view";
	}

	@RequestMapping(value="/${domainName}/add.do", method=RequestMethod.GET)
	public void add(Model model) {
		model.addAttribute(new ${domainClass}());
	}

	@RequestMapping(value="/${domainName}/add.do", method=RequestMethod.POST)
	public String add(${domainClass} ${domainName}, BindingResult result, SessionStatus status) {
		validator.validate(${domainName}, reulst);
        if(result.hasErrors())
			return "${domainName}/add";
		else {
			service.add(${domainName});
			status.setComplete();
			return "redirect:/${domainName}/list.do";
		}
	}

	@RequestMapping(value = "/${domainName}/update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute(service.get(id));
		return "${domainName}/update";
	}

	@RequestMapping(value = "/${domainName}/update/{id}", method = RequestMethod.POST)
	public String updateForm(@Valid ${domainClass} ${domainName}, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "${domainName}/update";
		} else {
			service.update(${domainName});
			status.setComplete();
			return "redirect:/${domainName}/" + ${domainName}.getId() + ".do";
		}
	}

	@RequestMapping("/${domainName}/delete/{id}")
	public String delete(@PathVariable int id) {
		service.delete(id);
		return "redirect:/${domainName}/list.do";
	}

}
