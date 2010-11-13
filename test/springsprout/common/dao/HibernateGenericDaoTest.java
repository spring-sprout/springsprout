package springsprout.common.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import springsprout.common.test.DBUnitSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/testContext.xml")
@Transactional
public class HibernateGenericDaoTest extends DBUnitSupport{

	@Autowired SampleDao dao;
	
	@Test
	public void add() throws Exception {
		SampleDomain entity = new SampleDomain();
		dao.add(entity);
		assertThat(dao.getAll().size(), is(1));
	}
	
	@Test
	public void getAll() throws Exception {
		insertXmlData("testData.xml");
		assertThat(dao.getAll().size(), is(2));
	}
	
	@Test
	public void getById() throws Exception {
		insertXmlData("testData.xml");
		assertThat(dao.getById(1).getName(), is("keesun"));
	}
	
	@Test
	public void delete() throws Exception {
		insertXmlData("testData.xml");
		SampleDomain entity = dao.getById(1);
		dao.delete(entity);
		assertThat(dao.getAll().size(), is(1));
		
		dao.deleteById(2);
		assertThat(dao.getAll().size(), is(0));
	}
	
	@Test
	public void update() throws Exception {
		insertXmlData("testData.xml");
		// entity is detached object
		SampleDomain entity = new SampleDomain();
		entity.setId(1);
		entity.setName("whiteship");
		
		dao.update(entity);
		// now, entity has been persistent object
		entity.setName("helols");
		dao.flush();
		assertThat(dao.getById(1).getName(), is("helols"));
	}
	
	@Test
	public void merge() throws Exception {
		insertXmlData("testData.xml");
		// entity is detached object
		SampleDomain entity = new SampleDomain();
		entity.setId(1);
		entity.setName("whiteship");
		
		SampleDomain newEntity = dao.merge(entity);
		// newEntity is persistent object, but entity is still detached object
		newEntity.setName("helols");
		entity.setName("nije");
		dao.flush();
		assertThat(dao.getById(1).getName(), is("helols"));
	}
	
	@Test
	public void save() throws Exception {
		insertXmlData("testData.xml");
		assertThat(dao.getAll().size(), is(2));
		
		SampleDomain e1 = new SampleDomain();
		dao.save(e1);
		assertThat(dao.getAll().size(), is(3));
		
		SampleDomain e2 = dao.getById(1);
		e2.setName("helols");
		dao.save(e2);
		assertThat(dao.getAll().size(), is(3));
	}
	
}
