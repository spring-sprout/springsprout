package sandbox.json;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

public class SpringJsonVIewTest {
	
	class SampleObject{
		private String name;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
	
	MappingJacksonJsonView jsonView;
	
	@Test
	public void result() throws Exception {
		jsonView = new MappingJacksonJsonView();
		Map<String, Object> model = new HashMap<String, Object>();
		SampleObject s1 = new SampleObject();
		s1.setName("기선");
		model.put("s1", s1);
		SampleObject s2 = new SampleObject();
		s2.setName("갑수");
		model.put("s2", s2);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		jsonView.render(model, request, response);
		
		System.out.println(response.getContentAsString());
	}

}
