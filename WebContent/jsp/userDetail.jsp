<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="/usermanage/platform_ui/layui-v2.2.5/layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="/usermanage/platform_ui/components/template_index/layuiAdmin-master/static/admin/css/admin.css" />
		<script src="/usermanage/platform_ui/layui-v2.2.5/layui/layui.js" type="text/javascript" charset="utf-8"></script>
	    <script src="/usermanage/platform_ui/components/template_index/layuiAdmin-master/static/admin/js/main.js" type="text/javascript" charset="utf-8"></script> 
	    <script src="/usermanage/common/common.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div class="wrap-container">
			<form class="layui-form" id="mySubmitForm" style="width: 90%;padding-top: 20px;">
					<div class="layui-form-item">
						<div class="layui-form-item">
							<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
								<label class="layui-form-label">姓名：</label>
								<div class="layui-input-block">
									<label class="layui-form-label">${user.name }</label>
								</div>
							</div>
							<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
								<label class="layui-form-label">年龄：</label>
								<div class="layui-input-block">
									<label class="layui-form-label">${user.age }</label>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
								<label class="layui-form-label">性别：</label>
								<div class="layui-input-block">
									<label class="layui-form-label">${user.sex }</label>
								</div>
							</div>
							<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
								<label class="layui-form-label">联系方式：</label>
								<div class="layui-input-block">
									<label class="layui-form-label">${user.tel }</label>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
								<label class="layui-form-label">学历：</label>
								<div class="layui-input-block">
									<label class="layui-form-label">${user.education }</label>
								</div>
							</div>
							<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
								<label class="layui-form-label">毕业院校：</label>
								<div class="layui-input-block">
									<label class="layui-form-label">${user.school }</label>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
								<label class="layui-form-label">住址：</label>
								<div class="layui-input-block">
									<label class="layui-form-label">${user.address }</label>
								</div>
							</div>
						</div>
					</div>
				</form>
		</div>

	</body>
</html>
