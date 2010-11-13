package springsprout.modules.main;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.test.DBUnitSupport;
import springsprout.modules.main.support.GraffitiDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@Transactional
public class GraffitiRepositoryImplTest extends DBUnitSupport {

	@Autowired GraffitiRepositoryImpl gr;


	@Test public void deleteFirstGraffiti() throws Exception {
		insertXmlData("testData.xml");
        assertThat(gr.getAll().size(), is(3));

//		gr.deleteFirstGraffiti();
//		assertThat(gr.getAll().size(), is(2));
//        System.out.println(gr.getAll().get(0).getId());
//        System.out.println(gr.getAll().get(1).getId());
//
//		assertThat(gr.getById(2), is(notNullValue()));
//		assertThat(gr.getById(3), is(notNullValue()));
	}

	@Test public void getTotalRowCount() throws Exception {
		insertXmlData("testData.xml");

		assertThat(gr.getTotalRowCount(), is(3));
	}

	@Test public void getByWriteDate() throws Exception {
		insertXmlData("testData.xml");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<GraffitiDTO> graffitis = null;
		Date writeDate = null;

		writeDate = dateFormat.parse("2009-10-12 16:00:01");
		graffitis = gr.getByWriteDate(writeDate);
		assertThat(graffitis.get(0) instanceof GraffitiDTO, is(true));
		assertThat(graffitis.size(), is(3));
		assertThat(graffitis.get(0).getContents(), is("테스트 로그 1"));
		
		writeDate = dateFormat.parse("2009-10-12 17:10:01");
		graffitis = gr.getByWriteDate(writeDate);

		assertThat(graffitis.size(), is(1));
	}

}
