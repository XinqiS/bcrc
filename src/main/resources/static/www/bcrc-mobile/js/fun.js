(function (a) {
	a(".aox").each(function () {
		if (a(this).find("article").height() >= 240) {
			a(this).addClass("mk")
		} else {
			a(this).find(".art_more").hide()
		}
	})
})(Zepto);
$(".art_more").click(function () {
	var a = $(this).parent();
	if (a.hasClass("on")) {
		a.removeClass("on");
		a.find(".icin").html("显示全部")
	} else {
		a.addClass("on");
		a.find(".icin").html("隐藏内容")
	}
});
var shadow = (function () {
	var a = function (c) {
		var c = c ? c : 9;
		if ($("#shadow")[0]) {
			$("#shadow").css("z-index", c).show()
		} else {
			$("body").append("<div id='shadow' style='z-index:" + c + "'></div>")
		}
	};
	var b = function () {
		$("#shadow").hide()
	};
	return {
		show: a,
		hide: b
	}
})();

function scrollway(b) {
	var a = document.body.scrollTop || document.documentElement.scrollTop,
		b = b || function () {};
	window.addEventListener("scroll", function () {
		var c = document.body.scrollTop || document.documentElement.scrollTop,
			d = c - a;
		if (d === 0) {
			return false
		}
		b(d > 0 ? "down" : "up");
		a = c
	}, false)
}
if ($(".filtertag").length > 0 && $(".search").length > 0) {
	var oFil = $(".filtertag");
	var oT = oFil.offset().top;
	$(window).scroll(function (a) {
		scrollway(function (c) {
			if (c == "down") {
				if ($(this).scrollTop() >= oT) {
					$(".search")[0].className = "search";
					if (!oFil.hasClass("on")) {
						oFil[0].className = "filtertag fix"
					}
				}
			} else {
				if (c == "up") {
					if ($(".search").length > 0) {
						var b = $(".search").offset().top;
						if ($(this).scrollTop() >= b) {
							$(".search")[0].className = "search fix";
							if (!oFil.hasClass("on")) {
								oFil[0].className = "filtertag fix f2"
							}
						}
						if ($(this).scrollTop() < oT) {
							$(".search")[0].className = "search";
							if (!oFil.hasClass("on")) {
								oFil[0].className = "filtertag"
							}
						}
					}
				}
			}
		})
	})
}
$(".filtertag .tab span").click(function (a) {
	$("#pageContent").css("z-index", "3")
}).blur(function (a) {
	$("#pageContent").css("z-index", "2")
});
var pop = (function () {
	var c = function (l, q) {
		var r = "";
		var k = typeof l == "object" ? 1 : 0;
		var i = typeof q[0] == "object" ? 1 : 0;
		if (k) {
			k = typeof l[0] == "object" ? 1 : 0;
			if (k) {
				k = l[0][0] ? l[0][0] : ""
			} else {
				k = l[0] ? l[0] : ""
			}
			r = l[1] ? l[1] : ""
		} else {
			k = l ? l : ""
		}
		if (i) {
			var o = q[1][0] ? q[1][0] : "pop.close()";
			var m = q[1][1] ? q[1][1] : "pop.close()";
			var n = '<div class="op"><span class="bb" onclick="' + o + '">' + q[0][0] + '</span><span class="bb" onclick="' + m + '">' + q[0][1] + "</span></div>"
		} else {
			var j = q[1] ? q[1] : "pop.close()";
			var n = '<div class="op o2"><span class="bb" onclick="' + j + '">' + q[0] + "</span></div>"
		}
		if ($(".popDialog")[0]) {
			if (l[0][1] == "warn") {
				$(".popDialog .cn").html('<img src="images/pop_warn.png" />' + k)
			} else {
				$(".popDialog .cn").html(k)
			}
			$(".popDialog .title").html(r);
			$(".popDialog .op").remove();
			$(".popDialog .in").append(n)
		} else {
			var p = "";
			p += '<div class="popDialog pophide"><div class="in">';
			p += '<p class="title">' + r + "</p>";
			p += '<div class="cn">';
			if (l[0][1] == "warn") {
				p += '<img src="images/pop_warn.png" />'
			}
			p += k;
			p += "</div>";
			p += n;
			p += "</div></div>";
			$("body").append(p)
		}
		shadow.show();
		$(".popDialog").show()
	};
	var a = function (j, k) {
		var i = $("." + j);
		if (i[0]) {
			i.children(".cn").html(k)
		} else {
			$("body").append('<div class="' + j + ' pophide"><div class="in"><span class="close" onclick="pop.close()"></span><div class="cn">' + k + "</div></div></div>")
		}
	};
	var h = function (j) {
		a("popMsg", j);
		shadow.show();
		$(".popMsg").show().addClass("on");
		var i = true;
		$(".popMsg")[0].addEventListener("animationend", function (k) {
			if (k.target === k.currentTarget && i) {
				i = false;
				shadow.hide();
				$(".popMsg").removeClass("on");
				$(".popMsg").hide()
			}
		}, false);
		$(".popMsg .close,#shadow").click(function () {
			$(".popMsg").removeClass("on")
		});
		g()
	};
	var e = function (i) {
		i = '<img src="' + $_CONFIG.imgpath + '/loading.gif" />' + i;
		a("popLoad", i);
		shadow.show();
		$(".popLoad").show();
		g()
	};
	var d = function (k, l, j) {
		if ($(".popNote")[0]) {
			$(".popNote").show().addClass("on").children("span").html(k)
		} else {
			var n = '<div class="popNote"><span>' + k + "</span></div>";
			$("body").append(n);
			setTimeout(function () {
				$(".popNote").addClass("on")
			}, 1)
		}
		var j = j ? j : "middle";
		var q = $(window).height();
		var m = $(".popNote").height();
		var o = (q - m) / 2;
		var i = (q - m) / 5;
		var p = (q - m) * 4 / 5;
		switch (j) {
			case "top":
				$(".popNote").css("top", i);
				break;
			case "middle":
				$(".popNote").css("top", o);
				break;
			case "bottom":
				$(".popNote").css("top", p);
				break
		}
		var l = l ? l : 1200;
		setTimeout(function () {
			$(".popNote").removeClass("on").hide()
		}, l)
	};
	var b = function (m, j) {
		var k = "";
		if ($(".popOther").length < 1) {
			k += '<div class="popOther pophide" style="display: none;">';
			k += '<div class="in">';
			k += "</div></div>";
			$("body").append(k)
		}
		var o = typeof (m.title) == "undefined" || m.title == "" ? "" : m.title;
		var n = typeof (m.msg) == "undefined" || m.msg == "" ? "" : m.msg;
		var l = typeof (m.msgother) == "undefined" || m.msgother == "" ? "" : m.msgother;
		var i = typeof (j[0]) == "undefined" || j[0] == "" ? "" : j[0];
		i = "pop.close();" + i;
		k = "";
		k += '<p class="title">' + o + "</p>";
		k += '<div class="cn">' + n + '<br><br><span class="c_orange">' + l + "</span></div>";
		k += '<div class="op">';
		k += '<span class="bb" onclick="pop.close();">取消</span><span class="bb" onclick="' + i + '">确定</span>';
		k += "</div></div>";
		$(".popOther .in").empty().append(k);
		shadow.show();
		$(".popOther").show();
		return false
	};
	var g = function () {
		$("#shadow").click(function (i) {
			pop.close()
		})
	};
	var f = function () {
		$(".pophide").hide();
		shadow.hide()
	};
	return {
		ini: c,
		msg: h,
		load: e,
		note: d,
		close: f,
		confirmdel: b
	}
})();
$(".sbox .ptxa textarea").on("input", function () {
	var c = $(this).val();
	var d = c.replace(/[^\x00-\xff]/g, "**");
	var b = Math.ceil(d.length / 2);
	var a = $(this).attr("id") == "leavereason" ? "/100" : "/1000";
	$(this).next().html(b + a)
});

function changeVerifyCode() {
	var b = new Date();
	var c = b.getTime();
	url = $("#verifyPic_img").attr("src");
	var a = $("#verifyPic_img").attr("type");
	if (a == "" || a == undefined) {
		a = 6
	}
	if (url.indexOf("?") > 0) {
		url = url.replace(/\?.*/g, "?")
	} else {
		url = url + "?"
	}
	url = url + "type=" + a + "&from_domain=i&t=" + c;
	$("#verifyPic_img").attr("src", url);
	$("#verifycode").val("");
	if (!("placeholder" in document.createElement("input"))) {
		$("#verifycode").focus().blur()
	}
	$("#verifycode_ok").hide()
}

function calendar(f, x, p, o) {
	var z = new Date();
	var s = z.getFullYear();
	var v = z.getMonth() + 1;
	var C = z.getDate();
	var a = s + "-" + v + "-" + C;
	var u = mhtm = dhtm = "";
	var l = ydown = s - x[1];
	var k = s + x[0] + 1;
	while (l < k) {
		u += '<div class="swiper-slide" title="' + l + '">' + l + "年</div>";
		l++
	}
	for (var t = 1; t < 13; t++) {
		if(t < 10 ){
		mhtm += '<div class="swiper-slide" title="' + '0' + t + '">' + '0' + t + "月</div>"
		}else{
			mhtm += '<div class="swiper-slide" title="' + t + '">' + t + "月</div>"
		}
	}
	for (var A = 1; A < 32; A++) {
		if(A < 10 ){
			dhtm += '<div class="swiper-slide" title="' + '0'+A + '">' + '0'+A + "日</div>"
		}else{
			dhtm += '<div class="swiper-slide" title="' +A + '">' +A + "日</div>"
		}
		
	}
	$(".calendar").remove();
	var n = "";
	n += '<div class="calendar cal-';
	n += f;
	n += '"><div class="cal-hd"><span class="close" onclick="cancelcalendar();">取消</span><div class="rt">';
	if (o) {
		n += '<span class="ctoday" onclick="btnfromnow(this);" data-id="' + p + '">至今</span>'
	}
	n += '<span class="ok" data-type="' + f + '" onclick="confirmcalendar(this);" data-id="' + p + '">确认</span></div></div><div class="cal-bd"><div class="swiper-wol"></div>';
	n += '<div class="cal-y swiper-container"><div class="swiper-wrapper">' + u + '</div><div class="swiper-gray"></div></div>';
	n += '<div class="cal-m swiper-container"><div class="swiper-wrapper">' + mhtm + '</div><div class="swiper-gray"></div></div>';
	n += '<div class="cal-d swiper-container"><div class="swiper-wrapper">' + dhtm + '</div><div class="swiper-gray"></div></div>';
	n += "</div></div>";
	$("body").append(n);
	$(".calendar").show();
	$(".calendar").on("touchmove", function (d) {
		d.preventDefault()
	}, false);
	var h = $("#" + p).html();
	if (h == "请选择" || h == "至今") {
		h = a
	} else {
		h = h
	}
	var B = h.split("-");
	var j = Math.abs(B[0] - ydown);
	var i = mnum = Math.abs(B[1] - 1);
	var e = Math.abs(B[0]);
	var g = Math.abs(B[2] - 1);

	function c() {
		return (e % 100 == 0 ? res = (e % 400 == 0 ? 1 : 0) : res = (e % 4 == 0 ? 1 : 0))
	}
	var q = c();
	if ($(".cal-d").css("display") == "block") {
		var b = new Swiper(".cal-d", {
			paginationClickable: true,
			direction: "vertical",
			centeredSlides: true,
			slidesPerView: 5,
			initialSlide: g
		})
	}
	var r = new Swiper(".cal-y", {
		paginationClickable: true,
		direction: "vertical",
		centeredSlides: true,
		slidesPerView: 5,
		initialSlide: j,
		onInit: function (m) {
			if ($(".cal-d").css("display") == "block") {
				var d = 31;
				if (i == 4 || i == 6 || i == 9 || i == 11) {
					d = 30
				} else {
					if (i == 2) {
						d = q + 28
					}
				}
				var E = b.slides.length;
				if (E < d) {
					var y = d - E + 1;
					for (var D = 1; D < y; D++) {
						b.appendSlide('<div class="swiper-slide" title="' + (D + E) + '">' + (D + E) + "日</div>")
					}
				} else {
					var F = E - d + 1;
					for (var D = 1; D < F; D++) {
						b.removeSlide((E - D))
					}
				}
			}
		},
		onSlideChangeEnd: function (m) {
			if ($(".cal-d").css("display") == "block") {
				e = $(".cal-y .swiper-slide-active").attr("title");
				q = c();
				if (i == 2) {
					var E = b.slides.length;
					var d = q + 28;
					if (E < d) {
						var y = d - E + 1;
						for (var D = 1; D < y; D++) {
							b.appendSlide('<div class="swiper-slide" title="' + (D + E) + '">' + (D + E) + "日</div>")
						}
					} else {
						var F = E - d + 1;
						for (var D = 1; D < F; D++) {
							b.removeSlide((E - D))
						}
					}
				}
			}
		}
	});
	if ($(".cal-m").css("display") == "block") {
		var w = new Swiper(".cal-m", {
			paginationClickable: true,
			direction: "vertical",
			centeredSlides: true,
			slidesPerView: 5,
			initialSlide: mnum,
			onSlideChangeEnd: function (m) {
				if ($(".cal-d").css("display") == "block") {
					i = $(".cal-m .swiper-slide-active").attr("title");
					var E = b.slides.length;
					var d = 31;
					if (i == 4 || i == 6 || i == 9 || i == 11) {
						d = 30
					} else {
						if (i == 2) {
							d = q + 28
						}
					}
					if (E < d) {
						var y = d - E + 1;
						for (var D = 1; D < y; D++) {
							b.appendSlide('<div class="swiper-slide" title="' + (D + E) + '">' + (D + E) + "日</div>")
						}
					} else {
						var F = E - d + 1;
						for (var D = 1; D < F; D++) {
							b.removeSlide((E - D))
						}
					}
				}
			}
		})
	}
}

function cancelcalendar() {
	$(".calendar").hide();
	return false
}

function btnfromnow(a) {
	obj = $(a).attr("data-id");
	$("#" + obj).html("至今");
	$(".calendar").hide();
	return false
}

function confirmcalendar(a) {
	type = $(a).attr("data-type");
	obj = $(a).attr("data-id");
	if ($("#" + obj).hasClass("c_default")) {
		$("#" + obj).removeClass("c_default")
	}
	switch (type) {
		case "date":
			yy = $(".cal-y .swiper-slide-active").attr("title");
			mm = $(".cal-m .swiper-slide-active").attr("title");
			dd = $(".cal-d .swiper-slide-active").attr("title");
			$("#" + obj).html(yy + "-" + mm + "-" + dd);
			break;
		case "month":
			yy = $(".cal-y .swiper-slide-active").attr("title");
			mm = $(".cal-m .swiper-slide-active").attr("title");
			$("#" + obj).html(yy + "-" + mm);
			break;
		case "year":
			yy = $(".cal-y .swiper-slide-active").attr("title");
			$("#" + obj).html(yy);
			break
	}
	if ($("#err_" + obj).length > 0) {
		$("#err_" + obj).hide()
	}
	$(".calendar").hide()
}

function orient() {
	if (window.orientation == 0 || window.orientation == 180) {
		$(".bar").show();
		return false
	} else {
		if (window.orientation == 90 || window.orientation == -90) {
			$(".bar").hide();
			return false
		}
	}
}
$(function () {
	orient()
});
$(window).bind("orientationchange", function (a) {
	orient()
});
if ($(".bar").length) {
	var H = $(window).height() - $(".ptit").position().top - 24;
	$(".bar span").css("line-height", Math.floor(H / $(".bar span").length) + "px");
	$(".bar .on").attr("data-attr", $(".bar .on").html());
	$(".bar span").click(function () {
		$(this).addClass("on").siblings("span").removeClass("on");
		$(this).attr("data-attr", $(this).html())
	})
}

function bodyScroll(a) {
	a.preventDefault()
}
var ModalHelper = (function (a) {
	var b;
	return {
		popShow: function () {
			b = $(window).scrollTop();
			$("body").addClass(a).css("top", -b + "px");
			$("#shadow").show().on("touchmove", bodyScroll, false);
			document.body.addEventListener("touchmove", bodyScroll, {
				passive: false
			})
		},
		popHide: function () {
			$("body").removeClass(a).css("top", "");
			$(window).scrollTop(b);
			$("#shadow").hide().off("touchmove", bodyScroll, false);
			document.body.removeEventListener("touchmove", bodyScroll, {
				passive: false
			})
		}
	}
})("modal-open");
