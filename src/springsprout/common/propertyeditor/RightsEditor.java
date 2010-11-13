package springsprout.common.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springsprout.common.util.StringUtils;
import springsprout.domain.Right;
import springsprout.modules.right.RightService;

@Component
public class RightsEditor extends PropertyEditorSupport {

	@Autowired
	RightService rs;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(!StringUtils.isEmpty( text)){
			String[] ids = text.split(",");
			Set<Right> rights = new HashSet<Right>();
			for(String id : ids){
				rights.add(rs.getRightById(Integer.parseInt(id)));
			}
			setValue(rights);
		}
	}
}
