package com.xxx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.dao.CourseDAO;
import com.xxx.dao.CourseDAO;
import com.xxx.model.CourseModel;
import com.xxx.model.UserModel;


@Service
public class CourseService{

	@Resource
	private CourseDAO courseDao;
	
	
	public void saveOrUpdate(CourseModel courseModel) {
		courseDao.saveOrUpdate(courseModel);
	}
	
	public void deleteCourse(long id) {
		courseDao.deleteCourse(id);
	}
	
	
	public List<CourseModel> getCourseList(CourseModel courseModel) {
		return courseDao.getCourseList(courseModel);
	}
	
	public Long getCourseCount(CourseModel courseModel){
		return courseDao.getCourseCount(courseModel);
	}
	
}
