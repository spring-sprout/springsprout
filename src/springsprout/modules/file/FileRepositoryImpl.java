package springsprout.modules.file;

import java.util.List;

import org.springframework.stereotype.Repository;

import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.UploadFile;

@Repository
public class FileRepositoryImpl extends HibernateGenericDao<UploadFile> implements FileRepository{

	@SuppressWarnings("unchecked")
	public List<UploadFile> list() {
		return getCurrentSession().createQuery("from UploadFile order by uploadDate desc").list();
	}

}
