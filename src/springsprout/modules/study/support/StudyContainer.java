package springsprout.modules.study.support;

import springsprout.domain.Study;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Miracle
 * Date: 2010. 4. 22
 * Time: 오후 10:53:40
 * To change this template use File | Settings | File Templates.
 */
public class StudyContainer {
    private List<Study> list;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Study> getList() {
        return list;
        
    }

    public void setList(List<Study> list) {
        this.list = list;
    }
}
