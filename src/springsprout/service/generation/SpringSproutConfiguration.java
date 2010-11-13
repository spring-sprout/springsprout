package springsprout.service.generation;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 4. 9
 * Time: 오후 3:01:25
 */
public class SpringSproutConfiguration extends Configuration {

    public SpringSproutConfiguration() throws IOException {
        setObjectWrapper(new DefaultObjectWrapper());
        setDirectoryForTemplateLoading(new FileSystemResource("doc/template").getFile());
    }

}
