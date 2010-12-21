package springsprout.modules.study;

import java.util.List;
import java.util.Set;

import springsprout.domain.Meeting;
import springsprout.domain.Study;

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
}
