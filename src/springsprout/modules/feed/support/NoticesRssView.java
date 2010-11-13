package springsprout.modules.feed.support;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Channel;
import springsprout.domain.Notice;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 27
 * Time: 오후 11:16:36
 */
@Component
public class NoticesRssView extends AbstractRssFeedView{

    @Override
    protected void buildFeedMetadata(Map<String, Object> map, Channel feed, HttpServletRequest req) {
        feed.setTitle("SpringSprout Notices");
        feed.setDescription("SpringSprount Feed");
        feed.setLink("http://springsprout.org");
    }

    @Override
    protected List<Item> buildFeedItems(Map<String, Object> map, HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<Item> items = new ArrayList<Item>();
        List<Notice> notices = (List<Notice>) map.get("notices");

        for(Notice notice : notices){
            Item entry = new Item();
            entry.setTitle(String.format(notice.getTitle()));
            entry.setAuthor(notice.getMember().getName());
            entry.setPubDate(notice.getCreated());

            Description description = new Description();
            description.setType("text/html");
            description.setValue(notice.getContents());
            entry.setDescription(description);

            items.add(entry);
        }

        return items;
    }
}
