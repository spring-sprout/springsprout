package springsprout.modules.main;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import springsprout.common.dao.GenericDao;
import springsprout.domain.Graffiti;
import springsprout.modules.main.support.GraffitiDTO;

public interface GraffitiRepository extends GenericDao<Graffiti> {
	
	void deleteFirstGraffiti() throws DataAccessException;
	
	int getTotalRowCount() throws DataAccessException;
	
	List<GraffitiDTO> getByWriteDate(Date writeDate) throws DataAccessException;
	
	List<GraffitiDTO> getListByID(int lastID) throws DataAccessException;

	List<GraffitiDTO> getAllContents();

}