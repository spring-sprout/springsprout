package springsprout.modules.notice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.test.DBUnitSupport;
import springsprout.domain.Notice;

import javax.annotation.security.RunAs;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 8. 17
 * Time: 오전 9:53:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@Transactional
public class NoticeRepositoryImplTest extends DBUnitSupport {

    @Autowired NoticeRepository repository;

    @Test
    public void testGetTheLastedOne() throws Exception {
        insertXmlData("testData.xml");
        Notice notice = repository.getTheLastedOne();
        assertThat(notice, is(notNullValue()));
        assertThat(notice.getId(), is(5));
    }
}
