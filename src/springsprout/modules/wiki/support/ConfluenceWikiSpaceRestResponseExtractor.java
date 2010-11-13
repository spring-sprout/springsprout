package springsprout.modules.wiki.support;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseExtractor;
import springsprout.domain.WikiSpace;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 3
 * Time: 오후 10:21:27
 */
@Component
public class ConfluenceWikiSpaceRestResponseExtractor implements ResponseExtractor<List<WikiSpace>> {

    public List<WikiSpace> extractData(ClientHttpResponse clientHttpResponse) throws IOException {
        String result = convertStreamToString(clientHttpResponse.getBody());
        List<WikiSpace> wikiSpaces = new ArrayList<WikiSpace>();
        try {
            JSONObject spaceListJSON = new JSONObject(result);
            JSONArray spaceJSONArray = spaceListJSON.getJSONArray("space");
            for (int i = 0; i < spaceJSONArray.length(); i++) {
                WikiSpace space = new WikiSpace();
                JSONObject spaceJSON = spaceJSONArray.getJSONObject(i);
                space.setName(spaceJSON.getString("name"));
                space.setKey(spaceJSON.getString("key"));
                space.setLink(spaceJSON.getJSONObject("link").getString("href"));
                space.setDescr(spaceJSON.getString("description"));
                wikiSpaces.add(space);
            }
        } catch (JSONException e) {
            throw new ConfluenceException("봄싹 위키 Space 목록 가져오기 실패", e);
        }
        return wikiSpaces;
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


