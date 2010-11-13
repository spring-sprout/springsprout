package springsprout.modules.${module};

import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;

import java.util.List;

@Repository
public class ${domainClass}RepositoryImpl extends HibernateGenericDao<${domainClass}> implements ${domainClass}Repository {

}
