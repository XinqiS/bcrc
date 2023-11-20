var domain = $_CONFIG.domain;
$(document).ready(function () {
	var c = $("#yearfrom").val(),
		a = $("#monthfrom").val(),
		b = $("#yearto").val(),
		d = $("#monthto").val();
	if (b == "") {
		$("#graduationmonth").hide();
		$("#leavemonth").hide();
		$("#projectmonthto").hide()
	} else {
		$("#graduationmonth").show();
		$("#leavemonth").show();
		$("#projectmonthto").show()
	}
	$("#sex span.rdo").click(function () {
		if ($(this).hasClass("on")) {
			return false
		} else {
			$(this).toggleClass("on");
			$(this).siblings().removeClass("on")
		}
	});
	$("#mobilecountry span.rdo").click(function () {
		if ($(this).hasClass("on")) {
			return false
		} else {
			$(this).toggleClass("on");
			$(this).siblings().removeClass("on")
		}
	});
	$("#isoversea span.rdo").click(function () {
		if ($(this).hasClass("on")) {
			return false
		} else {
			$(this).toggleClass("on");
			$(this).siblings().removeClass("on")
		}
	});
	$("#isfulltime").click(function () {
		$(this).toggleClass("on")
	});
	$("#majorname").click(function () {
		$("#err_majorname").hide();
		return false
	});
	$("#overseawork span.rdo").click(function () {
		if ($(this).hasClass("on")) {
			return false
		} else {
			$(this).toggleClass("on");
			$(this).siblings().removeClass("on")
		}
	});
	$("#worktype span.rdo").click(function () {
		if ($(this).hasClass("on")) {
			return false
		} else {
			$(this).toggleClass("on");
			$(this).siblings().removeClass("on")
		}
	});
//	$("#workzwld span.rdo").click(function () {
//		if ($(this).hasClass("on")) {
//			return false
//		} else {
//			$(this).toggleClass("on");
//			//$(this).siblings().removeClass("on")
//		}
//	});
	$("#funtypename").click(function () {
		$("#err_funtypedesc").hide();
		return false
	});
	$("#areaname").click(function () {
		$("#err_areaname").hide();
		return false
	});
	$("#salarytype").change(function () {
		var e = $(this).val();
		$(".xx").hide();
		$("#sal" + e).show()
	});
	$("#salarytype").find("option").each(function () {
		if ($(this).attr("selected") == true) {
			v = $(this).val();
			$("#sal" + v).show()
		}
	});
	$("#yearsal,#monthsal").change(function () {
		if ($(this).val() == "") {
			$(this).prev().addClass("c_tdefault")
		}
	});
	$(".refreshrsm").click(function () {
		var e = $(this).attr("value");
		refreshRsm(e);
		return false
	});
	$(".fast").click(function () {
		var e = $(this).attr("value");
		setFastRsm(e, this);
		return false
	});
	$(".delrsm").click(function () {
		var e = $(this).parent().attr("value");
		$(".aname").each(function () {
			if ($(this).attr("value") == e) {
				var f = $(this).html();
				delResume(e, f)
			}
		//var e = $(this).parent().attr("value");
		
		});
		return false
	});
	$(".showOpen").click(function () {
		var f = $(this).attr("value");
		var e = $(this).parent().attr("value");
		$(".aname").each(function () {
			if ($(this).attr("value") == e) {
				var g = $(this).html();
				showOpen(e, g, f);
				return false
			}
		});
		return false
	});
	$(".copyrsm").click(function () {
		$("#pageWp").hide();
		$("#copyrsm").show();
		var e = $(this).parent().attr("value");
		$("#copyUserid").val(e);
		return false
	});
	$("#copyback").click(function () {
		$("#copyrsm").hide();
		$("#pageWp").show();
		return false
	});
	$("#confirmCopy").click(function () {
		confirmCopyResume()
	});
	$("#copyinfo .e").click(function () {
		if (!$(this).hasClass("off")) {
			$(this).toggleClass("on");
			return false
		}
	});
	$("#editrsmname,#srsmname").click(function () {
		$("#changersmname").show().addClass("changeName");
		shadow.show();
		$("body").addClass("resumeShow");
		return false
	});
	$("#backtomyrsm").click(function () {
		location.href = domain + "resume/myresume.php"
	});
	$("#bday").blur(function () {
		var e = $("#bday").val();
		$("#bday").siblings().html(e);
		return false
	});
	$("#jsfbrq").blur(function () {
		var e = $("#jsfbrq").val();
		$("#jsfbrq").siblings().html(e);
		return false
	});
	$(".changeinfo .back").click(function () {
		$(".changeinfo").hide();
		$(".phone,.email,.pwd").hide();
		$("#pageContent").show();
		return false
	});
	$("#saveresumeone").click(function () {
		saveResumeOne()
	});
	$("#entrancetime").blur(function () {
		var e = $("#entrancetime").val();
		$("#entrancetime").siblings().html(e);
		return false
	});
	$("#graduationtime").blur(function () {
		var e = $("#graduationtime").val();
		$("#graduationtime").siblings().html(e);
		return false
	});
	$("#saveresumetwo").click(function () {
		if ($_CONFIG.template == "resume/eduexp" && $("#eduid").val()) {
			var e = $("#isverify").val();
			if (e == "1") {
				pop.ini(["该学历为“已认证学历”，保存后将失去认证标识。确定要保存么？", "保存教育经历"], [
					["取消", "确认"],
					["pop.close()", "pop.close();saveResumeTwo();"]
				])
			} else {
				saveResumeTwo()
			}
		} else {
			saveResumeTwo()
		}
		return false
	});
	$("#saveresumefour").click(function () {
		saveResumeFour()
	});
	$("#addwork").click(function () {
		location.href = $_CONFIG.domain + "resume/workexp.php?userid=" + $(this).attr("value")
	});
	$("#addedu").click(function () {
		location.href = $_CONFIG.domain + "resume/eduexp.php?userid=" + $(this).attr("value")
	});
	$("#addpro").click(function () {
		location.href = $_CONFIG.domain + "resume/proexpedit.php?userid=" + $(this).attr("value")
	})
});

function showReginfo(c) {
	if ($(".calendar ").css("display") == "block") {
		$(".calendar ").hide()
	}
	$("#pageContent").hide();
	$(".changeinfo").show();
	$("." + c).show();
	if (c == "email") {
		if (!$("#ioldemail")[0]) {
			var b = $("#baseinfoemail").attr("value");
			var a = '<div class="i" id="ioldemail"><div class="txt"><input type="text" placeholder="' + b + '"  class="old_email" disabled/></div></div>';
			$("#emailcontent").prepend(a)
		}
	}
	return false
}

function saveResumeOne() {
	var redirect = $("#redirect").attr("value");
	if (redirect) {
		storage.json_set("redirect", {
			ret_url: redirect
		})
	}
	$("#saveresumeone").attr("disabled", "disabled");
	var cname = filterTitle($("#cname")),
		sex = $("#sex span.on").attr("value"),
		bday = $("#bday").text(),
		mobilephone = filterTitle($("#mobilephone")),
		mpcountry = $("#mpverifystatus").val(),
		situation = $("#situation")[0] ? $("#situation").val() : "0",
		area = $("#jobarea").val(),
		workyear = $("#workyear")[0] ? $("#workyear").val() : "",
		idtype = $("#idtype")[0] ? $("#idtype").val() : "",
		idcard = $("#idcard")[0] ? filterTitle($("#idcard")) : "",
		yearsalary = filterTitle($("#yearsalary"));
	basesalary = filterTitle($("#basesalary"));
	allowance = filterTitle($("#allowance"));
	bonus = filterTitle($("#bonus"));
	stock = filterTitle($("#stock"));
	hukou = $("#hukou")[0] ? $("#hukou").val() : "", marriage = $("#marriage")[0] ? $("#marriage").val() : "", rsmid = $("#rsmid").val(), stature = $("#stature")[0] ? $("#stature").val() : "", address = $("#address")[0] ? $("#address").val() : "";
	var error = false;
	if (filterTitle($("#is_verify")) == 1) {
		mobilephone = filterTitle($("#inputphone"))
	}
	if (!/^1[3456789]{1,1}[0-9]{9,9}$/.test(mobilephone) && filterTitle($(this)).length <= 11) {
		var mpcountry = "000"
	} else {
		var mpcountry = "086"
	}
	if (typeof (mpcountry) == "undefined") {
		mpcountry = "086"
	}
	if ($("#baseinfoemail").attr("type") == "text") {
		var email = filterTitle($("#baseinfoemail"))
	} else {
		var email = ""
	}
	if (yearsalary != "" && !checkSalary(yearsalary)) {
		pop.ini([
			["精确到小数点后两位，不超过999.99", "warn"]
		], ["确定", "focusElement($('#yearsalary'))"]);
		inputPrompt($("#yearsalary"), "精确到小数点后两位，不超过999.99");
		error = true
	}
	if (basesalary != "" && !checkSalary(basesalary)) {
		pop.ini([
			["精确到小数点后两位，不超过999.99", "warn"]
		], ["确定", "focusElement($('#basesalary'))"]);
		inputPrompt($("#basesalary"), "精确到小数点后两位，不超过999.99");
		error = true
	}
	if (allowance != "" && !checkSalary(allowance)) {
		pop.ini([
			["精确到小数点后两位，不超过999.99", "warn"]
		], ["确定", "focusElement($('#allowance'))"]);
		inputPrompt($("#allowance"), "精确到小数点后两位，不超过999.99");
		error = true
	}
	if (bonus != "" && !checkSalary(bonus)) {
		pop.ini([
			["精确到小数点后两位，不超过999.99", "warn"]
		], ["确定", "focusElement($('#bonus'))"]);
		inputPrompt($("#bonus"), "精确到小数点后两位，不超过999.99");
		error = true
	}
	if (stock != "" && !checkSalary(stock)) {
		pop.ini([
			["精确到小数点后两位，不超过999.99", "warn"]
		], ["确定", "focusElement($('#stock'))"]);
		inputPrompt($("#stock"), "精确到小数点后两位，不超过999.99");
		error = true
	}
	if (error == true) {
		$("#saveresumeone").removeAttr("disabled");
		return false
	}
	var param = {
		cname: cname,
		sex: sex,
		bday: bday,
		mobilephone: mobilephone,
		mpcountry: mpcountry,
		situation: situation,
		area: area,
		workyear: workyear,
		idtype: idtype,
		idcard: idcard,
		email: email,
		yearsalary: yearsalary,
		basesalary: basesalary,
		allowance: allowance,
		bonus: bonus,
		stock: stock,
		hukou: hukou,
		marriage: marriage,
		rsmid: rsmid,
		stature: stature,
		address: address
	};
	var url = $_CONFIG.domain + "ajax/resume/baseinfo.ajax.php";
	var result = ajaxRequest(url, param, "post", "json");
	if (result.url && result.status == 0) {
		if ($_CONFIG.template == "resume/guid1") {
			_History.unbind()
		}
		location.href = result.url;
		return false
	}
	if (result.status == 1) {
		if ($_CONFIG.template == "resume/baseinfo") {
			location.href = $_CONFIG.domain + "resume/detail.php?userid=" + rsmid;
			return false
		} else {
			_History.unbind();
			location.href = $_CONFIG.domain + "resume/guide2.php?userid=" + result.userid;
			return false
		}
	}
	var i = 1;
	for (var key in result) {
		if (key == "result" || key == "status") {
			continue
		}
		var errid = eval('$("#err_' + key + '")');
		var erridspan = eval('$("#err_' + key + ' span")');
		var objid = '$("#' + key + '")';
		if (i == 1) {
			if (objid.length > 0) {
				if (result[key] == "请认证手机号，认证后可使用手机号登录") {
					pop.ini([
						["您还未认证手机号，认证成功后可直接使用手机号码进行登录，请认证！", "warn"]
					], ["确定", "focusElement($('#" + key + "'))"])
				} else {
					pop.ini([
						[result[key], "warn"]
					], ["确定", "focusElement($('#" + key + "'))"])
				}
			} else {
				pop.ini([
					[result[key], "warn"]
				], ["确定", "focusElement($('#" + key + "'))"])
			}
		}
		errid.show();
		erridspan.html(result[key]);
		i++;
		errid = "";
		objid = ""
	}
	$("#saveresumeone").removeAttr("disabled")
}

function showMajor() {
	degree = $("#degree").val();
	if (degree > 2 || degree == -1) {
		$(".zhuanye").show()
	} else {
		$(".zhuanye").hide()
	}
}
var saveResumeTwo = function () {
	$(".but").attr("disabled", "disabled");
	var schoolname = filterTitle($("#schoolname")),
		entrancetime = $("#entrancetime").text(),
		graduationtime = $("#graduationtime").text() == "至今" ? "" : $("#graduationtime").text(),
		degree = $("#degree").val(),
		isfulltime = $("#isfulltime").hasClass("on") ? "1" : "0",
		majordesc = filterTitle($("#majordesc")),
		major = $("#major")[0] ? $("#major").val() : "",
		oversea = $("#isoversea .rdo").first().hasClass("on") ? "1" : "0",
		describe = $("#describe")[0] ? filterTitle($("#describe")) : "",
		userid = $("#userid").val(),
		eduid = $("#eduid").val();
	var url = $_CONFIG.domain + "ajax/resume/eduinfo.ajax.php";
	var param = {
		schoolname: schoolname,
		entrancetime: entrancetime,
		graduationtime: graduationtime,
		isfulltime: isfulltime,
		majordesc: majordesc,
		major: major,
		oversea: oversea,
		degree: degree,
		describe: describe,
		userid: userid,
		eduid: eduid
	};
	console.log(param);
	var result = ajaxRequest(url, param, "post", "json");
	if (result.url && result.status == 0) {
		if ($_CONFIG.template == "resume/guid2") {
			_History.unbind()
		}
		location.href = result.url;
		return false
	}
	if (result.status == 1) {
		if ($_CONFIG.template == "resume/eduexp") {
			location.href = $_CONFIG.domain + "resume/eduexpedit.php?userid=" + userid
		} else {
			_History.unbind();
			location.href = $_CONFIG.domain + "resume/guide3.php?userid=" + userid
		}
	}
	var i = 1;
	for (var key in result.desc) {
		var errid = eval('$("#err_' + key + '")');
		var erridspan = eval('$("#err_' + key + ' span")');
		var objid = '$("#' + key + '")';
		if (i == 1) {
			if (objid.length > 0) {
				if (key != "majorname") {
					pop.ini([
						[result.desc[key], "warn"]
					], ["确定", "focusElement($('#" + key + "'))"])
				} else {
					pop.ini([
						[result.desc[key], "warn"]
					], ["确定", "focusElement($('#" + key + "'))"])
				}
				$(".but").removeAttr("disabled")
			} else {
				pop.ini([
					[result.desc[key], "warn"]
				], ["确定"]);
				$(".but").removeAttr("disabled")
			}
		}
		errid.show();
		erridspan.html(result.desc[key]);
		i++;
		errid = "";
		objid = ""
	}
};
var saveResumeThree = function (add) {
	$(".but").attr("disabled", "disabled");
	var coname = filterTitle($("#coname")),
		entrytime = $("#entrytime").text(),
		leavetime = $("#leavetime").text() == "至今" ? "" : $("#leavetime").text(),
		funtype = $("#funtype").val(),
		position = filterTitle($("#position")),
		indtype = $("#indtype").val(),
		worktype = $("#worktype span.on").first().attr("value"),
		cosize = $("#cosize")[0] ? $("#cosize").val() : "",
		cotype = $("#cotype")[0] ? $("#cotype").val() : "",
		department = filterTitle($("#department")),
		workdesc = filterTitle($("#workdesc")),
		reportboss = $("#reportboss")[0] ? filterTitle($("#reportboss")) : "",
		reportperson = $("#reportperson")[0] ? filterTitle($("#reportperson")) : "",
		isoversea = $("#overseawork span.on").attr("value"),
		leavereason = $("#leavereason")[0] ? filterTitle($("#leavereason")) : "",
		score = $("#score")[0] ? filterTitle($("#score")) : "",
		userid = $("#userid").val(),
		workid = $("#workid").val(),
		isedit = $("#isedit").val();
	var url = $_CONFIG.domain + "ajax/resume/workinfo.ajax.php";
	var param = {
		coname: coname,
		entrytime: entrytime,
		leavetime: leavetime,
		funtype: funtype,
		position: position,
		indtype: indtype,
		worktype: worktype,
		department: department,
		workdesc: workdesc,
		cosize: cosize,
		cotype: cotype,
		reportboss: reportboss,
		reportperson: reportperson,
		isoversea: isoversea,
		leavereason: leavereason,
		score: score,
		workid: workid,
		userid: userid
	};
	var result = ajaxRequest(url, param, "post", "json");
	if (result.url && result.status == 0) {
		if ($_CONFIG.template == "resume/guid3") {
			_History.unbind()
		}
		location.href = result.url;
		return false
	}
	if (result.status == 1) {
		if ($_CONFIG.template == "resume/workexp") {
			location.href = $_CONFIG.domain + "resume/workexpedit.php?userid=" + userid
		} else {
			jianli = $("#jianli").val();
			_History.unbind();
			if (add == 0) {
				location.href = $_CONFIG.domain + "resume/guide4.php?userid=" + userid
			} else {
				location.href = $_CONFIG.domain + "resume/guide3.php?userid=" + userid + "&jianli=" + jianli
			}
		}
	}
	var i = 1;
	for (var key in result.desc) {
		var errid = eval('$("#err_' + key + '")');
		var erridspan = eval('$("#err_' + key + ' span")');
		var objid = '$("#' + key + '")';
		if (i == 1) {
			if (objid.length > 0) {
				if (key != "funtypename") {
					pop.ini([
						[result.desc[key], "warn"]
					], ["确定", "focusElement($('#" + key + "'))"])
				} else {
					pop.ini([
						[result.desc[key], "warn"]
					], ["确定"])
				}
				$(".but").removeAttr("disabled")
			} else {
				pop.ini([
					[result.desc[key], "warn"]
				], ["确定"]);
				$(".but").removeAttr("disabled")
			}
		}
		errid.show();
		erridspan.html(result.desc[key]);
		i++;
		errid = "";
		objid = ""
	}
};
var saveResumeFour = function () {
	var jobterm = $("#jobterm").val(),
		area = $("#area").val(),
		indtype = $("#indtype").val(),
		funtype = $("#funtype").val(),
		entrytime = $("#entrytime").val(),
		intro = filterTitle($("#intro")),
		resumekey = filterTitle($("#resumekey")),
		userid = $("#userid").val();
	isedit = $("#isedit").val();
	var position = $("#position").val();
	if (area == "") {
		pop.ini([
			["请选择期望地点", "warn"]
		], ["确定"]);
		return false
	}
	if (funtype == "") {
		pop.ini([
			["请选择期望职能", "warn"]
		], ["确定"]);
		return false
	}
	if (position == "") {
		pop.ini([
			["请输入职位名称", "warn"]
		], ["确定"]);
		return false
	}
	if (position.length > 25) {
		pop.ini([
			["职位请控制在25个中文字以内", "warn"]
		], ["确定"]);
		return false
	}
	var salarytype = $("#salarytype").val();
	var inputsalary = "";
	if (salarytype == "4") {
		inputsalary = $("#yearsal")[0] ? $("#yearsal").val() : ""
	} else {
		if (salarytype == "1") {
			inputsalary = $("#monthsal")[0] ? $("#monthsal").val() : ""
		} else {
			if (salarytype == "3") {
				inputsalary = filterTitle($("#sal3"));
				if (!checkNumber(inputsalary, "int")) {
					pop.ini([
						["请输入整数", "warn"]
					], ["确定"]);
					inputPrompt($("#sal"), "请输入整数");
					return false
				}
			} else {
				if (salarytype == "2") {
					inputsalary = filterTitle($("#sal2"));
					if (!checkNumber(inputsalary, "int")) {
						pop.ini([
							["请输入整数", "warn"]
						], ["确定"]);
						inputPrompt($("#sal"), "请输入整数");
						return false
					}
				}
			}
		}
	}
	var is_customize = false;
	if (inputsalary == "customize") {
		is_customize = true;
		var min = filterTitle($("#sal_min"));
		var max = filterTitle($("#sal_max"));
		if (!checkNumber(min, "int") || !checkNumber(max, "int")) {
			pop.ini([
				["请输入整数", "warn"]
			], ["确定"]);
			inputPrompt($("#customizesalary"), "请输入整数");
			return false
		} else {
			if (min > 10000000 || max > 10000000) {
				pop.ini([
					["最多七位整数", "warn"]
				], ["确定"]);
				inputPrompt($("#customizesalary"), "最多七位整数");
				return false
			}
		}
		inputsalary = min + "-" + max
	}
	var url = $_CONFIG.domain + "ajax/resume/jobintent.ajax.php";
	var param = {
		jobterm: jobterm,
		area: area,
		indtype: indtype,
		funtype: funtype,
		salarytype: salarytype,
		inputsalary: inputsalary,
		entrytime: entrytime,
		intro: intro,
		resumekey: resumekey,
		position: position,
		is_customize: is_customize,
		userid: userid
	};
	var result = ajaxRequest(url, param, "post", "json");
	if (result.url && result.status == 0) {
		if ($_CONFIG.template == "resume/guid3") {
			_History.unbind()
		}
		location.href = result.url;
		return false
	}
	if (result.status == 1) {
		if ($_CONFIG.template == "resume/jobintent") {
			location.href = $_CONFIG.domain + "resume/detail.php?userid=" + userid
		} else {
			var redirect = storage.json_get("redirect");
			var ret_url = redirect.ret_url;
			if (ret_url) {
				storage.del("redirect");
				location.href = ret_url;
				return false
			} else {
				var config1 = "简历已创建成功！完善简历可以获得更多工作机会哦。";
				var config5 = [
					["完善简历", "查看简历"],
					["improveRsm()", "lookRsm()"]
				];
				pop.ini(config1, config5)
			}
		}
	}
	var i = 1;
	for (var key in result.desc) {
		var errid = eval('$("#err_' + key + '")');
		var erridspan = eval('$("#err_' + key + ' span")');
		var objid = '$("#' + key + '")';
		var objeval = eval('$("#' + key + '")');
		if (i == 1) {
			if (objid.length > 0 && objeval.is("input") && key != "reportperson" && key != "reportboss" && key != "reference") {
				pop.ini([
					[result.desc[key], "warn"]
				], ["确定", "focusElement($('#" + key + "'))"]);
				$(".but").removeAttr("disabled")
			} else {
				pop.ini([
					[result.desc[key], "warn"]
				], ["确定"]);
				$(".but").removeAttr("disabled")
			}
		}
		errid.show();
		erridspan.html(result.desc[key]);
		i++;
		errid = "";
		objid = ""
	}
};
var skipResumeThree = function () {
	var j = filterTitle($("#coname")),
		i = $("#entrytime").val(),
		f = $("#leavetime").val(),
		p = $("#funtype").val(),
		s = filterTitle($("#position")),
		c = $("#indtype").val(),
		d = $("#cosize")[0] ? $("#cosize").val() : "",
		l = $("#cotype")[0] ? $("#cotype").val() : "",
		o = filterTitle($("#department")),
		b = filterTitle($("#workdesc")),
		e = $("#reportboss")[0] ? filterTitle($("#reportboss")) : "",
		m = $("#reportperson")[0] ? filterTitle($("#reportperson")) : "",
		g = $("#reference")[0] ? filterTitle($("#reference")) : "",
		t = $("#overseawork span.on").attr("value"),
		h = $("#leavereason")[0] ? filterTitle($("#leavereason")) : "",
		n = $("#score")[0] ? filterTitle($("#score")) : "",
		a = $("#userid").val(),
		r = $("#workid").val();
	var k = false;
	if (j != "" || i != "" || f != "" || p != "" || c != "" || o != "" || b != "" || s != "") {
		k = true
	}
	if (k == false) {
		var q = "完善的工作经验将增加投递成功率，确定跳过么？";
		config5 = [
			["继续填写", "跳过"],
			["continueRsm()", "skipResume()"]
		];
		pop.ini(q, config5)
	} else {
		var q = "你的信息尚未保存，确定跳过么？";
		config5 = [
			["继续填写", "跳过"],
			["continueRsm()", "skipResume()"]
		];
		pop.ini(q, config5)
	}
};
var continueRsm = function () {
	pop.close()
};
var improveRsm = function () {
	_History.unbind();
	var a = $("#userid").val();
	location.href = domain + "resume/detail.php?userid=" + a
};
var skipResume = function () {
	_History.unbind();
	var a = $("#userid").val();
	location.href = $_CONFIG.domain + "resume/guide4.php?userid=" + a
};
var lookRsm = function () {
	_History.unbind();
	var a = $("#userid").val();
	location.href = $_CONFIG.domain + "resume/resumepreview.php?userid=" + a
};
var editWorkExp = function (a, b) {
	location.href = $_CONFIG.domain + "resume/workexp.php?userid=" + a + "&workid=" + b
};
var delWorkExp = function (a, f, e) {
	var b = $(e).parents().find("strong").text(),
		d = $(e).parents().find("i").text();
	var c = b + '<br><br><span class="c_orange">' + d + "</span>";
	$("#delworkpop .cn").html(c);
	$("#delworkid").attr("attr-userid", a);
	$("#delworkid").attr("attr-workid", f);
	$("#delworkpop,#shadow").show()
};
var delProjectExp = function (b, a, f) {
	var c = $(f).parents().find("strong").text(),
		e = $(f).parents().find("i").text();
	var d = c + '<br><br><span class="c_orange">' + e + "</span>";
	$("#delpropop .cn").html(d);
	$("#delproid").attr("attr-userid", b);
	$("#delproid").attr("attr-proid", a);
	$("#delpropop,#shadow").show()
};
var confirmDelWorkExp = function (h) {
	$("#delworkpop,#shadow").hide();
	var b = $(h).attr("attr-userid");
	var g = $(h).attr("attr-workid");
	var c = $_CONFIG.domain + "ajax/resume/delworkexp.ajax.php";
	var f = {
		userid: b,
		workid: g
	};
	var a = ajaxRequest(c, f, "post", "json");
	if (a.status == 1) {
		pop.ini(a.desc, ["确定"]);
		$("#workexp_" + g).remove();
		if (!$("#addwork")[0]) {
			$("#pageContent").append("<div onclick=\"location.href ='" + $_CONFIG.domain + "resume/workexp.php?userid=" + b + '\'" value="' + b + '" class="btn_add" >+ 添加工作经验</div>')
		}
		return false
	} else {
		if (a.url) {
			location.href = a.url;
			return false
		}
		var e = [
			[a.desc, "warn"]
		];
		var d = ["确定"];
		pop.ini(e, d);
		return false
	}
};
var editEduExp = function (a, b) {
	location.href = $_CONFIG.domain + "resume/eduexp.php?userid=" + a + "&eduid=" + b
};
var delEduExp = function (b, a, f) {
	var c = $(f).parents().find("strong").text(),
		e = $(f).parents().find("i").text();
	var d = c + '<br><br><span class="c_orange">' + e + "</span>";
	$("#deledupop .cn").html(d);
	$("#deleduid").attr("attr-userid", b);
	$("#deleduid").attr("attr-eduid", a);
	$("#deledupop,#shadow").show()
};
var confirmDelEduExp = function (h) {
	$("#deledupop,#shadow").hide();
	var c = $(h).attr("attr-userid");
	var b = $(h).attr("attr-eduid");
	var d = $_CONFIG.domain + "ajax/resume/deleduexp.ajax.php";
	var g = {
		userid: c,
		eduid: b
	};
	var a = ajaxRequest(d, g, "post", "json");
	if (a.status == 1) {
		pop.ini(a.desc, ["确定"]);
		$("#eduexp_" + b).remove();
		if (!$("#addedu")[0]) {
			$("#pageContent").append("<div onclick=\"location.href ='" + $_CONFIG.domain + "resume/eduexp.php?userid=" + c + '\'" value="' + c + '" value="' + c + '" class="btn_add" >+ 添加教育经历</div>')
		}
		return false
	} else {
		if (a.url) {
			location.href = a.url;
			return false
		}
		var f = [
			[a.desc, "warn"]
		];
		var e = ["确定"];
		pop.ini(f, e);
		return false
	}
};
var editProExp = function (b, a) {
	location.href = $_CONFIG.domain + "resume/proexpedit.php?userid=" + b + "&projectid=" + a
};
var saveProject = function () {
	var url = $_CONFIG.domain + "ajax/resume/saveproject.ajax.php";
	var param = {
		projectname: filterTitle($("#projectname")),
		timefrom: $("#timefrom").text(),
		timeto: $("#timeto").text() == "至今" ? "" : $("#timeto").text(),
		ifunction: filterTitle($("#function")),
		workcompname: $("#workcompname").val(),
		describe: filterTitle($("#prodescribe")),
		userid: $("#userid").val(),
		projectid: $("#projectid").val()
	};
	var result = ajaxRequest(url, param, "post", "json");
	if (result.url && result.status == 0) {
		location.href = result.url;
		return false
	}
	if (result.status == 1) {
		location.href = $_CONFIG.domain + "resume/proexplist.php?userid=" + $("#userid").val()
	}
	var i = 1;
	for (var key in result.desc) {
		var errid = eval('$("#err_' + key + '")');
		var erridspan = eval('$("#err_' + key + ' span")');
		var objid = '$("#' + key + '")';
		var objeval = eval('$("#' + key + '")');
		if (i == 1) {
			if (objid.length > 0 && objeval.is("input") && key != "reportperson" && key != "reportboss" && key != "reference") {
				pop.ini([
					[result.desc[key], "warn"]
				], ["确定", "focusElement($('#" + key + "'))"]);
				$(".but").removeAttr("disabled")
			} else {
				pop.ini([
					[result.desc[key], "warn"]
				], ["确定"]);
				$(".but").removeAttr("disabled")
			}
		}
		errid.show();
		erridspan.html(result.desc[key]);
		i++;
		errid = "";
		objid = ""
	}
};
var confirmDelProjectExp = function (e) {
	$("#delpropop,#shadow").hide();
	var c = $(e).attr("attr-userid");
	var b = $(e).attr("attr-proid");
	var d = $_CONFIG.domain + "ajax/resume/delproject.ajax.php?projectid=" + b + "&userid=" + c;
	var a = ajaxRequest(d);
	if (a.status == 1) {
		$("#projectexp_" + b).remove();
		if (!$("#addpro")[0]) {
			$("#pageContent").append("<div  onclick=\"location.href ='" + $_CONFIG.domain + "resume/proexpedit.php?userid=" + c + '\'" value="' + c + '" value="' + c + '" class="btn_add" >+ 添加项目经验</div>')
		}
		return false
	} else {
		if (a.url) {
			location.href = a.url
		}
		pop.ini([
			[a.message, "warn"]
		], ["确定"])
	}
};
var savePasteBaseInfo = function () {
	var cname = filterTitle($("#cname")),
		sex = $("#sex span.on").attr("value"),
		bday = $("#bday").text(),
		mpcountry = $("#mobilecountry span.on").attr("value"),
		mobilephone = filterTitle($("#mobilephone")),
		area = $("#jobarea").val(),
		workyear = $("#workyear").val(),
		idtype = $("#idtype").val(),
		idcard = filterTitle($("#idcard")),
		major = $("#major").val(),
		degree = $("#degree").val(),
		situation = $("#situation").val(),
		funtype = $("#funtype").val(),
		indtype = $("#indtype").val(),
		keyword = $("#resumekey").val(),
		isfulltime = $("#isfulltime").hasClass("on") ? "1" : "0",
		yearsalary = filterTitle($("#yearsalary"));
	if (yearsalary != "" && !checkSalary(yearsalary)) {
		pop.ini([
			["精确到小数点后两位，不超过999.99", "warn"]
		], ["确定"]);
		inputPrompt($("#yearsalary"), "精确到小数点后两位，不超过999.99");
		return false
	}
	if ($("#email")[0] && $("#email").parent().hasClass("ipt")) {
		var email = $("#email").val()
	} else {
		var email = ""
	}
	if (typeof (mpcountry) == "undefined") {
		mpcountry = "086"
	}
	var url = $_CONFIG.domain + "ajax/resume/pastebaseinfo.ajax.php";
	var param = {
		cname: cname,
		sex: sex,
		bday: bday,
		mpcountry: mpcountry,
		mobilephone: mobilephone,
		area: area,
		workyear: workyear,
		idtype: idtype,
		idcard: idcard,
		email: email,
		major: major,
		degree: degree,
		situation: situation,
		funtype: funtype,
		indtype: indtype,
		isfulltime: isfulltime,
		keyword: keyword,
		yearsalary: yearsalary
	};
	var result = ajaxRequest(url, param, "post", "json");
	if (result.url && result.status == 0) {
		location.href = result.url;
		return false
	}
	if (result.status == 1) {
		location.href = $_CONFIG.domain + "resume/pastedetail.php";
		return false
	}
	var i = 1;
	for (var key in result.desc) {
		var errid = eval('$("#err_' + key + '")');
		var erridspan = eval('$("#err_' + key + ' span")');
		var objid = '$("#' + key + '")';
		if (i == 1) {
			if (objid.length > 0) {
				if (result.desc[key] == "请认证手机号，认证后可使用手机号登录") {
					pop.ini([
						["您还未认证手机号，认证成功后可直接使用手机号码进行登录，请认证！", "warn"]
					], ["确定", "focusElement($('#" + key + "'))"])
				} else {
					pop.ini([
						[result.desc[key], "warn"]
					], ["确定", "focusElement($('#" + key + "'))"])
				}
			} else {
				pop.ini([
					[result.message, "warn"]
				], ["确定"])
			}
		}
		errid.show();
		erridspan.html(result.desc[key]);
		i++;
		errid = "";
		objid = ""
	}
};
var savePasteOtherinfo = function () {
	var d = filterTitle($("#otherinfo"));
	if (d == "") {
		pop.ini([
			["其他信息不能为空", "warn"]
		], ["确定"]);
		return false
	}
	var b = $_CONFIG.domain + "ajax/resume/pasteotherinfo.ajax.php";
	var c = {
		otherinfo: d
	};
	var a = ajaxRequest(b, c, "post", "json");
	if (a.status == 1) {
		location.href = $_CONFIG.domain + "resume/pastedetail.php"
	} else {
		if (a.url) {
			location.href = a.url;
			return false
		}
		pop.ini([
			[a.desc, "warn"]
		], ["确定"]);
		inputPrompt($("#otherinfo"), a.desc);
		return false
	}
};
var showRsmDetail = function (a) {
	obj = $(a).parent();
	if (!obj.hasClass("mk")) {
		obj.addClass("mk")
	} else {
		obj.removeClass("mk")
	}
};

function abc() {
	pop("成功", 2, "", "", "", "", "", "", 1)
}
var refreshRsm = function (b) {
	var c = $_CONFIG.domain + "ajax/resume/refreshresume.ajax.php";
	var g = {
		userid: b
	};
	var a = ajaxRequest(c, g, "post", "json");
	if (a.status == 1) {
		$(".finishtime").each(function () {
			if ($(this).attr("value") == b) {
				var h = "创建于" + $(this).attr("data-value") + "&nbsp;&nbsp;&nbsp;&nbsp;更新于" + a.update;
				$(this).html(h)
			}
		});
		var f = "简历刷新成功";
		pop.msg(f);
		return false
	} else {
		if (a.url) {
			location.href = a.url;
			return false
		}
		var e = [
			[a.desc, "warn"]
		];
		var d = ["确定"];
		pop.ini(e, d);
		return false
	}
};
var setFastRsm = function (c, h) {
	var b = $(h).hasClass("on") ? "0" : "1";
	var d = $_CONFIG.domain + "ajax/resume/quickrsm.ajax.php?rsmid=" + c + "&status=" + b;
	var g = {
		userid: c,
		status: b
	};
	var a = ajaxRequest(d, g, "post", "json");
	if (a.status == 1) {
		$(".fast").each(function () {
			if ($(this).attr("value") == c) {
				$(this).toggleClass("on")
			} else {
				$(this).removeClass("on")
			}
		});
		pop.ini(a.desc, ["确定"]);
		return false
	} else {
		if (a.url) {
			location.href = a.url;
			return false
		}
		var f = [
			[a.desc, "warn"]
		];
		var e = ["确定"];
		pop.ini(f, e);
		return false
	}
};
var showOpen = function (b, c, a) {
	openuserid = b;
	$("#openstatus .rsmname").html(c);
	$("#openstatus .e").each(function () {
		if ($(this).attr("value") == a) {
			$(this).addClass("on")
		} else {
			$(this).removeClass("on")
		}
	});
	$(".popOther").hide();
	shadow.show();
	$("#openstatus").show();
	$(".popE .e").click(function () {
		if (!$(this).hasClass("on")) {
			$(this).addClass("on");
			$(this).siblings().removeClass("on")
		}
	});
	return false
};
$("#confirmOpen").click(function () {
	$(".popE .e").each(function () {
		if ($(this).hasClass("on")) {
			ostatus = $(this).attr("value");
			return false
		}
	});
	confirmOpen(openuserid, ostatus);
	return false
});
var confirmOpen = function (c, b) {
	var d = $_CONFIG.domain + "ajax/resume/openresume.ajax.php";
	var g = {
		userid: c,
		status: b
	};
	var a = ajaxRequest(d, g, "post", "json");
	pop.close();
	if (a.status == 1) {
		$(".showOpen").each(function () {
			if ($(this).parent().attr("value") == c) {
				$(this).removeAttr("value");
				$(this).attr("value", b)
			}
			if ($(this).attr("value") == "0" && $(this).parent().attr("value") != c) {
				$(this).removeAttr("value");
				$(this).attr("value", "2")
			}
		});
		if (b == 0) {
			$(".openchange").each(function () {
				if ($(this).text() == "对所有公开") {
					$(this).text("对无忧公开")
				}
			});
			$("#rsm_" + c + " .openchange").text("对所有公开")
		} else {
			if (b == 2) {
				$("#rsm_" + c + " .openchange").text("对无忧公开")
			} else {
				if (b == 3) {
					$("#rsm_" + c + " .openchange").text("完全保密")
				}
			}
		}
		pop.ini(a.desc, ["确定"]);
		return false
	} else {
		if (a.url) {
			location.href = a.url;
			return false
		}
		var f = [
			[a.desc, "warn"]
		];
		var e = ["确定"];
		pop.ini(f, e);
		return false
	}
};
var copyResume = function (a) {
	pageshow("copyrsm")
};
var chooseResumePart = function (a) {
	if ($(a).hasClass("mk")) {
		$(a).removeClass("mk")
	} else {
		$(a).addClass("mk")
	}
};
var confirmCopyResume = function () {
	var f = $("#copyUserid").val(),
		h = $("#copyname").val();
	if (h == "") {
		var e = [
				["请填写简历名称", "warn"]
			],
			d = ["确定"];
		pop.ini(e, d);
		return false
	}
	var g = h.replace(/[^\x00-\xff]/g, "**");
	if (g.length > 50) {
		var e = [
				["简历名称太长", "warn"]
			],
			d = ["确定"];
		pop.ini(e, d);
		return false
	}
	var b = new Array();
	$("#copyinfo, .e").each(function () {
		if ($(this).hasClass("on")) {
			b.push($(this).attr("name"))
		}
	});
	var a = domain + "ajax/resume/copyresume.ajax.php";
	var c = {
		userid: f,
		rsmname: h,
		part: b
	};
	var i = ajaxRequest(a, c, "post", "json");
	if (i.status == 1) {
		location.reload();
		return false
	} else {
		if (i.url) {
			location.href = i.url;
			return false
		}
		var e = [
			[i.desc, "warn"]
		];
		var d = ["确定"];
		pop.ini(e, d);
		return false
	}
};
var delResume = function (a, d) {
	console.log(a);
	console.log(d);
	var c = "确定删除" + d + "么?";
	var b = [
		["取消", "确定"],
		["", "confirmDelResume('" + a + "')"]
	];
	pop.ini(c, b)
};
var confirmDelResume = function (b) {
	var c =  "delresume.ajax.php";
	var f = {
		userid: b
	};
	var a = ajaxRequest(c, f, "post", "json");
	if (a.status == 1) {
		pop.close();
		$("#rsm_" + b).remove();
		if (!$("#addrsm")[0] && a.addtype == 0) {
			$("#pageContent").append('<div id="addrsm" class="btn_add" onclick="createResume();">+ 创建简历</div>')
		}
		return false
	} else {
		if (a.url) {
			location.href = a.url;
			return false
		}
		var e = [
			[a.desc, "warn"]
		];
		var d = ["确定"];
		pop.ini(e, d);
		return false
	}
};
var saveRsmName = function (b) {
	var d = $("#rsmname").val();
	if (d == "") {
		$("#err_rsmname").show();
		$("#err_rsmname").find(".error").html("请输入简历名");
		return false
	}
	var f = d.replace(/[^\x00-\xff]/g, "**");
	if (f.length > 50) {
		$("#err_rsmname").show();
		$("#err_rsmname").find(".error").html("简历名称太长");
		return false
	}
	var c = domain + "ajax/resume/editresumename.ajax.php";
	var e = {
		userid: b,
		rsmname: d
	};
	var a = ajaxRequest(c, e, "post", "json");
	if (a.status == 1) {
		$("#srsmname").html(a.rsmname);
		$("#rsmname").val(d);
		$("#changersmname,#shadow").hide();
		$("body").removeClass("resumeShow");
		return false
	} else {
		if (a.url) {
			location.href = a.url;
			return false
		}
		$("#err_rsmname").show();
		$("#err_rsmname").find(".error").html(a.desc);
		return false
	}
};
var showSenior = function (a) {
	if ($(a).hasClass("on")) {
		$(a).removeClass("on");
		$("#highworkinfo").hide()
	} else {
		$(a).addClass("on");
		$("#highworkinfo").show()
	}
};
var createResume = function () {
	var b = "是否需要创建一份新简历";
	var a = [
		["取消", "确定"],
		["", "cResume()"]
	];
	pop.ini(b, a)
};
var cResume = function () {
	var b = domain + "ajax/resume/createresume.ajax.php";
	var e = {};
	var a = ajaxRequest(b, e, "post", "json");
	if (a.status == 1) {
		location.href = domain + "resume/detail.php?userid=" + a.rsmid
	} else {
		if (a.url) {
			location.href = a.url;
			return false
		}
		var d = [
			[a.desc, "warn"]
		];
		var c = ["确定"];
		pop.ini(d, c);
		return false
	}
};
if ("oninput" in document) {
	$(".inputblur").bind("input", function () {
		var a = $(this).attr("id");
		if (a == "inputphone") {
			a = "mobilephone"
		}
		$(this).next().show();
		$("#err_" + a).hide();
		if ($(this).attr("id") == "hoursal") {
			$("#err_daysal").hide()
		}
		if ($(this).attr("id") == "daysal") {
			$("#err_hoursal").hide()
		}
	})
} else {
	$(".inputblur").keydown(function () {
		var a = $(this).attr("id");
		if (a == "inputphone") {
			a = "mobilephone"
		}
		$(this).next().show();
		$("#err_" + a).hide();
		if ($(this).attr("id") == "hoursal") {
			$("#err_daysal").hide()
		}
		if ($(this).attr("id") == "daysal") {
			$("#err_hoursal").hide()
		}
	})
}
$(".inputblur").click(function () {
	if ($(".calendar ").css("display") == "block") {
		$(".calendar ").hide()
	}
});

function clearPrompt(a) {
	if (a != undefined) {
		var b = a.attr("id");
		$("#err_" + b).hide()
	}
}

function inputPrompt(b, d, a) {
	if (a) {
		b.focus()
	}
	clearPrompt(b);
	var c = b.attr("id");
	$("#err_" + c).show();
	$("#err_" + c).children().html(d)
}

function focusElement(a) {
	pop.close();
	a.focus()
}
$("#jobareaname").click(function () {
	clearPrompt($("#jobareaname"))
});
$(".inputblur").blur(function () {
	if ($(this).attr("id") == "cname") {
		var c = filterTitle($("#cname"));
		if (filterTitle($(this)) == "") {
			inputPrompt($("#cname"), "请输入姓名");
			return false
		} else {
			var u = c.replace(/[^\x00-\xff]/g, "**");
			if (u.length > 20) {
				inputPrompt($("#cname"), "姓名限10个中文字内");
				return false
			}
		}
	}
	if ($(this).attr("id") == "mobilephone") {
		var r = filterTitle($("#mobilephone"));
		var b = $("#mobilecountry span.on").attr("value");
		if (typeof (b) == "undefined") {
			b = "086"
		}
		if (filterTitle($(this)) == "") {
			inputPrompt($("#mobilephone"), "请输入手机号");
			return
		}
		if (b == "086") {
			if (!/^[0-9]{1,20}$/.test(r)) {
				inputPrompt($("#mobilephone"), "你输入的手机号码有误");
				return false
			} else {
				if (filterTitle($(this)).length > 11) {
					inputPrompt($("#mobilephone"), "仅支持11位大陆手机号");
					return false
				}
			}
		} else {
			if (b == "000") {
				if (!/^[0-9]{1,20}$/.test(r)) {
					inputPrompt($("#mobilephone"), "你输入的手机号码有误");
					return false
				}
			}
		}
	}
	if ($(this).attr("id") == "daysal" || $(this).attr("id") == "hoursal") {
		if (filterTitle($(this)) == "") {
			inputPrompt($("#sal"), "请输入期望薪资");
			return false
		} else {
			clearPrompt($("#sal"))
		}
	}
	if ($(this).attr("id") == "intro") {
		var A = $("#intro")[0] ? filterTitle($("#intro")) : "";
		var u = A.replace(/[^\x00-\xff]/g, "**");
		if (u.length > 2000) {
			inputPrompt($("#intro"), "自我介绍限1000个中文字内");
			return false
		}
	}
	if ($(this).attr("id") == "qy_zwms") {
		var A = $("#qy_zwms")[0] ? filterTitle($("#qy_zwms")) : "";
		var u = A.replace(/[^\x00-\xff]/g, "**");
		if (u.length > 2000) {
			inputPrompt($("#qy_zwms"), "职位描述限1000个中文字内");
			return false
		}
	}
	if ($(this).attr("id") == "qy_qyjl") {
		var A = $("#qy_qyjl")[0] ? filterTitle($("#qy_qyjl")) : "";
		var u = A.replace(/[^\x00-\xff]/g, "**");
		if (u.length > 2000) {
			inputPrompt($("#qy_qyjl"), "企业简历限1000个中文字内");
			return false
		}
	}
	if ($(this).attr("id") == "resumekey") {
		if (filterTitle($(this)) != "") {
			var G = $("#resumekey")[0] ? $("#resumekey").val() : "";
			var n = G.split(" ");
			n = $.grep(n, function (H) {
				return $.trim(H).length > 0
			});
			if (n.length > 10) {
				inputPrompt($("#resumekey"), "个人标签格式错误，请重新输入！");
				return false
			}
			var E = false;
			for (var m in n) {
				var u = n[m].replace(/[^\x00-\xff]/g, "**");
				if (u.length > 24) {
					E = true
				}
			}
			if (E) {
				inputPrompt($("#resumekey"), "个人标签格式错误，请重新输入！");
				return false
			} else {
				clearPrompt($("#resumekey"));
				return false
			}
		}
	}
	if ($(this).attr("id") == "email") {
		if (filterTitle($(this)) != "") {
			if ($("#email")[0] && $("#email").attr("disabled") != "disabled") {
				var t = filterTitle($("#email"))
			} else {
				var t = ""
			}
		} else {
			clearPrompt($("#email"));
			return false
		}
	}
	if ($(this).attr("id") == "baseinfoemail") {
		if (filterTitle($(this)) != "") {
			if ($("#baseinfoemail")[0] && $("#baseinfoemail").attr("disabled") != "disabled") {
				var t = filterTitle($("#baseinfoemail"))
			} else {
				var t = ""
			}
		} else {
			clearPrompt($("#baseinfoemail"));
			return false
		}
	}
	if ($(this).attr("id") == "schoolname") {
		var f = filterTitle($("#schoolname"));
		if (filterTitle($(this)) == "") {
			inputPrompt($("#schoolname"), "请输入学校名称");
			return false
		} else {
			var u = f.replace(/[^\x00-\xff]/g, "**");
			if (u.length > 50) {
				inputPrompt($("#schoolname"), "学校名称限25个中文字内");
				return false
			}
		}
	}
	if ($(this).attr("id") == "majordesc") {
		var w = $("#major")[0] ? $("#major").val() : "",
			e = $("#degree").val(),
			h = filterTitle($("#majordesc"));
		if ((e > 2 && e != 9) || e == "-1") {
			var u = h.replace(/[^\x00-\xff]/g, "**");
			if (u.length > 100) {
				inputPrompt($("#majordesc"), "专业名称限50个中文字内");
				return false
			}
		}
	}
	if ($(this).attr("id") == "describe") {
		var i = $("#describe")[0] ? filterTitle($("#describe")) : "";
		if (i != "") {
			var u = i.replace(/[^\x00-\xff]/g, "**");
			if (u.length > 2000) {
				inputPrompt($("#describe"), "专业描述限1000个中文字内");
				return false
			} else {
				clearPrompt($("#describe"));
				return false
			}
		}
	}
	if ($(this).attr("id") == "prodescribe") {
		var k = filterTitle($("#prodescribe"));
		var u = k.replace(/[^\x00-\xff]/g, "**");
		if (k == "") {
			inputPrompt($("#prodescribe"), "请输入项目描述");
			return false
		} else {
			if (u.length > 2000) {
				inputPrompt($("#prodescribe"), "请填写正确的项目描述，限1000个汉字");
				return false
			}
		}
	}
	if ($(this).attr("id") == "function") {
		var d = $("#function")[0] ? filterTitle($("#function")) : "";
		if (i != "") {
			var u = d.replace(/[^\x00-\xff]/g, "**");
			if (u.length > 2000) {
				inputPrompt($("#function"), "请填写正确的责任描述，限1000个汉字");
				return false
			} else {
				clearPrompt($("#function"));
				return false
			}
		}
	}
	if ($(this).attr("id") == "coname") {
		var q = filterTitle($("#coname"));
		if (filterTitle($(this)) == "") {
			inputPrompt($("#coname"), "请输入公司名称");
			return false
		}
	}
	if ($(this).attr("id") == "czprs") {
		var q = filterTitle($("#czprs"));
		if (filterTitle($(this)) == "") {
			inputPrompt($("#czprs"), "请输入招聘人数");
			return false
		}
	}
	if ($(this).attr("id") == "cjnyq") {
		var q = filterTitle($("#cjnyq"));
		if (filterTitle($(this)) == "") {
			inputPrompt($("#cjnyq"), "请输入技能要求");
			return false
		}
	}
	if ($(this).attr("id") == "zw_ssbm") {
		var q = filterTitle($("#zw_ssbm"));
		if (filterTitle($(this)) == "") {
			inputPrompt($("#zw_ssbm"), "请输入所属部门");
			return false
		}
	}
	if ($(this).attr("id") == "qy_lxr") {
		var q = filterTitle($("#qy_lxr"));
		if (filterTitle($(this)) == "") {
			inputPrompt($("#qy_lxr"), "请输入企业联系人");
			return false
		}
	}
	if ($(this).attr("id") == "qy_yx") {
		var q = filterTitle($("#qy_yx"));
		if (filterTitle($(this)) == "") {
			inputPrompt($("#qy_yx"), "请输入企业邮箱");
			return false
		}
	}
	if ($(this).attr("id") == "qy_xxdz") {
		var q = filterTitle($("#qy_xxdz"));
		if (filterTitle($(this)) == "") {
			inputPrompt($("#qy_xxdz"), "请输入企业详细地址");
			return false
		}
	}
	if ($(this).attr("id") == "cqymc") {
		var q = filterTitle($("#cqymc"));
		if (filterTitle($(this)) == "") {
			inputPrompt($("#cqymc"), "请输入企业名称");
			return false
		}
	}
	if ($(this).attr("id") == "position") {
		var F = filterTitle($("#position"));
		var u = F.replace(/[^\x00-\xff]/g, "**");
		if (F == "") {
			inputPrompt($("#position"), "请输入职位名称");
			return false
		} else {
			if (u.length > 70) {
				inputPrompt($("#position"), "职位名称限35个中文字内");
				return false
			}
		}
	}
	if ($(this).attr("id") == "workdesc") {
		var a = filterTitle($("#workdesc"));
		if (a == "") {
			inputPrompt($("#workdesc"), "请输入工作描述");
			return false
		}
		var u = a.replace(/[^\x00-\xff]/g, "**");
		if (u.length > 2000) {
			inputPrompt($("#workdesc"), "工作描述限1000个中文字内");
			return false
		}
	}
	if ($(this).attr("id") == "department") {
		var B = filterTitle($("#department"));
		if (B != "") {
			var u = B.replace(/[^\x00-\xff]/g, "**");
			if (u.length > 50) {
				inputPrompt($("#department"), "部门名称限25个中文字内");
				return false
			}
		}
	}
	if ($(this).attr("id") == "reportboss") {
		var g = $("#reportboss")[0] ? filterTitle($("#reportboss")) : "";
		if (g != "") {
			var u = g.replace(/[^\x00-\xff]/g, "**");
			if (u.length > 50) {
				inputPrompt($("#reportboss"), "汇报对象超长，请重新输入");
				return false
			}
		}
	}
	if ($(this).attr("id") == "reportperson") {
		var z = $("#reportperson")[0] ? filterTitle($("#reportperson")) : "";
		if (z != "") {
			if (!/^[0-9]*$/.test(z)) {
				inputPrompt($("#reportperson"), "请正确输入下属人数");
				return false
			} else {
				if (z.length > 5) {
					inputPrompt($("#reportperson"), "下属人数限5位数字");
					return false
				}
			}
		}
		if (z.substr(0, 1) == "0") {
			inputPrompt($("#reportperson"), "请正确输入下属人数");
			return false
		}
	}
	if ($(this).attr("id") == "leavereason") {
		var l = $("#leavereason")[0] ? filterTitle($("#leavereason")) : "";
		if (l != "") {
			var u = l.replace(/[^\x00-\xff]/g, "**");
			if (u.length > 200) {
				inputPrompt($("#leavereason"), "离职原因限100个中文字内");
				return false
			}
		}
	}
	if ($(this).attr("id") == "score") {
		var y = $("#score")[0] ? filterTitle($("#score")) : "";
		if (y != "") {
			var u = y.replace(/[^\x00-\xff]/g, "**");
			if (u.length > 2000) {
				inputPrompt($("#score"), "主要业绩限1000个中文字内");
				return false
			}
		}
	}
	if ($(this).attr("id") == "keyword") {
		var D = $("#keyword").val();
		if (D != "") {
			var o = D.split(" ");
			if (o.length > 10) {
				inputPrompt($("#keyword"), "个人标签格式错误，请重新输入！");
				return false
			}
			for (var m in o) {
				var u = o[m].replace(/[^\x00-\xff]/g, "**");
				if (u.length > 12) {
					inputPrompt($("#keyword"), "个人标签格式错误，请重新输入！");
					return false
				}
			}
		}
	}
	if ($(this).attr("id") == "otherinfo") {
		var s = filterTitle($("#otherinfo"));
		var u = s.replace(/[^\x00-\xff]/g, "**");
		if (s == "") {} else {
			if (u.length > 20000) {
				inputPrompt($("#otherinfo"), "输入限10000个中文字内");
				return false
			}
		}
	}
	if ($(this).attr("id") == "hoursal") {
		var C = $("#hoursal")[0] ? filterTitle($("#hoursal")) : "";
		if (C != "") {
			if (!/^[0-9]{0,7}$/.test(C)) {
				clearPrompt($("#daysal"));
				inputPrompt($("#hoursal"), "目前薪资限7位数字内");
				return false
			} else {
				if (C.length > 7) {
					clearPrompt($("#daysal"));
					inputPrompt($("#hoursal"), "目前薪资限7位数字内");
					return false
				}
			}
		}
	}
	if ($(this).attr("id") == "daysal") {
		var j = $("#daysal")[0] ? filterTitle($("#daysal")) : "";
		if (j != "") {
			if (!/^[0-9]{0,7}$/.test(j)) {
				clearPrompt($("#hoursal"));
				inputPrompt($("#daysal"), "目前薪资限7位数字内");
				return false
			} else {
				if (j.length > 7) {
					clearPrompt($("#hoursal"));
					inputPrompt($("#daysal"), "目前薪资限7位数字内");
					return false
				}
			}
		}
	}
	if ($(this).attr("id") == "projectname") {
		if ($.trim(filterTitle($(this))) == "") {
			inputPrompt($("#projectname"), "请输入项目名称")
		} else {
			clearPrompt($("#projectname"))
		}
	}
	if ($(this).attr("id") == "areaname") {
		var x = filterTitle($("#areaname"));
		if (filterTitle($(this)) == "") {
			clearPrompt($("#areaname"));
			return false
		} else {
			clearPrompt($("#areaname"));
			return false
		}
	}
	if ($(this).attr("id") == "funtypename") {
		var p = filterTitle($("#funtypename"));
		if (filterTitle($(this)) == "") {
			clearPrompt($("#funtype"));
			inputPrompt($("#funtype"), "选择职能");
			return false
		} else {
			clearPrompt($("#funtype"));
			return false
		}
	}
	if ($(this).attr("id") == "majordesc") {
		var h = filterTitle($("#majordesc"));
		var w = $("#major").val();
		var e = $("#degree").val();
		degrevalue = e > 2 || e == -1;
		if (w && w.substr(2) == "00" && h == "" && degrevalue) {
			inputPrompt($("#majordesc"), "请选择具体专业或自定义专业名称");
			return false
		} else {
			clearPrompt($("#majordesc"));
			return false
		}
	}
});
$(".selectchange").change(function () {
	if ($(this).attr("id") == "workyear") {
		if (filterTitle($(this)) == "") {
			inputPrompt($("#workyear"), "请选择工作年限");
			return false
		} else {
			clearPrompt($("#workyear"));
			return false
		}
	}
	if ($(this).attr("id") == "yearsal") {
		if (filterTitle($(this)) == "") {
			clearPrompt($("#sal"))
		} else {
			clearPrompt($("#sal"))
		}
		if (filterTitle($(this)) == "") {
			inputPrompt($("#sal"), "请选择期望薪资")
		}
		if (filterTitle($(this)) == "customize") {
			$("#customizesalary").show()
		} else {
			$("#customizesalary").hide()
		}
		return false
	}
	if ($(this).attr("id") == "monthsal") {
		if (filterTitle($(this)) == "") {
			clearPrompt($("#sal"))
		} else {
			clearPrompt($("#sal"))
		}
		if (filterTitle($(this)) == "customize") {
			$("#customizesalary").show()
		} else {
			$("#customizesalary").hide()
		}
		return false
	}
	if ($(this).attr("id") == "yearfrom" || $(this).attr("id") == "monthfrom") {
		var c = $("#yearfrom").val(),
			a = $("#monthfrom").val();
		if (c == "" || a == "") {
			inputPrompt($("#entrancetime"), "请选择开始时间");
			inputPrompt($("#timefrom"), "请选择开始时间");
			inputPrompt($("#entrytime"), "请选择入职时间");
			return false
		} else {
			if (c != "" && a != "") {
				clearPrompt($("#entrancetime"));
				clearPrompt($("#timefrom"));
				clearPrompt($("#entrytime"));
				a = (a < 10 ? "0" + a : a);
				var g = (c.toString() + a.toString());
				nowdate = getYM();
				if (g > nowdate) {
					inputPrompt($("#entrancetime"), "入学时间不能选择未来时间");
					inputPrompt($("#timefrom"), "开始时间不能选择未来时间");
					inputPrompt($("#entrytime"), "入职时间不能选择未来时间");
					return false
				}
			} else {
				clearPrompt($("#entrancetime"));
				clearPrompt($("#timefrom"));
				clearPrompt($("#entrytime"));
				return false
			}
		}
	}
	if ($(this).attr("id") == "yearto" || $(this).attr("id") == "monthto") {
		var c = $("#yearfrom").val(),
			a = $("#monthfrom").val(),
			b = $("#yearto").val(),
			f = $("#monthto").val();
		if (b == "") {
			$("#graduationmonth").hide();
			$("#leavemonth").hide();
			$("#projectmonthto").hide()
		} else {
			$("#graduationmonth").show();
			$("#leavemonth").show();
			$("#projectmonthto").show()
		}
		if (((b == "") ^ (f == "")) && b != "") {
			inputPrompt($("#graduationtime"), "请正确选择结束时间");
			inputPrompt($("#leavetime"), "请选择正确离职时间");
			inputPrompt($("#timeto"), "请选择正确项目结束时间");
			return false
		}
		if (b != "" && f != "") {
			var e = a.length == 1 ? c + "0" + a : c + a;
			var d = f.length == 1 ? b + "0" + f : b + f;
			nowdate = getYM();
			if (d > nowdate) {
				inputPrompt($("#leavetime"), "离职时间不能选择未来时间");
				inputPrompt($("#timeto"), "结束时间不能选择未来时间");
				clearPrompt($("#graduationtime"));
				return false
			}
			if (d < e) {
				inputPrompt($("#graduationtime"), "结束时间不能小于开始时间");
				inputPrompt($("#leavetime"), "离职时间不能小于入职时间");
				inputPrompt($("#timeto"), "项目结束时间不能小于入职时间");
				return false
			}
		}
		clearPrompt($("#graduationtime"));
		clearPrompt($("#leavetime"));
		clearPrompt($("#timeto"));
		return false
	}
	if ($(this).attr("id") == "salarytype") {
		clearPrompt($("#sal"));
		$("#customizesalary").css("display", "none");
		switch ($(this).val()) {
			case "1":
				if ($("#monthsal").val() == "" || ($("#monthsal").val() == "customize" && ($("#sal_min").val() == "" || $("#sal_max").val() == ""))) {
					inputPrompt($("#sal"), "请选择期望薪资")
				}
				if ($("#monthsal").val() == "customize") {
					$("#customizesalary").css("display", "block")
				}
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				if ($("#yearsal").val() == "" || ($("#yearsal").val() == "customize" && ($("#sal_min").val() == "" || $("#sal_max").val() == ""))) {
					inputPrompt($("#sal"), "请选择期望薪资")
				}
				if ($("#yearsal").val() == "customize") {
					$("#customizesalary").css("display", "block")
				}
				break
		}
	}
});
var simpleAssociation = function (a) {
	$(".asslist").find("div").html("");
	var c = $('input[name="' + a + '"]').attr("value");
	var b = 0;
	var d = "";
	if (c) {
		$.ajax({
			url: "//kwdsrv.51job.com/KwdSrvByKey/default.aspx",
			data: {
				kwd: c,
				src: a,
				rand: Math.random()
			},
			dataType: "jsonp",
			cache: false,
			async: false,
			timeout: 1000,
			success: function (f, g, e) {
				if ("1" == f.message) {
					if ("" != f.content) {
						$.each(unescape(f.content).split("\t"), function (h, j) {
							if (b < 4) {
								d += '<span class="ae at ' + a + '" onclick ="subvalue(this)" value ="' + j + '">' + j + "</span>";
								b++
							}
						});
						$("." + a + ".cn").parent().show();
						$("." + a + ".cn").append(d)
					} else {
						$("." + a + ".cn").parent().hide()
					}
				}
			}
		})
	} else {
		$("." + a + ".cn").parent().hide()
	}
};
var simpleAssociationforintent = function (a) {
	$(".job_ass").empty();
	var c = $('input[name="' + a + '"]').attr("value");
	var b = 0;
	var d = "";
	if (c) {
		$.ajax({
			url: "//kwdsrv.51job.com/KwdSrvByKey/default.aspx",
			data: {
				kwd: c,
				src: a,
				rand: Math.random()
			},
			dataType: "jsonp",
			cache: false,
			async: false,
			timeout: 1000,
			success: function (f, g, e) {
				if ("1" == f.message) {
					if ("" != f.content) {
						d += "<ul>";
						$.each(unescape(f.content).split("\t"), function (h, j) {
							if (b < 4) {
								d += '<li class="' + a + '" onclick ="subvalue(this)" value ="' + j + '">' + j + "</li>";
								b++
							}
						});
						d += "</ul>";
						$(".job_ass").show();
						$(".job_ass").append(d)
					}
				}
			}
		})
	} else {
		$(".job_ass").hide()
	}
};
$("html").click(function () {
	$(".asslist").find("div").html("");
	$(".asslist").hide();
	if ($_CONFIG.template == "resume/jobintent" || $_CONFIG.template == "resume/guide4") {
		$(".job_ass").hide()
	}
});
var subvalue = function (a) {
	if ($(a).hasClass("School")) {
		$("input[name='School']").val($(a).attr("value"));
		$("input[name='School']").focus()
	} else {
		if ($(a).hasClass("Major")) {
			$("input[name='Major']").val($(a).attr("value"));
			$("input[name='Major']").focus()
		} else {
			if ($(a).hasClass("Company")) {
				$("input[name='Company']").val($(a).attr("value"));
				$("input[name='Company']").focus()
			} else {
				if ($(a).hasClass("Job")) {
					$("input[name='Job']").val($(a).attr("value"));
					$("input[name='Job']").focus()
				}
			}
		}
	}
	$(".cn").html("");
	if ($_CONFIG.template == "resume/jobintent" || $_CONFIG.template == "resume/guide4") {
		$(".job_ass").empty()
	}
};
var showdetail = function (a) {
	if (!$("#showdetail").hasClass("on")) {
		$("#showdetail").addClass("on");
		$(".custom").show()
	} else {
		$("#showdetail").removeClass("on");
		$(".custom").hide()
	}
};
var getYM = function () {
	var a = new Date;
	var b = a.getFullYear(),
		d = a.getMonth() + 1;
	d = (d < 10 ? "0" + d : d);
	var c = (b.toString() + d.toString());
	return c
};
var checkSalary = function (a) {
	if ((!checkNumber(a)) || (a.indexOf(".") > 1 && a.substr(0, 1) == "0") || $.trim(a).length > 6 || Number(a) > 999.99 || (a.indexOf(".") > -1 && a.indexOf(".") < $.trim(a).length - 3) || a.indexOf(".") == -1 && a.substr(0, 1) == "0" || Number(a) < 0 || a.substr(0, 1) == "+") {
		return false
	}
	return true
};

function checkNumber(c) {
	var b = arguments[1] || "";
	if (isNaN(c)) {
		return false
	}
	if (b == "int") {
		var a = /^[1-9][0-9]*$/;
		if (!c.match(a)) {
			return false
		}
	}
	if (b == "numberchar") {
		var a = /^[0-9][0-9]*$/;
		if (!c.match(a)) {
			return false
		}
	}
	return true
}
$(".pclear").click(function () {
	if ($(this).parent().siblings().hasClass("asslist")) {
		$(".cn").html("")
	}
});
