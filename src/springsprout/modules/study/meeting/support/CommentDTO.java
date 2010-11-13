/**
 * Created by IntelliJ IDEA.
 * User: helols
 * Date: 2009. 12. 12
 * Time: 오후 4:40:15
 * enjoy springsprout ! development!
 */
package springsprout.modules.study.meeting.support;

import springsprout.common.annotation.CustomTransfer;
import springsprout.domain.Comment;

import java.text.SimpleDateFormat;

public class CommentDTO {
	private int id;
    private String comment;
    private String writerAvatar;
    private String writerName;
    private String createdStr;
    
    public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWriterAvatar() {
        return writerAvatar;
    }

    public void setWriterAvatar(String writerAvatar) {
        this.writerAvatar = writerAvatar;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getCreatedStr() {
        return createdStr.toString();
    }

    public void setCreatedStr(String createdStr) {
        this.createdStr = createdStr;
    }

    @CustomTransfer
    public void setWriterInfo(Comment comment){
        this.setWriterAvatar(comment.getWriter().getAvatar());
        this.setWriterName(comment.getWriter().getName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        this.setCreatedStr(sdf.format(comment.getCreated()));
    }
}
