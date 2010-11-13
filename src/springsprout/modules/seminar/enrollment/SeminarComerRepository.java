package springsprout.modules.seminar.enrollment;

import springsprout.domain.SeminarComer;
import springsprout.common.dao.GenericDao;

import java.util.List;

public interface SeminarComerRepository extends GenericDao<SeminarComer> {

    List<SeminarComer> listOfSeminar(int sid);

    SeminarComer getByEmail(String email);
}
