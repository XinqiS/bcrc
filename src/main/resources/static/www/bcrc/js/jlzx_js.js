 $(function () {
	//简历中心-创建简历
	//左侧菜单浮动
	$(window).scroll(function () {
		var scroll_T = $(window).scrollTop();
		//var vh = $(window).scrollTop();
		if (scroll_T > 600) {
			$(".cjjl_nav").addClass("left_fix");
		} else {
			$(".cjjl_nav").removeClass("left_fix");
		}
		
		

	//弹框点击关闭
	$(".base_area_click_close").on('click', function () {
		$("#base_area_layer").hide();
		$("#layer_back_drop").hide();
	});
	
		
		
    
	});
	
	

	

	
	//详细信息点击显示
	$("#xxxx_btn").on('click', function () {
		var jianliId=$("#jianliId").val();
		var  json={};
		json.jianliId=jianliId;
		$.ajax({
			url:"getGrJlInfo",   //请求的url地址
			dataType:"json",   //返回格式为json
			data:json,    //参数值
			type:"post",   //请求方式
			success:function(data){
				$("#basedetail").html('<div class="head" id="Basic">' +
					'<div class="face f2" id="sc">' +
					'<img id="tx" src="'+data.jlinfo.jianliTx+'" width="85" height="104" alt="头像">' +
					'<span class="esp" onclick="setAvatar(this);">修改</span>' +
					' <input type="file" style="display:none;"  id="myfile"  name="myfile" onchange="printFileInfo(this)"  accept="image/png,image/jpg,inamge/gif,image/bpm" multiple="multiple"/>' +
					'</div>'
					+' <div class="cbox">' +
					'<div class="h30">' +
					'<div class="c c2">' +
					'<label>姓名</label>' +
					'<i>*</i>' +
					'<div class="sh">' +
					'<div class="txt">' +
					'<input id="base_name" class="ef" maxlength="20" type="text" "></div>' +
					'</div>' +
					'<div class="err" id="base_name_warning" style="display:none">' +
					'<em class="icons"></em></div></div>'
					+' <div class="c c4"><label>性别</label><i>*</i>' +
					'<div class="sh" id="base_sex_div">' +
					' <div class="txt pointer" id="base_csituation_list" float-on="false">' +
					'<select class="ef" id="xingbie">'+
					'<option value="0">男</option>' +
					'<option value="1">女</option>' +
					'</select>' +
					'</div>' +
					'<div class="err" id="base_sex_warning" style="display:none"><em class="icons"></em></div></div></div></div>'
					+'<div class="h30"><div class="c c2" float-index="false">' +
					'<label>出生日期</label><i>*</i><div class="sh">' +
					'<div class="txt pointer" id="base_birthday_calendar" float-on="false">' +
					'<input class="ef date_img" id="base_birthday_input" type="date"  value="'+data.jlinfo.jianliCsrq+'"></div>' +
					'<input id="base_birthday" type="hidden" ></div></div>'
					+'<div class="c c4" float-index="false"><label>开始工作年份</label><i>*</i>' +
					'<div class="sh"><div class="txt pointer" id="base_workyear_calendar" float-on="false">' +
					'<input class="ef date_img" id="tjksgz"  type="year" > </div>' +
					'<input id="base_workyear" type="hidden" ></div></div></div>'
					+'<div class="h30" style="*position:relative;*z-index:2"><div class="c c2">' +
					'<label>手机</label><i>*</i><div class="sh">' +
					'<div class="txt pointer" name="11" ><input class="ef " id="tjtele" type="text"  ></div></div></div>'
					+' <div class="c c4" float-index="false"><label>求职状态</label><i>*</i>' +
					'<div class="sh"><div class="txt pointer" id="base_csituation_list" float-on="false">' +
					'<select class="ef" id="tjqzzt">' +
					'<option value="0" >目前正在找工作</option>' +
					'<option value="1">观望有好机会会考虑</option>' +
					'<option value="2">我目前不想换工作</option>' +
					'</select></div>' +
					'<input id="base_csituation" type="hidden" value="0"></div></div></div>'
					+'<div class="h30" style="*position:relative;*z-index:1">' +
					'<div class="c c2"><label>邮箱</label><i>*</i> ' +
					'<div class="sh"> <div class="txt pointer" >' +
					'<input class="ef " id="tjemail" type="text" value="'+data.jlinfo.jianliYx+'"></div></div></div>'
					+' <div class="c c4" float-index="false" id="base_area_index"><label>居住地</label><i>*</i><div class="sh">' +
					'<div class="txt pointer" float-on="false" id="base_area_div">' +
					'<input class="ef cursor" maxlength="35" type="text"  placeholder="填写/选择" id="base_area_input" >' +
					'' +
					'<div class="ul u3" id="base_area_list" style="display:none"> </div></div>' +
					'<input class="ef" id="base_area" type="hidden" value="240202" pre_code="240202"></div></div></div></div>'
					+'<div class="abox"><div class="mbox" onclick="showMoreClickEvent(this)"> ' +
					'<span class="icons">更多展开</span> <em class="icons" style="display: block;"></em> </div>' +
					'<div class="all" >' +
					'<div class="h30">' +
					'<div class="c c1" float-index="false" id="base_country_index"><label>户口</label>' +
					'<div class="sh"><div class="txt pointer" float-on="false" id="base_country_div">' +
					'<input class="ef cursor" maxlength="35"  placeholder="填写/选择" type="text" id="base_country_input" >' +
					'<div className="form-div"><input type="button"  id="thisBindqu2"></div>' +
					'<div class="ul u3" id="base_country_list"> </div></div><input id="base_country" type="hidden" value="" pre_code=""></div></div>'
					+'<div class="c c4" float-index="false"><label>婚姻状态</label><div class="sh">' +
					'<div class="txt pointer" id="base_marriage_list" float-on="false">' +
					'<select class="ef" id="tjhunyin">' +
					'<option value="0">已婚</option>' +
					'<option value="1" >未婚</option>' +
					'<option value="2">保密</option>' +
					'</select>' +
					'</div>' +
					'<input class="ef" id="base_marriage" type="hidden" value=""></div></div></div>'
					+' <div class="h30"><div class="c c1" float-index="false"><label>证件号</label>' +
					'<div class="sh sm"><div class="txt pointer" id="base_idtype_list" float-on="false">' +
					'<select class="ef select2" id="zjzjlx">' +
					'<option value="0" selected="selected">身份证</option>' +
					'<option value="1">护照</option>' +
					'<option value="2">军人证</option>' +
					'<option value="3">港澳居民来往内地通行证</option>' +
					'<option value="4">外国人永久居留身份证</option>' +
					'<option value="5">其它</option>' +
					'</select>' +
					'</div>' +
					'<input class="ef" id="base_idtype" type="hidden" value="0"></div>'
					+' <div class="sh sl"> <div class="txt"><input class="ef" maxlength="25" type="text" id="base_idcard" ></div> </div></div>'
					+'<div class="c c4" float-index="false"> <label>政治面貌</label><div class="sh">' +
					'<div class="txt pointer" id="base_politicsstatus_list" float-on="false">' +
					'<select class="ef " id="zjzzmm">' +
					'<option value="0">中共党员</option>' +
					'<option value="1">共青团员</option>' +
					'<option value="2">无党派民主人士</option>' +
					'<option value="3" selected="selected">普通群众</option>' +
					'<option value="4">其他</option>' +
					' </select>' +
					'</div>' +
					'<input class="ef" id="base_politicsstatus" type="hidden" value=""></div></div></div>'
					+'<div class="h30"><div class="c c1" float-index="false"><label>其他联系方式</label>' +
					'<div class="sh sm"><div class="txt pointer" id="base_contacttype_list" float-on="false">' +
					'<select class="ef select2" id="qtlxfs">' +
					'<option value="0">家庭电话</option>' +
					'<option value="1">公司电话</option>' +
					'<option value="2">微信</option>' +
					'<option value="3" selected="selected">QQ号</option>' +
					'</select>' +
					'</div>' +
					'<input class="ef" id="base_contacttype" type="hidden" value="00"></div>' +
					'<div class="sh sl">' +
					'<div class="txt">' +
					'<input class="ef" maxlength="20" id="base_othercontact" type="text" ></div></div></div>'
					+'<div class="c c4">' +
					'<label>身高</label>' +
					' <div class="sh"><div class="txt">' +
					'<input class="ef" maxlength="3" id="base_stature" type="text" ></div></div> cm </div></div>'
					+'<div class="h30"><div class="c c1"><label>家庭住址</label><div class="sh">' +
					'<div class="txt">' +
					'<input class="ef" maxlength="100" id="base_address" type="text" ></div></div></div>'
					+'<div class="c c4"><label>邮编</label><div class="sh">' +
					'<div class="txt">' +
					'<input class="ef" maxlength="6" id="base_zipcode" type="text" ></div> ' +
					'</div>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'<span class="ed_icon_blue icons" id="xxxx_btn" onclick="editButtonClick(this)"></span>'
				);
				$("#basedetail").addClass('com').removeClass('top_wrap');
				$(".face").addClass('f2');

				$(".head").append('<div class="btnbox"><span class="p_but" id="basedetail_save" onclick="saveInfo()">保存</span><span class="p_but gray" id="basedetail_cancel" onclick="cancelButtonClick(this)">取消</span></div>');
				$(".name").remove();
				$(".at").remove();
				$(".tab").remove();
				$(".abox").show();
				$(".cbox").show();
				if(!isNull(data.jlinfo.jianliXm)){
					$("#base_name").val(data.jlinfo.jianliXm);
				}
				if(!isNull(data.jlinfo.jianliKsgz)){
					$("#tjksgz").val(data.jlinfo.jianliKsgz);
				}
				if(!isNull(data.jlinfo.jianliSj)){
					$("#tjtele").val(data.jlinfo.jianliSj);
				}
				if(!isNull(data.jlinfo.jianliJzd)){
					$("#base_area_input").val(data.jlinfo.jianliJzd);
				}
				if(!isNull(data.jlinfo.jianliHk)){
					$("#base_country_input").val(data.jlinfo.jianliHk);
				}
				if(!isNull(data.jlinfo.jianliZjhm)){
					$("#base_idcard").val(data.jlinfo.jianliZjhm);
				}
				if(!isNull(data.jlinfo.jianliQtfshm)){
					$("#base_othercontact").val(data.jlinfo.jianliQtfshm);
				}
				if(!isNull(data.jlinfo.jianliSg)){
					$("#base_stature").val(data.jlinfo.jianliSg);
				}
				if(!isNull(data.jlinfo.jianliJtzz)){
					$("#base_address").val(data.jlinfo.jianliJtzz);
				}
				if(!isNull(data.jlinfo.jianliYb)){
					$("#base_zipcode").val(data.jlinfo.jianliYb);
				}
				if(!isNull(data.jlinfo.jianliXb)){
					$("#xingbie").val(data.jlinfo.jianliXb);
				}
				if(!isNull(data.jlinfo.jianliQzzt)){
					$("#tjqzzt").val(data.jlinfo.jianliQzzt);
				}
				if(!isNull(data.jlinfo.jianliHyzt)){
					$("#tjhunyin").val(data.jlinfo.jianliHyzt);
				}
				if(!isNull(data.jlinfo.jianliZjlx)){
					$("#zjzjlx").val(data.jlinfo.jianliZjlx);
				}
				if(!isNull(data.jlinfo.jianliZzmm)){
					$("#zjzzmm").val(data.jlinfo.jianliZzmm);
				}
				if(!isNull(data.jlinfo.jianliQtfalx)){
					$("#qtlxfs").val(data.jlinfo.jianliQtfalx);
				}




			}
		});


	});
	//日期点击显示
	$(".i_calendar").on('click', function () {
		$(this).siblings('.flbox').show();

	});
	
	$(".tab_dzdiv table tr").eq(0).show();

	//居住地点击切换
	$(".tab_dz").on('click', function () {
		$(this).addClass("on").siblings('li').removeClass('on');
		$(".tab_dzdiv table tr").eq($(this).index()).show().siblings('tr').hide();
	});
	//居住地点击关闭
	$(".base_area_click_close").on('click', function () {
		$("#base_area_layer").hide();
		$("#layer_back_drop").hide();
	});
	
	
	//行业击切换
	
	$(".tab_hytable table tr").eq(0).show();
	$(".tab_hydiv li").on('click', function () {
		$(this).addClass("on").siblings('li').removeClass('on');
		$(".tab_hytable table  tr").eq($(this).index()).show().siblings('tr').hide();
	});
	//职能点击切换
	
	$(".zn_table .de").eq(0).show();
	$(".tab_zndiv li").on('click', function () {
		$(this).addClass("on").siblings('li').removeClass('on');
		$(".zn_table .de").eq($(this).index()).show().siblings('.de').hide();
	});
	

	
	$(".int_expectindustry_click_close").on('click', function(){
		$(".layer_class").hide();
		$("#layer_back_drop").hide();
	});
	
	
	
	
	
//	居住地点击
	
	
	
	
	

});


	//年收入增加
function salary_editButtonClick(o) {
	var  json={};
	if($("#jlBanbenId").val()!=undefined){
		json.jlBbId=$("#jlBanbenId").val();
	}
	$.ajax({
		url:"findQzyxByJlbbIdThree",   //请求的url地址
		dataType:"json",   //返回格式为json
		data:json,    //参数值
		type:"post",   //请求方式
		success:function(data) {
			$("#salary_edit").siblings('.bh_div').html('<div><div class="bd com">' +
				'  <div class="con">' +
				'    <div class="h30 h2"> ' +
				'       <div class="c c3"> ' +
				'         <label>目前年收入</label>' +
				'   <div class="sh sl"><div class="txt">' +
				' <input class="ef" maxlength="6" id="sal_salary" type="text" value=""> <span>万元</span>' +
				'</div>' +
				'</div>' +
				'</div>' +
				'</div>' +
				'</div>' +
				' <div class="btnbox">' +
				' <span class="p_but" id="salary_save" onclick="saveNsr()">保存</span>' +
				'<span class="p_but gray" id="salary_cancel" onclick="salaryButtonClick(this)">取消</span>' +
				' </div>' +
				' </div>' +
				'</div>');
			$("#salary_edit").siblings('.icons').remove();
			$("#salary").removeClass('m1');
			$("#salary").removeClass('top_wrap').addClass('b2');
			$(this).remove();
			sal_salary(data);
		}
	});
}
 function sal_salary(xb) {
	if(!isNull(data.qzyx)){
		if(!isNull(data.qzyx.qzyxNsr)){
			$("#sal_salary").val(data.qzyx.qzyxNsr);
		}
	}
 }
 function saveNsr(){
	 if(isNull($("#sal_salary").val())){
		 alert("年收入不能为空！！！");
		 return false;
	 }else if(!isNumber($("#sal_salary").val())){
		 alert("请输入正确的数字！！！");
		 return false;
	 }
	 var  json={};
	 if($("#qzyxId").val()!=undefined){
		 json.qzyxId=$("#qzyxId").val();
	 }
	 if($("#jlBanbenId").val()!=undefined){
		 json.jlBbId=$("#jlBanbenId").val();
	 }
	 json.qzyxNsr=$("#sal_salary").val();
	 $.ajax({
		 type: "POST",
		 async:false,
		 url: "saveUserQzyx",
		 data:json,
		 dataType: "json",
		 success: function(data){
			 alert(data.message);
			 if(!isNull(data.qzyx.jlBbId)){
				 window.location.href="goEditResume?id="+data.qzyx.jlBbId;
			 }else{
				 window.location.href="goCreateResume";
			 }
			 /*if($("#jlBanbenId").val()!=undefined){
				 window.location.href="goEditResume?id="+$("#jlBanbenId").val();
			 }else{
				 window.location.href="goCreateResume";
			 }*/
		 }
	 });
 }

//年收入取消
function salaryButtonClick(o) {
	$("#salary").addClass('m1');
	$("#salary").removeClass('b2').addClass('top_wrap');
	if($("#jlBanbenId").val()!=undefined){
		window.location.href="goEditResume?id="+$("#jlBanbenId").val();
	}else{
		window.location.href="goCreateResume";
	}
}




//详细信息点击显示 
function editButtonClick(p_oEvent) {


}
 function setAvatar(target){
	 $("#myfile").click();
 }

 var myarr=[];
 function printFileInfo(target){
	 $("#sc").html('<img id="tx" src="'+ URL.createObjectURL(target.files[0]) +'" width="85" height="104"/>')
	 var fileSize = 0;
	 if ( !target.files) {
		 var filePath = target.value;
		 var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
		 var file = fileSystem.GetFile (filePath);
		 fileSize = file.Size;
	 } else {
		 fileSize = target.files[0].size;
	 }
	 var size = fileSize / 1024;
	 if(size>10240){
		 window.wxc.xcConfirm("附件不能大于10M!", "error");
		 target.value="";
		 return
	 }
	// var picFile = document.getElementById('myfile');
	// var files = picFile.files;
	// for(var i=0; i<files.length; i++){
	//	 var file = files[i];
//		 myarr.push(file)
	// }
 }

 function saveInfo(){
	 var  json={};
	 if($("#jianliId").val()!=undefined){
		 json.jianliId=$("#jianliId").val();
	 }
	 if($("#zhId").val()!=undefined){
		 json.zhId=$("#zhId").val();
	 }
	 /* if($("#jlBbId").val()!=undefined){
         json.jlBbId=$("#jlBbId").val();
     } */
	 if(isNull($("#base_name").val())){
		 alert("姓名不能为空！！！");
		 return false;
	 }
	 if(isNull($("#base_birthday_input").val())){
		 alert("出生日期不能为空！！！");
		 return false;
	 }
	 if(isNull($("#tjksgz").val())){
		 alert("开始工作不能为空！！！");
		 return false;
	 }else if(!isNumber($("#tjksgz").val())) {
	     alert("请输入开始工作年限！！！！");
		 return false;
	 }else if($("#tjksgz").val().length>4){
		 alert("开始工作为年限，请不要超过4位数字！！！！");
		 return false;
	 }else if($("#tjksgz").val().length<4){
		 alert("开始工作为年限，请不要少于4为数字");
		 return false;
	 }else{
		 var myDate = new Date();
		 var tYear = myDate.getFullYear();
		 if(parseInt($("#tjksgz").val())>parseInt(tYear)){
			 alert("开始工作年份不能大于当前年份！！！");
			 return false;
		 }

	 }
	 if(isNull($("#tjtele").val())){
		 alert("手机不能为空！！！");
		 return false;
	 }else if(!checkMobile($("#tjtele").val())){
		 alert('联系电话格式不正确！');
		 return false;
	 }
	 if(isNull($("#base_area_input").val())){
		 alert("居住地不能为空！！！");
		 return false;
	 }
	 if(!isNull($("#base_idcard").val())){
		 if($("#zjzjlx").val()=='0'){
			 var pass=IdentityCodeValid($("#base_idcard").val());
			 if(pass!="1"){
				 alert( "您输入的身份证号码不正确，请重新输入！");
				 return false;
			 }
		 }
	 }
	 var hyzt=1;

	 if(isNumber(parseInt($("#tjhunyin").val()))){
		 hyzt=parseInt($("#tjhunyin").val());
	 }
	 var zzmm=3;
	 if(isNumber(parseInt($("#zjzzmm").val()))){
		 zzmm=parseInt($("#zjzzmm").val());
	 }
	 var zjlx=0;
	 if(isNumber(parseInt($("#zjzjlx").val()))){
		 zjlx=parseInt($("#zjzjlx").val());
	 }
	 var qzzt=parseInt($("#tjqzzt").val());

	 var lxfs=0;
	 if(isNumber( parseInt($("#qtlxfs").val()))){
		 lxfs=parseInt($("#qtlxfs").val());
	 }
	 $.each($('#sex span'),function(){
		 var sex = $(this).attr('class');
		 if(sex =="rdo on"){
			 json.jianliXb=$(this).attr('value');
		 }
	 });
	 json.jianliXm=$("#base_name").val();
	 json.jianliXb=$("#xingbie").val();
	 json.jianliCsrq=$("#base_birthday_input").val();
	 json.jianliKsgz=$("#tjksgz").val();
	 json.jianliSj=$("#tjtele").val();
	 json.jianliJzd=$("#base_area_input").val();
	 json.jianliQzzt=qzzt;
	 json.jianliHyzt=hyzt;
	 json.jianliZjlx=zjlx;
	 json.jianliZzmm=zzmm;
	 json.jianliQtfalx=lxfs;
	 json.jianliSg=$("#base_stature").val();
	 json.jianliYb=$("#base_zipcode").val();
	 json.jianliYx=$("#tjemail").val();
	 json.jianliQtfshm=$("#base_othercontact").val();
	 json.jianliJtzz=$("#base_address").val();
	 json.jianliTx=$("#tx").attr("src");
	 json.jianliZjhm=$("#base_idcard").val();
	 json.jianliHk=$("#base_country_input").val();
	 $.ajax({
		 type: "POST",
		 async:false,
		 url: "saveUserConsumeInfo",
		 data: json,
		 dataType: "json",
		 success: function(data){
			 alert("保存成功！");
			 if($("#jlBanbenId").val()!=undefined){
				 window.location.href="goEditResume?id="+$("#jlBanbenId").val();
			 }else{
				 window.location.href="goCreateResume";
			 }


		 }
	 });


 }

 /*if($("#tjqzzt").text()=='目前正在找工作'){
	 qzzt=0;
 }else if($("#qzzt").text()=='观望有好机会会考虑'){
	 qzzt=1;
 }else if($("#qzzt").text()=='我目前不想换工作'){
	 qzzt=2;
 }

 if($("#hyzt").text()=='已婚'){
	 hyzt=0;
 }else if($("#hyzt").text()=='未婚'){
	 hyzt=1;
 }else if($("#hyzt").text()=='保密'){
	 hyzt=2;
 }

 if($("#zzmm").text()=='中共党员'){
	 zzmm=0;
 }else if($("#zzmm").text()=='共青团员'){
	 zzmm=1;
 }else if($("#zzmm").text()=='无党派民主人士'){
	 zzmm=2;
 }else if($("#zzmm").text()=='普通群众'){
	 zzmm=3;
 }else if($("#zzmm").text()=='其他'){
	 zzmm=4;
 }

 if($("#zjlx").text()=='身份证'){
	 zjlx=0;
 }else if($("#zjlx").text()=='护照'){
	 zjlx=1;
 }else if($("#zjlx").text()=='军人证'){
	 zjlx=2;
 }else if($("#zjlx").text()=='港澳居民来往内地通行证'){
	 zjlx=3;
 }else if($("#zjlx").text()=='外国人永久居留身份证'){
	 zjlx=4;
 }else if($("#zjlx").text()=='其它'){
	 zjlx=5;
 }

 if($("#lxfs").text()=='家庭电话'){
	 lxfs=0;
 }else if($("#lxfs").text()=='公司电话'){
	 lxfs=1;
 }else if($("#lxfs").text()=='微信'){
	 lxfs=2;
 }else if($("#lxfs").text()=='QQ号'){
	 lxfs=3;
 }*/

//详细信息点击显示  更多展示
function showMoreClickEvent(p_oEvent) {
	if ($('.main').hasClass('eng')) {
		if (($(p_oEvent).hasClass('on'))) {
			$(p_oEvent).removeClass('on').children('span').html('More').next().css('display', 'none').parent().next().css('display', 'none');
		} else {
			$(p_oEvent).addClass('on').children('span').html('Hide').next().css('display', 'block').parent().next().css('display', 'block');
		}
	} else {
		if (($(p_oEvent).hasClass('on')) && (!$(p_oEvent).parent().parent().hasClass('m1'))) {
			$(p_oEvent).removeClass('on').children('span').html('更多展开').next().css('display', 'none').parent().next().css('display', 'none');
		} else if ((!$(p_oEvent).hasClass('on')) && (!$(p_oEvent).parent().parent().hasClass('m1'))) {
			$(p_oEvent).addClass('on').children('span').html('更多收起').next().css('display', 'block').parent().next().css('display', 'block');
		} else if (($(p_oEvent).hasClass('on')) && ($(p_oEvent).parent().parent().hasClass('m1'))) {
			$(p_oEvent).removeClass('on').children('em').css('display', 'none').parent().next().css('display', 'none');
		} else {
			$(p_oEvent).addClass('on').children('em').css('display', 'block').parent().next().css('display', 'block');
		}
	}
}


//基本信息取消

function cancelButtonClick(o) {

	$("#basedetail_cancel").parents('.box').addClass('top_wrap').removeClass('com');
	$(".face").removeClass('f2');
	//$(".face").addClass('top_wrap');
	$(".btnbox").remove();
	$(".cbox").hide();
	$(".abox").hide();
	if($("#jlBanbenId").val()!=undefined){
		window.location.href="goEditResume?id="+$("#jlBanbenId").val();
	}else{
		window.location.href="goCreateResume";
	}
 }




//求职意向增加
function intention_editButtonClick() {
	var  json={};
	if($("#jlBanbenId").val()!=undefined){
		json.jlBbId=$("#jlBanbenId").val();
	}
	$.ajax({
		type: "POST",
		async:false,
		url: "findQzyxByJlbbIdThree",
		data:json,
		dataType: "json",
		success: function(data){

			$("#intention_edit").siblings('.bh_div2').html('<div> <div class="bd com">  <div class="con"> '
				+ ' <div class="c" float-index="false">  ' +
				'<label>期望薪资</label><i>*</i><div class="h30">    ' +
				'<div class="sh ">' +
				'<div class="txt pointer" float-on="false" id="int_expectarea_div">' +
				'<input class="ef" type="text" id="zjaddqwxz"  maxlength="30" placeholder="请填写期望薪资格式为：数字-数字" input-type="selectionlist">' +
				'</div>' +
				'</div><span class="f12">请合理输入薪资区间，格式为数字-数字例：2001-4001元</span>' +
				'  </div>' +
				'</div>'
				+ '<div class="c" float-index="false" id="int_expectarea_index">' +
				'<label>地点</label><i>*</i>' +
				'<div class="h30">' +
				'<div class="sh ">' +
				'<div class="txt pointer" id="int_expectarea_under"> ' +
				'<input class="ef" type="text"  id="zjadddd" placeholder="请填写地点">' +
				'</div>' +
				' </div>' +
				'</div>' +
				'</div>'
				+ '<div class="c" float-index="false" id="int_expectarea_index">' +
				'<label>职能</label><i>*</i>' +
				'<div class="h30">' +
				'<div class="sh">' +
				'<div class="txt pointer" float-on="false" id="work_func_div">' +
				'<input id="zjaddwork_func_input" placeholder="填写/选择"  class="ef cursor" type="text" >' +
				' <a id="result1" cols="80" rows="10" href="javascript:void(0);" class="zwxz" onclick="result1()"></a>' +
				'<div class="ul u3" id="work_func_list"> </div></div><input id="work_func" type="hidden" value="" pre_code=""> </div><span class="f12">注：可填写或选择，如选择器中不存在，可填写！</span></div></div>'
				+ '<div class="c" float-index="false" id="int_position_index">' +
				'<label>职位<i>*</i></label>                ' +
				'<div class="h30"><div class="sh">' +
				'<div class="txt" float-on="false" id="int_position_div"> ' +
				'<input class="ef" id="zjaddint_position" maxlength="50" type="text" >' +
				'  <div class="ul u3" id="int_position_list"> ' +
				'</div>' +
				'</div> ' +
				'</div>' +
				'<div class="err" id="int_position_warning" style="display:none"><em class="icons"></em></div></div></div>'
				+ '<div class="c" float-index="false" id="zjaddint_expectindustry_index"><label>行业<i>*</i></label>' +
				'<div class="tbox" id="int_expectindustry_under"><div class="clear"></div></div>' +
				'<div class="h30">' +
				'<div class="sh">' +
				'<div class="txt pointer" float-on="false" id="int_expectindustry_div">' +
				'<input placeholder="填写/选择"  class="ef cursor" id="zjaddint_expectindustry_input" type="text" style="width:85%" maxlength="24">' +
				'<input id="btn_IndustryID" type="button"  onclick="IndustrySelect(\'zjaddint_expectindustry_input\')" />' +
				'<div class="ul u3" id="int_expectindustry_list"></div></div><input id="int_expectindustry" type="hidden" value=""></div><span class="f12">注：可填写或选择，如选择器中不存在，可填写！</span>' +
				'<div class="err" id="int_expectindustry_warning" style="display:none"><em class="icons"></em></div></div></div>'
				+ '<div class="c" float-index="false" id="int_resumekey_index"> ' +
				'<label>个人标签</label>' +
				'<div class="tbox" id="int_resumekey_under">' +
				'<div class="clear"></div></div>' +
				'<div class="h30">  <div class="sh">' +
				'<div class="txt pointer" float-on="false" id="int_resumekey_div">' +
				'<input class="ef cursor" maxlength="24" id="zjaddint_resumekey_input" onkeyup="showbutton(event)" type="text" >' +
				'<span class="ic pointer" id="int_resumekey_add" style="display:none">添加</span>' +
				'<div class="ul u3" id="int_resumekey_list"> </div></div><input id="int_resumekey" type="hidden" value=""> </div><span class="f12">限10个，每个词不超过12个中文或24个英文</span>' +
				' <div class="err" id="int_resumekey_warning" style="display:none"><em class="icons"></em></div> ' +
				' </div>' +
				'</div>'
				+ '<div class="c"><label>自我评价</label>' +
				'<textarea placeholder="介绍自己，说明自己的最大优势，让企业看到你的闪光点"  id="zjaddint_selfintro" maxlength="500"></textarea> </div>' +
				'<div class="h30" style="margin: 10px 0 0 0;">' +
				'<div class="c c3" float-index="false" style=""><label>到岗时间<i>*</i></label>' +
				'<div class="sh" style="width: 145px">' +
				'<div class="txt pointer" id="int_entrytime_list" float-on="false"> ' +
				'<select class="ef select3" id="zjadddgsj">' +
				'<option value="0">随时</option>' +
				'<option value="1">1周内</option>' +
				'<option value="2">1个月内</option>' +
				'<option value="3">3个月内</option>' +
				'<option value="4">待定</option>' +
				'</select>' +
				'</div></div> </div>' +
				'<div class="c c3" float-index="false">' +
				'<label>工作类型<i>*</i></label>' +
				'<div class="sh" style="width: 145px;"><div class="txt pointer" id="int_seektype_list" float-on="false"> ' +
				'<select class="ef select3" id="zjaddgzlx">' +
				'<option value="0">全职</option>' +
				'<option value="1">兼职</option>' +
				'<option value="2">实习</option>' +
				'</select>' +
				'</div><input id="int_seektype" type="hidden" value="0"></div></div></div> '
				+ '<div class="btnbox"> <span class="p_but" id="intention_save" onclick="saveQzyx();">保存</span> ' +
				'<span class="p_but gray" id="intention_cancel" onclick="intentionButtonClick(this)">取消</span></div></div></div>');
			$("#intention_edit").siblings('.icons').remove();
			$("#intention").removeClass('m1');
			$("#intention").removeClass('top_wrap').addClass('b2');
			$(this).remove();
			//data不为空时为上面HTML赋值
			zjaddqwxz(data);
			zjadddd(data);
			zjaddwork_func_input(data);
			zjaddint_position(data);
			zjaddint_expectindustry_input1(data);
			zjaddint_resumekey_input(data);
			zjaddint_selfintro(data);
			zjadddgsj(data);
			zjaddgzlx(data);

		}
	});
}
 function result1(){
	 $("#result1").positionSelect({
		 containerId: "positionDiv",
		 className: "big-window",
		 nameId: "zjaddwork_func_input",
		 required: true,
		 maxCount: 5,
		 onConfirm: null
	 });
 }
function zjaddqwxz(data){
	if(!isNull(data)){
		$('#zjaddqwxz').val(data.qzyx.qzyxQwxz);
	}
}
 function zjadddd(data){
	 if(!isNull(data)){
		 $('#zjadddd').val(data.qzyx.qzyxDd);
	 }
 }
 function zjaddwork_func_input(data){
	 if(!isNull(data)){
		 $('#zjaddwork_func_input').val(data.qzyx.qzyxZn);
	 }
 }
 function zjaddint_position(data){
	 if(!isNull(data)){
		 $('#zjaddint_position').val(data.qzyx.qzyxZw);
	 }
 }
 function zjaddint_expectindustry_input1(data){
	 if(!isNull(data)){
		 $('#zjaddint_expectindustry_input').val(data.qzyx.qzyxHy);
	 }
 }
 function zjaddint_resumekey_input(data){
	 if(!isNull(data)){
		 $('#zjaddint_resumekey_input').val(data.qzyx.qzyxGrbq);
	 }
 }
 function zjaddint_selfintro(data){
	 if(!isNull(data)){
		 $('#zjaddint_selfintro').val(data.qzyx.qzyxZwpj);
	 }
 }
 function zjadddgsj(data){
	 if(!isNull(data)){
		 $('#zjadddgsj').val(data.qzyx.qzyxDgsj);
	 }else{
		 $('#zjadddgsj').val('2');
	 }
 }
 function zjaddgzlx(data){
	 if(!isNull(data)){
		 $('#zjaddgzlx').val(data.qzyx.qzyxGzlx);
	 }else{
		 $('#zjaddgzlx').val('0');
	 }
 }


//保存求职意向
 function saveQzyx(){
	 var  json={};
	 if(isNull($("#zjadddd").val())){
		 alert("地点不能为空！！！");
		 return false;
	 }
	 if(isNull($("#zjaddwork_func_input").val())){
		 alert("职能不能为空！！！");
		 return false;
	 }
	 if(isNull($("#zjaddint_expectindustry_input").val())){
		 alert("行业不能为空！！！");
		 return false;
	 }
	 if(isNull($("#zjaddint_position").val())){
		 alert("职位不能为空！！！");
		 return false;
	 }
	 var zjadd=$("#zjaddqwxz").val();

	 if(isNull(zjadd)){
		 alert("期望薪资不能为空！！！");
		 return false;
	 }else if(zjadd.indexOf("-")==-1){
		 alert("期望薪资格式不对，请修改为正确格式数字-数字，例2001-4001！！！");
		 return false;
	 }
	 var zj=zjadd.split("-");
	 if(zj.length==2){
		 if(isNull(trim(zj[0]))){
			 alert("期望薪资格式不对，请修改为正确格式数字-数字，例2001-4001！！！");
			 return false;
		 }else if(!isNumber(parseInt(trim(zj[0])))){
			 alert("期望薪资格式不对，请修改为正确格式数字-数字，例2001-4001！！！");
			 return false;
		 }

		 if(isNull(trim(zj[1]))){
			 alert("期望薪资格式不对，请修改为正确格式数字-数字，例2001-4001！！！");
			 return false;
		 }else  if(!isNumber(parseInt(trim(zj[1])))){
			 alert("期望薪资格式不对，请修改为正确格式数字-数字，例2001-4001！！！");
			 return false;
		 }
		 if(parseInt(trim(zj[0]))>=parseInt(trim(zj[1]))){
			 alert("期望薪资格式不对，请修改为正确格式数字-数字，例2001-4001！！！");
			 return false;
		 }
	 }else{
		 alert("期望薪资格式不对，请修改为正确格式数字-数字，例2001-4001！！！");
		 return false;
	 }

	 var gzlx=parseInt($("#zjaddgzlx").val());
	 var dgsj=parseInt($("#zjadddgsj").val());
	 if(!isNumber(gzlx)){
		 alert("工作类型不能为空！！！");
		 return false;
	 }
	 if(!isNumber(dgsj)){
		 alert("到岗时间不能为空！！！");
		 return false;
	 }


	 if($("#qzyxId").val()!=undefined){
		 json.qzyxId=$("#qzyxId").val();
	 }
	 if($("#jlBanbenId").val()!=undefined){
		 json.jlBbId=$("#jlBanbenId").val();
	 }
	 if($("#zjaddint_selfintro").val().length>500){
		 alert("自我描述长度不能超过500！！！");
		 return false;
	 }
	 json.qzyxDd=$("#zjadddd").val();
	 json.qzyxZn=$("#zjaddwork_func_input").val();
	 json.qzyxZw=$("#zjaddint_position").val();
	 json.qzyxQwxz=$("#zjaddqwxz").val();
	 json.qzyxHy=$("#zjaddint_expectindustry_input").val();
	 json.qzyxZwpj=$("#zjaddint_selfintro").val();
	 json.qzyxGrbq=$("#zjaddint_resumekey_input").val();
	 json.qzyxDgsj=dgsj;
	 json.qzyxGzlx=gzlx;
	 $.ajax({
		 type: "POST",
		 async:false,
		 url: "saveUserQzyx",
		 data:json,
		 dataType: "json",
		 success: function(data){
			 alert(data.message);
			 if(!isNull(data.qzyx.jlBbId)){
				 window.location.href="goEditResume?id="+data.qzyx.jlBbId;
			 }else{
				 window.location.href="goCreateResume";
			 }
		 }
	 });
 }
//求职意向取消

function intentionButtonClick(o) {
	$("#intention").addClass('m1');
	$("#intention").removeClass('b2').addClass('top_wrap');
	if($("#jlBanbenId").val()!=undefined){
		window.location.href="goEditResume?id="+$("#jlBanbenId").val();
	}else{
		window.location.href="goCreateResume";
	}

}


//工作经验增加
function workButtonClick(work) {
	$(work).removeClass('add').addClass('unadd');
	$("#work").html('<div class="bd com" id="work_modify_">'
    +'<div class="con">'
		+' <div class="c" float-index="false"> <label>时间</label><i>*</i>' +
		'<div class="h30"> <div class="sh sx">' +
		'<div class="txt pointer" id="work_timefrom_calendar" float-on="false">' +
		'<input class="ef date_img" id="addwork_timefrom_input" type="date"  ></div></div>' +
		'<span class="dao">到</span><div class="sh sx"><div class="txt pointer" id="work_timeto_calendar" float-on="false">' +
		'<input class="ef date_img" id="addwork_timeto_input" type="date" > </div></div></div></div>'
		+'<div class="c" float-index="false" id="work_compname_index"><label>公司</label><i>*</i> ' +
		'<div class="h30"> <div class="sh"><div class="txt" float-on="false" id="work_compname_div"> ' +
		'<input class="ef" id="addwork_compname" maxlength="50" type="text" >' +
		'<div class="ul u3" id="work_compname_list"> </div> </div></div></div></div>'
		+'<div class="c " float-index="false" id="work_func_index"> ' +
		'<label>职能</label><i>*</i><div class="sh">' +
		'<div class="txt pointer" float-on="false" id="work_func_div">' +
		'<input id="addwork_func_input" placeholder="填写/选择"  class="ef cursor" type="text" >' +
		'<a id="result3" cols="80" rows="10" href="javascript:void(0);" class="zwxz" onclick="result3()"></a>' +
		'<div class="ul u3" id="work_func_list"></div></div><input id="work_func" type="hidden"  ></div><span class="f12">注：可填写或选择，如选择器中不存在，可填写！</span></div>' +
		'<div class="c " float-index="false" id="work_industry_index"><label>行业</label><i>*</i>' +
		'<div class="sh"><div class="txt pointer" float-on="false" id="work_industry_div">' +
		' <input placeholder="填写/选择"  class="ef cursor" style="width: 85%;" id="addwork_industry_input" type="text" >' +
		'<input  type="button" id="btn_jobArea"  onclick="IndustrySelect(\'addwork_industry_input\')" />' +
		'<div class="ul u3" id="work_industry_list"></div></div><input id="work_industry" type="hidden"  ></div><span class="f12">注：可填写或选择，如选择器中不存在，可填写！</span>' +
		'<input id="work_industry" type="hidden"  pre_code=""></div>'
		+'<div class="h30"><div class="c c1" float-index="false" id="work_position_index"><label>职位</label><i>*</i>' +
		'<div class="sh"><div class="txt" float-on="false" id="work_position_div">' +
		'<input class="ef" id="addwork_position" maxlength="70" type="text" ></div></div></div>' +
		'<div class="c c4" float-index="false"><label>公司规模</label><div class="sh">' +
		'<div class="txt pointer" id="work_companysize_list" float-on="false">' +
		'<select class="ef select4" id="addgsgm">' +
		'<option value="0">少于50人</option>' +
		'<option value="1">150-500人</option>' +
		'<option value="2">500-1000人</option>' +
		'<option value="3">5000-10000人</option>' +
		'</select>' +
		'</div>' +
		'<input id="work_companysize" type="hidden" ></div></div></div>'
		+'<div class="h30"><div class="c c1"><label>部门</label><i>*</i>' +
		'<div class="sh"><div class="txt"><input class="ef" id="addwork_department" maxlength="50" type="text" ></div></div></div>' +
		'<div class="c c4" float-index="false"><label>公司性质</label><i>*</i>' +
		'<div class="sh">' +
		'<div class="txt pointer" id="work_companytype_list" float-on="false">' +
		'<select class="ef select4" id="addgsxz">' +
		'<option value="0">外资（欧美）</option>' +
		'<option value="1">外资（非欧美）</option>' +
		'<option value="2">合资</option>' +
		'<option value="3">国企</option>' +
		'<option value="4">民营公司</option>' +
		'<option value="5">上市公司</option>' +
		'<option value="6">创业公司</option>' +
		'<option value="7">外企代表处</option>' +
		'<option value="8">政府机关</option>' +
		'<option value="9">事业单位</option>' +
		'<option value="10">非营利组织</option>' +
		'</select>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>'
		+'<div class="c"><label>工作描述</label><i>*</i>' +
		'<textarea id="addwork_describe" maxlength="500" placeholder="描述你的职责范围、工作任务及取得的成绩等"></textarea>' +
		'<div class="h30"></div></div>'
		+'<div class="c"><label>工作类型</label>' +
		'<div class="btox txt" id="work_type_div">' +
		'<select class="ef" id="addworktype">' +
		'<option value="0">全职</option>' +
		'<option value="1">兼职</option>' +
		'<option value="2">实习</option>' +
		'</select>' +
		'</div></div>'
		+'<div class="h30">' +
		'<div class="c c5"><label>下属人数</label>' +
		'<div class="sh"><div class="txt">' +
		'<input class="ef" maxlength="5" id="addwork_reportperson" type="text" ></div> </div></div>'
		+'<div class="c c5 cl"><label>汇报对象</label><div class="sh">' +
		'<div class="txt"><input class="ef" id="addwork_reportboss" maxlength="50" type="text" ></div></div></div>'
		+'<div class="c c7 cl"><label>离职原因</label><div class="sh">' +
		'<div class="txt"> <input class="ef" id="addwork_leavereason" maxlength="200" type="text" ></div></div></div></div>'
		+'<div class="c"><label>主要业绩</label>' +
		'<textarea id="addwork_score" maxlength="500"  placeholder="描述取得业绩的主要内容，以充分证明作为高级人才的能力和价值"></textarea>' +
		'<div class="h30"></div></div>'
		+'<div class="c"><label>海外经历</label>' +
		'<div class="btox txt" id="work_isoverseas_div">' +
		'<select class="ef" id="addoverseawork">' +
		'<option value="0">是</option>' +
		'<option value="1" selected="selected">否</option>' +
		'</select>' +
		'</div></div></div></div></div>'
    +'<div class="btnbox"><span class="p_but" id="work_save_" onclick="saveGzjy(1);">保存</span><span class="p_but gray" id="work_cancel_" onclick="work_cancel_ButtonClick(this)">取消</span></div></div>');
}

 function result3(){
	 $("#result3").positionSelect({
		 containerId: "positionDiv",
		 className: "big-window",
		 nameId: "addwork_func_input",
		 required: true,
		 maxCount: 5,
		 onConfirm: null
	 });
 }
/*
添加工作经验
 */
 function saveGzjy(gzjyId){
	 if(isNull($("#addwork_timefrom_input").val())){
		 alert("开始时间不能为空！！！");
		 return false;
	 }
	 if(isNull($("#addwork_timeto_input").val())){
		 alert("结束时间不能为空！！！");
		 return false;
	 }
	 if(isNull($("#addwork_compname").val())){
		 alert("公司不能为空！！！");
		 return false;
	 }
	 if(isNull($("#addwork_func_input").val())){
		 alert("职能不能为空！！！");
		 return false;
	 }
	 if(isNull($("#addwork_industry_input").val())){
		 alert("行业不能为空！！！");
		 return false;
	 }
	 if(isNull($("#addwork_position").val())){
		 alert("职位不能为空！！！");
		 return false;
	 }
	 if(isNull($("#addwork_department").val())){
		 alert("部门不能为空！！！");
		 return false;

	 }
	 if(isNull($("#addwork_describe").val())){
		 alert("工作描述不能为空！！！");
		 return false;
	 }
	 if($("#addwork_describe").val().length>500){
		 alert("工作描述长度不能超过500！！！");
		 return false;
	 }
	 if($("#addwork_score").val().length>500){
		 alert("主要业绩长度不能超过500！！！");
		 return false;
	 }

	 var  json={};
	 if(!isNull(gzjyId)){
		 if(gzjyId!=1){
			 json.gzjyId=gzjyId;
		 }
	 }
	 if($("#jlBanbenId").val()!=undefined){
		 json.jlBbId=$("#jlBanbenId").val();
	 }
	 json.gzjyKssj=$("#addwork_timefrom_input").val();
	 json.gzjyJssj=$("#addwork_timeto_input").val();
	 json.gzjyGs=$("#addwork_compname").val();
	 json.gzjyZn=$("#addwork_func_input").val();
	 json.gzjyHy=$("#addwork_industry_input").val();
	 json.gzjyZw=$("#addwork_position").val();
	 var gsgm=parseInt($("#addgsgm").val());;
	 var gsxz=parseInt($("#addgsxz").val());;
	 json.gzjyGsgm=gsgm;
	 json.gzjyBm=$("#addwork_department").val();
	 json.gzjyGsxz=gsxz;
	 json.gzjyGzlx=$("#addworktype").val();
	 json.gzjyHwjl=$("#addoverseawork").val();
	 json.gzjyXsrs=$("#addwork_reportperson").val();
	 json.gzjyHbdx=$("#addwork_reportboss").val();
	 json.gzjyLzyy=$("#addwork_leavereason").val();
	 json.gzjyGzms=$("#addwork_describe").val();
	 json.gzjyZyyj=$("#addwork_score").val();
	 $.ajax({
		 type: "POST",
		 async:false,
		 url: "saveGzjy",
		 data:json,
		 dataType: "json",
		 success: function(data){
			 alert(data.message);
			 window.location.href="goEditResume?id="+$("#jlBanbenId").val();
		 }
	 });
 }
 /*
 修改工作经验
  */
function gzjyEdit(dataID){
	var  json={};
	if($("#jlBanbenId").val()!=undefined){
		json.jlBbId=$("#jlBanbenId").val();
	}
	json.gzjyId=dataID;
	$.ajax({
		type: "POST",
		async: false,
		url: "findGzjyByIdForJlgzjy",
		data: json,
		dataType: "json",
		success: function (data) {
			$(".gzjy").css("height","840");
			$("#work").css({"position": "absolute","left": "0","top": "60px"});
			$(work).removeClass('add').addClass('unadd');
			$("#work").html('<div class="bd com" id="work_modify_">'
				+ '<div class="con">'
				+ ' <div class="c" float-index="false"> <label>时间</label><i>*</i>' +
				'<div class="h30"> <div class="sh sx">' +
				'<div class="txt pointer" id="work_timefrom_calendar" float-on="false">' +
				'<input class="ef date_img" id="eaddwork_timefrom_input" type="date"  value="'+data.gzjy.gzjyKssj+'"></div></div>' +
				'<span class="dao">到</span><div class="sh sx"><div class="txt pointer" id="work_timeto_calendar" float-on="false">' +
				'<input class="ef date_img" id="eaddwork_timeto_input" type="date" value="'+data.gzjy.gzjyJssj+'"> </div></div></div></div>'
				+ '<div class="c" float-index="false" id="work_compname_index"><label>公司</label><i>*</i> ' +
				'<div class="h30"> <div class="sh"><div class="txt" float-on="false" id="work_compname_div"> ' +
				'<input class="ef" id="eaddwork_compname" maxlength="50" type="text" value="'+data.gzjy.gzjyGs+'">' +
				'<div class="ul u3" id="work_compname_list"> </div> </div></div></div></div>'
				+ ' <div class="c " float-index="false" id="work_func_index"> ' +
				'<label>职能</label><i>*</i><div class="sh">' +
				'<div class="txt pointer" float-on="false" id="work_func_div">' +
				'<input id="eaddwork_func_input"  placeholder="填写/选择" class="ef cursor" type="text" value="'+data.gzjy.gzjyZn+'" >' +
				'<a id="result2" cols="80" rows="10" href="javascript:void(0);" class="zwxz" onclick="result2()"></a>' +
				'<div class="ul u3" id="work_func_list"></div></div><input id="work_func" type="hidden"  ></div><span class="f12">注：可填写或选择，如选择器中不存在，可填写！</span></div>' +
				'<div class="c " float-index="false" id="work_industry_index"><label>行业</label><i>*</i>' +
				'<div class="sh"><div class="txt pointer" float-on="false" id="work_industry_div">' +
				' <input placeholder="填写/选择" class="ef cursor"   style="width: 85%;" id="eaddwork_industry_input" type="text"  value="'+data.gzjy.gzjyHy+'">' +
				'<input id="btn_residency" type="button"  onclick="IndustrySelect(\'eaddwork_industry_input\')" />' +
				'<div class="ul u3" id="work_industry_list"></div></div><input id="work_industry" type="hidden"  ></div><span class="f12">注：可填写或选择，如选择器中不存在，可填写！</span>' +
				'<input id="work_industry" type="hidden"  pre_code=""></div>'
				+ '<div class="h30"><div class="c c1" float-index="false" id="work_position_index"><label>职位</label><i>*</i>' +
				'<div class="sh"><div class="txt" float-on="false" id="work_position_div">' +
				'<input class="ef" id="eaddwork_position" maxlength="70" type="text" value="'+data.gzjy.gzjyZw+'"></div></div></div>' +
				'<div class="c c4" float-index="false"><label>公司规模</label><div class="sh">' +
				'<div class="txt pointer" id="work_companysize_list" float-on="false">' +
				'<select class="ef select4" id="eaddgsgm">' +
				'<option value="0">少于50人</option>' +
				'<option value="1">150-500人</option>' +
				'<option value="2">500-1000人</option>' +
				'<option value="3">5000-10000人</option>' +
				'</select>' +
				'</div>' +
				'<input id="work_companysize" type="hidden" ></div></div></div>'
				+ '<div class="h30"><div class="c c1"><label>部门</label><i>*</i>' +
				'<div class="sh"><div class="txt"><input class="ef" id="eaddwork_department" maxlength="50" type="text" value="'+data.gzjy.gzjyBm+'"></div></div></div>' +
				'<div class="c c4" float-index="false"><label>公司性质</label><i>*</i>' +
				'<div class="sh">' +
				'<div class="txt pointer" id="work_companytype_list" float-on="false">' +
				'<select class="ef select4" id="eaddgsxz">' +
				'<option value="0">外资（欧美）</option>' +
				'<option value="1">外资（非欧美）</option>' +
				'<option value="2">合资</option>' +
				'<option value="3">国企</option>' +
				'<option value="4">民营公司</option>' +
				'<option value="5">上市公司</option>' +
				'<option value="6">创业公司</option>' +
				'<option value="7">外企代表处</option>' +
				'<option value="8">政府机关</option>' +
				'<option value="9">事业单位</option>' +
				'<option value="10">非营利组织</option>' +
				'</select>' +
				'</div>' +
				'</div>' +
				'</div>' +
				'</div>'
				+ '<div class="c"><label>工作描述</label><i>*</i>' +
				'<textarea id="eaddwork_describe" maxlength="500" placeholder="描述你的职责范围、工作任务及取得的成绩等">'+data.gzjy.gzjyGzms+'</textarea>' +
				'<div class="h30"> </div></div>'
				+ '<div class="c"><label>工作类型</label>' +
				'<div class="btox txt" id="work_type_div">' +
				'<select class="ef" id="eaddworktype">' +
				'<option value="0">全职</option>' +
				'<option value="1">兼职</option>' +
				'<option value="2">实习</option>' +
				'</select>' +
				'</div></div>'
				+ '<div class="h30">' +
				'<div class="c c5"><label>下属人数</label>' +
				'<div class="sh"><div class="txt">' +
				'<input class="ef" maxlength="5" id="eaddwork_reportperson" type="text" value="'+data.gzjy.gzjyXsrs+'"></div> </div></div>'
				+ '<div class="c c5 cl"><label>汇报对象</label><div class="sh">' +
				'<div class="txt"><input class="ef" id="eaddwork_reportboss" maxlength="50" type="text" value="'+data.gzjy.gzjyHbdx+'"></div></div></div>'
				+ '<div class="c c7 cl"><label>离职原因</label><div class="sh">' +
				'<div class="txt"> <input class="ef" id="eaddwork_leavereason" maxlength="200" type="text" value="'+data.gzjy.gzjyLzyy+'"></div></div></div></div>'
				+ '<div class="c"><label>主要业绩</label>' +
				'<textarea id="eaddwork_score" maxlength="500" placeholder="描述取得业绩的主要内容，以充分证明作为高级人才的能力和价值">'+data.gzjy.gzjyZyyj+'</textarea>' +
				'<div class="h30"></div></div>'
				+ '<div class="c"><label>海外经历</label>' +
				'<div class="btox txt" id="work_isoverseas_div">' +
				'<select class="ef" id="eaddoverseawork">' +
				'<option value="0">是</option>' +
				'<option value="1">否</option>' +
				'</select>' +
				'</div></div></div></div></div>'
				+ '<div class="btnbox"><span class="p_but" id="work_save_" onclick="saveGzjy1(\'' + dataID + '\');">保存</span><span class="p_but gray" id="work_cancel_" onclick="work_cancel_ButtonClick(this)">取消</span></div></div>');
			eaddgsgm(data);
			eaddgsxz(data);
			eaddworktype(data);
			eaddoverseawork(data);

		}
	});
}

 function result2(){
	 $("#result2").positionSelect({
		 containerId: "positionDiv",
		 className: "big-window",
		 nameId: "eaddwork_func_input",
		 required: true,
		 maxCount: 5,
		 onConfirm: null
	 });
 }

 function eaddgsgm(data){
	if(!isNull(data)){
		$("#eaddgsgm").val(data.gzjy.gzjyGsgm);
	}
 }
 function eaddgsxz(data){
	 if(!isNull(data)){
		 $("#eaddgsxz").val(data.gzjy.gzjyGsxz);
	 }
 }
 function eaddworktype(data){
	 if(!isNull(data)){
		 $("#eaddworktype").val(data.gzjy.gzjyGzlx);
	 }
 }
 function eaddoverseawork(data){
	 if(!isNull(data)){
		 $("#eaddoverseawork").val(data.gzjy.gzjyHwjl);
	 }
 }

 /*
修改工作经验
 */
 function saveGzjy1(gzjyId){
	 if(isNull($("#eaddwork_timefrom_input").val())){
		 alert("开始时间不能为空！！！");
		 return false;
	 }
	 if(isNull($("#eaddwork_timeto_input").val())){
		 alert("结束时间不能为空！！！");
		 return false;
	 }
	 if(isNull($("#eaddwork_compname").val())){
		 alert("公司不能为空！！！");
		 return false;
	 }
	 if(isNull($("#eaddwork_func_input").val())){
		 alert("职能不能为空！！！");
		 return false;
	 }
	 if(isNull($("#eaddwork_industry_input").val())){
		 alert("行业不能为空！！！");
		 return false;
	 }
	 if(isNull($("#eaddwork_position").val())){
		 alert("职位不能为空！！！");
		 return false;
	 }
	 if(isNull($("#eaddwork_department").val())){
		 alert("部门不能为空！！！");
		 return false;

	 }
	 if(isNull($("#eaddwork_describe").val())){
		 alert("工作描述不能为空！！！");
		 return false;
	 }
	 if($("#eaddwork_describe").val().length>500){
		 alert("工作描述长度不能超过500！！！");
		 return false;
	 }
	 if($("#eaddwork_score").val().length>500){
		 alert("主要业绩长度不能超过500！！！");
		 return false;
	 }

	 var  json={};
	 if(!isNull(gzjyId)){
		 if(gzjyId!=1){
			 json.gzjyId=gzjyId;
		 }
	 }
	 if($("#jlBanbenId").val()!=undefined){
		 json.jlBbId=$("#jlBanbenId").val();
	 }
	 json.gzjyKssj=$("#eaddwork_timefrom_input").val();
	 json.gzjyJssj=$("#eaddwork_timeto_input").val();
	 json.gzjyGs=$("#eaddwork_compname").val();
	 json.gzjyZn=$("#eaddwork_func_input").val();
	 json.gzjyHy=$("#eaddwork_industry_input").val();
	 json.gzjyZw=$("#eaddwork_position").val();
	 var gsgm=parseInt($("#eaddgsgm").val());;
	 var gsxz=parseInt($("#eaddgsxz").val());;
	 json.gzjyGsgm=gsgm;
	 json.gzjyBm=$("#eaddwork_department").val();
	 json.gzjyGsxz=gsxz;
	 json.gzjyGzlx=$("#eaddworktype").val();
	 json.gzjyHwjl=$("#eaddoverseawork").val();
	 json.gzjyXsrs=$("#eaddwork_reportperson").val();
	 json.gzjyHbdx=$("#eaddwork_reportboss").val();
	 json.gzjyLzyy=$("#eaddwork_leavereason").val();
	 json.gzjyGzms=$("#eaddwork_describe").val();
	 json.gzjyZyyj=$("#eaddwork_score").val();
	 $.ajax({
		 type: "POST",
		 async:false,
		 url: "saveGzjy",
		 data:json,
		 dataType: "json",
		 success: function(data){
			 alert(data.message);
			 window.location.href="goEditResume?id="+$("#jlBanbenId").val();
		 }
	 });
 }
/*
删除工作经验
 */
 function gzjyDel(dataID){
	 if(confirm("确定删除该条工作经验么？")){
		 $.ajax({
			 type: "POST",
			 async:false,
			 url: "deleteGzjyById",
			 data:{ids:dataID},
			 dataType: "json",
			 success: function(data){
				 window.location.href="goEditResume?id="+$("#jlBanbenId").val();
			 }});
	 }
 }

//工作经验取消
function work_cancel_ButtonClick(work) {
	if($("#jlBanbenId").val()!=undefined){
		window.location.href="goEditResume?id="+$("#jlBanbenId").val();
	}else{
		window.location.href="goCreateResume";
	}
}
//教育经历增加
function EducationButtonClick(education) {
	$(education).removeClass('add').addClass('unadd');
	$("#education").html('<div class="bd com" id="education_modify_">'
		+'<div class="con">'
		+' <div class="c" float-index="false">' +
		'<label>时间</label><i>*</i>' +
		'<div class="h30"><div class="sh sx">' +
		'<div class="txt pointer" id="edu_timefrom_calendar" float-on="false">' +
		'<input class="ef date_img" id="addedu_timefrom_input" type="date" ></div></div>' +
		'<span class="dao">到</span><div class="sh sx">' +
		'<div class="txt pointer" id="edu_timeto_calendar" float-on="false">' +
		'<input class="ef date_img" id="addedu_timeto_input" type="date">' +
		'</div> </div></div></div>'
		+'<div class="c" float-index="false" maxlength="50" id="edu_schoolname_index"> ' +
		'<label>学校</label><i>*</i><div class="h30"><div class="sh">' +
		'<div class="txt" float-on="false" id="edu_schoolname_div">' +
		'<input class="ef" maxlength="10" id="addedu_schoolname" type="text"></div> </div></div> </div>'
		+'<div class="c" float-index="false"><label>学历/学位</label><i>*</i>' +
		'<div class="h30"><div class="sh mr"><div class="txt pointer" id="edu_degree_list" float-on="false">' +
		'<select class="ef select3" id="addxl" style="background-position: 170px;">' +
		'<option value="0">初中及以下</option>' +
		'<option value="1">高中</option>' +
		'<option value="2">中专</option>' +
		'<option value="3">大专</option>' +
		'<option value="4">本科</option>' +
		'<option value="5">硕士</option>' +
		'<option value="6">博士</option>' +
		'<option value="7">MBA</option>' +
		'</select>' +
		'</div></div><span>' +
		'<input class="tz" type="checkbox" checked="checked" id="addisfulltime"/>全日制</span></div></div>'
		+'<div class="c" float-index="false" id="edu_major_index">' +
		'<label>专业</label><i>*</i><div class="h30"><div class="sh mr">' +
		'<div class="txt pointer" float-on="false" id="edu_major_div">' +
		'<input class="ef cursor" id="addedu_major_input" type="text"  ></div> </div></div></div>'
		+'<div class="c"><label>专业描述</label>' +
		'<textarea id="addedu_describe" maxlength="500"  placeholder="描述在校期间所学的专业，主要包括课程内容、毕业设计等"></textarea>' +
		'<div class="h30">' +
		'</div></div>'
		+'<div class="c"><label>海外经历</label>' +
		'<div class="btox txt" id="edu_isoverseas_div">' +
		'<select class="ef " id="addhwjla">' +
		'<option value="0">是</option>' +
		'<option value="1" selected>否</option>' +
		'</select>' +
		'</div></div></div>');
	$("#education").after('<div class="btnbox"><span class="p_but" id="education_save_" onclick="saveJyjl();">保存</span><span class="p_but gray" id="education_cancel_" onclick="education_cancel_ButtonClick(this)">取消</span> </div></div>'
    +'</div>');
}
/*
添加教育经历
 */
 function saveJyjl(){
	 if(isNull($("#addedu_timefrom_input").val())){
		 alert("开始时间不能为空！！！");
		 return false;
	 }
	 if(isNull($("#addedu_timeto_input").val())){
		 alert("结束时间不能为空！！！");
		 return false;
	 }
	 if(isNull($("#addedu_schoolname").val())){
		 alert("学校不能为空！！！");
		 return false;
	 }
	 if(isNull($("#addedu_major_input").val())){
		 alert("专业不能为空！！！");
		 return false;
	 }
	 if($("#addedu_describe").val().length>500){
		 alert("专业描述不能超过500！！！");
		 return false;
	 }
	 var  json={};
	 if($("#jlBanbenId").val()!=undefined){
		 json.jlBbId=$("#jlBanbenId").val();
	 }

	 if(isNull($("#addxl").val())){
		 alert("学历不能为空！！！");
		 return false;
	 }
	 if($("#addisfulltime").is(':checked')){
		 json.jyjlQrz=1;
	 }else{
		 json.jyjlQrz=0;
	 }



	 json.jyjlKssj=$("#addedu_timefrom_input").val();
	 json.jyjlJssj=$("#addedu_timeto_input").val();
	 json.jyjlXx=$("#addedu_schoolname").val();
	 json.jyjlZy=$("#addedu_major_input").val();
	 json.jyjlHwjl=$("#addhwjla").val();
	 json.jyjlZyms=$("#addedu_describe").val();
	 json.jyjlXl=$("#addxl").val();
	 $.ajax({
		 type: "POST",
		 async:false,
		 url: "saveJyjl",
		 data:json,
		 dataType: "json",
		 success: function(data){
			 alert(data.message);
			 window.location.href="goEditResume?id="+data.jyjl.jlBbId;
		 }
	 });
 }
 //教育经历删除方法
 function jyjlDel(dataID){
	 if(confirm("确定删除该条教育经历么？")){
		 $.ajax({
			 type: "POST",
			 async:false,
			 url: "deleteJyjlById",
			 data:{ids:dataID},
			 dataType: "json",
			 success: function(data){
				 window.location.href="goEditResume?id="+$("#jlBanbenId").val();
			 }
		 });
	 }
 }
 /*
 教育经历修改
  */
 function jyjlEdit(dataId){
	 var  json={};
	 if($("#jlBanbenId").val()!=undefined){
		 json.jlBbId=$("#jlBanbenId").val();
	 }
	 json.jyjlId=dataId;
	 $.ajax({
		 type: "POST",
		 async: false,
		 url: "findJyjlByIdForSjJljyjl",
		 data: json,
		 dataType: "json",
		 success: function (data) {

			 $("#education_new").removeClass('add').addClass('unadd');
			 $("#education").html('<div class="bd com" id="education_modify_">'
				 + '<div class="con">'
				 + ' <div class="c" float-index="false">' +
				 '<label>时间</label><i>*</i>' +
				 '<div class="h30"><div class="sh sx">' +
				 '<div class="txt pointer" id="edu_timefrom_calendar" float-on="false">' +
				 '<input class="ef date_img" id="eaddedu_timefrom_input" type="date" value="'+data.jyjl.jyjlKssj+'"></div></div>' +
				 '<span class="dao">到</span><div class="sh sx">' +
				 '<div class="txt pointer" id="edu_timeto_calendar" float-on="false">' +
				 '<input class="ef date_img" id="eaddedu_timeto_input" type="date" value="'+data.jyjl.jyjlJssj+'">' +
				 '</div> </div></div></div>'
				 + '<div class="c" float-index="false" maxlength="50" id="edu_schoolname_index"> ' +
				 '<label>学校</label><i>*</i><div class="h30"><div class="sh">' +
				 '<div class="txt" float-on="false" id="edu_schoolname_div">' +
				 '<input class="ef" maxlength="50" id="eaddedu_schoolname" type="text" value="'+data.jyjl.jyjlXx+'"></div> </div></div> </div>'
				 + '<div class="c" float-index="false"><label>学历/学位</label><i>*</i>' +
				 '<div class="h30"><div class="sh mr"><div class="txt pointer" id="edu_degree_list" float-on="false">' +
				 '<select class="ef select3" id="eaddxl" style="background-position: 170px;">' +
				 '<option value="0">初中及以下</option>' +
				 '<option value="1">高中</option>' +
				 '<option value="2">中专</option>' +
				 '<option value="3">大专</option>' +
				 '<option value="4">本科</option>' +
				 '<option value="5">硕士</option>' +
				 '<option value="6">博士</option>' +
				 '<option value="7">MBA</option>' +
				 '</select>' +
				 '</div></div><span>' +
				 '<input class="tz" type="checkbox" id="eaddisfulltime"/>全日制</span></div></div>'
				 + '<div class="c" float-index="false" id="edu_major_index">' +
				 '<label>专业</label><i>*</i><div class="h30"><div class="sh mr">' +
				 '<div class="txt pointer" float-on="false" id="edu_major_div">' +
				 '<input class="ef cursor" id="eaddedu_major_input" type="text"  value="'+data.jyjl.jyjlZy+'"></div> </div></div></div>'
				 + '<div class="c"><label>专业描述</label>' +
				 '<textarea id="eaddedu_describe"  maxlength="500" placeholder="描述在校期间所学的专业，主要包括课程内容、毕业设计等">'+data.jyjl.jyjlZyms+'</textarea>' +
				 '<div class="h30">' +
				 '</div></div>'
				 + '<div class="c"><label>海外经历</label>' +
				 '<div class="btox txt" id="edu_isoverseas_div">' +
				 '<select class="ef " id="eaddhwjla">' +
				 '<option value="0">是</option>' +
				 '<option value="1" selected>否</option>' +
				 '</select>' +
				 '</div></div></div>');
			 $("#education").after('<div class="btnbox"><span class="p_but" id="education_save_" onclick="saveJyjl1(\'' + dataId + '\');">保存</span><span class="p_but gray" id="education_cancel_" onclick="education_cancel_ButtonClick(this)">取消</span> </div></div>'
				 + '</div>');
			 if(!isNull(data.jyjl.jyjlHwjl)){
				 $("#eaddhwjla").val(data.jyjl.jyjlHwjl);
			 }
			 if(!isNull(data.jyjl.jyjlXl)){
				 $("#eaddxl").val(data.jyjl.jyjlXl);
			 }
			 if(!isNull(data.jyjl.jyjlQrz)){
				 var s=data.jyjl.jyjlQrz;
				 if(s==1){
					 $("#eaddisfulltime").attr("checked",true);
				 }else{
					 $("#eaddisfulltime").attr("checked",false);
				 }

			 }
		 }
	 });
 }

 function saveJyjl1(dataId){
	 if(isNull($("#eaddedu_timefrom_input").val())){
		 alert("开始时间不能为空！！！");
		 return false;
	 }
	 if(isNull($("#eaddedu_timeto_input").val())){
		 alert("结束时间不能为空！！！");
		 return false;
	 }
	 if(isNull($("#eaddedu_schoolname").val())){
		 alert("学校不能为空！！！");
		 return false;
	 }
	 if(isNull($("#eaddedu_major_input").val())){
		 alert("专业不能为空！！！");
		 return false;
	 }
	 if($("#eaddedu_describe").val().length>500){
		 alert("专业描述不能超过500！！！");
		 return false;
	 }

	 var  json={};
	 json.jyjlId=dataId;
	 if($("#jlBanbenId").val()!=undefined){
		 json.jlBbId=$("#jlBanbenId").val();
	 }

	 if(isNull($("#eaddxl").val())){
		 alert("学历不能为空！！！");
		 return false;
	 }
	 if($("#eaddisfulltime").is(':checked')){
		 json.jyjlQrz=1;
	 }else{
		 json.jyjlQrz=0;
	 }

	 json.jyjlKssj=$("#eaddedu_timefrom_input").val();
	 json.jyjlJssj=$("#eaddedu_timeto_input").val();
	 json.jyjlXx=$("#eaddedu_schoolname").val();
	 json.jyjlZy=$("#eaddedu_major_input").val();
	 json.jyjlHwjl=$("#eaddhwjla").val();
	 json.jyjlZyms=$("#eaddedu_describe").val();
	 json.jyjlXl=$("#eaddxl").val();
	 $.ajax({
		 type: "POST",
		 async:false,
		 url: "saveJyjl",
		 data:json,
		 dataType: "json",
		 success: function(data){
			 alert(data.message);
			 window.location.href="goEditResume?id="+$("#jlBanbenId").val();
		 }
	 });
 }
//教育经历取消
function education_cancel_ButtonClick(education) {
	$('#education_new').removeClass('unadd').addClass('add');
	if($("#jlBanbenId").val()!=undefined){
		window.location.href="goEditResume?id="+$("#jlBanbenId").val();
	}else{
		window.location.href="goCreateResume";
	}
}
//校内荣誉增加
function schoolaward_newClick(work) {
	$("#schoolaward_new").removeClass('add').addClass('unadd');
	$("#schoolaward").addClass('bd');
	$("#schoolaward").addClass('com');
	$("#schoolaward").html('<div class="" id="schoolaward_modify_"><div class="con">'
        +'<div class="c" float-index="false"><label>时间</label><i>*</i><div class="h30"><div class="sh">'
                    +'<div class="txt pointer" id="bonus_time_calendar" float-on="false">'
                       +' <input class="ef" id="addbonus_time_input" type="date"  ></div></div></div>'
                  +'<div class="err" id="bonus_time_warning" style="display:none"><em class="icons"></em></div></div>'
        +'<div class="h30"><div class="c c1"><label>奖项</label><i>*</i><div class="sh">' +
		'<div class="txt">' +
		'<input class="ef" maxlength="50" id="addbonus_name" type="text" ></div> </div>' +
		'<div class="err" id="bonus_name_warning" style="display:none"><em class="icons"></em></div></div>'
            +'<div class="c c4"><label>级别</label><div class="sh">' +
		'<div class="txt"><input class="ef" maxlength="50" id="addbonus_class" type="text" value=""> </div></div>' +
		'<div class="err" id="bonus_class_warning" style="display:none"><em class="icons"></em></div></div></div></div>'
    +'<div class="btnbox"><span class="p_but" id="schoolaward_save_" onclick="saveXnry();">保存</span><span class="p_but gray" id="schoolaward_cancel_" onclick="schoolaward_cancel_Click(this)">取消</span></div></div>');
	
	
}
 function saveXnry(){
	 if(isNull($("#addbonus_time_input").val())){
		 alert("时间不能为空！！！");
		 return false;
	 }
	 if(isNull($("#addbonus_name").val())){
		 alert("奖项不能为空！！！");
		 return false;
	 }
	 var  json={};
	 if($("#jlBanbenId").val()!=undefined){
		 json.jlBbId=$("#jlBanbenId").val();
	 }
	 json.xnrySj=$("#addbonus_time_input").val();
	 json.xnryJx=$("#addbonus_name").val();
	 json.xnryJb=$("#addbonus_class").val();
	 $.ajax({
		 type: "POST",
		 async:false,
		 url: "saveXnry",
		 data:json,
		 dataType: "json",
		 success: function(data){
			 alert(data.message);
			 window.location.href="goEditResume?id="+data.xnry.jlBbId;
		 }
	 });
 }
 function xnryEdit(dataId){
	 var  json={};
	 if($("#jlBanbenId").val()!=undefined){
		 json.jlBbId=$("#jlBanbenId").val();
	 }
	 json.xnryId=dataId;
	 $.ajax({
		 type: "POST",
		 async: false,
		 url: "findJyjlByIdForXnry",
		 data: json,
		 dataType: "json",
		 success: function (data) {
			 $("#schoolaward_new").removeClass('add').addClass('unadd');
			 $("#schoolaward").addClass('bd');
			 $("#schoolaward").addClass('com');
			 $("#schoolaward").html('<div class="" id="schoolaward_modify_"><div class="con">'
				 + '<div class="c" float-index="false"><label>时间</label><i>*</i><div class="h30"><div class="sh">'
				 + '<div class="txt pointer" id="bonus_time_calendar" float-on="false">'
				 + ' <input class="ef" id="eaddbonus_time_input" type="date"  value="'+data.xnry.xnrySj+'"></div></div></div>'
				 + '<div class="err" id="bonus_time_warning" style="display:none"><em class="icons"></em></div></div>'
				 + '<div class="h30"><div class="c c1"><label>奖项</label><i>*</i><div class="sh">' +
				 '<div class="txt">' +
				 '<input class="ef" maxlength="50" id="eaddbonus_name" type="text" value="'+data.xnry.xnryJx+'"></div> </div>' +
				 '<div class="err" id="bonus_name_warning" style="display:none"><em class="icons"></em></div></div>'
				 + '<div class="c c4"><label>级别</label><div class="sh">' +
				 '<div class="txt"><input class="ef" maxlength="50" id="eaddbonus_class" type="text"> </div></div>' +
				 '<div class="err" id="bonus_class_warning" style="display:none"><em class="icons"></em></div></div></div></div>'
				 + '<div class="btnbox"><span class="p_but" id="schoolaward_save_" onclick="saveXnry1(\''+dataId+'\');">保存</span><span class="p_but gray" id="schoolaward_cancel_" onclick="schoolaward_cancel_Click(this)">取消</span></div></div>');
			 eaddbonus_class(data);
		 }
	 });
 }
 function eaddbonus_class(data){
	 if(!isNull(data.xnry.xnryJb)){
		 $("#eaddbonus_class").val(data.xnry.xnryJb)
	 }

 }
 function saveXnry1(dataId){
	 if(isNull($("#eaddbonus_time_input").val())){
		 alert("时间不能为空！！！");
		 return false;
	 }
	 if(isNull($("#eaddbonus_name").val())){
		 alert("奖项不能为空！！！");
		 return false;
	 }
	 var  json={};
	 json.xnryId=dataId;
	 if($("#jlBanbenId").val()!=undefined){
		 json.jlBbId=$("#jlBanbenId").val();
	 }
	 json.xnrySj=$("#eaddbonus_time_input").val();
	 json.xnryJx=$("#eaddbonus_name").val();
	 json.xnryJb=$("#eaddbonus_class").val();
	 $.ajax({
		 type: "POST",
		 async:false,
		 url: "saveXnry",
		 data:json,
		 dataType: "json",
		 success: function(data){
			 alert(data.message);
			 window.location.href="goEditResume?id="+$("#jlBanbenId").val();
		 }
	 });
 }
 //校内荣誉删除方法
 function xnryDel(dataID){
	 if(confirm("确定删除该条校内荣誉么？")){
		 $.ajax({
			 type: "POST",
			 async:false,
			 url: "deleteXnryById",
			 data:{ids:dataID},
			 dataType: "json",
			 success: function(data){
				 window.location.href="goEditResume?id="+$("#jlBanbenId").val();
			 }
		 });
	 }
 }

//校内荣誉取消
function schoolaward_cancel_Click(work) {
	$('#schoolaward_new').removeClass('unadd').addClass('add');
	$("#schoolaward").removeClass('bd');
	$("#schoolaward").removeClass('com');
	if($("#jlBanbenId").val()!=undefined){
		window.location.href="goEditResume?id="+$("#jlBanbenId").val();
	}else{
		window.location.href="goCreateResume";
	}
}
//校内职务增加
function schooljob_newClick(i) {
	$("#schooljob_new").removeClass('add').addClass('unadd');
	$("#schooljob").addClass('bd');
	$("#schooljob").addClass('com');
	$("#schooljob").html('<div class="" id="schooljob_modify_"><div class="con"><div class="c" float-index="false">'
           +' <label>时间</label><i>*</i>'
           +' <div class="h30">'
                +'<div class="sh sx"><div class="txt pointer" id="work_timefrom_calendar" float-on="false">' +
		'<input class="ef date_img" id="addtjwork_timefrom_input" type="date" ></div></div>' +
		'<span class="dao">到</span><div class="sh sx">' +
		'<div class="txt pointer" id="work_timeto_calendar" float-on="false">' +
		'<input class="ef date_img" id="addtjwork_timeto_input" type="date" > ' +
		'</div></div> </div></div>'
        +'<div class="c"><label>职务</label><i>*</i><div class="h30"><div class="sh">' +
		'<div class="txt"><input class="ef" maxlength="50" id="addtjprac_name" type="text" ></div></div>' +
		'<div class="err" id="prac_name_warning" style="display:none"><em class="icons"></em></div></div></div>'
        +'<div class="c"><label>职务描述</label><textarea id="addtjprac_describe" maxlength="500"  placeholder="描述在校期间所担任职位的主要工作内容及职责等"></textarea>' +
		'<div class="h30 clno"> ' +
		'<div class="err" id="prac_describe_warning" style="display:none"><em class="icons"></em></div></div></div></div>'
   +' <div class="btnbox"> <span class="p_but" id="schooljob_save_" onclick="saveXnzw();">保存</span> <span class="p_but gray" id="schooljob_cancel_" onclick="schooljob_cancel_Click(this)">取消</span></div></div>');
	
	
}
/*
添加职务
 */
 function saveXnzw(){
	 if(isNull($("#addtjwork_timefrom_input").val())){
		 alert("开始时间不能为空！！！");
		 return false;
	 }
	 if(isNull($("#addtjwork_timeto_input").val())){
		 alert("结束时间不能为空！！！");
		 return false;
	 }
	 if(isNull($("#addtjprac_name").val())){
		 alert("职务不能为空！！！");
		 return false;
	 }
	 if($("#addtjprac_describe").val().length>500){
		 alert("职务描述不能超过500！！！");
		 return false;
	 }
	 var  json={};
	 if($("#jlBanbenId").val()!=undefined){
		 json.jlBbId=$("#jlBanbenId").val();
	 }
	 json.xnzwKssj=$("#addtjwork_timefrom_input").val();
	 json.xnzwJssj=$("#addtjwork_timeto_input").val();
	 json.xnzwZw=$("#addtjprac_name").val();
	 json.xnzwZwms=$("#addtjprac_describe").val();
	 $.ajax({
		 type: "POST",
		 async:false,
		 url: "saveXnzw",
		 data:json,
		 dataType: "json",
		 success: function(data){
			 alert(data.message);
			 window.location.href="goEditResume?id="+data.xnzw.jlBbId;
		 }
	 });
 }
/*
xnzwEdit修改校内职务
 */
 function xnzwEdit(dataId){
	 var  json={};
	 if($("#jlBanbenId").val()!=undefined){
		 json.jlBbId=$("#jlBanbenId").val();
	 }
	 json.xnzwId=dataId;
	 $.ajax({
		 type: "POST",
		 async: false,
		 url: "findJyjlByIdForXnzw",
		 data: json,
		 dataType: "json",
		 success: function (data) {
			 $("#schooljob_new").removeClass('add').addClass('unadd');
			 $("#schooljob").addClass('bd');
			 $("#schooljob").addClass('com');
			 $("#schooljob").html('<div class="" id="schooljob_modify_"><div class="con"><div class="c" float-index="false">'
				 + ' <label>时间</label><i>*</i>'
				 + ' <div class="h30">'
				 + '<div class="sh sx"><div class="txt pointer" id="work_timefrom_calendar" float-on="false">' +
				 '<input class="ef date_img" id="eaddtjwork_timefrom_input" type="date" value="'+data.xnzw.xnzwKssj+'"></div></div>' +
				 '<span class="dao">到</span><div class="sh sx">' +
				 '<div class="txt pointer" id="work_timeto_calendar" float-on="false">' +
				 '<input class="ef date_img" id="eaddtjwork_timeto_input" type="date" value="'+data.xnzw.xnzwJssj+'"> ' +
				 '</div></div> </div></div>'
				 + '<div class="c"><label>职务</label><i>*</i><div class="h30"><div class="sh">' +
				 '<div class="txt"><input class="ef" maxlength="50" id="eaddtjprac_name" type="text" value="'+data.xnzw.xnzwZw+'"></div></div>' +
				 '<div class="err" id="prac_name_warning" style="display:none"><em class="icons"></em></div></div></div>'
				 + '<div class="c"><label>职务描述</label><textarea id="eaddtjprac_describe" maxlength="500"  placeholder="描述在校期间所担任职位的主要工作内容及职责等">'+data.xnzw.xnzwZwms+'</textarea>' +
				 '<div class="h30 clno">  ' +
				 '<div class="err" id="prac_describe_warning" style="display:none"><em class="icons"></em></div></div></div></div>'
				 + ' <div class="btnbox"> <span class="p_but" id="schooljob_save_" onclick="saveXnzw1(\''+dataId+'\');">保存</span> <span class="p_but gray" id="schooljob_cancel_" onclick="schooljob_cancel_Click(this)">取消</span></div></div>');
			 eaddprac_describe_chars(data);
		 }
	 });

 }
function eaddprac_describe_chars(data){
	 if(isNull(data)){
		 var s=data.xnzw.xnzwZwms;
		 $("#eaddprac_describe_chars").text(s.length+"/1000字");
	 }
}
 /*
修改职务
 */
 function saveXnzw1(dataId){
	 if(isNull($("#eaddtjwork_timefrom_input").val())){
		 alert("开始时间不能为空！！！");
		 return false;
	 }
	 if(isNull($("#eaddtjwork_timeto_input").val())){
		 alert("结束时间不能为空！！！");
		 return false;
	 }
	 if(isNull($("#eaddtjprac_name").val())){
		 alert("职务不能为空！！！");
		 return false;
	 }
	 if($("#eaddtjprac_describe").val().length>500){
		 alert("职务描述不能超过500！！！");
		 return false;
	 }
	 var  json={};
	 json.xnzwId=dataId;
	 if($("#jlBanbenId").val()!=undefined){
		 json.jlBbId=$("#jlBanbenId").val();
	 }
	 json.xnzwKssj=$("#eaddtjwork_timefrom_input").val();
	 json.xnzwJssj=$("#eaddtjwork_timeto_input").val();
	 json.xnzwZw=$("#eaddtjprac_name").val();
	 json.xnzwZwms=$("#eaddtjprac_describe").val();
	 $.ajax({
		 type: "POST",
		 async:false,
		 url: "saveXnzw",
		 data:json,
		 dataType: "json",
		 success: function(data){
			 alert(data.message);
			 window.location.href="goEditResume?id="+$("#jlBanbenId").val();
		 }
	 });
 }
 //校内职务删除方法
 function xnzwDel(dataID){
	 if(confirm("确定删除该条校内职务么？")){
		 $.ajax({
			 type: "POST",
			 async:false,
			 url: "deleteXnzwById",
			 data:{ids:dataID},
			 dataType: "json",
			 success: function(data){
				 window.location.href="goEditResume?id="+$("#jlBanbenId").val();
			 }
		 });
	 }
 }

 //校内职务取消
function schooljob_cancel_Click(work) {
	$('#schooljob_new').removeClass('unadd').addClass('add');
	$("#schooljob").removeClass('bd');
	$("#schooljob").removeClass('com');
	if($("#jlBanbenId").val()!=undefined){
		window.location.href="goEditResume?id="+$("#jlBanbenId").val();
	}else{
		window.location.href="goCreateResume";
	}
}
//技能特长增加
function skilllanguage_newButtonClick(work) {
	var  json={};
	if($("#jlBanbenId").val()!=undefined){
		json.jlBbId=$("#jlBanbenId").val();
	}
	$.ajax({
		type: "POST",
		async: false,
		url: "findQzyxByJlbbIdThree",
		data: json,
		dataType: "json",
		success: function (data) {
			$(work).removeClass('add').addClass('unadd');
			$("#Skills_div").addClass('bd');
			$("#Skills_div").addClass('com');
			$("#Skills_div").html('<textarea placeholder="请填写技能特长" maxlength="500" id="updattraindescribe" ></textarea>');
			$("#Skills_div").after('<div class="btnbox"><span class="p_but" id="schooljob_save_" onclick="saveJntc();">保存</span><span class="p_but gray" id="schooljob_cancel_" onclick="skilltrain_cancel_ButtonClick(this)">取消</span></div>'
			);
			if(!isNull(data.qzyx.qzyxJntc)){
				$("#updattraindescribe").val(data.qzyx.qzyxJntc);
			}
		}
	});
}
function updattraindescribe(data){
	 if(isNull(data)){
		 $("#updattraindescribe").val(data.qzyx.qzyxJntc);
	 }

}
 function saveJntc(){
	 if(isNull($("#updattraindescribe").val())){
		 alert("技能特长不能为空！！！");
		 return false;
	 }
	 if($("#updattraindescribe").val().length>500){
		 alert("技能特长不能超过500！！！");
		 return false;
	 }
	 var  json={};
	 if($("#qzyxId").val()!=undefined){
		 json.qzyxId=$("#qzyxId").val();
	 }
	 if($("#jlBanbenId").val()!=undefined){
		 json.jlBbId=$("#jlBanbenId").val();
	 }
	 json.qzyxJntc=$("#updattraindescribe").val();
	 $.ajax({
		 type: "POST",
		 async:false,
		 url: "saveUserQzyx",
		 data:json,
		 dataType: "json",
		 success: function(data){
			 if(!isNull(data.qzyx.jlBbId)){
				 window.location.href="goEditResume?id="+data.qzyx.jlBbId;
			 }else{
				 window.location.href="goCreateResume";
			 }
		 }
	 });
 }
//技能特长取消
function skilltrain_cancel_ButtonClick(work) {
	$('#skilllanguage_new').removeClass('unadd').addClass('add');
	$("#Skills_div").removeClass('bd');
	$("#Skills_div").removeClass('com');
	$("#Skills_div").siblings('.btnbox').remove();
	if($("#jlBanbenId").val()!=undefined){
		window.location.href="goEditResume?id="+$("#jlBanbenId").val();
	}else{
		window.location.href="goCreateResume";
	}
}

//居住地点击显示

//function base_area_click(int){
//	$('#layer_back_drop').show();
//		$('#layer_back_drop').addClass("layer_back_drop_class");
//		$("#base_area_layer").show();
//	
//	
//	jzdfz();
//}
////居住地点击显示2
//
function base_area_click2(int){
	$('#layer_back_drop').show();
	$('#base_area_layer').show();
	$('#layer_back_drop').addClass("layer_back_drop_class");
	
	//jzdfz();
}
function base_area_click(int){
	$('#layer_back_drop').show();
	$('#base_area_layer').show();
	$('#layer_back_drop').addClass("layer_back_drop_class");
	
	//jzdfz();
}

//户口国籍击显示
function base_country_click(int){
	$('#layer_back_drop').show();
		$('#layer_back_drop').addClass("layer_back_drop_class");
		$("#base_area_layer").show();
}
//职能击显示
function work_func_click(int){
	$('#layer_back_drop').show();
		$('#layer_back_drop').addClass("layer_back_drop_class");
		$("#work_func_layer").show();
}


//行业点击显示

function work_industry_click(int){
	$('#layer_back_drop').show();
	$('#layer_back_drop').addClass("layer_back_drop_class");
	$("#int_expectindustry_multiple_under_layer_id").show();
}
//点击取消

function close_i(int){
	$("#int_expectindustry_multiple_under_layer_id").hide();
		$("#layer_back_drop").hide();
		$("#int_expectfunc_layer").hide();
		$("#work_func_layer").hide();
}

//创建简历保存

function saveConfirmClick(save){
	$("#basedetail").addClass('top_wrap').removeClass('com');
	$(".face").removeClass('f2');
}

//性别点击
function base_sex_div(base){
	$(base).children('span').addClass('on').siblings('span').removeClass('on');
		
}


//居住地赋值
//function jzdfz(i){
//	$(".js_more em").on('click', function(){
//		var dz_1 = $(this).html();
//		$("#base_area_layer").hide();
//		$("#layer_back_drop").hide();
//		
//		$("#base_area_input").val(dz_1);
//	});
//		
//}


 /**************************************************************************
  身份号码排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
  地址码表示编码对象常住户口所在县(市、旗、区)的行政区划代码。
  出生日期码表示编码对象出生的年、月、日，其中年份用四位数字表示，年、月、日之间不用分隔符。
  顺序码表示同一地址码所标识的区域范围内，对同年、月、日出生的人员编定的顺序号。
  顺序码的奇数分给男性，偶数分给女性。
  校验码是根据前面十七位数字码，按照ISO 7064:1983.MOD 11-2校验码计算出来的检验码。
  15位校验规则 6位地址编码+6位出生日期+3位顺序号
  18位校验规则 6位地址编码+8位出生日期+3位顺序号+1位校验位
  校验位规则     公式:∑(ai×Wi)(mod 11)……………………………………(1)
  公式(1)中：
  i----表示号码字符从右至左包括校验码在内的位置序号；
  ai----表示第i位置上的号码字符值；
  Wi----示第i位置上的加权因子，其数值依据公式Wi=2^(n-1）(mod 11)计算得出。
  i 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
  Wi 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 1
  ****************************************************************************/

 /**
  * 身份证城市代码列表
  */
 var aIdentityCode_City = { // 城市代码列表
	 11 : "北京",12 : "天津",13 : "河北",14 : "山西",15 : "内蒙古",21 : "辽宁",22 : "吉林",
	 23 : "黑龙江 ",31 : "上海",32 : "江苏",33 : "浙江",34 : "安徽",35 : "福建",36 : "江西",
	 37 : "山东",41 : "河南",42 : "湖北 ",43 : "湖南",44 : "广东",45 : "广西",46 : "海南",
	 50 : "重庆",51 : "四川",52 : "贵州",53 : "云南",54 : "西藏 ",61 : "陕西",62 : "甘肃",
	 63 : "青海",64 : "宁夏",65 : "新疆",71 : "台湾",81 : "香港",82 : "澳门",91 : "国外 "
 };

 //检查号码是否符合规范，包括长度，类型
 function IdentityCode_isCardNo(card) {
	 //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
	 var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/; // 正则表达式
	 if (reg.test(card) === false) {
		 return false;
	 }
	 return true;
 };

 //取身份证前两位，校验省份
 function IdentityCode_checkProvince(card) {
	 var province = card.substr(0, 2);
	 if (aIdentityCode_City[province] == undefined) {
		 return false;
	 }
	 return true;
 };

 //检查生日是否正确，15位以'19'年份来进行补齐。
 function IdentityCode_checkBirthday(card) {
	 var len = card.length;
	 //身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字
	 if (len == '15') {
		 var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/;
		 var arr_data = card.match(re_fifteen); // 正则取号码内所含出年月日数据
		 var year = arr_data[2];
		 var month = arr_data[3];
		 var day = arr_data[4];
		 var birthday = new Date('19' + year + '/' + month + '/' + day);
		 return IdentityCode_verifyBirthday('19' + year, month, day, birthday);
	 }
	 //身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X
	 if (len == '18') {
		 var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
		 var arr_data = card.match(re_eighteen); // 正则取号码内所含出年月日数据
		 var year = arr_data[2];
		 var month = arr_data[3];
		 var day = arr_data[4];
		 var birthday = new Date(year + '/' + month + '/' + day);
		 return IdentityCode_verifyBirthday(year, month, day, birthday);
	 }
	 return false;
 };

 //校验日期 ，15位以'19'年份来进行补齐。
 function IdentityCode_verifyBirthday(year, month, day, birthday) {
	 var now = new Date();
	 var now_year = now.getFullYear();
	 //年月日是否合理
	 if (birthday.getFullYear() == year
		 && (birthday.getMonth() + 1) == month
		 && birthday.getDate() == day) {
		 //判断年份的范围（3岁到150岁之间)
		 var time = now_year - year;
		 if (time >= 3 && time <= 150) {
			 return true;
		 }
		 return false;
	 }
	 return false;
 };

 //校验位的检测
 function IdentityCode_checkParity(card) {
	 card = IdentityCode_changeFivteenToEighteen(card); // 15位转18位
	 var len = card.length;
	 if (len == '18') {
		 var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		 var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
		 var cardTemp = 0, i, valnum;
		 for (i = 0; i < 17; i++) {
			 cardTemp += card.substr(i, 1) * arrInt[i];
		 }
		 valnum = arrCh[cardTemp % 11];
		 if (valnum == card.substr(17, 1)) {
			 return true;
		 }
		 return false;
	 }
	 return false;
 };

 //15位转18位身份证号
 function IdentityCode_changeFivteenToEighteen(card) {
	 if (card.length == '15') {
		 var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		 var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
		 var cardTemp = 0, i;
		 card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);
		 for (i = 0; i < 17; i++) {
			 cardTemp += card.substr(i, 1) * arrInt[i];
		 }
		 card += arrCh[cardTemp % 11];
		 return card;
	 }
	 return card;
 };

 /**
  * 身份证号码检验主入口
  * 符合规则则返回性别：0|女生 1|男生
  * 不符合规则弹出提示错误
  */
 function IdentityCodeValid(card) {

	 var pass = "1";
	 //是否为空
	 if (pass && card === '')
		 pass = "2";
	 //校验长度，类型
	 if (pass && IdentityCode_isCardNo(card) === false)
		 pass = "3";
	 //检查省份
	 if (pass && IdentityCode_checkProvince(card) === false)
		 pass = "4";
	 //校验生日
	 if (pass && IdentityCode_checkBirthday(card) === false)
		 pass = "5";
	 //检验位的检测
	 if (pass && IdentityCode_checkParity(card) === false)
		 pass = "6";

	 //   var iCard = IdentityCode_changeFivteenToEighteen(card);
	 //if (parseInt(iCard.charAt(16)) % 2 == 0) {
	 //     sex = "0"; // 女生
	 // } else {
	 //      sex = "1"; // 男生
	 // }
	 //   return sex;
	 return pass;


 }

