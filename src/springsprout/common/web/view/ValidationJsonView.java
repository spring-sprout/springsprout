package springsprout.common.web.view;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * jQuery Validation 과 사용하기 위하여, @Valid 에서 에러가 있을 경우, 
 * 에러정보를 포함한 Json 으 리턴한다.
 * @author JUNE
 */
public class ValidationJsonView extends AbstractView {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private BindException ex;
	private HttpServletResponse response;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public ValidationJsonView() {
		setContentType("application/json");
	};
	
	private ModelMap getErrorInfo() {
		ModelMap model = new ModelMap();
		model.addAttribute("errorType", "validError");
		model.addAttribute("errorCount", ex.getErrorCount());
		model.addAttribute("errors", ex.getFieldErrors());
		return model;
	}
	
	private void setScBadRequest() {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.ex = (BindException) model.get("exception");
		this.response = response;
		
		logger.info( ex.getFieldErrors().get(0).toString());
		logger.info("Error Fields : {}", ex.getFieldErrors());
		
		setScBadRequest();
		JsonGenerator generator =  objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
		
		objectMapper.writeValue(generator, getErrorInfo());
		
	}

}
