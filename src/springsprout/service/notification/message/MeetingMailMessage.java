package springsprout.service.notification.message;

import springsprout.common.convention.Convention;
import springsprout.common.util.StringUtils;
import springsprout.domain.Meeting;
import springsprout.domain.Study;
import springsprout.domain.enumeration.MeetingStatus;

public class MeetingMailMessage extends SpringSproutMailMessage {
	
	private Study study;
	private Meeting meeting;
	private MeetingStatus status;

	public MeetingMailMessage(Study study, Meeting meeting, MeetingStatus status) {
		this.setMembers(study.getCurrentMembers());
		this.study = study;
		this.meeting = meeting;
		this.status = status;
		
		System.out.println("메일 수신자 수:" + getMembers().size());
	}

	public MeetingMailMessage(Meeting meeting, MeetingStatus status) {
		this(meeting.getStudy(), meeting, status);
	}

	public String getContents() {
		StringBuilder content = new StringBuilder();
		switch (status) {
		case OPEN:
			content.append("<h1>새로운 모임 시작합니다.</h1>");
			content.append("<a href=\"" + getMeetingViewLink() + "\">상세내용은 클릭하세요.</a>");
			break;
		case UPDATED:
			content.append("<h1>모임 내용이 변경 됐습니다.</h1>");
			content.append("<a href=\"" + getMeetingViewLink() + "\">상세내용은 클릭하세요.</a>");
			break;
		case ENDED:
			content.append("<h1>스터디가 종료 됐습니다.</h1>");
			content.append("<a href=\"" + study.getLink() + "\">상세내용은 클릭하세요.</a>");		
			break;
		}
		appendMeetingInfo(content);
		return content.toString();
	}
	
	private void appendMeetingInfo(StringBuilder content) {
		content.append("<ul>");
		content.append("<li>");
		content.append("모임명: " + meeting.getTitle());
		content.append("</li>");
		content.append("<li>");
		content.append("모임장: " + meeting.getOwner().getName());
		content.append("</li>");
		content.append("<li>");
		content.append("제한인원: " + meeting.getMaximum());
		content.append("</li>");
		content.append("<li>");
		content.append("모임 시작 일시: " + meeting.getStartDateTime());
		content.append("</li>");
		content.append("<li>");
		content.append("모임 종료 일시: " + meeting.getEndDateTime());
		content.append("</li>");
		content.append("<li>");
		content.append("모임장소: " + meeting.getLocation());
		content.append("</li>");
		content.append("<li>");
		content.append("설명<br/> " + StringUtils.nl2br(meeting.getContents()));
		content.append("</li>");
		content.append("</ul>");
	}

	public String getMessage() {
		StringBuilder msg = new StringBuilder();
		msg.append(study.getStudyName());
		msg.append(" 스터디의 ");
		msg.append(meeting.getTitle());
		switch (status) {
		case OPEN:
			msg.append(" 모임이 개설되었습니다.\n");
			break;
		case UPDATED:
			msg.append(" 모임이 변경되었습니다.\n");
			break;
		}
		msg.append(getMeetingViewLink());
		return msg.toString();
	}

    private String getMeetingViewLink() {
        return Convention.HOME_URL + "study/" + study.getId() + "/meeting/" + meeting.getId() + "";
    }

    public String getTitle() {
		return SpringSproutMail.SUBJECT_PREFIX + " [" + study.getStudyName()
		+ "] 스터디의 [" + meeting.getTitle() + "] 모임 공지합니다.";
	}

}
