package springsprout.modules.seminar.schedule;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springsprout.common.propertyeditor.FormatDatePropertyEditor;
import springsprout.domain.SeminarDetailSchedule;
import springsprout.modules.seminar.schedule.support.SeminarScheduleValidator;
import springsprout.modules.seminar.support.SeminarViewNames;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 26
 * Time: 오전 7:18:55
 */
@Controller
@RequestMapping("/seminar")
public class SeminarScheduleController {

    @Autowired SeminarScheduleService service;
    @Autowired SeminarScheduleValidator validator;

    /**
    * 발표 목록
    */
    @RequestMapping("/{sid}/schedules")
    public String scheduleListOfStudy(@PathVariable int sid, Model model) {
        model.addAttribute("schedules", service.scheduleListOfStudy(sid));
        return SeminarViewNames.SEMINAR_VIEW;
    }

    /**
    * 발표 등록
    */
    @RequestMapping(value = "/{sid}/schedule/add", method = RequestMethod.GET)
    public String formOfScheduleForm(@PathVariable int sid, Model model) {
        model.addAttribute(new SeminarDetailSchedule());
        return SeminarViewNames.SEMINAR_SHEDULE_FORM;
    }

    @RequestMapping(value = "/{sid}/schedule/add", method = RequestMethod.POST)
    public String submitScheduleForm(@PathVariable int sid,
                                     @ModelAttribute SeminarDetailSchedule schedule,
                                     BindingResult result, Model model) {
        validator.validate(schedule, result);
        if(result.hasErrors())
            return SeminarViewNames.SEMINAR_SHEDULE_FORM;
        service.addSchedult(sid, schedule);
        return SeminarViewNames.REDIRECT_SEMINAR_VIEW;
    }


    /**
     * 발표 수정
     */
    @RequestMapping(value = "/{sid}/schedule/{id}/update", method = RequestMethod.GET)
    public String formOfScheduleUpdate(@PathVariable int sid, @PathVariable int id, Model model) {
        model.addAttribute("schedule", service.getById(id));
        return SeminarViewNames.SEMINAR_SCHEDULE_UPDATE_FORM;
    }

    @RequestMapping(value = "/{sid}/schedule/{id}/update", method = RequestMethod.POST)
    public String submitScheduleUpdate(@PathVariable int sid, @PathVariable int id, 
                                       @ModelAttribute SeminarDetailSchedule schedule,
                                       BindingResult result, Model model) {
        validator.validate(schedule, result);
        if(result.hasErrors())
            return SeminarViewNames.SEMINAR_SCHEDULE_UPDATE_FORM;
        service.update(schedule);
        return SeminarViewNames.REDIRECT_SEMINAR_VIEW;
    }

    /**
     * 발표 삭제
     */
    @RequestMapping(value = "/{sid}/schedule/{id}/remove", method = RequestMethod.GET)
    public String presentationRemove(@PathVariable int sid, @PathVariable int id) {
        service.removeSchedulrOfSeminar(sid, id);
        return SeminarViewNames.REDIRECT_SEMINAR_VIEW;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, "startTime",  new FormatDatePropertyEditor("HH:mm"));
        binder.registerCustomEditor(Date.class, "endTime", new FormatDatePropertyEditor("HH:mm"));
    }
}
