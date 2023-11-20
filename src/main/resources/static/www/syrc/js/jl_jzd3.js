$(function () {
	$(".int_expectfunc_click_center_right_list").eq(0).show();
		//弹框点击切换
	$("#int_expectfunc_click_center_left li").on('click', function () {
	
		$(this).addClass("on").siblings('li').removeClass('on');
		$("#int_expectfunc_click_center_right  .int_expectfunc_click_center_right_list").eq($(this).index()).show().siblings('.int_expectfunc_click_center_right_list').hide();
	});
	
	


//赋值行业
$("#int_expectfunc_click_bottom_save").on('click',function(){
	var li_txt = $("#int_expectfunc_click_multiple_selected").text();
	//var em_txt = $(li_txt).html();
	var hyVal="";
	$(".ttag").each(function(){
		if(hyVal == "")
			hyVal = $(this).text();
        else
        	hyVal+="+"+$(this).text(); 
	});
	$(window.parent.document).find("#int_hy_input").val(hyVal);
	
	//求职意向-行业
	//$(window.parent.document).find("#int_expectindustry_div").html(li_txt);
	//$(window.parent.document).find("#int_expectindustry_div").addClass('clearfix');
	//$(window.parent.document).find("#int_expectindustry_div").css("border","0px");
	//$(window.parent.document).find("#int_expectindustry_div").css("height","auto");
	
	
	//工作经验-行业
	//$(window.parent.document).find("#work_industry_div").html(li_txt);
	//$(window.parent.document).find("#work_industry_div").addClass('clearfix');
	//$(window.parent.document).find("#work_industry_div").css("border","0px");
	//$(window.parent.document).find("#work_industry_div").css("height","auto");
	
	
	
	$("body",parent.document).find('#layer_back_drop').hide(); 
	$("body",parent.document).find('#base_area_layer').hide(); 	
	$("body",parent.document).find('#int_cx').css("display","block");
	
	//$("#work_cx").show();
});
	
	//頂部增加
	//$(".int_expectfunc_click_center_right_list .dz_div li em").on('click', function(){
	//	var em_txt = $(this).text();
	//	$(this).addClass('on');
		
		//行業
		
		
	//	$("#int_expectfunc_click_multiple_selected").append('<span class="ttag">'+em_txt+'<em></em></span>');
		$(this).parents('li').siblings('li').children('em').removeClass('on');
		
		//$("#int_expectfunc_click_multiple").removeClass('element_hide').addClass('element_show');
		//$("#int_expectfunc_click_multiple_selected").append('<span class="ttag">'+em_txt+'<em></em></span>');
	//	$(".line_div em").off("on");
		
		//var channels=$("#int_expectfunc_click_multiple_selected span");   
		// for(var i=0;i<channels.length;i++){ 
			// var linum=0;
//			 if(i >= 4){
//					
//				 $("#int_expectfunc_click_multiple_error").removeClass('element_hide').addClass('element_show');
//			
//				 $(".line_div em").off("click"); 
//			 }else{
//				 $(".line_div em").off("on"); 
//				 $("#int_expectfunc_click_multiple_error").removeClass('element_show').addClass('element_hide');
//				
//			 }
//		  }
		
		
		
		
		
		//ttag();
		
		
	//});
	
	
	
	
	
//	取消
	$(".base_area_click_close").on('click', function(){
		$("body",parent.document).find('#layer_back_drop').hide(); 
		$("body",parent.document).find('#base_area_layer').hide(); 	
		$(window.parent.document).find("#int_expectindustry_div").html();
		$(window.parent.document).find("#work_industry_div").html();

	});
	
//	取消
	$("#int_expectfunc_click_close").on('click', function(){
		$("body",parent.document).find('#layer_back_drop').hide(); 
		$("body",parent.document).find('#base_area_layer').hide(); 
		$(window.parent.document).find("#int_expectindustry_div").html();
		$(window.parent.document).find("#work_industry_div").html();

	});
//	取消
	$(".int_expectfunc_click_close").on('click', function(){
		$("body",parent.document).find('#layer_back_drop').hide(); 
		$("body",parent.document).find('#base_area_layer').hide(); 
		$(window.parent.document).find("#int_expectindustry_div").html();
		$(window.parent.document).find("#work_industry_div").html();

	});
	
	

	var show_count = parseInt($(window.parent.document).find("#count").val());   //要显示的条数
	//alert(show_count);
	var count = 1;    //递增的开始值，这里是你的ID
	$(".dz_div em").click(function () {
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
		
			$(this).remove();//移除当前行
			//changeIndex();
			$("#int_expectfunc_click_multiple_error").removeClass('element_show').addClass('element_hide');
	});
	
	
	
//	$(".tin .ttag").click(function(){	
//		alert(1);
//			$(this).remove();
//		
//			//dxzj();
//
//	});
}