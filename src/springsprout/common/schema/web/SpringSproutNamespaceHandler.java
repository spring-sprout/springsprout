package springsprout.common.schema.web;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 12. 11
 * Time: 오후 12:13:06
 */
public class SpringSproutNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("jstl-view-resolver", new JstlViewResolverBeanDefinitionParser());
    }
}
