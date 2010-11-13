package springsprout.modules.seminar;

import java.util.List;

import springsprout.common.dao.GenericDao;
import springsprout.domain.Seminar;
import springsprout.domain.SeminarComer;

public interface SeminarRepository extends GenericDao<Seminar>{

	/**
	 * findByWaitComers
	 * 참석대기인원 목록 
	 */
	List<SeminarComer> findWaitComersBy(Seminar seminar);

    /**
	 * findByAttendAvailableComers
	 * 참석가능인원 목록 
	 */
	List<SeminarComer> findAttendAvailableComersBy(Seminar seminar);

    /**
	 * findByActiveSeminar
	 * 세미나정보 
	 */
	List<Seminar> findActiveSeminar();

    /**
	 * findByPastSeminar
	 * 지난 세미나 목록   
	 */
	List<Seminar> findPastSeminar();

    Seminar getTheLatestSeminar();
}