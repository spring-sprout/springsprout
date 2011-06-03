package springsprout.modules.notice;

import springsprout.common.dao.GenericDao;
import springsprout.domain.Notice;
import springsprout.modules.notice.support.NoticeCriteria;

import java.util.List;


public interface NoticeRepository extends GenericDao<Notice> {

    List<Notice> getAllByCreatedOrderDesc();
    
    List<Notice> getListSorted(NoticeCriteria cri);
    
    int getTotalRowsCount(NoticeCriteria cri);

    Notice getTheLastedOne();

    List<Notice> findRecentNotice(int size);
}
