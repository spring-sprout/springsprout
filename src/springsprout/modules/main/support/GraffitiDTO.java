package springsprout.modules.main.support;

import java.util.Date;

public class GraffitiDTO {
	
	private int id;					// 낙서번호
	private String writerName;		// 낙서쓴사람이름
	private String writerAvatar;	// 낙서쓴사람아바타
    private String contents;		// 낙서내용
    private Date writtenDate;    //낙시 쓴 일자.

    public String getFormatedDate() {
        return writtenDate.toString();
    }

    public Date getWrittenDate() {
        return writtenDate;
    }
    public void setWrittenDate(Date writtenDate) {
        this.writtenDate = writtenDate;
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
	
}
