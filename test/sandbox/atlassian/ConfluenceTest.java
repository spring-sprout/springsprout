package sandbox.atlassian;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 25
 * Time: 오후 11:37:36
 */
public class ConfluenceTest {

    public static final String SPACE_LIST_URL = "http://wiki.springsprout.org/rest/prototype/1/space?os_authType=basic";
    public static final String JAVA_LIST_URL = "http://wiki.springsprout.org/rest/prototype/1/space/JAVA?os_authType=basic";
    public static final String CONTENT_URL = "http://wiki.springsprout.org/rest/prototype/1/content/426332?os_authType=basic";

    public static final String FEED_URL = "http://wiki.springsprout.org/createrssfeed.action?types=page&sort=modified&showContent=true&spaces=conf_all&labelString%3D&rssType=atom&maxResults=10&timeSpan=30&publicFeed=true&title=SpringSprout+RSS+Feed&showDiff=false";

    /**
     * http://confluence.atlassian.com/display/CONFDEV/Using+the+REST+APIs
     */
    @Test
    @Ignore
    public void testGetWikiSpaceList() throws JSONException {
        RequestCallback callback = new SpringSproutDefaultRestRequestCallBack();
        ResponseExtractor<String> extractor = new SpringSproutJsonRestResponseExtractor();

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.execute(SPACE_LIST_URL, HttpMethod.GET, callback, extractor);
        System.out.println(result);

        // JSON -> Object
        // ref: http://decoder.tistory.com/38
        JSONObject spaceListJSON = new JSONObject(result);
        JSONArray spaceJSONArray = spaceListJSON.getJSONArray("space");
        for(int i = 0 ; i < spaceJSONArray.length() ; i++){
            JSONObject spaceJSON = spaceJSONArray.getJSONObject(i);
            System.out.println(spaceJSON.getString("name"));
            System.out.println(spaceJSON.getString("key"));
            System.out.println(spaceJSON.getString("description"));
        }

        result = restTemplate.execute(JAVA_LIST_URL, HttpMethod.GET, callback, extractor);
        System.out.println(result);

        result = restTemplate.execute(CONTENT_URL, HttpMethod.GET, callback, extractor);
        System.out.println(result);
    }

    @Test
    @Ignore
    public void feed() throws FeedException, IOException {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(new URL(FEED_URL)));
        List list = feed.getEntries();
        for(int i = 0 ; i < list.size() ; i++){
            SyndEntry entry = (SyndEntry)list.get(i);
            System.out.println("=========[" + i + "]=========");
            System.out.println(entry.getAuthor());
            System.out.println(entry.getLink());
            System.out.println(entry.getTitle());
            System.out.println(entry.getPublishedDate());
//            System.out.println(entry.getDescription());
        }
    }

    static class SpringSproutDefaultRestRequestCallBack implements RequestCallback {
        public void doWithRequest(ClientHttpRequest clientHttpRequest) throws IOException {
            HttpHeaders headers = clientHttpRequest.getHeaders();
            headers.add("Authorization", "Basic d2hpdGVzaGlwOjEyMzIzcQ==");
            headers.add("Accept", "application/json");
        }
    }

    static class SpringSproutJsonRestResponseExtractor implements ResponseExtractor<String> {

        public String extractData(ClientHttpResponse clientHttpResponse) throws IOException {
            return convertStreamToString(clientHttpResponse.getBody());
        }

        /**
         * To convert the InputStream to String we use the BufferedReader.readLine()
         * method. We iterate until the BufferedReader return null which means
         * there's no more data to read. Each line will appended to a StringBuilder
         * and returned as String.
         * <p/>
         * copy from : http://www.kodejava.org/examples/266.html
         *
         * @param is InputStream to be conveted.
         * @return if the parameter is not null return conveted string, or empty string("");
         * @throws IOException
         */
        public String convertStreamToString(InputStream is) throws IOException {
            if (is != null) {
                StringBuilder sb = new StringBuilder();
                String line;

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                } finally {
                    is.close();
                }
                return sb.toString();
            } else {
                return "";
            }
        }
    }



}
