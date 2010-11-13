package springsprout.modules.link;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import springsprout.domain.Link;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 7. 8
 * Time: 오전 10:21:12
 */
@Controller
@RequestMapping("/link")
@SessionAttributes("link")
public class LinkController {

    @Autowired LinkService service;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("list", service.list());
        return "link/index";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("link", new Link());
        return "link/add";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addFormSubmit(@Valid Link link, BindingResult result, SessionStatus status, Model model) {
        if(result.hasErrors())
            return "link/add";
        status.setComplete();
        service.add(link);
        return "redirect:/link";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(@PathVariable int id, Model model){
        model.addAttribute("link", service.get(id));
        return "link/view";
    }

    @RequestMapping(value = "/{id}/form", method = RequestMethod.GET)
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("link", service.get(id));
        return "link/edit";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateFormSubmit(@PathVariable int id, @Valid Link link, BindingResult result, SessionStatus status){
        if(result.hasErrors())
            return "link/edit";
        status.setComplete();
        service.update(link);
        return "redirect:/link/" + id;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id){
        service.delete(id);
        return "redirect:/link";
    }

}
