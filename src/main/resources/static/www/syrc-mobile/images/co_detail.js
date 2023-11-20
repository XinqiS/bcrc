webpackJsonp([5], {
	0: function (i, t) {},
	1: function (i, t) {},
	14: function (i, t) {},
	20: function (i, t) {},
	36: function (i, t, e) {
		"use strict";
		e(5), e(6), e(7), e(14), e(20), e(1), e(0), e(8), e(9), e(37), e(10), e(38);
		var n = function (i) {
			return i && i.__esModule ? i : {
				"default": i
			}
		}(e(11));
		window.pop = n["default"]
	},
	37: function (i, t, e) {
		"use strict";
		$(document).ready(function () {
			window.onload = function () {
				slider.init(".jc_ilst", {
					time: 700,
					width: 65
				}), $(".jc_lf").on("click", function () {
					slider.right()
				}), $(".jc_rt").on("click", function () {
					slider.left()
				})
			};
			var i = $('input[name="islogin"]').val();
			1 == $('input[name="iseffect"]').val() && 1 == i && getshieldcompanystatus(), $(".elswitch").click(function () {
				var i = 1;
				$(this).hasClass("on") ? $(this).removeClass("on") : ($(this).addClass("on"), i = 3), shieldcompany(i)
			})
		}), window.shieldcompany = function (i) {
			var t = $("#coid").val(),
				e = $("#pagecode").val(),
				n = window.location.href,
				o = $_CONFIG.domain + "ajax/my/shieldcompany.ajax.php?jsoncallback=?",
				s = {
					coid: t,
					type: i,
					url: n,
					pagecode: e,
					requesttype: "jsonp",
					t: Math.random()
				};
			$.getJSON(o, s, function (i) {
				if (1 != i.status) {
					if ($(".elswitch").toggleClass("on"), i.url) return window.location.href = i.url, !1;
					pop.ini([
						[i.desc, "warn"]
					], ["确定"])
				}
			})
		}, window.getshieldcompanystatus = function () {
			var i = $("#coid").val(),
				t = window.location.href,
				e = $_CONFIG.domain + "ajax/my/getshieldcompanystatus.ajax.php?jsoncallback=?",
				n = {
					coid: i,
					url: t,
					requesttype: "jsonp",
					t: Math.random()
				};
			$.getJSON(e, n, function (i) {
				1 == i.status && $(".elswitch").addClass("on")
			})
		}
	},
	38: function (i, t, e) {
		"use strict";

		function n(i) {
			var t = window.innerHeight - 30,
				e = $(i),
				n = e.find("img"),
				o = 0;
			var s = e.find("span").attr("title"),
				a = new Image;
			a.src = s, a.onload = function () {
				! function (i) {
					(o = i.width <= 300 ? i.height : parseInt(300 * i.height / i.width)) > t && (e.height(t), $("#imgSwiper").height(t + document.body.scrollTop), o = t)
				}(a);
				var i = (t - o) / 2 - 80;
				i = i > 0 ? i : 0, n.removeAttr("width").removeAttr("height").removeAttr("style").attr("src", s).attr("alt", "").css({
					"max-height": t,
					"margin-top": i
				})
			}
		}
		window.slider = function () {
			$ = Zepto || new Error("依赖Zepto");
			var i, t, e, n, o = 200,
				s = 0,
				a = 0,
				r = 0,
				c = 0,
				d = $(window).width() - 64;

			function l(t) {
				i.css({
					transform: "translateX(" + t + "px)"
				}), t >= 0 ? $(".jc_lf").hide() : $(".jc_lf").show(), d < t + o ? $(".jc_rt").show() : $(".jc_rt").hide()
			}

			function p(t) {
				i.css("transition", "transform " + n + "ms ease"), l(t), setTimeout(function () {
					h()
				}, n)
			}

			function h() {
				if (r > 0) l(0), r = 0;
				else if (Math.abs(r) > Math.abs(t - i.width())) l(r = -Math.abs(t - i.width()));
				else {
					l(Math.round(r / e) * e)
				}
			}
			return {
				init: function (p, w) {
					(i = $('<div class="clearbox"></div>')).append($(p).children()), $(p).append(i), t = i.parent().width(), i.parent().css("overflow", "hidden"), c = Math.round(t / (w.width + 16)), n = w.time, e = w.width + 16, i.width(i.children().length * e - 16), (o = $(".clearbox").width()) > d ? ($(".jc_rt").show(), i.on("touchstart", function (i) {
						s = i.touches[0].pageX
					}), i.on("touchmove", function (i) {
						var t = $(this);
						a = i.touches[0].pageX - s, s = i.touches[0].pageX, t.css("transition", ""), l(r += a)
					}), i.on("touchend", function (i) {
						$(this).css("transition", "transform " + n + "ms ease"), h()
					})) : $(".jc_rt").hide()
				},
				left: function () {
					Math.abs(r) >= Math.abs(t - i.width()) ? r -= e / 2 : r -= e * c, p(r)
				},
				right: function () {
					p(r += r >= 0 ? e / 2 : e * c)
				}
			}
		}(Zepto), window.fSwiper = function (i) {
			var t = swiperImg.length,
				e = window.innerHeight - 30,
				o = (e + document.body.scrollTop) / 1.2,
				s = 0;
			if ($("#js_swiper")[0]) $("#shadow,#js_swiper").show(), $("#imgSwiper").css("height", e), setTimeout(function () {
				imgSwiper.swipeTo(i, 0)
			}, 100);
			else {
				var a = "<div id='js_swiper'>";
				a += "<div class='swiper_box'>", t > 1 && (a += "<span class='prev' onclick='imgSwiper.swipePrev()' style='top:" + (e / 2 - 30 + document.body.scrollTop) + "px'></span><span class='next' onclick='imgSwiper.swipeNext()' style='top:" + (e / 2 - 30 + document.body.scrollTop) + "px'></span>"), a += "<div id='imgSwiper' class='swiper-container' style='height:" + o + "px'>", a += "<div class='swiper-wrapper'>";
				for (var r = 0; r < t; r++) a += "<div class='swiper-slide' style='color:red;height:" + e + "px'>", a += "<img width='40' height='40' style='margin-top:" + (e / 2 - 50 + document.body.scrollTop) + "px' src='//img01.51jobcdn.com/im/mobile/m/loading3.gif' alt='loading' />", a += "<span style='display:none' title='" + swiperImg[r] + "'></span>", a += "</div>";
				a += "</div>", a += "</div>", a += "<div class='close'><span></span></div>", a += "<div class='page'><span>" + r + "</span>/" + t + "</div>", a += "</div>", a += "</div>", $("#shadow")[0] || (a += "<div id='shadow'></div>"), $("body").append(a);
				var c = t > 1;
				window.imgSwiper = $("#imgSwiper").swiper({
					mode: "horizontal",
					loop: c,
					onSwiperCreated: function () {
						c && (n($("#imgSwiper .swiper-slide")[1]), $(".swiper_box").find(".page span").html(1))
					},
					onSlideChangeStart: function (i) {
						var o = imgSwiper.activeSlide(),
							s = imgSwiper.activeIndex;
						0 == s ? s = t - 1 : s > t ? s = 0 : s--;
						var a = s + 1;
						$(".swiper_box").find(".page span").html(a);
						e = window.innerHeight - 30, document.body.scrollTop;
						$(".swiper-slide img").removeAttr("style").attr("src", "//img01.51jobcdn.com/im/mobile/m/loading3.gif").attr("alt", "loading").css({
							width: "40px",
							height: "40px",
							"margin-top": e
						}), n(o)
					}
				}), n(imgSwiper.activeSlide()), fSwiper(i)
			}
			document.addEventListener("touchmove", function (i) {
				0 == s && i.preventDefault()
			}, !1), $("#js_swiper .close").click(function (i) {
				s = 1, $("#shadow,#js_swiper").hide()
			}), $(window).resize(function (i) {
				var t = imgSwiper.activeSlide(),
					e = window.innerHeight - 30;
				document.body.scrollTop, document.body.scrollTop;
				$("#imgSwiper").css("height", e), $(".swiper-slide img").removeAttr("style").attr("src", "//img01.51jobcdn.com/im/mobile/m/loading3.gif").attr("alt", "loading").css({
					width: "40px",
					height: "40px",
					"margin-top": e
				}), n(t)
			})
		}
	}
}, [36]);
