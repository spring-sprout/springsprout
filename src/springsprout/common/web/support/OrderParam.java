package springsprout.common.web.support;

import springsprout.common.util.StringUtils;
import springsprout.common.web.URLBuilder;

public class OrderParam {

    private String field;

    private String direction;

    public String getField() {
//        if(this.field == null || this.field.isEmpty())
//            this.field = "created";
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDirection() {
        if( StringUtils.isEmpty( direction))
            direction = "desc";
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        URLBuilder builder = new URLBuilder();
        builder.addParameter("o_field", field, "");
        builder.addParameter("o_direction", direction, "");
        return builder.toString();
    }

}
