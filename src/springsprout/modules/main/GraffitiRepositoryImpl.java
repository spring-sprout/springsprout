package springsprout.modules.main;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.Graffiti;
import springsprout.modules.main.support.GraffitiDTO;

import java.util.Date;
import java.util.List;

@Repository
public class GraffitiRepositoryImpl extends HibernateGenericDao<Graffiti> implements GraffitiRepository {

	/**
	 * 첫번째 낙서를 찾아서 삭제한다.
	 * 첫번째 낙서는 가장 낮은 id 를 가진 Graffiti 를 기준으로 한다.
	 */
	public void deleteFirstGraffiti() throws DataAccessException {
		deleteById((Integer)getCurrentSession()
				.createCriteria(Graffiti.class).setProjection(Projections.min("id")).uniqueResult());
	}

	/**
	 * 총 낙서 갯수를 반환한다.
	 */
	public int getTotalRowCount() throws DataAccessException {
		return (Integer)getCurrentSession()
			.createCriteria(Graffiti.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<GraffitiDTO> getByWriteDate(Date writeDate) throws DataAccessException {
		Criteria c = getCurrentSession().createCriteria(Graffiti.class)
			.createAlias("member", "member")
			.add(Expression.ge("writeDate", writeDate))
			.addOrder(Order.asc("writeDate"))
			.setProjection(
				Projections.projectionList()
				.add(Projections.property("id").as("id"))
				.add(Projections.property("member.name").as("writerName"))
				.add(Projections.property("member.avatar").as("writerAvatar"))
				.add(Projections.property("contents").as("contents"))
				.add(Projections.property("writeDate").as("writtenDate"))
			)
			.setResultTransformer(new AliasToBeanResultTransformer(GraffitiDTO.class));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<GraffitiDTO> getAllContents() {
		Criteria c = getCurrentSession().createCriteria(Graffiti.class)
			.createAlias("member", "member")
			.addOrder(Order.asc("writeDate"))
			.setProjection(
				Projections.projectionList()
				.add(Property.forName("id").as("id"))
				.add(Property.forName("member.name").as("writerName"))
				.add(Property.forName("member.avatar").as("writerAvatar"))
				.add(Property.forName("contents").as("contents"))
                .add(Projections.property("writeDate").as("writtenDate"))
			)
			.setResultTransformer(new AliasToBeanResultTransformer(GraffitiDTO.class));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<GraffitiDTO> getListByID( int lastID) throws DataAccessException {
		Criteria c = getCurrentSession().createCriteria(Graffiti.class)
			.createAlias("member", "member")
			.add( Expression.gt( "this.id", lastID))
			.addOrder(Order.asc("writeDate"))
			.setProjection(
				Projections.projectionList()
				.add(Property.forName("id").as("id"))
				.add(Property.forName("member.name").as("writerName"))
				.add(Property.forName("member.avatar").as("writerAvatar"))
				.add(Property.forName("contents").as("contents"))
	            .add(Projections.property("writeDate").as("writtenDate"))
			)
			.setResultTransformer(new AliasToBeanResultTransformer(GraffitiDTO.class));
		return c.list();
	}

}