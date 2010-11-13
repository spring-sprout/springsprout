package springsprout.common.web.support;

public class StandardPaging extends AbstractPaging {

	public StandardPaging() {
		this.size  = DEFAULT_SIZE;
		this.navigationPages = DEFAULT_NAVIGATION_PAGES;
	}
	public StandardPaging(int size){
		this.size  = size;
		this.navigationPages = DEFAULT_NAVIGATION_PAGES;
	}
	
	public StandardPaging(int size, int navigationPages){
		this.size  = size;
		this.navigationPages = navigationPages;
	}
	
	@Override
	public void calculateBeginPage() {		 
		 this.beginPage = Math.max(currentPage - ((currentPage -1)%navigationPages),1);
	}

	@Override
	public void calculateEndPage() {
		this.endPage = currentPage+ (navigationPages-(currentPage%navigationPages) == navigationPages ? 0:navigationPages-(currentPage%navigationPages));
	}
	
	@Override
	public void chechkEndPageOverTotalPage() {
    	this.endPage = this.endPage > this.totalPage ? this.totalPage : this.endPage; 
    }
}
