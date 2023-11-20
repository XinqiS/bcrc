	(function($){
		$.fn.TL_Tab = function(options) {
			var defaults = {
				tab : ".df_67 ol li",
				box : ".df_67 ul li",
				events : "over",
				num : 3,
				speed : 300
			};
			var setting = $.extend(defaults, options);

			var tl_tab = setting.tab;
			var tl_box = setting.box;
			var tl_events = setting.events;
			var tl_num = setting.num;
			var tl_speed = setting.speed;
			
			var tl_time_out = null;
			
			$(tl_box).css({
				display: 'none'
			});
			
			$(tl_tab).removeClass('df_on');
			$(tl_tab).eq(tl_num - 1).addClass('df_on');
		
			$(tl_box).eq(tl_num - 1).css({
				display: 'block'
			});
		
			if (tl_events === 'click') {
				$(tl_tab).click(function () {
					$(tl_tab).removeClass('df_on');
					$(tl_tab).eq($(this).index()).addClass('df_on');
					$(tl_box).stop().fadeOut(tl_speed);
					$(tl_box).eq($(this).index()).stop().fadeIn(tl_speed);
				});  
			} 
			if (tl_events === 'over') {
				$(tl_tab).hover(function () {
					var _this = this;
					clearTimeout(tl_num);
					tl_time_out = setTimeout(function () {
						$(tl_tab).removeClass('df_on');
						$(tl_tab).eq($(_this).index()).addClass('df_on');
						$(tl_box).stop().fadeOut(tl_speed);
						$(tl_box).eq($(_this).index()).stop().fadeIn(tl_speed);
					}, 30);
		
				}, function () {
					clearTimeout(tl_time_out);
				});
			} 			
					
		};
	})(jQuery);
	
	
	$(function(){
	//搜索
	$(".hea_ss").on('click', function(){
		$(".hea_search").toggle();	
		});
	//下拉
	$(".hea_xl").on('click', function(){
		$(".nav-jit-hid").toggle();	
		});
	});