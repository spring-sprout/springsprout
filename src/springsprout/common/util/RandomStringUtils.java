package springsprout.common.util;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class RandomStringUtils {
	
	private RandomStringUtils() {}
	
	public static String getRandomMD5() {
		String result = null;
		try {
			result = DigestUtils.md5DigestAsHex(String.valueOf(new Random().nextInt(1000000)).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("can not make rand string");
		}
		return result;
	}
}