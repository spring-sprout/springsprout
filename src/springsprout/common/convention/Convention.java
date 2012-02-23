package springsprout.common.convention;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

public class Convention {

	public static final String HOME_URL;

    static {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocations(new Resource[]{new ClassPathResource("/config.xml"), new ClassPathResource("/environment-config.xml")});
		Properties properties = null;
		try {
			factoryBean.afterPropertiesSet();
			properties  = factoryBean.getObject();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		if(properties != null) {
			HOME_URL = properties.getProperty("url");
		} else {
			HOME_URL = "http://springsprout.org/";
		}
	}

	
}
