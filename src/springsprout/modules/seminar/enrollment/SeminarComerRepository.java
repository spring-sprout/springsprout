package springsprout.modules.seminar.enrollment;

import springsprout.common.dao.GenericDao;
import springsprout.domain.SeminarComer;

import java.util.List;

public interface SeminarComerRepository extends GenericDao<SeminarComer> {

    List<SeminarComer> listOfSeminar(int sid);

    SeminarComer getByEmail(String email);
}
