package com.xxx.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.xxx.model.CourseModel;
import com.xxx.model.JsonResponse;
import com.xxx.service.CourseService;
import com.xxx.util.WebUtil;
import com.xxx.util.StringUtil;

@Controller
@Scope("prototype")
public class CourseController extends ActionSupport{
	
	@Resource
	private CourseService courseService;
	
	public void getCourseList(){
		Map<String, String> paramMap = WebUtil.getParamMap();
		CourseModel courseModel = new CourseModel();
		String pageIndex = paramMap.get("page");
		if (StringUtil.isNotNullString(pageIndex)) {
			courseModel.setPageIndex(Integer.parseInt(pageIndex));
		}
		String pageSize = paramMap.get("limit");
		if (StringUtil.isNotNullString(pageSize)) {
			courseModel.setPageSize(Integer.parseInt(pageSize));
		}
		courseModel.setQuery(paramMap.get("query"));
		long totalCount = courseService.getCourseCount(courseModel);
		List<CourseModel> pageList = courseService.getCourseList(courseModel);
		JsonResponse response = new JsonResponse();
		response.setCount(totalCount);
		response.setSuccessed(pageList);
		WebUtil.outValue(response);
	}
	
	public void operCourse() {
		Map<String, String> paramMap = WebUtil.getParamMap();
		String name = paramMap.get("name");
		int type = 0;
		String typeStr = paramMap.get("type");
		if (null != typeStr){
			type = Integer.parseInt(typeStr);
		}
		int score = 0;
		String scoreStr = paramMap.get("score");
		if (null != scoreStr){
			score = Integer.parseInt(scoreStr);
		}
		CourseModel courseModel = new CourseModel(name, type, score);
		String id = paramMap.get("id");
		if(StringUtil.isNotNullString(id)){
			courseModel.setId(Long.parseLong(id));
		}
		courseService.saveOrUpdate(courseModel);
		JsonResponse response = new JsonResponse();
		response.setSuccessed();
		WebUtil.outValue(response);
	}
	
	public void deleteCourse() {
		Map<String, String> paramMap = WebUtil.getParamMap();
		long id = Long.parseLong(paramMap.get("id"));
		courseService.deleteCourse(id);
		JsonResponse response = new JsonResponse();
		response.setSuccessed();
		WebUtil.outValue(response);
	}
	
	
}
