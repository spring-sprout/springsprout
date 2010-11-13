package sandbox.async;

import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 10
 * Time: 오후 1:02:31
 * To change this template use File | Settings | File Templates.
 */
@Service
public class BeanService {

    public void normal(){
        System.out.println("===========================");
        System.out.println(Thread.currentThread());
        System.out.println("===========================");
        System.out.println("do it");
        this.more();
    }

    @Async
    public Future<Thread> more() {
        return new AsyncResult<Thread>(Thread.currentThread());
    }

}
