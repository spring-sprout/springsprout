package springsprout.modules.study;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springsprout.domain.Meeting;
import springsprout.domain.Study;

@Service
@Transactional
public class StudyIndexServiceImpl implements StudyIndexService {

	@Autowired StudyRepository studyRepository;
	public static final int studyViewCount = 4;
	
	public Meeting getRecentMeeting() {
		return studyRepository.getRecentMeeting();
	}

	public List<Study> getRecentStudies() {
		return studyRepository.findActiveStudies(studyViewCount);
	}

	public List<Study> find(String key) {
		return studyRepository.findStudies(key);
	}

}
