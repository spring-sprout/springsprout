package springsprout.modules.main.support;

import springsprout.common.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GraffitiDTO {
	
	private int id;					// 낙서번호
	private String writerName;		// 낙서쓴사람이름
	private String writerAvatar;	// 낙서쓴사람아바타
    private String contents;		// 낙서내용
    private Date writtenDate;       //낙시 쓴 일자.
	private int writerId;           //낙서쓴사람 ID

    public String getFormattedDate() {
        return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(writtenDate);
    }
    public Date getWrittenDate() {
        return writtenDate;
    }
    public void setWrittenDate(Date writtenDate) {
        this.writtenDate = writtenDate;
    }
	public String getWrittenDateString(){
		return StringUtils.convertDate2String(this.writtenDate);
	}
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public String getWriterAvatar() {
		return writerAvatar;
	}
	public void setWriterAvatar(String writerAvatar) {
		this.writerAvatar = writerAvatar;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getWriterId() {
		return writerId;
	}
	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}
}
