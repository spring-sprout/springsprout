package springsprout.domain.enumeration;

import springsprout.common.enumeration.PersistentEnum;

public enum ResourceType implements PersistentEnum {

	URL(10, "URL", 1), 
	FILE(20, "파일", 2);
	
	private final Integer value;
	private final String descr;
	private final int order;
	
	private ResourceType(Integer value, String descr, int order) {
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
