package springsprout.modules.file;

import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.UploadFile;

import java.util.List;

@Repository
public class FileRepositoryImpl extends HibernateGenericDao<UploadFile> implements FileRepository{

	@SuppressWarnings("unchecked")
	public List<UploadFile> list() {
		return getCurrentSession().createQuery("from UploadFile order by uploadDate desc").list();
	}

}
