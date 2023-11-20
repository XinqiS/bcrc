$(function () {

	
	//企业中心-发布职位管理切换
	
	$(".tab_table1").eq(0).show();
	$(".tab_tit1 span").on('click', function () {
		$(this).addClass("on").siblings('span').removeClass('on');
		$(".tab_table1").eq($(this).index()).show().siblings('.tab_table1').hide();
	});
	//企业中心-应聘人才管理
	
	$(".tab_table2").eq(0).show();
	$(".tab_tit2 span").on('click', function () {
		$(this).addClass("on").siblings('span').removeClass('on');
		$(".tab_table2").eq($(this).index()).show().siblings('.tab_table2').hide();
	});
	
	var left_hei = $(".con_left").height();


});

//
////职能击显示
//function work_func_click(int){
//	$('#layer_back_drop').show();
//		$('#layer_back_drop').addClass("layer_back_drop_class");
//		$("#work_func_layer").show();
//}
//function close_i(int){/
	//$("#int_expectindustry_multiple_under_layer_id").hide();
	//	$("#layer_back_drop").hide();
	//	$("#int_expectfunc_layer").hide();
	//	$("#work_func_layer").hide();
//}