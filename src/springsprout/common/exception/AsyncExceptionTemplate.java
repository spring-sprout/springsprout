package springsprout.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.AsyncTaskExecutor;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 11. 19
 * Time: 오전 12:10:09
 */
public class AsyncExceptionTemplate implements ExceptionTemplate {

    private Logger logger = LoggerFactory.getLogger(this.getClass());    

    AsyncTaskExecutor asyncTaskExecutor;

    public void setAsyncTaskExecutor(AsyncTaskExecutor asyncTaskExecutor) {
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    public void catchAll(final ExceptionalWork work) {
        asyncTaskExecutor.execute(new Runnable(){
            public void run() {
                try {
                    work.run();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info(e.getMessage());
                }
            }
        }, AsyncTaskExecutor.TIMEOUT_IMMEDIATE);

    }
}
