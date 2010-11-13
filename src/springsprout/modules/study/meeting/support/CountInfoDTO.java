package springsprout.modules.study.meeting.support;

import springsprout.domain.Meeting;
import springsprout.domain.Presentation;
import springsprout.domain.Study;

/**
 * 스터디의 탭에 보일 카운터 정보를 표시하는 DTO
 * @author June
 *
 */
public class CountInfoDTO {

	private int attendanceCount;
	private int presentationCount; 
	private int resourcesCount;
	private int commentsCount;
	private int fileCount;
	
	public CountInfoDTO() {}
	
	public CountInfoDTO( Study study) {
		this.attendanceCount = study.getMemberCount();
	}
	
	public CountInfoDTO(Meeting meeting) {
		this.attendanceCount = meeting.getAttendanceCount();
		this.presentationCount = meeting.getPresentationCount();
		this.resourcesCount = meeting.getResourcesCount();
		this.commentsCount = meeting.getCommentsCount();
	}
	
	public CountInfoDTO( Presentation presentation) {
		this.setFileCount(presentation.getResourceCount());
		this.commentsCount = presentation.getCommentCount();
	}
	
	public int getAttendanceCount() {
		return attendanceCount;
	}
	public void setAttendanceCount(int attendanceCount) {
		this.attendanceCount = attendanceCount;
	}
	public int getPresentationCount() {
		return presentationCount;
	}
	public void setPresentationCount(int presentationCount) {
		this.presentationCount = presentationCount;
	}
	public int getResourcesCount() {
		return resourcesCount;
	}
	public void setResourcesCount(int resourcesCount) {
		this.resourcesCount = resourcesCount;
	}
	public int getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public int getFileCount() {
		return fileCount;
	}
	
	
}
