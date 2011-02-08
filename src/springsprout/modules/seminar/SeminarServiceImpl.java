package springsprout.modules.seminar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.domain.Comment;
import springsprout.domain.Seminar;

import java.util.List;

@Service
@Transactional
public class SeminarServiceImpl implements SeminarService {

    @Autowired SeminarRepository seminarRepository;

	public Seminar getById(int id) {
		return seminarRepository.getById(id);
	}

    @Transactional(readOnly = true)
	public List<Seminar> findByActiveSeminar() {
		return seminarRepository.findActiveSeminar();
	}

    @Transactional(readOnly = true)
	public List<Seminar> findByPastSeminar() {
		return seminarRepository.findPastSeminar();
	}

    public void add(Seminar seminar) {
        seminarRepository.add(seminar);
    }

    public void update(Seminar seminar) {
        seminarRepository.update(seminar);
    }

    public void deleteById(int id) {
        seminarRepository.deleteById(id);
    }

    public List<Comment> getCommentListOfSeminar(int sid) {
        return seminarRepository.getById(sid).getComments();    
    }

    public void addComment(int sid, Comment comment) {
        seminarRepository.getById(sid).addComment(comment);
    }

    public Seminar getTheLatestSeminar() {
        return seminarRepository.getTheLatestSeminar();
    }
}
