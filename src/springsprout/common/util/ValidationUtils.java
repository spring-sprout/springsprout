package springsprout.common.util;

import java.util.List;
import java.util.Set;

public class ValidationUtils {

	private ValidationUtils() {}
	
	public static boolean isNull( Object obj) {
		if ( obj == null)
			return true;
		else 
			return false;
	}
	
	public static boolean isNotNull( Object obj) {
		return !isNull(obj);
	}
	
	public static boolean isNull( List<?> list) {
		if ( list.isEmpty() || list == null)
			return true;
		return false;
	}
	
	public static boolean isNull( Set<?> set) {
		if ( set.isEmpty() || set == null)
			return true;
		return false;
	}
	
	public static boolean isNull( Object ...objs) {
		for (Object obj : objs) {
			if ( isNull(obj)) return true;
		}
		return false;
	}
	
	public static boolean isNotNull( Object ...objs) {
		return !isNull(objs);
	}
}
