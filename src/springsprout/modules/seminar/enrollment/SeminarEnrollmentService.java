package springsprout.modules.seminar.enrollment;

import springsprout.domain.SeminarComer;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 26
 * Time: 오후 10:37:39
 */
public interface SeminarEnrollmentService {

    List<SeminarComer> enrollementsListOfSeminar(int sid);

    boolean isDuplicatedEmail(String email);

    void addNewEnrollment(int sid, SeminarComer seminarComer);

    SeminarComer getById(int eid);

    void update(SeminarComer seminarComer);

    void confirm(int eid);

    void join(int eid);

    void enroll(int eid);

    void remove(int sid, int eid);
    
}
