(function($){
	
	$.fn.TL_PC_Slider = function(options) {
		return $.fn.TL_PC_Slider.defaults = {
			type: "TL_Display", // TL_Display 渐显 TL_Top 上下滚动 TL_Left左右 TL_Fade淡入淡出
			auto_play: false,
			timer: 500,//持续时间
			speed: 2500,// 	运行间隔。
			tab: ".tab li",
			box: ".box",
			page_box: ".df_slide_page",
			actions: "tl_hover",//tl_hover tl_click
			tab_on: "df_on", 
			page: false,//是否分页
			prev: ".df_prev",
			next: ".df_next",
			fun_start: null,
			fun_end: null
		}, 
		
		this.each(function() {
			var sitting = $.extend({}, $.fn.TL_PC_Slider.defaults, options),
				tl_type = sitting.type,
				tl_prev = $(sitting.prev, $(this)),
				tl_next = $(sitting.next, $(this)),
				tl_page_box = $(sitting.page_box, $(this)),
				tl_tab = $(sitting.tab, $(this)),
				tl_tab_sise = tl_tab.size(),
				tl_box = $(sitting.box, $(this)),
				tl_box_size = tl_box.children().size(),
				tl_index = 0,//默认项
				tl_timer = parseInt(sitting.timer),
				tl_get_speed = parseInt(sitting.speed);
	
			var tl_outer_height = 0;
			var tl_outer_width = 0;
			var tl_width = 0;
			var tl_height = 0;
			var I = null;
			var tl_tab_on = sitting.tab_on;
			var tl_find_on = tl_tab.index($(this).find("." + tl_tab_on));
			var tl_child_on = tl_index = -1 == tl_find_on ? tl_index : tl_find_on;//默认on
			var tl_new_index = tl_index;
			var tl_pages = tl_box_size >= 1 ? 0 != tl_box_size % 1 ? tl_box_size % 1 : 1 : 0;
			//alert(tl_pages)	
			if (0 == tl_tab_sise && (tl_tab_sise = tl_box_size), "false" == sitting.page || 0 == sitting.page ? !1 : !0) {//如果分页
				if (tl_box_size >= 1) {
				
					if ("TL_Left" == tl_type || "TL_Top" == tl_type) {
						tl_tab_sise = 0 != tl_box_size % 1 ? (0 ^ tl_box_size / 1) + 1 : tl_box_size / 1
					}
					else {
						var V = tl_box_size - 1;
						tl_tab_sise = 1 + parseInt(0 != V % 1 ? V / 1 + 1 : V / 1), 0 >= tl_tab_sise && (tl_tab_sise = 1)
					} 
				}
				else {
					tl_tab_sise = 1;
				}
				tl_tab.html("");
				var tl_item_num = "";
				if (1 == sitting.page || "true" == sitting.page) {
					for (var q1 = 0; tl_tab_sise > q1; q1++) tl_item_num += "<li><a href='javascript:void(0);'>" + (q1 + 1) + "<a/></li>"
				}
				else {
					for (var q2 = 0; tl_tab_sise > q2; q2++) tl_item_num += sitting.page.replace("$", q2 + 1)
				}
			    //alert(tl_item_num)	
				tl_tab.html(tl_item_num);
				var tl_tab = tl_tab.children();
			}
			if (tl_box_size >= 1) {
			
				tl_box.children().each(function() {
					$(this).width() > tl_width && (
						tl_width = $(this).width(),
						tl_outer_width = $(this).outerWidth(!0)
					),
					$(this).height() > tl_height && (
						tl_height = $(this).height(), 
						tl_outer_height = $(this).outerHeight(!0)
					)
				});
				function tl_list_each() {
					for (var a1 = 0; 1 > a1; a1++) 
					tl_box.children().eq(a1).clone().addClass("clone").appendTo(tl_box);
					for (var a2 = 0; tl_pages > a2; a2++)
					tl_box.children().eq(tl_box_size - a2 - 1).clone().addClass("clone").prependTo(tl_box)
				};
				switch (tl_type) {
					case "TL_Display":
					tl_box.css({
						position: "relative",
						width: tl_outer_width,
						height: tl_outer_height
					}).children().css({
						width: tl_width,
						position: "absolute",
						left: 0,
						top: 0,
						display: "none"
					});
					break;
					case "TL_Top":
					tl_list_each(), 
					tl_box.wrap('<div class="df_slide_box" style="overflow:hidden; position:relative; height:' + tl_outer_height + 'px"></div>').css({
						top: -(tl_index * 1) * tl_outer_height,
						position: "relative",
						padding: 0,
						margin: 0
					}).children().css({
						height: tl_height
					});
					break;
					case "TL_Left":
					tl_list_each(), 
					tl_box.wrap('<div class="df_slide_box" style="overflow:hidden; position:relative; width:' * tl_outer_width + 'px"></div>').css({
						width: (tl_box_size + 1 + tl_pages) * tl_outer_width,
						position: "relative",
						overflow: "hidden",
						padding: 0,
						margin: 0,
						left: -(tl_pages + tl_index * 1) * tl_outer_width
					}).children().css({
						width: tl_width,
						float: "left"
					});
					break;
				}
			}
			
			var tl_elm = function(elm) {
					var tl_str = elm * 1;
					return elm == tl_tab_sise ? tl_str = tl_box_size : -1 == elm && 0 != tl_box_size % 1 && (tl_str = -tl_box_size % 1), 
					tl_str
				};
				
			var tl_init = function(k) {
				if (tl_child_on != tl_index || k) {
					if ((tl_new_index = tl_index, tl_index >= tl_tab_sise ? tl_index = 0 : 0 > tl_index && (tl_index = tl_tab_sise - 1)), 
						($.isFunction(sitting.fun_start) && sitting.fun_start(
							tl_index,
							tl_tab_sise,
							$(this),
							$(sitting.tab, $(this)),
							tl_box,
							tl_prev,
							tl_next
						)
					) , tl_box_size >= 1){ 
					
						switch (tl_type) {
							case "TL_Fade":
							tl_box.children().stop(false, false).eq(tl_index).animate({
								opacity: "show"
							}, tl_timer, "swing", function() {
								$.isFunction(sitting.fun_end) && (
									sitting.fun_end(
										tl_index,
										tl_tab_sise,
										$(this),
										$(sitting.tab, $(this)),
										tl_box, 
										tl_prev, 
										tl_next
									)
								)
							}).siblings().hide();
							break;
							case "TL_Display":
							tl_box.children().stop(false, false).eq(tl_index).animate({
								opacity: "show"
							}, tl_timer, "swing", function() {
								$.isFunction(sitting.fun_end) && (
									sitting.fun_end(
										tl_index,
										tl_tab_sise,
										$(this),
										$(sitting.tab, $(this)),
										tl_box, 
										tl_prev, 
										tl_next
									)
								)
							}).siblings().animate({
								opacity: "hide"
							}, tl_timer, "swing");
							break;
							case "TL_Left":
							tl_box.stop(false, true).animate({
								left: -(tl_elm(tl_new_index) + tl_pages) * tl_outer_width
							}, tl_timer, "swing", function() {
								-1 >= tl_new_index ? 
								tl_box.css({
									left: -(tl_pages + (tl_tab_sise - 1) * 1) * tl_outer_width
								}) 
								: 
								tl_new_index >= tl_tab_sise && tl_box.css({
									left: -tl_pages * tl_outer_width
								}), 
								$.isFunction(sitting.fun_end) && (
									sitting.fun_end(
										tl_index,
										tl_tab_sise,
										$(this),
										$(sitting.tab, $(this)),
										tl_box, 
										tl_prev, 
										tl_next
									)
								)
							});
							break;
							case "TL_Top":
							tl_box.stop(false, false).animate({
								top: -(tl_elm(tl_new_index) + tl_pages) * tl_outer_height
							}, tl_timer, "swing", function() {
								-1 >= tl_new_index ? 
								tl_box.css({
									top: -(tl_pages + (tl_tab_sise - 1) * 1) * tl_outer_height
								}) 
								: 
								tl_new_index >= tl_tab_sise && tl_box.css({
									top: -tl_pages * tl_outer_height
								}), 
								$.isFunction(sitting.fun_end) && (
									sitting.fun_end(
										tl_index,
										tl_tab_sise,
										$(this),

										$(sitting.tab, $(this)),
										tl_box, 
										tl_prev, 
										tl_next
									)
								)
							});
							break;
						}
						tl_tab.removeClass(tl_tab_on).eq(tl_index).addClass(tl_tab_on), 
						tl_child_on = tl_index, 
						"true" || (
							tl_next.removeClass("df_stop_next"), 
							tl_prev.removeClass("df_stop_prev"), 
							0 == tl_index && tl_prev.addClass("df_stop_prev"), 
							tl_index == tl_tab_sise - 1 && tl_next.addClass("df_stop_next")
						), 
						tl_page_box.html("<strong>" + (tl_index + 1) + "</strong><b> / " + tl_tab_sise + "</b>")
					}
				}
			};
				
			tl_init(!0); 
			
			var tl_clear = null;
			
			if ("tl_hover"===sitting.actions){
				tl_tab.hover(function() {
					var a7 = tl_tab.index(this);
					I = setTimeout(function() {
						tl_index = a7,
						tl_init(),
						clearInterval(tl_clear),
						tl_clear = setInterval(function() {
							tl_index++, tl_init()
						}, tl_get_speed)
					}, 100)
				}, function() {
					clearTimeout(I)
				})
			} 
			if ("tl_click"===sitting.actions){
				tl_tab.click(function() {
					tl_index = tl_tab.index(this),
					tl_init(),
					clearInterval(tl_clear),
					tl_clear = setInterval(function() {
						tl_index++, tl_init()
					}, tl_get_speed)
				})
			
			}
			tl_next.click(function() {
				("true" || tl_index != tl_tab_sise - 1) && (
					tl_index++, 
					tl_init(), 
					clearInterval(tl_clear), 
					tl_clear = setInterval(function() {
						tl_index++, tl_init()
					}, tl_get_speed)
				)
			});//右按扭
			tl_prev.click(function() {
				("true" || 0 != tl_index) && (
					tl_index--, 
					tl_init(), 
					clearInterval(tl_clear), 
					tl_clear = setInterval(function() {
						tl_index++, tl_init()
					}, tl_get_speed)
				)
			});//左按扭
			if (sitting.auto_play){
				tl_clear = setInterval(function() {
					tl_index++, tl_init()
				}, tl_get_speed)
				
				$(this).hover(function(){
					clearInterval(tl_clear)
				},function(){
					clearInterval(tl_clear), 
					tl_clear = setInterval(function() {
						tl_index++, tl_init()
					}, tl_get_speed)
				});
			}
	
		})
	}
})(jQuery);