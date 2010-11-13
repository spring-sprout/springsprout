package springsprout.modules.term.support;

import springsprout.domain.DevTerm;
import springsprout.domain.KorTerm;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010. 1. 13
 * Time: 오전 7:19:48
 * To change this template use File | Settings | File Templates.
 */
public class TermAddDTO {

    private DevTerm devTerm;

    private KorTerm korTerm;

    public DevTerm getDevTerm() {
        return devTerm;
    }

    public void setDevTerm(DevTerm devTerm) {
        this.devTerm = devTerm;
    }

    public KorTerm getKorTerm() {
        return korTerm;
    }

    public void setKorTerm(KorTerm korTerm) {
        this.korTerm = korTerm;
    }
}
