package springsprout.common.convention;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

public class Convention {

	public static final String HOME_URL;

    static {
		Properties properties = new Properties();
		try {
			properties.load(new ClassPathResource("/system.properties").getInputStream());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}

		HOME_URL = properties.getProperty("url");
	}


	
}
