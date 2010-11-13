package springsprout.modules.study.meeting.presentation;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;

import springsprout.domain.Presentation;
import springsprout.domain.Member;
import springsprout.domain.Comment;
import springsprout.service.security.SecurityService;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 17
 * Time: 오후 11:48:34
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class PresentationServiceTest {

    PresentationService service;
    @Mock PresentationRepository presentationRepository;
    @Mock SecurityService securityService;

    @Before
    public void setUp() {
        service = new PresentationService();
        service.repository = presentationRepository;
        service.securityService = securityService;
    }


    @Test
    public void addCommentToPresentation() {
        Member member = new Member();
        Presentation presentation = new Presentation();
        Comment comment = new Comment();
        comment.setComment("방가 방가");

        when(securityService.getCurrentMember()).thenReturn(member);

        service.addComment(presentation, comment);

        assertThat(comment.getCreated(), is(notNullValue()));
        System.out.println(comment.getCreated());
        assertThat(comment.getWriter(), is(member));
        verify(presentationRepository).update(presentation);
    }

    @Test
    public void presentationViewCount() {
        int presentationId = 1;
        Presentation p = new Presentation();
        p.setId(presentationId);
        assertThat(p.getViewCount(), is(0));

        when(presentationRepository.getById(presentationId)).thenReturn(p);

        service.viewPresentation(presentationId);
        assertThat(p.getViewCount(), is(1));
        service.viewPresentation(presentationId);
        assertThat(p.getViewCount(), is(2));
    }

    @Test
    public void commentCount() {
        Presentation p = new Presentation();
        Member member = new Member();
        assertThat(p.getCommentCount(), is(0));

        when(securityService.getCurrentMember()).thenReturn(member);

        Comment comment = new Comment();
        comment.setComment("hi");
        service.addComment(p, comment);

        assertThat(p.getCommentCount(), is(1));
    }


}
