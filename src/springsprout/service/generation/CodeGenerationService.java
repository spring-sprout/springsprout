package springsprout.service.generation;


/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 3
 * Time: 오후 3:55:43
 * To change this template use File | Settings | File Templates.
 */
public interface CodeGenerationService {

    void generateController(ControllerSetter settings) throws CodeGenerationException;

    void generateRepository(RepositorySetter settings) throws CodeGenerationException;

}
