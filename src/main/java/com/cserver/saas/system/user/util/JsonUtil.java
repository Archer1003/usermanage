package com.cserver.saas.system.user.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	public static String toLayUiJson(List<Object> list, long totalRows) {
		StringBuffer returnJson = new StringBuffer("{\"code\": 0,\"msg\": \"\",\"count\":" + totalRows + " ,\"data\": ");
		JSONObject jsonObj = new JSONObject();
		String json = jsonObj.toJSONString(list);
		returnJson.append(json);
		returnJson.append("}");
		return returnJson.toString();
	}
	public static void returnJson(List<Object> list, long totalRows, HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("报错信息:" + e.getMessage());
		} finally {
			out.print(toLayUiJson(list, totalRows));
			out.flush();
			out.close();
		}
		
	}
}
