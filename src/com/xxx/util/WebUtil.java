package com.xxx.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.xxx.model.JsonResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class WebUtil {

    public static Map<String, String> getParamMap() {
    	HttpServletRequest request = ServletActionContext.getRequest();
        Enumeration<String> keys = request.getParameterNames();
        Map<String, String> paramMap = new HashMap<String, String>(16);
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = request.getParameter(key);
            paramMap.put(key, value);
        }
        return paramMap;
    }

	public static void outValue(JsonResponse value){
		String json = JSON.toJSONString(value);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(json);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != pw) {
				pw.close();
				pw = null;
			}
		}
	}
}