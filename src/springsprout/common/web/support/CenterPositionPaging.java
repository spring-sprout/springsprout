package springsprout.common.web.support;

public class CenterPositionPaging extends AbstractPaging {

	public CenterPositionPaging() {
		this.size  = DEFAULT_SIZE;
		this.navigationPages = DEFAULT_NAVIGATION_PAGES;
	}
	public CenterPositionPaging(int size){
		this.size  = size;
		this.navigationPages = DEFAULT_NAVIGATION_PAGES;
	}
	
	public CenterPositionPaging(int size, int navigationPages){
		this.size  = size;
		this.navigationPages = navigationPages;
	}
	
	@Override
	public void calculateBeginPage() {		 
		 this.beginPage = currentPage - (int)Math.floor((navigationPages%2 == 0?navigationPages-1:navigationPages)/2);
	}

	@Override
	public void calculateEndPage() {
		this.endPage = currentPage + (int)Math.ceil(navigationPages/2);
	}
	
	@Override
	public void chechkEndPageOverTotalPage() {
		if(this.endPage > this.totalPage){
    		this.beginPage -= this.endPage - this.totalPage;
    		this.endPage = this.totalPage;
    	}
		this.beginPage = Math.max(this.beginPage, 1);
    }
}
