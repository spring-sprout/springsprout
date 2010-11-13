package springsprout.domain;

import springsprout.domain.enumeration.StatisticsColumnType;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 7
 * Time: 오전 1:08:40
 */
public class StatisticsColumn {

    private StatisticsColumnType columnType;

    private String columnLabel;


    public StatisticsColumn(StatisticsColumnType columnType, String columnLabel) {
        this.columnType = columnType;
        this.columnLabel = columnLabel;
    }

    public StatisticsColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(StatisticsColumnType columnType) {
        this.columnType = columnType;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public String getId() {
        return columnLabel;
    }
}
