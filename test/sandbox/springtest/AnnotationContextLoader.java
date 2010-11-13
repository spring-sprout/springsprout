package sandbox.springtest;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.test.context.support.AbstractContextLoader;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keesun Baik
 * @author Seongyoon Kim
 */
public class AnnotationContextLoader extends AbstractContextLoader {

    private static final String JAVA_FILE_SUFFIX = ".java";
    private static final String APP_CONFIG_FILE_PREFIX = "AppConfig";

    @Override
    public String getResourceSuffix() {
        return APP_CONFIG_FILE_PREFIX + JAVA_FILE_SUFFIX;
    }

    @Override
    protected String[] generateDefaultLocations(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
		String suffix = getResourceSuffix();
		Assert.hasText(suffix, "Resource suffix must not be empty");
		return new String[] { clazz.getName() + suffix };
    }

    @Override
    protected String[] modifyLocations(Class<?> clazz, String... locations) {
		String[] modifiedLocations = new String[locations.length];
		for (int i = 0; i < locations.length; i++) {
            String path = locations[i];

            if(path.endsWith("/"))
                path = path.substring(0, path.length() - 1);

            if (path.startsWith("/")) {
                modifiedLocations[i] = ClassUtils.convertResourcePathToClassName(path.substring(1));
			}
			else if (!ResourcePatternUtils.isUrl(path)) {
                modifiedLocations[i] = getClassName(clazz, path);
            }
			else {
                throw new UnsupportedOperationException();
            }
		}
		return modifiedLocations;
	}

    private String getClassName(Class clazz, String path) {
        return ClassUtils.convertResourcePathToClassName(
            StringUtils.cleanPath(ClassUtils.classPackageAsResourcePath(clazz) + "/" + path));
    }

    public final ConfigurableApplicationContext loadContext(String... locations) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        prepareContext(context);
        createCustomBean((DefaultListableBeanFactory)context.getBeanFactory(), locations);
        customizeBeanFactory(context.getDefaultListableBeanFactory());
        context.register(getAppConfigClasses(context.getClassLoader(), locations));
        context.scan(getAppConfigPackages(context.getClassLoader(), locations));
        AnnotationConfigUtils.registerAnnotationConfigProcessors(context);
        customizeContext(context);
        context.refresh();
		context.registerShutdownHook();
        return context;
    }

    private String[] getAppConfigPackages(ClassLoader classLoader, String[] locations) {
        List<String> packages = new ArrayList<String>();
        for(String location : locations){
            if(!location.contains(JAVA_FILE_SUFFIX))
                packages.add(location);
        }
        return packages.toArray(new String[packages.size()]);
    }

    private Class<?>[] getAppConfigClasses(ClassLoader classLoader, String[] locations) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for(String location : locations){
            if(location.contains(JAVA_FILE_SUFFIX))
                classes.add(ClassUtils.forName(location.replace(JAVA_FILE_SUFFIX, ""), classLoader));
        }
        return classes.toArray(new Class<?>[classes.size()]);
    }

    protected void createCustomBean(DefaultListableBeanFactory context, String[] locations) throws ClassNotFoundException {
    }

    /**
	 * Prepare the {@link org.springframework.context.support.GenericApplicationContext} created by this ContextLoader.
	 * Called <i>before</> bean definitions are read.
	 * <p>The default implementation is empty. Can be overridden in subclasses to
	 * customize GenericApplicationContext's standard settings.
	 * @param context the context for which the BeanDefinitionReader should be created
	 * @see #loadContext
	 * @see org.springframework.context.support.GenericApplicationContext#setResourceLoader
	 * @see org.springframework.context.support.GenericApplicationContext#setId
	 */
    protected void prepareContext(GenericApplicationContext context) {

    }

    /**
	 * Customize the internal bean factory of the ApplicationContext created by
	 * this ContextLoader.
	 * <p>The default implementation is empty but can be overridden in subclasses
	 * to customize DefaultListableBeanFactory's standard settings.
	 * @param beanFactory the bean factory created by this ContextLoader
	 * @see #loadContext
	 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#setAllowBeanDefinitionOverriding(boolean)
	 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#setAllowEagerClassLoading(boolean)
	 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#setAllowCircularReferences(boolean)
	 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#setAllowRawInjectionDespiteWrapping(boolean)
	 */
    protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {

    }

    /**
	 * Customize the {@link GenericApplicationContext} created by this ContextLoader
	 * <i>after</i> bean definitions have been loaded into the context but
	 * before the context is refreshed.
	 * <p>The default implementation is empty but can be overridden in subclasses
	 * to customize the application context.
	 * @param context the newly created application context
	 */
	protected void customizeContext(GenericApplicationContext context) {
	}

}
