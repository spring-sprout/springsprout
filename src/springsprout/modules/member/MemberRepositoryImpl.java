package springsprout.modules.member;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import springsprout.common.dao.HibernateGenericDao;
import springsprout.common.web.support.OrderParam;
import springsprout.common.web.support.Paging;
import springsprout.domain.Member;
import springsprout.domain.enumeration.MemberStatus;
import springsprout.modules.member.exception.MemberNotFoundException;
import springsprout.modules.member.support.MemberContext;
import springsprout.modules.member.support.MemberSearchParam;
import springsprout.modules.ajax.support.AutoCompleteParams;

@Repository
public class MemberRepositoryImpl extends HibernateGenericDao<Member> implements MemberRepository {

	@SuppressWarnings("unchecked")
	public List<Member> getMemberList() {
        OrderParam orderParam = new OrderParam();
        orderParam.setField("name");
        orderParam.setDirection("asc");

		return getMemberList(null, orderParam, null);
	}

    public List<Member> getMemberList(MemberSearchParam searchParam,  OrderParam orderParam, Paging paging) {
        Criteria c = getCurrentSession().createCriteria(Member.class);

        applySearchParam(searchParam, c);
        applyPageParam(paging, c);
        applyOrderParam(orderParam, c);

        return 	c.list();
    }

	public int getTotalRowsCount(MemberSearchParam searchParam) {
		Criteria c = getCurrentSession().createCriteria(Member.class)
				.setProjection(Projections.rowCount());

		applySearchParam(searchParam, c);

		return (Integer) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Member> getMemberListByContext(MemberContext context) {
		Criteria c = getCurrentSession().createCriteria(Member.class);

		applySearchParam(context.getSearchParam(), c);
		applyPageParam(context.getPaging(), c);
		applyOrderParam(context.getOrderParam(), c);

		return c.list();
	}

	public void deleteAll() {
		getCurrentSession().createSQLQuery("delete * from Member");
	}

	public Member findByEmail(String email) {
		return (Member) getCurrentSession().createQuery(
				"from Member where email = ?").setString(0, email)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Member> getJoinedMemberList() {
		Criteria c = getCurrentSession().createCriteria(Member.class);
		c.add(Restrictions.eq("status", MemberStatus.JOIN.getValue()));
        c.addOrder(Order.asc("id"));
		return c.list();
	}

	public String getMemberEmailById(Integer id) {
		return (String) getCurrentSession().createQuery(
				"select email from Member where id = ?").setInteger(0, id)
				.uniqueResult();
	}

    public List<Member> getMemberListByAjaxParams(AutoCompleteParams autoCompleteParam) {
        Criteria c = getCurrentSession().createCriteria(Member.class);
        c.add(Restrictions.ilike("name", autoCompleteParam.getKeyword(), MatchMode.ANYWHERE));
        ProjectionList proList = Projections.projectionList();
        proList.add(Projections.property("id"));
        proList.add(Projections.property("name"));
        c.setProjection(proList);
        return c.list();
    }

    public Member getOriginalMemberBy(Member updatedMember) {
        Member originalMember = (Member) getCurrentSession().createCriteria(Member.class).add(Restrictions.eq("id", updatedMember.getId())).uniqueResult();
        if(originalMember == null)
            throw new MemberNotFoundException(updatedMember.getId());
        return originalMember;
    }

    private void applySearchParam(MemberSearchParam searchParam, Criteria c) {
        if(searchParam == null) searchParam = new MemberSearchParam();

		if (StringUtils.hasText(searchParam.getName())) {
			c.add(Restrictions.ilike("name", searchParam.getName(), MatchMode.ANYWHERE));
		}
		if (StringUtils.hasText(searchParam.getEmail())) {
			c.add(Restrictions.ilike("email", searchParam.getEmail(), MatchMode.ANYWHERE));
		}
        if (StringUtils.hasText(searchParam.getAllowedEmail())) {
            c.add(Restrictions.eq("isAllowedEmail", Boolean.valueOf(searchParam.getAllowedEmail())));
        }
        if (StringUtils.hasText(searchParam.getAllowedGoogleTalk())) {
            c.add(Restrictions.eq("isAllowedGoogleTalk", Boolean.valueOf(searchParam.getAllowedGoogleTalk())));
        }
	}

	private void applyOrderParam(OrderParam orderParam, Criteria c) {
        if(orderParam == null) orderParam = new OrderParam();

		String field = orderParam.getField();
		if (!StringUtils.hasText(field))
			c.addOrder(Order.asc("id")); // apply default
		else if (orderParam.getDirection().equals("desc"))
			c.addOrder(Order.desc(field));
		else
			c.addOrder(Order.asc(field));
	}

	private void applyPageParam(Paging standardPaging, Criteria c) {
        if(standardPaging == null) return;

        c.setFirstResult(standardPaging.getFirstRowNumber());
        c.setMaxResults(standardPaging.getSize());
	}    

}
