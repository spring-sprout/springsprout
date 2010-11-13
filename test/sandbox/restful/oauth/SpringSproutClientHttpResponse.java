/**
 * Created by IntelliJ IDEA.
 * User: isyoon
 * Date: 2010. 7. 9
 * Time: 오전 4:56:01
 * enjoy springsprout ! development!
 */
package sandbox.restful.oauth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class SpringSproutClientHttpResponse implements ClientHttpResponse {
    private final HttpURLConnection connection;

    public SpringSproutClientHttpResponse(HttpURLConnection connection) {
        this.connection = connection;
    }
    
    private HttpHeaders headers;

    public HttpStatus getStatusCode() throws IOException {
        return HttpStatus.valueOf(this.connection.getResponseCode());
    }

    public String getStatusText() throws IOException {
        return this.connection.getResponseMessage();
    }

    public HttpHeaders getHeaders() {
        if (this.headers == null) {
            this.headers = new HttpHeaders();
            // Header field 0 is the status line for most HttpURLConnections, but not on GAE
            String name = this.connection.getHeaderFieldKey(0);
            if (StringUtils.hasLength(name)) {
                this.headers.add(name, this.connection.getHeaderField(0));
            }
            int i = 1;
            while (true) {
                name = this.connection.getHeaderFieldKey(i);
                if (!StringUtils.hasLength(name)) {
                    break;
                }
                this.headers.add(name, this.connection.getHeaderField(i));
                i++;
            }
        }
        return this.headers;
    }

    public InputStream getBody() throws IOException {
        InputStream errorStream = this.connection.getErrorStream();
        return (errorStream != null ? errorStream : this.connection.getInputStream());
    }

    public void close() {
        this.connection.disconnect();
    }

}
