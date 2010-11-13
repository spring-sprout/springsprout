package springsprout.service.generation;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 3
 * Time: 오후 3:59:38
 * To change this template use File | Settings | File Templates.
 */
public class FreemarkerCodeGenerationService implements CodeGenerationService {

    private Configuration configuration;
    private Stack<File> generatedFiles;

    public FreemarkerCodeGenerationService(Configuration configuration){
        this.configuration = configuration;
    }

    public void generateController(ControllerSetter settings) throws CodeGenerationException {
        generatedFiles = new Stack<File>();
        FreemarkerControllerSetter fcSettings = (FreemarkerControllerSetter)settings;
        generateDirs(fcSettings.getDestinationDirs());
        generateCode(fcSettings.getTemplateFileName(), fcSettings.getModelMap(), fcSettings.getDestinationFile());
    }

    private void generateCode(String templateFileName, Map<String, String> modelMap, File destinationFile) {
        Template template = loadTemplate(templateFileName);
        FileWriter writer = null;
        try {
            writer = new FileWriter(destinationFile);
            template.process(modelMap, writer);
            writer.flush();
            writer.close();
            System.out.println(destinationFile.getAbsolutePath()  + " created");
            generatedFiles.push(destinationFile);
        } catch (IOException e) {
            throw new CodeGenerationException("destincation file creation fail", e);
        } catch (TemplateException e) {
            throw new CodeGenerationException("template processing fail", e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
            }
        }
    }

    private void generateDirs(List<File> files) {
        for(File dir : files){
            boolean created = dir.mkdir();
            if(created)
                generatedFiles.push(dir);
        }
    }

    private Template loadTemplate(String templateFileName) {
        Template template = null;
        try {
            template = configuration.getTemplate(templateFileName);
        } catch (IOException e) {
            throw new CodeGenerationException("template file loading fail with [" + templateFileName + "]", e);
        }
        return template;
    }

    public void generateRepository(RepositorySetter setter) throws CodeGenerationException {
        generatedFiles = new Stack<File>();
        FreemarkerRepositorySetter frSetter = (FreemarkerRepositorySetter)setter;
        generateDirs(frSetter.getDestinationDirs());
        generateCode(frSetter.getInterfaceTemplateName(), frSetter.getModelMap(), frSetter.getInterfaceFile());
        generateCode(frSetter.getImplTemplateName(), frSetter.getModelMap(), frSetter.getImplFile());
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void deleteController() {
        deleteGeneratedFiles();
    }

    private void deleteGeneratedFiles() {
        while(!generatedFiles.empty()){
            File file = generatedFiles.pop();
            System.out.println(file.getAbsolutePath());
            boolean deleted = file.delete();
            if(deleted)
                System.out.println(file.getAbsolutePath() + " deleted");
            else
                System.out.println(file.getAbsolutePath() + " not deleted");
        }
    }

    public void deleteRepository() {
        deleteGeneratedFiles();
    }
}
