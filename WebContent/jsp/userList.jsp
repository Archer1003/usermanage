<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="layui-card layadmin-header">
    <div class="layui-breadcrumb" lay-filter="breadcrumb" style="visibility: visible;">
        <a href="">主页</a><span lay-separator="">/</span>
        <a href="javascript:openPage('jsp/userList.jsp')"><cite>用户列表<a href="testUserController.do">下载</a></cite></a>
    </div>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">

        <div class="layui-col-md12">
            <div id="main-container" class="main-container">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <div class="searchDiv">
						    <div style="margin-bottom: 5px;">
						        <button class="layui-btn layui-btn-mini layui-btn-normal  add-btn listenClick" data-type="addForm">添加</button>
						    </div>
						      搜索姓名：
                            <div class="layui-inline">
                                 <input class="layui-input" id="searchName" autocomplete="off">
                            </div>
                            <button class="layui-btn listenClick" data-type="searchForm">搜索</button>
						</div>
						<table id="tableList" class="layui-table"  lay-filter="demo">
						</table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs listenClick" lay-event="updateForm" >修改</a>
	<a class="layui-btn layui-btn-normal layui-btn-xs listenClick" lay-event="detailForm">详情</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs listenClick" lay-event="delForm">删除</a>
</script>
<script>
	layui.use('table', function(){
	    var table = layui.table;
	    var form = layui.form;
	    table.render({
	        elem:'#tableList',
	        url:'searchListUserController.action',
	        id:'tableList',
	        height:500,
	        limit:10,
	        page: true, //开启分页
	        limits:[10, 20, 50, 100],
	        cols:  [[ //标题栏
	                  {field:'name',title: '姓名', width:'20%'},
	                  {field:'age',title: '年龄', templet: '#updateType', width:'20%'},
	                  {field:'sex',title: '性别', width:'10%'}, 
	                  {field:'tel',title: '联系方式', width:'10%'},
	                  {field:'updateTime',title: '创建时间', width:'20%', templet:'#formatTime'},
	                  {title: '操作',align:'center', width:'20%', toolbar: '#barDemo'}
	        ]]
	    });
	    //监听工具条
	    table.on('tool(demo)', function(obj){
	        var data = obj.data;
	        var url=$(this).attr('data-url');
	        if(obj.event === 'delForm') {
	        	deleteObject("deleteObjectUserController.action?id=" + data.id, "tableList");
	        } else if(obj.event === 'updateForm') {
	        	openDialog("修改用户", "700px", "350px", "toUpdatePageUserController.action?id=" + data.id);
	        } else if(obj.event === 'detailForm') {
	        	openDialog("用户详情", "700px", "400px", "toViewObjectUserController.action?id=" + data.id);
	        }
	    });
	    //监听checkbox复选框开关事件
        form.on('switch(type)',function (data) {
        	var selectIfKey=data.othis;                                                 
            // 获取当前所在行                                                                 
            var parentTr = selectIfKey.parents("tr");                                  
            // 获取当前所在行的索引                                                              
            var name = $(parentTr).find("td:eq(1)").find(".layui-table-cell").text(); 
            alert(name);
        });
	    var active = {
	    	addForm:function () {
	    		openDialog("添加用户", "700px", "400px", "jsp/userAdd.jsp");
	        },
	        searchForm: function(){
	        	var searchName = $('#searchName').val();
	    		var url = 'searchListUserController.action?name=' + searchName;
	        	//过滤表格数据
	            searchTable(url, "tableList");
	        }
	    };
	    //监听click事件
	    listenClick(active);
	});
	
</script>
<script id="formatTime" type="text/html">  
    {{formatDate(d.updateTime)}}   
</script>  
