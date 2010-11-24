package springsprout.modules.study.post;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.test.DBUnitSupport;
import springsprout.domain.study.board.Post;
import springsprout.modules.study.post.PostResposiroty;
import springsprout.modules.study.post.imagePost.ImagePostRepository;
import springsprout.modules.study.post.textPost.TextPostRepository;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 10. 14
 * Time: 오후 2:51:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/testContext.xml")
@Transactional
public class PostRespositoryImplTest extends DBUnitSupport{

    @Autowired PostResposiroty postResposiroty;
    @Autowired TextPostRepository textPostRepository;
    @Autowired ImagePostRepository imagePostRepository;

    @Test
    public void di(){
        assertNotNull(postResposiroty);
        assertNotNull(textPostRepository);
        assertNotNull(imagePostRepository);
    }

    @Test
    public void postType() throws Exception {
        insertXmlData("testData.xml");
        List<Post> post = postResposiroty.getAll();
        
        assertThat(postResposiroty.getAll().size(), is(5));
        assertThat(textPostRepository.getAll().size(), is(3));
        assertThat(imagePostRepository.getAll().size(), is(2));
    }
}
