/**
 * Created by IntelliJ IDEA.
 * User: helols
 * Date: 2009. 10. 31
 * Time: 오전 11:18:09
 * enjoy springsprout ! development!
 */
package springsprout.common.util;

import static org.hamcrest.Matchers.is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import springsprout.domain.Member;
import springsprout.domain.Notice;
import springsprout.modules.admin.support.MemberMgtDTO;
import springsprout.modules.main.support.NoticeDTO;

import java.util.Date;

public class BeanUtilsTest {

    Notice notice;
    NoticeDTO noticeDTO;
    Member member;
    MemberMgtDTO memberMgtDto;

    @Before public void setUp(){
        notice = new Notice();
        noticeDTO = new NoticeDTO();
        member = new Member();
        memberMgtDto = new MemberMgtDTO();
    }

    @Test public void beanCopy(){
        testBeanSetValue();
        Boolean isCustomTransfer = false;
        BeanUtils.beanCopy(noticeDTO, notice, isCustomTransfer);

        Assert.assertThat(noticeDTO.getTitle(),is(notice.getTitle()));
        Assert.assertThat(noticeDTO.getCreated(),is(notice.getCreated()));
        Assert.assertThat(noticeDTO.getId(),is(notice.getId()));
    }

    @Test public void beanCopyWithAnnotation(){
        memberMgtDto.setPassword("123");
        BeanUtils.beanCopy(member, memberMgtDto);
        Assert.assertThat(member.getNewPassword(),is("123"));
    }

    private void testBeanSetValue() {
        notice.setTitle("테스트제목");
        notice.setCreated(new Date());
        notice.setId(123);
    }

}
