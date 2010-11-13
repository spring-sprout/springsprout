package sandbox.freemarker;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.HashMap;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 3
 * Time: 오후 1:26:45
 * To change this template use File | Settings | File Templates.
 */
public class SimpleExample {

    public static void main(String[] args) throws Exception {
        /* ------------------------------------------------------------------- */
        /* You should do this ONLY ONCE in the whole application life-cycle:   */

        /* Create and adjust the configuration */
        Configuration cfg = new Configuration();
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDirectoryForTemplateLoading(new ClassPathResource("/sandbox/freemarker").getFile());

        /* ------------------------------------------------------------------- */
        /* You usually do these for many times in the application life-cycle:  */

        /* Get or create a template */
        Template temp = cfg.getTemplate("testTemplate.ftl");

        /* Create a data-model */
        Map root = new HashMap();
        root.put("message", "Hello Freemarker");

        /* Merge data-model with template */
        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, out);
        out.flush();
    }
}
