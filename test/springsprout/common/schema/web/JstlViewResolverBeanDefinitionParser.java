package springsprout.common.schema.web;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.w3c.dom.Element;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 12. 11
 * Time: 오전 11:42:51
 */
public class JstlViewResolverBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return InternalResourceViewResolver.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        builder.addPropertyValue("viewClass", JstlView.class);

        String prefix = element.getAttribute("prefix");
        builder.addPropertyValue("prefix", prefix);
        String suffix = element.getAttribute("suffix");
        builder.addPropertyValue("suffix", suffix);
    }
}
