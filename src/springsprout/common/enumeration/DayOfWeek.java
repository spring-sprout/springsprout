package springsprout.common.enumeration;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 11. 21
 * Time: 오후 9:38:00
 */
public enum DayOfWeek {

    MONDAY(0, "월요일"),
    TUESDAY(1, "화요일"),
    WEDNESDAY(2, "수요일"),
    THURSDAY(3, "목요일"),
    FRIDAY(4, "금요일"),
    SATURDAY(5, "토요일"),
    SUNDAY(6, "일요일");

    private String title;
    private int order;

    DayOfWeek(int order, String title) {
        this.title = title;
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public int getOrder() {
        return order;
    }
}
