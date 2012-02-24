package sandbox.propertyPlaceHolder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class BeanSPTest {

    @Autowired ApplicationContext ac;
    @Autowired BeanSP beanSP;

    @Test
    public void di(){
        assertThat(ac, is(notNullValue()));
        assertThat(beanSP.name, is(notNullValue()));
        assertThat(beanSP.username, is("whiteship"));
    }

}
