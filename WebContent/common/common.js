//表单提交	formId:表单Id  formUrl:表单提交地址    tableId：要刷新的表格Id  type：页面类型    dialog弹出层    page页面
function submitForm(formId, url, tableId, type, locationUrl){
	var indexLoading= openMask();
	$.ajax({
	    type: "POST",//方法类型
	    dataType: "json",//预期服务器返回的数据类型
	    url: url ,//url
	    data: $('#' + formId).serialize(),
	    success: function (result) {
	    	layer.open({
				title: '提示信息',
				content: "<div style='text-align:center;'>" + result.message + "</div>",
				end: function(index, layero){ 
					closeMask(indexLoading);
					if(result.isSuccess == "Y") {
						if(type == "dialog") {
							closeDialog();
							parent.layui.table.reload(tableId);
						} else {
							 $('#main-container').load(locationUrl);
						}
                		
					}
				}
			});
	    }
	});
}
//删除数据
function deleteObject(url, tableId) {
	layer.confirm("<div style='text-align:center;'>确认删除？</div>", function(index){
		var indexLoading= openMask();
		 $.ajax({
	          type: "POST",//方法类型
	          dataType: "json",//预期服务器返回的数据类型
	          url: url,
	          success: function (result) {
	          	layer.open({
	  				title: '提示信息',
	  			  	content: "<div style='text-align:center;'>" + result.message + "</div>",
	  			  	end: function(index, layero) { 
	  			  		closeMask(indexLoading);
	  					layui.table.reload(tableId);
	  			  	}
	  			});
	          	
	          }
	      });
        layer.close(index);
    });
	 
}
//打开弹出层
function openDialog(title, width, height, url) {
	layer.open({
        type: 2,//iframe引入方式
        title: title,
        area: [width, height],
        fixed: false, //不固定
        content: url
    });
}
//打开新页面
function openPage(url) {
	 $('#main-container').load(url);
}
//过滤表格数据
function searchTable(url, tableId) {
	url = encodeURI(encodeURI(url));
    //执行重载
	layui.table.reload(tableId, {url:url});
}
//监听点击时间
function listenClick(active) {
	 $('.listenClick').on('click', function(){
         var type = $(this).data('type');
         active[type] ? active[type].call(this) : '';
     });
}
//关闭弹出层
function closeDialog() {
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index);
}
//打开遮罩层
function openMask() {
	return layer.load(2, {shade: [0.3, '#F2F2F2']});
}
//关闭遮罩层
function closeMask(index_loading) {
	return layer.close(index_loading);
}
//格式化日期
function formatDate(tm){   
    var date = new Date(tm).toLocaleString();   
    return date;   
}
//休眠
function sleep(n) {
	var start=new Date().getTime();
	while(true) if(new Date().getTime()-start>n) break;
}
