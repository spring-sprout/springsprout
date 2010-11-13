package springsprout.common.extjs;

/**
 * ExtJS의 기본 페이징 변수를 모아둔 크리테리아입니다.
 * 다른 검색 조건들은 이 클래스를 상속 받아 처리해주시면 됩니다.
 *
 * <table>
 *  <tr><td>변수명</td><td>설명</td></tr>
 *  <tr><td>start</td><td>레코드의 시작 위치</td></tr>
 *  <tr><td>limit</td><td>한번에 가지고 올 레코드의 갯수</td></tr>
 *  <tr><td>sort</td><td>정렬시 기준이 되는 필드명</td></tr>
 *  <tr><td>dir</td><td>정렬 방향[오름 차순/내림 차순] (ASC/DESC)</td></tr>
 * </table>
 * 
 * start부터 limit만큼 sort 필드를 기준으로 dir 방향으로 정렬된 레코드를 가지고 옵니다.
 * @since 2010.04.22
 * @author miracle
 */
public class BaseCriteria {
    /**
     * 한번에 가지고 올 레코드의 갯수
     */
    private int limit;
    /**
     * 한번에 가지고 올 레코드의 갯수
     */
    private int start;
    /**
     * 정렬시 기준이 되는 필드명
     */
    private String sort;
    /**
     * 정렬 방향[오름 차순/내림 차순] (ASC/DESC)
     */
    private String dir;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "BaseCriteria{" +
                "limit=" + limit +
                ", start=" + start +
                ", sort='" + sort + '\'' +
                ", dir='" + dir + '\'' +
                '}';
    }
}
