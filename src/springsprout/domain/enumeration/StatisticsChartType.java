package springsprout.domain.enumeration;

import springsprout.common.enumeration.PersistentEnum;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 7
 * Time: 오전 9:54:44
 */
public enum StatisticsChartType implements PersistentEnum {

	MOTION(10,"motion",1),
	PIE(20,"pie",2),
	BAR(30,"bar",3),
	AREA(40,"area",4);

	private final Integer value;
	private final String descr;
	private final int order;

	private StatisticsChartType(Integer value, String descr, int order) {
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