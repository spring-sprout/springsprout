package springsprout.common.messages;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="testContext.xml")
public class MessageAccessorTest {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired MessageSourceAccessor accessor;
	
	@Test
	public void printMsg() {
		logger.info(accessor.getMessage("study.open"));
	}
	
	@Test
	public void compare() {
		assertThat(accessor.getMessage("study.open"), is("스터디가 개설되었습니다."));
	}
}
