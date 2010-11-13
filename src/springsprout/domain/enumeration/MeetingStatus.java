package springsprout.domain.enumeration;

import springsprout.common.enumeration.PersistentEnum;

public enum MeetingStatus implements PersistentEnum {
	
	OPEN(10, "개설", 1), 
	DELETED(30, "삭제", 3), 
	ENDED(20, "종료", 2),
	UPDATED(40, "수정", 4);
	
	private final Integer value;
	private final String descr;
	private final int order;
	
	private MeetingStatus(Integer value, String descr, int order) {
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