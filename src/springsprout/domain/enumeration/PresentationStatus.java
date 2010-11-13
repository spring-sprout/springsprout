package springsprout.domain.enumeration;

import springsprout.common.enumeration.PersistentEnum;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 8
 * Time: 오전 8:27:29
 * To change this template use File | Settings | File Templates.
 */
public enum PresentationStatus implements PersistentEnum {

	OPENED(10, "준비", 1),
	STARTED(20, "시작", 2),
    ENDED(30, "종료", 3),
    DELETED(40, "삭제", 4),
    CANCLED(50, "취소", 5);

	private final Integer value;
	private final String descr;
	private final int order;

	private PresentationStatus(Integer value, String descr, int order) {
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