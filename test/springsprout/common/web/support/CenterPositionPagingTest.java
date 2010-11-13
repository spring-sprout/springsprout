package springsprout.common.web.support;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CenterPositionPagingTest {

	CenterPositionPaging centerPositionPaging;
	@Before
	public void setUp(){
		centerPositionPaging = new CenterPositionPaging();
	}
	
	/*
	 * navigationPages : 10 
	 * currentPage : 1 
	 * 으로 설정.
	 */
	
	@Test
	public void totalPage() throws Exception {
		assertTotalPage(5, 9, 2);
		assertTotalPage(10, 9, 1);
		assertTotalPage(5, 20, 4);
	}

	private void assertTotalPage(int size, int totalRowsCount, int totalPage) {
		setPagingInfo(size,10,1,totalRowsCount);
		assertEquals(totalPage, centerPositionPaging.getTotalPage());
	}
	
	@Test
	public void beginPage() throws Exception {
		assertBeginPage(5,17,15);
		assertBeginPage(10,17,13);
		
		assertBeginPage(5,1,1);
		assertBeginPage(10,1,1);

		assertBeginPage(5,22,18);
		assertBeginPage(10,14,10);
		
	}

	/*
	 * display row size : 10 
	 * totalRowCount : 216 
	 * 으로 설정.
	 */
	private void assertBeginPage(int navigationPages,int page, int beginPage) {
		setPagingInfo(10,navigationPages,page , 216);
		assertEquals(beginPage, centerPositionPaging.getBeginPage());
	}
	
	private void setPagingInfo(int size, int navigationPages, int page,int totalRowsCount) {
		centerPositionPaging.setSize(size);
		centerPositionPaging.setNavigationPages(navigationPages);
		centerPositionPaging.setCurrentPage(page);
		centerPositionPaging.setTotalRowsCount(totalRowsCount);
	}

	@Test
	public void endPage() throws Exception {
		assertEndPage(1,  10, 15, 5, 10);
		assertEndPage(1,  10, 15, 6, 11);
		assertEndPage(1,  10, 15, 15, 15);
		assertEndPage(1,  5, 15, 5, 7);
		assertEndPage(1,  9, 15, 6, 10);
		assertEndPage(1,  9, 15, 5, 9);
	}

	private void assertEndPage(int size, int navigationPages, int totalRowsCount, int page, int endPage) {
		setPagingInfo(size,navigationPages,page , totalRowsCount);
		assertEquals(endPage, centerPositionPaging.getEndPage());
	}

}
