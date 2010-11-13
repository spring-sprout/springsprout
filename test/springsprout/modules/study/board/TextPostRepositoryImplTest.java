package springsprout.modules.study.board;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import springsprout.common.test.DBUnitSupport;
import springsprout.domain.study.board.TextPost;
import springsprout.modules.study.board.textPost.TextPostRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/testContext.xml")
@Transactional
public class TextPostRepositoryImplTest {

	@Autowired TextPostRepository repository;

    @Test
    public void di(){
        assertNotNull(repository);
    }
	
	@Test
	public void getAllAndGetRootPostsOnly() {
		TextPost rootPost = new TextPost();
		rootPost.setTitle("스프링의 장점은?");
		repository.add(rootPost);
		
		TextPost post2 = new TextPost();
		post2.setTitle("스프링의 장점은? 2");
		repository.add(post2);
		
		TextPost post3 = new TextPost();
		post3.setTitle("스프링의 장점은? 3");
		repository.add(post3);

        TextPost post4 = new TextPost();
		post3.setTitle("Why not?");
		repository.add(post4);

        post3.addBranch(post4);

        assertThat(repository.getAll().size(), is(4));
		assertThat(repository.getRootPostList().size(), is(3));
	}
	
	@Test
	public void addReplyToParent() {
		TextPost rootPost1 = new TextPost();
		rootPost1.setTitle("스프링의 장점은?");
		repository.add(rootPost1);
		
		TextPost childPost1 = new TextPost();
		childPost1.setTitle("복잡한 엔터프라이즈 개발 간소화");
		childPost1.setRootPost(rootPost1);
		repository.add(childPost1);
		
		TextPost childPost2 = new TextPost();
		childPost2.setTitle("높은 추상화를 통한 객체지향 프로그래밍 극대화");
		childPost2.setRootPost(rootPost1);
		repository.add(childPost2);
		
		TextPost rootPost2 = new TextPost();
		rootPost2.setTitle("스프링의 장점은? 2");
		repository.add(rootPost2);
		
		TextPost rootPost3 = new TextPost();
		rootPost3.setTitle("스프링의 장점은? 3");
		repository.add(rootPost3);
		
		TextPost rootPost = childPost1.getRootPost();
		List<TextPost> branchPosts = repository.getBranchPostsOf(rootPost);
        
		String result = "";
        for (TextPost textPost : branchPosts) {
			result += textPost.getTitle();
		}

        assertThat(result, containsString(childPost1.getTitle()));
        assertThat(result, containsString(childPost2.getTitle()));
	}
	
}
