package springsprout.modules.feed.support;

import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;
import springsprout.domain.Notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 11. 27
 * Time: 오후 4:14:11
 */

@Component
public class NoticesAtomView extends AbstractAtomFeedView {

    @Override
    protected void buildFeedMetadata(Map model, Feed feed, HttpServletRequest request) {
        feed.setId("tag:springsprout.org");
        feed.setTitle("SpringSprout Notices");
        feed.setEncoding("UTF8");
        feed.setCopyright("봄싹");
        List<Notice> notices = (List<Notice>) model.get("notices");
        for (Notice notice : notices) {
            Date date = notice.getCreated();
            if (feed.getUpdated() == null || date.compareTo(feed.getUpdated()) > 0) {
                feed.setUpdated(date);
            }
        }
    }

    @Override
    protected List<Entry> buildFeedEntries(Map<String, Object> map, HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<Entry> entries = new ArrayList<Entry>();

        List<Notice> notices = (List<Notice>) map.get("notices");

        for(Notice notice : notices){
            Entry entry = new Entry();
            String date = String.format("%1$tY-%1$tm-%1$td", notice.getCreated());
            entry.setId(String.format("tag:springsprout.org,%s:%d", date, notice.getId()));
            entry.setTitle(String.format("On %s, %s", date, notice.getTitle()));
            entry.setUpdated(notice.getCreated());

            Content summary = new Content();
            summary.setType("text/html");
            summary.setValue(notice.getContents());
            entry.setSummary(summary);

            entries.add(entry);
        }

        return entries;
    }
}
