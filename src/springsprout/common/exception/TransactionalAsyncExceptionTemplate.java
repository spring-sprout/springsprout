package springsprout.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 11. 19
 * Time: 오전 11:40:54
 */
public class TransactionalAsyncExceptionTemplate implements ExceptionTemplate {

    private Logger logger = LoggerFactory.getLogger(this.getClass());    

    AsyncTaskExecutor asyncTaskExecutor;
    PlatformTransactionManager transactionManager;

    public void setAsyncTaskExecutor(AsyncTaskExecutor asyncTaskExecutor) {
        this.asyncTaskExecutor = asyncTaskExecutor;
    }
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void catchAll(final ExceptionalWork work) {
        asyncTaskExecutor.execute(new Runnable(){
            public void run() {
                TransactionStatus ts = transactionManager.getTransaction(new DefaultTransactionDefinition());
                try {
                    work.run();
                    transactionManager.commit(ts);
                } catch (Exception e) {
                    transactionManager.rollback(ts);
                    e.printStackTrace();
                    logger.info(e.getMessage());
                }
            }
        }, AsyncTaskExecutor.TIMEOUT_IMMEDIATE);

    }

}
