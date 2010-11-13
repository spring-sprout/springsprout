package springsprout.domain.enumeration;

import springsprout.common.enumeration.PersistentEnum;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 7
 * Time: 오전 1:11:03
 */
public enum StatisticsColumnType implements PersistentEnum {

	BOOLEAN(10,"boolean",1),
	STRING(20,"string",2),
	NUMBER(30,"number",3),
	DATE(40,"date",4);

	private final Integer value;
	private final String descr;
	private final int order;

	private StatisticsColumnType(Integer value, String descr, int order) {
		this.value = value;
		this.descr = descr;
		this.order = order;
	}

	public Integer getValue() {
		return value;
	}
	public String getDescr() {
		return descr;
	}
	public int getOrder(){
		return order;
	}

}