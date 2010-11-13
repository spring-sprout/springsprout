package sandbox.applicationContext;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RefreshTest {

	@Autowired ApplicationContext applicationContext;
	
	@Test
	@Ignore
	public void refresh() throws Exception {
		applicationContext.getBean("sampleBean");
		System.out.println("sleep");
		for(int i = 0 ; i < 50000000 ; i++){
			new String(i + i + "");
		}
		System.out.println("awake");
		((ConfigurableApplicationContext)applicationContext).close();
		applicationContext.getBean("sampleBean");
		applicationContext.getBean("sampleBean");
	}
	
}
