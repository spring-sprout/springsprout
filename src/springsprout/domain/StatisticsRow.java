package springsprout.domain;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 7
 * Time: 오후 5:30:38
 */
public class StatisticsRow {

    int index;
    List<StatisticsValue> values;

    public StatisticsRow(List<StatisticsValue> statisticsValues) {
        this.values = statisticsValues;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<StatisticsValue> getValues() {
        return values;
    }

    public void setValues(List<StatisticsValue> values) {
        this.values = values;
    }

}
