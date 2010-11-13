package springsprout.common.web.support;

/**
 * Created by IntelliJ IDEA.
 * User: arawn
 * Date: 2010. 4. 16
 * Time: 오후 4:01:42
 * To change this template use File | Settings | File Templates.
 */
public class ExtJsGridPaging implements Paging {

    protected int size;
    protected int currentPage;
    protected int totalRowsCount;
    
    public void setStart(int start) {
        this.currentPage = start;
    }

    public void setLimit(int limit) {
        this.size = limit;
    }

    public void setTotalRowsCount(int totalRowsCount) {
        this.totalRowsCount = totalRowsCount;
    }
    public int getTotalRowsCount() {
        return totalRowsCount;
    }

    public int getFirstRowNumber() {
        return currentPage;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getCurrentPage() {
        return currentPage;
    }

    @Deprecated
    public int getTotalPage() {
        return 0;
    }

    @Deprecated
    public int getEndPage() {
        return 0;
    }

    @Deprecated
    public int getNavigationPages() {
        return 0;
    }

}
