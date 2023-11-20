$(document).ready(function () {
	var a = $_CONFIG.template;
	if (a == "my/shieldcompany" || a == "my/searchshieldcompany") {
		alert(1);
		var b = $("#total").val();
		if (b > 0) {
			$("#pageWp").addClass("hhint")
		}
		$(".coid").click(function () {
			$(this).toggleClass("checked");
			return false
		});
		$(".qx").click(function () {
			var d = false;
			$(".coid").each(function () {
				if (!$(this).hasClass("checked")) {
					d = true;
					return false
				}
			});
			if (d) {
				$(this).addClass("on");
				$(".coid").addClass("checked")
			} else {
				$(this).removeClass("on");
				$(".coid").removeClass("checked")
			}
		});
		$(".del").click(function () {
			if ($(".checked").length < 1) {
				pop.ini([
					["你没有选择任何公司", "warn"]
				], ["确定"]);
				return false
			}
			pop.ini(["你确定要删除吗？"], [
				["取消", "确定"],
				["pop.close()", "delshieldcompany()"]
			])
		});
		$(".add").click(function () {
			if ($(".checked").length < 1) {
				pop.ini([
					["你没有选择任何公司", "warn"]
				], ["确定"]);
				return false
			}
			addshieldcompany()
		});
		$(".j").click(function () {
			gotosearch()
		})
	} else {
		
		if (a == "my/hrletter") {
			alert(2);
			$(".close").click(function () {
				var e = $(this).attr("value");
				var d = "";
				$(".close").each(function (f) {
					if ($(this).attr("value") == e) {
						d = f;
						return false
					}
				});
				pop.ini(["你确定要删除吗？"], [
					["取消", "确定"],
					["pop.close()", "delmessage(" + e + "," + d + ")"]
				])
			})
		} else {
			if (a == "my/mycollection") {
				var c = $("input[name='isadd']").val();
				if (c == 1) {
					$("#pageWp").addClass("hfloat")
				}
				$(".jobid").click(function () {
					$(this).toggleClass("checked");
					return false
				});
				$(".qx").click(function () {
					var d = false;
					$(".jobid").each(function () {
						if (!$(this).hasClass("checked")) {
							d = true;
							return false
						}
					});
					if (d) {
						$(this).addClass("on");
						$(".jobid").addClass("checked")
					} else {
						$(this).removeClass("on");
						$(".jobid").removeClass("checked")
					}
					return false
				});
				$(".remove").click(function () {
					
					if ($(".checked").length < 1) {
						pop.ini([
							["你没有选择任何职位", "warn"]
						], ["确定"]);
						return false
					}
					pop.ini(["你确定要删除吗？"], [
						["取消", "确定"],
						["pop.close()", "delcollection()"]
					])
				});
				$(document).on("click", ".toapply", function () {
					if ($(".checked").length < 1) {
						pop.ini([
							["你没有选择任何职位", "warn"]
						], ["确定"]);
						return false
					}
					var d = new Array();
					$(".checked").each(function () {
						d.push($(this).attr("value"))
					});
					jobid = d.toString();
					applyjob(jobid)
				});
				$(".apply_job").click(function () {
					if ($(".checked").length < 1) {
						pop.ini([
							["你没有选择任何职位", "warn"]
						], ["确定"]);
						return false
					}
					var d = new Array();
					$(".checked").each(function () {
						d.push($(this).attr("value"))
					});
					jobid = d.toString();
					applyjob(jobid)
				});
				$(".favorite").click(function () {
					var d = $(this).siblings(".cname").text();
					var e = $(this).attr("data-id");
					var g = $(this).attr("data-type");
					var f = $(this).attr("data-ind");
					pop.ini(["是否取消收藏心愿公司 “" + d + "”"], [
						["取消", "确定"],
						["pop.close()", "delFavoriteCo('" + e + "', '" + g + "','" + f + "');"]
					]);
					return false
				})
			} else {
				if (a == "my/applyhistory") {
					$(".pipei").click(function () {
						var i = $(this).attr("value");
						var f = $(this).attr("value1");
						var e = window.location.href;
						var g = $_CONFIG.domain + "ajax/search/compete.ajax.php";
						var h = {
							jobid: i,
							url: e,
							resumeid: f
						};
						var d = ajaxRequest(g, h, "post", "json");
						if (d.status == 1) {
							location.href = $_CONFIG.domain + "my/rsmmatch.php?jobid=" + i + "&resumeid=" + f;
							return false
						} else {
							if (d.url) {
								location.href = d.url
							} else {
								pop.ini([
									[d.desc, "warn"]
								], ["确定"])
							}
							return false
						}
					})
				} else {
					if (a == "my/my51job") {
						$("#pageWp").addClass("htab")
					}
				}
			}
		}
	}
});
var delshieldcompany = function () {
	var c = new Array();
	var d = new Array();
	$(".checked").each(function () {
		if ($(this).attr("data-type") == 1) {
			c.push($(this).attr("value"))
		} else {
			d.push($(this).next().text())
		}
	});
	coid = c.toString();
	keyword = JSON.stringify(d);
	var b = $_CONFIG.domain + "/ajax/my/shieldcompany.ajax.php";
	var e = {
		coid: coid,
		keyword: keyword,
		type: 1
	};
	var a = ajaxRequest(b, e, "post", "json");
	if (a.status == 1) {
		$(".checked").parent().remove();
		pop.close();
		if ($(".coid").length < 1) {
			window.location.reload()
		}
		return false
	} else {
		if (a.url) {
			location.href = a.url;
			return false
		}
		pop.ini([
			[a.desc, "warn"]
		], ["确定"])
	}
};
var delmessage = function (d, b) {
	var f = $("#pageno").val();
	var c = $_CONFIG.domain + "/ajax/my/delhrletter.ajax.php";
	var e = {
		msgid: d
	};
	var a = ajaxRequest(c, e, "post", "json");
	if (a.status == 1) {
		$(".p").eq(b).remove();
		if ($(".close").length < 1) {
			f = f > 1 ? f - 1 : f;
			window.location.href = $_CONFIG.domain + "/my/hrletter.php?pageno=" + f
		}
		pop.close()
	} else {
		if (a.url) {
			location.href = a.url;
			return false
		}
		pop.ini([
			[a.desc, "warn"]
		], ["确定"])
	}
};
var delcollection = function () {
	var f = $("#pageno").val();
	var c = new Array();
	var b = window.location.href;
	$(".checked").each(function () {
		c.push($(this).attr("value"))
	});
	jobid = c.toString();
	var d = $_CONFIG.domain + "/ajax/my/mycollection.ajax.php";
	var e = {
		jobid: jobid,
		url: b,
		type: 1
	};
	var a = ajaxRequest(d, e, "post", "json");
	if (a.status == 1) {
		$(".checked").parent().remove();
		pop.close();
		if ($(".jobid").length < 1) {
			f = f > 1 ? f - 1 : f;
			window.location.href = $_CONFIG.domain + "/my/mycollection.php?pageno=" + f
		}
		return false
	} else {
		if (a.url) {
			location.href = a.url;
			return false
		}
		pop.ini([
			["删除失败", "warn"]
		], ["确定"])
	}
};

function hint() {
	pop.ini([
		["未提交的申请请访问电脑版", "warn"]
	], ["确定"])
}

function gotosearch() {
	var a = $("#keyword").val();
	if ($.trim(a) == "") {
		pop.ini([
			["请输入关键字", "warn"]
		], ["确定"]);
		return false
	}
	document.cosearch.submit();
	return false
}

function addshieldcompany() {
	var d = new Array();
	var b = $("#keyword").val();
	$(".checked").each(function () {
		d.push($(this).attr("value"))
	});
	coid = d.toString();
	var c = $_CONFIG.domain + "ajax/my/shieldcompany.ajax.php";
	var e = {
		coid: coid,
		type: 3,
		url: $_CONFIG.domain + "searchshieldcompany.php?keyword=" + b
	};
	var a = ajaxRequest(c, e, "post", "json");
	if (a.status == 1) {
		pop.ini([
			["已成功添加到屏蔽公司"]
		], ["确定", "gotosheildcompany()"]);
		return false
	} else {
		if (a.url) {
			location.href = a.url;
			return false
		}
		pop.ini([
			[a.desc, "warn"]
		], ["确定"])
	}
}

function gotosheildcompany() {
	location.href = $_CONFIG.domain + "my/shieldcompany.php";
	pop.close();
	return false
}

function showHr(c) {
	var b = [$(c).next().val(), "HR兴趣度"],
		a = ["确定"];
	pop.ini(b, a);
	return false
}
var delFavoriteCo = function (c, f, d) {
	var b = $_CONFIG.domain + "ajax/my/editFavoriteCo.ajax.php";
	var e = {
		coid: c,
		type: f,
		indtype: d,
		action: "del"
	};
	var a = ajaxRequest(b, e, "post", "json");
	if (a.status == 1) {
		window.location.reload()
	} else {
		pop.ini([
			[a.desc, "warn"]
		], ["确定"])
	}
	return false
};
