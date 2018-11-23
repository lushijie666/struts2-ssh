package com.xxx.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.xxx.model.JsonResponse;
import com.xxx.model.UserModel;
import com.xxx.service.UserService;
import com.xxx.util.WebUtil;
import com.xxx.util.StringUtil;

@Controller
@Scope("prototype")
public class UserController extends ActionSupport{

	@Resource
	private UserService userService;

	public void getUserList() {
		Map<String, String> paramMap = WebUtil.getParamMap();
		UserModel userModel = new UserModel();
		String pageIndex = paramMap.get("page");
		if (StringUtil.isNotNullString(pageIndex)) {
			userModel.setPageIndex(Integer.parseInt(pageIndex));
		}
		String pageSize = paramMap.get("limit");
		if (StringUtil.isNotNullString(pageSize)) {
			userModel.setPageSize(Integer.parseInt(pageSize));
		}
		userModel.setQuery(paramMap.get("query"));
		long totalCount = userService.getUserCount(userModel);
		List<UserModel> pageList = userService.getUserList(userModel);
		JsonResponse response = new JsonResponse();
		response.setCount(totalCount);
		response.setSuccessed(pageList);
		WebUtil.outValue(response);
	}

	public void operUser() {
		Map<String, String> paramMap = WebUtil.getParamMap();
		String name = paramMap.get("name");
		String password = paramMap.get("password");
		int age = 0;
		String ageStr = paramMap.get("age");
		if (null != ageStr) {
			age = Integer.parseInt(ageStr);
		}
		String job = paramMap.get("job");
		UserModel userModel = new UserModel(name, age, job);
		userModel.setPassword(password);
		String id = paramMap.get("id");
		if (null != id && id.trim().length() > 0) {
			userModel.setId(Long.parseLong(id));
		} 
		userService.saveOrUpdate(userModel);
		JsonResponse response = new JsonResponse();
		WebUtil.outValue(response);
	}

	public void deleteUser() {
		Map<String, String> paramMap = WebUtil.getParamMap();
		long id = Long.parseLong(paramMap.get("id"));
		userService.deleteUser(id);
		JsonResponse response = new JsonResponse();
		response.setSuccessed();
		WebUtil.outValue(response);
	}
}
