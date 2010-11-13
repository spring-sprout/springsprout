package springsprout.service.generation;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 3
 * Time: 오후 3:57:45
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
public class CodeGenerationException extends RuntimeException {

    public CodeGenerationException(String msg, Throwable e){
        super(msg, e);
    }

}
