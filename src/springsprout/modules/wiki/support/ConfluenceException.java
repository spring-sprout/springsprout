package springsprout.modules.wiki.support;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 3
 * Time: 오후 10:36:22
 */
@SuppressWarnings("serial")
public class ConfluenceException extends RuntimeException {

    public ConfluenceException(String msg, Exception e){
        super(msg, e);
    }
}
