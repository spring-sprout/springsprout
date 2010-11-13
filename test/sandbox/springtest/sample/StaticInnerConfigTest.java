package sandbox.springtest.sample;

import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import sandbox.springtest.StaticMemberClassLoader;

import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 18
 * Time: 오전 11:24:06
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = StaticMemberClassLoader.class)
public class StaticInnerConfigTest {

    @Autowired ApplicationContext ac;

    static class Hello {
        @Inject Printer printer;
    }

    static class Printer {
    }

    class Foo {}

    @Test
    public void inject() {
        for(String beanName : ac.getBeanDefinitionNames()){
            System.out.println(beanName);
        }

        Hello hello = ac.getBean(Hello.class);
        assertThat(hello.printer, is(notNullValue()));
    }
}
