package springsprout.common.web.support;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PostPaging {
	public static String START						= 	"start";
	public static String LIMIT						= 	"limit";
	public static String TOTALCOUNT					= 	"totalCount";
	
	private int limit;
	private int start;
	private int now;
	private int totalCount;
	private int totalPage;
	private List<Integer> paging;
	
	public PostPaging() {}
	
	public PostPaging( int limit, int start, int totalCount) {
		super();
		this.limit = limit;
		this.start = start * this.limit;
		this.now = this.start / this.limit + 1;;
		this.totalCount = totalCount;
		this.totalPage = getTotalPage( totalCount, this.limit);
		this.paging = getPaging(now, totalPage);
	}
	
	public static int getTotalPage( int total, int limit) {
		int result = total/limit;
		if ( (total % limit) != 0)
			return ++result;
		else
			return result;
	}
	
	public static List<Integer> getPaging( int now, int total)
	{
		List<Integer> arrPaging = new ArrayList<Integer>();
		if ( now <= 5)
		{
			for ( int i = 0, end = total; i < end; i++)
			{
				if ( i >= 10) break;
				arrPaging.add( i + 1);
			}
		} else {
			for ( int i = now - 5, j = 0; i < now + 6; i++, j++)
			{
				if ( i >= total) break;
				arrPaging.add( i + 1);
			}
		}
		return arrPaging;
	}
	
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

	public int getNow() {
		return now;
	}

	public void setNow(int now) {
		this.now = now;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setPaging(List<Integer> paging) {
		this.paging = paging;
	}

	public List<Integer> getPaging() {
		return paging;
	}
}
