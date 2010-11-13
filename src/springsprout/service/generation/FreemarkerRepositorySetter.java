package springsprout.service.generation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 4
 * Time: 오후 2:18:03
 * To change this template use File | Settings | File Templates.
 */
public class FreemarkerRepositorySetter implements RepositorySetter {

    private String module;
    private String interfaceTemplateName;
    private String implTemplateName;
    private String destinationDirPath;
    private Class<?> domainClass;

    private Map<String, String> modelMap;
    private List<File> destinationDirs;
    private File interfaceFile;
    private File implFile;

    public FreemarkerRepositorySetter(String module, String interfaceTemplateName, String implTemplateName, String destinationDirPath, Class<?> domainClass) {
        this.module = module;
        this.interfaceTemplateName = interfaceTemplateName;
        this.implTemplateName = implTemplateName;
        this.destinationDirPath = destinationDirPath;
        this.domainClass = domainClass;

        String domainClassName = domainClass.getSimpleName();
        modelMap = new HashMap<String, String>();
        modelMap.put("module", module);
        modelMap.put("domainClass", domainClassName);

        interfaceFile = new File(destinationDirPath + "/" + module + "/" + domainClassName + "Repository.java");
        implFile = new File(destinationDirPath + "/" + module + "/" + domainClassName + "RepositoryImpl.java");

        destinationDirs = new ArrayList<File>();
        destinationDirs.add(new File(destinationDirPath));
        destinationDirs.add(new File(destinationDirPath + "/" + module));
    }

    public FreemarkerRepositorySetter(String destinationDirPath, String module, Class<?> domainClass) {
        this(module, "repository.ftl", "repository_impl.ftl", destinationDirPath, domainClass);
    }

    public String getModule() {
        return module;
    }
    public String getInterfaceTemplateName() {
        return interfaceTemplateName;
    }
    public String getImplTemplateName() {
        return implTemplateName;
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
    public List<File> getDestinationDirs() {
        return destinationDirs;
    }
    public File getInterfaceFile() {
        return interfaceFile;
    }
    public File getImplFile() {
        return implFile;
    }

}
