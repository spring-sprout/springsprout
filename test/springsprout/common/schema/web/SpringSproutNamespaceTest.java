package springsprout.common.schema.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ViewResolver;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 12. 11
 * Time: 오후 12:21:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringSproutNamespaceTest {

    @Autowired ViewResolver viewResolver;
    @Autowired ApplicationContext applicationContext;

    @Test
    public void di(){
        assertThat(viewResolver, is(notNullValue()));

        for(String name : applicationContext.getBeanDefinitionNames()){
            System.out.println(name);
            System.out.println(applicationContext.getBean(name));
        }
        
    }
}
