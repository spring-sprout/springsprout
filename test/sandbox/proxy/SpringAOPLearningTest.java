package sandbox.proxy;

import org.junit.Test;
import org.junit.Before;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 5
 * Time: 오전 10:13:58
 */
public class SpringAOPLearningTest {

    interface Service {
        public void hi();
        public void hi2();
    }

    class ServiceImpl implements Service {
        public void hi(){
            System.out.println("my business");
        }

        public void hi2() {
            System.out.println("my2");
        }
    }

    class ServiceLogging implements Service {

        Service serviceImpl;

        ServiceLogging(Service serviceImpl) {
            this.serviceImpl = serviceImpl;
        }

        public void hi() {
            System.out.println("before hi");
            serviceImpl.hi();
            System.out.println("after hi");
        }

        public void hi2(){
            System.out.println("hi2");
        }
    }

    class ServiceLogginHandler implements InvocationHandler {

        Object target;

        ServiceLogginHandler(Object target) {
            this.target = target;
        }

        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            System.out.println("before hi");
            method.invoke(target, objects);
            System.out.println("after hi");
            return null;
        }
    }

    class ServiceLoggingAdvice implements MethodInterceptor {
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println("before hi");
            invocation.proceed();
            System.out.println("after hi");
            return null;  
        }
    }

    class ServiceLoggingPointcur extends NameMatchMethodPointcut {

        ServiceLoggingPointcur() {
            setMappedName("hi");
        }
    }

    Service service;
    ProxyFactoryBean proxyFactoryBean;


    @Before
    public void setUp(){
//        service = new ServiceLogging(new ServiceImpl());
        proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(new ServiceImpl());
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(
                new ServiceLoggingPointcur(), 
                new ServiceLoggingAdvice()));
    }

    @Test
    public void logging(){
//        Class[] proxyInterfaces = new Class[] { Service.class };
//        Service service =
//                (Service) Proxy.newProxyInstance(
//                this.getClass().getClassLoader(),
//                proxyInterfaces,
//                new ServiceLogginHandler(new ServiceImpl()));
//        service.hi();
        Service service = (Service) proxyFactoryBean.getObject();
        service.hi();
        service.hi2();
    }

}
