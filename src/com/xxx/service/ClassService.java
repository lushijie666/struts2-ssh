package com.xxx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.dao.ClassDAO;
import com.xxx.dao.ClassDAO;
import com.xxx.model.ClassModel;
import com.xxx.model.UserModel;


@Service
public class ClassService{

	@Resource
	private ClassDAO classDao;
	
	
	public void deleteClass(String ids) {
		classDao.deleteClass(ids);
	}
	
	
	public List<ClassModel> getClassList(ClassModel classModel) {
		return classDao.getClassList(classModel);
	}
	
	public Long getClassCount(ClassModel classModel){
		return classDao.getClassCount(classModel);
	}
	
}
