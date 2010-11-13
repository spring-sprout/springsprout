package springsprout.service.generation;

import java.io.IOException;

import springsprout.domain.Location;
import springsprout.domain.SeminarComer;
import springsprout.domain.SeminarDetailSchedule;
import springsprout.domain.WikiSpace;

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
