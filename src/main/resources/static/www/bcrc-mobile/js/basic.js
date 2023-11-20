 $(function () {
 	$(".tit").on('click', function(){
		var txt = $(this).text();
		$("#pageWp").show();
		$("#barea").hide();
		$(".location font").text(txt);
		$(document).scrollTop(0);
	});
	 
//	 基本信息页 男女切换
	 $("#sex span").on('click', function(){
		 $(this).addClass('on').siblings('span').removeClass('on');
	 });
//	 目前工作
	 $("#situation").on('click', function(){
		 // alert(this_txt);
		 var this_txt = $('#situation option:selected').text();
		// alert(this_txt);
		 
		 $('#situation').siblings('span').text(this_txt);
	 });
//	 开始工作年份
	 $("#workyear").on('click', function(){
		 // alert(this_txt);
		 var this_txt = $('#workyear option:selected').text();
		// alert(this_txt);
		 
		 $('#workyear').siblings('span').text(this_txt);
	 });
//	 最低学历
	 $("#zw_zdxl").on('click', function(){
		 // alert(this_txt);
		 var this_txt = $('#zw_zdxl option:selected').text();
		// alert(this_txt);
		 
		 $('#zw_zdxl').siblings('span').text(this_txt);
	 });
//	 应届毕业生
	 $("#zw_yjbys").on('click', function(){
		 // alert(this_txt);
		 var this_txt = $('#zw_yjbys option:selected').text();
		// alert(this_txt);
		 
		 $('#zw_yjbys').siblings('span').text(this_txt);
	 });
//	 工作年限
	 $("#zw_gznx").on('click', function(){
		 // alert(this_txt);
		 var this_txt = $('#zw_gznx option:selected').text();
		// alert(this_txt);
		 
		 $('#zw_gznx').siblings('span').text(this_txt);
	 });
//	 身份证
	 $("#idtype").on('click', function(){
		 // alert(this_txt);
		 var this_txt = $('#idtype option:selected').text();
		// alert(this_txt);
		 
		 $('#idtype').siblings('span').text(this_txt);
	 });
//	 婚姻状况
	 $("#marriage").on('click', function(){
		 // alert(this_txt);
		 var this_txt = $('#marriage option:selected').text();
		// alert(this_txt);
		 
		 $('#marriage').siblings('span').text(this_txt);
	 });
//	 薪资类型
	 $("#salarytype").on('click', function(){
		 // alert(this_txt);
		 var this_txt = $('#salarytype option:selected').text();
		// alert(this_txt);
		 
		 $('#salarytype').siblings('span').text(this_txt);
	 });
//	 工作类型
	 $("#jobterm").on('click', function(){
		 // alert(this_txt);
		 var this_txt = $('#jobterm option:selected').text();
		// alert(this_txt);
		 
		 $('#jobterm').siblings('span').text(this_txt);
	 });
//	 到岗时间
	 $("#entrytime").on('click', function(){
		 // alert(this_txt);
		 var this_txt = $('#entrytime option:selected').text();
		// alert(this_txt);
		 
		 $('#entrytime').siblings('span').text(this_txt);
	 });
//	 行业性质
	 $("#cotype").on('click', function(){
		 // alert(this_txt);
		 var this_txt = $('#cotype option:selected').text();
		// alert(this_txt);
		 
		 $('#cotype').siblings('span').text(this_txt);
	 });
//	 学历
	 $("#degree").on('click', function(){
		 // alert(this_txt);
		 var this_txt = $('#degree option:selected').text();
		// alert(this_txt);
		 
		 $('#degree').siblings('span').text(this_txt);
	 });
//	 公司规模信息赋值
	 $("#cosize").on('click', function(){
		
		 // alert(this_txt);
		 var this_txt = $('#cosize option:selected').text();
		 
		// alert(this_txt);
		 
		 $('#cosize').siblings('span').text(this_txt);
	 });
	 
	 
	 $("#barea .tit").on('click', function(){
		  var ts_tit = $(this).text();
		// alert(ts_tit);
		 $("#pageTop .location font").text(ts_tit);
	 });cancelAnimationFrame
	 
	 
	 
	 //邀请面试
	 $("#addms").on('click', function(){
		 $("#shadow").show();
		 $("#popyqms").removeClass('pophide');
		 $("#popyqms").find('.cn').html('邀请面试，请联系人才中心XXXXXXXXXXXXc');
	 });
	 
	 $(".popmsc").on('click',function(){
		  $("#shadow").hide();
		 $("#popyqms").addClass('pophide');
		 $("#popyqms").find('.cn').html('');
	 });
	 
	 
	 
	 
	 /* 单机li进行页面跳转 */
        $("ul li").click(function(){
            /*当前标签下的a标签*/
            var obj = $(this).find("a");
			//alert(obj.html())
            /*获取第一个a标签，进行跳转*/
            window.location.href=$(obj[0]).attr("href");
        });
	 
	 
//	 发布职位管理切换
	 $(".qy_tab").eq(0).show();
	 $(".qy_tita a").on('click',function(){
		 $(this).addClass('on').siblings('a').removeClass('on');
		 $(".qy_tab").eq($(this).index()).show().siblings(".qy_tab").hide();
	 });
	 
//	 应聘人才管理切换
	 $(".qy_tab1").eq(0).show();
	 $(".qy_tita1 a").on('click',function(){
		 $(this).addClass('on').siblings('a').removeClass('on');
		 $(".qy_tab1").eq($(this).index()).show().siblings(".qy_tab1").hide();
	 });
	 
	 
	 
//	 头部导航更多
	 var i=0;
	 $("#jl_navxl").on('click',function(){
		 i++;
		if(i % 2!=0){
			$(".jl_nav").animate({"height":"200px"},600);
			$(".nav_a").animate({"height":"200px"},600);
			$(this).text("点击隐藏");
		}else{
			$(".jl_nav").animate({"height":"35px"},600);
			$(".nav_a").animate({"height":"35px"},600);
			$(this).text("点击展开");
		}

	 });
	 

 });




//个人登录后 地址弹出

function dqdzC(i){
	$("#pageWp").hide();
	$("#barea").show();
	
}
function dqdzD(i){
	$("#pageWp").show();
	$("#barea").hide();
}




 