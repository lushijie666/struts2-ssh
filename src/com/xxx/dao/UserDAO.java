package com.xxx.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.stereotype.Repository;

import com.xxx.model.UserModel;
import com.xxx.util.StringUtil;


@Repository
public class UserDAO {
	
	@Resource
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.openSession();
	}
	
	public void saveOrUpdate(UserModel userModel) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
        session.saveOrUpdate(userModel);
        tx.commit();

	}
	
	public void deleteUser(long id) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Object user = session.get(UserModel.class, id);
		session.delete(user);
		tx.commit();
	}
	
	
	public List<UserModel> getUserList(UserModel userModel) {
		Criteria criteria = getSession().createCriteria(UserModel.class);
		String query = userModel.getQuery();
		if(StringUtil.isNotNullString(query)){
			Criterion queryName = Restrictions.like("name",query);
			criteria.add(queryName);
		}
		criteria.setFirstResult((userModel.getPageIndex() - 1) * userModel.getPageSize());
		criteria.setMaxResults(userModel.getPageSize());
		criteria.addOrder(Order.desc("id"));
		return criteria.list();
	}
	
	public long getUserCount(UserModel userModel) {
		CriteriaImpl criteria = (CriteriaImpl) getSession().createCriteria(UserModel.class);
		String query = userModel.getQuery();
		if(StringUtil.isNotNullString(query)){
			Criterion queryName = Restrictions.like("name",query);
			criteria.add(queryName);
		}
		Projection projection = criteria.getProjection();
		criteria.setProjection(Projections.rowCount());  
		Object count = criteria.uniqueResult();
		Long result = 0l;
		if(null != count){
			result = (Long) count;
		}
		return result;
	}
	
	
}
