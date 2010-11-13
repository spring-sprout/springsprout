package springsprout.modules.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springsprout.common.SpringSprout2System;
import springsprout.domain.Study;
import springsprout.modules.admin.support.StudyDTO;
import springsprout.modules.study.StudyService;
import springsprout.modules.study.support.StudyContainer;
import springsprout.modules.study.support.StudyCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Miracle
 * Date: 2010. 4. 14
 * Time: 오후 10:24:30
 */

@Controller
@RequestMapping("/admin/study")
public class StudyMgtController {

    private final StudyService studyService;
    
    @Autowired
    public StudyMgtController(StudyService studyService) {
        this.studyService = studyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getList(@ModelAttribute StudyCriteria cri, Model model) {
        
        StudyContainer container = studyService.findStudies(cri);
        
        List<Study> stduyList = container.getList();
        List<StudyDTO> studyDTOs = new ArrayList<StudyDTO>();
        
        for(Study s : stduyList) {
            StudyDTO studyDTO = new StudyDTO();
            springsprout.common.util.BeanUtils.beanCopy(studyDTO, s);

            
            studyDTOs.add(studyDTO);
        }

        model.addAttribute("studys", studyDTOs)
             .addAttribute("total", container.getTotal());
        
        return SpringSprout2System.JSON_VIEW;
    }
}
