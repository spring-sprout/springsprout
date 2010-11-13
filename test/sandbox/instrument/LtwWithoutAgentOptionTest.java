package sandbox.instrument;

import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver;
import org.springframework.instrument.InstrumentationSavingAgent;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.lang.instrument.Instrumentation;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 9
 * Time: 오후 4:25:04
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class LtwWithoutAgentOptionTest {

    @Autowired ApplicationContext ac;

    @Test
    public void ltw(){
        assertThat(ac, is(notNullValue()));
        for(String beanName : ac.getBeanDefinitionNames()){
            System.out.println(beanName);
        }
    }

}
