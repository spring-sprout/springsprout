package springsprout.common.binding;

import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.stereotype.Component;

import springsprout.common.propertyeditor.FormatDatePropertyEditor;
import springsprout.common.propertyeditor.GenericEnumPropertyEditor;
import springsprout.common.propertyeditor.GenericFakePropertyEditor;
import springsprout.domain.Role;
import springsprout.domain.enumeration.MemberStatus;
import springsprout.domain.enumeration.ResourceType;
import springsprout.domain.enumeration.StudyStatus;
import springsprout.domain.Member;

@Component
public class SpringSproutBindingInitializer implements PropertyEditorRegistrar{
	
	private class MemberStatusPropertyEditor extends GenericEnumPropertyEditor<MemberStatus> {}
	private class StudyStatusPropertyEditor extends GenericEnumPropertyEditor<StudyStatus> {}
	private class ResourceTypePropertyEditor extends GenericEnumPropertyEditor<ResourceType> {}

	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(Date.class, new FormatDatePropertyEditor());
		registry.registerCustomEditor(MemberStatus.class, new MemberStatusPropertyEditor());
		registry.registerCustomEditor(StudyStatus.class, new StudyStatusPropertyEditor());
		registry.registerCustomEditor(ResourceType.class, new ResourceTypePropertyEditor());
		registry.registerCustomEditor(Member.class, new GenericFakePropertyEditor<Member>(Member.class));
		registry.registerCustomEditor(Role.class, new GenericFakePropertyEditor<Role>(Role.class));
	}
}