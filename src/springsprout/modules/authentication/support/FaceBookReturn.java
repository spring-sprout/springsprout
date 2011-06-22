package springsprout.modules.authentication.support;

/**
 * Created by IntelliJ IDEA.
 * User: keesun
 * Date: 11. 6. 22
 * Time: 오후 12:12
 * http://developers.facebook.com/docs/authentication/
 *
 * When the user denied,
 * http://YOUR_URL?error_reason=user_denied&
     error=access_denied&error_description=The+user+denied+your+request.
 * When the user accepts,
 * http://YOUR_URL?code=A_CODE_GENERATED_BY_SERVER
 *
 */
public class FaceBookReturn {

    String error_reason;

    String error;

    String error_description;

    String code;

    public String getError_reason() {
        return error_reason;
    }

    public void setError_reason(String error_reason) {
        this.error_reason = error_reason;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
