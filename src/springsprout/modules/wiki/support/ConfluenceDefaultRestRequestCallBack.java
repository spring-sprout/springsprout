package springsprout.modules.wiki.support;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 3
 * Time: 오후 10:19:49
 */
@Component
public class ConfluenceDefaultRestRequestCallBack implements RequestCallback {
    public void doWithRequest(ClientHttpRequest clientHttpRequest) throws IOException {
        HttpHeaders headers = clientHttpRequest.getHeaders();
        headers.add("Authorization", "Basic d2hpdGVzaGlwOjEyMzIzcQ==");
        headers.add("Accept", "application/json");
    }
}