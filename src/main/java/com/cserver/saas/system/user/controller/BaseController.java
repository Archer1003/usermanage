package com.cserver.saas.system.user.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import net.sf.json.JSONObject;
public class BaseController {
	/***
	 * 初始化分页
	 * @param request
	 */
	public Map<String, Object> getInitPageMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String limit = request.getParameter("limit");
		String page = request.getParameter("page");
		int currentPage = 0;
    	int pageSize = 0;
    	int startRow = 0;
		if(null != limit && null != page) {
			currentPage = Integer.parseInt(page);
			pageSize = Integer.parseInt(limit);
        	startRow = (currentPage - 1) * pageSize;
        	map.put("startRow", startRow);
        	map.put("pageSize", pageSize);
		}
		return map;
	}
	
	public void responseWriter(Map<String, Object> responseMap, HttpServletResponse response) throws IOException {
		JSONObject json = JSONObject.fromObject(responseMap);
		String returnwords = json.toString().replace("null", "\"\"");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(returnwords);
	}
	
	public String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
