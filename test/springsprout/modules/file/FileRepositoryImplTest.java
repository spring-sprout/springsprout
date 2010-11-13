package springsprout.modules.file;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import springsprout.common.test.DBUnitSupport;
import springsprout.domain.UploadFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@Transactional
public class FileRepositoryImplTest extends DBUnitSupport{

	@Autowired FileRepositoryImpl fileRepository;
	
	/**
	 * <uploadfile id="1" uploadDate="2008-12-10" />
	 * <uploadfile id="2" uploadDate="2010-1-2" />
	 * <uploadfile id="3" uploadDate="2009-7-31" />
	 */
	@Test @Ignore
	public void list() throws Exception {
		insertXmlData("testData.xml");
		List<UploadFile> files = fileRepository.list();
		assertThat(files.size(), is(3));
		assertThat(files.get(0).getId(), is(2));
		assertThat(files.get(1).getId(), is(3));
		assertThat(files.get(2).getId(), is(1));
	}
}
