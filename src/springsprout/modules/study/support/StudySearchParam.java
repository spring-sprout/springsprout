package springsprout.modules.study.support;

import springsprout.common.web.URLBuilder;
import springsprout.common.web.support.SearchParam;

public class StudySearchParam extends SearchParam{

    private String studyName;

    public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	@Override
    public String toString() {
        URLBuilder builder = new URLBuilder();
        builder.addParameter("studyName", studyName, "");
        return builder.toString();
    }
}
