package springsprout.common.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springsprout.common.util.StringUtils;
import springsprout.domain.Role;
import springsprout.modules.role.RoleService;

@Component
public class RolesEditor extends PropertyEditorSupport{

	@Autowired
	RoleService rs;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(!StringUtils.isEmpty( text)){
			String[] ids = text.split(",");
			Set<Role> roles = new HashSet<Role>();
			for(String id : ids){
				roles.add(rs.getRoleById(Integer.parseInt(id)));
			}
			setValue(roles);
		}else{
			setValue(null);
		}
	}
}
