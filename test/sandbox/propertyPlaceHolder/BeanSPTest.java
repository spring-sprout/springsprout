package sandbox.propertyPlaceHolder;

import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import sandbox.springtest.AnnotationContextLoader;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationContextLoader.class, locations = "./")
public class BeanSPTest {

    @Autowired ApplicationContext ac;
    @Autowired BeanSP beanSP;

    @Test
    public void di(){
        assertThat(ac, is(notNullValue()));
        assertThat(beanSP.name, is(notNullValue()));
        assertThat(beanSP.username, is("sa"));
    }

}
