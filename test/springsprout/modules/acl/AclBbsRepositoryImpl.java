package springsprout.modules.acl;

import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.security.AclBbs;

@Repository
public class AclBbsRepositoryImpl extends HibernateGenericDao<AclBbs> implements AclBbsRepository {

	
	public void delete(AclBbs bbs){
		getCurrentSession().delete(bbs);
	}
	
	public AclBbs get(AclBbs bbs){
		return (AclBbs) getCurrentSession().get(entityClass, bbs.getId());
	}
}
