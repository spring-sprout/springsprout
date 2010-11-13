package springsprout.domain.enumeration;

import springsprout.common.enumeration.PersistentEnum;

public enum SeminarStatus implements PersistentEnum {

    READY(10, "준비", 1),
    ENROLLMENT_STARTED(20, "접수중", 2),
    ENROLLMENT_ENDED(30, "접수완료", 3),
    SEMINAR_STARTED(40, "세미나 시작", 4),
    SEMINAR_ENDED(50, "세미나 종료", 5);

    private final Integer value;
    private final String desc;
    private final int order;

    private SeminarStatus(Integer value, String desc, int order) {
        this.value = value;
        this.desc = desc;
        this.order = order;
    }

    public String getDescr() {
        return desc;
    }

    public int getOrder() {
        return order;
    }

    public Integer getValue() {
        return value;
    }

}
