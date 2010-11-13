package springsprout.domain.enumeration;

import springsprout.common.enumeration.PersistentEnum;

public enum MemberStatus implements PersistentEnum {

	JOIN_WAIT(10, "가입대기", 1), 
	JOIN(20, "가입", 2), 
	OUT(30, "탈퇴", 3),
	COMPULSORY_OUT(40, "강제퇴출", 4);

	private final Integer value;
	private final String descr;
	private final int order;
	
	private MemberStatus(Integer value, String descr, int order) {
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
