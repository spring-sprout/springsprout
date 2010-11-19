package springsprout.modules.calendar;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.HtmlTextConstruct;
import com.google.gdata.data.Link;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclFeed;
import com.google.gdata.data.acl.AclNamespace;
import com.google.gdata.data.acl.AclScope;
import com.google.gdata.data.calendar.*;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import springsprout.modules.calendar.CalendarServiceCallBack;
import springsprout.modules.member.MemberService;
import springsprout.modules.study.StudyRepository;
import springsprout.modules.study.StudyService;
import springsprout.domain.Meeting;
import springsprout.domain.Member;
import springsprout.domain.Study;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import springsprout.service.security.SecurityService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static springsprout.modules.calendar.CalendarUtil.*;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 8. 10
 * Time: 오후 1:22:08
 * <p/>
 * http://code.google.com/intl/ko-KR/apis/calendar/data/2.0/developers_guide_java.html
 * http://code.google.com/intl/ko-KR/apis/gdata/javadoc/index.html
 */
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class GoogleCalendarServiceImpl implements GoogleCalendarService {
	
	@Autowired MemberService memberService;
	@Resource StudyService studyService;
    @Autowired SecurityService securityService;

    public static final String OWN_CALENDAR_URL = "https://www.google.com/calendar/feeds/default/owncalendars/full";
    final Logger logger = LoggerFactory.getLogger(GoogleCalendarServiceImpl.class);
	CalendarService calendarService;

	private String title;
	private String username;
    private String password;

    public void setCalendarService(CalendarService calendarService) {
        this.calendarService = calendarService;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @PostConstruct
    public void init() {
        calendarService = new CalendarService(title);
        try {
            calendarService.setUserCredentials(username, password);
        } catch (AuthenticationException e) {
            throw new RuntimeException("로그인 실패 username : [" + username + "] password: [" + password + "]", e);
        }
    }

    public List<CalendarEntry> getOwnCalendarList() {
        CalendarFeed resultFeed = getCalendarFeed();
        return resultFeed.getEntries();
    }

    private CalendarFeed getCalendarFeed() {
        final URL feedUrl = makeUrl(OWN_CALENDAR_URL);
        CalendarFeed resultFeed = serviceWithCalendarStrategy(new CalendarServiceCallBack<CalendarFeed>() {
            public CalendarFeed queryForObject() throws IOException, ServiceException {
                return calendarService.getFeed(feedUrl, CalendarFeed.class);
            }
        });
        return resultFeed;
    }

    private <T> T serviceWithCalendarStrategy(CalendarServiceCallBack<T> strategy) {
        T result = null;
        try {
            result = strategy.queryForObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public CalendarEntry createNewStudyCalendar(Study study) {
        final CalendarEntry calendar = new CalendarEntry();
        calendar.setTitle(new PlainTextConstruct(study.getStudyName()));
        calendar.setSummary(new HtmlTextConstruct(study.getLink()));
        calendar.setTimeZone(new TimeZoneProperty("Asia/Seoul"));
        calendar.setHidden(HiddenProperty.FALSE);
        calendar.setColor(new ColorProperty("#2952A3"));

        final URL postUrl = makeUrl(OWN_CALENDAR_URL);

        CalendarEntry newCalendar = serviceWithCalendarStrategy(new CalendarServiceCallBack<CalendarEntry>(){
            public CalendarEntry queryForObject() throws IOException, ServiceException {
                return calendarService.insert(postUrl, calendar);
            }
        });
        
        Member manager = studyService.getManagerOf(study);
        addToAccessControlList(manager.getEmail(), newCalendar);
        setCalendarId(newCalendar, study);
        return newCalendar;
    }
    
    public void addToAccessControlList(Study study, Member member) {
    	if (member.getEmail() == null) {
    		logger.info("이메일이 없습니다.");
    		return;
    	} else if (study.getCalendarId() == null) {
    		logger.info("아직 캘린더에 등록되지 않았습니다.");
    		return;
    	}
    	
    	String email = member.getEmail();
    	CalendarEntry calendar = findByCalendarId(getCalendarFeed(), study);
    	
    	addToAccessControlList(email, calendar);
    }
    
    private void addToAccessControlList(String email, CalendarEntry calendar) {
    	if (!email.contains("gmail")) {
    		logger.info("Gmail이 아닙니다: " + email);
    		return;
    	} else if (isRegisteredAccessControlList(email, calendar)) {
    		logger.info("이미 등록된 사용자 입니다: " + email);
    		return;
    	}
    	    	
    	final AclEntry entry = new AclEntry();
    	entry.setScope(new AclScope(AclScope.Type.USER, email));
    	entry.setRole(CalendarAclRole.READ);
    	
    	final URL aclUrl = makeAclUrl(calendar.getEditLink().getHref());
    	
    	AclEntry insertedEntry = serviceWithCalendarStrategy(new CalendarServiceCallBack<AclEntry>() {
			public AclEntry queryForObject() throws IOException,
					ServiceException {
				return calendarService.insert(aclUrl, entry);
			}
    	});
    }
    
    public void removeToAccessControlList(Study study, Member member) {
    	String email = member.getEmail();
    	CalendarEntry calendar = findByCalendarId(getCalendarFeed(), study);
    	
    	removeToAccessControlList(email, calendar);
    }
    
    private void removeToAccessControlList(final String email, final CalendarEntry calendar) {
    	if (!email.contains("gmail")) {
    		logger.info("Gmail이 아닙니다: " + email);
    		return;
    	} else if (!isRegisteredAccessControlList(email, calendar)) {
    		logger.info("등록된 사용자가 아닙니다: " + email);
    		return;
    	}
    	
    	serviceWithCalendarStrategy(new CalendarServiceCallBack() {
			public Object queryForObject() throws IOException, ServiceException {
				for (AclEntry entry: getAclFeed(calendar).getEntries()) {
					if (entry.getScope().getValue().equals(email)) {
						entry.delete();
						logger.info("사용자가 ACL 리스트에서 삭제 됐습니다: " + email);
					}
				}
				return null;
			}
		});
	}
    
	public boolean isRegisteredAccessControlList(String email, CalendarEntry calendar) {
		logger.info("Calendar: " + calendar.getTitle().getPlainText() + ", email: " + email);
		AclFeed aclFeed = getAclFeed(calendar);
		
		for(AclEntry aclEntry : aclFeed.getEntries()) {
			logger.info("Scope: Type=" + aclEntry.getScope().getType() +
					" (" + aclEntry.getScope().getValue() + ")");
			logger.info("Role: " + aclEntry.getRole().getValue());
			
			if (aclEntry.getScope().getValue().equals(email)) {
				return true;
			}
		}
		
		return false;
    }
	
	private AclFeed getAclFeed(CalendarEntry calendar) {
		final Link link = calendar.getLink(AclNamespace.LINK_REL_ACCESS_CONTROL_LIST,
				Link.Type.ATOM);
		AclFeed aclFeed = serviceWithCalendarStrategy(new CalendarServiceCallBack<AclFeed>() {
			public AclFeed queryForObject() throws IOException,
					ServiceException {
				return calendarService.getFeed(new URL(link.getHref()), AclFeed.class);
			}			
		});
		return aclFeed;
	}
    
    private URL makeAclUrl(String editLinkUrl) {
    	String aclUrl = editLinkUrl.replaceFirst("default/owncalendars/full/", "").concat("/acl/full");
    	logger.info("AinfoControlList URL: " + aclUrl);
    	return makeUrl(aclUrl);
    }
    
    private URL makeUrl(String url) {
        URL feedUrl = null;
        try {
            feedUrl = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL 생성 실패 url : [" + OWN_CALENDAR_URL, e);
        }
        return feedUrl;
    }
    
    public void updateStudyCalendar(Study study, Member currentMember) {
    	updateStudyCalendar(study);
    }
    
    public void updateStudyCalendar(Study study) {
        CalendarFeed resultFeed = getCalendarFeed();
        final CalendarEntry calendar = findByCalendarId(resultFeed, study);
        if (calendar == null)
            return;

        calendar.setTitle(new PlainTextConstruct(study.getStudyName()));        

        serviceWithCalendarStrategy(new CalendarServiceCallBack(){
            public Object queryForObject() throws IOException, ServiceException {
                calendar.update();
                return null;
            }
        });
    }
    
	public void deleteStudyCalendar(Study study) {
    	if (study.getCalendarId() == null) {
    		return;
    	}
    	
    	final CalendarEntry calendar = findByCalendarId(getCalendarFeed(), study);
    	
    	serviceWithCalendarStrategy(new CalendarServiceCallBack() {
			public Object queryForObject() throws IOException, ServiceException {
				calendar.delete();
				return null;
			}
		});
    }
    
    public CalendarEventEntry createNewMeetingEvent(Meeting meeting) {
    	final CalendarEventEntry event = new CalendarEventEntry();
    	event.setTitle(new PlainTextConstruct(meeting.getTitle()));
    	event.setContent(new PlainTextConstruct(meeting.getContents()));    	
    	event.addTime(whenWeMeet(meeting));
    	
    	final URL postUrl = makeOtherCalendarUrl(meeting.getStudy());
    	
    	CalendarEventEntry newEvent = serviceWithCalendarStrategy(new CalendarServiceCallBack<CalendarEventEntry>() {
			public CalendarEventEntry queryForObject() throws IOException,
					ServiceException {				
				return calendarService.insert(postUrl, event);
			}
		});
    	
    	logger.info("생성된 이벤트 ID: " + newEvent.getId());
    	
    	meeting.setEtag(newEvent.getEtag());
    	setMeetingId(newEvent, meeting);
    	return newEvent;
    }
    
    public void deleteMeetingEvent(Meeting meeting) {
    	if (meeting.getEventId() == null) {
    		return;
    	}
    	
    	CalendarEventEntry event = findEventByMeeting(meeting);
    	
    	if (event == null) {
    		logger.info("이벤트를 찾을 수 없습니다.");
    		return;
    	}
    	
    	final URL eventUrl = makeUrl(event.getEditLink().getHref());
    	logger.info("삭제할 이벤트엔트리 URL: " + eventUrl);
    	
    	serviceWithCalendarStrategy(new CalendarServiceCallBack() {
			public Object queryForObject() throws IOException,
					ServiceException {				
				calendarService.delete(eventUrl, "*");
				return null;
			}
		});
    }
    
    public CalendarEventEntry updateMeetingEvent(Meeting meeting, String oldMeetingTitle) {
    	if (meeting.getEventId() == null) {
    		return createNewMeetingEvent(meeting);
    	}
    	
    	final CalendarEventEntry event = findEventByMeetingTitle(meeting, oldMeetingTitle);
    	
    	event.setTitle(new PlainTextConstruct(meeting.getTitle()));
    	event.setContent(new PlainTextConstruct(meeting.getContents()));
    	event.addTime(whenWeMeet(meeting));
    	
    	final URL eventUrl = makeUrl(event.getEditLink().getHref());
    	logger.info("수정할 이벤트엔트리 URL: " + eventUrl);
    	
    	CalendarEventEntry updatedEvent = serviceWithCalendarStrategy(new CalendarServiceCallBack<CalendarEventEntry>() {
			public CalendarEventEntry queryForObject() throws IOException,
					ServiceException {
				return calendarService.update(eventUrl, event);
			}
		});
    	
    	return updatedEvent;
    }
    
    URL makeEventUrlById(Meeting meeting) {
    	URL eventEntryUrl = makeUrl("http://www.google.com/calendar/feeds/" + meeting.getStudy().getCalendarId() + "%40group.calendar.google.com/private/full/" + meeting.getEventId());
		return eventEntryUrl;
	}

	public CalendarEventEntry findEventByMeeting(Meeting meeting) {
    	// CalendarFeedURL 얻어오기
    	URL calendarUrl = makeOtherCalendarUrl(meeting.getStudy());
    	
    	if (calendarUrl == null) {
    		return null;
    	}
    	
    	// CalendarEventFeed 가져오기
    	final CalendarQuery query = new CalendarQuery(calendarUrl);
    	query.setFullTextQuery(forExactSearching(meeting.getTitle()));
    	When when = whenWeMeet(meeting);
    	query.setMinimumStartTime(when.getStartTime());
    	query.setMaximumStartTime(when.getEndTime());
    	
    	CalendarEventFeed feed = serviceWithCalendarStrategy(new CalendarServiceCallBack<CalendarEventFeed>() {
			public CalendarEventFeed queryForObject() throws IOException,
					ServiceException {
				return calendarService.query(query, CalendarEventFeed.class);
			}
		});
    	
    	// CalendarEventEntry 가져오기
    	CalendarEventEntry event = null;
    	if (feed.getTotalResults() > 0) {
    		for (CalendarEventEntry entry : feed.getEntries()) {
    			if (entry.getId().contains(meeting.getEventId())) {
    				event = entry;
    			}
    		}
    	}
    	
    	return event;
    }
    
    URL makeOtherCalendarUrl(Study study) {
    	CalendarEntry calendar = findByCalendarId(getCalendarFeed(), study);
    	String otherCalendarFeedUrl = getCalendarFeedUrl(calendar);
    	return makeUrl(otherCalendarFeedUrl);
    }
    
    private String getCalendarFeedUrl(CalendarEntry calendar) {
    	String originalUrl = calendar.getEditLink().getHref();
    	logger.info(originalUrl);
    	// 링크변환 참조 사이트:
    	// http://webcache.googleusercontent.com/search?q=cache:http://67central.com/bc/2008/09/28/adding-events-to-secondary-google-calendars-in-python/
    	return originalUrl.replaceFirst("default/owncalendars/full/", "") + "/private/full";
    }
    
    private When whenWeMeet(Meeting meeting) {
    	When eventTimes = new When();
    	DateTime start = combineDateTime(meeting.getOpenDate(), meeting.getOpenTime());
    	DateTime end = combineDateTime(meeting.getCloseDate(), meeting.getCloseTime());
    	eventTimes.setStartTime(start);
    	eventTimes.setEndTime(end);
    	return eventTimes;
    }
    
    private DateTime combineDateTime(Date date, Date time) {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	
    	StringBuilder dateTimeBuilder = new StringBuilder();
    	dateTimeBuilder.append(formatter.format(date));
    	
    	formatter.applyPattern("'T'HH:mm:ss+09:00");
    	dateTimeBuilder.append(formatter.format(time));
    	
    	logger.info("XML DateTime 형식으로 변환: " + dateTimeBuilder.toString());    	
    	return DateTime.parseDateTime(dateTimeBuilder.toString());
    }
    
    private CalendarEntry findByCalendarId(CalendarFeed resultFeed, Study study) {
        if (resultFeed == null || resultFeed.getTotalResults() == 0 || study.getCalendarId() == null)
            return null;

        CalendarEntry result = null;
        logger.info(study.getCalendarId());
        for (CalendarEntry entry : resultFeed.getEntries()) {
            if (getCalendarFeedUrl(entry).contains(study.getCalendarId())) {
                result = entry;
            }
        }
        return result;
    }
    
    private void setCalendarId(CalendarEntry newCalendar, Study study) {    	
    	String calendarId = newCalendar.getId().replaceFirst("http://www.google.com/calendar/feeds/default/calendars/", "");
    	calendarId = calendarId.substring(0, calendarId.indexOf("%40"));
    	logger.info("스터디에 저장할 캘린더 아이디: " + calendarId);
    	study.setCalendarId(calendarId);
    }
    
    private void setMeetingId(CalendarEventEntry newEvent, Meeting meeting) {
    	String eventId = newEvent.getId().substring(newEvent.getId().lastIndexOf("/") + 1);
    	logger.info("미팅에 저장할 이벤트 아이디: " + eventId);
    	meeting.setEventId(eventId);
    }
    
    CalendarEntry findCalendarByStudyTitle(CalendarFeed resultFeed, Study study) {
    	if (resultFeed == null || resultFeed.getTotalResults() == 0)
    		return null;
    	
    	CalendarEntry result = null;
    	for (CalendarEntry entry : resultFeed.getEntries()) {
    		if (entry.getTitle().getPlainText().equals(study.getStudyName())) {
    			result = entry;
    			break;
    		}
    	}
    	return result;
    }
    
	public void synchronizeForLegacy(Study study) {
		registerGoogleCalendarForLegacy(study);
		addACLForLegacyMemerOf(study);
	}
	
	private void registerGoogleCalendarForLegacy(Study study) {
		CalendarEntry calendar = findByCalendarId(getCalendarFeed(), study);
		if (calendar == null || study.getCalendarId() == null) {
			createNewStudyCalendar(study);
//			repository.update(study);
		}
	}

	private void addACLForLegacyMemerOf(Study study) {
		Set<Member> members = studyService.getMembersOf(study);
		CalendarEntry calendar = findByCalendarId(getCalendarFeed(), study);
		for (Member member: members) {
			String email = member.getEmail();
			if (!isRegisteredAccessControlList(email, calendar)) {
				addToAccessControlList(email, calendar);
			}
		}
	}
	
	public void synchronizeForLegacy(Set<Meeting> oldMeetings) {
		registerGoogleCalendarForLegacy(oldMeetings);
	}
	
	public void synchronizeForLegacy(Set<Meeting> oldMeetings, String updatedMeetingTitle) {
		registerGoogleCalendarForLegacy(oldMeetings, updatedMeetingTitle);
	}
	
	private void registerGoogleCalendarForLegacy(Set<Meeting> oldMeetings) {
		for (Meeting meeting: oldMeetings) {
			CalendarEventEntry event = findEventByMeetingTitle(meeting);
			if (event == null || meeting.getEventId() == null) {
				createNewMeetingEvent(meeting);
			}
		}
	}
	
	private void registerGoogleCalendarForLegacy(Set<Meeting> oldMeetings, String updatedMeetingTitle) {
		for (Meeting meeting: oldMeetings) {
			if (meeting.getTitle().equals(updatedMeetingTitle))
				continue;
			CalendarEventEntry event = findEventByMeetingTitle(meeting);
			if (event == null || meeting.getEventId() == null) {
				createNewMeetingEvent(meeting);
			}
		}
	}
	
	private CalendarEventEntry findEventByMeetingTitle(Meeting meeting, String oldMeetingTitle) {
		if (meeting.getTitle() == null) {
			logger.info("모임 제목이 없습니다.");
			return null;
		}
		logger.info("찾으려는 모임 제목: " + oldMeetingTitle);
		URL calendarUrl = makeOtherCalendarUrl(meeting.getStudy());
		final CalendarQuery query = new CalendarQuery(calendarUrl);
		query.setFullTextQuery(forExactSearching(oldMeetingTitle));
		CalendarEventFeed events = serviceWithCalendarStrategy(new CalendarServiceCallBack<CalendarEventFeed>() {
			public CalendarEventFeed queryForObject() throws IOException,
					ServiceException {
				return calendarService.query(query, CalendarEventFeed.class);
			}
		});
		
		for (CalendarEventEntry entry : events.getEntries()) {
			if (entry.getTitle().getPlainText().equals(oldMeetingTitle)) {
				return entry;
			}
		}
		
		logger.info("모임을 찾지 못했습니다.");
		return null;
	}
	
	private CalendarEventEntry findEventByMeetingTitle(Meeting meeting) {
		if (meeting.getTitle() == null) {
			logger.info("모임 제목이 없습니다.");
			return null;
		}
		logger.info("찾으려는 모임 제목: " + meeting.getTitle());
		URL calendarUrl = makeOtherCalendarUrl(meeting.getStudy());
		final CalendarQuery query = new CalendarQuery(calendarUrl);
		query.setFullTextQuery(forExactSearching(meeting.getTitle()));
		CalendarEventFeed events = serviceWithCalendarStrategy(new CalendarServiceCallBack<CalendarEventFeed>() {
			public CalendarEventFeed queryForObject() throws IOException,
					ServiceException {
				return calendarService.query(query, CalendarEventFeed.class);
			}
		});
		
		for (CalendarEventEntry entry : events.getEntries()) {
			if (entry.getTitle().getPlainText().equals(meeting.getTitle())) {
				return entry;
			}
		}
		
		logger.info("모임을 찾지 못했습니다.");
		return null;
	}
}
