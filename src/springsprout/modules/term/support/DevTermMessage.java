package springsprout.modules.term.support;

import springsprout.common.convention.Convention;
import springsprout.domain.DevTerm;
import springsprout.service.notification.message.SpringSproutMessage;

import java.util.Collection;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 1. 19
 * Time: 오후 4:30:44
 */
public class DevTermMessage implements SpringSproutMessage {

    public static enum Status {NEW, UPDATED}

    private DevTerm devTerm;
    private Status status;

    public DevTermMessage(DevTerm devTerm, Status status) {
        this.devTerm = devTerm;
        this.status = status;
    }

    public String getMessage() {
        StringBuilder msg = new StringBuilder();
		msg.append("[");
		msg.append(devTerm.getPhrase());
        switch (this.status){
            case NEW:
                msg.append("] 용어가 등록되었습니다. ");
                break;
            case UPDATED:
                msg.append("] 용어가 수정되었습니다. ");
                break;
        }
		msg.append(Convention.HOME_URL + "term/" + devTerm.getId() + "");
		return msg.toString();
    }

    public Collection<String> getMessageReceivers() {
        return null;
    }

    public String getTitle() {
        return null;
    }

    public String[] getMailReceivers() {
        return new String[0];
    }

    public String getFrom() {
        return null;
    }

    public String getContents() {
        return null;
    }

    public boolean isHTML() {
        return false;
    }

    public Map getModelObject() {
        return null;
    }
}
