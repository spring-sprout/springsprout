package springsprout.modules.member.support;

import springsprout.common.web.URLBuilder;
import springsprout.common.web.support.SearchParam;

public class MemberSearchParam extends SearchParam {

    private String name;

    private String email;

    private String allowedEmail;

    private String allowedGoogleTalk;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAllowedEmail() {
        return allowedEmail;
    }
    public void setAllowedEmail(String allowedEmail) {
        this.allowedEmail = allowedEmail;
    }

    public String getAllowedGoogleTalk() {
        return allowedGoogleTalk;
    }
    public void setAllowedGoogleTalk(String allowedGoogleTalk) {
        this.allowedGoogleTalk = allowedGoogleTalk;
    }

    @Override
    public String toString() {
        URLBuilder builder = new URLBuilder();
        builder.addParameter("s_name", name, "");
        builder.addParameter("s_email", email, "");
        return builder.toString();
    }

}
