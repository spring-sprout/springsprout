package springsprout.common.web.support;

import springsprout.common.web.URLBuilder;

public abstract class AbstractPaging implements Paging{

    protected int size; // number of items in one page.
    protected int currentPage; // currentPage number.
    protected int beginPage;
    protected int endPage;
    protected int totalRowsCount;
    protected int totalPage;
    protected int navigationPages;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage(){
    	return currentPage;
    }

    public int getTotalRowsCount() {
        return totalRowsCount;
    }

    public void setTotalRowsCount(int totalRowsCount) {
        this.totalRowsCount = totalRowsCount;
        calculatePage();
    }

    public void setNavigationPages(int navigationPages) {
		this.navigationPages = navigationPages;
	}

	public int getNavigationPages() {
		return navigationPages;
	}

    public int getFirstRowNumber() {
        return Math.max((currentPage - 1) * size ,0);
    }

    public int getBeginPage(){
    	return this.beginPage;
    }

    public int getEndPage(){
    	return this.endPage;
    }

    public int getTotalPage(){
    	return this.totalPage;
    }

    @Override
    public String toString() {
        URLBuilder builder = new URLBuilder();
        builder.addParameter("p_page", currentPage, "");
        builder.addParameter("p_size", size, "");
        return builder.toString();
    }

    public int[] getPageSizes(){
        return new int[]{1, 5, 10, 15, 20, 25, 30, 50, 100};
    }

    public void calculatePage(){
    	checkCurrentPageZero();
    	calculateTotalPage();
    	chechkCurrentPageOverTotalPage();
    	calculateBeginPage();
    	calculateEndPage();
    	chechkBeginPageMoreZero();
    	chechkEndPageOverTotalPage();
    }

	private void chechkBeginPageMoreZero() {
		if(this.beginPage < 1){
			this.endPage -= this.beginPage-1;
			this.beginPage -= this.beginPage-1;
		}
	}

	public void calculateTotalPage(){
    	this.totalPage = (int)Math.ceil((double)totalRowsCount/size);
    }

	public abstract void calculateBeginPage();

    public abstract void calculateEndPage();

    public abstract void chechkEndPageOverTotalPage();

    private void checkCurrentPageZero() {
    	this.currentPage = currentPage == 0 ? 1 :currentPage;
	}

	private void chechkCurrentPageOverTotalPage() {
    	this.currentPage = currentPage > totalPage ? totalPage : currentPage;
	}
}
