package springsprout.modules.study.support;

import java.util.List;
import java.util.Set;

import springsprout.domain.Meeting;
import springsprout.domain.Study;

/**
 * 스터디 인덱스 화면 정보를 표시하기 위한 클래스
 * 
 * @author June
 * 
 */
public class StudyIndexInfo {

	private static final int previewCount = 3;
	private int studyCount;
	private int studyMoreCount;
	private int meetingCount;
	private int meetingMoreCount;
	private int presentationCount;
	private int presentationMoreCount;
	private int closedStudyCount;
	private int closedStudyMoreCount;
	private Study hotStudy;

	private List<Study> activeStudies;
	private List<Study> pastStudies;

	public StudyIndexInfo() { }

	public StudyIndexInfo(List<Study> activeStudies, List<Study> pastStudies) {
		this.activeStudies = activeStudies;
		this.pastStudies = pastStudies;
		this.setStudyCountInfo();
		this.setMeetingCountInfo();
		this.setPresentationCountInfo();
		this.setClosedCountInfo();
	}

	public int getStudyCount() {
		return studyCount;
	}

	public void setStudyCount(int studyCount) {
		this.studyCount = studyCount;
	}

	public int getStudyMoreCount() {
		return studyMoreCount;
	}

	public void setStudyMoreCount(int studyMoreCount) {
		this.studyMoreCount = studyMoreCount;
	}

	public int getMeetingCount() {
		return meetingCount;
	}

	public void setMeetingCount(int meetingCount) {
		this.meetingCount = meetingCount;
	}

	public int getMeetingMoreCount() {
		return meetingMoreCount;
	}

	public void setMeetingMoreCount(int meetingMoreCount) {
		this.meetingMoreCount = meetingMoreCount;
	}

	public int getPresentationCount() {
		return presentationCount;
	}

	public void setPresentationCount(int presentationCount) {
		this.presentationCount = presentationCount;
	}

	public int getPresentationMoreCount() {
		return presentationMoreCount;
	}

	public void setPresentationMoreCount(int presentationMoreCount) {
		this.presentationMoreCount = presentationMoreCount;
	}

	public int getClosedStudyCount() {
		return closedStudyCount;
	}

	public void setClosedStudyCount(int closedStudyCount) {
		this.closedStudyCount = closedStudyCount;
	}

	public int getClosedStudyMoreCount() {
		return closedStudyMoreCount;
	}

	public void setClosedStudyMoreCount(int closedStudyMoreCount) {
		this.closedStudyMoreCount = closedStudyMoreCount;
	}

	private void setStudyCountInfo() {
		int count = activeStudies.size();
		int moreCount = 0;

		moreCount = compareCountAndSet(count, moreCount);
		
		this.studyCount = count;
		this.studyMoreCount = moreCount;

	}

	private int compareCountAndSet(int count, int moreCount) {
		if (count > previewCount) moreCount = count - previewCount;
		else if (count <= previewCount) moreCount = count;
		
		return moreCount;
	}
	
	private void setMeetingCountInfo() {
		int count = 0;
		int moreCount = 0;
		for (Study study : activeStudies) count += study.getMeetingCount();

		moreCount = compareCountAndSet(count, moreCount);
		
		this.meetingCount = count;
		this.meetingMoreCount = moreCount;
	}
	
	private void setPresentationCountInfo() {
		int count = 0;
		int moreCount = 0;
		for (Study study : activeStudies) {
			Set<Meeting> meetings = study.getMeetings();
			for (Meeting meeting : meetings) count += meeting.getPresentationCount();
		}
		
		moreCount = compareCountAndSet(count, moreCount);
		
		this.presentationCount = count;
		this.presentationMoreCount = moreCount;
	}
	
	private void setClosedCountInfo() {
		int count = pastStudies.size();
		int moreCount = 0;
		
		moreCount = compareCountAndSet(count, moreCount);
		
		this.closedStudyCount = count;
		this.closedStudyMoreCount = moreCount;
	}

	public void setHotStudy(Study hotStudy) {
		this.hotStudy = hotStudy;
	}

	public Study getHotStudy() {
		return hotStudy;
	}
}
