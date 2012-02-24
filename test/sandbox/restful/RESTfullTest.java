/**
 * Created by IntelliJ IDEA.
 * User: isyoon
 * Date: 2010. 7. 5
 * Time: 오후 2:20:23
 * enjoy springsprout ! development!
 */
package sandbox.restful;

import org.codehaus.jackson.JsonNode;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "restfulTestContext.xml")
public class RESTfullTest {
    @Autowired
    RestTemplate restTemplate;

    private final static String DEFAULT_REST_BASE_URL = "http://api.twitter.com/1/{uname}/lists";
    private final static String TWITTER_LIST_ID = "/{id}";
    private final static String TWITTER_FORMAT = ".json";
    private final static MediaType MEDIA_TYPE = APPLICATION_FORM_URLENCODED;

    Map<String, String> urlVariables;

    @Before
    public void setUp() {
        urlVariables = new HashMap<String, String>();
        urlVariables.put("uname", "isyooon");
    }

    @Test
    public void diTest() {
        assertThat(restTemplate, is(notNullValue()));
        assertThat(restTemplate.getMessageConverters().size(), is(2));
    }

    @Ignore
    public void tweetPostList() {
        LinkedMultiValueMap<String, String> paramMap = new LinkedMultiValueMap<String, String>();
        paramMap.add("name", "jcotest");
        paramMap.add("mode", "public");
        paramMap.add("description", "restTemplate");
        JsonNode result = restTemplate.postForObject(getUrl(POST), paramMap, JsonNode.class, urlVariables);
        assertThat(result, is(notNullValue()));
    }

    @Ignore
    public void tweetUpdatelist() {
        urlVariables.put("id", "jcotest");
        urlVariables.put("name", "jcotest");
        urlVariables.put("mode", "public");
        urlVariables.put("description", "update");

        try{
            restTemplate.put(getUrl(PUT),null, urlVariables);
        }catch (HttpClientErrorException he){
            fail(he.getMessage());
        }
    }

    @Ignore
    public void tweetGetList() {
        JsonNode result = restTemplate.getForObject(getUrl(GET), JsonNode.class, urlVariables);
        assertThat(result.path("lists").size(), is(1));
        System.out.println("lists >> "+ result.path("lists"));
    }

    @Ignore
    public void tweetDeleteList() {
        urlVariables.put("id", "jcotest");
        try {
            restTemplate.delete(getUrl(DELETE), urlVariables);
        } catch (HttpClientErrorException he) {
            fail(he.getMessage());
        }
    }

    private String getUrl(HttpMethod httpMethod) {
        switch (httpMethod) {
            case GET:
                return DEFAULT_REST_BASE_URL + TWITTER_FORMAT + "?cursor=-1";
            case POST:
                return DEFAULT_REST_BASE_URL + TWITTER_FORMAT;
            case PUT:
                return DEFAULT_REST_BASE_URL + TWITTER_LIST_ID + TWITTER_FORMAT+"?name={name}&mode={mode}&description={description}";
            case DELETE:
                return DEFAULT_REST_BASE_URL + TWITTER_LIST_ID + TWITTER_FORMAT;
            default:
                return DEFAULT_REST_BASE_URL + TWITTER_LIST_ID + TWITTER_FORMAT;
        }
    }
}
