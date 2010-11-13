package springsprout.common.enumeration;

import java.util.Comparator;

public class PersistentEnumComparator<E extends PersistentEnum> implements Comparator<E> {

	public int compare(E e1, E e2) {
		return e1.getOrder() - e2.getOrder();
	}

}
