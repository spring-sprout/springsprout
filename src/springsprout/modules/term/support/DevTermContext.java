package springsprout.modules.term.support;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import springsprout.common.util.StringUtils;
import springsprout.common.web.support.AbstractModulesContext;
import springsprout.common.web.support.CenterPositionPaging;
import springsprout.common.web.support.OrderParam;
import springsprout.common.web.support.Paging;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 1. 12
 * Time: 오후 3:17:09
 */
@Component
public class DevTermContext extends AbstractModulesContext {

    public void bindParams(HttpServletRequest request) throws ServletRequestBindingException {
        bindOrderParam(request);
		bindSearchParam(request);
		bindPageParam(request);
    }

	private void bindOrderParam(HttpServletRequest request)
			throws ServletRequestBindingException {
		OrderParam orderParam = new OrderParam();
		orderParam.setDirection(ServletRequestUtils.getStringParameter(request,
				"o_direction"));
		orderParam.setField(ServletRequestUtils.getStringParameter(request,
				"o_field"));

        String filed = orderParam.getField();
        if(StringUtils.isEmpty(filed))
            orderParam.setField("created");

        String direction = orderParam.getDirection();
        if(StringUtils.isEmpty(direction))
            orderParam.setDirection("desc");
        
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
		DevTermSearchParam searchParam = new DevTermSearchParam();
		searchParam.setKeyword(ServletRequestUtils.getStringParameter(request,
				"s_keyword"));
        searchParam.setHowRU(ServletRequestUtils.getStringParameter(request,
				"s_howru"));
		this.setSearchParam(searchParam);
	}

    public DevTermSearchParam getSearchParam(){
        return (DevTermSearchParam) super.getSearchParam();
    }

    public String getRedirectToListURL() {
        return null;
    }

    public String getAllParam() {
        return null;
    }

    public String getTabName() {
        String orderField = getOrderParam().getField();
        String orderDirection = getOrderParam().getDirection();
        if(orderField.equals("korTermCount")){
            if(orderDirection.equals("asc"))
                return "term_lonely";
            else
                return "term_hot";
        } else if(orderField.equals("phrase")){
            if(orderDirection.equals("asc"))
                return "term_a2z";
            else
                return "term_z2a";
        }
        return "term_new";
    }
}
