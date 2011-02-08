package springsprout.common.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class HibernateGenericDao<E> implements GenericDao<E> {

	protected Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public HibernateGenericDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		Type type = genericSuperclass.getActualTypeArguments()[0];
       if (type instanceof ParameterizedType) {
         this.entityClass = (Class) ((ParameterizedType) type).getRawType();
       } else {
         this.entityClass = (Class) type;
       }
	}

	@Autowired
	protected SessionFactory sessionFactory;

	public void add(E entity) {
		getCurrentSession().save(entity);
	}

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria getCriteria() {
        return sessionFactory.getCurrentSession().createCriteria(entityClass);
    }

	@SuppressWarnings("unchecked")
	public List<E> getAll() {
		return getCurrentSession().createCriteria(entityClass)
				.list();
	}

	@SuppressWarnings("unchecked")
	public E getById(Serializable id){
		return (E) getCurrentSession().get(entityClass, id);
	}
	
	public void delete(E entity){
		getCurrentSession().delete(entity);
	}
	
	public void update(E entity){
		getCurrentSession().update(entity);
	}
	
	public void flush(){
		getCurrentSession().flush();
	}
	
	@SuppressWarnings("unchecked")
	public E merge(E entity){
		return (E) getCurrentSession().merge(entity);
	}

	public void save(E entity) {
		getCurrentSession().save(entity);
	}

    public void clear() {
        getCurrentSession().clear();
    }

    public void deleteById(Serializable id) {
		getCurrentSession().delete(getById(id));
	}

}
