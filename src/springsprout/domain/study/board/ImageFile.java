package springsprout.domain.study.board;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import springsprout.common.annotation.DomainInfo;
import springsprout.domain.UploadFile;

@SuppressWarnings("serial")
@Entity
@DomainInfo("이미지파일")
@DiscriminatorValue("IMAGE")
public class ImageFile extends UploadFile {

	@Column(length = 255)
	private String thumbNailName;
	
	@Transient
	private String webPath;

	public void setThumbNailName( String thumbNailName) {
		this.thumbNailName = thumbNailName;
	}

	public String getThumbNailName() {
		return thumbNailName;
	}

	public void setWebPath( String webPath) {
		this.webPath = webPath;
	}

	public String getWebPath() {
		return webPath;
	}
	
}
