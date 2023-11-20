var ajaxRequest = function (url) {
	var params = arguments[1] ? arguments[1] : {};
	var method = arguments[2] ? arguments[2] : "post";
	method = method != "post" && method != "get" ? "post" : method;
	var datatype = arguments[3] ? arguments[3] : "json";
	for (var key in params) {
		if (params[key]) {
			params[key] = (params[key])
		}
	}
	var result = {};
	$.ajax({
		type: method,
		url: url,
		data: params,
		async: false,
		dataType: datatype,
		success: function (ret_data) {
			switch (datatype) {
				case "text":
					result = ret_data;
					break;
				default:
					result = eval(ret_data);
					break
			}
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			result = false
		}
	});
	return result
};
var storage = {
	isLocalStorage: (window.localStorage ? true : false),
	set: function (a, b) {
		if (this.isLocalStorage) {
			try {
				localStorage[a] = b
			} catch (c) {
				return
			}
		}
	},
	get: function (a) {
		if (this.isLocalStorage) {
			return localStorage[a]
		}
	},
	del: function (a) {
		if (this.isLocalStorage) {
			localStorage.removeItem(a)
		}
	},
	clear: function () {
		if (this.isLocalStorage) {
			localStorage.clear()
		}
	},
	json_set: function (a, b) {
		if (this.isLocalStorage) {
			try {
				localStorage[a] = JSON.stringify(b)
			} catch (c) {
				return
			}
		}
	},
	json_get: function (a) {
		if (this.isLocalStorage) {
			var b = localStorage[a] ? JSON.parse(localStorage[a]) : "";
			return b
		}
	},
	display: function () {
		if (this.isLocalStorage) {
			var b = "";
			for (var a = 0; a < localStorage.length; a++) {
				key = localStorage.key(a);
				value = localStorage.getItem(key);
				b += "\nkey:" + key + " value:" + value
			}
			return b
		}
	}
};
var filterTitle = function (a) {
	if (a.val() == a.attr("title")) {
		return ""
	} else {
		return a.val()
	}
};
$(".selt select ").change(function () {
	var a = "";
	$(this).find("option").each(function () {
		if ($(this).attr("selected") == true) {
			a = $(this).html();
			return false
		}
	});
	$(this).siblings().removeClass("c_default").html(a)
});
$(".selt select ").click(function () {
	if ($(".calendar ").css("display") == "block") {
		$(".calendar ").hide()
	}
});
$(function () {
	$("a[tag],span[tag],i[tag]").click(function () {
		var b = $(this).attr("tag");
		if (b != "") {
			var a = $_CONFIG.domain + "ajax/in/trace.ajax.php?jsoncallback=?&tag=" + b;
			$.getJSON(a)
		}
	})
});
