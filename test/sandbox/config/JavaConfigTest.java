package sandbox.config;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 11
 * Time: 오후 7:41:26
 */
public class JavaConfigTest {

    AnnotationConfigApplicationContext ac;

    @Before
    public void setUp(){
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
//        ac.scan(new String[]{"sandbox.config"});
        ac.register(SampleBean2.class);
        assertThat(ac, is(notNullValue()));
    }

    @Test
    public void getBeanFromAppConfig(){
        SampleBean bean1 = ac.getBean("sampleBean", SampleBean.class);
        SampleBean bean2 = ac.getBean("sampleBean", SampleBean.class);
        assertThat(bean1, is(notNullValue()));
        assertThat(bean2, is(notNullValue()));
        assertThat(bean1, is(bean2));

        AppConfig config1 = new AppConfig();
        SampleBean bean3 = config1.sampleBean();
        assertThat(bean3, is(not(bean2)));

        AppConfig config2 = ac.getBean("appConfig", AppConfig.class);
        SampleBean bean4 = config2.sampleBean();
        assertThat(bean4, is(bean2));

        SampleBean2 sb1 = ac.getBean("sampleBean2", SampleBean2.class);
        assertThat(bean1, is(notNullValue()));
        SampleBean bean5 = sb1.sampleBean();
        assertThat(bean5, is(not(bean2)));
    }

    @Test
    public void getBeanFromComponent(){
        for(String name : ac.getBeanDefinitionNames()){
            System.out.println(name);
        }
    }
}
