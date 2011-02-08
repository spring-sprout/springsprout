package springsprout.modules.right;

import springsprout.common.dao.GenericDao;
import springsprout.domain.Right;

import java.util.List;

public interface RightRepository extends GenericDao<Right>{

	List<Right> getRightList();

}