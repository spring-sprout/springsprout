package springsprout.common.enumeration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class PersistentEnumUtil {
	
	public static <E extends PersistentEnum> E valueOf(Class<E> enumType, Object value){
		E[] es = enumType.getEnumConstants();
		for(E e : es){
			if( e.getValue() == value || e.getValue().equals(value))
				return e;
		}
		return null;
	}

	public static <E extends PersistentEnum> List<E> sortedListOf(Class<E> enumType) {
		E[] es = enumType.getEnumConstants();
		List<E> ess = Arrays.asList(es);
		Collections.sort(ess, new PersistentEnumComparator<PersistentEnum>());
		return ess;
	}

}
