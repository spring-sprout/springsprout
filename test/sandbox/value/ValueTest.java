package sandbox.value;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 3. 16
 * Time: 오후 5:54:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ValueTest {

    @Autowired Whiteship whiteship;

    @Test
    public void defaultName(){
        assertThat(whiteship.getName(), is("keesun"));
    }

}
