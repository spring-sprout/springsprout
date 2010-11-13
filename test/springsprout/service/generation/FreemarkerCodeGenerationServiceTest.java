package springsprout.service.generation;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.File;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import springsprout.domain.Study;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 4
 * Time: 오후 1:13:28
 * To change this template use File | Settings | File Templates.
 */
public class FreemarkerCodeGenerationServiceTest {

    FreemarkerCodeGenerationService service;

    @Before
    public void setUp() throws IOException {
        Configuration configuration = new Configuration();
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setDirectoryForTemplateLoading(new FileSystemResource("doc/template").getFile());

        assertNotNull(configuration);

        service = new FreemarkerCodeGenerationService(configuration);
    }

    @Test
    public void generateDao(){
        RepositorySetter settings = new FreemarkerRepositorySetter("test", "repository.ftl", "repository_impl.ftl", "test/springsprout/modules", Study.class);
        service.generateRepository(settings);

        assertTrue(new File("test/springsprout/modules/test/StudyRepository.java").exists());
        assertTrue(new File("test/springsprout/modules/test/StudyRepositoryImpl.java").exists());
        service.deleteRepository();
        assertFalse(new File("test/springsprout/modules/test/StudyRepository.java").exists());
        assertFalse(new File("test/springsprout/modules/test/StudyRepositoryImpl.java").exists());
    }

    @Test
    public void generateController() throws IOException {
        service.generateController(new FreemarkerControllerSetter("test", "controller.ftl", "test/springsprout/modules", Study.class));

        assertTrue(new File("test/springsprout/modules/test/StudyController.java").exists());
        service.deleteController();
        assertFalse(new File("test/springsprout/modules/test/StudyController.java").exists());
    }

    //TODO template file loading fail test

    //TODO destination file make fail test

    //TODO template processing fail test

    //TODO destination folder mkdir test
}
