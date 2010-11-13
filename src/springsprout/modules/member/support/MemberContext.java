package springsprout.modules.member.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import springsprout.common.web.support.AbstractModulesContext;
import springsprout.common.web.support.CenterPositionPaging;
import springsprout.common.web.support.OrderParam;
import springsprout.common.web.support.Paging;

@Component
public class MemberContext extends AbstractModulesContext {

	public MemberSearchParam getSearchParam(){
		return (MemberSearchParam) super.getSearchParam();
	}

	private void bindOrderParam(HttpServletRequest request)
			throws ServletRequestBindingException {
		OrderParam orderParam = new OrderParam();
		orderParam.setDirection(ServletRequestUtils.getStringParameter(request,
				"o_direction"));
		orderParam.setField(ServletRequestUtils.getStringParameter(request,
				"o_field"));
		this.setOrderParam(orderParam);
	}

	private void bindPageParam(HttpServletRequest request) {
		CenterPositionPaging centerPositionPaging = new CenterPositionPaging(ServletRequestUtils
				.getIntParameter(request, "p_size", Paging.DEFAULT_SIZE));
		centerPositionPaging.setCurrentPage(ServletRequestUtils.getIntParameter(
				request, "p_page", Paging.DEFAULT_PAGE));
		this.setPaging(centerPositionPaging);
	}

	private void bindSearchParam(HttpServletRequest request)
			throws ServletRequestBindingException {
		MemberSearchParam searchParam = new MemberSearchParam();
		searchParam.setEmail(ServletRequestUtils.getStringParameter(request,
				"s_email"));
		searchParam.setName(ServletRequestUtils.getStringParameter(request,
				"s_name"));
		this.setSearchParam(searchParam);
	}

	public void bindParams(HttpServletRequest request)
			throws ServletRequestBindingException {
		bindOrderParam(request);
		bindSearchParam(request);
		bindPageParam(request);
	}

	public String getRedirectToListURL() {
		return "redirect:/member/list.do?" + getAllParam();
	}

	public String getAllParam() {
		return getPaging() + "&" + getOrderParam() + "&" + getSearchParam();
	}

}
