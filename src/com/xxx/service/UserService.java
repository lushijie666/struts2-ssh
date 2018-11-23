package com.xxx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.dao.UserDAO;
import com.xxx.model.UserModel;
import com.xxx.util.MD5;
import com.xxx.util.StringUtil;


@Service
public class UserService{

	@Resource
	private UserDAO userDao;
	
	
	public void saveOrUpdate(UserModel userModel) {
		String password = userModel.getPassword();
		if(StringUtil.isNotNullString(password)){
			userModel.setPassword(MD5.encode(password));
		}
		userDao.saveOrUpdate(userModel);
	}
	
	public void deleteUser(long id) {
		userDao.deleteUser(id);
	}
	
	public long getUserCount(UserModel userModel){
		return userDao.getUserCount(userModel);
	}
	
	public List<UserModel> getUserList(UserModel userModel){
		return userDao.getUserList(userModel);
	}
	
}
