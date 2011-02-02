package springsprout.modules.term;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.test.DBUnitSupport;
import springsprout.common.web.support.CenterPositionPaging;
import springsprout.common.web.support.OrderParam;
import springsprout.modules.term.support.DevTermContext;
import springsprout.modules.term.support.DevTermSearchParam;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 1. 12
 * Time: 오후 5:09:13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@Transactional
public class DevTermRepositoryImplTest extends DBUnitSupport{

    @Autowired DevTermRepositoryImpl devTermRepository;

    @Test
    public void testGetTotalRowsCount() throws Exception {
        insertXmlData("testData.xml");
		assertThat(devTermRepository.getTotalRowsCount(new DevTermSearchParam()), is(3));
    }

    @Test
    @Ignore
    public void testSearchByContext() throws Exception {
        insertXmlData("testData.xml");
        DevTermSearchParam searchParam = new DevTermSearchParam();
        searchParam.setKeyword("j");

        DevTermContext context = new DevTermContext();
        context.setSearchParam(searchParam);
        context.setOrderParam(new OrderParam());
        context.setPaging(new CenterPositionPaging());

        assertThat(devTermRepository.searchByContext(context).size(), is(2));
    }
}
