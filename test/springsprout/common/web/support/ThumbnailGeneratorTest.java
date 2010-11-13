package springsprout.common.web.support;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springsprout.common.web.support.ThumbnailGenerator;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/testContext.xml")
public class ThumbnailGeneratorTest {

	@Autowired ThumbnailGenerator thumbnailGenerator;
	
	@Test
    public void generate() throws IOException {
    	String path = this.getClass().getResource("").getPath();
    	File srcFile = new File( path + "testImage.jpg");
    	File destFile = new File("web/images/userimage/dosajun@gmail.com/testDestImage.jpg");
    	thumbnailGenerator.generate(srcFile, destFile);
    	
    	assertThat( destFile.canRead(), is(true));
    	assertThat( destFile.delete(), is(true));
    }
    
   
}
