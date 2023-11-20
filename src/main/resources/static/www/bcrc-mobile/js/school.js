$(document).ready(function () {
	var a = getQueryString("tab");
	var b = 0;
	if (a == "practice") {
		var b = 1
	}
	$(".jnav .n").each(function () {
		if ($(this).index() == b) {
			$(this).addClass("on")
		}
	});
	$(".rpbox").hide();
	$(".rpbox").eq(b).show();
	$(".jnav .n").click(function () {
		var c = $(this).index();
		$(this).addClass("on").siblings().removeClass("on");
		$(".rpbox").hide();
		$(".rpbox").eq(c).show()
	});
//	$(".add_honour").click(function () {
//		var c = $("#userid").val();
//		location.href = $_CONFIG.domain + "resume/schoolhonour.php?userid=" + c
//	});
	$(".edit_honour").click(function () {
		var d = $("#userid").val();
		var c = $(this).attr("id");
		location.href = $_CONFIG.domain + "resume/schoolhonour.php?userid=" + d + "&honourid=" + c
	});
	$(".delete_honour").click(function () {
		var c = $(this).attr("id");
		var d = $(this).parents(".e").find("strong").html();
		var e = $(this).parents(".e").find("i").text();
		var f = {
			title: "是否删除以下校内荣誉？",
			msg: d + '</br></br><span class="c_orange">' + e + "</span>"
		};
		pop.confirmdel(f, ["deletehonour(" + c + ")"])
	});
	$(".saveschoolhonour").click(function () {
		var l = $(this).attr("click-data");
		if (l != 1) {
			return false
		}
		$(this).attr("click-data", 0);
		var g = $("#userid").val();
		var n = $("#honourid").val();
		var f = $("#bonustime").text();
		var j = $("#cbonusname").val();
		var i = $("#cbonusclass").val();
		var k = window.location.href;
		var e = {
			userid: g,
			honourid: n,
			bonustime: f,
			cbonusname: j,
			cbonusclass: i,
			jumpurl: k,
			operate: ""
		};
		var c = $_CONFIG.domain + "ajax/resume/schoolhonour.ajax.php";
		var o = ajaxRequest(c, e, "post", "json");
		if (o.status == "-1") {
			window.location.href = o.url;
			return false
		} else {
			if (o.status == "1") {
				window.location.href = $_CONFIG.domain + "resume/school.php?userid=" + g + "&tab=honour"
			} else {
				if (o.desc) {
					pop.ini([
						[o.desc, "warn"]
					], ["确定"])
				} else {
					if (o.message) {
						var h = "";
						var d = 0;
						$.each(o.message, function (m, p) {
							if (d < 1) {
								h = p;
								d++
							}
							$("#err_" + m + " span").text(p);
							$("#err_" + m).show()
						});
						pop.ini([
							[h, "warn"]
						], ["确定"])
					}
				}
				if ($(".saveschoolhonour").length > 0) {
					$(".saveschoolhonour").attr("click-data", 1)
				}
			}
		}
		return false
	});
	$(".edit_practice").click(function () {
		var c = $("#userid").val();
		var d = $(this).attr("id");
		location.href = $_CONFIG.domain + "resume/schoolpractice.php?userid=" + c + "&practiceid=" + d
	});
//	$(".add_job").click(function () {
//		var c = $("#userid").val();
//		location.href = $_CONFIG.domain + "resume/schoolpractice.php?userid=" + c
//	});
	$(".delete_practice").click(function () {
		var d = $(this).attr("id");
		var c = $(this).parents(".e").find("strong").html();
		var e = $(this).parents(".e").find("i").text();
		var f = {
			title: "是否删除以下校内职务？",
			msg: c + '</br></br><span class="c_orange">' + e + "</span>"
		};
		pop.confirmdel(f, ["deletepractice(" + d + ")"])
	});
	$(".savepractrice").click(function () {
		var o = $(this).attr("click-data");
		if (o != 1) {
			return false
		}
		$(this).attr("click-data", 0);
		var h = $("#userid").val();
		var l = $("#starttime").text();
		var e = $("#endtime").text();
		var j = $("#cpracticename").val();
		var c = $("#cdescribe").val();
		var i = $("#practiceid").val();
		var n = window.location.href;
		var g = {
			userid: h,
			practiceid: i,
			starttime: l,
			endtime: e,
			cname: j,
			cdescribe: c,
			jumpurl: n,
			operate: ""
		};
		var d = $_CONFIG.domain + "ajax/resume/schoolpractice.ajax.php";
		var p = ajaxRequest(d, g, "post", "json");
		if (p.status == "-1") {
			window.location.href = p.url;
			return false
		} else {
			if (p.status == "1") {
				window.location.href = $_CONFIG.domain + "resume/school.php?tab=practice&userid=" + h
			} else {
				if (p.desc) {
					pop.ini([
						[p.desc, "warn"]
					], ["确定"])
				} else {
					if (p.message) {
						var k = "";
						var f = 0;
						$.each(p.message, function (m, q) {
							if (f < 1) {
								k = q;
								f++
							}
							$("#err_" + m + " span").text(q);
							$("#err_" + m).show()
						});
						pop.ini([
							[k, "warn"]
						], ["确定"])
					}
				}
				if ($(".savepractrice").length > 0) {
					$(".savepractrice").attr("click-data", 1)
				}
			}
		}
		return false
	})
});

function deletehonour(d) {
	var c = $("#userid").val();
	var b = window.location.href;
	var f = {
		userid: c,
		honourid: d,
		operate: "delete",
		jumpurl: b
	};
	var e = $_CONFIG.domain + "ajax/resume/schoolhonour.ajax.php";
	var a = ajaxRequest(e, f, "post", "json");
	if (a.status == "1") {
		pop.ini([
			[a.desc]
		], ["确定"]);
		$("#schoolhonour_" + d).remove();
		if ($(".honourbox").children(".e").length >= 20) {
			$(".add_honour").hide()
		} else {
			$(".add_honour").show()
		}
	} else {
		pop.ini([
			["服务器正忙，请稍后再试"]
		], ["确定"])
	}
}

function deletepractice(e) {
	var c = $("#userid").val();
	var b = window.location.href;
	var f = {
		userid: c,
		practiceid: e,
		operate: "delete",
		jumpurl: b
	};
	var d = $_CONFIG.domain + "ajax/resume/schoolpractice.ajax.php";
	var a = ajaxRequest(d, f, "post", "json");
	if (a.status == "1") {
		pop.ini([
			[a.desc]
		], ["确定"]);
		$("#schoolpractice_" + e).remove();
		if ($(".practricerbox").children(".e").length >= 20) {
			$(".add_job").hide()
		} else {
			$(".add_job").show()
		}
	} else {
		pop.ini([
			["服务器正忙，请稍后再试"]
		], ["确定"])
	}
}

function getQueryString(a) {
	var b = new RegExp("(^|&)" + a + "=([^&]*)(&|$)", "i");
	var c = window.location.search.substr(1).match(b);
	if (c != null) {
		return unescape(c[2])
	}
	return null
};