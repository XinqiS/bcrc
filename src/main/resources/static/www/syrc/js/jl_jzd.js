$(function () {
	//简历中心-创建简历
	//左侧菜单浮动
	$(window).scroll(function () {
		var scroll_T = $(window).scrollTop();
		//var vh = $(window).scrollTop();
		if (scroll_T > 145) {
			$(".cjjl_nav").addClass("left_fix");
		} else {
			$(".cjjl_nav").removeClass("left_fix");
		}
		
		
    
	});
	
	/*$("#leftNav").children().click(function(){
		$(this).children(":last-child").attr("class","right y");
		$(this).siblings().children(":last-child").attr("class","right n");
	});*/
	
	//详细信息点击显示
	$("#xxxx_btn").on('click', function () {
		$.ajax({
            type: "POST",
            async:false,
            url: "getUserConsumeInfo",
            data: {jianliId:$("#jianliId").val()},
            dataType: "json",
            success: function(data){
            	var csrq;
            	if(data.jianliCsrq==null){
            		csrq="";
            	}else{
            		csrq=data.jianliCsrq;
            	}
            	$("#basedetail").html('<form id="grxx"><input name="jianliId" type="hidden" value="'+data.jianliId+'"><input name="zhId" type="hidden" value="'+data.zhId+'"><input name="jianliTx" id="jianliTx" type="hidden" value="'+data.jianliTx+'"><div class="head" id="Basic"><div class="face f2"><img id="base_avatar" src="'+data.jianliTx+'" width="85" height="104" alt="头像"><span class="esp" onclick="myfile.click();"><input type="file" id="myfile"  name="myfile" style="visibility:hidden" onchange="printFileInfo(this)" accept="image/*" multiple="multiple"/>修改</span>|<span id="base_avatar_delete" class="esp clb" style="cursor:default" "="" onclick="txDel(\''+data.jianliId+'\');">删除</span></div>'
            		    +' <div class="cbox"><div class="h30"><div class="c c2"><label>姓名</label><i>*</i><div class="sh"><div class="txt"><input id="base_name" name="jianliXm" class="ef" maxlength="20" type="text" value="'+data.jianliXm+'"></div></div><div class="err" id="base_name_warning" style="display:none"><em class="icons"></em></div></div>'
            		                +' <div class="c c4"><label>性别</label><i>*</i><div class="sh" id="base_sex_div" > <div class="txt pointer" id="base_csituation_list" float-on="false"><select class="ef" name="jianliXb" id="jianliXb"></select></div><div class="err" id="base_sex_warning" style="display:none"><em class="icons"></em></div></div></div></div>'
            		              +'<div class="h30"><div class="c c2" float-index="false"><label>出生日期</label><i>*</i><div class="sh"><div class="txt pointer" id="base_birthday_calendar" float-on="false"><input class="ef date_img" name="jianliCsrq" id="base_birthday_input" type="text" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})" value="'+csrq+'"></div><input id="base_birthday" type="hidden" value="1990/07/18"></div></div>'
            		                +'<div class="c c4" float-index="false"><label>开始工作年份</label><i>*</i><div class="sh"><div class="txt pointer" id="base_workyear_calendar" float-on="false"><input class="ef date_img" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})" name="jianliKsgz" maxlength="4"  type="text" value="'+data.jianliKsgz+'"> </div><input id="base_workyear" type="hidden" value="'+data.jianliKsgz+'"></div></div></div>'
            		              +'<div class="h30" style="*position:relative;*z-index:2"><div class="c c2"><label>手机</label><i>*</i><div class="sh"><div class="txt pointer" name="11" ><input class="ef " id="tele" name="jianliSj" type="text" maxlength="40"  value="'+data.jianliSj+'"></div></div></div>'
            		               +' <div class="c c4" float-index="false"><label>求职状态</label><i>*</i><div class="sh"><div class="txt pointer" id="base_csituation_list" float-on="false"><select class="ef" name="jianliQzzt" id="jianliQzzt"></select></div><input id="base_csituation" type="hidden" value="2"></div></div></div>'
            		              +'<div class="h30" style="*position:relative;*z-index:1"><div class="c c2"><label>邮箱</label><i>*</i> <div class="sh"> <div class="txt pointer" ><input class="ef " id="email" name="jianliYx" type="text" maxlength="20" value="'+data.jianliYx+'"></div></div></div>'
            		               +' <div class="c c4" float-index="false" id="base_area_index"><label>居住地</label><i>*</i><div class="sh"><div class="txt pointer" float-on="false" id="base_area_div"><input class="ef cursor" maxlength="35" type="text" name="jianliJzd" id="base_area_input" placeholder="填写" value="'+data.jianliJzd+'" pre_value="长春-南关区"><div class="ul u3" id="base_area_list" style="display:none"> </div></div><input class="ef" id="base_area" type="hidden" value="240202" pre_code="240202"></div></div></div></div>'
            		            +'<div class="abox"><div class="mbox" onclick="showMoreClickEvent(this)"> <span class="icons">更多展开</span> <em class="icons" style="display: block;"></em> </div><div class="all" ><div class="h30"><div class="c c1" float-index="false" id="base_country_index"><label>户口/国籍</label><div class="sh"><div class="txt pointer" float-on="false" id="base_country_div"><input class="ef cursor" maxlength="35" id="base_country_input" placeholder="填写" type="text" name="jianliHk" value="'+data.jianliHk+'" pre_value=""><div class="ul u3" id="base_country_list"> </div></div><input id="base_country" type="hidden" value="" pre_code=""></div></div>'
            		                  +'<div class="c c4" float-index="false"><label>婚姻状态</label><div class="sh"><div class="txt pointer" id="base_marriage_list" float-on="false"><select class="ef" name="jianliHyzt" id="jianliHyzt"></select></div><input class="ef" id="base_marriage" type="hidden" value=""></div></div></div>'
            		               +' <div class="h30"><div class="c c1" float-index="false"><label>证件号</label><div class="sh sm"><div class="txt pointer" id="base_idtype_list" float-on="false"><select class="ef select2" name="jianliZjlx" id="jianliZjlx"></select></div><input class="ef" id="base_idtype" type="hidden" value="4"></div>'
            		                   +' <div class="sh sl"> <div class="txt"><input id="base_idcard" name="jianliZjhm" class="ef" maxlength="25" type="text" value="'+data.jianliZjhm+'"></div> </div></div>'
            		                  +'<div class="c c4" float-index="false"> <label>政治面貌</label><div class="sh"><div class="txt pointer" id="base_politicsstatus_list" float-on="false"><select class="ef " name="jianliZzmm" id="jianliZzmm"></select></div><input class="ef" id="base_politicsstatus" type="hidden" value="4"></div></div></div>'
            		                +'<div class="h30"><div class="c c1" float-index="false"><label>其他联系方式</label><div class="sh sm"><div class="txt pointer" id="base_contacttype_list" float-on="false"><select class="ef select2" name="jianliQtfalx" id="jianliQtfalx"></select></div><input class="ef" id="base_contacttype" type="hidden" value="00"></div><div class="sh sl"><div class="txt"><input class="ef" maxlength="20" name="jianliQtfshm" id="base_othercontact" type="text" value="'+data.jianliQtfshm+'"></div></div></div>'
            		                  +'<div class="c c4"><label>身高</label> <div class="sh"><div class="txt"><input class="ef" maxlength="3" id="base_stature" type="text" name="jianliSg" value="'+data.jianliSg+'"></div></div> cm </div></div>'
            		                +'<div class="h30"><div class="c c1"><label>家庭住址</label><div class="sh"><div class="txt"><input class="ef" maxlength="100" id="base_address" type="text" name="jianliJtzz" value="'+data.jianliJtzz+'"></div></div></div>'
            		                  +'<div class="c c4"><label>邮编</label><div class="sh"><div class="txt"><input class="ef" maxlength="6" id="base_zipcode" type="text" name="jianliYb" value="'+nullToEmptyStr(data.jianliYb)+'"></div> </div></div></div></div></div></div><span class="ed_icon_blue icons" id="xxxx_btn" onclick="editButtonClick();"></span></form>'
            							);
            				//性别
            				$("#jianliXb").append(dicContainer["100"].toString());
            				if(data.jianliXb!=null){
								$("#jianliXb").find("option[value = '"+data.jianliXb+"']").attr("selected","selected"); 
							}
            				//求职状态
            				$("#jianliQzzt").append(dicContainer["qzzt"].toString());
            				if(data.jianliQzzt!=null){
								$("#jianliQzzt").find("option[value = '"+data.jianliQzzt+"']").attr("selected","selected"); 
							}
            				//证件类型
            				$("#jianliZjlx").append(dicContainer["zjlx"].toString());
            				if(data.jianliZjlx!=null){
								$("#jianliZjlx").find("option[value = '"+data.jianliZjlx+"']").attr("selected","selected"); 
							}
            				//联系方式
            				$("#jianliQtfalx").append(dicContainer["lxfs"].toString());
            				if(data.jianliQtfalx!=null){
								$("#jianliQtfalx").find("option[value = '"+data.jianliQtfalx+"']").attr("selected","selected"); 
							}
            				//婚姻状态
            				$("#jianliHyzt").append(dicContainer["hyzt"].toString());
            				if(data.jianliHyzt!=null){
								$("#jianliHyzt").find("option[value = '"+data.jianliHyzt+"']").attr("selected","selected"); 
							}
            				//政治面貌
            				$("#jianliZzmm").append(dicContainer["106"].toString());
            				if(data.jianliZzmm!=null){
								$("#jianliZzmm").find("option[value = '"+data.jianliZzmm+"']").attr("selected","selected"); 
							}
            				
            				
            				$("#basedetail").addClass('com').removeClass('top_wrap');
            				$(".face").addClass('f2');
            			
            				$(".head").append('<div class="btnbox"><span class="p_but" id="basedetail_save" onclick="grxxSave();">保存</span><span class="p_but gray" id="basedetail_cancel" onclick="cancelButtonClick(this)">取消</span></div>');
            				$(".name").remove();
            				$(".xjz").remove();
            				$(".tab").remove();
            				$(".abox").show();
            				$(".cbox").show();      
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
	
	
	
	
	

});



	//年收入增加
function salary_editButtonClick(o) {
	var qzyxId=$("#qzyxId").val();
	$.ajax({
        type: "POST",
        async:false,
        url: "findUserQzyx",
        data: {qzyxId:qzyxId},
        dataType: "json",
        success: function(data){
        	if(data!=null&&data.qzyxNsr!=null){
        		$("#salary_edit").siblings('.bh_div').html('<div><div class="bd com">  <div class="con">    <div class="h30 h2">        <div class="c c3">          <label>目前年收入</label>   <div class="sh sl"><div class="txt"> <input class="ef" maxlength="6" name="qzyxNsr" id="qzyxNsr" type="text" value="'+data.qzyxNsr+'">                     </div>    </div>   万元 </div></div></div> <div class="btnbox"> <span class="p_but" id="salary_save" onclick="savNsr();">保存</span><span class="p_but gray" id="salary_cancel" onclick="salaryButtonClick(this)">取消</span> </div> </div></div>');
        	}else{
        		$("#salary_edit").siblings('.bh_div').html('<div><div class="bd com">  <div class="con">    <div class="h30 h2">        <div class="c c3">          <label>目前年收入</label>   <div class="sh sl"><div class="txt"> <input class="ef" maxlength="6" name="qzyxNsr" id="qzyxNsr" type="text" value="">                     </div>    </div>   万元 </div></div></div> <div class="btnbox"> <span class="p_but" id="salary_save" onclick="savNsr();">保存</span><span class="p_but gray" id="salary_cancel" onclick="salaryButtonClick(this)">取消</span> </div> </div></div>');
        	}
			$("#salary_edit").siblings('.icons').remove();
			$("#salary").removeClass('m1');
			$("#salary").removeClass('top_wrap').addClass('b2');
			$(this).remove();
        }
     	});
}
function savNsr(){
	var qzyxId=$("#qzyxId").val();
	var qzyxNsr=$("#qzyxNsr").val();
	if(isNull(qzyxNsr)){
		alert("年收入不能为空");
		return false;
	}
	var jlBbId=$("#jlBanbenId").val();
	$.ajax({
        type: "POST",
        async:false,
        url: "saveUserQzyx",
        data: {qzyxId:qzyxId,qzyxNsr:qzyxNsr,jlBbId:jlBbId},
        dataType: "json",
        success: function(data){
        	alert(data.message);
        	$("#qzyxId").val(data.qzyx.qzyxId);
        	$("#jlBanbenId").val(data.qzyx.jlBbId);
        	$("#salary").addClass('m1');
        	$("#salary").removeClass('b2').addClass('top_wrap');
        	$("#salary").html('<div class="hd"> <strong class="icons"><em class="e0 icons"></em>目前年收入</strong> <span class="f16">'+data.qzyx.qzyxNsr+'万元</span> <span class="f12">（包含基本工资、补贴、奖金、股权收益等）</span> </div><div class="bh_div"></div><span class="ed_icon_blue icons" onclick="salary_editButtonClick(this)" id="salary_edit"></span>');
        }
	});
}
function salaryButtonClick(o) {
	var qzyxId=$("#qzyxId").val();
	$.ajax({
        type: "POST",
        async:false,
        url: "findUserQzyx",
        data: {qzyxId:qzyxId},
        dataType: "json",
        success: function(data){
        	$("#salary").addClass('m1');
        	$("#salary").removeClass('b2').addClass('top_wrap');
        	if(data!=null&&data.qzyxNsr!=null){
        		$("#salary").html('<div class="hd"> <strong class="icons"><em class="e0 icons"></em>目前年收入</strong> <span class="f16">'+data.qzyxNsr+'万元</span> <span class="f12">（包含基本工资、补贴、奖金、股权收益等）</span> </div><div class="bh_div"></div><span class="ed_icon_blue icons" onclick="salary_editButtonClick(this)" id="salary_edit"></span>');
        	}else{
        		$("#salary").html('<div class="hd"> <strong class="icons"><em class="e0 icons"></em>目前年收入</strong> <span class="f16"></span> <span class="f12">（包含基本工资、补贴、奖金、股权收益等）</span> </div><div class="bh_div"><div class="none icons">完善年收入情况，让HR更了解你！</div></div><span class="ed_icon_blue icons" onclick="salary_editButtonClick(this)" id="salary_edit"></span>');
        	}
        }
     	});
	
	

}
//技能特长增加
function skilllanguage_newButtonClick(work) {
	var qzyxId=$("#qzyxId").val();
	$.ajax({
        type: "POST",
        async:false,
        url: "findUserQzyx",
        data: {qzyxId:qzyxId},
        dataType: "json",
        success: function(data){
        	$(work).removeClass('add').addClass('unadd');
        	$("#Skills_div").addClass('bd');
        	$("#Skills_div").addClass('com');
        	if(data!=null&&data.qzyxJntc!=null){
        		$("#Skills_div").html('<textarea placeholder="请填写技能特长" maxlength="1000" name="qzyxJntc" id="qzyxJntc">'+data.qzyxJntc+'</textarea>');
        	}else{
        		$("#Skills_div").html('<textarea placeholder="请填写技能特长" maxlength="1000" name="qzyxJntc" id="qzyxJntc"></textarea>');
        	}
        	
        	$("#Skills_div").after('<div class="btnbox"><span class="p_but" id="schooljob_save_" onclick="saveJntc();">保存</span><span class="p_but gray" id="schooljob_cancel_" onclick="skilltrain_cancel_ButtonClick(this)">取消</span></div>');
        }
     	});
	
}
function saveJntc(){
	var qzyxId=$("#qzyxId").val();
	var qzyxJntc=$("#qzyxJntc").val();
	if(isNull(qzyxJntc)){
		alert("技能特长不能为空！！！");
		return false;
	}
	//设置技能特长蓝色
	$("#skill_complete").attr("class","right y");
	var jlBbId=$("#jlBanbenId").val();
	$.ajax({
        type: "POST",
        async:false,
        url: "saveUserQzyx",
        data: {qzyxId:qzyxId,qzyxJntc:qzyxJntc,jlBbId:jlBbId},
        dataType: "json",
        success: function(data){
        	alert(data.message);
        	$("#qzyxId").val(data.qzyx.qzyxId);
        	$("#jlBanbenId").val(data.qzyx.jlBbId);
        	//$("#salary").addClass('m1');
        	//$("#salary").removeClass('b2').addClass('top_wrap');
        	//$("#salary").html('<div class="hd"> <strong class="icons"><em class="e0 icons"></em>目前年收入</strong> <span class="f16">'+data.qzyx.qzyxNsr+'万元</span> <span class="f12">（包含基本工资、补贴、奖金、股权收益等）</span> </div><div class="bh_div"><div class="none icons">完善年收入情况，让HR更了解你！</div></div><span class="ed_icon_blue icons" onclick="salary_editButtonClick(this)" id="salary_edit"></span>');
        }
	});
}
//技能特长取消

function skilltrain_cancel_ButtonClick(work) {
	var qzyxId=$("#qzyxId").val();
	$.ajax({
        type: "POST",
        async:false,
        url: "findUserQzyx",
        data: {qzyxId:qzyxId},
        dataType: "json",
        success: function(data){
        	$('#skilllanguage_new').removeClass('unadd').addClass('add');
        	$("#Skills_div").removeClass('bd');
        	$("#Skills_div").removeClass('com');
        	$("#Skills_div").siblings('.btnbox').remove();
        	
        	if(data!=null&&data.qzyxJntc!=null){
        		$("#skill_complete").attr("class","right y");
        		$("#Skills_div").html(' <div class="tit"> <span>'+data.qzyxJntc+'</span></div>');
        	}else{
        		$("#Skills_div").html('<div class="none icons">完善技能特长能力，让HR更了解你！</div>');
        	}
        	
        }
     	});
	
}

function printFileInfo(target){
	var myarr=[];
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
    var picFile = document.getElementById('myfile');
    var files = picFile.files;
    for(var i=0; i<files.length; i++){
        var file = files[i];
        myarr.push(file)
    }
    var formData = new FormData();
    $.each(myarr,function(i,file){
        formData.append('myfile',file);

    });
    $.ajax({
        url : "updateJianliTx",
        type : 'POST',
        dataType: 'json',
        data : formData,
        crossDomain: true,
        cache: false,
        processData: false,
        contentType: false,
        success : function(responseStr) {
        	$("#jianliTx").val(responseStr.fileUrl);
        	$("#base_avatar").attr("src",responseStr.fileUrl);
        },
    });
}
function txDel(jianliId){
	$.ajax({
        type: "POST",
        async:false,
        url: "deleteTx",
        data: {jianliId:jianliId},
        dataType: "json",
        success: function(data){
        	$("#jianliTx").val(data.jianliTx);
        	$("#base_avatar").attr("src",data.jianliTx);
        }
    });
}
//详细信息点击显示 
function editButtonClick() {
	$.ajax({
        type: "POST",
        async:false,
        url: "getUserConsumeInfo",
        data: {jianliId:$("#jianliId").val()},
        dataType: "json",
        success: function(data){
        	var csrq;
        	if(data.jianliCsrq==null){
        		csrq="";
        	}else{
        		csrq=data.jianliCsrq;
        	}
        	$("#basedetail").html('<form id="grxx"><input name="jianliId" type="hidden" value="'+data.jianliId+'"><input name="zhId" type="hidden" value="'+data.zhId+'"><input name="jianliTx" id="jianliTx" type="hidden" value="'+data.jianliTx+'"><div class="head" id="Basic"><div class="face f2"><img id="base_avatar" src="'+data.jianliTx+'" width="85" height="104" alt="头像"><span class="esp" onclick="myfile.click();"><input type="file" id="myfile"  name="myfile" style="visibility:hidden" onchange="printFileInfo(this)" accept="image/*" multiple="multiple"/>修改</span>|<span id="base_avatar_delete" class="esp clb" style="cursor:default" "="" onclick="txDel(\''+data.jianliId+'\');">删除</span></div>'
        		    +' <div class="cbox"><div class="h30"><div class="c c2"><label>姓名</label><i>*</i><div class="sh"><div class="txt"><input id="base_name" name="jianliXm" class="ef" maxlength="20" type="text" value="'+data.jianliXm+'"></div></div><div class="err" id="base_name_warning" style="display:none"><em class="icons"></em></div></div>'
        		                +' <div class="c c4"><label>性别</label><i>*</i><div class="sh" id="base_sex_div" > <div class="txt pointer" id="base_csituation_list" float-on="false"><select class="ef" name="jianliXb" id="jianliXb"></select></div><div class="err" id="base_sex_warning" style="display:none"><em class="icons"></em></div></div></div></div>'
        		              +'<div class="h30"><div class="c c2" float-index="false"><label>出生日期</label><i>*</i><div class="sh"><div class="txt pointer" id="base_birthday_calendar" float-on="false"><input class="ef date_img" name="jianliCsrq" id="base_birthday_input" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})" type="text" value="'+csrq+'"></div><input id="base_birthday" type="hidden" value="1990/07/18"></div></div>'
        		                +'<div class="c c4" float-index="false"><label>开始工作年份</label><i>*</i><div class="sh"><div class="txt pointer" id="base_workyear_calendar" float-on="false"><input class="ef date_img" name="jianliKsgz" maxlength="4"  onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})" type="text" value="'+data.jianliKsgz+'"> </div><input id="base_workyear" type="hidden" value="'+data.jianliKsgz+'"></div></div></div>'
        		              +'<div class="h30" style="*position:relative;*z-index:2"><div class="c c2"><label>手机</label><i>*</i><div class="sh"><div class="txt pointer" name="11" ><input class="ef " id="tele" name="jianliSj" type="text"  value="'+data.jianliSj+'"></div></div></div>'
        		               +' <div class="c c4" float-index="false"><label>求职状态</label><i>*</i><div class="sh"><div class="txt pointer" id="base_csituation_list" float-on="false"><select class="ef" name="jianliQzzt" id="jianliQzzt"></select></div><input id="base_csituation" type="hidden" value="2"></div></div></div>'
        		              +'<div class="h30" style="*position:relative;*z-index:1"><div class="c c2"><label>邮箱</label><i>*</i> <div class="sh"> <div class="txt pointer" ><input class="ef " id="email" name="jianliYx" type="text" value="'+data.jianliYx+'"></div></div></div>'
        		               +' <div class="c c4" float-index="false" id="base_area_index"><label>居住地</label><i>*</i><div class="sh"><div class="txt pointer" float-on="false" id="base_area_div"><input class="ef cursor" maxlength="35" type="text" name="jianliJzd" id="base_area_input" placeholder="填写" value="'+data.jianliJzd+'" pre_value="长春-南关区"><div class="ul u3" id="base_area_list" style="display:none"> </div></div><input class="ef" id="base_area" type="hidden" value="240202" pre_code="240202"></div></div></div></div>'
        		            +'<div class="abox"><div class="mbox" onclick="showMoreClickEvent(this)"> <span class="icons">更多展开</span> <em class="icons" style="display: block;"></em> </div><div class="all" ><div class="h30"><div class="c c1" float-index="false" id="base_country_index"><label>户口/国籍</label><div class="sh"><div class="txt pointer" float-on="false" id="base_country_div"><input class="ef cursor" maxlength="35" id="base_country_input" placeholder="填写" type="text" name="jianliHk" value="'+data.jianliHk+'" pre_value=""><div class="ul u3" id="base_country_list"> </div></div><input id="base_country" type="hidden" value="" pre_code=""></div></div>'
        		                  +'<div class="c c4" float-index="false"><label>婚姻状态</label><div class="sh"><div class="txt pointer" id="base_marriage_list" float-on="false"><select class="ef" name="jianliHyzt" id="jianliHyzt"></select></div><input class="ef" id="base_marriage" type="hidden" value=""></div></div></div>'
        		               +' <div class="h30"><div class="c c1" float-index="false"><label>证件号</label><div class="sh sm"><div class="txt pointer" id="base_idtype_list" float-on="false"><select class="ef select2" name="jianliZjlx" id="jianliZjlx"></select></div><input class="ef" id="base_idtype" type="hidden" value="4"></div>'
        		                   +' <div class="sh sl"> <div class="txt"><input id="base_idcard" name="jianliZjhm" class="ef" maxlength="25" type="text" value="'+data.jianliZjhm+'"></div> </div></div>'
        		                  +'<div class="c c4" float-index="false"> <label>政治面貌</label><div class="sh"><div class="txt pointer" id="base_politicsstatus_list" float-on="false"><select class="ef " name="jianliZzmm" id="jianliZzmm"></select></div><input class="ef" id="base_politicsstatus" type="hidden" value="4"></div></div></div>'
        		                +'<div class="h30"><div class="c c1" float-index="false"><label>其他联系方式</label><div class="sh sm"><div class="txt pointer" id="base_contacttype_list" float-on="false"><select class="ef select2" name="jianliQtfalx" id="jianliQtfalx"></select></div><input class="ef" id="base_contacttype" type="hidden" value="00"></div><div class="sh sl"><div class="txt"><input class="ef" maxlength="20" name="jianliQtfshm" id="base_othercontact" type="text" value="'+data.jianliQtfshm+'"></div></div></div>'
        		                  +'<div class="c c4"><label>身高</label> <div class="sh"><div class="txt"><input class="ef" maxlength="3" id="base_stature" type="text" name="jianliSg" value="'+data.jianliSg+'"></div></div> cm </div></div>'
        		                +'<div class="h30"><div class="c c1"><label>家庭住址</label><div class="sh"><div class="txt"><input class="ef" maxlength="100" id="base_address" type="text" name="jianliJtzz" value="'+data.jianliJtzz+'"></div></div></div>'
        		                  +'<div class="c c4"><label>邮编</label><div class="sh"><div class="txt"><input class="ef" maxlength="6" id="base_zipcode" type="text" name="jianliYb" value="'+data.jianliYb+'"></div> </div></div></div></div></div></div><span class="ed_icon_blue icons" id="xxxx_btn" onclick="editButtonClick();"></span></form>'
        							);
        				//性别
        				$("#jianliXb").append(dicContainer["100"].toString());
        				if(data.jianliXb!=null){
							$("#jianliXb").find("option[value = '"+data.jianliXb+"']").attr("selected","selected"); 
						}
        				//求职状态
        				$("#jianliQzzt").append(dicContainer["qzzt"].toString());
        				if(data.jianliQzzt!=null){
							$("#jianliQzzt").find("option[value = '"+data.jianliQzzt+"']").attr("selected","selected"); 
						}
        				//证件类型
        				$("#jianliZjlx").append(dicContainer["zjlx"].toString());
        				if(data.jianliZjlx!=null){
							$("#jianliZjlx").find("option[value = '"+data.jianliZjlx+"']").attr("selected","selected"); 
						}
        				//联系方式
        				$("#jianliQtfalx").append(dicContainer["lxfs"].toString());
        				if(data.jianliQtfalx!=null){
							$("#jianliQtfalx").find("option[value = '"+data.jianliQtfalx+"']").attr("selected","selected"); 
						}
        				//婚姻状态
        				$("#jianliHyzt").append(dicContainer["hyzt"].toString());
        				if(data.jianliHyzt!=null){
							$("#jianliHyzt").find("option[value = '"+data.jianliHyzt+"']").attr("selected","selected"); 
						}
        				//政治面貌
        				$("#jianliZzmm").append(dicContainer["106"].toString());
        				if(data.jianliZzmm!=null){
							$("#jianliZzmm").find("option[value = '"+data.jianliZzmm+"']").attr("selected","selected"); 
						}
        				
        				
        				$("#basedetail").addClass('com').removeClass('top_wrap');
        				$(".face").addClass('f2');
        			
        				$(".head").append('<div class="btnbox"><span class="p_but" id="basedetail_save" onclick="grxxSave();">保存</span><span class="p_but gray" id="basedetail_cancel" onclick="cancelButtonClick(this)">取消</span></div>');
        				$(".name").remove();
        				$(".xjz").remove();
        				$(".tab").remove();
        				$(".abox").show();
        				$(".cbox").show();      
        }
    });
	
}
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
	var jianliId = $("input[name=jianliId]").val();
	$("#basedetail_cancel").parents('.box').addClass('top_wrap').removeClass('com');
	$(".face").removeClass('f2');
	//$(".face").addClass('top_wrap');
	$(".btnbox").remove();
	$(".cbox").hide();
	$(".abox").hide();
	$.ajax({
        type: "POST",
        async:false,
        url: "getUserConsumeInfo",
        data: {jianliId:jianliId},
        dataType: "json",
        success: function(data){
        	var myDate = new Date();
    	    var tYear = myDate.getFullYear();
    	    var gzjyNs="";
    	    var gzjyNl="";
    	    var xb="";
    	    var qzzt="";
    	    var hyzt="";
    	    var zzmm="";
    	    var csrq="";
    	    if(data.jianliKsgz!=null&&data.jianliKsgz!=""){
    	    	gzjyNs=tYear-parseInt(data.jianliKsgz.substr(0, 4));
    	    	
    	    }
    	    if(data.jianliCsrq!=null&&data.jianliCsrq!=""){
    	    	gzjyNl=tYear-parseInt(data.jianliCsrq.substr(0, 4));
    	    	csrq=data.jianliCsrq;
    	    }
    	    if(data.jianliXb!=null){
    	    	xb=dicContainer.getDicItemName('100', data.jianliXb);
    	    }
    	    if(data.jianliQzzt!=null){
    	    	qzzt=dicContainer.getDicItemName('qzzt', data.jianliQzzt);
    	    }
    	    if(data.jianliHyzt!=null){
    	    	hyzt=dicContainer.getDicItemName('hyzt', data.jianliHyzt);
    	    }
    	    if(data.jianliZzmm!=null){
    	    	zzmm=dicContainer.getDicItemName('106', data.jianliZzmm);
    	    }
      		$(".face").html('<img src="'+data.jianliTx+'" width="85" height="104" alt="头像">');
      		$(".face").after('<div class="name ">'+data.jianliXm+'</div><p class="at">现居住'+data.jianliJzd+'│ '+gzjyNs+'年工作经验 │ '+xb+' │ ('+gzjyNl+'岁)'+csrq+' │ '+qzzt+'</p><div class="tab"> <span class="email icons at" title="'+data.jianliYx+'">'+data.jianliYx+'</span> <span class="tel icons">'+data.jianliSj+'</span> </div><div class="abox">'
  	              +'<div class="mbox" onclick="showMoreClickEvent(this)"> <span class="icons">更多展开</span> <em class="icons"></em> </div>'
	              +'<div class="all"><div class="e e2 ef"><label>户口/国籍</label><i>：</i>'
	               +'<div>'+data.jianliHk+'</div></div> <div class="e e2"><label>婚姻状态</label><i>：</i><div>'+hyzt+'</div>'
	                +'</div><div class="e e2"><label>政治面貌</label><i>：</i><div>'+zzmm+'</div>'
	                +'</div> <div class="clear"></div></div></div>');
        }
	});
        
}

//基本信息保存

function grxxSave() {
	var jianliId = $("input[name=jianliId]").val();
	if(isNull($("input[name='jianliXm']").val())){
		alert("姓名不能为空！！！");
		return false;
	}
	if(isNull($("input[name='jianliCsrq']").val())){
		alert("出生日期不能为空！！！");
		return false;
	}
	if(isNull($("input[name='jianliKsgz']").val())){
		alert("开始工作不能为空！！！");
		return false;
	}else{
		var myDate = new Date();
	    var tYear = myDate.getFullYear();
	    if(parseInt($("input[name='jianliKsgz']").val())>parseInt(tYear)){
	    	alert("开始工作年份不能大于当前年份！！！");
	    	return false;
	    }

	}
	if(isNull($("input[name='jianliSj']").val())){
		alert("手机不能为空！！！");
		return false;
	}else if(!checkMobile($("input[name='jianliSj']").val())){
    	alert('联系电话格式不正确！');
        return false;
    }
	if(isNull($("input[name='jianliJzd']").val())){
		alert("居住地不能为空！！！");
		return false;
	}
	/*if(isNull($("input[name='jianliHk']").val())){
		alert("户口不能为空！！！");
		return false;
	}
	if(isNull($("input[name='jianliZjhm']").val())){
		alert("证件号码不能为空！！！");
		return false;
	}
	if(isNull($("input[name='jianliQtfshm']").val())){
		alert("联系方式不能为空！！！");
		return false;
	}
	if(isNull($("input[name='jianliSg']").val())){
		alert("身高不能为空！！！");
		return false;
	}
	if(isNull($("input[name='jianliJtzz']").val())){
		alert("家庭住址不能为空！！！");
		return false;
	}
	if(isNull($("input[name='jianliYb']").val())){
		alert("邮编不能为空！！！");
		return false;
	}*/
	$.ajax({
	      type: "POST",
	      async:false,
	      url: "saveUserConsumeInfo",
	      data: $("#grxx").serialize(),
	      dataType: "json",
	      success: function(data){
	    	    alert(data.message);
	    	    $("#basedetail_cancel").parents('.box').addClass('top_wrap').removeClass('com');
	    		$(".face").removeClass('f2');
	    		//$(".face").addClass('top_wrap');
	    		$(".btnbox").remove();
	    		$(".cbox").hide();
	    		$(".abox").hide();
	    		
	    		$.ajax({
	    	      type: "POST",
	    	      async:false,
	    	      url: "getUserConsumeInfo",
	    	      data: {jianliId:jianliId},
	    	      dataType: "json",
	    	      success: function(data){
	    	    	  	var myDate = new Date();
	    	    	    var tYear = myDate.getFullYear();
	    	    	    var gzjyNs="";
	    	    	    var gzjyNl="";
	    	    	    var xb="";
	    	    	    var qzzt="";
	    	    	    var hyzt="";
	    	    	    var zzmm="";
	    	    	    var csrq="";
	    	    	    if(data.jianliKsgz!=null){
	    	    	    	gzjyNs=tYear-parseInt(data.jianliKsgz.substr(0, 4));
	    	    	    }
	    	    	    if(data.jianliCsrq!=null){
	    	    	    	gzjyNl=tYear-parseInt(data.jianliCsrq.substr(0, 4));
	    	    	  	    csrq=data.jianliCsrq;
	    	    	    }
	    	    	    if(data.jianliQzzt!=null){
	    	    	    	qzzt=dicContainer.getDicItemName('qzzt', data.jianliQzzt);
	    	    	    }
	    	    	    if(data.jianliXb!=null){
	    	    	    	xb=dicContainer.getDicItemName('100', data.jianliXb);
	    	    	    }
	    	    	    if(data.jianliHyzt!=null){
	    	    	    	hyzt=dicContainer.getDicItemName('hyzt', data.jianliHyzt);
	    	    	    }
	    	    	    if(data.jianliZzmm!=null){
	    	    	    	zzmm=dicContainer.getDicItemName('106', data.jianliZzmm);
	    	    	    }
	    	      		$(".face").html('<img src="'+data.jianliTx+'" width="85" height="104" alt="头像">');
	    	      		$(".face").after('<div class="name ">'+data.jianliXm+'</div><p class="at">现居住'+data.jianliJzd+'│ '+gzjyNs+'年工作经验 │ '+xb+' │ ('+gzjyNl+'岁)'+csrq+' │ '+qzzt+'</p><div class="tab"> <span class="email icons at" title="'+data.jianliYx+'">'+data.jianliYx+'</span> <span class="tel icons">'+data.jianliSj+'</span> </div><div class="abox">'
	          	              +'<div class="mbox" onclick="showMoreClickEvent(this)"> <span class="icons">更多展开</span> <em class="icons"></em> </div>'
	        	              +'<div class="all"><div class="e e2 ef"><label>户口/国籍</label><i>：</i>'
	        	               +'<div>'+data.jianliHk+'</div></div> <div class="e e2"><label>婚姻状态</label><i>：</i><div>'+hyzt+'</div>'
	        	                +'</div><div class="e e2"><label>政治面貌</label><i>：</i><div>'+zzmm+'</div>'
	        	                +'</div> <div class="clear"></div></div></div>');
	    	      }
	    		});	
	      }
		});
	
      
}




//求职意向增加
function intention_editButtonClick() {
	var qzyxId=$("#qzyxId").val();
	$.ajax({
        type: "POST",
        async:false,
        url: "findUserQzyx",
        data: {qzyxId:qzyxId},
        dataType: "json",
        success: function(data){
        	//alert(data.qzyxId);
        	if(data!=null&&data.qzyxQwxz!=null){
        		$("#intention_edit").siblings('.bh_div2').html('<form id="qzyxForm"><input name="qzyxId" type="hidden" value="'+data.qzyxId+'"><input name="jlBbId" type="hidden" value="'+data.jlBbId+'"><div> <div class="bd com">  <div class="con"> ' 
        			+ ' <div class="c" float-index="false">  <label>期望薪资</label><i>*</i><div class="h30">    <div class="sh "><div class="txt pointer" float-on="false" id="int_expectarea_div"><input class="ef" maxlength="20" name="qzyxQwxz" type="text" value="'+data.qzyxQwxz+'" placeholder="请填写期望薪资" input-type="selectionlist"></div></div>  </div></div>'
        			+'<div class="c" float-index="false" id="int_expectarea_index">                <label>地点</label><i>*</i><div class="h30">    <div class="sh "><div class="txt pointer" id="int_expectarea_under"> <input class="ef" maxlength="20" type="text" name="qzyxDd" value="'+data.qzyxDd+'" placeholder="请填写地点"></div> </div></div></div>'       
        			+'<div class="c" float-index="false" id="int_expectarea_index">                <label>职能</label><i>*</i><div class="h30">    <div class="sh"><div class="txt pointer" float-on="false" id="work_func_div"><input id="work_zn_input" name="qzyxZn" value="'+data.qzyxZn+'" maxlength="80" placeholder="选择" readOnly class="ef cursor" type="text" value="" pre_value=""> <span class="ic i_block pointer" id="work_func_click" onclick="work_func_click(this)"></span><div class="ul u3" id="work_func_list"> </div></div><input id="work_func" type="hidden" value="" pre_code=""> </div></div></div>'          
        			+'<div class="c" float-index="false" id="int_position_index"><label>职位<i>*</i></label>                <div class="h30"><div class="sh"><div class="txt" float-on="false" id="int_position_div"> <input class="ef" id="int_position" maxlength="50" name="qzyxZw" type="text" readOnly value="'+data.qzyxZw+'">  <div class="ul u3" id="int_position_list"> </div></div> </div><div class="err" id="int_position_warning" style="display:none"><em class="icons"></em></div></div></div>'
        			+'<div class="c" float-index="false" id="int_expectindustry_index"><label>行业</label><div class="tbox" id="int_expectindustry_under"><div class="clear"></div></div><div class="h30"><div class="sh"><div class="txt pointer" float-on="false" id="int_expectindustry_div"><input placeholder="选择" readOnly class="ef cursor" id="int_hy_input" name="qzyxHy" maxlength="80" value="'+data.qzyxHy+'" type="text" maxlength="24"> <span class="ic i_block pointer" onclick="work_industry_click(this)" id="int_expectindustry_click"></span><div class="ul u3" id="int_expectindustry_list"></div></div><input id="int_expectindustry" type="hidden" value=""></div><div class="err" id="int_expectindustry_warning" style="display:none"><em class="icons"></em></div></div></div>'
        			+'<div class="c" float-index="false" id="int_resumekey_index"> <label>个人标签</label><div class="tbox" id="int_resumekey_under"><div class="clear"></div></div><div class="h30">  <div class="sh"><div class="txt pointer" float-on="false" id="int_resumekey_div"><input class="ef cursor" maxlength="24" id="int_resumekey_input" onkeyup="showbutton(event)" type="text" name="qzyxGrbq" value="'+data.qzyxGrbq+'" placeholder="职业/技能/行业相关的关键词"  value=""><span class="ic pointer" id="int_resumekey_add" style="display:none">添加</span><div class="ul u3" id="int_resumekey_list"> </div></div><input id="int_resumekey" type="hidden" value=""> </div> <div class="err" id="int_resumekey_warning" style="display:none"><em class="icons"></em></div> <span class="f12">限10个，每个词不超过12个中文或24个英文</span> </div>            </div>'
        			+'<div class="c"><label>自我评价</label><textarea placeholder="介绍自己，说明自己的最大优势，让企业看到你的闪光点" maxlength="1000" id="int_selfintro" name="qzyxZwpj">'+data.qzyxZwpj+'</textarea> </div><div class="h30">                    <span class="frt" id="int_selfintro_chars">'+data.qzyxZwpj.length+'/1000 字</span> </div>'
        			+'<div class="h30"><div class="c c3" float-index="false" style=""><label>到岗时间</label><div class="sh"><div class="txt pointer" id="int_entrytime_list" float-on="false"> <select class="ef select3" name="qzyxDgsj" id="qzyxDgsj"></select><span class="ic i_arrow"></span></div></div> </div><div class="c c3" float-index="false"><label>工作类型</label><div class="sh"><div class="txt pointer" id="int_seektype_list" float-on="false"> <select class="ef select3" name="qzyxGzlx" id="qzyxGzlx"></select><span class="ic i_arrow"></span></div><input id="int_seektype" type="hidden" value="0"></div></div></div> '
        			+'<div class="btnbox"> <span class="p_but" id="intention_save" onclick="a();">保存</span> <span class="p_but gray" id="intention_cancel" onclick="intentionButtonClick(this)">取消</span></div></div></div></form>');
        			$("#int_selfintro").bind("input propertychange",function(event){
        				  var $this = $(this).val(); 
        				  if ($this.length > 1000) {  
        					  $(this).val($this.substring(0, 1000));  
        		          }  
        		          $("#int_selfintro_chars").text($(this).val().length+"/1000 字"); 
        			});
	        		//性别
					$("#qzyxDgsj").append(dicContainer["dgsj"].toString());
					if(data.qzyxDgsj!=null){
						$("#qzyxDgsj").find("option[value = '"+data.qzyxDgsj+"']").attr("selected","selected"); 
					}
					//求职状态
					$("#qzyxGzlx").append(dicContainer["gzlx"].toString());
					if(data.qzyxGzlx!=null){
						$("#qzyxGzlx").find("option[value = '"+data.qzyxGzlx+"']").attr("selected","selected"); 
					}
        			
        			$("#intention_edit").siblings('.icons').remove();
        			$("#intention").removeClass('m1');
        			$("#intention").removeClass('top_wrap').addClass('b2');
        			$(this).remove();
        	}else{
        		$("#intention_edit").siblings('.bh_div2').html('<form id="qzyxForm"><input name="qzyxId" type="hidden" value="'+$("#qzyxId").val()+'"><input name="jlBbId" type="hidden" value="'+$("#jlBanbenId").val()+'"><div> <div class="bd com">  <div class="con"> ' 
            			+ ' <div class="c" float-index="false">  <label>期望薪资</label><i>*</i><div class="h30">    <div class="sh "><div class="txt pointer" float-on="false" id="int_expectarea_div"><input class="ef" name="qzyxQwxz" maxlength="20" type="text" value="" placeholder="请填写期望薪资" input-type="selectionlist"></div></div>  </div></div>'
            			+'<div class="c" float-index="false" id="int_expectarea_index">                <label>地点</label><i>*</i><div class="h30">    <div class="sh "><div class="txt pointer" id="int_expectarea_under"> <input class="ef" type="text" name="qzyxDd" maxlength="20" value="" placeholder="请填写地点"></div> </div></div></div>'       
            			+'<div class="c" float-index="false" id="int_expectarea_index">                <label>职能</label><i>*</i><div class="h30">    <div class="sh"><div class="txt pointer" float-on="false" id="work_func_div"><input id="work_zn_input" name="qzyxZn" maxlength="80" value="" placeholder="选择" class="ef cursor" readOnly type="text" value="" pre_value=""> <span class="ic i_block pointer" id="work_func_click" onclick="work_func_click(this)"></span><div class="ul u3" id="work_func_list"> </div></div><input id="work_func" type="hidden" value="" pre_code=""> </div></div></div>'          
            			+'<div class="c" float-index="false" id="int_position_index"><label>职位<i>*</i></label>                <div class="h30"><div class="sh"><div class="txt" float-on="false" id="int_position_div"> <input class="ef" id="int_position" maxlength="50" name="qzyxZw" type="text" value="">  <div class="ul u3" id="int_position_list"> </div></div> </div><div class="err" id="int_position_warning" style="display:none"><em class="icons"></em></div></div></div>'
            			+'<div class="c" float-index="false" id="int_expectindustry_index"><label>行业</label><div class="tbox" id="int_expectindustry_under"><div class="clear"></div></div><div class="h30"><div class="sh"><div class="txt pointer" float-on="false" id="int_expectindustry_div"><input placeholder="选择" maxlength="80" readOnly class="ef cursor" id="int_hy_input" name="qzyxHy" value="" type="text" maxlength="24"> <span class="ic i_block pointer" onclick="work_industry_click(this)" id="int_expectindustry_click"></span><div class="ul u3" id="int_expectindustry_list"></div></div><input id="int_expectindustry" type="hidden" value=""></div><div class="err" id="int_expectindustry_warning" style="display:none"><em class="icons"></em></div></div></div>'
            			+'<div class="c" float-index="false" id="int_resumekey_index"> <label>个人标签</label><div class="tbox" id="int_resumekey_under"><div class="clear"></div></div><div class="h30">  <div class="sh"><div class="txt pointer" float-on="false" id="int_resumekey_div"><input class="ef cursor" maxlength="24" id="int_resumekey_input" onkeyup="showbutton(event)" type="text" name="qzyxGrbq" value="" placeholder="职业/技能/行业相关的关键词" value=""><span class="ic pointer" id="int_resumekey_add" style="display:none">添加</span><div class="ul u3" id="int_resumekey_list"> </div></div><input id="int_resumekey" type="hidden" value=""> </div> <div class="err" id="int_resumekey_warning" style="display:none"><em class="icons"></em></div> <span class="f12">限10个，每个词不超过12个中文或24个英文</span> </div>            </div>'
            			+'<div class="c"><label>自我评价</label><textarea placeholder="介绍自己，说明自己的最大优势，让企业看到你的闪光点" id="int_selfintro" maxlength="1000" name="qzyxZwpj"></textarea> </div><div class="h30">                    <span class="frt" id="int_selfintro_chars">0/1000 字</span> </div>'
            			+'<div class="h30"><div class="c c3" float-index="false" style=""><label>到岗时间</label><div class="sh"><div class="txt pointer" id="int_entrytime_list" float-on="false"> <select class="ef select3" name="qzyxDgsj" id="qzyxDgsj"></select><span class="ic i_arrow"></span></div></div> </div><div class="c c3" float-index="false"><label>工作类型</label><div class="sh"><div class="txt pointer" id="int_seektype_list" float-on="false"> <select class="ef select3" name="qzyxGzlx" id="qzyxGzlx"></select><span class="ic i_arrow"></span></div><input id="int_seektype" type="hidden" value="0"></div></div></div> '
            			+'<div class="btnbox"> <span class="p_but" id="intention_save" onclick="a();">保存</span> <span class="p_but gray" id="intention_cancel" onclick="intentionButtonClick(this)">取消</span></div></div></div></form>');
			        	$("#int_selfintro").bind("input propertychange",function(event){
			  				  var $this = $(this).val(); 
			  				  if ($this.length > 1000) {  
			  					  $(this).val($this.substring(0, 1000));  
			  		          }  
			  		          $("#int_selfintro_chars").text($(this).val().length+"/1000 字"); 
			  			});
		        		//性别
						$("#qzyxDgsj").append(dicContainer["dgsj"].toString());
						
						//求职状态
						$("#qzyxGzlx").append(dicContainer["gzlx"].toString());
						
        				$("#intention_edit").siblings('.icons').remove();
            			$("#intention").removeClass('m1');
            			$("#intention").removeClass('top_wrap').addClass('b2');
            			$(this).remove();
        	}
        }
     	});
	
}

function a(){
	if(isNull($("input[name='qzyxQwxz']").val())){
		alert("期望薪资不能为空！！！");
		return false;
	}
	if(isNull($("input[name='qzyxDd']").val())){
		alert("地点不能为空！！！");
		return false;
	}
	if(isNull($("input[name='qzyxZn']").val())){
		alert("职能不能为空！！！");
		return false;
	}
	if(isNull($("input[name='qzyxZw']").val())){
		alert("职位不能为空！！！");
		return false;
	}
	if(isNull($("input[name='qzyxHy']").val())){
		alert("行业不能为空！！！");
		return false;
	}
	/*if(isNull($("input[name='qzyxGrbq']").val())){
		alert("个人标签不能为空！！！");
		return false;
	}
	if(isNull($("input[name='qzyxZwpj']").val())){
		alert("自我评价不能为空！！！");
		return false;
	}*/
	$("#intention_complete").attr("class","right y");
	$.ajax({
        type: "POST",
        async:false,
        url: "saveUserQzyx",
        data:$("#qzyxForm").serialize(),
        dataType: "json",
        success: function(data){
        	alert(data.message);
        	$("#qzyxId").val(data.qzyx.qzyxId);
        	$("#jlBanbenId").val(data.qzyx.jlBbId);
        	var gzlx="";
        	if(data.qzyx.qzyxGzlx!=null){
        		gzlx=dicContainer.getDicItemName('gzlx', data.qzyx.qzyxGzlx);
    	    }
        	$("#intention").addClass('m1');
        	$("#intention").removeClass('b2').addClass('top_wrap');
        	$(".bh_div2").html('<div class="bd"><div class="con"><div class="e e3">'
                    +'<label>期望薪资</label><i>：</i><div>'+data.qzyx.qzyxQwxz+'</div>'
                +'</div><div class="e e3"><label>地点</label><i>：</i>'
                    +'<div><span class="ong">'+data.qzyx.qzyxDd+'</span></div></div><div class="e e3 ef"><label>职能/职位</label><i>：</i><div><span class="ong">'+data.qzyx.qzyxZn+'</span><span class="ong">&nbsp;&nbsp;'+data.qzyx.qzyxZw+'</span></div></div><div class="e e3"><label>行业</label><i>：</i><div> <span class="ong">'+data.qzyx.qzyxHy+'</span></div>'
                +'</div><div class="e e2"><label>工作类型</label><i>：</i><div>'+gzlx+'</div> </div><div class="clear"></div></div></div>');
        	$(".bh_div2").after('<span class="ed_icon_blue icons" id="salary_edit"></span>');
        }
	});
}
//求职意向取消

function intentionButtonClick(o) {
		var qzyxId=$("#qzyxId").val();
		$.ajax({
	        type: "POST",
	        async:false,
	        url: "findUserQzyx",
	        data: {qzyxId:qzyxId},
	        dataType: "json",
	        success: function(data){
				$("#intention").addClass('m1');
				$("#intention").removeClass('b2').addClass('top_wrap');
				if(data!=null&&data.qzyxGzlx!=null){
					$("#intention_complete").attr("class","right y");
					var gzlx="";
		        	if(data.qzyxGzlx!=null){
		        		gzlx=dicContainer.getDicItemName('gzlx', data.qzyxGzlx);
		    	    }
					$(".bh_div2").html('<div class="bd"><div class="con"><div class="e e3">'
		                    +'<label>期望薪资</label><i>：</i><div>'+data.qzyxQwxz+'</div>'
		                +'</div><div class="e e3"><label>地点</label><i>：</i>'
		                    +'<div><span class="ong">'+data.qzyxDd+'</span></div></div><div class="e e3 ef"><label>职能/职位</label><i>：</i><div><span class="ong">'+data.qzyxZn+'  </span><span class="ong">&nbsp;&nbsp;'+data.qzyxZw+'</span></div></div><div class="e e3"><label>行业</label><i>：</i><div> <span class="ong">'+data.qzyxHy+'</span></div>'
		                +'</div><div class="e e2"><label>工作类型</label><i>：</i><div>'+gzlx+'</div> </div><div class="clear"></div></div></div>');
					$(".bh_div2").after('<span class="ed_icon_blue icons" id="salary_edit"></span>');
				}else{
					$(".bh_div2").html('<div class="none icons" id="schoolaward_empty">完善求职意向，让HR更了解你！</div> ');
				}
			}
	});
}
//工作经验---------------------------------------------------开始
/*function workButtonClick(work) {
	$(work).removeClass('add').addClass('unadd');
	$("#work").html('<div class="bd com" id="work_modify_">'
    +'<div class="con">'
       +' <div class="c" float-index="false"> <label>时间</label><i>*</i><div class="h30"> <div class="sh sx"><div class="txt pointer" id="work_timefrom_calendar" float-on="false"><input class="ef date_img" id="work_timefrom_input" type="date"  value=""></div></div><span class="dao">到</span><div class="sh sx"><div class="txt pointer" id="work_timeto_calendar" float-on="false"><input class="ef date_img" id="work_timeto_input" type="date"  value="至今"> </div></div></div></div>'
		+'<div class="c" float-index="false" id="work_compname_index"><label>公司</label><i>*</i> <div class="h30"> <div class="sh"><div class="txt" float-on="false" id="work_compname_div"> <input class="ef" id="work_compname" maxlength="50" type="text" value=""><div class="ul u3" id="work_compname_list"> </div> </div></div></div></div>'
        +'<div class="h30"> <div class="c c1" float-index="false" id="work_func_index"> <label>职能</label><i>*</i><div class="sh"><div class="txt pointer" float-on="false" id="work_func_div"><input id="work_func_input" placeholder="填写/选择" class="ef cursor" type="text" value="" pre_value=""> <span class="ic i_block pointer" id="work_func_click" onclick="work_func_click(this)"></span><div class="ul u3" id="work_func_list"></div></div><input id="work_func" type="hidden" value="" pre_code=""></div></div><div class="c c4" float-index="false" id="work_industry_index"><label>行业</label><i>*</i><div class="sh"><div class="txt pointer" float-on="false" id="work_industry_div"> <input placeholder="填写/选择" class="ef cursor" id="work_industry_input" type="text" value="" pre_value=""><span class="ic i_block pointer" id="work_industry_click" onclick="work_industry_click(this)"></span><div class="ul u3" id="work_industry_list"></div></div><input id="work_industry" type="hidden" value="" pre_code=""></div><input id="work_industry" type="hidden" value="" pre_code=""></div></div>'
        +'<div class="h30"><div class="c c1" float-index="false" id="work_position_index"><label>职位</label><i>*</i><div class="sh"><div class="txt" float-on="false" id="work_position_div"><input class="ef" id="work_position" maxlength="70" type="text" value=""></div></div></div><div class="c c4" float-index="false"><label>公司规模</label><div class="sh"><div class="txt pointer" id="work_companysize_list" float-on="false"><select class="ef select4"><option>少于50人</option><option>150-500人</option><option>500-1000人</option><option>5000-10000人</option></select></div><input id="work_companysize" type="hidden" value=""></div></div></div>'
         +'<div class="h30"><div class="c c1"><label>部门</label><i>*</i><div class="sh"><div class="txt"><input class="ef" id="work_department" maxlength="50" type="text" value=""></div></div></div><div class="c c4" float-index="false"><label>公司性质</label><i>*</i><div class="sh"><div class="txt pointer" id="work_companytype_list" float-on="false"><select class="ef select4"><option>外资（欧美）</option><option>外资（非欧美）</option><option>合资</option><option>国企</option><option>民营公司</option><option>上市公司</option><option>创业公司</option><option>外企代表处</option><option>政府机关</option><option>事业单位</option><option>非营利组织</option></select></div></div></div></div>'
        +'<div class="c"><label>工作描述</label><i>*</i><textarea id="work_describe"  placeholder="描述你的职责范围、工作任务及取得的成绩等"></textarea><div class="h30"><span class="frt" id="work_describe_chars">0/1000 字</span> </div></div>'
        +'<div class="c"><label>工作类型</label><div class="btox txt" id="work_type_div"><select class="ef"><option>全职</option><option>兼职</option></select></div></div>'
        +'<div class="h30"><div class="c c5"><label>下属人数</label><div class="sh"><div class="txt"><input class="ef" maxlength="5" id="work_reportperson" type="text" value=""></div> </div></div>'
        +'<div class="c c5 cl"><label>汇报对象</label><div class="sh"><div class="txt"><input class="ef" id="work_reportboss" maxlength="50" type="text" value=""></div></div></div>'
        +'<div class="c c7 cl"><label>离职原因</label><div class="sh"><div class="txt"> <input class="ef" id="work_leavereason" maxlength="200" type="text" value=""></div></div></div></div>'
        +'<div class="c"><label>主要业绩</label><textarea id="work_score"  placeholder="描述取得业绩的主要内容，以充分证明作为高级人才的能力和价值"></textarea><div class="h30"><span class="frt" id="work_score_chars">0/1000 字</span></div></div>'
        +'<div class="c"><label>海外经历</label><div class="btox txt" id="work_isoverseas_div"><select class="ef"><option>是</option><option selected>否</option></select></div></div></div></div></div>'
    +'<div class="btnbox"><span class="p_but" id="work_save_" onclick="saveButtonClick(this);">保存</span><span class="p_but gray" id="work_cancel_" onclick="work_cancel_ButtonClick(this)">取消</span></div></div>');
}
//工作经验取消

function work_cancel_ButtonClick(work) {
	$('#work_new').removeClass('unadd').addClass('add');
	$("#work").html('<div class="none icons" id="work_empty">完善工作经验，展现工作内容及能力，让HR更了解你！</div>');
}*/
//工作经验增加
function workButtonClick(work){
	$(work).removeClass('add').addClass('unadd');
	$(work).attr("onclick","");
	//$("#work").addClass('bd');
	//$("#work").addClass('com');
	
	var xinzengForm='<form id="gzjyForm"><input type="hidden" name="jlBbId" value="'+$("#jlBanbenId").val()+'"/><div class="bd com" id="work_modify_">'
    +'<div class="con">'
    +' <div class="c" float-index="false"> <label>时间</label><i>*</i><div class="h30"> <div class="sh sx"><div class="txt pointer" id="work_timefrom_calendar" float-on="false"><input class="ef date_img" id="work_timefrom_input" name="gzjyKssj" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})" type="text"  value=""></div></div><span class="dao">到</span><div class="sh sx"><div class="txt pointer" id="work_timeto_calendar" float-on="false"><input class="ef date_img" id="work_timeto_input" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})" type="text" name="gzjyJssj" value="至今"> </div></div></div></div>'
		+'<div class="c" float-index="false" id="work_compname_index"><label>公司</label><i>*</i> <div class="h30"> <div class="sh"><div class="txt" float-on="false" id="work_compname_div"> <input class="ef" id="work_compname" maxlength="50" name="gzjyGs" type="text" value=""><div class="ul u3" id="work_compname_list"> </div> </div></div></div></div>'
     +'<div class="h30"> <div class="c c1" float-index="false" id="work_func_index"> <label>职能</label><i>*</i><div class="sh"><div class="txt pointer" float-on="false" id="work_func_div"><input id="work_zn_input" placeholder="选择" readOnly name="gzjyZn" class="ef cursor" type="text" value="" pre_value=""> <span class="ic i_block pointer" id="work_func_click" onclick="work_func_click(this)"></span><div class="ul u3" id="work_func_list"></div></div><input id="work_func" type="hidden" value="" pre_code=""></div></div><div class="c c4" float-index="false" id="work_industry_index"><label>行业</label><i>*</i><div class="sh"><div class="txt pointer" float-on="false" id="work_industry_div"> <input placeholder="选择" readOnly name="gzjyHy" class="ef cursor" id="int_hy_input" type="text" value="" pre_value=""><span class="ic i_block pointer" id="work_industry_click" onclick="work_industry_click(this)"></span><div class="ul u3" id="work_industry_list"></div></div><input id="work_industry" type="hidden" value="" pre_code=""></div><input id="work_industry" type="hidden" value="" pre_code=""></div></div>'
     +'<div class="h30"><div class="c c1" float-index="false" id="work_position_index"><label>职位</label><i>*</i><div class="sh"><div class="txt" float-on="false" id="work_position_div"><input class="ef" id="work_position" maxlength="70" type="text" name="gzjyZw" value=""></div></div></div><div class="c c4" float-index="false"><label>公司规模</label><div class="sh"><div class="txt pointer" id="work_companysize_list" float-on="false"><select class="ef select4" name="gzjyGsgm" id="gzjyGsgm"></select></div><input id="work_companysize" type="hidden" value=""></div></div></div>'
      +'<div class="h30"><div class="c c1"><label>部门</label><i>*</i><div class="sh"><div class="txt"><input class="ef" id="work_department" maxlength="50" name="gzjyBm" type="text" value=""></div></div></div><div class="c c4" float-index="false"><label>公司性质</label><i>*</i><div class="sh"><div class="txt pointer" id="work_companytype_list" float-on="false"><select class="ef select4" name="gzjyGsxz" id="gzjyGsxz"></select></div></div></div></div>'
     +'<div class="c"><label>工作描述</label><i>*</i><textarea id="work_describe" name="gzjyGzms" maxlength="1000"  placeholder="描述你的职责范围、工作任务及取得的成绩等"></textarea><div class="h30"><span class="frt" id="work_describe_chars">0/1000 字</span> </div></div>'
     +'<div class="c"><label>工作类型</label><div class="btox txt" id="work_type_div"><select class="ef" name="gzjyGzlx" id="gzjyGzlx"></select></div></div>'
     +'<div class="h30"><div class="c c5"><label>下属人数</label><div class="sh"><div class="txt"><input class="ef" maxlength="5" name="gzjyXsrs" id="work_reportperson" type="text" value=""></div> </div></div>'
     +'<div class="c c5 cl"><label>汇报对象</label><div class="sh"><div class="txt"><input class="ef" id="work_reportboss" maxlength="50" name="gzjyHbdx" type="text" value=""></div></div></div>'
     +'<div class="c c7 cl"><label>离职原因</label><div class="sh"><div class="txt"> <input class="ef" id="work_leavereason" maxlength="200" type="text" name="gzjyLzyy" value=""></div></div></div></div>'
     +'<div class="c"><label>主要业绩</label><textarea id="work_score" name="gzjyZyyj" maxlength="1000"  placeholder="描述取得业绩的主要内容，以充分证明作为高级人才的能力和价值"></textarea><div class="h30"><span class="frt" id="work_score_chars">0/1000 字</span></div></div>'
     +'<div class="c"><label>海外经历</label><div class="btox txt" id="work_isoverseas_div"><select class="ef" name="gzjyHwjl" id="gzjyHwjl"></select></div></div></div></div></div>'
 +'<div class="btnbox"><span class="p_but" id="work_save_" onclick="saveGzjy();">保存</span><span class="p_but gray" id="work_cancel_" onclick="work_cancel_ButtonClick(this)">取消</span></div></div></form>';
	
	
	var oldHtml=$("#work").html();//判断，原先是否有数据，有的话，取原值，（第1步）
	$("#work").html(xinzengForm);
	$("#work_describe").bind("input propertychange",function(event){
		  var $this = $(this).val(); 
		  if ($this.length > 1000) {  
			  $(this).val($this.substring(0, 1000));  
      }  
      $("#work_describe_chars").text($(this).val().length+"/1000 字"); 
	});
	$("#work_score").bind("input propertychange",function(event){
		  var $this = $(this).val(); 
		  if ($this.length > 1000) {  
			  $(this).val($this.substring(0, 1000));  
      }  
      $("#work_score_chars").text($(this).val().length+"/1000 字"); 
	});
	//公司规模
	$("#gzjyGsgm").append(dicContainer["gsgm"].toString());
	/*if(data.jianliXb!=null){
		$("#jianliXb").find("option[value = '"+data.jianliXb+"']").attr("selected","selected"); 
	}*/
	//公司性质
	$("#gzjyGsxz").append(dicContainer["gsxz"].toString());
		
	//工作类型
	$("#gzjyGzlx").append(dicContainer["gzlx"].toString());
	
	//海外经历
	$("#gzjyHwjl").append(dicContainer["119"].toString());
	$("#gzjyHwjl").find("option[value = '1']").attr("selected","selected"); 
	$("#work").append('');
	 $("#work").append(oldHtml);//判断，原先是否有数据，有的话，取原值，（第2步）
}
//保存工作经验
function saveGzjy(){
	if(isNull($("input[name='gzjyKssj']").val())){
		alert("开始时间不能为空！！！");
		return false;
	}
	if(isNull($("input[name='gzjyJssj']").val())){
		alert("结束时间不能为空！！！");
		return false;
	}
	if(isNull($("input[name='gzjyGs']").val())){
		alert("公司不能为空！！！");
		return false;
	}
	if(isNull($("input[name='gzjyZn']").val())){
		alert("职能不能为空！！！");
		return false;
	}
	if(isNull($("input[name='gzjyHy']").val())){
		alert("行业不能为空！！！");
		return false;
	}
	if(isNull($("input[name='gzjyZw']").val())){
		alert("职位不能为空！！！");
		return false;
	}
	if(isNull($("input[name='gzjyBm']").val())){
		alert("部门不能为空！！！");
		return false;
	}if(isNull($("input[name='gzjyGzms']").val())){
		alert("工作描述不能为空！！！");
		return false;
	}
	/*if(isNull($("input[name='gzjyXsrs']").val())){
		alert("下属人数不能为空！！！");
		return false;
	}
	if(isNull($("input[name='gzjyHbdx']").val())){
		alert("汇报对象不能为空！！！");
		return false;
	}
	if(isNull($("input[name='gzjyLzyy']").val())){
		alert("离职原因不能为空！！！");
		return false;
	}
	if(isNull($("input[name='gzjyZyyj']").val())){
		alert("主要业绩不能为空！！！");
		return false;
	}*/
	$("#work_complete").attr("class","right y");
	$.ajax({
      type: "POST",
      async:false,
      url: "saveGzjy",
      data:$("#gzjyForm").serialize(),
      dataType: "json",
      success: function(data){
      	alert(data.message);
      	$("#jlBanbenId").val(data.gzjy.jlBbId);
      	$('#work_new').removeClass('unadd').addClass('add');
      	$('#work_new').attr("onclick","workButtonClick(this)");
      	$("#work").removeClass('bd');
      	$("#work").removeClass('com');
      	$("#work").siblings('.btnbox').remove();
      	$("#work").html("");
      	$.ajax({
              type: "POST",
              async:false,
              url: "findGzjylistByJlbbId",
              data:{jlbbId:$("#jlBanbenId").val()},
              dataType: "json",
              success: function(data){
              	if(data.length!=0){
              		for(var i=0;i<data.length;i++){
              			var gzlx=dicContainer.getDicItemName('gzlx', data[i].gzjyGzlx);
              			var hwjl=dicContainer.getDicItemName('119', data[i].gzjyHwjl);
              			var gsxz=dicContainer.getDicItemName('gsxz', data[i].gzjyGsxz);
              			var gsgm=dicContainer.getDicItemName('gsgm', data[i].gzjyGsgm);
              			if(data[i].gzjyHwjl==0){
              				$("#work").append('<div class="bd" id="'+data[i].gzjyId+'">'
              			    +'<div class="con edit ebox"><div class="sp"><span>'+data[i].gzjyKssj+'-'+data[i].gzjyJssj+'</span><strong class="w280 at" title="'+data[i].gzjyGs+'">'+data[i].gzjyGs+'</strong><strong class="fbox jian hai"><span class="zhi at" title="'+data[i].gzjyZn+'">'+data[i].gzjyZn+'</span><em>('+gzlx+')<i>海外经历</i></em></strong></div><p class="pp at" title="'+data[i].gzjyHy+'  |  '+gsgm+'  |  '+gsxz+'  |  '+data[i].gzjyBm+'">'+data[i].gzjyHy+'  |  '+gsgm+'  |  '+gsxz+'  |  '+data[i].gzjyBm+'</p>'
              		        +'<div class="e"> <label>工作描述</label><i>：</i><div>'+data[i].gzjyGzms+'</div></div><div class="clear"></div><span class="ed_icon"><em class="edi icons"  onclick="gzjyEdit(\''+data[i].gzjyId+'\')"></em><em class="del icons"  onclick="gzjyDel(\''+data[i].gzjyId+'\')"></em></span></div></div>');
              			}else{
              				$("#work").append('<div class="bd" id="'+data[i].gzjyId+'">'
              	      			    +'<div class="con edit ebox"><div class="sp"><span>'+data[i].gzjyKssj+'-'+data[i].gzjyJssj+'</span><strong class="w280 at" title="'+data[i].gzjyGs+'">'+data[i].gzjyGs+'</strong><strong class="fbox jian hai"><span class="zhi at" title="'+data[i].gzjyZn+'">'+data[i].gzjyZn+'</span></strong></div><p class="pp at" title="'+data[i].gzjyHy+'  |  '+gsgm+'  |  '+gsxz+'  |  '+data[i].gzjyBm+'">'+data[i].gzjyHy+'  |  '+gsgm+'  |  '+gsxz+'  |  '+data[i].gzjyBm+'</p>'
              	      		        +'<div class="e"> <label>工作描述</label><i>：</i><div>'+data[i].gzjyGzms+'</div></div><div class="clear"></div><span class="ed_icon"><em class="edi icons"  onclick="gzjyEdit(\''+data[i].gzjyId+'\')"></em><em class="del icons"  onclick="gzjyDel(\''+data[i].gzjyId+'\')"></em></span></div></div>');
              			}
              		}
              	}else{
              		$("#work").append('<div class="none icons" id="schoolaward_empty">完善工作经验，展现工作内容及能力，让HR更了解你！</div> ');
              	}
		        	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
		        	//请求后台方法
		        	
		        	//回调函数中进行业务逻辑判断
		        	//如果没有数据，则展示以下内容---------------Start
		        	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
		        	//如果没有数据，则展示以下内容---------------End
		        	
		        	//alert($("#schoolaward").html(""));
              }
      	});
      }
	});
}

//工作经验修改方法
function gzjyEdit(dataID){
	$.ajax({
      type: "POST",
      async:false,
      url: "findGzjyById",
      data:{id:dataID},
      dataType: "json",
      success: function(data){
			//定义静态HTML，根据返回的JSON 套用
      	var editForm='<form id="gzjyForm"><input type="hidden" name="gzjyId" value="'+data.gzjyId+'"/><input type="hidden" name="jlBbId" value="'+data.jlBbId+'"/><div class="bd com" id="work_modify_">'
        +'<div class="con">'
        +' <div class="c" float-index="false"> <label>时间</label><i>*</i><div class="h30"> <div class="sh sx"><div class="txt pointer" id="work_timefrom_calendar" float-on="false"><input class="ef date_img" id="work_timefrom_input" name="gzjyKssj" type="text" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})"  value="'+data.gzjyKssj+'"></div></div><span class="dao">到</span><div class="sh sx"><div class="txt pointer" id="work_timeto_calendar" float-on="false"><input class="ef date_img" id="work_timeto_input" type="text" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})" name="gzjyJssj" value="'+data.gzjyJssj+'"> </div></div></div></div>'
    		+'<div class="c" float-index="false" id="work_compname_index"><label>公司</label><i>*</i> <div class="h30"> <div class="sh"><div class="txt" float-on="false" id="work_compname_div"> <input class="ef" id="work_compname" maxlength="50" name="gzjyGs" type="text" value="'+data.gzjyGs+'"><div class="ul u3" id="work_compname_list"> </div> </div></div></div></div>'
         +'<div class="h30"> <div class="c c1" float-index="false" id="work_func_index"> <label>职能</label><i>*</i><div class="sh"><div class="txt pointer" float-on="false" id="work_func_div"><input id="work_zn_input" placeholder="选择" name="gzjyZn" readOnly class="ef cursor" type="text" value="'+data.gzjyZn+'" pre_value=""> <span class="ic i_block pointer" id="work_func_click" onclick="work_func_click(this)"></span><div class="ul u3" id="work_func_list"></div></div><input id="work_func" type="hidden"  pre_code=""></div></div><div class="c c4" float-index="false" id="work_industry_index"><label>行业</label><i>*</i><div class="sh"><div class="txt pointer" float-on="false" id="work_industry_div"> <input placeholder="选择"  readOnly  name="gzjyHy" class="ef cursor" id="int_hy_input" type="text" value="'+data.gzjyHy+'" pre_value=""><span class="ic i_block pointer" id="work_industry_click" onclick="work_industry_click(this)"></span><div class="ul u3" id="work_industry_list"></div></div><input id="work_industry" type="hidden" value="" pre_code=""></div><input id="work_industry" type="hidden" value="" pre_code=""></div></div>'
         +'<div class="h30"><div class="c c1" float-index="false" id="work_position_index"><label>职位</label><i>*</i><div class="sh"><div class="txt" float-on="false" id="work_position_div"><input class="ef" id="work_position" maxlength="70" type="text" name="gzjyZw" value="'+data.gzjyZw+'"></div></div></div><div class="c c4" float-index="false"><label>公司规模</label><div class="sh"><div class="txt pointer" id="work_companysize_list" float-on="false"><select class="ef select4" name="gzjyGsgm" id="gzjyGsgm"></select></div><input id="work_companysize" type="hidden" value=""></div></div></div>'
          +'<div class="h30"><div class="c c1"><label>部门</label><i>*</i><div class="sh"><div class="txt"><input class="ef" id="work_department" maxlength="50" name="gzjyBm" type="text" value="'+data.gzjyBm+'"></div></div></div><div class="c c4" float-index="false"><label>公司性质</label><i>*</i><div class="sh"><div class="txt pointer" id="work_companytype_list" float-on="false"><select class="ef select4" name="gzjyGsxz" id="gzjyGsxz"></select></div></div></div></div>'
         +'<div class="c"><label>工作描述</label><i>*</i><textarea id="work_describe" name="gzjyGzms" maxlength="1000"  placeholder="描述你的职责范围、工作任务及取得的成绩等">'+data.gzjyGzms+'</textarea><div class="h30"><span class="frt" id="work_describe_chars">'+data.gzjyGzms.length+'/1000 字</span> </div></div>'
         +'<div class="c"><label>工作类型</label><div class="btox txt" id="work_type_div"><select class="ef" name="gzjyGzlx" id="gzjyGzlx"></select></div></div>'
         +'<div class="h30"><div class="c c5"><label>下属人数</label><div class="sh"><div class="txt"><input class="ef" maxlength="5" name="gzjyXsrs" id="work_reportperson" type="text" value="'+data.gzjyXsrs+'"></div> </div></div>'
         +'<div class="c c5 cl"><label>汇报对象</label><div class="sh"><div class="txt"><input class="ef" id="work_reportboss" maxlength="50" name="gzjyHbdx" type="text" value="'+data.gzjyHbdx+'"></div></div></div>'
         +'<div class="c c7 cl"><label>离职原因</label><div class="sh"><div class="txt"> <input class="ef" id="work_leavereason" maxlength="200" type="text" name="gzjyLzyy" value="'+data.gzjyLzyy+'"></div></div></div></div>'
         +'<div class="c"><label>主要业绩</label><textarea id="work_score" name="gzjyZyyj" maxlength="1000"  placeholder="描述取得业绩的主要内容，以充分证明作为高级人才的能力和价值">'+data.gzjyZyyj+'</textarea><div class="h30"><span class="frt" id="work_score_chars">'+data.gzjyZyyj.length+'/1000 字</span></div></div>'
         +'<div class="c"><label>海外经历</label><div class="btox txt" id="work_isoverseas_div"><select class="ef" name="gzjyHwjl" id="gzjyHwjl"></select></div></div></div></div></div>'
         +'<div class="btnbox"><span class="p_but" id="work_save_" onclick="saveGzjy();">保存</span><span class="p_but gray" id="work_cancel_" onclick="work_cancel_ButtonClick(this)">取消</span></div></div></form>';
				  $("#"+dataID).addClass('bd');
					$("#"+dataID).addClass('com');
					//展示出表单并填充后台数据
			$("#"+dataID).html(editForm);
			$("#work_describe").bind("input propertychange",function(event){
				  var $this = $(this).val(); 
				  if ($this.length > 1000) {  
					  $(this).val($this.substring(0, 1000));  
		        }  
		        $("#work_describe_chars").text($(this).val().length+"/1000 字"); 
			});
			$("#work_score").bind("input propertychange",function(event){
				  var $this = $(this).val(); 
				  if ($this.length > 1000) {  
					  $(this).val($this.substring(0, 1000));  
		        }  
		        $("#work_score_chars").text($(this).val().length+"/1000 字"); 
			});
			$("#gzjyGsgm").append(dicContainer["gsgm"].toString());
			if(data.gzjyGsgm!=null){
				$("#gzjyGsgm").find("option[value = '"+data.gzjyGsgm+"']").attr("selected","selected"); 
			}
			//公司性质
			$("#gzjyGsxz").append(dicContainer["gsxz"].toString());
			if(data.gzjyGsxz!=null){
				$("#gzjyGsxz").find("option[value = '"+data.gzjyGsxz+"']").attr("selected","selected"); 
			}
			//工作类型
			$("#gzjyGzlx").append(dicContainer["gzlx"].toString());
			if(data.gzjyGzlx!=null){
				$("#gzjyGzlx").find("option[value = '"+data.gzjyGzlx+"']").attr("selected","selected"); 
			}
			//海外经历
			$("#gzjyHwjl").append(dicContainer["119"].toString());
			if(data.gzjyHwjl!=null){
				$("#gzjyHwjl").find("option[value = '"+data.gzjyHwjl+"']").attr("selected","selected"); 
			}
      }
    	});
}

//工作经验删除方法
function gzjyDel(dataID){
	if(confirm("确定删除该条工作经验么？")){
		$.ajax({
	      type: "POST",
	      async:false,
	      url: "deleteGzjyById",
	      data:{ids:dataID},
	      dataType: "json",
	      success: function(data){
	      	$('#work_new').removeClass('unadd').addClass('add');
	      	$('#work_new').attr("onclick","workButtonClick(this)");
	      	$("#work").removeClass('bd');
	      	$("#work").removeClass('com');
	      	$("#work").siblings('.btnbox').remove();
	      	$("#work").html("");
	      	$.ajax({
	              type: "POST",
	              async:false,
	              url: "findGzjylistByJlbbId",
	              data:{jlbbId:$("#jlBanbenId").val()},
	              dataType: "json",
	              success: function(data){
	            	  
	            	  if(data.length!=0){
	            		  $("#work_complete").attr("class","right y");
	                		for(var i=0;i<data.length;i++){
	                			var gzlx=dicContainer.getDicItemName('gzlx', data[i].gzjyGzlx);
	                  			var hwjl=dicContainer.getDicItemName('119', data[i].gzjyHwjl);
	                  			var gsxz=dicContainer.getDicItemName('gsxz', data[i].gzjyGsxz);
	                  			var gsgm=dicContainer.getDicItemName('gsgm', data[i].gzjyGsgm);
	                  			if(data[i].gzjyHwjl==0){
	                  				$("#work").append('<div class="bd" id="'+data[i].gzjyId+'">'
	                  			    +'<div class="con edit ebox"><div class="sp"><span>'+data[i].gzjyKssj+'-'+data[i].gzjyJssj+'</span><strong class="w280 at" title="'+data[i].gzjyGs+'">'+data[i].gzjyGs+'</strong><strong class="fbox jian hai"><span class="zhi at" title="'+data[i].gzjyZn+'">'+data[i].gzjyZn+'</span><em>('+gzlx+')<i>海外经历</i></em></strong></div><p class="pp at" title="'+data[i].gzjyHy+'  |  '+gsgm+'  |  '+gsxz+'  |  '+data[i].gzjyBm+'">'+data[i].gzjyHy+'  |  '+gsgm+'  |  '+gsxz+'  |  '+data[i].gzjyBm+'</p>'
	                  		        +'<div class="e"> <label>工作描述</label><i>：</i><div>'+data[i].gzjyGzms+'</div></div><div class="clear"></div><span class="ed_icon"><em class="edi icons"  onclick="gzjyEdit(\''+data[i].gzjyId+'\')"></em><em class="del icons"  onclick="gzjyDel(\''+data[i].gzjyId+'\')"></em></span></div></div>');
	                  			}else{
	                  				$("#work").append('<div class="bd" id="'+data[i].gzjyId+'">'
	                  	      			    +'<div class="con edit ebox"><div class="sp"><span>'+data[i].gzjyKssj+'-'+data[i].gzjyJssj+'</span><strong class="w280 at" title="'+data[i].gzjyGs+'">'+data[i].gzjyGs+'</strong><strong class="fbox jian hai"><span class="zhi at" title="'+data[i].gzjyZn+'">'+data[i].gzjyZn+'</span></strong></div><p class="pp at" title="'+data[i].gzjyHy+'  |  '+gsgm+'  |  '+gsxz+'  |  '+data[i].gzjyBm+'">'+data[i].gzjyHy+'  |  '+gsgm+'  |  '+gsxz+'  |  '+data[i].gzjyBm+'</p>'
	                  	      		        +'<div class="e"> <label>工作描述</label><i>：</i><div>'+data[i].gzjyGzms+'</div></div><div class="clear"></div><span class="ed_icon"><em class="edi icons"  onclick="gzjyEdit(\''+data[i].gzjyId+'\')"></em><em class="del icons"  onclick="gzjyDel(\''+data[i].gzjyId+'\')"></em></span></div></div>');
	                  			}
	                		}
	                		}else{
	                		$("#work_complete").attr("class","right n");
	                		$("#work").append('<div class="none icons" id="schoolaward_empty">完善工作经验，展现工作内容及能力，让HR更了解你！</div> ');
	                	}
			        	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
			        	//请求后台方法
			        	
			        	//回调函数中进行业务逻辑判断
			        	//如果没有数据，则展示以下内容---------------Start
			        	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
			        	//如果没有数据，则展示以下内容---------------End
			        	
			        	//alert($("#schoolaward").html(""));
	              }
	      	});
	      }
		});
	}
}

//工作经验取消
function work_cancel_ButtonClick(work) {
	$('#work_new').removeClass('unadd').addClass('add');
	$('#work_new').attr("onclick","workButtonClick(this)");
	$("#work").removeClass('bd');
	$("#work").removeClass('com');
	$("#work").siblings('.btnbox').remove();
	$("#work").html("");
	$.ajax({
      type: "POST",
      async:false,
      url: "findGzjylistByJlbbId",
      data:{jlbbId:$("#jlBanbenId").val()},
      dataType: "json",
      success: function(data){
    	  if(data.length!=0){
    		$("#work_complete").attr("class","right y");
      		for(var i=0;i<data.length;i++){
      			var gzlx=dicContainer.getDicItemName('gzlx', data[i].gzjyGzlx);
      			var hwjl=dicContainer.getDicItemName('119', data[i].gzjyHwjl);
      			var gsxz=dicContainer.getDicItemName('gsxz', data[i].gzjyGsxz);
      			var gsgm=dicContainer.getDicItemName('gsgm', data[i].gzjyGsgm);
      			if(data[i].gzjyHwjl==0){
      				$("#work").append('<div class="bd" id="'+data[i].gzjyId+'">'
      			    +'<div class="con edit ebox"><div class="sp"><span>'+data[i].gzjyKssj+'-'+data[i].gzjyJssj+'</span><strong class="w280 at" title="'+data[i].gzjyGs+'">'+data[i].gzjyGs+'</strong><strong class="fbox jian hai"><span class="zhi at" title="'+data[i].gzjyZn+'">'+data[i].gzjyZn+'</span><em>('+gzlx+')<i>海外经历</i></em></strong></div><p class="pp at" title="'+data[i].gzjyHy+'  |  '+gsgm+'  |  '+gsxz+'  |  '+data[i].gzjyBm+'">'+data[i].gzjyHy+'  |  '+gsgm+'  |  '+gsxz+'  |  '+data[i].gzjyBm+'</p>'
      		        +'<div class="e"> <label>工作描述</label><i>：</i><div>'+data[i].gzjyGzms+'</div></div><div class="clear"></div><span class="ed_icon"><em class="edi icons"  onclick="gzjyEdit(\''+data[i].gzjyId+'\')"></em><em class="del icons"  onclick="gzjyDel(\''+data[i].gzjyId+'\')"></em></span></div></div>');
      			}else{
      				$("#work").append('<div class="bd" id="'+data[i].gzjyId+'">'
      	      			    +'<div class="con edit ebox"><div class="sp"><span>'+data[i].gzjyKssj+'-'+data[i].gzjyJssj+'</span><strong class="w280 at" title="'+data[i].gzjyGs+'">'+data[i].gzjyGs+'</strong><strong class="fbox jian hai"><span class="zhi at" title="'+data[i].gzjyZn+'">'+data[i].gzjyZn+'</span></strong></div><p class="pp at" title="'+data[i].gzjyHy+'  |  '+gsgm+'  |  '+gsxz+'  |  '+data[i].gzjyBm+'">'+data[i].gzjyHy+'  |  '+gsgm+'  |  '+gsxz+'  |  '+data[i].gzjyBm+'</p>'
      	      		        +'<div class="e"> <label>工作描述</label><i>：</i><div>'+data[i].gzjyGzms+'</div></div><div class="clear"></div><span class="ed_icon"><em class="edi icons"  onclick="gzjyEdit(\''+data[i].gzjyId+'\')"></em><em class="del icons"  onclick="gzjyDel(\''+data[i].gzjyId+'\')"></em></span></div></div>');
      			}
      		}
      	}else{
      		 $("#work_complete").attr("class","right n");
      		$("#work").append('<div class="none icons" id="schoolaward_empty">完善工作经验，展现工作内容及能力，让HR更了解你！</div> ');
      	}
      	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
      	//请求后台方法
      	
      	//回调函数中进行业务逻辑判断
      	//如果没有数据，则展示以下内容---------------Start
      	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
      	//如果没有数据，则展示以下内容---------------End
      	
      	//alert($("#schoolaward").html(""));
      }
	});
	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
	//请求后台方法
	
	//回调函数中进行业务逻辑判断
	//如果没有数据，则展示以下内容---------------Start
	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
	//如果没有数据，则展示以下内容---------------End
	
	//alert($("#schoolaward").html(""));
	
}

//工作经验----------------------------------------------------------结束


//教育经历开始--------------------------------------------------------开始
/*function EducationButtonClick(education) {
	$(education).removeClass('add').addClass('unadd');
	$("#education").html('<div class="bd com" id="education_modify_">'
    +'<div class="con">'
       +' <div class="c" float-index="false"><label>时间</label><i>*</i><div class="h30"><div class="sh sx"><div class="txt pointer" id="edu_timefrom_calendar" float-on="false"><input class="ef date_img" id="edu_timefrom_input" type="date"  value=""></div></div><span class="dao">到</span><div class="sh sx"><div class="txt pointer" id="edu_timeto_calendar" float-on="false"><input class="ef date_img" id="edu_timeto_input" type="date" readonly="readonly" value="至今"></div> </div></div></div>'
        +'<div class="c" float-index="false" maxlength="50" id="edu_schoolname_index"> <label>学校</label><i>*</i><div class="h30"><div class="sh"><div class="txt" float-on="false" id="edu_schoolname_div"><input class="ef" maxlength="50" id="edu_schoolname" type="text" value=""></div> </div></div> </div>'
        +'<div class="c" float-index="false"><label>学历/学位</label><i>*</i><div class="h30"><div class="sh mr"><div class="txt pointer" id="edu_degree_list" float-on="false"><select class="ef select3"><option>初中及以下</option><option>高中</option><option>中专</option><option>大专</option><option>本科</option><option>硕士</option><option>博士</option><option>MBA</option></select></div></div><span><input class="tz" type="checkbox" />全日制</span></div></div>'
            
        +'<div class="c" float-index="false" id="edu_major_index"><label>专业</label><i>*</i><div class="h30"><div class="sh mr"><div class="txt pointer" float-on="false" id="edu_major_div"><input class="ef cursor" id="edu_major_input" type="text" placeholder="高中及以下学历选填" value="" pre_value=""></div> </div></div></div>'
       +'<div class="c"><label>专业描述</label><textarea id="edu_describe"  placeholder="描述在校期间所学的专业，主要包括课程内容、毕业设计等"></textarea><div class="h30"><span class="frt" id="edu_describe_chars">0/1000 字</span></div></div>'
        +'<div class="c"><label>海外经历</label><div class="btox txt" id="edu_isoverseas_div"><select class="ef "><option>是</option><option selected>否</option></select></div></div></div>');
	$("#education").after('<div class="btnbox"><span class="p_but" id="education_save_" onclick="saveButtonClick(this);">保存</span><span class="p_but gray" id="education_cancel_" onclick="education_cancel_ButtonClick(this)">取消</span> </div></div>'
    +'</div>');
}
//教育经历取消

function education_cancel_ButtonClick(education) {
	$('#education_new').removeClass('unadd').addClass('add');
	$("#education").siblings('.btnbox').remove();
	$("#education").html(' <div class="none icons" onclick="EducationButtonClick(this);" id="education_empty">完善教育经历，展现专业能力，让HR更了解你！</div>');
}*/
//教育经历增加
function EducationButtonClick(education){
	//$("#education").addClass('bd');
	//$("#education").addClass('com');
	$(education).removeClass('add').addClass('unadd');
	$(education).attr("onclick","");
	var xinzengForm='<form id="jyjlForm"><input type="hidden" name="jlBbId" value="'+$("#jlBanbenId").val()+'"/><div class="bd com" id="education_modify_">'
    +'<div class="con">'
       +' <div class="c" float-index="false"><label>时间</label><i>*</i><div class="h30"><div class="sh sx"><div class="txt pointer" id="edu_timefrom_calendar" float-on="false"><input class="ef date_img" id="edu_timefrom_input" type="text" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})" name="jyjlKssj" value=""></div></div><span class="dao">到</span><div class="sh sx"><div class="txt pointer" id="edu_timeto_calendar" float-on="false"><input class="ef date_img" id="edu_timeto_input" type="text" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})"  name="jyjlJssj" value="至今"></div> </div></div></div>'
        +'<div class="c" float-index="false" maxlength="50" id="edu_schoolname_index"> <label>学校</label><i>*</i><div class="h30"><div class="sh"><div class="txt" float-on="false" id="edu_schoolname_div"><input class="ef" maxlength="50" id="edu_schoolname" type="text" name="jyjlXx" value=""></div> </div></div> </div>'
        +'<div class="c" float-index="false"><label>学历/学位</label><i>*</i><div class="h30"><div class="sh mr"><div class="txt pointer" id="edu_degree_list" float-on="false"><select class="ef select3" name="jyjlXl" id="jyjlXl"></select></div></div><span><input class="tz" type="checkbox" name="jyjlQrzCheck"/><input type="hidden" name="jyjlQrz" value=""/>全日制</span></div></div>'
            
        +'<div class="c" float-index="false" id="edu_major_index"><label>专业</label><i>*</i><div class="h30"><div class="sh mr"><div class="txt pointer" float-on="false" id="edu_major_div"><input class="ef cursor" id="edu_major_input" name="jyjlZy" type="text" maxlength="40" placeholder="高中及以下学历选填" value="" pre_value=""></div> </div></div></div>'
       +'<div class="c"><label>专业描述</label><textarea id="edu_describe" maxlength="1000"  placeholder="描述在校期间所学的专业，主要包括课程内容、毕业设计等" name="jyjlZyms"></textarea><div class="h30"><span class="frt" id="edu_describe_chars">0/1000 字</span></div></div>'
        +'<div class="c"><label>海外经历</label><div class="btox txt" id="edu_isoverseas_div"><select class="ef " name="jyjlHwjl" id="jyjlHwjl"></select></div></div></div>'
	+'<div class="btnbox"><span class="p_but" id="education_save_" onclick="saveJyjl();">保存</span><span class="p_but gray" id="education_cancel_" onclick="education_cancel_ButtonClick(this)">取消</span> </div></div>'
    +'</div></form>';
	
	 var oldHtml=$("#education").html();//判断，原先是否有数据，有的话，取原值，（第1步）
	 $("#education").append('');
	 $("#education").html(xinzengForm);
	 $("#edu_describe").bind("input propertychange",function(event){
		  var $this = $(this).val(); 
		  if ($this.length > 1000) {  
			  $(this).val($this.substring(0, 1000));  
       }  
       $("#edu_describe_chars").text($(this).val().length+"/1000 字"); 
	});
	//工作类型
	$("#jyjlXl").append(dicContainer["syxl"].toString());
		
	//海外经历
    $("#jyjlHwjl").append(dicContainer["119"].toString());
    $("#jyjlHwjl").find("option[value = '1']").attr("selected","selected"); 
	 $("#education").append(oldHtml);//判断，原先是否有数据，有的话，取原值，（第2步）
}
//保存教育经历
function saveJyjl(){
	if(isNull($("input[name='jyjlKssj']").val())){
		alert("开始时间不能为空！！！");
		return false;
	}
	if(isNull($("input[name='jyjlJssj']").val())){
		alert("结束时间不能为空！！！");
		return false;
	}
	if(isNull($("input[name='jyjlXx']").val())){
		alert("学校不能为空！！！");
		return false;
	}
	if(isNull($("input[name='jyjlZy']").val())){
		alert("专业不能为空！！！");
		return false;
	}
	/*if(isNull($("input[name='jyjlZyms']").val())){
		alert("专业描述不能为空！！！");
		return false;
	}*/
	$("#education_complete").attr("class","right y");
	if($("input[name='jyjlQrzCheck']").attr('checked')){
		$("input[name='jyjlQrz']").val("1");
	}else{
		$("input[name='jyjlQrz']").val("0");
	}
	$.ajax({
      type: "POST",
      async:false,
      url: "saveJyjl",
      data:$("#jyjlForm").serialize(),
      dataType: "json",
      success: function(data){
      	alert(data.message);
      	$("#jlBanbenId").val(data.jyjl.jlBbId);
      	$('#education_new').removeClass('unadd').addClass('add');
      	$('#education_new').attr("onclick","EducationButtonClick(this)");
      	$("#education").removeClass('bd');
      	$("#education").removeClass('com');
      	$("#education").siblings('.btnbox').remove();
      	$("#education").html("");
      	$.ajax({
              type: "POST",
              async:false,
              url: "findJyjllistByJlbbId",
              data:{jlbbId:$("#jlBanbenId").val()},
              dataType: "json",
              success: function(data){
              	if(data.length!=0){
              		for(var i=0;i<data.length;i++){
              			var xl=dicContainer.getDicItemName('syxl', data[i].jyjlXl);
              			if(data[i].jyjlHwjl==0){
              				$("#education").append('<div class="bd">'
                      			    +'<div class="con edit" id="'+data[i].jyjlId+'">'
                      			  +'<div class="sp"><span>'+data[i].jyjlKssj+'-'+data[i].jyjlJssj+'</span><strong class="fbox hai w146"><span class="at" title="'+data[i].jyjlXx+'">'+data[i].jyjlXx+'</span><input type="hidden" id="education_verify_178993151" value="0"><em><i>海外经历</i></em></strong>'
                      			+'<span class="cl3 w140 at">'+xl+'</span></div><div class="clear"></div><span class="ed_icon"><em class="edi icons" id="education_edit_178993151" onclick="jyjlEdit(\''+data[i].jyjlId+'\')"></em><em class="del icons" id="education_delete_178993151" onclick="jyjlDel(\''+data[i].jyjlId+'\')"></em></span></div></div>');
              			}else{
              				$("#education").append('<div class="bd">'
                      			    +'<div class="con edit" id="'+data[i].jyjlId+'">'
                      			  +'<div class="sp"><span>'+data[i].jyjlKssj+'-'+data[i].jyjlJssj+'</span><strong class="fbox hai w146"><span class="at" title="'+data[i].jyjlXx+'">'+data[i].jyjlXx+'</span><input type="hidden" id="education_verify_178993151" value="0"></strong>'
                      			+'<span class="cl3 w140 at">'+xl+'</span></div><div class="clear"></div><span class="ed_icon"><em class="edi icons" id="education_edit_178993151" onclick="jyjlEdit(\''+data[i].jyjlId+'\')"></em><em class="del icons" id="education_delete_178993151" onclick="jyjlDel(\''+data[i].jyjlId+'\')"></em></span></div></div>');
              			}
              	
              		}
              	}else{
              		$("#education").append('<div class="none icons" id="schoolaward_empty">完善教育经历，展现专业能力，让HR更了解你！</div> ');
              	}
		        	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
		        	//请求后台方法
		        	
		        	//回调函数中进行业务逻辑判断
		        	//如果没有数据，则展示以下内容---------------Start
		        	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
		        	//如果没有数据，则展示以下内容---------------End
		        	
		        	//alert($("#schoolaward").html(""));
              }
      	});
      }
	});
}

//教育经历修改方法
function jyjlEdit(dataID){
	$.ajax({
      type: "POST",
      async:false,
      url: "findJyjlById",
      data:{id:dataID},
      dataType: "json",
      success: function(data){
			//定义静态HTML，根据返回的JSON 套用
      	var editForm='<form id="jyjlForm"><input type="hidden" name="jyjlId" value="'+data.jyjlId+'"/><input type="hidden" name="jlBbId" value="'+data.jlBbId+'"/>'
        +'<div class="con">'
        +'<div class="c" float-index="false"><label>时间</label><i>*</i><div class="h30"><div class="sh sx"><div class="txt pointer" id="edu_timefrom_calendar" float-on="false"><input class="ef date_img" id="edu_timefrom_input" type="text" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})" name="jyjlKssj" value="'+data.jyjlKssj+'"></div></div><span class="dao">到</span><div class="sh sx"><div class="txt pointer" id="edu_timeto_calendar" float-on="false"><input class="ef date_img" id="edu_timeto_input" type="text" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})"  name="jyjlJssj" value="'+data.jyjlJssj+'"></div> </div></div></div>'
         +'<div class="c" float-index="false" maxlength="50" id="edu_schoolname_index"> <label>学校</label><i>*</i><div class="h30"><div class="sh"><div class="txt" float-on="false" id="edu_schoolname_div"><input class="ef" maxlength="50" id="edu_schoolname" type="text" name="jyjlXx" value="'+data.jyjlXx+'"></div> </div></div> </div>'
         +'<div class="c" float-index="false"><label>学历/学位</label><i>*</i><div class="h30"><div class="sh mr"><div class="txt pointer" id="edu_degree_list" float-on="false"><select class="ef select3" name="jyjlXl" id="jyjlXl"></select></div></div><span><input class="tz" type="checkbox" name="jyjlQrzCheck"/><input type="hidden" name="jyjlQrz" value=""/>全日制</span></div></div>'
         +'<div class="c" float-index="false" id="edu_major_index"><label>专业</label><i>*</i><div class="h30"><div class="sh mr"><div class="txt pointer" float-on="false" id="edu_major_div"><input class="ef cursor" id="edu_major_input" name="jyjlZy" type="text" maxlength="40" placeholder="高中及以下学历选填" value="'+data.jyjlZy+'" pre_value=""></div> </div></div></div>'
        +'<div class="c"><label>专业描述</label><textarea id="edu_describe" maxlength="1000"  placeholder="描述在校期间所学的专业，主要包括课程内容、毕业设计等" name="jyjlZyms">'+data.jyjlZyms+'</textarea><div class="h30"><span class="frt" id="edu_describe_chars">'+data.jyjlZyms.length+'/1000 字</span></div></div>'
         +'<div class="c"><label>海外经历</label><div class="btox txt" id="edu_isoverseas_div"><select class="ef " name="jyjlHwjl" id="jyjlHwjl"></select></div></div></div>'
 	+'<div class="btnbox"><span class="p_but" id="education_save_" onclick="saveJyjl();">保存</span><span class="p_but gray" id="education_cancel_" onclick="education_cancel_ButtonClick(this)">取消</span> </div></div>'
     +'</form>';
			$("#"+dataID).addClass('bd');
			$("#"+dataID).addClass('com');
					//展示出表单并填充后台数据
			$("#"+dataID).html(editForm);
			 $("#edu_describe").bind("input propertychange",function(event){
				  //alert($(this).val());
				  var $this = $(this).val(); 
				  if ($this.length > 1000) {  
					  $(this).val($this.substring(0, 1000));  
		       }  
		       $("#edu_describe_chars").text($(this).val().length+"/1000 字"); 
			});
			$("#jyjlXl").append(dicContainer["syxl"].toString());
			if(data.jyjlXl!=null){
				$("#jyjlXl").find("option[value = '"+data.jyjlXl+"']").attr("selected","selected"); 
			}
			//海外精力
			$("#jyjlHwjl").append(dicContainer["119"].toString());
			if(data.jyjlHwjl!=null){
				$("#jyjlHwjl").find("option[value = '"+data.jyjlHwjl+"']").attr("selected","selected"); 
			}
			if(data.jyjlQrz==1){
				$("input[name='jyjlQrzCheck']").attr("checked","checked");
			}
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
	      	$('#education_new').removeClass('unadd').addClass('add');
	      	$('#education_new').attr("onclick","EducationButtonClick(this)");
	      	$("#education").removeClass('bd');
	      	$("#education").removeClass('com');
	      	$("#education").siblings('.btnbox').remove();
	      	$("#education").html("");
	      	$.ajax({
	              type: "POST",
	              async:false,
	              url: "findJyjllistByJlbbId",
	              data:{jlbbId:$("#jlBanbenId").val()},
	              dataType: "json",
	              success: function(data){
	              if(data.length!=0){ 
	            	$("#education_complete").attr("class","right y");
	              	for(var i=0;i<data.length;i++){
	              		var xl=dicContainer.getDicItemName('syxl', data[i].jyjlXl);
	          			if(data[i].jyjlHwjl==0){
	          				$("#education").append('<div class="bd">'
	                  			    +'<div class="con edit" id="'+data[i].jyjlId+'">'
	                  			  +'<div class="sp"><span>'+data[i].jyjlKssj+'-'+data[i].jyjlJssj+'</span><strong class="fbox hai w146"><span class="at" title="'+data[i].jyjlXx+'">'+data[i].jyjlXx+'</span><input type="hidden" id="education_verify_178993151" value="0"><em><i>海外经历</i></em></strong>'
	                  			+'<span class="cl3 w140 at">'+xl+'</span></div><div class="clear"></div><span class="ed_icon"><em class="edi icons" id="education_edit_178993151" onclick="jyjlEdit(\''+data[i].jyjlId+'\')"></em><em class="del icons" id="education_delete_178993151" onclick="jyjlDel(\''+data[i].jyjlId+'\')"></em></span></div></div>');
	          			}else{
	          				$("#education").append('<div class="bd">'
	                  			    +'<div class="con edit" id="'+data[i].jyjlId+'">'
	                  			  +'<div class="sp"><span>'+data[i].jyjlKssj+'-'+data[i].jyjlJssj+'</span><strong class="fbox hai w146"><span class="at" title="'+data[i].jyjlXx+'">'+data[i].jyjlXx+'</span><input type="hidden" id="education_verify_178993151" value="0"></strong>'
	                  			+'<span class="cl3 w140 at">'+xl+'</span></div><div class="clear"></div><span class="ed_icon"><em class="edi icons" id="education_edit_178993151" onclick="jyjlEdit(\''+data[i].jyjlId+'\')"></em><em class="del icons" id="education_delete_178993151" onclick="jyjlDel(\''+data[i].jyjlId+'\')"></em></span></div></div>');
	          			}
	            	
	            	}
	                }else{
	                	$("#education_complete").attr("class","right n");
	            		$("#education").append('<div class="none icons" id="schoolaward_empty">完善教育经历，展现专业能力，让HR更了解你！</div> ');
	            	}
			        	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
			        	//请求后台方法
			        	
			        	//回调函数中进行业务逻辑判断
			        	//如果没有数据，则展示以下内容---------------Start
			        	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
			        	//如果没有数据，则展示以下内容---------------End
			        	
			        	//alert($("#schoolaward").html(""));
	              }
	      	});
	      }
		});
	}
}

//教育经历取消
function education_cancel_ButtonClick(work) {
	$('#education_new').removeClass('unadd').addClass('add');
	$('#education_new').attr("onclick","EducationButtonClick(this)");
	$("#education").removeClass('bd');
	$("#education").removeClass('com');
	$("#education").siblings('.btnbox').remove();
	$("#education").html("");
	$.ajax({
        type: "POST",
        async:false,
        url: "findJyjllistByJlbbId",
        data:{jlbbId:$("#jlBanbenId").val()},
        dataType: "json",
        success: function(data){
        	if(data.length!=0){
        		$("#education_complete").attr("class","right y");
        		for(var i=0;i<data.length;i++){
        			var xl=dicContainer.getDicItemName('syxl', data[i].jyjlXl);
          			if(data[i].jyjlHwjl==0){
          				$("#education").append('<div class="bd">'
                  			    +'<div class="con edit" id="'+data[i].jyjlId+'">'
                  			  +'<div class="sp"><span>'+data[i].jyjlKssj+'-'+data[i].jyjlJssj+'</span><strong class="fbox hai w146"><span class="at" title="'+data[i].jyjlXx+'">'+data[i].jyjlXx+'</span><input type="hidden" id="education_verify_178993151" value="0"><em><i>海外经历</i></em></strong>'
                  			+'<span class="cl3 w140 at">'+xl+'</span></div><div class="clear"></div><span class="ed_icon"><em class="edi icons" id="education_edit_178993151" onclick="jyjlEdit(\''+data[i].jyjlId+'\')"></em><em class="del icons" id="education_delete_178993151" onclick="jyjlDel(\''+data[i].jyjlId+'\')"></em></span></div></div>');
          			}else{
          				$("#education").append('<div class="bd">'
                  			    +'<div class="con edit" id="'+data[i].jyjlId+'">'
                  			  +'<div class="sp"><span>'+data[i].jyjlKssj+'-'+data[i].jyjlJssj+'</span><strong class="fbox hai w146"><span class="at" title="'+data[i].jyjlXx+'">'+data[i].jyjlXx+'</span><input type="hidden" id="education_verify_178993151" value="0"></strong>'
                  			+'<span class="cl3 w140 at">'+xl+'</span></div><div class="clear"></div><span class="ed_icon"><em class="edi icons" id="education_edit_178993151" onclick="jyjlEdit(\''+data[i].jyjlId+'\')"></em><em class="del icons" id="education_delete_178993151" onclick="jyjlDel(\''+data[i].jyjlId+'\')"></em></span></div></div>');
          			}
        	
        		}
        	}else{
        		$("#education_complete").attr("class","right n");
        		$("#education").append('<div class="none icons" id="schoolaward_empty">完善教育经历，展现专业能力，让HR更了解你！</div> ');
        	}
	        	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
	        	//请求后台方法
	        	
	        	//回调函数中进行业务逻辑判断
	        	//如果没有数据，则展示以下内容---------------Start
	        	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
	        	//如果没有数据，则展示以下内容---------------End
	        	
	        	//alert($("#schoolaward").html(""));
        }
	});
	
}

//教育经历----------------------------------------------------------------结束
//校内荣誉----------------------------------------------------------------开始
function schoolaward_newClick(work){
	$(work).removeClass('add').addClass('unadd');
	$(work).attr("onclick","");
	$("#schoolaward").addClass('bd');
	$("#schoolaward").addClass('com');
	
	var xinzengForm='<form id="xnryForm"><input type="hidden" name="jlBbId" value="'+$("#jlBanbenId").val()+'"/><div class="" id="schoolaward_modify_"><div class="con">'
    +'<div class="c" float-index="false"><label>时间</label><i>*</i><div class="h30"><div class="sh">'
                +'<div class="txt pointer" id="bonus_time_calendar" float-on="false">'
                   +' <input class="ef" id="bonus_time_input" name="xnrySj" type="text" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})"  value=""></div></div></div>'
              +'<div class="err" id="bonus_time_warning" style="display:none"><em class="icons"></em></div></div>'
    +'<div class="h30"><div class="c c1"><label>奖项</label><i>*</i><div class="sh"><div class="txt"><input class="ef" name="xnryJx" maxlength="50" id="bonus_name" type="text" value=""></div> </div><div class="err" id="bonus_name_warning" style="display:none"><em class="icons"></em></div></div>'
        +'<div class="c c4"><label>级别</label><div class="sh"><div class="txt"><input class="ef" name="xnryJb" maxlength="50" id="bonus_class" type="text" value=""> </div></div><div class="err" id="bonus_class_warning" style="display:none"><em class="icons"></em></div></div></div></div>'
		  +'<div class="btnbox"><span class="p_but" id="schoolaward_save_" onclick="saveXnry();">保存</span><span class="p_but gray" id="schoolaward_cancel_" onclick="schoolaward_cancel_Click(this)">取消</span></div></div></form>';
	 var oldHtml=$("#schoolaward").html();//判断，原先是否有数据，有的话，取原值，（第1步）
	$("#schoolaward").html(xinzengForm);
	$("#schoolaward").append('');
	 $("#schoolaward").append(oldHtml);//判断，原先是否有数据，有的话，取原值，（第2步）
}
function saveXnry(){
	if(isNull($("input[name='xnrySj']").val())){
		alert("时间不能为空！！！");
		return false;
	}
	if(isNull($("input[name='xnryJx']").val())){
		alert("奖项不能为空！！！");
		return false;
	}
	/*if(isNull($("input[name='xnryJb']").val())){
		alert("级别不能为空！！！");
		return false;
	}*/
	$("#school_complete").attr("class","right y");
	$.ajax({
        type: "POST",
        async:false,
        url: "saveXnry",
        data:$("#xnryForm").serialize(),
        dataType: "json",
        success: function(data){
        	alert(data.message);
        	$("#jlBanbenId").val(data.xnry.jlBbId);
        	$('#schoolaward_new').removeClass('unadd').addClass('add');
        	$('#schoolaward_new').attr("onclick","schoolaward_newClick(this)");
        	$("#schoolaward").removeClass('bd');
        	$("#schoolaward").removeClass('com');
        	$("#schoolaward").siblings('.btnbox').remove();
        	$("#schoolaward").html("");
        	$.ajax({
                type: "POST",
                async:false,
                url: "findXnrylistByJlbbId",
                data:{jlbbId:$("#jlBanbenId").val()},
                dataType: "json",
                success: function(data){
                	if(data.length!=0){
                		for(var i=0;i<data.length;i++){
                		$("#schoolaward").append('<div class="bd" id="'+data[i].xnryId+'">'
                		+'<div class="con edit"><div class="sp"><span>'+data[i].xnrySj+'</span><strong class="w180 at" title="'+data[i].xnryJx+'">'+data[i].xnryJx+'</strong>'
                		+'<span class="cl3 w230 at" title="'+data[i].xnryJb+'">'+data[i].xnryJb+'</span> </div><span class="ed_icon"><em class="edi icons"  onclick="xnryEdit(\''+data[i].xnryId+'\')"></em><em class="del icons"  onclick="xnryDel(\''+data[i].xnryId+'\')"></em></span></div></div>');
                	
                		}
                	}else{
                		$("#schoolaward").append('<div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div> ');
                	}
		        	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
		        	//请求后台方法
		        	
		        	//回调函数中进行业务逻辑判断
		        	//如果没有数据，则展示以下内容---------------Start
		        	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
		        	//如果没有数据，则展示以下内容---------------End
		        	
		        	//alert($("#schoolaward").html(""));
                }
        	});
        }
	});
}

//校内荣誉修改方法
function xnryEdit(dataID){
	$.ajax({
        type: "POST",
        async:false,
        url: "findXnryById",
        data:{id:dataID},
        dataType: "json",
        success: function(data){
			//定义静态HTML，根据返回的JSON 套用
        	var editForm='<form id="xnryForm"><input type="hidden" name="xnryId" value="'+data.xnryId+'"/><input type="hidden" name="jlBbId" value="'+data.jlBbId+'"/><div class="" id="schoolaward_modify_"><div class="con">'
            +'<div class="c" float-index="false"><label>时间</label><i>*</i><div class="h30"><div class="sh">'
                        +'<div class="txt pointer" id="bonus_time_calendar" float-on="false">'
                           +' <input class="ef" id="bonus_time_input" name="xnrySj" type="text" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})"  value="'+data.xnrySj+'"></div></div></div>'
                      +'<div class="err" id="bonus_time_warning" style="display:none"><em class="icons"></em></div></div>'
            +'<div class="h30"><div class="c c1"><label>奖项</label><i>*</i><div class="sh"><div class="txt"><input class="ef" name="xnryJx" maxlength="50" id="bonus_name" type="text" value="'+data.xnryJx+'"></div> </div><div class="err" id="bonus_name_warning" style="display:none"><em class="icons"></em></div></div>'
                +'<div class="c c4"><label>级别</label><div class="sh"><div class="txt"><input class="ef" name="xnryJb" maxlength="50" id="bonus_class" type="text" value="'+data.xnryJb+'"> </div></div><div class="err" id="bonus_class_warning" style="display:none"><em class="icons"></em></div></div></div></div>'
        		  +'<div class="btnbox"><span class="p_but" id="schoolaward_save_" onclick="saveXnry();">保存</span><span class="p_but gray" id="schoolaward_cancel_" onclick="schoolaward_cancel_Click(this)">取消</span></div></div></form>';
				  $("#"+dataID).addClass('bd');
					$("#"+dataID).addClass('com');
					//展示出表单并填充后台数据
			$("#"+dataID).html(editForm);
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
	        	$('#schoolaward_new').removeClass('unadd').addClass('add');
	        	$('#schoolaward_new').attr("onclick","schoolaward_newClick(this)");
	        	$("#schoolaward").removeClass('bd');
	        	$("#schoolaward").removeClass('com');
	        	$("#schoolaward").siblings('.btnbox').remove();
	        	$("#schoolaward").html("");
	        	$.ajax({
	                type: "POST",
	                async:false,
	                url: "findXnrylistByJlbbId",
	                data:{jlbbId:$("#jlBanbenId").val()},
	                dataType: "json",
	                success: function(data){
	                	if(data.length!=0){
	                		$("#school_complete").attr("class","right y");
	                		for(var i=0;i<data.length;i++){
	                		$("#schoolaward").append('<div class="bd" id="'+data[i].xnryId+'">'
	                		+'<div class="con edit"><div class="sp"><span>'+data[i].xnrySj+'</span><strong class="w180 at" title="'+data[i].xnryJx+'">'+data[i].xnryJx+'</strong>'
	                		+'<span class="cl3 w230 at" title="'+data[i].xnryJb+'">'+data[i].xnryJb+'</span> </div><span class="ed_icon"><em class="edi icons"  onclick="xnryEdit(\''+data[i].xnryId+'\')"></em><em class="del icons"  onclick="xnryDel(\''+data[i].xnryId+'\')"></em></span></div></div>');
	                	
	                		}
	                	}else{
	                		$("#school_complete").attr("class","right n");
	                		$("#schoolaward").append('<div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div> ');
	                	}
			        	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
			        	//请求后台方法
			        	
			        	//回调函数中进行业务逻辑判断
			        	//如果没有数据，则展示以下内容---------------Start
			        	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
			        	//如果没有数据，则展示以下内容---------------End
			        	
			        	//alert($("#schoolaward").html(""));
	                }
	        	});
	        }
		});
	}
}

//校内荣誉取消
function schoolaward_cancel_Click(work) {
	$('#schoolaward_new').removeClass('unadd').addClass('add');
	$('#schoolaward_new').attr("onclick","schoolaward_newClick(this)");
	$("#schoolaward").removeClass('bd');
	$("#schoolaward").removeClass('com');
	$("#schoolaward").siblings('.btnbox').remove();
	$("#schoolaward").html("");
	$.ajax({
        type: "POST",
        async:false,
        url: "findXnrylistByJlbbId",
        data:{jlbbId:$("#jlBanbenId").val()},
        dataType: "json",
        success: function(data){
        	if(data.length!=0){
        		$("#school_complete").attr("class","right y");
        		for(var i=0;i<data.length;i++){
        		$("#schoolaward").append('<div class="bd" id="'+data[i].xnryId+'">'
        		+'<div class="con edit"><div class="sp"><span>'+data[i].xnrySj+'</span><strong class="w180 at" title="'+data[i].xnryJx+'">'+data[i].xnryJx+'</strong>'
        		+'<span class="cl3 w230 at" title="'+data[i].xnryJb+'">'+data[i].xnryJb+'</span> </div><span class="ed_icon"><em class="edi icons"  onclick="xnryEdit(\''+data[i].xnryId+'\')"></em><em class="del icons"  onclick="xnryDel(\''+data[i].xnryId+'\')"></em></span></div></div>');
        	
        		}
        	}else{
        		$("#school_complete").attr("class","right n");
        		$("#schoolaward").append('<div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div> ');
        	}
        	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
        	//请求后台方法
        	
        	//回调函数中进行业务逻辑判断
        	//如果没有数据，则展示以下内容---------------Start
        	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
        	//如果没有数据，则展示以下内容---------------End
        	
        	//alert($("#schoolaward").html(""));
        }
	});
	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
	//请求后台方法
	
	//回调函数中进行业务逻辑判断
	//如果没有数据，则展示以下内容---------------Start
	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
	//如果没有数据，则展示以下内容---------------End
	
	//alert($("#schoolaward").html(""));
	
}
//校内荣誉-----------------------------------------------------------结束


//校内职务------------------------------------------------------------开始
function schooljob_newClick(i){
	$(i).removeClass('add').addClass('unadd');
	$(i).attr("onclick","");
	$("#schooljob").addClass('bd');
	$("#schooljob").addClass('com');
	
	var xinzengForm='<form id="xnzwForm"><input type="hidden" name="jlBbId" value="'+$("#jlBanbenId").val()+'"/><div class="" id="schooljob_modify_"><div class="con"><div class="c" float-index="false">'
        +' <label>时间</label><i>*</i>'
        +' <div class="h30">'
             +'<div class="sh sx"><div class="txt pointer" id="work_timefrom_calendar" float-on="false"><input class="ef date_img" name="xnzwKssj" id="work_timefrom_input" type="text" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})" value=""></div></div><span class="dao">到</span><div class="sh sx"><div class="txt pointer" id="work_timeto_calendar" float-on="false"><input class="ef date_img" name="xnzwJssj" id="work_timeto_input" type="text" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})" value="至今"> </div></div> </div></div>'
     +'<div class="c"><label>职务</label><i>*</i><div class="h30"><div class="sh"><div class="txt"><input class="ef" name="xnzwZw" maxlength="50" id="prac_name" type="text" value=""></div></div><div class="err" id="prac_name_warning" style="display:none"><em class="icons"></em></div></div></div>'
     +'<div class="c"><label>职务描述</label><textarea name="xnzwZwms" maxlength="1000" id="prac_describe"  placeholder="描述在校期间所担任职位的主要工作内容及职责等"></textarea><div class="h30 clno"> <span class="frt" id="prac_describe_chars">0/1000 字</span> <div class="err" id="prac_describe_warning" style="display:none"><em class="icons"></em></div></div></div></div><div class="btnbox"> <span class="p_but" id="schooljob_save_" onclick="saveXnzw();">保存</span> <span class="p_but gray" id="schooljob_cancel_" onclick="schooljob_cancel_Click(this)">取消</span></div></div></form>';
	
	var oldHtml=$("#schooljob").html();//判断，原先是否有数据，有的话，取原值，（第1步）
	$("#schooljob").html(xinzengForm);
	$("#prac_describe").bind("input propertychange",function(event){
		  //alert($(this).val());
		  var $this = $(this).val(); 
		  if ($this.length > 1000) {  
			  $(this).val($this.substring(0, 1000));  
     }  
     $("#prac_describe_chars").text($(this).val().length+"/1000 字"); 
	});
	$("#schooljob").append('');
	$("#schooljob").append(oldHtml);//判断，原先是否有数据，有的话，取原值，（第2步）
}
function saveXnzw(){
	if(isNull($("input[name='xnzwKssj']").val())){
		alert("开始时间不能为空！！！");
		return false;
	}
	if(isNull($("input[name='xnzwJssj']").val())){
		alert("结束时间不能为空！！！");
		return false;
	}
	if(isNull($("input[name='xnryZw']").val())){
		alert("职务不能为空！！！");
		return false;
	}
	$("#school_complete").attr("class","right y");
	/*if(isNull($("input[name='xnryZwms']").val())){
		alert("职务描述不能为空！！！");
		return false;
	}*/
	$.ajax({
      type: "POST",
      async:false,
      url: "saveXnzw",
      data:$("#xnzwForm").serialize(),
      dataType: "json",
      success: function(data){
      	alert(data.message);
      	$("#jlBanbenId").val(data.xnzw.jlBbId);
      	$('#schooljob_new').removeClass('unadd').addClass('add');
      	$("#schooljob_new").attr("onclick","schooljob_newClick(this)");
      	$("#schooljob").removeClass('bd');
      	$("#schooljob").removeClass('com');
      	$("#schooljob").siblings('.btnbox').remove();
      	$("#schooljob").html("");
      	$.ajax({
              type: "POST",
              async:false,
              url: "findXnzwlistByJlbbId",
              data:{jlbbId:$("#jlBanbenId").val()},
              dataType: "json",
              success: function(data){
              	if(data.length!=0){
              		for(var i=0;i<data.length;i++){
              			$("#schooljob").append('<div class="bd" id="'+data[i].xnzwId+'">'
                  			    +'<div class="con edit"><div class="sp"><span>'+data[i].xnzwKssj+'-'+data[i].xnzwJssj+'</span><strong class="w430 at" title="团支书">'+data[i].xnzwZw+'</strong>'
                  		        +'</div><div class="e"><label>职务描述</label><i>：</i><div>'+data[i].xnzwZwms+'</div></div><div class="clear"></div><span class="ed_icon"><em class="edi icons" onclick="xnzwEdit(\''+data[i].xnzwId+'\')"></em><em class="del icons" onclick="xnzwDel(\''+data[i].xnzwId+'\')"></em></span> </div></div>');
              	
              		}
              	}else{
              		$("#schooljob").append('<div class="none icons" id="schoolaward_empty">完善校内职务，展现校园活动经验，让HR更了解你！</div> ');
              	}
		        	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
		        	//请求后台方法
		        	
		        	//回调函数中进行业务逻辑判断
		        	//如果没有数据，则展示以下内容---------------Start
		        	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
		        	//如果没有数据，则展示以下内容---------------End
		        	
		        	//alert($("#schoolaward").html(""));
              }
      	});
      }
	});
}

//校内职务修改方法
function xnzwEdit(dataID){
	$.ajax({
      type: "POST",
      async:false,
      url: "findXnzwById",
      data:{id:dataID},
      dataType: "json",
      success: function(data){
			//定义静态HTML，根据返回的JSON 套用
      	var editForm='<form id="xnzwForm"><input type="hidden" name="xnzwId" value="'+data.xnzwId+'"/><input type="hidden" name="jlBbId" value="'+data.jlBbId+'"/><div class="" id="schooljob_modify_"><div class="con"><div class="c" float-index="false">'
	         +' <label>时间</label><i>*</i>'
	         +' <div class="h30">'
             +'<div class="sh sx"><div class="txt pointer" id="work_timefrom_calendar" float-on="false"><input class="ef date_img" name="xnzwKssj" id="work_timefrom_input" type="text" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})" value="'+data.xnzwKssj+'"></div></div><span class="dao">到</span><div class="sh sx"><div class="txt pointer" id="work_timeto_calendar" float-on="false"><input class="ef date_img" name="xnzwJssj" id="work_timeto_input" type="text" onClick="WdatePicker({startDate: \'%y年%M月01日\', dateFmt: \'yyyy年MM月dd日\',maxDate:\'%y年%M月%d日\', alwaysUseStartDate: true})" value="'+data.xnzwJssj+'"> </div></div> </div></div>'
		     +'<div class="c"><label>职务</label><i>*</i><div class="h30"><div class="sh"><div class="txt"><input class="ef" name="xnzwZw" maxlength="50" id="prac_name" type="text" value="'+data.xnzwZw+'"></div></div><div class="err" id="prac_name_warning" style="display:none"><em class="icons"></em></div></div></div>'
		     +'<div class="c"><label>职务描述</label><textarea name="xnzwZwms" maxlength="1000" id="prac_describe"  placeholder="描述在校期间所担任职位的主要工作内容及职责等">'+data.xnzwZwms+'</textarea><div class="h30 clno"> <span class="frt" id="prac_describe_chars">'+data.xnzwZwms.length+'/1000 字</span> <div class="err" id="prac_describe_warning" style="display:none"><em class="icons"></em></div></div></div></div><div class="btnbox"> <span class="p_but" id="schooljob_save_" onclick="saveXnzw();">保存</span> <span class="p_but gray" id="schooljob_cancel_" onclick="schooljob_cancel_Click(this)">取消</span></div></div></form>';
      	$("#"+dataID).addClass('bd');
					$("#"+dataID).addClass('com');
					//展示出表单并填充后台数据
			$("#"+dataID).html(editForm);
			$("#prac_describe").bind("input propertychange",function(event){
				  //alert($(this).val());
				  var $this = $(this).val(); 
				  if ($this.length > 1000) {  
					  $(this).val($this.substring(0, 1000));  
		     }  
		     $("#prac_describe_chars").text($(this).val().length+"/1000 字"); 
			});
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
	      	$('#schooljob_new').removeClass('unadd').addClass('add');
	      	$("#schooljob_new").attr("onclick","schooljob_newClick(this)");
	      	$("#schooljob").removeClass('bd');
	      	$("#schooljob").removeClass('com');
	      	$("#schooljob").siblings('.btnbox').remove();
	      	$("#schooljob").html("");
	      	$.ajax({
	              type: "POST",
	              async:false,
	              url: "findXnzwlistByJlbbId",
	              data:{jlbbId:$("#jlBanbenId").val()},
	              dataType: "json",
	              success: function(data){
	              	if(data.length!=0){
	              		$("#school_complete").attr("class","right y");
	              		for(var i=0;i<data.length;i++){
	              			$("#schooljob").append('<div class="bd" id="'+data[i].xnzwId+'">'
	                  			    +'<div class="con edit"><div class="sp"><span>'+data[i].xnzwKssj+'-'+data[i].xnzwJssj+'</span><strong class="w430 at" title="团支书">'+data[i].xnzwZw+'</strong>'
	                  		        +'</div><div class="e"><label>职务描述</label><i>：</i><div>'+data[i].xnzwZwms+'</div></div><div class="clear"></div><span class="ed_icon"><em class="edi icons" onclick="xnzwEdit(\''+data[i].xnzwId+'\')"></em><em class="del icons" onclick="xnzwDel(\''+data[i].xnzwId+'\')"></em></span> </div></div>');
	              	
	              		}
	              	}else{
	              		$("#school_complete").attr("class","right n");
	              		$("#schooljob").append('<div class="none icons" id="schoolaward_empty">完善校内职务，展现校园活动经验，让HR更了解你！</div> ');
	              	}
			        	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
			        	//请求后台方法
			        	
			        	//回调函数中进行业务逻辑判断
			        	//如果没有数据，则展示以下内容---------------Start
			        	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
			        	//如果没有数据，则展示以下内容---------------End
			        	
			        	//alert($("#schoolaward").html(""));
	              }
	      	});
	      }
		});
	}
}

//校内职务取消
function schooljob_cancel_Click(work) {
	$('#schooljob_new').removeClass('unadd').addClass('add');
	$("#schooljob_new").attr("onclick","schooljob_newClick(this)");
	$("#schooljob").removeClass('bd');
	$("#schooljob").removeClass('com');
	$("#schooljob").siblings('.btnbox').remove();
	$("#schooljob").html("");
	$.ajax({
      type: "POST",
      async:false,
      url: "findXnzwlistByJlbbId",
      data:{jlbbId:$("#jlBanbenId").val()},
      dataType: "json",
      success: function(data){
      	if(data.length!=0){
      		$("#school_complete").attr("class","right y");
      		for(var i=0;i<data.length;i++){
      		$("#schooljob").append('<div class="bd" id="'+data[i].xnzwId+'">'
      			    +'<div class="con edit"><div class="sp"><span>'+data[i].xnzwKssj+'-'+data[i].xnzwJssj+'</span><strong class="w430 at" title="团支书">'+data[i].xnzwZw+'</strong>'
      		        +'</div><div class="e"><label>职务描述</label><i>：</i><div>'+data[i].xnzwZwms+'</div></div><div class="clear"></div><span class="ed_icon"><em class="edi icons" id="'+data[i].xnzwId+'" onclick="xnzwEdit(\''+data[i].xnzwId+'\')"></em><em class="del icons" id="'+data[i].xnzwId+'" onclick="xnzwDel(\''+data[i].xnzwId+'\')"></em></span> </div></div>');
      	
      		}
      	}else{
      		$("#school_complete").attr("class","right n");
      		$("#schooljob").append('<div class="none icons" id="schoolaward_empty">完善校内职务，展现学习能力，让HR更了解你！</div> ');
      	}
      	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
      	//请求后台方法
      	
      	//回调函数中进行业务逻辑判断
      	//如果没有数据，则展示以下内容---------------Start
      	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
      	//如果没有数据，则展示以下内容---------------End
      	
      	//alert($("#schoolaward").html(""));
      }
	});
	//请求后台，根据数据结果判断，如果没有数据则展示 以下HTML内容（被注释的部分），如果有数据，则展示数据
	//请求后台方法
	
	//回调函数中进行业务逻辑判断
	//如果没有数据，则展示以下内容---------------Start
	//$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
	//如果没有数据，则展示以下内容---------------End
	
	//alert($("#schoolaward").html(""));
	
}
/*function schooljob_newClick(i) {
	$(i).removeClass('add').addClass('unadd');
	$("#schooljob").addClass('bd');
	$("#schooljob").addClass('com');
	$("#schooljob").html('<div class="" id="schooljob_modify_"><div class="con"><div class="c" float-index="false">'
         +' <label>时间</label><i>*</i>'
         +' <div class="h30">'
              +'<div class="sh sx"><div class="txt pointer" id="work_timefrom_calendar" float-on="false"><input class="ef date_img" id="work_timefrom_input" type="date" value=""></div></div><span class="dao">到</span><div class="sh sx"><div class="txt pointer" id="work_timeto_calendar" float-on="false"><input class="ef date_img" id="work_timeto_input" type="date" value="至今"> </div></div> </div></div>'
         
      
      +'<div class="c"><label>职务</label><i>*</i><div class="h30"><div class="sh"><div class="txt"><input class="ef" maxlength="50" id="prac_name" type="text" value=""></div></div><div class="err" id="prac_name_warning" style="display:none"><em class="icons"></em></div></div></div>'
      +'<div class="c"><label>职务描述</label><textarea id="prac_describe"  placeholder="描述在校期间所担任职位的主要工作内容及职责等"></textarea><div class="h30 clno"> <span class="frt" id="prac_describe_chars">0/1000 字</span> <div class="err" id="prac_describe_warning" style="display:none"><em class="icons"></em></div></div></div></div>'
 );
	
	$("#schooljob").after('<div class="btnbox"> <span class="p_but" id="schooljob_save_" onclick="saveButtonClick(this);">保存</span> <span class="p_but gray" id="schooljob_cancel_" onclick="schooljob_cancel_Click(this)">取消</span></div></div>');	
}
//校内职务取消

function schooljob_cancel_Click(work) {
	$('#schooljob_new').removeClass('unadd').addClass('add');
	$("#schooljob").removeClass('bd');
	$("#schooljob").removeClass('com');
	$("#schooljob").siblings('.btnbox').remove();
	$("#schooljob").html('<div class="none icons" id="schooljob_empty">完善校内职务，展现校园活动经验，让HR更了解你！</div>');
}*/
//校内职务---------------------------------------------------结束

//居住地赋值
function jzdfz(i){
	$(".js_more em").on('click', function(){
		var dz_1 = $(this).html();
		$("#base_area_layer").hide();
		$("#layer_back_drop").hide();
		
		$("#base_area_input").val(dz_1);
});
	$(".js_more em").off('click',function(){
		alert("事件解除");
	});
}
//居住地显示
function base_area_click(int){
	$(".js_more em").unbind("click");
	$('#layer_back_drop').show();
		$('#layer_back_drop').addClass("layer_back_drop_class");
		$("#base_area_layer").show();
	jzdfz(int);
}
//户口国籍赋值
function hkgjfz(i){
	
	$(".js_more em").on('click', function(){
		var dz_1 = $(this).html();
		$("#base_area_layer").hide();
		$("#layer_back_drop").hide();
		
		$("#base_country_input").val(dz_1);
});
	
}
//户口国籍击显示
function base_country_click(int){
	$(".js_more em").unbind("click");
	$('#layer_back_drop').show();
		$('#layer_back_drop').addClass("layer_back_drop_class");
		$("#base_area_layer").show();
		hkgjfz(int);
}

//职能点击赋值
function znfz(i){
	$(".js_more em").on('click', function(){
		var dz_1 = $(this).html();
		$("#work_func_layer").hide();
		$("#layer_back_drop").hide();
		
		$("#work_zn_input").val(dz_1);
});
}
//职能点击显示
function work_func_click(int){
	$(".js_more em").unbind("click");
	$('#layer_back_drop').show();
		$('#layer_back_drop').addClass("layer_back_drop_class");
		$("#work_func_layer").show();
		
		znfz(int);
}

//行业点击赋值
function hyfz(i){
	$(".js_more em").on('click', function(){
		var dz_1 = $(this).html();
		$("#int_expectindustry_multiple_under_layer_id").hide();
		$("#layer_back_drop").hide();
		
		$("#int_hy_input").val(dz_1);
});
}

//行业点击显示

function work_industry_click(int){
	$(".js_more em").unbind("click");
	$('#layer_back_drop').show();
	$('#layer_back_drop').addClass("layer_back_drop_class");
	$("#int_expectindustry_multiple_under_layer_id").show();
	hyfz(int);
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
	$("#basedetail").html('<div class="head" id="Basic"> <div class="face"><img src="images/man.png" width="85" height="104" alt="头像"></div><div class="name ">王一</div><p class="at">现居住长春-南关区 │ 8年工作经验 │ 男 │ 29 岁 (1990/07/18) │ 目前正在找工作</p><div class="tab"> <span class="email icons at" title="3431877856@qq.com">3431877856@qq.com</span><span class="tel icons">15948700252</span></div> <div class="abox"><div class="mbox" onclick="showMoreClickEvent(this)"><span class="icons">更多展开</span><em class="icons"></em></div><div class="all"><div class="e e2 ef"><label>户口/国籍</label><i>：</i><div>宁波</div></div><div class="e e2"><label>婚姻状态</label><i>：</i><div>未婚</div></div><div class="e e2"><label>政治面貌</label><i>：</i><div>中共党员</div></div> <div class="clear"></div></div></div></div><span class="ed_icon_blue icons" id="basedetail_edit" onclick="editButtonClick(this)"></span>');
	$("#basedetail").addClass('top_wrap').removeClass('com');
	$(".face").removeClass('f2');
}

//性别点击
function base_sex_div(base){
	$(base).children('span').addClass('on').siblings('span').removeClass('on');
		
}


