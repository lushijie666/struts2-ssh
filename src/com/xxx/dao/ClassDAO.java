package com.xxx.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xxx.model.ClassModel;
import com.xxx.model.CourseModel;
import com.xxx.util.StringUtil;


@Repository
public class ClassDAO {
	
	@Resource
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.openSession();
	}
	
	
	public void deleteClass(String ids){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		if (StringUtil.isNotNullString(ids)) {
			String sql = "delete from t_class where id in (" + ids +")";
			session.createSQLQuery(sql).executeUpdate();
		}
		tx.commit();
		session.close();
	}
	
	
	public List<ClassModel> getClassList(ClassModel classModel) {
		String sql = "select tc.*, tu.name as userName from t_class tc left join t_user tu on tc.user_id = tu.id ";
		String query = classModel.getQuery();
		if (StringUtil.isNotNullString(query)){
			sql += " where (tc.class_name LIKE :query OR tc.teacher_name like :query OR tu.name like :query) ";
		}
		Session session = getSession();
		SQLQuery createSQLQuery = session.createSQLQuery(sql);
		if (StringUtil.isNotNullString(query)){
			createSQLQuery.setParameter("query", "%" + query + "%");
		}
		createSQLQuery.setFirstResult((classModel.getPageIndex() - 1) * classModel.getPageSize());
		createSQLQuery.setMaxResults(classModel.getPageSize());
		List<Object[]> list = createSQLQuery.list();
		List<ClassModel> classList = new ArrayList<ClassModel>();
		if (null != list && list.size() > 0) {
			for (Object[] o : list) {
				ClassModel model = new ClassModel();
				model.setId(Long.parseLong(o[0].toString()));
				model.setClassName(o[1].toString());
				model.setUserId(Long.parseLong(o[2].toString()));
				model.setTeacherName((o[3].toString()));
				model.setUserName(o[4].toString());
				classList.add(model);
			}
		}
		return classList;
	}
	
	public Long getClassCount(ClassModel classModel) {
		String sql = "select count(1) from t_class tc left join t_user tu on tc.user_id = tu.id ";
		String query = classModel.getQuery();
		if (StringUtil.isNotNullString(query)){
			sql += " where (tc.class_name LIKE :query OR tc.teacher_name like :query OR tu.name like :query) ";
		}
		Session session = getSession();
		SQLQuery createSQLQuery = session.createSQLQuery(sql);
		if (StringUtil.isNotNullString(query)){
			createSQLQuery.setParameter("query", "%" + query + "%");
		}
		BigInteger count = (BigInteger)createSQLQuery.uniqueResult();
		return count.longValue();
	}
	
}
