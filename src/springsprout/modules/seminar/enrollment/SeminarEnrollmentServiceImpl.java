package springsprout.modules.seminar.enrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.domain.Seminar;
import springsprout.domain.SeminarComer;
import springsprout.modules.seminar.SeminarRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 26
 * Time: 오후 10:38:25
 */
@Service
@Transactional
public class SeminarEnrollmentServiceImpl implements SeminarEnrollmentService{

    @Autowired SeminarComerRepository repository;
    @Autowired SeminarRepository seminarRepository;


    public List<SeminarComer> enrollementsListOfSeminar(int sid) {
        return repository.listOfSeminar(sid);
    }

    public boolean isDuplicatedEmail(String email) {
        if(repository.getByEmail(email) != null)
            return true;
        return false;
    }

    public void addNewEnrollment(int sid, SeminarComer seminarComer) {
        Seminar seminar = seminarRepository.getById(sid);
        seminar.addSeminarCommer(seminarComer);
        //sending a mail

    }

    public SeminarComer getById(int eid) {
        return repository.getById(eid);
    }

    public void update(SeminarComer seminarComer) {
        repository.update(seminarComer);
        //sending a mail
    }

    public void confirm(int eid) {
        repository.getById(eid).confirm();
    }

    public void join(int eid) {
        repository.getById(eid).join();
    }

    public void enroll(int eid) {
        repository.getById(eid).enroll();
    }

    public void remove(int sid, int eid) {
        Seminar seminar = seminarRepository.getById(sid);
        SeminarComer seminarComer = repository.getById(eid);
        seminar.removeSeminarCommer(seminarComer);
        repository.delete(seminarComer);
        //sending a mail
    }

}
