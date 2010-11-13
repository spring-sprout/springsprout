package springsprout.modules.study.support;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import springsprout.common.web.support.AbstractModulesContext;
import springsprout.common.web.support.Paging;
import springsprout.common.web.support.StandardPaging;

import javax.servlet.http.HttpServletRequest;

@Component
public class StudyContext extends AbstractModulesContext {

	public StudySearchParam getSearchParam() {
		return (StudySearchParam)super.getSearchParam();
	}

	private void bindSearchParam(HttpServletRequest request)
			throws ServletRequestBindingException {
		StudySearchParam searchParam = new StudySearchParam();
		searchParam.setStudyName(ServletRequestUtils.getStringParameter(request, "study_name"));
		this.setSearchParam(searchParam);
	}

	public void bindParams(HttpServletRequest request)
			throws ServletRequestBindingException {
		bindSearchParam(request);
		bindPageParam(request);
	}

	private void bindPageParam(HttpServletRequest request) {
		StandardPaging standardPaging = new StandardPaging(ServletRequestUtils
				.getIntParameter(request, "p_size", Paging.DEFAULT_SIZE));
		standardPaging.setCurrentPage(ServletRequestUtils.getIntParameter(
				request, "request_page", Paging.DEFAULT_PAGE));
		this.setPaging(standardPaging);
	}
	
	public String getRedirectToListURL() {
		return "redirect:/study/list.do?" + getAllParam();
	}

	public String getAllParam() {
		return getPaging() + "&" + getOrderParam() + "&" +  getSearchParam();
	}

}
