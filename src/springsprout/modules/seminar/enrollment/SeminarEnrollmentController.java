package springsprout.modules.seminar.enrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springsprout.domain.Seminar;
import springsprout.domain.SeminarComer;
import springsprout.modules.seminar.SeminarService;
import springsprout.modules.seminar.enrollment.support.SeminarEnrollmentValidator;
import springsprout.modules.seminar.support.SeminarViewNames;
import springsprout.service.security.SecurityService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 26
 * Time: 오전 7:23:17
 */

@Controller
@RequestMapping("/seminar/*")
public class SeminarEnrollmentController {

    @Autowired SeminarEnrollmentService service;
    @Autowired SeminarEnrollmentValidator validator;
    @Autowired SecurityService securityService;

    /**
     * 참석자 목록
     */
    @RequestMapping(value = "/seminar/{sid}/commers")
    public String enrollementsListOfSeminar(@PathVariable int sid, Model model) {
        model.addAttribute("commers", service.enrollementsListOfSeminar(sid));
        return SeminarViewNames.SEMINAR_INDEX;
    }

    /**
     * 참석 신청 폼
     */
    @RequestMapping(value = "/seminar/{sid}/enrollment/add", method = RequestMethod.GET)
    public String fromOfNewEnrollment(@PathVariable int sid, Model model) {
        model.addAttribute(new SeminarComer(securityService.getPersistentMember()));
        return SeminarViewNames.SEMINAR_ENROLLMENT_FORM;
    }

    /**
     * 참석 신청 폼 처리
     */
    @RequestMapping(value = "/seminar/{sid}/enrollment/add", method = RequestMethod.POST)
    public String submitNewEnrollment(@PathVariable int sid,
                                      @ModelAttribute SeminarComer seminarComer,
                                      BindingResult result, Model model) {
        validator.validate(seminarComer, result);
        if(result.hasErrors())
            return  SeminarViewNames.SEMINAR_ENROLLMENT_FORM;

        service.addNewEnrollment(sid, seminarComer);
        return SeminarViewNames.REDIRECT_SEMINAR_VIEW;
    }

    /**
     * 참석 신청 수정폼
     */
    @RequestMapping(value = "/seminar/{sid}/enrollment/{eid}/update", method = RequestMethod.GET)
    public String formOfUpdatingEnrollment(@PathVariable int sid, @PathVariable int eid, Model model){
        model.addAttribute(service.getById(eid));
        return SeminarViewNames.SEMINAR_ENROLLMENT_UPDATE_FORM;
    }

    /**
     * 참석 신청 수정 폼처리
     */
    @RequestMapping(value = "/seminar/{sid}/enrollment/{eid}/update", method = RequestMethod.POST)
    public String updateEnrollment(@PathVariable int sid, @PathVariable int eid,
                                   @ModelAttribute SeminarComer seminarComer,
                                   BindingResult result, Model model) {

        SeminarComer existingSeminarComer = service.getById(eid);
        validator.validateUpdate(existingSeminarComer, seminarComer, result);
        if(result.hasErrors())
            return  SeminarViewNames.SEMINAR_ENROLLMENT_UPDATE_FORM;

        service.update(seminarComer);
        return SeminarViewNames.REDIRECT_SEMINAR_VIEW;
    }

    /**
     * 신청 (결제) 확인
     */
    @RequestMapping(value = "/enrollment/{eid}/confirm")
    public String confirmEnrollment(@PathVariable int eid){
        service.confirm(eid);
        return SeminarViewNames.SEMINAR_VIEW;
    }

    /**
     * 참석 확인
     */
    @RequestMapping(value = "/enrollment/{eid}/join")
    public String joinEnrollment(@PathVariable int eid){
        service.join(eid);
        return SeminarViewNames.SEMINAR_VIEW;
    }

    /**
     * 등록 상태로 전환
     */
    @RequestMapping(value = "/enrollment/{eid}/enroll")
    public String enrollEnrollment(@PathVariable int eid){
        service.enroll(eid);
        return SeminarViewNames.SEMINAR_VIEW;
    }

    /**
     * 참석 삭제
     */
    @RequestMapping(value = "/seminar/{sid}/enrollment/{eid}/delete")
    public String delete(@PathVariable int sid, @PathVariable int eid) {
        service.remove(sid, eid);
        return SeminarViewNames.REDIRECT_SEMINAR_INDEX;
    }

}
