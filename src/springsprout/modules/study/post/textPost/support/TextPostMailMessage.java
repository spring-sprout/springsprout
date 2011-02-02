package springsprout.modules.study.post.textPost.support;

import springsprout.common.convention.Convention;
import springsprout.domain.Member;
import springsprout.domain.Study;
import springsprout.domain.study.board.Post;
import springsprout.domain.study.board.TextPost;
import springsprout.service.notification.message.SpringSproutMail;
import springsprout.service.notification.message.SpringSproutMailMessage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * User: whiteship2000
 * Date: 11. 2. 2.
 * Time: 오전 11:14
 */
public class TextPostMailMessage extends SpringSproutMailMessage {

    TextPost post;
    Study study;

    public TextPostMailMessage(Study study, TextPost post, Collection<Member> members) {
        super(members);
        this.post = post;
        this.study = study;
    }

    public String getTitle() {
        return SpringSproutMail.SUBJECT_PREFIX + " " + post.getTitle();
    }

    public String getContents() {
        return "ftl:textPostMailMessage.ftl";
    }

    public String getMessage() {
        return "새 게시물이 등록됐습니다. " + link();
    }

    private String link() {
        return Convention.HOME_URL + "study/" + study.getId() + "/post/textPost/article/" + post.getId();
    }

    @Override
    public Map getModelObject() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("post", post);
        map.put("study", study);
        return map;
    }
}
