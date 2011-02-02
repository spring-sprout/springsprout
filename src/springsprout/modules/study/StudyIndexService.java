package springsprout.modules.study;

import springsprout.domain.Meeting;
import springsprout.domain.Study;
import springsprout.modules.study.support.StudyIndexInfo;

import java.util.List;
import java.util.Map;

/**
 * 스터디 인덱스 화면을 위한 서비스
 * 
 * @author June
 *
 */
public interface StudyIndexService {
	public Meeting getRecentMeeting();
	public List<Study> getRecentStudies();
	public List<Study> find(String key);
	public StudyIndexInfo makeStudyIndexInfo();
	public Map<String, Object> swichStudyView(String studyType);
}
