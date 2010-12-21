package springsprout.modules.study.post.textPost;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import springsprout.common.dao.HibernateGenericDao;
import springsprout.domain.study.board.TextPost;

@Repository
public class TextPostRepositoryImpl extends HibernateGenericDao<TextPost> implements TextPostRepository {

	public List<TextPost> getRootPostList() {
		return getCriteria().addOrder(Order.desc("createdAt")).add(Restrictions.isNull("rootPost")).list();
	}

	public List<TextPost> getBranchPostsOf(TextPost textPost) {
		return getCriteria().add(Restrictions.eq("rootPost.id", textPost.getId())).list();
	}

	public List<TextPost> getRootPostList(int start, int limit, int studyId) {
		return getCriteria().addOrder(Order.desc("createdAt")).add(Restrictions.isNull("rootPost")).setFirstResult(start).setMaxResults(limit)
			.add(Restrictions.eq("rootStudy.id", studyId)).list();
	}         

	public List<TextPost> findPostByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getCount() {
		return (Integer) getCriteria().setProjection(Projections.rowCount()).list().get(0);
	}

	public int getRootPostCount() {
		return (Integer) getCriteria().setProjection(Projections.rowCount()).add(Restrictions.isNull("rootPost")).list().get(0);
	}


}
