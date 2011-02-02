package springsprout.modules.seminar.register;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springsprout.domain.Member;
import springsprout.domain.Seminar;
import springsprout.domain.SeminarComer;

import java.util.List;

@Repository
public class SeminarRegisterRepository {
	@Autowired SessionFactory sessionFactory;
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void flush() {
		getCurrentSession().flush();
	}

	public void save(SeminarComer comer) {
		this.getCurrentSession().save(comer);
	}
	
	public void update(SeminarComer comer) {
		this.getCurrentSession().update(comer);
	}

	public boolean hasRegistrationByEmail(Seminar seminar,String email) {
		Query  q = this.getCurrentSession().createQuery(
				"select count(*) from seminar_comers where seminar = :seminar and email = :email  " );
		q.setEntity("seminar", seminar);
		q.setString("email", email);
		long cnt = (Long) q.uniqueResult();
		return cnt > 0L ? true : false;
	}
	
	public boolean hasRegistrationByEmail(Seminar seminar,String email,String excludeEmail) {
		Query  q = this.getCurrentSession().createQuery(
				"select count(*) from seminar_comers where seminar = :seminar and email = :email and email != :excludeEmail " );
		q.setEntity("seminar", seminar);
		q.setString("email", email);
		q.setString("excludeEmail", excludeEmail);
		long cnt = (Long) q.uniqueResult();
		return cnt > 0L ? true : false;
	}

	public boolean hasRegistrationByMember(Seminar seminar,Member member) {
		Query  q = this.getCurrentSession().createQuery(
			"select count(*) from seminar_comers where seminar = :seminar and member_id = :member_id ");
		q.setEntity("seminar", seminar);
		q.setInteger("member_id", member.getId());
		long cnt = (Long) q.uniqueResult();
		return cnt > 0L ? true : false;
	}

	public void delete(SeminarComer comer) {
		this.getCurrentSession().delete(comer);
	}

	//serminar와 member을 unique keys로 설정하고 get(seminar,member) 하고 싶군.
	@SuppressWarnings("unchecked")
	public SeminarComer getSeminarComerByMember(Seminar seminar,Member member) {
		Query  q = this.getCurrentSession().createQuery(
		"from seminar_comers where seminar = :seminar and member_id = :member_id ");
		q.setEntity("seminar", seminar);
		q.setInteger("member_id",member.getId());
		q.setMaxResults(1);
		List<SeminarComer> comers = q.list();
		if(comers != null && comers.size() > 0) {
			return comers.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public SeminarComer getSeminarComerByEmailAndPassword(Seminar seminar,
			String email,String password) {
		Query  q = this.getCurrentSession().createQuery(
		"from seminar_comers where seminar = :seminar and email = :email and password = :password ");
		q.setEntity("seminar", seminar);
		q.setString("email",email);
		q.setString("password",password);
		q.setMaxResults(1);
		List<SeminarComer> comers = q.list();
		if(comers != null && comers.size() > 0) {
			return comers.get(0);
		}
		return null;
	}

	public SeminarComer getSeminarComerById(int comerId) {
		return (SeminarComer) this.getCurrentSession().load(SeminarComer.class, comerId);
	}
	
}
