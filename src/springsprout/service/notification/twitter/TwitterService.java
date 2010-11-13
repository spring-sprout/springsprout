package springsprout.service.notification.twitter;

import springsprout.service.notification.NotificationService;
import springsprout.service.notification.exception.NotificationException;
import springsprout.service.notification.message.SpringSproutMessage;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterService implements NotificationService {

	Twitter twitter;

    public TwitterService(Twitter twitter) {
        this.twitter = twitter;
    }

    public void sendMessage(SpringSproutMessage ssm)
			throws NotificationException {
		try {
			twitter.updateStatus(ssm.getMessage());
		} catch (TwitterException e) {
			throw new TwitterServiceException("HTTP status code: "
					+ e.getStatusCode() + " 메시지: " + ssm.getMessage(), e);
		}
	}

}
