package sandbox.async;

import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.assertThat;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 10
 * Time: 오후 1:02:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AsyncAnnoTest {

    @Autowired BeanService beanService;

    @Test
    public void async() throws Exception {
        assertThat(beanService, is(notNullValue()));
        Set<Thread> threads = new HashSet<Thread>();

        for(int i = 0 ; i < 200 ; i++){
            collectThreadInfo(beanService.more(), threads);
        }
        assertThat(threads.size(), is(5));
        assertThat(threads.contains(beanService.more().get()), is(true));
    }

    private void collectThreadInfo(Future<Thread> future, Set<Thread> threads) throws Exception {
        threads.add(future.get());
    }

}
