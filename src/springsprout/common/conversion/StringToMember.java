package springsprout.common.conversion;

import org.springframework.binding.convert.converters.StringToObject;

import springsprout.common.util.StringUtils;
import springsprout.domain.Member;

public class StringToMember extends StringToObject{
	
	public StringToMember() {
		super(Member.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object toObject(String id, Class targetClass)
			throws Exception {
		if(StringUtils.isEmpty(id))
			return null;
        return new Member(Integer.parseInt(id));
	}

	@Override
	protected String toString(Object object) throws Exception {
		if(object == null)
			return "";
		Member member = (Member)object;
		return member.getName();
	}

}
