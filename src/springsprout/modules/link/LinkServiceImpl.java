package springsprout.modules.link;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.domain.Link;
import springsprout.service.security.SecurityService;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 7. 8
 * Time: 오전 10:18:39
 */
@Service
@Transactional
public class LinkServiceImpl implements LinkService {

    @Autowired LinkRepository linkDao;
    @Autowired SecurityService securityService;

    public List<Link> list() {
        return linkDao.getAll();
    }

    public void add(Link link) {
        link.setCreated(new Date());
        link.setUpdated(new Date());
        link.setWriter(securityService.getCurrentMember());
        linkDao.add(link);
    }

    public Link get(int id) {
        return linkDao.getById(id);
    }

    public void update(Link link) {
        link.setUpdated(new Date());
        linkDao.update(link);
    }

    public void delete(int id) {
        linkDao.deleteById(id);
    }
    
}
