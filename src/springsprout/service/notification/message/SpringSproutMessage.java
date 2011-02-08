package springsprout.service.notification.message;

import java.util.Collection;
import java.util.Map;

public interface SpringSproutMessage {
	
	public String getMessage();
	public Collection<String> getMessageReceivers();
	
	public String getTitle();
	public String[] getMailReceivers();
	public String getFrom();
	public String getContents();
	public boolean isHTML();

    public Map getModelObject();
}
