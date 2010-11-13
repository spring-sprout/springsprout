package springsprout.common.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<E> {

	void add(E entity);

	List<E> getAll();

	E getById(Serializable id);

	void delete(E entity);

	void deleteById(Serializable id);

	void update(E entity);

	void flush();

	E merge(E entity);
	
	void save(E entity);

    void clear();

}