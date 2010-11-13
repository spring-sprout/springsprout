package springsprout.modules.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import springsprout.common.conversion.JsonStringToBean;
import springsprout.common.propertyeditor.RightsEditor;
import springsprout.domain.Role;
import springsprout.modules.role.support.RoleValidator;

import java.util.Set;

import static springsprout.common.SpringSprout2System.JSON_VIEW;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {

	@Autowired private RoleService roleService;
	@Autowired private RoleValidator validator;
	@Autowired private RightsEditor re;
	
    @RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView list(){
        return new ModelAndView(JSON_VIEW)
    		.addObject("roles", roleService.getAll());
	}

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public ModelAndView update(String roles){
        roleService.update(JsonStringToBean.convert(roles,Role.class));
        return new ModelAndView(JSON_VIEW)
                .addObject("success",true)
                .addObject("roles",roleService.getAll());
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public ModelAndView add(String roles){
        roleService.add(JsonStringToBean.convert(roles, Role.class));
        return new ModelAndView(JSON_VIEW)
                .addObject("success",true)
                .addObject("roles",roleService.getAll());
    }

//    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Set.class, "rights", re);
    }

}
