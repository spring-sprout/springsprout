package springsprout.modules.seminar.register;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import springsprout.domain.Seminar;
import springsprout.modules.seminar.SeminarService;
import springsprout.modules.seminar.register.exception.SeminarAlreadyRegistrationException;
import springsprout.modules.seminar.register.exception.SeminarRegistrationAuthException;
import springsprout.modules.seminar.register.exception.SeminarRegistrationDoesNotExistException;

import javax.servlet.http.HttpSession;

@Deprecated
public class SeminarRegisterController {
    private SeminarService seminarService;
    private SeminarRegisterService service;
    private SeminarRegisterValidator validator;

    private String viewName(String name) {
        return "/seminar/register/" + name;
    }

    private String redirectUrl(Seminar seminar, String name) {
        return "redirect:/seminar/" + seminar.getId() + "/" + name;
    }

    private String redirectUrl(long seminarId, String name) {
        return "redirect:/seminar/" + seminarId + "/" + name;
    }

    private String message(String message, Model model) {
        model.addAttribute("message", message);
        return this.viewName("message");
    }

    private Seminar loadSeminar(int id, Model model) {
        Seminar seminar = this.seminarService.getById(id);
        model.addAttribute(seminar);
        return seminar;
    }

    private void setFlashMessage(String message, HttpSession session) {
        session.setAttribute("SESSION_FLASH_MSG", message);
    }

    private void actvieTabUI(String name, Model model) {
        model.addAttribute("minitab_" + name, "active");
    }

    @RequestMapping(value = "/seminar/{id}/register", method = RequestMethod.GET)
    public String registerForm(@PathVariable int id, Model model) {
        this.actvieTabUI("register", model);
        Seminar seminar = this.loadSeminar(id, model);
        if (!seminar.isActive()) {
            return this.message("지난 세미나는 신청하실수 없습니다.", model);
        }

        SeminarRegistration registration = null;
        try {
            registration = this.service.newRegistration(seminar);
        }
        catch (SeminarAlreadyRegistrationException e) {
            return this.message("회원님의 계정으로 이미 신청되어 있습니다", model);
        }

        model.addAttribute("registration", registration);
        return this.viewName("register");
    }

    @RequestMapping(value = "/seminar/{id}/register", method = RequestMethod.POST)
    public String register(@PathVariable int id,
                           @ModelAttribute("registration") SeminarRegistration registration,
                           BindingResult result, SessionStatus status,
                           Model model,
                           HttpSession session) {

        this.actvieTabUI("register", model);
        Seminar seminar = this.loadSeminar(id, model);
        if (!seminar.isActive()) {
            return this.message("지난 세미나는 신청하실수 없습니다.", model);
        }

        this.validator.register(seminar, registration, result);
        if (result.hasErrors()) {
            model.addAttribute("registerForm", registration);
            return this.viewName("register");
        }
        try {
            this.service.register(registration, seminar, result);
        }
        catch (SeminarAlreadyRegistrationException e) {
            return this.message("회원님의 계정으로 이미 신청되어 있습니다", model);
        }

        status.setComplete();
        this.setFlashMessage("세미나 신청이 정상적으로 이루어졌습니다.", session);
        return this.redirectUrl(seminar, "view");
    }

    @RequestMapping(value = "/seminar/{id}/register/update", method = RequestMethod.GET)
    public String updateForm(@PathVariable int id, Model model) {
        this.actvieTabUI("register_update", model);

        Seminar seminar = this.loadSeminar(id, model);
        if (!seminar.isActive()) {
            return this.message("지난 세미나는 신청하실수 없습니다.", model);
        }

        try {
            SeminarRegistration registration = this.service.getRegistration(seminar);
            model.addAttribute("registration", registration);
        }
        catch (SeminarRegistrationDoesNotExistException e) {
            return this.message("신청된 정보가 없습니다.", model);
        }
        catch (SeminarRegistrationAuthException e) {
            model.addAttribute("registration", e.getRegistration());
            return this.viewName("auth");
        }

        return this.viewName("update");
    }

    @RequestMapping(value = "/seminar/{id}/register/update", method = RequestMethod.POST)
    public String update(@PathVariable int id,
                         @ModelAttribute("registration") SeminarRegistration registration,
                         BindingResult result, SessionStatus status,
                         Model model, HttpSession session) {
        this.actvieTabUI("register_update", model);

        Seminar seminar = this.loadSeminar(id, model);
        if (!seminar.isActive()) {
            return this.message("지난 세미나는 신청하실수 없습니다.", model);
        }

        try {
            if (!this.service.modifyRegistration(seminar, registration, result)) {
                return this.viewName("update");
            }
        }
        catch (SeminarRegistrationDoesNotExistException e) {
            return this.message("신청된 정보가 없습니다.", model);
        }
        catch (SeminarRegistrationAuthException e) {
            model.addAttribute("registration", e.getRegistration());
            return this.viewName("auth");
        }

        status.setComplete();
        this.setFlashMessage("세미나 신청 정보를 정상적으로 수정했습니다.", session);
        return this.redirectUrl(seminar, "view");

    }

    @RequestMapping(value = "/seminar/{id}/register/cancel", method = RequestMethod.GET)
    public String cancelForm(@PathVariable int id, Model model) {
        this.actvieTabUI("register_cancel", model);

        Seminar seminar = this.loadSeminar(id, model);
        if (!seminar.isActive()) {
            return this.message("지난 세미나는 신청하실수 없습니다.", model);
        }

        try {
            SeminarRegistration registration = this.service.getRegistration(seminar);
            model.addAttribute("registration", registration);
        }
        catch (SeminarRegistrationDoesNotExistException e) {
            return this.message("회원님의 계정으로 신청된 정보가 없습니다.", model);
        }
        catch (SeminarRegistrationAuthException e) {
            model.addAttribute("registration", e.getRegistration());
            return this.viewName("auth");
        }

        return this.viewName("cancel");
    }

    @RequestMapping(value = "/seminar/{id}/register/cancel", method = RequestMethod.POST)
    public String cancel(@PathVariable int id,
                         @ModelAttribute("registration") SeminarRegistration registration,
                         BindingResult result, SessionStatus status,
                         Model model, HttpSession session) {
        this.actvieTabUI("register_cancel", model);

        Seminar seminar = this.loadSeminar(id, model);
        if (!seminar.isActive()) {
            return this.message("지난 세미나는 신청하실수 없습니다.", model);
        }

        try {
            if (!this.service.cancelRegistration(seminar, registration, result)) {
                return this.viewName("cancel");
            }
        }
        catch (SeminarRegistrationDoesNotExistException e) {
            return this.message("회원님의 계정으로 신청된 정보가 없습니다.", model);
        }
        catch (SeminarRegistrationAuthException e) {
            model.addAttribute("registration", e.getRegistration());
            return this.viewName("auth");
        }

        status.setComplete();
        this.setFlashMessage("세미나 신청를 정상적으로 취소했습니다.", session);
        return this.redirectUrl(id, "view");

    }
}
