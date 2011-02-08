package springsprout.modules.term.support;

import org.springframework.context.ApplicationContext;
import springsprout.common.util.StringUtils;
import springsprout.domain.Tag;
import springsprout.modules.tag.TagRepository;
import springsprout.modules.tag.TagRepositoryImpl;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 2
 * Time: 오후 11:16:26
 */
public class TagsPropertyEditor extends PropertyEditorSupport {

    private TagRepository tagRepository;

    public TagsPropertyEditor(ApplicationContext applicationContext) {
        this.tagRepository = applicationContext.getBean("tagRepositoryImpl", TagRepositoryImpl.class);
    }

    @SuppressWarnings("unchecked")
	@Override
    public String getAsText() {
        if(getValue() == null)
            return "";
        String result = "";
        for(Tag tag : (List<Tag>) getValue())
            result += tag.getTag() + " ";
        return result;
    }

    @Override
    public void setAsText(String s) throws IllegalArgumentException {
        List<Tag> tags = new ArrayList<Tag>();
        if(StringUtils.isEmpty(s))
            setValue(tags);
        else {
            String[] tagStrings = s.split(" ");
            for(String tag : tagStrings){
                Tag oldTag = tagRepository.getByTag(tag);
                if(oldTag != null)
                    tags.add(oldTag);
                else
                    tags.add(new Tag(tag));
            }
            setValue(tags);
        }
    }
}
