package springsprout.domain;

import springsprout.common.util.DateUtils;
import springsprout.domain.enumeration.StatisticsChartType;
import springsprout.domain.enumeration.StatisticsColumnType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 6
 * Time: 오후 11:38:05
 */
public class Statistics {

    private String chartName;

    private StatisticsChartType chartType;

    private List<StatisticsColumn> statisticsColumns;

    private List<StatisticsValue> statisticsValues;

    private List<StatisticsRow> statisticsRows;

    public Statistics(StatisticsChartType chartType, String chartName) {
        this.chartType = chartType;
        this.chartName = chartName;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public StatisticsChartType getChartType() {
        return chartType;
    }

    public void setChartType(StatisticsChartType chartType) {
        this.chartType = chartType;
    }

    public List<StatisticsColumn> getStatisticsColumns() {
        if(this.statisticsColumns == null)
            this.statisticsColumns = new ArrayList<StatisticsColumn>();
        return statisticsColumns;
    }

    public void setStatisticsColumns(List<StatisticsColumn> statisticsColumns) {
        this.statisticsColumns = statisticsColumns;
    }

    public List<StatisticsValue> getStatisticsValues() {
        if(this.statisticsValues == null)
            this.statisticsValues = new ArrayList<StatisticsValue>();
        return statisticsValues;
    }

    public void setStatisticsValues(List<StatisticsValue> statisticsValues) {
        this.statisticsValues = statisticsValues;
    }

    public int getRow() {
        return getStatisticsRows().size();
    }

    public int getColumnCount(){
        return getStatisticsColumns().size();
    }

    public int getValueCount(){
        return getStatisticsValues().size();
    }

    public List<StatisticsRow> getStatisticsRows() {
        if(this.statisticsRows == null)
            this.statisticsRows = new ArrayList<StatisticsRow>();
        return statisticsRows;
    }

    public void setStatisticsRows(List<StatisticsRow> statisticsRows) {
        this.statisticsRows = statisticsRows;
    }

    public Statistics withColumn(StatisticsColumnType columnType, String columnLable) {
        StatisticsColumn statisticsColumn = new StatisticsColumn(columnType, columnLable);
        getStatisticsColumns().add(statisticsColumn);
        return this;
    }

    public void addRow(Object... values) {
        List<StatisticsValue> sValues = new ArrayList<StatisticsValue>();

        //add values
        for(int i = 0 ; i < values.length ; i++){
            StatisticsValue value = new StatisticsValue(getStatisticsRows().size(), i, values[i]);
            getStatisticsValues().add(value);
            sValues.add(value);
        }

        //add row
        StatisticsRow sRow = new StatisticsRow(sValues);
        getStatisticsRows().add(sRow);
    }

    /**
     *
    {
     cols:
         [
             {id: 'task', label: 'Task', type: 'string'},
             {id: 'hours', label: 'Hours per Day', type: 'number'}
         ],

     rows:
         [
            {c:
                [
                    {v: 'Work', p: {'style': 'border: 7px solid orange;'}}, {v: 11}
                ]
            },

            {c:
                [
                    {v: 'Eat'},
                    {v: 2}
                ]
            },

            {c:[{v: 'Commute'}, {v: 2, f: '2.000'}]}
         ]
     }
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        builder.append("cols:");
        builder.append("[");
        for(int i = 0 ; i < getStatisticsColumns().size() ; i++){
            StatisticsColumn c = getStatisticsColumns().get(i);
            builder.append("{");
            builder.append("id:");
            builder.append("'");
            builder.append(c.getId());
            builder.append("'");
            builder.append(", ");
            builder.append("label:");
            builder.append("'");
            builder.append(c.getColumnLabel());
            builder.append("'");
            builder.append(", ");
            builder.append("type:");
            builder.append("'");
            builder.append(c.getColumnType().getDescr());
            builder.append("'");
            builder.append("}");
            if(i != getStatisticsColumns().size() - 1)
                builder.append(",");
        }
        builder.append("]");
        builder.append(",");
        builder.append("rows:");
        builder.append("[");
        for(int j = 0 ; j < getStatisticsRows().size() ; j++){
            StatisticsRow row = getStatisticsRows().get(j);
            builder.append("{");
            builder.append("c:");
            builder.append("[");
            for(int k = 0 ; k < row.getValues().size() ; k++){
                StatisticsValue value = row.getValues().get(k);
                builder.append("{");
                builder.append(getValueByColumnType(value.getValue(), getStatisticsColumns().get(value.getColumnIndex())));
                builder.append("}");
                if (k != row.getValues().size() - 1)
                    builder.append(",");
            }
            builder.append("]");
            builder.append("}");
            if(j != getStatisticsRows().size() - 1)
                builder.append(",");
        }

        builder.append("]");
        builder.append("}");

        return builder.toString();
    }

    /**
     * http://code.google.com/intl/ko-KR/apis/visualization/documentation/reference.html#dateformatter
     *
     * ex) v: new Date(2008, 3, 30, 0, 31, 26), f: '4/30/08 12:31 AM'
     *
     * @param value
     * @param statisticsColumn
     * @return
     */
    private String getValueByColumnType(Object value, StatisticsColumn statisticsColumn) {
        StringBuilder result = new StringBuilder("v:");
        if(value == null)
            return result.append("''").toString();

        switch (statisticsColumn.getColumnType()) {
            case NUMBER:
                result.append((Number)value);
                break;
            case BOOLEAN:
                result.append((Boolean)value);
                break;
            case STRING:
                result.append("'" + ((String)value) + "'");
                break;
            case DATE:
                Date dateValue = (Date)value;
                result.append("new Date(" + DateUtils.makeJsDate(dateValue) + ", 0, 0, 0)");
                result.append(", f: '");
                result.append(DateUtils.make년월일(dateValue));
                result.append("'");
        }
        return result.toString();
    }
}
