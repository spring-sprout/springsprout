package sandbox.props;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: nhn
 * Date: 10. 12. 20
 * Time: 오후 3:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("a-context.xml")
public class PropsAllTest {

    @Resource Props propsA;
    @Resource Props propsB;
    @Resource Props propsC;

    @Autowired ApplicationContext applicationContext;

    @Test
    public void di(){
        assertThat(propsA, is(notNullValue()));
        assertThat(propsA.getValue(), is(10));

        assertThat(propsB, is(notNullValue()));
        assertThat(propsB.getValue(), is(20));

        assertThat(propsC, is(notNullValue()));
        assertThat(propsC.getValue(), is(30));
    }

    @Test
    public void allBean(){
        for(String name : applicationContext.getBeanDefinitionNames()){
            System.out.println(name);
            System.out.println(applicationContext.getBean(name));
        }
    }
}
