package springsprout.service.notification.message;

import springsprout.common.convention.Convention;
import springsprout.common.util.StringUtils;
import springsprout.domain.Member;
import springsprout.domain.Study;
import springsprout.domain.enumeration.StudyStatus;

import java.util.Collection;

public class StudyMailMessage extends SpringSproutMailMessage {
	
	private Study study;
	private StudyStatus status;

	public StudyMailMessage(Study study, StudyStatus status, Collection<Member> members) {
		super(members);
		this.study = study;
		this.status = status;
	}

	public String getMessage() {
		StringBuilder msg = new StringBuilder();
		msg.append(study.getStudyName());
		switch (status) {
		case OPEN:
			msg.append(" 스터디가 개설되었습니다.\n");
			break;
		case UPDATED:
			msg.append(" 스터디 내용이 수정되었습니다.\n");
			break;
		case ENDED:
			msg.append(" 스터디가 종료되었습니다.\n");
			break;
		}
		msg.append(Convention.HOME_URL + "study/" + study.getId() + "");
		return msg.toString();
	}

	public String getContents() {
		StringBuilder content = new StringBuilder();
		switch (status) {
		case OPEN:
			content.append("<h1>새로운 스터디 시작합니다.</h1>");
			content.append("<a href=\"" + study.getLink() + "\">상세내용은 클릭하세요.</a>");
			break;
		case UPDATED:
			content.append("<h1>스터디 수정 내역입니다.</h1>");
			content.append("<a href=\"" + study.getLink() + "\">상세내용은 클릭하세요.</a>");
			break;
		case ENDED:
			content.append("<h1>스터디가 종료 됐습니다.</h1>");
			String studyUrl = Convention.HOME_URL + "study/list";
			content.append("<a href=\"" + studyUrl + "\">상세내용은 클릭하세요.</a>");		
			break;
		}
		appendStudyInfo(content);
		return content.toString();
	}
	
	private void appendStudyInfo(StringBuilder content) {
		content.append("<ul>");
		content.append("<li>");
		content.append("스터디명: " + study.getStudyName());
		content.append("</li>");
		content.append("<li>");
		content.append("스터디장: " + study.getManager().getName());
		content.append("</li>");
		content.append("<li>");
		content.append("최대인원: " + study.getMaximum());
		content.append("</li>");
		content.append("<li>");
		content.append("시작일: " + study.getStartDay());
		content.append("</li>");
		content.append("<li>");
		content.append("종료일: " + study.getEndDay());
		content.append("</li>");
		content.append("<li>");
		content.append("설명: " + StringUtils.nl2br(study.getDescr()));
		content.append("</li>");
		content.append("</ul>");		
	}

	public String getTitle() {
		return SpringSproutMail.SUBJECT_PREFIX + " [" + study.getStudyName() + "] 스터디 공지합니다.";
	}

}
