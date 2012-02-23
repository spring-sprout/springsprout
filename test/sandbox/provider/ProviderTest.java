package sandbox.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 1. 12
 * Time: 오후 9:26:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ProviderTest {

    @Autowired White white;

    @Test
    public void get(){
        white.hi();
        white.hi();
        white.hi();
        white.hi();
    }

}
