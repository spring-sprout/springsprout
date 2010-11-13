package springsprout.modules.member.support;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import springsprout.common.web.support.OrderParam;
import springsprout.common.web.support.Paging;
import springsprout.common.web.support.StandardPaging;


public class MemberContextTest {

	MemberContext context;

	@Before
	public void setUp(){
		context = new MemberContext();
		MemberSearchParam searchParam = new MemberSearchParam();
		Paging standardPaging = new StandardPaging(5);
		OrderParam orderParam = new OrderParam();
		context.setSearchParam(searchParam);
		context.setOrderParam(orderParam);
		context.setPaging(standardPaging);
		context.getPaging().setTotalRowsCount(10);
	}

	@Test
	public void getAllParam() throws Exception {
		assertEquals("p_page=1&p_size=5&o_field=&o_direction=&s_name=&s_email=",
				context.getAllParam());
	}

	@Test
	public void getPageParam() throws Exception {
		assertEquals("p_page=1&p_size=5", context.getPaging().toString());
	}

	@Test
	public void getSearchParam() throws Exception {
		assertEquals("s_name=&s_email=", context.getSearchParam().toString());
	}


	@Test
	public void getOrderParam() throws Exception {
		assertEquals("o_field=&o_direction=", context.getOrderParam().toString());
	}

	@Test
	public void bindParams() throws Exception {
		MemberContext context = new MemberContext();
		MockHttpServletRequest mockReqeust = new MockHttpServletRequest();
		mockReqeust.addParameter("o_direction", "asc");
		mockReqeust.addParameter("o_field", "name");

		context.bindParams(mockReqeust);
		OrderParam orderParam = context.getOrderParam();
		assertEquals("asc", orderParam.getDirection());
		assertEquals("name", orderParam.getField());
	}
}
