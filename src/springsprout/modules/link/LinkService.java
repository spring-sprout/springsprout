package springsprout.modules.link;

import java.util.List;

import springsprout.domain.Link;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 7. 8
 * Time: 오전 10:17:18
 */
public interface LinkService {

    public List<Link> list();
    public void add(Link link);
    public Link get(int id);
    public void update(Link link);
    public void delete(int id);
}
