package springsprout.modules.file;

import java.util.List;

import springsprout.common.dao.GenericDao;
import springsprout.domain.UploadFile;

public interface FileRepository extends GenericDao<UploadFile> {

	List<UploadFile> list();

}