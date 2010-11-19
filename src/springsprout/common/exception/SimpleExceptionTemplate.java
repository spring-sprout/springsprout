package springsprout.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 11. 18
 * Time: 오후 11:22:17
 */
public class SimpleExceptionTemplate implements ExceptionTemplate {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void catchAll(ExceptionalWork work) {
        try {
            work.run();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}
