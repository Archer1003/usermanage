package com.cserver.saas.system.user.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cserver.saas.system.user.entity.User;
import com.cserver.saas.system.user.service.UserService;
import com.cserver.saas.system.user.util.ExportExcel;
import com.cserver.saas.system.user.util.JsonUtil;


@Controller
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	
	@RequestMapping("/testUserController.do")
	public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//excel里sheet页的名称
    	String title = "疫情上报导出";
    	//excel里列标题
    	String[] headers = { "姓名", "年龄","地址"};
    	//需要导出的字段key
    	String[] fiels = { "name", "age","address"};
    	//要导出的数据List, 不用定义泛型，但是必须存Map<String, Object>
    	List list = new ArrayList();
    	Map<String, Object> map1 = new HashMap<String, Object>();
    	map1.put("id", "1");
    	map1.put("name", "张三");
    	map1.put("age", "20.0");
    	map1.put("address", "西安");
    	Map<String, Object> map2 = new HashMap<String, Object>();
    	map2.put("id", "2");
    	map2.put("name", "李四");
    	map2.put("age", "30.2");
    	map2.put("address", "咸阳");
    	Map<String, Object> map3 = new HashMap<String, Object>();
    	map3.put("id", "3");
    	map3.put("name", "王五");
    	map3.put("age", "40");
    	map3.put("address", "汉中");
    	list.add(map1);
    	list.add(map2);
    	list.add(map3);
    	//文件名
    	String exportName =  "疫情上报导出.xls";
    	OutputStream os = new FileOutputStream(exportName);
    	ExportExcel.exportExcel(title, headers, fiels, list, os);
    	ExportExcel.download(exportName, response); 
	}
	/***
	 * 修改
	 * @param model
	 * @param request
	 * @param rs
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/updateObjectUserController.action")
	public void updateObject(@ModelAttribute User user, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String isSuccess = "Y";
		String message = "修改成功";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sameName", user.getName());
		map.put("notOwnId", user.getId());
		List<Object> list = userService.searchList(map);
		if(list.size() > 0) {
			isSuccess = "N";
			message = "名称重复";
		} else {
			user.setUpdateTime(new Date());
			User userUpdate = (User) userService.getObject(user.getId());
			BeanUtils.copyProperties(user, userUpdate, getNullPropertyNames(user));
			userService.updateObject(userUpdate);
		}
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("isSuccess", isSuccess);
		responseMap.put("message", message);
		responseWriter(responseMap, response);
	}
	/***
	 * 跳转修改页面
	 * @param model
	 * @param request
	 * @param rs
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/toUpdatePageUserController.action")
	public String toUpdatePage(ModelMap model, HttpServletRequest request,
			HttpServletResponse rs) throws IOException {
		String id = request.getParameter("id");
		User user = (User) userService.getObject(id);
		model.addAttribute("user", user);
		return "userUpdate";
	}
	/***
	 * 详情页面
	 * @param model
	 * @param request
	 * @param rs
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/toViewObjectUserController.action")
	public String toViewObject(ModelMap model, HttpServletRequest request,
			HttpServletResponse rs) throws IOException {
		String id = request.getParameter("id");
		User user = (User) userService.getObject(id);
		model.addAttribute("user", user);
		return "userDetail";
	}
	/***
	 * 删除
	 * @param model
	 * @param request
	 * @param rs
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/deleteObjectUserController.action")
	public void deleteObject(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String isSuccess = "Y";
		String message = "删除成功";
		String id = request.getParameter("id");
		userService.deleteObject(id);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("isSuccess", isSuccess);
		responseMap.put("message", message);
		responseWriter(responseMap, response);
	}
	/***
	 * 新增
	 * @param model
	 * @param request
	 * @param rs
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/addObjectUserController.action")
	public void addObject(@ModelAttribute User user, @RequestParam("file") MultipartFile file,  HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(!file.isEmpty()) {
            //上传文件路径
            String path = "D:/";
            
            System.out.println(path);
            //上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + filename));
            //输出文件上传最终的路径 测试查看
            System.out.println(path + File.separator + filename);
		}
		
		
		String isSuccess = "Y";
		String message = "添加成功";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sameName", user.getName());
		List<Object> list = userService.searchList(map);
		if(list.size() > 0) {
			isSuccess = "N";
			message = "名称重复";
		}else {
			user = setInitParam(user, request); 
			userService.addObject(user);
		}
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("isSuccess", isSuccess);
		responseMap.put("message", message);
		responseWriter(responseMap, response);
	}
	/***
	 * 查询
	 * @param model
	 * @param request
	 * @param rs
	 * @return
	 * @throws Exception 
	 * @throws IOException
	 */
	@RequestMapping("/searchListUserController.action")
	public void searchList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = getInitPageMap(request);
		String loginName = request.getHeader("loginName");
		String name = request.getParameter("name");
		if(null != name) {
			name = java.net.URLDecoder.decode(name,"UTF-8");//转码
			map.put("name", name);
			map.put("username", name);
		}
		List<Object> userList = userService.searchList(map);
		long totalRows = userService.searchCounts(map);
		JsonUtil.returnJson(userList, totalRows, response);
	}
	private User setInitParam(User user, HttpServletRequest request) {
		user.setId(System.currentTimeMillis() + "");
		user.setUpdateTime(new Date());
		return user;
	}
	
}
