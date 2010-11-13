package springsprout.common.web.support;

public interface Paging {
	  public static final int DEFAULT_SIZE = 10;
	  public static final int SIZE_5 = 10;
	  public static final int DEFAULT_PAGE = 1;
	  public static final int DEFAULT_NAVIGATION_PAGES = 10;
	  
	  public void setTotalRowsCount(int totalRowsCount);

	  public int getFirstRowNumber();

	  public int getSize();
	  
	  public void setSize(int size);
	  
	  public void setCurrentPage(int currentPage);
	  
	  public int getTotalPage();
	  
	  public int getEndPage();
	  
	  public int getCurrentPage();
	  
	  public int getNavigationPages();
	  
}
