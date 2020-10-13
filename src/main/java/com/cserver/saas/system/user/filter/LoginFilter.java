package com.cserver.saas.system.user.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cserver.saas.system.user.util.HttpRequest;

public class LoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String servletPath = httpServletRequest.getServletPath();
		/** 有username，则已登录，继续走doFilter，否则未登录，跳转至登录页 */
		if ((null != httpServletRequest.getSession().getAttribute("loginName")
				&& !httpServletRequest.getSession().getAttribute("loginName").toString().isEmpty())
				|| servletPath.equals("/doLogic.action")) {
			if(null != httpServletRequest.getSession().getAttribute("loginName")) {
				/*Cookie cookie = new Cookie("loginName", httpServletRequest.getSession().getAttribute("loginName").toString());
				cookie.setPath("/");
				httpServletResponse.addCookie(cookie);*/
			}
			chain.doFilter(httpServletRequest, httpServletResponse);
		} else {
			httpServletRequest.getSession().invalidate();
			String access_token = "";
			while (true) {
				// 获取token
				JSONObject jsonObjs = getToken();
				Object success = jsonObjs.getString("success");
				if (success.toString().equals("true")) {
					access_token = (String) jsonObjs.get("access_token");
					httpServletRequest.getSession().setAttribute("token", access_token);
					break;
				}
			}
			String contextPath = httpServletRequest.getContextPath();
			String serverName = httpServletRequest.getServerName();
			int serverPort = httpServletRequest.getServerPort();
			String casUrl = "";
			casUrl = "http://sso.cserver.com.cn/sso.web/loginCserver";
			//casUrl = "http://192.168.1.99:8060/sso.web/loginCserver";
			//String casReturnUrl = "http://" + serverName + ":" + serverPort + contextPath + "/doLogic.action?loginName=ysf@qq.com";
			String casReturnUrl = "http://" + serverName + ":" + serverPort + contextPath + "/doLogic.action";
			// String casRealUrl = "http://" + serverName + ":" + serverPort +
			// contextPath + servletPath + "?" + paramString;
			String casRealUrl = "http://" + serverName + ":" + serverPort + contextPath;
			String info1 = "{\"returnUrl\": \"" + casReturnUrl + "\",\"realUrl\": \"" + casRealUrl
					+ "\",\"access_token\":\"" + access_token + "\"}";
			info1 = java.net.URLEncoder.encode(info1, "utf-8");

			httpServletResponse.sendRedirect(httpServletResponse.encodeRedirectURL(casUrl + "?info=" + info1));
			return;
		}
	}

	/*
	 * public JSONObject getToken() { JSONObject jsonObj = new JSONObject();
	 *//** 这个appid和key是正式环境任意一个isv账号新增生成的 *//*
											 * jsonObj.put("appid",
											 * "cserverc099e0784c6645d880d13da7b6aad88d"
											 * ); jsonObj.put("appkey",
											 * "e6c7ae2401def33a320403192f63442cce434818d65c136711d2c490034b72a8"
											 * ); String jsonObjStr =
											 * jsonObj.toString(); String
											 * resultJson =
											 * HttpRequest.sendPostS(
											 * "http://my.cserver.com.cn/csaas/api/access_token",
											 * jsonObjStr); JSONObject jsonObjs
											 * =
											 * JSONObject.parseObject(resultJson
											 * ); return jsonObjs; }
											 */
	public JSONObject getToken() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("appid", "cserverc099e0784c6645d880d13da7b6aad88d");
		jsonObj.put("appkey", "e6c7ae2401def33a320403192f63442cce434818d65c136711d2c490034b72a8");
		String jsonObjStr = jsonObj.toString();
		String resultJson = HttpRequest.sendPost("http://my.cserver.com.cn/csaas/api/access_token", jsonObjStr);
		JSONObject jsonObjs = JSONObject.parseObject(resultJson);
		return jsonObjs;
	}
	/***
	 * 获取token
	 * 
	 * @return
	 */
/*	public JSONObject getToken() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("appid", "cserverf6a50622458f4e908b13f2746e3bbec1");
		jsonObj.put("appkey", "2e65aeaa18d7d1d6eaeac9cb24f7fb539dcca3a18627918bc439003812a8229d");
		String jsonObjStr = jsonObj.toString();
		String resultJson = HttpRequest.sendPost("http://192.168.1.99:8080/csaas/api/access_token", jsonObjStr);
		JSONObject jsonObjs = JSONObject.parseObject(resultJson);
		return jsonObjs;
	}*/

	public void init(FilterConfig config) throws ServletException {

	}

}
