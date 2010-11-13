package springsprout.domain.enumeration;

import springsprout.common.enumeration.PersistentEnum;

public enum StudyStatus implements PersistentEnum {
	
	OPEN(10, "개설", 1), 
	STARTED(20, "진행중", 2), 
	UPDATED(40, "수정", 4), 
	DELETED(50, "삭제", 5), 
	ENDED(30, "종료", 3);
	
	private final Integer value;
	private final String descr;
	private final int order;
	
	private StudyStatus(Integer value, String descr, int order) {
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