package springsprout.modules.study;

import springsprout.domain.Comment;
import springsprout.domain.Study;
import springsprout.modules.study.support.StudyContainer;
import springsprout.modules.study.support.StudyCriteria;
import springsprout.modules.study.support.StudyIndexInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 3. 16
 * Time: 오전 10:38:00
 */
public interface StudyService {

    void addStudy(Study study);

    void updateStudy(Study study, Boolean isGoingToBeNotified);

    List<Study> findActiveStudies();

    List<Study> findActiveStudies(int rows);

    List<Study> findPastStudies();

    StudyContainer findStudies(StudyCriteria cri);

    Study getStudyById(int id);

    void notify(int studyId);

    void deleteStudy(Study study);

    void endStudy(Study study);

    void startStudy(Study study);

    void addCurrentMember(Study study);

    void removeCurrentMember(Study study);

    boolean isCurrentUserAlreadyJoinedIn(int studyId);

    boolean isCurrentUserTheStudyManagerOrAdmin(int studyId);
    
    /**
     * 스터디 인덱스 화면에서 보여줄 정보를 구성한다.
     * @return
     */
    StudyIndexInfo makeStudyIndexInfo(); 
    
}
