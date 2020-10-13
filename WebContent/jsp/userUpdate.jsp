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
					<input type="hidden" name="id" value="${user.id }" />
					<div class="layui-form-item">
						<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
							<label class="layui-form-label">姓名：</label>
							<div class="layui-input-block">
								<input type="text" name="name" value="${user.name }" class="layui-input" />
							</div>
						</div>
						<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
							<label class="layui-form-label">年龄：</label>
							<div class="layui-input-block">
								<input type="text" name="age" value="${user.age }" lay-verify="number" class="layui-input" />
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
							<label class="layui-form-label">性别：</label>
							<div class="layui-input-block">
								 <select name="sex">
                                	<option value="男" <c:if test='${user.sex == "男"}'>selected</c:if>>男</option>
                                	<option value="女" <c:if test='${user.sex == "女"}'>selected</c:if>>女</option>
                                </select>
							</div>
						</div>
						<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
							<label class="layui-form-label">联系方式：</label>
							<div class="layui-input-block">
								<input type="text" name="tel" value="${user.tel }" class="layui-input" />
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
							<label class="layui-form-label">学历：</label>
							<div class="layui-input-block">
								<select name="education">
                                	<option value="本科" <c:if test='${user.education == "本科"}'>selected</c:if>>本科</option>
                                	<option value="专科" <c:if test='${user.education == "专科"}'>selected</c:if>>专科</option>
                                </select>
							</div>
						</div>
						<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
							<label class="layui-form-label">毕业院校：</label>
							<div class="layui-input-block">
								 <input type="text" name="school" value="${user.school }" class="layui-input" />
							</div>
						</div>
					</div>
                    <div class="layui-form-item">
						<label class="layui-form-label">住址：</label>
						<div class="layui-input-block">
							<input type="text" name="address" value="${user.address }" class="layui-input" />
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<div class="layui-footer">
								<button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">提交</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							</div>
						</div>
					</div>
				</form>
		</div>
		<script>
			var $;
			layui.use(['form', 'table'], function() {
				$ = layui.$;
				var form = layui.form;
				//更新渲染 
				form.render(); 
				//监听提交
				form.on('submit(formDemo)', function(data) {
		            submitForm("mySubmitForm", "${pageContext.request.contextPath}/updateObjectUserController.action", "tableList", "dialog");
					return false;
				});

			});

		</script>
	</body>
</html>
