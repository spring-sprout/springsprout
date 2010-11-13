package springsprout.common.web.support;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import springsprout.common.web.support.StandardPaging;

public class StandardPagingTest {

	StandardPaging standardPaging;

	@Before
	public void setUp(){
		standardPaging = new StandardPaging();
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
		assertEquals(totalPage, standardPaging.getTotalPage());
	}
	
	@Test
	public void beginPage() throws Exception {
		assertBeginPage(5,17,16);
		assertBeginPage(10,17,11);
		
		assertBeginPage(5,1,1);
		assertBeginPage(10,1,1);

		assertBeginPage(5,23,21);
		assertBeginPage(10,13,11);
		
	}

	/*
	 * display row size : 10 
	 * totalRowCount : 216 
	 * 으로 설정.
	 */
	private void assertBeginPage(int navigationPages,int page, int beginPage) {
		setPagingInfo(10,navigationPages,page , 216);
		assertEquals(beginPage, standardPaging.getBeginPage());
	}
	
	private void setPagingInfo(int size, int navigationPages, int page,int totalRowsCount) {
		standardPaging.setSize(size);
		standardPaging.setNavigationPages(navigationPages);
		standardPaging.setCurrentPage(page);
		standardPaging.setTotalRowsCount(totalRowsCount);
	}

	@Test
	public void endPage() throws Exception {
		assertEndPage(5,  10, 9, 1, 2);
		assertEndPage(5,   5, 9, 1, 2);
		assertEndPage(10, 10, 123, 3, 10);
		assertEndPage(10,  5, 123, 3, 5);
	}

	private void assertEndPage(int size, int navigationPages, int totalRowsCount, int page, int endPage) {
		setPagingInfo(size,navigationPages,page , totalRowsCount);
		assertEquals(endPage, standardPaging.getEndPage());
	}

}
