package sandbox.springtest.sample;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sandbox.springtest.AnnotationContextLoader;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 15
 * Time: 오후 12:32:04
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationContextLoader.class, locations = {"/sandbox/springtest/sample/SpringAnnotationConfigTestAppConfig.java"})
//@ContextConfiguration(loader = AnnotationContextLoader.class, locations = {"/sandbox/springtest/sample/"})
//@ContextConfiguration(loader = AnnotationContextLoader.class, locations = {"/sandbox/springtest/sample"})
//@ContextConfiguration(loader = AnnotationContextLoader.class, locations = {"./SpringAnnotationConfigTestAppConfig.java"})
//@ContextConfiguration(loader = AnnotationContextLoader.class, locations = {"./"})
//@ContextConfiguration(loader = AnnotationContextLoader.class, locations = {"."})
public class SpringAnnotationConfigTest {

    @Autowired ApplicationContext ac;
    @Autowired String name;

    @Test
    public void di(){
        assertNotNull(ac);
        assertThat(name, is("keesun"));
//        for(String beanName : ac.getBeanDefinitionNames())
//            System.out.println(beanName);
    }

}
