package springsprout.modules.file;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="/testContext.xml")
public class FileServiceTest {

	@Autowired @Qualifier("fileService") FileService fileService;

	@Test public void test(){
		Assert.assertEquals(1,1);
	}
}
