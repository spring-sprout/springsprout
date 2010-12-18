package springsprout.domain.enumeration;

import springsprout.common.enumeration.PersistentEnum;

public enum SurbeyStatus implements PersistentEnum  {
	SINGLE(10, "단답형", 1),
	MULTIPLE(20, "다답형", 2);

	private final Integer value;
    private final String descr;
    private final int order;

	
	private SurbeyStatus(Integer value, String descr, int order) {
		this.value = value;
		this.descr = descr;
		this.order = order;
	}
	
	public String getDescr() {
		return descr;
	}

	public Object getValue() {
		return value;
	}

	public int getOrder() {
		return order;
	}

}
