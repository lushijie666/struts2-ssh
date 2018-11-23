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

import com.xxx.model.CourseModel;
import com.xxx.model.UserModel;
import com.xxx.util.StringUtil;


@Repository
public class CourseDAO {
	
	@Resource
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.openSession();
	}
	
	public void saveOrUpdate(CourseModel courseModel) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
        session.saveOrUpdate(courseModel);
        tx.commit();
	}
	
	public void deleteCourse(long id){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Object course = session.get(CourseModel.class, id);
		session.delete(course);
		tx.commit();
	}
	
	
	public List<CourseModel> getCourseList(CourseModel courseModel) {
		Criteria criteria = getSession().createCriteria(CourseModel.class);
		String query = courseModel.getQuery();
		if(StringUtil.isNotNullString(query)){
			Criterion queryName = Restrictions.like("name",query);
			criteria.add(queryName);
		}
		criteria.setFirstResult((courseModel.getPageIndex() - 1) * courseModel.getPageSize());
		criteria.setMaxResults(courseModel.getPageSize());
		criteria.addOrder(Order.desc("id"));
		return criteria.list();
	}
	
	public Long getCourseCount(CourseModel courseModel) {
		CriteriaImpl criteria = (CriteriaImpl) getSession().createCriteria(CourseModel.class);
		String query = courseModel.getQuery();
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
