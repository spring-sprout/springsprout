package springsprout.modules.term.support;

import springsprout.common.web.URLBuilder;
import springsprout.common.web.support.SearchParam;
import springsprout.domain.DevTerm;
import springsprout.domain.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 1. 12
 * Time: 오후 3:21:57
 */
public class DevTermSearchParam extends SearchParam {

    private String keyword;
    private Member createdBy;
    private Member korTermCreatedBy;
    private String howRU;
    private Member favMember;

	public DevTermSearchParam() {
	}

	public DevTermSearchParam(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Member getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Member createdBy) {
        this.createdBy = createdBy;
    }

    public String getHowRU() {
        return howRU;
    }

    public void setHowRU(String howRU) {
        this.howRU = howRU;
    }

    @Override
    public String toString() {
        URLBuilder builder = new URLBuilder();
        builder.addParameter("s_keyword", keyword, "");
        return builder.toString();
    }

    public Member getKorTermCreatedBy() {
        return korTermCreatedBy;
    }

    public void setKorTermCreatedBy(Member korTermCreatedBy) {
        this.korTermCreatedBy = korTermCreatedBy;

    }

    public Member getFavMember() {
        return favMember;
    }

    public void setFavMember(Member favMember) {
        this.favMember = favMember;
    }

    public Integer[] getFavTermKeys() {
        List<Integer> devTermIdList = new ArrayList<Integer>();
        if(getFavMember() == null || getFavMember().getFavoriteTerms() == null)
            return null;
        for(DevTerm devTerm : getFavMember().getFavoriteTerms().keySet()){
            devTermIdList.add(devTerm.getId());
        }
        return devTermIdList.toArray(new Integer[devTermIdList.size()]); 
    }
}
