package springsprout.modules.term.support;

import springsprout.common.convention.Convention;
import springsprout.common.util.StringUtils;
import springsprout.domain.DevTerm;
import springsprout.domain.Member;
import springsprout.domain.KorTerm;
import springsprout.service.notification.message.SpringSproutMailMessage;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 1. 19
 * Time: 오후 4:39:43
 */
public class KorTermMailMessage extends SpringSproutMailMessage {

    private DevTerm devTerm;
    private KorTerm korTerm;

    public KorTermMailMessage(Member member, DevTerm devTerm, KorTerm korTerm) {
        super(Arrays.asList(new Member[]{member}));
        this.devTerm = devTerm;
        this.korTerm = korTerm;
    }

    public String getTitle() {
        return devTerm.getPhrase() + " 용어에 새로운 한글 용어가 등록됐습니다.";
    }

    public String getContents() {
        StringBuilder content = new StringBuilder();
        content.append("<h1>새로운 한글 용어가 등록됐습니다.</h1>");
        content.append("<a href=\"" + getLink() + "\">상세내용은 클릭하세요.</a>");
		appendKorTermInfo(content);
		return content.toString();
    }

    private String getLink(){
        return Convention.HOME_URL + "term/" + devTerm.getId() + "";
    }

    private void appendKorTermInfo(StringBuilder content) {
        content.append("<ul>");
		content.append("<li>");
		content.append("개발용어: " + devTerm.getPhrase());
		content.append("</li>");
		content.append("<li>");
		content.append("한글용어: " + korTerm.getKorPhrase());
		content.append("</li>");
		content.append("<li>");
		content.append("한글용어 등록자: " + korTerm.getMember().getName());
		content.append("</li>");
		content.append("<li>");
		content.append("설명: " + StringUtils.nl2br(korTerm.getDetails()));
		content.append("</li>");
		content.append("</ul>");
    }

    public String getMessage() {
        StringBuilder msg = new StringBuilder();
        msg.append("[");
        msg.append(devTerm.getPhrase());
        msg.append("] 용어에 한글 용어 [");
        msg.append(korTerm.getKorPhrase());
        msg.append("] 이/가 등록됐습니다. ");

        msg.append(Convention.HOME_URL + "term/" + devTerm.getId() + "");
        return msg.toString();
    }
}
