<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit">
  		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>用户信息管理</title>
		<link rel="stylesheet" type="text/css" href="/usermanage/platform_ui/layui-v2.2.5/layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="/usermanage/platform_ui/components/template_index/layuiAdmin-master/static/admin/css/admin.css" />
		<script src="/usermanage/platform_ui/layui-v2.2.5/layui/layui.js" type="text/javascript" charset="utf-8"></script>
	    <script src="/usermanage/platform_ui/components/template_index/layuiAdmin-master/static/admin/js/main.js" type="text/javascript" charset="utf-8"></script> 
	    <script src="/usermanage/common/common.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div class="main-layout" id='main-layout'>
			<!--侧边栏-->
			<div class="main-layout-side">
				<div class="m-logo">用户信息管理</div>
				<ul class="layui-nav layui-nav-tree" lay-filter="leftNav">
				  <li class="layui-nav-item layui-nav-itemed" id="codeRepertoryRow">
				    <a href="javascript:;"><i class="iconfont">&#xe607;</i>用户管理</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="jsp/userList.jsp" data-id='9'><span class="l-line"></span>用户管理</a></dd>
				    </dl>
				  </li>
				</ul>
			</div>
			<!--右侧内容-->
			<div class="main-layout-container">
				<!--头部-->
				<div class="main-layout-header">
					<div class="menu-btn" id="hideBtn">
						<a href="javascript:;" title="侧边伸缩">
							<i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
						</a>
					</div>
					<ul class="layui-nav" lay-filter="rightNav">
					  <li class="layui-nav-item">
					    <a href="javascript:;" data-id='5' data-text="个人信息">${sessionScope.USER.userName}</a>
					  </li>
					</ul>
				</div>
				<!--主体内容-->
				<div class="main-layout-body">
				<div id="main-container" class="main-content">
				</div>
			</div>
			</div>
			<!--遮罩-->
			<div class="main-mask">
				
			</div>
		</div>
		<script>
		var $;
		layui.use(['jquery', 'table'], function() {
			$ = layui.jquery;
		});
		
		</script>
	</body>
</html>
