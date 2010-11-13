package springsprout.modules.seminar;

import springsprout.domain.Comment;
import springsprout.domain.Seminar;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 26
 * Time: 오후 10:26:06
 */
public interface SeminarService {
    Seminar getTheLatestSeminar();

    List<Seminar> findByActiveSeminar();

    List<Seminar> findByPastSeminar();

    Seminar getById(int id);

    void add(Seminar seminar);

    void update(Seminar seminar);

    void deleteById(int id);

    List<Comment> getCommentListOfSeminar(int sid);

    void addComment(int sid, Comment comment);

}
