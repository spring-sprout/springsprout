package sandbox.springtest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.BeansException;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 18
 * Time: 오후 12:06:11
 */
public class StaticMemberClassLoader extends AnnotationContextLoader {

    @Override
    protected void createCustomBean(DefaultListableBeanFactory context, String[] locations) throws ClassNotFoundException {
        for(String string : locations){
            Class beanClass = ClassUtils.forName(string, getClass().getClassLoader());
            context.registerBeanDefinition(beanClass.getSimpleName(), BeanDefinitionBuilder.rootBeanDefinition(beanClass).getBeanDefinition());
        }
    }

    @Override
    protected String[] generateDefaultLocations(Class<?> clazz) {
        Class[] classes = clazz.getDeclaredClasses();
        List<String> modifiedLocations = new ArrayList<String>();
        for (int i = 0; i < classes.length; i++) {
            Class klass = classes[i];
            if(klass.getModifiers() == Modifier.STATIC)
                modifiedLocations.add(klass.getName());
        }
        return modifiedLocations.toArray(new String[modifiedLocations.size()]);
    }

}
