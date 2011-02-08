package springsprout.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class CommentTest {
	
	@Test
	public void applyAtHtml() throws Exception {
		Comment comment = new Comment();
		comment.setComment("@Keesun 하이?");
		comment.applyAtHtml();
		assertThat(comment.getComment(), is("<span id=\"at\">@Keesun</span> 하이?"));
	}

}
