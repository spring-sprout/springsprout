package springsprout.modules.right;

import java.util.List;

import springsprout.common.dao.GenericDao;
import springsprout.domain.Right;

public interface RightRepository extends GenericDao<Right>{

	List<Right> getRightList();

}