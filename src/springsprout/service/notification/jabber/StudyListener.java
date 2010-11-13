package springsprout.service.notification.jabber;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springsprout.domain.Meeting;
import springsprout.domain.Study;
import springsprout.modules.study.StudyService;
import springsprout.modules.study.meeting.MeetingService;

import java.util.List;

@Component
public class StudyListener implements PacketListener{

	@Autowired StudyService studyService;
    @Autowired MeetingService meetingService;
	@Autowired XMPPConnection connection;

	public void processPacket(Packet p) {
		String response = null;
		String username = null;
		if(p instanceof Message){
			Message msg = (Message)p;
			response = getResonse(msg.getFrom(), msg.getBody());
			username = msg.getFrom();
		}
		if(response != null && username != null){
			Message msg = new Message(username, Message.Type.chat);
			msg.setBody(response);
			connection.sendPacket(msg);
			response = null;
			username = null;
		}
	}

	private String getResonse(String from, String body) {
		StringBuilder builder = new StringBuilder();
		if(body.equals("study?")){
			List<Study> activeStudies = studyService.findActiveStudies();
			for(Study study : activeStudies){
				builder.append(study.getStudyName());
				builder.append("\n");
				builder.append(study.getLink());
				builder.append("\n");
			}
			return builder.toString();
		} else if(body.equals("meeting?")) {
			List<Meeting> activeMeetings = meetingService.findActiveMeetings();
			for(Meeting meeting: activeMeetings){
				builder.append(meeting.getTitle());
				builder.append("\n");
				builder.append(meeting.getLink());
				builder.append("\n");
			}
			return builder.toString();
		}
		return "1. study? == 진행 중인 스터디 목록\n 2. meeting? == 개설 상태 모임 목록";
	}
	
}
