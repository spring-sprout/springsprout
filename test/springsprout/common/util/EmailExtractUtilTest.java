package springsprout.common.util;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import springsprout.common.util.EmailExtractUtil;
import springsprout.domain.Member;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;


/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 30
 * Time: 오후 11:30:29
 */
public class EmailExtractUtilTest {

    @Test
    public void testExtractMailAllowedEmailFrom() {
        List<Member> members = new ArrayList<Member>();
        Member member = new Member();
        member.setIsAllowedEmail(true);
        member.setEmail("whiteship2000@gmail.com");
        members.add(member);
        member = new Member();
        member.setIsAllowedEmail(false);
        member.setEmail("whiteship2001@gmail.com");
        members.add(member);
        member = new Member();
        member.setIsAllowedEmail(true);
        member.setEmail("whiteship20om");
        members.add(member);

        String[] mails = EmailExtractUtil.extractMailAllowedEmailFrom(members);
        assertThat(mails.length, is(1));
    }

    @Test
    public void testExtractMessageAllowedEmailCollectionFrom() {
        List<Member> members = new ArrayList<Member>();
        Member member = new Member();
        member.setIsAllowedGoogleTalk(true);
        member.setEmail("whiteship2000@gmail.com");
        members.add(member);
        member = new Member();
        member.setIsAllowedGoogleTalk(false);
        member.setEmail("whiteship2001@gmail.com");
        members.add(member);
        member = new Member();
        member.setIsAllowedGoogleTalk(true);
        member.setEmail("whiteship20om");
        members.add(member);
        Collection<String> mails = EmailExtractUtil.extractMessageAllowedEmailCollectionFrom(members);
        assertThat(mails.size(), is(1));
    }

    @Test
    public void testExtractEmailCollectionFrom() {
        Member member = new Member();
        member.setEmail("whiteship2000@gmail.com");
        Collection<String> mails = EmailExtractUtil.extractEmailCollectionFrom(member);
        assertThat(mails.size(), is(1));

        member = new Member();
        member.setEmail("whiteship20om");
        mails = EmailExtractUtil.extractEmailCollectionFrom(member);
        assertThat(mails, is(nullValue()));
    }

    @Test
    public void testExtractEmailArrayForm() {
        Member member = new Member();
        member.setEmail("whiteship2000@gmail.com");
        String[] mails = EmailExtractUtil.extractEmailArrayForm(member);
        assertThat(mails.length, is(1));

        member = new Member();
        member.setEmail("whiteship20om");
        mails = EmailExtractUtil.extractEmailArrayForm(member);
        assertThat(mails, is(nullValue()));
    }
}
