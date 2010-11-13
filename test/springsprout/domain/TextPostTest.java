package springsprout.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import springsprout.domain.study.board.TextPost;

public class TextPostTest {

	@Test
	public void createChildPost() {
		TextPost parentPost = new TextPost();
		parentPost.setId(6);
		parentPost.setTitle("Converter는 어떻게 사용하나요?");
		
		TextPost post = new TextPost();
		post.setId(1);
		
		post.setRootPost(parentPost);
		
		assertThat(post.getRootPost().getTitle(), is(parentPost.getTitle()));
		
	}
}
