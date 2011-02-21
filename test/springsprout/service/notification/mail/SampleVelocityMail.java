package springsprout.service.notification.mail;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import springsprout.service.notification.message.SpringSproutMail;

@Service
public class SampleVelocityMail extends SpringSproutMail {
	@Autowired VelocityEngine velocityEngine;
	
	@Override
	public String getContents() {
		return makeContents();
	}

	@Override
	public String getTitle() {
		return "봄싹 의견 테스트";
	}
	
	@Override
	public String[] getMailReceivers() {
		return new String[]{"dosajun@gmail.com"};
	}
	
	private String makeContents() {
		Map<String, String> model = new HashMap<String, String>();
		model.put("author", "김제준");
		model.put("studyName", "테스트 스터디");
		model.put("message", "러브라인도 좋지만 멋진 캐릭터를 훅 가게 만들었다");
		model.put("studyLink", "http://www.springsprout.org/study/" + 5);
		model.put("meetingLink", "http://www.springsprout.org/study/" + 5 + "/meeting/" + 3);
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/web/WEB-INF/velocity/commentNotifyMail.html", "UTF-8", model);
	}

}
