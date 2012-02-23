package springsprout.common.messages;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="testContext.xml")
public class MessageAccessorTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
