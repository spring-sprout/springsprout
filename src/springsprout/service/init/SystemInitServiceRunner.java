package springsprout.service.init;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 4. 19
 * Time: 오후 1:43:02
 */
public class SystemInitServiceRunner {

    public static void main(String[] args){
        ApplicationContext ac = new ClassPathXmlApplicationContext("springsprout/service/init/applicationContext.xml");
        NotificationOffInitService notificationOffInitService = ac.getBean(NotificationOffInitService.class);
        DefaultRoleSetupService defaultRoleSetupService = ac.getBean(DefaultRoleSetupService.class);

        assert defaultRoleSetupService != null;
        defaultRoleSetupService.addDefaultRoles();
        defaultRoleSetupService.addDefaultUsers();

        assert notificationOffInitService != null;
        notificationOffInitService.notificationOffAllMember();
    }

}
