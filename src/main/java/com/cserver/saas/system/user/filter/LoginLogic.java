package com.cserver.saas.system.user.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.cserver.saas.system.user.entity.User;
import com.cserver.saas.system.user.service.UserService;
@Controller
public class LoginLogic {
	@RequestMapping("/doLogic.action")
	public static void doLogic(ServletRequest request, ServletResponse response) throws IOException, ServletException{
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String info = httpServletRequest.getParameter("info");
		String loginName = httpServletRequest.getParameter("loginName");
		/***info有值，则是单点认证成果后返回的，set进session中*/
		if(info!=null){
			info =  new String(info.getBytes("iso8859-1"), "UTF-8");
			JSONObject jsonObjs = JSONObject.parseObject(info);
			Object success = jsonObjs.getString("success");
			if (success.toString().equals("true")) {
				httpServletRequest.getSession().setAttribute("loginName", (String) jsonObjs.get("username"));
				httpServletRequest.getSession().setAttribute("realname", (String) jsonObjs.get("realname"));
				
				Object userObj = httpServletRequest.getSession().getAttribute("user");
				if(null == userObj) {
					ApplicationContext context = new ClassPathXmlApplicationContext("../applicationContext.xml");
					UserService userService = (UserService) context.getBean("userService");
					User user = (User) userService.getObject((String) jsonObjs.get("id"));
					httpServletRequest.getSession().setAttribute("user", user);
				}
			} else {
				if (jsonObjs.getString("success").equals("true")) {
					String access_token = (String) jsonObjs.get("access_token");
					httpServletRequest.getSession().setAttribute("token", access_token);
				}
			}
			String realUrl = (String) jsonObjs.get("realUrl");
			// httpServletResponse.sendRedirect(httpServletResponse.encodeRedirectURL(realUrl));
			Cookie cookie3 = new Cookie("loginName", (String) jsonObjs.get("username"));
			cookie3.setPath("/");
			httpServletResponse.addCookie(cookie3);
			httpServletResponse.sendRedirect(httpServletResponse.encodeRedirectURL(realUrl));
			//httpServletResponse.sendRedirect(httpServletResponse.encodeRedirectURL(realUrl + "?loginName=" + (String) jsonObjs.get("username")));
		} else {
			PrintWriter pw = response.getWriter();
			try {
				pw.println("单点认证出错");
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				pw.flush();
				pw.close();
			}
			
		}
		
		
		
	}
}
