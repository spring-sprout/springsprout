package springsprout.common.web.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestBindingException;


public abstract class AbstractModulesContext {

	private Paging paging;

	private OrderParam orderParam;
	
	private SearchParam searchParam = new SearchParam();

	public void setOrderParam(OrderParam orderParam) {
		this.orderParam = orderParam;
	}

	public OrderParam getOrderParam() {
		return orderParam;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setSearchParam(SearchParam searchParam) {
		this.searchParam = searchParam;
	}

	public SearchParam getSearchParam() {
		return searchParam;
	}
	
	public void setTotalRowsCount(int totalRowsCount) {
		paging.setTotalRowsCount(totalRowsCount);
	}
	
	public abstract String getRedirectToListURL();
	
	public abstract String getAllParam();
	
	public abstract void bindParams(HttpServletRequest request) throws ServletRequestBindingException;

}
