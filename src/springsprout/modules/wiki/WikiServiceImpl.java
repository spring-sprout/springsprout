package springsprout.modules.wiki;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import springsprout.domain.WikiFeed;
import springsprout.domain.WikiSpace;
import springsprout.modules.wiki.support.ConfluenceDefaultRestRequestCallBack;
import springsprout.modules.wiki.support.ConfluenceException;
import springsprout.modules.wiki.support.ConfluenceWikiSpaceRestResponseExtractor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.    
 * User: whiteship
 * Date: 2010. 4. 3
 * Time: 오후 10:17:40
 */
@Service
@Transactional
public class WikiServiceImpl implements WikiService {

    public static final String SPACE_LIST_URL = "http://wiki.springsprout.org/rest/prototype/1/space?os_authType=basic";
    public static final String FEED_URL = "http://wiki.springsprout.org/createrssfeed.action?types=page&sort=modified&showContent=true&spaces=conf_all&labelString%3D&rssType=atom&maxResults=10&timeSpan=30&publicFeed=true&title=SpringSprout+RSS+Feed&showDiff=false";

    @Autowired ConfluenceDefaultRestRequestCallBack restRequestCallBack;
    @Autowired ConfluenceWikiSpaceRestResponseExtractor restResponseExtractor;
    @Autowired WikiSpaceRepository wikiSpaceRepository;
    @Autowired WikiFeedRepository wikiFeedRepository;

    private List<WikiSpace> spaceListByRestUrl() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.execute(SPACE_LIST_URL, HttpMethod.GET, restRequestCallBack, restResponseExtractor);
    }

    private List<WikiFeed> wikiFeedListByAtom() {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = null;
        try {
            feed = input.build(new XmlReader(new URL(FEED_URL)));
        } catch (FeedException e) {
            throw new ConfluenceException("wiki feed not available", e);
        } catch (IOException e) {
            throw new ConfluenceException("wiki feed url [" + FEED_URL + "] is not valid", e);
        }
        @SuppressWarnings("unchecked")
		List<SyndEntry> list = feed.getEntries();
        List<WikiFeed> wikiFeedList = new ArrayList<WikiFeed>();
        for(int i = 0 ; i < list.size() ; i++){
            SyndEntry entry = list.get(i);
            WikiFeed wikiFeed = new WikiFeed();
            wikiFeed.setAuthor(entry.getAuthor());
            wikiFeed.setLink(entry.getLink());
            wikiFeed.setPublished(entry.getPublishedDate());
            wikiFeed.setTitle(entry.getTitle());
            wikiFeed.setDescr(entry.getDescription().getValue());
            wikiFeedList.add(wikiFeed);
        }
        return wikiFeedList;
    }

    public List<WikiSpace> spaceList() {
        return wikiSpaceRepository.getAll();
    }

    public List<WikiFeed> wikiFeedList() {
        return  wikiFeedRepository.getAll();
    }

    public void refresh() {
        List<WikiSpace> spaceList = spaceListByRestUrl();
        for(WikiSpace wikiSpace : spaceList){
            if(wikiSpaceRepository.findByKey(wikiSpace.getKey()) == null)
                wikiSpaceRepository.add(wikiSpace);
        }

        wikiFeedRepository.deleteAll();

        List<WikiFeed> feedList = wikiFeedListByAtom();
        for(WikiFeed wikiFeed : feedList){
            wikiFeedRepository.add(wikiFeed);
        }
    }

}
