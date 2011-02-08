package springsprout.service.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.domain.Member;
import springsprout.modules.member.MemberRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 4. 19
 * Time: 오전 11:26:33
 */

@Service
@Transactional
public class NotificationOffInitService {

    @Autowired MemberRepository memberRepository;

    public void notificationOffAllMember(){
        for(Member member : memberRepository.getAll()){
            member.setIsAllowedEmail(false);
            member.setIsAllowedGoogleTalk(false);
        }
    }

}
