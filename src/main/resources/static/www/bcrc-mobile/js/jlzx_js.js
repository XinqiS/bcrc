 $(function () {
	//简历中心-创建简历
	//左侧菜单浮动
	$(window).scroll(function () {
		var scroll_T = $(window).scrollTop();
		//var vh = $(window).scrollTop();
		if (scroll_T > 500) {
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
		
		$("#basedetail").html('<div class="head" id="Basic"><div class="face f2"><img id="base_avatar" src="images/man.png" width="85" height="104" alt="头像"><span class="esp" onclick="setAvatar();">修改</span>|<span id="base_avatar_delete" class="esp clb" style="cursor:default" "="" onclick="javascript:void(0);">删除</span></div>'
    +' <div class="cbox"><div class="h30"><div class="c c2"><label>姓名</label><i>*</i><div class="sh"><div class="txt"><input id="base_name" class="ef" maxlength="20" type="text" value="王一"></div></div><div class="err" id="base_name_warning" style="display:none"><em class="icons"></em></div></div>'
                +' <div class="c c4"><label>性别</label><i>*</i><div class="sh" id="base_sex_div" > <div class="txt pointer" id="base_csituation_list" float-on="false"><select class="ef"><option>男</option><option>女</option></select></div><div class="err" id="base_sex_warning" style="display:none"><em class="icons"></em></div></div></div></div>'
              +'<div class="h30"><div class="c c2" float-index="false"><label>出生日期</label><i>*</i><div class="sh"><div class="txt pointer" id="base_birthday_calendar" float-on="false"><input class="ef date_img" id="base_birthday_input" type="date"  value="1990-07-18"></div><input id="base_birthday" type="hidden" value="1990/07/18"></div></div>'
                +'<div class="c c4" float-index="false"><label>开始工作年份</label><i>*</i><div class="sh"><div class="txt pointer" id="base_workyear_calendar" float-on="false"><input class="ef date_img"  type="year" value="2011"> </div><input id="base_workyear" type="hidden" value="2011"></div></div></div>'
              +'<div class="h30" style="*position:relative;*z-index:2"><div class="c c2"><label>手机</label><i>*</i><div class="sh"><div class="txt pointer" name="11" ><input class="ef " id="tele" type="text"  value="15948700252"></div></div></div>'
               +' <div class="c c4" float-index="false"><label>求职状态</label><i>*</i><div class="sh"><div class="txt pointer" id="base_csituation_list" float-on="false"><select class="ef"><option>目前正在找工作</option><option>观望有好机会会考虑</option><option>我目前不想换工作</option></select></div><input id="base_csituation" type="hidden" value="0"></div></div></div>'
              +'<div class="h30" style="*position:relative;*z-index:1"><div class="c c2"><label>邮箱</label><i>*</i> <div class="sh"> <div class="txt pointer" ><input class="ef " id="email" type="text" value="3431877856@qq.com"></div></div></div>'
               +' <div class="c c4" float-index="false" id="base_area_index"><label>居住地</label><i>*</i><div class="sh"><div class="txt pointer" float-on="false" id="base_area_div"><input class="ef cursor" maxlength="35" type="text" id="base_area_input" placeholder="填写/选择" value="长春-南关区" pre_value="长春-南关区"><span class="ic i_block pointer" onclick="base_area_click2();" id="base_area_click" ></span><div class="ul u3" id="base_area_list" style="display:none"> </div></div><input class="ef" id="base_area" type="hidden" value="240202" pre_code="240202"></div></div></div></div>'
            +'<div class="abox"><div class="mbox" onclick="showMoreClickEvent(this)"> <span class="icons">更多展开</span> <em class="icons" style="display: block;"></em> </div><div class="all" ><div class="h30"><div class="c c1" float-index="false" id="base_country_index"><label>户口/国籍</label><div class="sh"><div class="txt pointer" float-on="false" id="base_country_div"><input class="ef cursor" maxlength="35" id="base_country_input" placeholder="填写/选择" type="text" value="" pre_value=""><span id="base_country_click" onclick="base_country_click(this)" class="ic i_block pointer"></span><div class="ul u3" id="base_country_list"> </div></div><input id="base_country" type="hidden" value="" pre_code=""></div></div>'
                  +'<div class="c c4" float-index="false"><label>婚姻状态</label><div class="sh"><div class="txt pointer" id="base_marriage_list" float-on="false"><select class="ef"><option>已婚</option><option>未婚</option><option>保密</option></select></div><input class="ef" id="base_marriage" type="hidden" value=""></div></div></div>'
               +' <div class="h30"><div class="c c1" float-index="false"><label>证件号</label><div class="sh sm"><div class="txt pointer" id="base_idtype_list" float-on="false"><select class="ef select2"><option>身份证</option><option>护照</option><option>军人证</option><option>港澳居民来往内地通行证</option><option>外国人永久居留身份证</option><option>其它</option></select></div><input class="ef" id="base_idtype" type="hidden" value="0"></div>'
                   +' <div class="sh sl"> <div class="txt"><input id="base_idcard" class="ef" maxlength="25" type="text" value=""></div> </div></div>'
                  +'<div class="c c4" float-index="false"> <label>政治面貌</label><div class="sh"><div class="txt pointer" id="base_politicsstatus_list" float-on="false"><select class="ef "><option>中共党员</option><option>共青团员</option><option>民主党派人士</option><option>无党派民主人士</option><option>无党派民主人士</option><option>普通公民</option> </select></div><input class="ef" id="base_politicsstatus" type="hidden" value=""></div></div></div>'
                +'<div class="h30"><div class="c c1" float-index="false"><label>其他联系方式</label><div class="sh sm"><div class="txt pointer" id="base_contacttype_list" float-on="false"><select class="ef select2"><option>家庭电话</option><option>公司电话</option><option>微信</option><option>QQ号</option></select></div><input class="ef" id="base_contacttype" type="hidden" value="00"></div><div class="sh sl"><div class="txt"><input class="ef" maxlength="20" id="base_othercontact" type="text" value=""></div></div></div>'
                  +'<div class="c c4"><label>身高</label> <div class="sh"><div class="txt"><input class="ef" maxlength="3" id="base_stature" type="text" value=""></div></div> cm </div></div>'
                +'<div class="h30"><div class="c c1"><label>家庭住址</label><div class="sh"><div class="txt"><input class="ef" maxlength="100" id="base_address" type="text" value=""></div></div></div>'
                  +'<div class="c c4"><label>邮编</label><div class="sh"><div class="txt"><input class="ef" maxlength="6" id="base_zipcode" type="text" value=""></div> </div></div></div></div></div></div><span class="ed_icon_blue icons" id="xxxx_btn" onclick="editButtonClick(this)"></span>'
					);
		$("#basedetail").addClass('com').removeClass('top_wrap');
		$(".face").addClass('f2');
	
		$(".head").append('<div class="btnbox"><span class="p_but" id="basedetail_save">保存</span><span class="p_but gray" id="basedetail_cancel" onclick="cancelButtonClick(this)">取消</span></div>');
		$(".name").remove();
		$(".at").remove();
		$(".tab").remove();
		$(".abox").show();
		$(".cbox").show();
		
		//jzdfz();
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
	$("#salary_edit").siblings('.bh_div').html('<div><div class="bd com">  <div class="con">    <div class="h30 h2">        <div class="c c3">          <label>目前年收入</label>   <div class="sh sl"><div class="txt"> <input class="ef" maxlength="6" id="sal_salary" type="text" value="">                     </div>    </div>   万元 </div></div></div> <div class="btnbox"> <span class="p_but" id="salary_save" onclick="saveButtonClick(this);">保存</span><span class="p_but gray" id="salary_cancel" onclick="salaryButtonClick(this)">取消</span> </div> </div></div>');
	$("#salary_edit").siblings('.icons').remove();
	$("#salary").removeClass('m1');
	$("#salary").removeClass('top_wrap').addClass('b2');
	$(this).remove();
}
//年收入取消

function salaryButtonClick(o) {
	$("#salary").addClass('m1');
	$("#salary").removeClass('b2').addClass('top_wrap');
	$("#salary").html('<div class="hd"> <strong class="icons"><em class="e0 icons"></em>目前年收入</strong> <span class="f16"></span> <span class="f12">（包含基本工资、补贴、奖金、股权收益等）</span> </div><div class="bh_div"><div class="none icons">完善年收入情况，让HR更了解你！</div></div><span class="ed_icon_blue icons" onclick="salary_editButtonClick(this)" id="salary_edit"></span>');
	

}




//详细信息点击显示 
function editButtonClick(p_oEvent) {
	
	$("#basedetail").html('<div class="head" id="Basic"><div class="face f2"><img id="base_avatar" src="images/man.png" width="85" height="104" alt="头像"><span class="esp" onclick="setAvatar();">修改</span>|<span id="base_avatar_delete" class="esp clb" style="cursor:default" "="" onclick="javascript:void(0);">删除</span></div>'
    +' <div class="cbox"><div class="h30"><div class="c c2"><label>姓名</label><i>*</i><div class="sh"><div class="txt"><input id="base_name" class="ef" maxlength="20" type="text" value="王一"></div></div><div class="err" id="base_name_warning" style="display:none"><em class="icons"></em></div></div>'
                +' <div class="c c4"><label>性别</label><i>*</i><div class="sh" id="base_sex_div"> <div class="txt pointer" id="base_csituation_list" float-on="false"><select class="ef"><option>男</option><option>女</option></select></div><div class="err" id="base_sex_warning" style="display:none"><em class="icons"></em></div></div></div></div>'
              +'<div class="h30"><div class="c c2" float-index="false"><label>出生日期</label><i>*</i><div class="sh"><div class="txt pointer" id="base_birthday_calendar" float-on="false"><input class="ef date_img" id="base_birthday_input" type="date"  value="1990-07-18"></div><input id="base_birthday" type="hidden" value="1990/07/18"></div></div>'
                +'<div class="c c4" float-index="false"><label>开始工作年份</label><i>*</i><div class="sh"><div class="txt pointer" id="base_workyear_calendar" float-on="false"><input class="ef date_img"  type="year" value="2011"> </div><input id="base_workyear" type="hidden" value="2011"></div></div></div>'
              +'<div class="h30" style="*position:relative;*z-index:2"><div class="c c2"><label>手机</label><i>*</i><div class="sh"><div class="txt pointer" name="11" ><input class="ef " id="tele" type="text"  value="15948700252"></div></div></div>'
               +' <div class="c c4" float-index="false"><label>求职状态</label><i>*</i><div class="sh"><div class="txt pointer" id="base_csituation_list" float-on="false"><select class="ef"><option>目前正在找工作</option><option>观望有好机会会考虑</option><option>我目前不想换工作</option></select></div><input id="base_csituation" type="hidden" value="0"></div></div></div>'
              +'<div class="h30" style="*position:relative;*z-index:1"><div class="c c2"><label>邮箱</label><i>*</i> <div class="sh"> <div class="txt pointer" ><input class="ef " id="email" type="text" value="3431877856@qq.com"></div></div></div>'
               +' <div class="c c4" float-index="false" id="base_area_index"><label>居住地</label><i>*</i><div class="sh"><div class="txt pointer" float-on="false" id="base_area_div"><input class="ef cursor" maxlength="35" type="text" id="base_area_input" placeholder="填写/选择" value="长春-南关区" pre_value="长春-南关区"><span class="ic i_block pointer" id="base_area_click" onclick="base_area_click(this)"></span><div class="ul u3" id="base_area_list" style="display:none"> </div></div><input class="ef" id="base_area" type="hidden" value="240202" pre_code="240202"></div></div></div></div>'
            +'<div class="abox"><div class="mbox" onclick="showMoreClickEvent(this)"> <span class="icons">更多展开</span> <em class="icons" style="display: block;"></em> </div><div class="all" ><div class="h30"><div class="c c1" float-index="false" id="base_country_index"><label>户口/国籍</label><div class="sh"><div class="txt pointer" float-on="false" id="base_country_div"><input class="ef cursor" maxlength="35" id="base_country_input" placeholder="填写/选择" type="text" value="" pre_value=""><span id="base_country_click" onclick="base_country_click(this)" class="ic i_block pointer"></span><div class="ul u3" id="base_country_list"> </div></div><input id="base_country" type="hidden" value="" pre_code=""></div></div>'
                  +'<div class="c c4" float-index="false"><label>婚姻状态</label><div class="sh"><div class="txt pointer" id="base_marriage_list" float-on="false"><select class="ef"><option>已婚</option><option>未婚</option><option>保密</option></select></div><input class="ef" id="base_marriage" type="hidden" value=""></div></div></div>'
               +' <div class="h30"><div class="c c1" float-index="false"><label>证件号</label><div class="sh sm"><div class="txt pointer" id="base_idtype_list" float-on="false"><select class="ef select2"><option>身份证</option><option>护照</option><option>军人证</option><option>港澳居民来往内地通行证</option><option>外国人永久居留身份证</option><option>其它</option></select></div><input class="ef" id="base_idtype" type="hidden" value="0"></div>'
                   +' <div class="sh sl"> <div class="txt"><input id="base_idcard" class="ef" maxlength="25" type="text" value=""></div> </div></div>'
                  +'<div class="c c4" float-index="false"> <label>政治面貌</label><div class="sh"><div class="txt pointer" id="base_politicsstatus_list" float-on="false"><select class="ef "><option>中共党员</option><option>共青团员</option><option>民主党派人士</option><option>无党派民主人士</option><option>无党派民主人士</option><option>普通公民</option> </select></div><input class="ef" id="base_politicsstatus" type="hidden" value=""></div></div></div>'
                +'<div class="h30"><div class="c c1" float-index="false"><label>其他联系方式</label><div class="sh sm"><div class="txt pointer" id="base_contacttype_list" float-on="false"><select class="ef select2"><option>家庭电话</option><option>公司电话</option><option>微信</option><option>QQ号</option></select></div><input class="ef" id="base_contacttype" type="hidden" value="00"></div><div class="sh sl"><div class="txt"><input class="ef" maxlength="20" id="base_othercontact" type="text" value=""></div></div></div>'
                  +'<div class="c c4"><label>身高</label> <div class="sh"><div class="txt"><input class="ef" maxlength="3" id="base_stature" type="text" value=""></div></div> cm </div></div>'
                +'<div class="h30"><div class="c c1"><label>家庭住址</label><div class="sh"><div class="txt"><input class="ef" maxlength="100" id="base_address" type="text" value=""></div></div></div>'
                  +'<div class="c c4"><label>邮编</label><div class="sh"><div class="txt"><input class="ef" maxlength="6" id="base_zipcode" type="text" value=""></div> </div></div></div></div></div></div><span class="ed_icon_blue icons" id="xxxx_btn" onclick="editButtonClick(this)"></span>'
					);
		$("#basedetail").addClass('com').removeClass('top_wrap');
		$(".face").addClass('f2');
	
		$(".head").append('<div class="btnbox"><span class="p_but" id="basedetail_save">保存</span><span class="p_but gray" id="basedetail_cancel" onclick="cancelButtonClick(this)">取消</span></div>');
		$(".name").remove();
		$(".at").remove();
		$(".tab").remove();
		$(".abox").show();
		$(".cbox").show();
	//jzdfz();
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

	$("#basedetail_cancel").parents('.box').addClass('top_wrap').removeClass('com');
	$(".face").removeClass('f2');
	//$(".face").addClass('top_wrap');
	$(".btnbox").remove();
	$(".cbox").hide();
	$(".abox").hide();
	$(".face").html('<img src="images/man.png" width="85" height="104" alt="头像">');
	$(".face").after('<div class="name ">王一</div><p class="at">现居住长春-南关区 │ 8年工作经验 │ 男 │ 29 岁 (1990/07/18) │ 目前正在找工作</p><div class="tab"> <span class="email icons at" title="3431877856@qq.com">3431877856@qq.com</span> <span class="tel icons">15948700252</span> </div>');
}




//求职意向增加
function intention_editButtonClick() {
	$("#intention_edit").siblings('.bh_div2').html('<div> <div class="bd com">  <div class="con"> ' 
	+ ' <div class="c" float-index="false">  <label>期望薪资</label><i>*</i><div class="h30">    <div class="sh "><div class="txt pointer" float-on="false" id="int_expectarea_div"><input class="ef" type="text"  placeholder="请填写期望薪资" input-type="selectionlist"></div></div>  </div></div>'
	+'<div class="c" float-index="false" id="int_expectarea_index">                <label>地点</label><i>*</i><div class="h30">    <div class="sh "><div class="txt pointer" id="int_expectarea_under"> <input class="ef" type="text"  value="" placeholder="请填写地点"></div> </div></div></div>'       
	+'<div class="c" float-index="false" id="int_expectarea_index">                <label>职能</label><i>*</i><div class="h30">    <div class="sh"><div class="txt pointer" float-on="false" id="work_func_div"><input id="work_func_input" placeholder="填写/选择" class="ef cursor" type="text" value="" pre_value=""> <span class="ic i_block pointer" id="work_func_click" onclick="work_func_click(this)"></span><div class="ul u3" id="work_func_list"> </div></div><input id="work_func" type="hidden" value="" pre_code=""> </div></div></div>'          
	+'<div class="c" float-index="false" id="int_position_index"><label>职位<i>*</i></label>                <div class="h30"><div class="sh"><div class="txt" float-on="false" id="int_position_div"> <input class="ef" id="int_position" maxlength="50" type="text" value="">  <div class="ul u3" id="int_position_list"> </div></div> </div><div class="err" id="int_position_warning" style="display:none"><em class="icons"></em></div></div></div>'
	+'<div class="c" float-index="false" id="int_expectindustry_index"><label>行业</label><div class="tbox" id="int_expectindustry_under"><div class="clear"></div></div><div class="h30"><div class="sh"><div class="txt pointer" float-on="false" id="int_expectindustry_div"><input placeholder="填写/选择" class="ef cursor" id="int_expectindustry_input" type="text" maxlength="24"> <span class="ic i_block pointer" onclick="work_industry_click(this)" id="int_expectindustry_click"></span><div class="ul u3" id="int_expectindustry_list"></div></div><input id="int_expectindustry" type="hidden" value=""></div><div class="err" id="int_expectindustry_warning" style="display:none"><em class="icons"></em></div></div></div>'
	+'<div class="c" float-index="false" id="int_resumekey_index"> <label>个人标签</label><div class="tbox" id="int_resumekey_under"><div class="clear"></div></div><div class="h30">  <div class="sh"><div class="txt pointer" float-on="false" id="int_resumekey_div"><input class="ef cursor" maxlength="24" id="int_resumekey_input" onkeyup="showbutton(event)" type="text" placeholder="职业/技能/行业相关的关键词" value=""><span class="ic pointer" id="int_resumekey_add" style="display:none">添加</span><div class="ul u3" id="int_resumekey_list"> </div></div><input id="int_resumekey" type="hidden" value=""> </div> <div class="err" id="int_resumekey_warning" style="display:none"><em class="icons"></em></div> <span class="f12">限10个，每个词不超过12个中文或24个英文</span> </div>            </div>'
	+'<div class="c"><label>自我评价</label><textarea placeholder="介绍自己，说明自己的最大优势，让企业看到你的闪光点" id="int_selfintro"></textarea> </div><div class="h30">                    <span class="frt" id="int_selfintro_chars">0/1000 字</span> </div>'
	+'<div class="h30"><div class="c c3" float-index="false" style=""><label>到岗时间</label><div class="sh"><div class="txt pointer" id="int_entrytime_list" float-on="false"> <select class="ef select3"><option>随时</option><option>1周内</option><option>1个月内</option><option>3个月内</option><option>待定</option></select><span class="ic i_arrow"></span></div></div> </div><div class="c c3" float-index="false"><label>工作类型</label><div class="sh"><div class="txt pointer" id="int_seektype_list" float-on="false"> <select class="ef select3"><option>全职</option><option>兼职</option><option>实习</option></select><span class="ic i_arrow"></span></div><input id="int_seektype" type="hidden" value="0"></div></div></div> '
	+'<div class="btnbox"> <span class="p_but" id="intention_save" onclick="saveButtonClick(this);">保存</span> <span class="p_but gray" id="intention_cancel" onclick="intentionButtonClick(this)">取消</span></div></div></div>');
	$("#intention_edit").siblings('.icons').remove();
	$("#intention").removeClass('m1');
	$("#intention").removeClass('top_wrap').addClass('b2');
	$(this).remove();
}
//求职意向取消

function intentionButtonClick(o) {
	$("#intention").addClass('m1');
	$("#intention").removeClass('b2').addClass('top_wrap');
	$(".bh_div2").html('<div class="bd"><div class="con"> <div class="e"><label>工作类型</label><i>：</i><div>全职</div></div><div class="clear"></div></div></div>');
	$(".bh_div2").after('<span class="ed_icon_blue icons" id="salary_edit"></span>');

}
//工作经验增加
function workButtonClick(work) {
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
}
//教育经历增加
function EducationButtonClick(education) {
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
}
//校内荣誉增加
function schoolaward_newClick(work) {
	$(work).removeClass('add').addClass('unadd');
	$("#schoolaward").addClass('bd');
	$("#schoolaward").addClass('com');
	$("#schoolaward").html('<div class="" id="schoolaward_modify_"><div class="con">'
        +'<div class="c" float-index="false"><label>时间</label><i>*</i><div class="h30"><div class="sh">'
                    +'<div class="txt pointer" id="bonus_time_calendar" float-on="false">'
                       +' <input class="ef" id="bonus_time_input" type="date"  value=""></div></div></div>'
                  +'<div class="err" id="bonus_time_warning" style="display:none"><em class="icons"></em></div></div>'
        +'<div class="h30"><div class="c c1"><label>奖项</label><i>*</i><div class="sh"><div class="txt"><input class="ef" maxlength="50" id="bonus_name" type="text" value=""></div> </div><div class="err" id="bonus_name_warning" style="display:none"><em class="icons"></em></div></div>'
            +'<div class="c c4"><label>级别</label><div class="sh"><div class="txt"><input class="ef" maxlength="50" id="bonus_class" type="text" value=""> </div></div><div class="err" id="bonus_class_warning" style="display:none"><em class="icons"></em></div></div></div></div>'
    +'<div class="btnbox"><span class="p_but" id="schoolaward_save_" onclick="saveButtonClick(this);">保存</span><span class="p_but gray" id="schoolaward_cancel_" onclick="schoolaward_cancel_Click(this)">取消</span></div></div>');
	
	
}
//校内荣誉取消

function schoolaward_cancel_Click(work) {
	$('#schoolaward_new').removeClass('unadd').addClass('add');
	$("#schoolaward").removeClass('bd');
	$("#schoolaward").removeClass('com');
//	$("#schoolaward").siblings('.btnbox').remove();
	$("#schoolaward").html(' <div class="none icons" id="schoolaward_empty">完善校内荣誉，展现学习能力，让HR更了解你！</div>');
}
//校内职务增加
function schooljob_newClick(i) {
	$(i).removeClass('add').addClass('unadd');
	$("#schooljob").addClass('bd');
	$("#schooljob").addClass('com');
	$("#schooljob").html('<div class="" id="schooljob_modify_"><div class="con"><div class="c" float-index="false">'
           +' <label>时间</label><i>*</i>'
           +' <div class="h30">'
                +'<div class="sh sx"><div class="txt pointer" id="work_timefrom_calendar" float-on="false"><input class="ef date_img" id="work_timefrom_input" type="date" value=""></div></div><span class="dao">到</span><div class="sh sx"><div class="txt pointer" id="work_timeto_calendar" float-on="false"><input class="ef date_img" id="work_timeto_input" type="date" value="至今"> </div></div> </div></div>'
           
        
        +'<div class="c"><label>职务</label><i>*</i><div class="h30"><div class="sh"><div class="txt"><input class="ef" maxlength="50" id="prac_name" type="text" value=""></div></div><div class="err" id="prac_name_warning" style="display:none"><em class="icons"></em></div></div></div>'
        +'<div class="c"><label>职务描述</label><textarea id="prac_describe"  placeholder="描述在校期间所担任职位的主要工作内容及职责等"></textarea><div class="h30 clno"> <span class="frt" id="prac_describe_chars">0/1000 字</span> <div class="err" id="prac_describe_warning" style="display:none"><em class="icons"></em></div></div></div></div>'
   +' <div class="btnbox"> <span class="p_but" id="schooljob_save_" onclick="saveButtonClick(this);">保存</span> <span class="p_but gray" id="schooljob_cancel_" onclick="schooljob_cancel_Click(this)">取消</span></div></div>');
	
	
}
//校内职务取消

function schooljob_cancel_Click(work) {
	$('#schooljob_new').removeClass('unadd').addClass('add');
	$("#schooljob").removeClass('bd');
	$("#schooljob").removeClass('com');
//	$("#schoolaward").siblings('.btnbox').remove();
	$("#schooljob").html('<div class="none icons" id="schooljob_empty">完善校内职务，展现校园活动经验，让HR更了解你！</div>');
}
//技能特长增加
function skilllanguage_newButtonClick(work) {
	$(work).removeClass('add').addClass('unadd');
	$("#Skills_div").addClass('bd');
	$("#Skills_div").addClass('com');
	$("#Skills_div").html('<textarea placeholder="请填写技能特长"></textarea>'				  
   );
	$("#Skills_div").after('<div class="btnbox"><span class="p_but" id="schooljob_save_" onclick="saveButtonClick(this);">保存</span><span class="p_but gray" id="schooljob_cancel_" onclick="skilltrain_cancel_ButtonClick(this)">取消</span></div>'				  
   );
}
//技能特长取消

function skilltrain_cancel_ButtonClick(work) {
	$('#skilllanguage_new').removeClass('unadd').addClass('add');
	$("#Skills_div").removeClass('bd');
	$("#Skills_div").removeClass('com');
	$("#Skills_div").siblings('.btnbox').remove();
	$("#Skills_div").html(' <div class="tit"> <span>校内荣誉</span></div>'
          +'<div id="schoolaward">'
           +'<div class="none icons">完善技能特长能力，让HR更了解你！</div>'
          +'</div>');
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
	$("#basedetail").html('<div class="head" id="Basic"> <div class="face"><img src="images/man.png" width="85" height="104" alt="头像"></div><div class="name ">王一</div><p class="at">现居住长春-南关区 │ 8年工作经验 │ 男 │ 29 岁 (1990/07/18) │ 目前正在找工作</p><div class="tab"> <span class="email icons at" title="3431877856@qq.com">3431877856@qq.com</span><span class="tel icons">15948700252</span></div> <div class="abox"><div class="mbox" onclick="showMoreClickEvent(this)"><span class="icons">更多展开</span><em class="icons"></em></div><div class="all"><div class="e e2 ef"><label>户口/国籍</label><i>：</i><div>宁波</div></div><div class="e e2"><label>婚姻状态</label><i>：</i><div>未婚</div></div><div class="e e2"><label>政治面貌</label><i>：</i><div>中共党员</div></div> <div class="clear"></div></div></div></div><span class="ed_icon_blue icons" id="basedetail_edit" onclick="editButtonClick(this)"></span>');
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


