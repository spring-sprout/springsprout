package springsprout.domain.enumeration;

import springsprout.common.enumeration.PersistentEnum;

public  enum SeminarComerStatus implements PersistentEnum {

	ENROLLED(10,"신청",1),
	CONFIRMED(20,"확인",2),
	JOINED(30,"참석",3);
	
	private final Integer value;
	private final String descr;
	private final int order;
	
	private SeminarComerStatus(Integer value, String descr, int order) {
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
