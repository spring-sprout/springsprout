package springsprout.modules.study.meeting.presentation;

import java.util.List;

import springsprout.common.dao.GenericDao;
import springsprout.domain.Presentation;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 2
 * Time: 오후 8:46:56
 * To change this template use File | Settings | File Templates.
 */
public interface PresentationRepository extends GenericDao<Presentation>{
	
	/**
	 * 준비중인 모임이 가장 위로 올라 가도록 조회
	 * @param meetingId
	 * @return
	 */
	public List<Presentation> getPresentationListSortByActivity( int meetingId);
}
