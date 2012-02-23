/**
 * Created by IntelliJ IDEA.
 * User: isyoon
 * Date: 2010. 7. 9
 * Time: 오전 4:54:19
 * enjoy springsprout ! development!
 */
package sandbox.restful.oauth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.AbstractClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class SpringSproutClientHttpRequest extends AbstractClientHttpRequest {

    private final static String TWITTER_CONSUMER_KEY = "YTScqXoK5WiXpe0JWke1A";
    private final static String TWITTER_CONSUMER_SECRET = "iPIX0FjuMJFE8vQ1VES8Gn4iDwsliwXHH04TETjHlw";
    private final static String TWITTER_OAUTH_TOKEN = "139620256-dLt40SyUT3cKHoX6obQAU2WbpmGY3ww8mAkET3p4";
    private final static String TWITTER_OAUTH_TOKE_SECRET = "VPfFxMfDCO5byqrsuWJkS328JHZifTwXC2PDZ18cVEk";
    
    private final HttpURLConnection connection;

    public SpringSproutClientHttpRequest(HttpURLConnection connection) {
        this.connection = connection;
    }

    public HttpMethod getMethod() {
        return HttpMethod.valueOf(this.connection.getRequestMethod());
    }

    public URI getURI() {
        try {
            return this.connection.getURL().toURI();
        }
        catch (URISyntaxException ex) {
            throw new IllegalStateException("Could not get HttpURLConnection URI: " + ex.getMessage(), ex);
        }
    }

//
//    @Override
//    protected ClientHttpResponse executeInternal(HttpHeaders headers, byte[] bufferedOutput) throws IOException {
//        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
//            String headerName = entry.getKey();
//            for (String headerValue : entry.getValue()) {
//                this.connection.addRequestProperty(headerName, headerValue);
//            }
//        }
//        OAuthConsumer oAuthConsumer = new DefaultOAuthConsumer(TWITTER_CONSUMER_KEY,TWITTER_CONSUMER_SECRET);
//        oAuthConsumer.setTokenWithSecret(TWITTER_OAUTH_TOKEN,TWITTER_OAUTH_TOKE_SECRET);
//
//        if(bufferedOutput.length > 0){
////            oAuthConsumer.setAdditionalParameters(OAuth.decodeForm(new String(bufferedOutput)));
//        }
//        try {
//            oAuthConsumer.sign(this.connection);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        this.connection.connect();
//        if (bufferedOutput.length > 0) {
//            FileCopyUtils.copy(bufferedOutput, this.connection.getOutputStream());
//        }
//        return new SpringSproutClientHttpResponse(this.connection);
//    }

    @Override
    protected OutputStream getBodyInternal(HttpHeaders headers) throws IOException {
        return null;
    }

    @Override
    protected ClientHttpResponse executeInternal(HttpHeaders headers) throws IOException {
        return null;
    }
}
