var getAreaHtml = {
	getContent: function () {
		var b = "";
		var a = areaPopDeal.popType;
		var c = areaPopDeal.selectedArea;
		if ($("#barea").length > 0) {
			return ""
		}
		if (a == "multi") {
			b += '<div id="barea" class="cover c2 cover_fix2"><div class="top on" style="z-index:9;"><a href="javascript:void(0);" onclick="areaPopDeal.hidePop();" class="back"></a><a href="javascript:void(0);" onclick="areaPopDeal.confirmArea();" class="ok">确定</a><span>地区选择</span>';
			b += '<div class="all off">';
			b += '<div class="tit" id="selectareanum" onclick="areaPopDeal.changeMultiTab(this);">已选地区（' + c.areacode.length + "/5）<span>收起</span></div>";
			b += '<div class="con" id="selectarealist" style="display:block">';
			$.each(c.areacode, function (e, f) {
				b += '<div class="e" value="' + f + '"><em onclick="areaPopDeal.delMultiArea(this);"></em>' + c.areaname[e] + "</div>"
			});
			b += "</div></div></div>"
		} else {
			var d = a == "hukou" ? "国籍/户口选择" : "地区选择";
			b += '<div id="barea" class="cover c2 fixtop"><div class="top"><a href="javascript:void(0);" onclick="areaPopDeal.hidePop();" class="back"></a>' + d + "</div>"
		}
		b += this.getTabBar();
		b += this.getCurArea();
		b += this.getHotcity();
		b += this.getAllCityByCharacter();
		b += this.getProvince();
		b += this.getAbroad();
		b += "</div>";
		return b
	},
	getHotcity: function (f) {
		var c = ["salary/index", "resume/baseinfo", "resume/guide1", "resume/pastebaseinfo"];
		var e = "";
		var b = "";
		var d = areaPopDeal.popType;
		var g = (d == "hukou" || d == "livingarea") ? areaPopDeal.selectedArea.areacodedeal : areaPopDeal.selectedArea.areacode;
		e += '<div class="ptit" id="tag_hot"><div class="e"><span>热门城市</span></div></div>';
		e += '<div class="elist">';
		var a = 1;
		$.each(hotcity, function (h, i) {
			if (i.k == "000000") {
				if (d == "multi") {
					return true
				} else {
					if (d != "top" && $.inArray($_CONFIG.template, c) != "-1") {
						return true
					}
				}
			}
			b = $.inArray(i.k, g) != "-1" ? "mk" : "";
			e += '<div class="e hotcity ' + b + '" value="' + i.k + '"><div class="tit">' + i.v + "</div></div>";
			if (a % 3 == 0) {
				e += '<div class="clist"></div>';
				a = 0
			}
			a++
		});
		if (a > 1 && a <= 3) {
			e += '<div class="clist"></div>'
		}
		e += "</div>";
		return e
	},
	getCurArea: function () {
		var b = $_CONFIG.jobarea;
		var c = $_CONFIG.jobareaname;
		var a = "";
		if (b != "000000") {
			a += '<div class="ptit" id="tag_now"><div class="e"><span>当前地区</span></div></div>';
			a += '<div class="ncity" value="' + b + '" onclick="areaPopDeal.setArea(this, \'curr\');"><span>' + c + "</span></div>"
		}
		return a
	},
	getAllCityByCharacter: function () {
		var c = "";
		var a = 1;
		var b = areaPopDeal.popType;
		var d = (b == "hukou" || b == "livingarea") ? areaPopDeal.selectedArea.areacodedeal : areaPopDeal.selectedArea.areacode;
		$.each(allCityByCharacter, function (e, f) {
			c += '<div class="ptit" id="tag_' + e + '"><div class="e"><span>' + e + "</span></div></div>";
			c += '<div class="elist">';
			a = 1;
			$.each(f, function (g, h) {
				isselect = $.inArray(h.k, d) != "-1" ? "mk" : "";
				c += '<div class="e character ' + isselect + '" value="' + h.k + '"><div class="tit">' + h.v + "</div></div>";
				if (a % 3 == 0) {
					c += '<div class="clist"></div>';
					a = 0
				}
				a++
			});
			if (a > 1 && a <= 3) {
				c += '<div class="clist"></div>'
			}
			c += "</div>"
		});
		return c
	},
	getProvince: function () {
		var a = 1;
		var c = areaPopDeal.popType;
		var d = (c == "hukou" || c == "livingarea") ? areaPopDeal.selectedArea.areacodedeal : areaPopDeal.selectedArea.areacode;
		var b = "";
		b += '<div class="ptit" id="tag_province"><div class="e"><span>省份</span></div></div>';
		b += '<div class="elist">';
		$.each(allProvince, function (e, f) {
			if (f.k == "360000") {
				return true
			}
			isselect = $.inArray(f.k, d) != "-1" ? "mk" : "";
			b += '<div class="e province ' + isselect + '" value="' + f.k + '"><div class="tit">' + f.v + "</div></div>";
			if (a % 3 == 0) {
				b += '<div class="clist"></div>';
				a = 0
			}
			a++
		});
		if (a > 1 && a <= 3) {
			b += '<div class="clist"></div>'
		}
		b += "</div>";
		return b
	},
	getAbroad: function () {
		var c = "";
		c += '<div class="ptit" id="tag_abroad"><div class="e"><span>国外</span></div></div>';
		c += '<div class="elist">';
		var b = areaPopDeal.popType;
		var a = 1;
		var d = (b == "hukou" || b == "livingarea") ? areaPopDeal.selectedArea.areacodedeal : areaPopDeal.selectedArea.areacode;
		if (b == "hukou") {
			$.each(continent, function (e, f) {
				isselect = $.inArray(f.k, d) != "-1" ? "mk" : "";
				c += '<div class="e abroad ' + isselect + '" value="' + f.k + '"><div class="tit">' + f.v + "</div></div>";
				if (a % 3 == 0) {
					c += '<div class="clist"></div>';
					a = 0
				}
				a++
			})
		} else {
			isselect = $.inArray("360000", d) != "-1" ? "mk" : "";
			c += '<div class="e abroad ' + isselect + '" value="360000"><div class="tit">国外</div></div>'
		}
		if (a > 1 && a <= 3) {
			c += '<div class="clist"></div>'
		}
		return c
	},
	getTabBar: function () {
		var b = "";
		var a = $_CONFIG.jobarea;
		b += '<div class="bar">';
		if (a != "000000") {
			b += '<span value="now" id="now" style="color:#2d86d7;">当前</span><span value="hot" id="hot">热门</span>'
		} else {
			b += '<span value="hot" id="hot" style="color:#2d86d7;">热门</span>'
		}
		$.each(allCityByCharacter, function (c, d) {
			b += '<span value="' + c + '" id="' + c + '">' + c + "</span>"
		});
		b += '<span value="province" id="province">省份</span><span value="abroad" id="abroad">国外</span>';
		b += "</div>";
		return b
	}
};
var areaPopDeal = {
	popType: "",
	tabObj: {},
	selectedArea: {},
	setConfig: function (b, a, c) {
		this.popType = a, this.tabObj = b;
		this.selectedArea = c;
		return true
	},
	setArea: function (e, a) {
		a = (typeof (a) == "undefined" || a == "") ? "" : a;
		var c = this.popType;
		var d = a == "curr" ? $_CONFIG.jobarea : $(e).attr("value");
		var b = a == "curr" ? $_CONFIG.jobareaname : $(e).children(".tit").text();
		switch (c) {
			case "top":
				this.changeLocation(d, b);
				break;
			case "single":
				$(this.tabObj).text(b).removeClass("c_default");
				$(this.tabObj).next().val(d);
				this.hidePop();
				break;
			case "multi":
				this.selectArea(e);
				break;
			case "hukou":
			case "livingarea":
				if (a == "curr") {
					$(this.tabObj).text(b).removeClass("c_default");
					$(this.tabObj).next().val(d);
					this.hidePop();
					return false
				}
				if (d.substr(0, 2) == "36") {
					var f = abroad[d]
				} else {
					var f = allCityHasDistrict[d]
				}
				if (typeof (f) == "undefined") {
					$(this.tabObj).text(b).removeClass("c_default");
					$(this.tabObj).next().val(d);
					this.hidePop()
				} else {
					if (c == "hukou" && d.substr(0, 2) != "36") {
						$(this.tabObj).text(b).removeClass("c_default");
						$(this.tabObj).next().val(d);
						this.hidePop()
					} else {
						this.showChildrenArea(e)
					}
				}
				break
		}
		return false
	},
	selectArea: function (h) {
		var g = this.selectedArea;
		var e = $(h).attr("value");
		var c = $(h).children(".tit").length > 0 ? $(h).children(".tit").text() : $(h).children("span").text();
		var d = "";
		var b = [];
		if ($.inArray(e, g.areacode) != "-1") {
			$.each(g.areacode, function (i, j) {
				if (j == e) {
					g.areacode.splice(i, 1);
					g.areaname.splice(i, 1);
					return false
				}
			});
			$(".elist .e").each(function (i) {
				if ($(this).attr("value") == e) {
					$(this).removeClass("mk")
				}
			});
			$("#selectarealist .e").each(function (i) {
				if ($(this).attr("value") == e) {
					$(this).remove();
					return false
				}
			})
		} else {
			var a = e.substr(0, 2);
			if ($.inArray(a, ["01", "02", "04", "05", "06"]) == "-1") {
				if (e.substr(2, 2) != "00" && e.substr(4, 2) == "00") {
					$.each(g.areacode, function (i, j) {
						if (a == j.substr(0, 2) && j.substr(2, 4) == "0000") {
							b.push(j)
						}
					})
				} else {
					if (e.substr(2, 4) == "0000") {
						$.each(g.areacode, function (i, j) {
							if (a == j.substr(0, 2) && j.substr(2, 2) != "00" && j.substr(4, 2) == "00") {
								b.push(j)
							}
						})
					}
				}
				if (b.length <= 0 && g.areacode.length >= 5) {
					pop.ini([
						["您已经选择了5个地区，如需更换，请取消后重新选择！", "warn"]
					], ["确定"]);
					return false
				}
				$.each(b, function (i, j) {
					$.each(g.areacode, function (m, l) {
						if (j == l) {
							g.areacode.splice(m, 1);
							g.areaname.splice(m, 1);
							return false
						}
					});
					$(".elist .e").each(function (k) {
						if ($(this).attr("value") == j) {
							$(this).removeClass("mk")
						}
					});
					$("#selectarealist .e").each(function (k) {
						if ($(this).attr("value") == j) {
							$(this).remove()
						}
					})
				})
			} else {
				if (g.areacode.length >= 5) {
					pop.ini([
						["您已经选择了5个地区，如需更换，请取消后重新选择！", "warn"]
					], ["确定"]);
					return false
				}
			}
			g.areacode.push(e);
			g.areaname.push(c);
			$(".elist .e").each(function (i) {
				if ($(this).attr("value") == e) {
					$(this).addClass("mk")
				}
			});
			d += '<div class="e" value="' + e + '"><em onclick="areaPopDeal.delMultiArea(this);"></em>' + c + "</div>";
			$("#selectarealist").append(d)
		}
		var f = $("#selectareanum").parent().hasClass("off") ? "收起" : "展开";
		$("#selectareanum").html("已选地区（" + g.areacode.length + "/5）<span>" + f + "</span>");
		this.selectedArea = g;
		return false
	},
	changeLocation: function (c, b) {
		var a = {
			areacode: c,
			areaname: b
		};
		ajaxRequest($_CONFIG.domain + "ajax/in/location.ajax.php/?areacode=" + c + "&areaname=" + b);
		storage.json_set("location", a);
		if ("search/joblist" == $_CONFIG.template || "company/joblist" == $_CONFIG.template) {
			$("input[name='jobarea']").val(c);
			$("input[name='landmark']").val("");
			$("input[name='lonlat']").val("");
			$("input[name='tubename']").val("");
			$("input[name='tubeline']").val("");
			$("input[name='radius']").val("");
			$("form[name='jobform']").submit();
			return false
		} else {
			window.location.reload();
			scrollTo(0, 0)
		}
	},
	showAreaPop: function (c, b) {
		b = (typeof (b) == "undefined" || b == "") ? "single" : b;
		var d = this.areaDeal(c, b);
		this.setConfig(c, b, d);
		var a = getAreaHtml.getContent();
		if (b == "multi") {
			$("#shadow").before(a)
		} else {
			$("body").append(a)
		}
		this.showPop();
		$(".hotcity, .character, .province, .abroad").click(function () {
			areaPopDeal.setArea(this);
			return false
		});
		return false
	},
	areaDeal: function (e, c) {
		var b = (c == "top") ? $_CONFIG.jobarea : $(e).next().val();
		var d = b.length > 0 ? b.split(",") : [];
		var f = (c == "top") ? $_CONFIG.jobareaname : (b.length > 0 ? $(e).text().split(",") : []);
		var a = {
			areacode: d,
			areacodedeal: [],
			areaname: f
		};
		if (c == "hukou" || c == "livingarea") {
			$.each(d, function (h, i) {
				var g = i.substr(0, 2);
				if ($.inArray(g, ["01", "02", "04", "05", "06"]) != "-1") {
					if (i.substr(2, 2) != "00") {
						a.areacodedeal.push(g + "0000")
					} else {
						a.areacodedeal.push(i)
					}
				} else {
					if (i.substr(4, 2) != "00") {
						a.areacodedeal.push(i.substr(0, 4) + "00")
					} else {
						a.areacodedeal.push(i)
					}
				}
			})
		}
		return a
	},
	showPop: function () {
		$(".calendar ").hide();
		$("#pageContent").hide();
		$("#pageWp").hide();
		$("#barea").show();
		scrollTo(0, 0);
		rightChange();
		return false
	},
	hidePop: function () {
		$("#pageContent").show();
		$("#pageWp").show();
		$("#barea").remove();
		scrollTo(0, 0);
		return false
	},
	changeMultiTab: function (a) {
		if ($(a).parent().hasClass("on")) {
			$(a).parent().removeClass("on").addClass("off");
			$(a).next().show();
			$(a).children("span").text("收起");
			$("#barea .top").css("z-index", 9)
		} else {
			$(a).parent().removeClass("off").addClass("on");
			$(a).next().hide();
			$(a).children("span").text("展开");
			$("#barea .top").css("z-index", 3)
		}
		return true
	},
	delMultiArea: function (c) {
		var d = $(c).parent();
		var a = d.attr("value");
		var e = this.selectedArea;
		$.each(e.areacode, function (f, g) {
			if (a == g) {
				e.areacode.splice(f, 1);
				e.areaname.splice(f, 1);
				return false
			}
		});
		$(".elist .e").each(function (f) {
			if ($(this).attr("value") == a) {
				$(this).removeClass("mk")
			}
		});
		d.remove();
		var b = $("#selectareanum").parent().hasClass("off") ? "收起" : "展开";
		$("#selectareanum").html("已选地区（" + e.areacode.length + "/5）<span>" + b + "</span>");
		this.selectedArea = e;
		return true
	},
	confirmArea: function () {
		var a = this.selectedArea;
		if (a.areacode.length > 0) {
			$(this.tabObj).text(a.areaname).removeClass("c_default")
		} else {
			$(this.tabObj).text("请选择").addClass("c_default")
		}
		$(this.tabObj).next().val(a.areacode);
		this.hidePop();
		return true
	},
	showChildrenArea: function (f) {
		var c = findNextTab(f, "clist");
		if ($(f).hasClass("on")) {
			$(f).removeClass("on");
			$(c).hide();
			return true
		} else {
			$(".hotcity,.character,.province,.abroad").removeClass("on");
			$(".clist").hide();
			$(f).addClass("on")
		}
		var e = $(f).attr("value");
		var b = $(f).children(".tit").text();
		var d = "";
		var a = "";
		var h = areaPopDeal.selectedArea.areacode;
		if (e.substr(0, 2) == "36") {
			var g = abroad[e]
		} else {
			var g = allCityHasDistrict[e]
		}
		$.each(g, function (i, j) {
			a = $.inArray(j.k, h) != "-1" ? "ok" : "";
			d += '<a href="javascript:void(0);" class="region ' + a + '" value="' + j.k + '">' + (e == j.k ? "所有" : j.v) + "</a>"
		});
		$(c).empty().append(d).show();
		$(".region").click(function () {
			areaPopDeal.setRegionArea(this);
			return false
		});
		return true
	},
	setRegionArea: function (c) {
		var b = $(c).attr("value");
		var a = $(c).text();
		if (a == "所有") {
			if (b.substr(0, 2) == "36") {
				var d = abroad[b]
			} else {
				var d = allCityHasDistrict[b]
			}
			a = d[0]["v"]
		}
		$(this.tabObj).text(a).removeClass("c_default");
		$(this.tabObj).next().val(b);
		this.hidePop();
		return true
	}
};
var findNextTab = function (d, a) {
	var b = $(d).next();
	var c = 1;
	while (b.length) {
		if (b.hasClass(a)) {
			break
		} else {
			b = b.next()
		}
	}
	return b
};
var rightChange = function () {
	if ($(".bar").length < 1) {
		return false
	}
	var a = "";
	var d = "";
	var b = "";
	var c = $(window).height() - $(".ptit").position().top - 24;
	$(".bar span").css("line-height", Math.floor(c / $(".bar span").length) + "px");
	$(".bar .on").attr("data-attr", $(".bar .on").html());
	clearSpanStyle();
	$(".bar span").click(function () {
		a = $(this).html();
		d = $(this).attr("value");
		b = $("#barea .top").height();
		$(this).addClass("on").siblings("span").removeClass("on").css("color", "");
		$(this).attr("data-attr", a);
		$(window).scrollTop($("#tag_" + d).offset().top - b);
		clearSpanStyle()
	});
	return true
};
var clearSpanStyle = function () {
	setTimeout(function () {
		$(".bar span").siblings(".on").css("color", "#2d86d7").removeClass("on")
	}, 500)
};
$(window).scroll(function () {
	if ($("#barea").length <= 0) {
		return false
	}
	var d = $(window).scrollTop();
	var b = "";
	var c = "";
	var a = $("#barea .top").length > 0 ? $("#barea .top").height() : 0;
	$(".ptit").each(function (i) {
		b = $(this).offset().top;
		c = $(this).next().offset().top;
		if (d >= (b - a) && d <= c) {
			var h = $(this).attr("id");
			var g = h.split("_")[1];
			var f = $("#" + g).html();
			$(".bar span").removeClass("on").css("color", "");
			$("#" + g).addClass("on").attr("data-attr", f);
			return false
		}
	});
	setTimeout(function () {
		if (d == $(window).scrollTop()) {
			clearSpanStyle()
		}
	}, 500);
	return false
});
