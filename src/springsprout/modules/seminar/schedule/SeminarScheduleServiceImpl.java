package springsprout.modules.seminar.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.domain.SeminarDetailSchedule;
import springsprout.modules.seminar.SeminarRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 28
 * Time: 오후 3:42:53
 */
@Service
@Transactional
public class SeminarScheduleServiceImpl implements SeminarScheduleService {

    @Autowired SeminarDetailScheduleRepository repository;
    @Autowired SeminarRepository seminarRepository;
    
    public List<SeminarDetailSchedule> scheduleListOfStudy(int sid) {
        return repository.scheduleListOfStudy(sid);
    }

    public void addSchedult(int sid, SeminarDetailSchedule schedule) {
        seminarRepository.getById(sid).addSeminarDetailSchedule(schedule);
    }

    public SeminarDetailSchedule getById(int id) {
        return repository.getById(id);
    }

    public void update(SeminarDetailSchedule schedule) {
        repository.update(schedule);
    }

    public void removeSchedulrOfSeminar(int sid, int id) {
        SeminarDetailSchedule schedule = repository.getById(id);
        seminarRepository.getById(sid).removeSeminarDetailSchedule(schedule);
        repository.delete(schedule);
    }
}
