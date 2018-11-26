package com.xxx.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.xxx.model.ClassModel;
import com.xxx.model.JsonResponse;
import com.xxx.service.ClassService;
import com.xxx.util.WebUtil;
import com.xxx.util.StringUtil;

@Controller
@Scope("prototype")
public class ClassController extends ActionSupport{
	
	@Resource
	private ClassService classService;
	
	public void getClassList(){
		Map<String, String> paramMap = WebUtil.getParamMap();
		ClassModel classModel = new ClassModel();
		String pageIndex = paramMap.get("page");
		if (StringUtil.isNotNullString(pageIndex)) {
			classModel.setPageIndex(Integer.parseInt(pageIndex));
		}
		String pageSize = paramMap.get("limit");
		if (StringUtil.isNotNullString(pageSize)) {
			classModel.setPageSize(Integer.parseInt(pageSize));
		}
		classModel.setQuery(paramMap.get("query"));
		long totalCount = classService.getClassCount(classModel);
		List<ClassModel> pageList = classService.getClassList(classModel);
		JsonResponse response = new JsonResponse();
		response.setCount(totalCount);
		response.setSuccessed(pageList);
		WebUtil.outValue(response);
	}
	
	
	public void deleteClass() {
		Map<String, String> paramMap = WebUtil.getParamMap();
		String ids = paramMap.get("id");
		classService.deleteClass(ids);
		JsonResponse response = new JsonResponse();
		response.setSuccessed();
		WebUtil.outValue(response);
	}
}
