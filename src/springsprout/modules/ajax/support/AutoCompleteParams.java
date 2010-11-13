package springsprout.modules.ajax.support;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 10. 29
 * Time: 오후 5:36:50
 */
public class AutoCompleteParams {

    String keyword;

    Boolean selectFirst;

    public String getKeyword() {
        try {
            if(this.keyword != null){
                this.keyword = URLDecoder.decode(this.keyword, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Boolean getSelectFirst() {
        return selectFirst;
    }

    public void setSelectFirst(Boolean selectFirst) {
        this.selectFirst = selectFirst;
    }

}