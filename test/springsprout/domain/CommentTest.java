package springsprout.domain;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;


public class CommentTest {
	
	@Test
	public void applyAtHtml() throws Exception {
		Comment comment = new Comment();
		comment.setComment("@Keesun 하이?");
		comment.applyAtHtml();
		assertThat(comment.getComment(), is("<span id=\"at\">@Keesun</span> 하이?"));
	}

}
