package springsprout.modules.notice;

import java.util.List;

import springsprout.common.dao.GenericDao;
import springsprout.domain.Notice;
import springsprout.modules.notice.support.NoticeCriteria;


public interface NoticeRepository extends GenericDao<Notice> {

    List<Notice> getAllByCreatedOrderDesc();
    
    List<Notice> getListSorted(NoticeCriteria cri);
    
    int getTotalRowsCount(NoticeCriteria cri);

    Notice getTheLastedOne();
    
}
