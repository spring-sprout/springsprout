package springsprout.modules.seminar;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.test.DBUnitSupport;
import springsprout.domain.Seminar;
import springsprout.domain.SeminarComer;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 25
 * Time: 오전 9:53:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@Transactional
public class SeminarRepositoryImplTest extends DBUnitSupport {

    @Autowired SeminarRepository seminarRepository;

    /**
     * Semianr 1's maximum is 1;
     * Seminar 1's enrolled commers are 2;
     * So, Seminar 1's waiting commers are 1(id, 2);
     * @throws Exception
     */
    @Test
    public void testFindWaitComersBy() throws Exception {
        insertXmlData("testData.xml");
        Seminar seminar = seminarRepository.getById(1);
        List<SeminarComer> list = seminarRepository.findWaitComersBy(seminar);
        assertThat(list.size(), is(1));
        assertThat(list.get(0).getId(), is(2));
    }

    /**
     * Seminar 1's maximum is 1;
     * Seminar 1's enrolled commers are 2;
     * So, Seminar 1's attendable commers are 1(id, 1);
     * @throws Exception
     */
    @Test
    public void testFindAttendAvailableComersBy() throws Exception {
        insertXmlData("testData.xml");
        Seminar seminar = seminarRepository.getById(1);
        List<SeminarComer> list = seminarRepository.findAttendAvailableComersBy(seminar);
        assertThat(list.size(), is(1));
        assertThat(list.get(0).getId(), is(1));
    }

    /**
     * There are 3 seminars.
     * One of them is now 접수중 (status, 20)
     * The others are now 세미나 종료 (status, 50)
     * So, Avtive Seminar is 1.(id, 1)
     * @throws Exception
     */
    @Test
    public void testFindActiveSeminar() throws Exception {
        insertXmlData("testData.xml");
        List<Seminar> list = seminarRepository.findActiveSeminar();
        assertThat(list.size(), is(1));
        assertThat(list.get(0).getId(), is(1));
    }

    /**
     * There are 3 seminars.
     * One of them is now 접수중 (status, 20)
     * The others are now 세미나 종료 (status, 50)
     * So, Past Seminars are 2.(id, 2, 3)
     * @throws Exception
     */
    @Test
    public void testFindPastSeminar() throws Exception {
        insertXmlData("testData.xml");
        List<Seminar> list = seminarRepository.findPastSeminar();
        assertThat(list.size(), is(2));
        assertThat(list.get(0).getId(), is(2));
        assertThat(list.get(1).getId(), is(3));
    }

    /**
     * There are 3 seminars.
     * One of them is the lastest one.(id=2, created=2010-4-26)
     * @throws Exception
     */
    @Test
    public void testGetTheLatestSeminar() throws Exception {
        insertXmlData("testData.xml");
        Seminar seminar = seminarRepository.getTheLatestSeminar();
        assertThat(seminar, is(notNullValue()));
        assertThat(seminar.getId(), is(2));
    }

}
