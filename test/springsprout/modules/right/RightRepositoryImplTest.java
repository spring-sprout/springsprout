package springsprout.modules.right;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import springsprout.common.test.DBUnitSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@Transactional
public class RightRepositoryImplTest extends DBUnitSupport{
	
	@Autowired
	RightRepository rr;
	
	/**
	 * <right id="1" name="a" />
	 * <right id="2" name="c" />
	 * <right id="3" name="b" />
	 */
	@Test
	public void getRightList() throws Exception {
		insertXmlData("testData.xml");
		assertThat(rr.getRightList().size(), is(3));
		assertThat(rr.getRightList().get(0).getId(), is(1));
		assertThat(rr.getRightList().get(1).getId(), is(3));
		assertThat(rr.getRightList().get(2).getId(), is(2));
	}
}
