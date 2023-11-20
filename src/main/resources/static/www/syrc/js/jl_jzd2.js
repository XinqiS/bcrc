$(function () {
	$(".int_expectfunc_click_center_right_list").eq(0).show();
		//弹框点击切换
	$("#int_expectfunc_click_center_left li").on('click', function () {
	
		$(this).addClass("on").siblings('li').removeClass('on');
		$("#int_expectfunc_click_center_right  .int_expectfunc_click_center_right_list").eq($(this).index()).show().siblings('.int_expectfunc_click_center_right_list').hide();
	});
	
	//弹框顶部增加
$(".dz_div .line ul li").on('click',function(){
	//$(this).addClass('on');
	//多选
	//var li_txt = $(this).children('em').html();
	
	
	$(this).parents('ul').siblings('.line_div').children('.in').eq($(this).index()).show().siblings('.in').hide();
	var hei = $(this).parents('ul').siblings('.line_div').children('.in').eq($(this).index()).height();
	$(this).parents('ul').siblings('.line_div').height(hei);
	$(this).parents('.line').siblings('.line').find('.in').hide();
	$(this).parents('.line').siblings('.line').find('.line_div').height(0);
	
	
	//$(window.parent.document).find("#work_func").val(li_txt);
});

//赋值职能
$("#int_expectfunc_click_bottom_save").on('click',function(){
	var li_txt = $("#int_expectfunc_click_multiple_selected").text();
	//var em_txt = $(li_txt).html();
	//
	var znVal="";
	$(".ttag").each(function(){
		if(znVal == "")
			znVal = $(this).text();
        else
        	znVal+="+"+$(this).text(); 
	});
	//alert($(window.parent.document).find("#work_zn_input").val());
	$(window.parent.document).find("#work_zn_input").val(znVal);
	//alert($(window.parent.document).find("#work_zn_input").val());
	//$(window.parent.document).find("#work_func_div").html(li_txt);
	//$(window.parent.document).find("#work_func_div").addClass('clearfix');
	//$(window.parent.document).find("#work_func_div").css("border","0px");
	//$(window.parent.document).find("#work_func_div").css("height","auto");
	$("body",parent.document).find('#layer_back_drop').hide(); 
	$("body",parent.document).find('#base_area_layer').hide(); 	
	$("body",parent.document).find('#work_cx').css("display","block");
	
	//$("#work_cx").show();
});
	
	
	//$(".line_div em").on('click', function(){
	//	var em_txt = $(this).html();
		//var inp_txt = $(window.parent.document).find("#base_area").val();	
		//$("body",parent.document).find('#layer_back_drop').hide(); 
		//$("body",parent.document).find('#base_area_layer').hide(); 
		//职能
		//$(window.parent.document).find("#work_zn_input").val(em_txt);
		
	//	$("#int_expectfunc_click_multiple").removeClass('element_hide').addClass('element_show');
		//$("#int_expectfunc_click_multiple_selected").append('<span class="ttag">'+em_txt+'<em></em></span>');
	//	$(".line_div em").off("on");
		
	//	var channels=$("#int_expectfunc_click_multiple_selected span");   
	//	 for(var i=0;i<channels.length;i++){ 
		//	 var linum=0;
	//		 if(i >= 4){
	//
		//		 $("#int_expectfunc_click_multiple_error").removeClass('element_hide').addClass('element_show');
			
	//			 $(".line_div em").off("click"); 
		//	 }else{
		//		 $(".line_div em").off("on"); 
		//		 $("#int_expectfunc_click_multiple_error").removeClass('element_show').addClass('element_hide');
				
		//	 }
	//	  }
		
		
		
		
		
//		if($("#int_expectfunc_click_multiple_selected span").size()< 5){
//			$(".line_div em").off("on");
//			$(this).off("click");
//			//$("#int_expectfunc_click_multiple_selected").append('<span class="ttag">'+em_txt+'<em></em></span>');
//		}else if($("#int_expectfunc_click_multiple_selected span").size()>= 5){
//
//			$("#int_expectfunc_click_multiple_error").removeClass('element_hide').addClass('element_show');
//			$(".line_div em").off("click");
//		}
		
		
		//ttag();
		
		
	//});
	
	
	
	
	
//	取消
	$(".base_area_click_close").on('click', function(){
		$("body",parent.document).find('#layer_back_drop').hide(); 
		$("body",parent.document).find('#base_area_layer').hide(); 	

	});
	
//	取消
	$("#int_expectfunc_click_close").on('click', function(){
		$("body",parent.document).find('#layer_back_drop').hide(); 
		$("body",parent.document).find('#base_area_layer').hide(); 	

	});
//	取消
	$(".int_expectfunc_click_close").on('click', function(){
		$("body",parent.document).find('#layer_back_drop').hide(); 
		$("body",parent.document).find('#base_area_layer').hide(); 	

	});



		
			var show_count = parseInt($(window.parent.document).find("#count").val());   //要显示的条数
			//alert(show_count);
			var count = 1;    //递增的开始值，这里是你的ID
			$(".line_div em").click(function () {
				var em_txt = $(this).html();
		
				var length = $("#int_expectfunc_click_multiple_selected .ttag").length;
				//alert(length);
				
				// $(this).off("click"); 
				if (length < show_count)    //点击时候，如果当前的数字小于递增结束的条件
				{
					$("#int_expectfunc_click_multiple_selected").clone().appendTo("#dynamicTable tbody");   //在表格后面添加一行
					$("#int_expectfunc_click_multiple").removeClass('element_hide').addClass('element_show');
					$("#int_expectfunc_click_multiple_selected").append('<span class="ttag">'+em_txt+'<em></em></span>');
					//$(".line_div em").off("on");
					ttag();
					
				}else{
					$("#int_expectfunc_click_multiple_error").removeClass('element_hide').addClass('element_show');
					
					// $(".line_div em").off("click"); 
					ttag();
				}
			});
			
			
			
	
	
	

	
	

});




//多选删除

function ttag(){
	

	$("#int_expectfunc_click_multiple_selected span").click(function(){
		
		var length = $("#int_expectfunc_click_multiple_selected .ttag").length;
		//alert(length);
		$("#int_expectfunc_click_multiple_error").removeClass('element_show').addClass('element_hide');
			$(this).remove();//移除当前行
			//changeIndex();
	
	});
	
	
	
//	$(".tin .ttag").click(function(){	
//		alert(1);
//			$(this).remove();
//		
//			//dxzj();
//
//	});
}