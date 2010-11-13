/**
 * Created by IntelliJ IDEA.
 * User: helols
 * Date: 2009. 10. 31
 * Time: 오전 10:56:48
 * enjoy springsprout ! development!
 */
package springsprout.modules.main.support;

import java.util.Date;

import springsprout.common.annotation.CustomTransfer;
import springsprout.common.util.StringUtils;
import springsprout.domain.Notice;

public class NoticeDTO {

    private Integer id;

	private String title;

    private String cuttingTitle;

    private Date created;
    
    private String memberName;
    
    private Date modified;
    
    private String modifierName;
    
    private String contents;
    
	public String getCuttingTitle() {
        return cuttingTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
        this.cuttingTitle = StringUtils.cutBytes(title,50);
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@CustomTransfer
    public void setMemberInfo(Notice notice){
        this.setMemberName(notice.getMember().getName());
        this.setModifierName(notice.getModifier()==null?"":notice.getModifier().getName());
    }

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getModified() {
		return modified;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getContents() {
		return contents;
	}

}
