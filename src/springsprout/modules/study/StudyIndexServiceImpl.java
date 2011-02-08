package springsprout.modules.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.domain.Meeting;
import springsprout.domain.Study;
import springsprout.modules.study.support.StudyIndexInfo;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudyIndexServiceImpl implements StudyIndexService {
	public static final int studyViewCount = 4;
	@Autowired StudyRepository studyRepository;
	@Resource StudyService advancedStudyService;
	
	public Meeting getRecentMeeting() {
		return studyRepository.getRecentMeeting();
	}

	public List<Study> getRecentStudies() {
		return studyRepository.findActiveStudies(studyViewCount);
	}

	public List<Study> find(String key) {
		return studyRepository.findStudies(key);
	}

	public StudyIndexInfo makeStudyIndexInfo() {
		return new StudyIndexInfo( studyRepository.findActiveStudies(), studyRepository.findPastStudies());
	}

	public Map<String, Object> swichStudyView(String studyType) {
		Map<String, Object> model = new HashMap<String, Object>();
		if ( studyType.equals("active")) {
			model.put("list", advancedStudyService.findActiveStudies());
			model.put("minitab_active", "active");
		} else {
			model.put("list", advancedStudyService.findPastStudies());
			model.put("minitab_past", "active");
		}
		return model;
	}

}
