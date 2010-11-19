package springsprout.modules.study;

import springsprout.common.dao.GenericDao;
import springsprout.domain.Member;
import springsprout.domain.Study;
import springsprout.modules.study.support.StudyContext;
import springsprout.modules.study.support.StudyCriteria;

import java.util.List;

public interface StudyRepository extends GenericDao<Study>{

    List<Study> getStudyList();
    
	List<Study> getStudyList(StudyCriteria cri);

	List<Study> getStudyListByContext(StudyContext context);

	int getTotalRowsCount(StudyCriteria cri);

	List<Study> findActiveStudies();

	List<Study> findPastStudies();

    boolean isUserAlreayJoinedIn(Member user, int studyId);

    boolean isUserTheStudyManager(Member userId, int studyId);

    List<Study> findActiveStudies(int rows);

    Member getManagerByStudyId(Integer studyId);

    List<Member> getMemberListByStudyId(Integer studyId);
}