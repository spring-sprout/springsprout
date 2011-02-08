package springsprout.modules.tag;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.test.DBUnitSupport;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 4
 * Time: 오전 7:04:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@Transactional
public class TagRepositoryImplTest extends DBUnitSupport{

    @Autowired TagRepositoryImpl repository;

    @Test
    public void getByTag() throws Exception {
        insertXmlData("testData.xml");
        assertThat(repository.getAll().size(), is(2));
        String tag = "spring";
        assertThat(repository.getByTag(tag), is(notNullValue()));
        tag = "keesun";
        assertThat(repository.getByTag(tag), is(nullValue()));
    }
}
