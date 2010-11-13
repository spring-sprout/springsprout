package springsprout.modules.member.exception;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 5. 19
 * Time: 오후 8:08:13
 */
public class MemberNotFoundException extends RuntimeException{

    public MemberNotFoundException(Integer id) {
        super("id에 해당하는 Member를 찾지 못했습니다.");
    }
}
