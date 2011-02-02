package springsprout.service.generation;

import springsprout.domain.Location;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 4. 9
 * Time: 오후 2:57:10
 */
public class CodeGenerationRunner {

    public static void main(String[] agrs) throws IOException {
        SpringSproutConfiguration configuration = new SpringSproutConfiguration();
        CodeGenerationService service = new FreemarkerCodeGenerationService(configuration);
        RepositorySetter settings =
            new FreemarkerRepositorySetter("src/springsprout/modules", "location", Location.class);
        service.generateRepository(settings);
    }
}
