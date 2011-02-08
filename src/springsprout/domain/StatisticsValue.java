package springsprout.domain;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 7
 * Time: 오전 1:08:56
 */
public class StatisticsValue {

    int index;

    int columnIndex;

    Object value;

    public StatisticsValue(int rowIndex, int columnIndex, Object value) {
        this.index = rowIndex;
        this.columnIndex = columnIndex;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
