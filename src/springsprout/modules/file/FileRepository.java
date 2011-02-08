package springsprout.modules.file;

import springsprout.common.dao.GenericDao;
import springsprout.domain.UploadFile;

import java.util.List;

public interface FileRepository extends GenericDao<UploadFile> {

	List<UploadFile> list();

}