package sandbox.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import sandbox.springtest.StaticMemberClassLoader;
import sandbox.springtest.AnnotationContextLoader;

import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 1. 12
 * Time: 오후 9:26:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationContextLoader.class)
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
