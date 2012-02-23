package sandbox.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.test.DBUnitSupport;
import springsprout.domain.Member;
import springsprout.modules.member.MemberRepository;

import javax.sql.DataSource;


/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 7
 * Time: 오전 8:33:34
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@Transactional
public class LearningTest extends DBUnitSupport{

    @Autowired DataSource dataSource;
    @Autowired MemberRepository memberRepository;

    @Test
    public void sqlUpdate() throws Exception {
        insertXmlData("testData.xml");

        String updateMemberNameSql = "update member set name = \'keesun\' where id = \'1\'";
        SqlUpdate memberNameUpdate = new SqlUpdate(dataSource, updateMemberNameSql);
        memberNameUpdate.update();
        Member member2 = memberRepository.getById(1);
        
//        assertThat(member2.getName(), is("keesun"));
    }


}
