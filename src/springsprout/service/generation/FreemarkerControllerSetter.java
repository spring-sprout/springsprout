package springsprout.service.generation;

import org.springframework.util.ClassUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 4
 * Time: 오후 1:41:06
 * To change this template use File | Settings | File Templates.
 */
public class FreemarkerControllerSetter implements ControllerSetter {

    private String module;
    private String templateFileName;
    private String destinationDirPath;
    private Class<?> domainClass;

    private Map<String, String> modelMap;
    private List<File> destinationDirs;
    private File destinationFile;

    public FreemarkerControllerSetter(String module, String templateFileName, String destinationDirPath, Class<?> domainClass) {
        this.module = module;
        this.templateFileName = templateFileName;
        this.destinationDirPath = destinationDirPath;
        this.domainClass = domainClass;

        String domainClassName = domainClass.getSimpleName();
        modelMap = new HashMap<String,  String>();
        modelMap.put("module", module);
        modelMap.put("domainClass", domainClassName);
        modelMap.put("domainName", ClassUtils.getShortNameAsProperty(domainClass));

        destinationFile = new File(destinationDirPath + "/" + module + "/" + domainClassName + "Controller.java");

        destinationDirs = new ArrayList<File>();
        destinationDirs.add(new File(destinationDirPath));
        destinationDirs.add(new File(destinationDirPath + "/" + module));
    }

    public String getModule() {
        return module;
    }
    public String getTemplateFileName() {
        return templateFileName;
    }
    public String getDestinationDirPath() {
        return destinationDirPath;
    }
    public Class<?> getDomainClass() {
        return domainClass;
    }

    public Map<String, String> getModelMap() {
        return modelMap;
    }
    public File getDestinationFile() {
        return destinationFile;
    }
    public List<File> getDestinationDirs() {
        return destinationDirs;
    }
}
