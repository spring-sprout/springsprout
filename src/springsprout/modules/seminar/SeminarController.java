package springsprout.modules.seminar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springsprout.common.propertyeditor.FormatDatePropertyEditor;
import springsprout.domain.Seminar;
import springsprout.modules.seminar.support.SeminarValidator;
import springsprout.modules.seminar.support.SeminarViewNames;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seminar")
public class SeminarController {
    
	@Autowired private SeminarService seminarService;
	@Autowired private SeminarValidator seminarSaveValidator;

    /**
     * 진행중인 세미나 목록
     * @param model
     * @return
     */
	@RequestMapping("/index")
	public String index(Model model) {
		List<Seminar> list = this.seminarService.findByActiveSeminar();
		this.activeTabUI("active", model);
		model.addAttribute("list", list);
		return SeminarViewNames.SEMINAR_INDEX;
	}

    /**
     * 지난 세미나 목록
     * @param model
     * @return
     */
	@RequestMapping("/past")
	public String past(Model model) {
		List<Seminar> list = this.seminarService.findByPastSeminar();
		this.activeTabUI("past", model);
		model.addAttribute("list", list);
		return SeminarViewNames.SEMINAR_INDEX;
	}

    /**
     * 세미나 뷰
     * @param id
     * @param model
     * @return
     */
	@RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
	public String view(@PathVariable int id, Model model) {
		Seminar seminar = seminarService.getById(id);
		model.addAttribute(seminar);
		return SeminarViewNames.SEMINAR_VIEW;
	}
	
	/**
	 * 세미나 등록 페이지
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addForm(Model model) {
		Seminar seminar = new Seminar();
		model.addAttribute(seminar);
		return SeminarViewNames.SEMINAR_FORM;
	}

    /**
     * 세미나 추가 폼처리
     * @param seminar
     * @param result
     * @return
     */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addSubmitForm(Seminar seminar, BindingResult result) {
		seminarSaveValidator.onSave(seminar, result);
		if (result.hasErrors()) return SeminarViewNames.SEMINAR_FORM;
		seminarService.add(seminar);
		return "redirect:/seminar" + seminar.getId() + "/view";
	}

	/**
	 * 세미나 수정 페이지
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String updateForm(@PathVariable int id,Model model) {
		Seminar seminar = seminarService.getById(id);
		model.addAttribute(seminar);
		return SeminarViewNames.SEMINAR_FORM;
	}

    /**
     * 세미나 수정 폼처리
     * @param seminar
     * @param result
     * @return
     */
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String seminarUpdate(Seminar seminar, BindingResult result) {
		seminarSaveValidator.onSave(seminar, result);
		if (result.hasErrors()) return SeminarViewNames.SEMINAR_FORM;
		seminarService.update(seminar);
		return "redirect:/seminar/" + seminar.getId() + "/view";
	}

	/**
	 * 세미나 삭제 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String seminarRemove(@PathVariable int id) {
		seminarService.deleteById(id);
		return SeminarViewNames.REDIRECT_SEMINAR_INDEX;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, "seminarOpenTime",  new FormatDatePropertyEditor("HH:mm"));
        binder.registerCustomEditor(Date.class, "seminarCloseTime", new FormatDatePropertyEditor("HH:mm"));
    }

	private void activeTabUI(String tabItemName, Model model) {
		model.addAttribute("minitab_" + tabItemName, "active");
	}

}

