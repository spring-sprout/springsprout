package sandbox.props;

import org.junit.Test;
import org.junit.runner.RunWith;
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
@ContextConfiguration("b-context.xml")
public class PropsBTest {

    @Resource Props propsB;

    @Test
    public void di(){
        assertThat(propsB, is(notNullValue()));
        assertThat(propsB.getValue(), is(20));
    }
}
