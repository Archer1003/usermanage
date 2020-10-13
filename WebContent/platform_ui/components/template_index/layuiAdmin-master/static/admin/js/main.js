layui.use(['layer', 'form', 'element', 'jquery'], function() {
	var layer = layui.layer;
	var element = layui.element;
	var form = layui.form;
	var $ = layui.jquery;
	var hideBtn = $('#hideBtn');
	var mainLayout = $('#main-layout');
	var mainMask = $('.main-mask');
    //$('#main-container').load('menu1.html');   //刷新初始加载页面
	//监听导航点击
	element.on('nav(leftNav)', function(elem) {
		var navA = $(elem).find('a');
		var id = navA.attr('data-id');
		var url = navA.attr('data-url');
		var text = navA.attr('data-text');
		if(!url){
			return;
		}
		$('#main-container').load(url);

		mainLayout.removeClass('hide-side');
	});
	//监听导航点击
	element.on('nav(rightNav)', function(elem) {
		var navA = $(elem).find('a');
		var id = navA.attr('data-id');
		var url = navA.attr('data-url');
		var text = navA.attr('data-text');
		if(!url){
			return;
		}
        $('#main-container').load(url);
		mainLayout.removeClass('hide-side');
	});
	//菜单隐藏显示
	hideBtn.on('click', function() {
		if(!mainLayout.hasClass('hide-side')) {
			mainLayout.addClass('hide-side');
		} else {
			mainLayout.removeClass('hide-side');
		}
	});
	//遮罩点击隐藏
	mainMask.on('click', function() {
		mainLayout.removeClass('hide-side');
	})
});