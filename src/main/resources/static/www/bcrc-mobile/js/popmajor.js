$(function(){$("#shadow").click(function(){pop.close();closeSearchPop()})});function setMajorPage(){if($(".calendar ").css("display")=="block"){$(".calendar ").hide()}if($("#shadow ").css("display")=="block"){$("#shadow ").hide()}if(!$("#bmajor")[0]){var a="";a+='<div id="bmajor" class="cover fixtop"><div class="top"><a href="javascript:void(0);" onclick="pagemajorhide();return false;" class="back"></a><span>专业选择</span></div><div class="searchbox"><div class="search" id="search"><input maxlength="25" oninput="majorkeyword(this)" type="text" placeholder="请输入专业名称" class="full" ><em style="display:none" onclick="cleaninput()"></em></div></div><div class="plist">';$.each(major_class,function(b,c){a+='<div class="i" onclick="setMajorChild(this);" value="'+c.k+'"><span>'+c.v+"</span></div>"});a+="</div></div>";$("body").append(a)}if($("#pageContent")[0]){$("#pageContent").hide()}if($("#pageWp")[0]){$("#pageWp").hide()}$("#bmajor").show();scrollTo(0,0);return false}function setMajorChild(c){var a=$(c).attr("value");$(c).toggleClass("down");$("#bmajor .plist .i").each(function(){if($(this).attr("value")!=a&&$(this).hasClass("down")){$(this).removeClass("down")}});if($(c).hasClass("down")){if(!$(c).next().hasClass("clist")){var b="";b+='<div class="clist childmajor" style="display:block">';$.each(majorlist,function(d,e){if(e.k.substr(0,2)==a.substr(0,2)){b+='<a href="javascript:void(0)" onclick="setMajor(this);" value="'+e.k+'">'+e.v+"</a>"}});b+="</div>";$(".childmajor").hide();$(c).after(b)}else{$(".childmajor").hide();$(c).next().show()}}else{$(c).next().hide()}return false}function setMajor(c){var a=$(c).attr("value"),b=$(c).text();$(".childmajor a").each(function(){if($(this).attr("value")==a){$(this).addClass("on")}else{$(this).removeClass("on")}});$("#majorname").removeClass("c_default").text(b);$("#major").val(a);$("#majordesc").val(b);pagemajorhide();return false}function pagemajorhide(){if($("#shadow ").css("display")=="block"){closeSearchPop()}$("#bmajor").hide();$("#pageContent").show();if($("#err_majordesc").css("display")=="block"){$("#err_majordesc").hide()}return false}function majorkeyword(f){if($(".flist").length>0){$(".flist").remove();$(".closelist").remove()}var e=$(f).val();if(e==""){$("#search em").hide()}else{$("#search em").show()}var c='<div class="flist swiper-container"><div class="swiper-wrapper"><div class="swiper-slide">';var d="";var a="";if(e!=""){$.each(majorlist,function(g,h){if(h.k.substr(2,4)=="00"){d=h.k;a=h.v}else{if(h.v.indexOf(e)!=-1){if(c.indexOf(a)!=-1){c+='<a class="li" href="javascript:void(0)" onclick="setSearchMajor(this)" value="'+h.k+'">'+h.v+"</a>"}else{c+='<div class="tt">'+a+"</div>";c+='<a class="li" href="javascript:void(0)" onclick="setSearchMajor(this)" value="'+h.k+'">'+h.v+"</a>"}}}})}if(c=='<div class="flist swiper-container"><div class="swiper-wrapper"><div class="swiper-slide">'){if($("#search input").val().replace(/^\s+|\s+$/g,"")!=""){c+='<div class="fnolist">没有查询结果，<span onclick="addzidingyi()">添加为自定义专业</span>？</div>'}}else{c+='<a href="javascript:void(0)" class="tt c_orange"  onclick="addzidingyi()">添加为自定义专业 </a></div>'}c+="</div></div></div>";c+='<div class="close closepop closelist" onclick="closeSearchPop()"></div>';$(".searchbox").append(c);var b=new Swiper(".flist.swiper-container",{freeMode:true,slidesPerView:"auto",mousewheelControl:true,direction:"vertical"});$(".searchbox").addClass("search_fix");$("#bmajor").removeClass("fixtop").addClass("cover_fix3");ModalHelper.popShow();$(".searchbox").on("touchmove",bodyScroll,false);$("#shadow").show();b.update()}function cleaninput(){$("#search input").val("");$("#search em").hide();majorkeyword("#search input")}function closeSearchPop(){$("#search input").val("");$("#search em").hide();$(".searchbox").removeClass("search_fix");$("#bmajor").removeClass("cover_fix3").addClass("fixtop");$(".flist").remove();$(".closelist").remove();ModalHelper.popHide();$(".searchbox").off("touchmove",bodyScroll,false);if($("#shadow ").css("display")=="block"){$("#shadow ").hide()}}function setSearchMajor(c){var a=$(c).attr("value");var b=$(c).text();$("#bmajor .plist .i").each(function(){if($(this).attr("value")==(a.substr(0,2)+"00")){if(!$(this).hasClass("down")){$(this).addClass("down")}if(!$(this).next().hasClass("clist")){var d="";d+='<div class="clist childmajor" style="display:block">';$.each(majorlist,function(e,f){if(f.k.substr(0,2)==a.substr(0,2)){if(f.k==a){d+='<a href="javascript:void(0)" class="on" onclick="setMajor(this);" value="'+f.k+'">'+f.v+"</a>"}else{d+='<a href="javascript:void(0)" onclick="setMajor(this);" value="'+f.k+'">'+f.v+"</a>"}}});d+="</div>";$(this).after(d)}else{$(".childmajor a").each(function(){if($(this).attr("value")==a){$(this).addClass("on")}else{$(this).removeClass("on")}});$(this).next().show()}}else{if($(this).hasClass("down")){$(this).removeClass("down");$(this).next().hide()}}});$("#majorname").removeClass("c_default").text(b);$("#major").val(a);$("#majordesc").val(b);pagemajorhide();closeSearchPop()}function addzidingyi(b){$("#bmajor").hide();$("#shadow").hide();ModalHelper.popHide();$(".searchbox").off("touchmove",bodyScroll,false);var a='<div id="customize" class="cover">';a+='<div class="top"><a href="javascript:void(0)" onclick="zidingyiback()" class="back"></a>专业分类选择</div>';a+='<div class="ctags">';a+='<div class="ctle">请选择自定义专业<span class="c_orange search_input"></span>所属专业分类</div>';a+='<div class="cdiv">';$.each(major_class,function(c,d){a+='<a href="javascript:void(0)" onclick="chooseBigClass(this)" value="'+d.k+'">'+d.v+"</a>"});a+="</div>";a+='<button class="btn mark zdybigmajorsave" onclick="">确定</button>';a+="</div>";a+="</div>";$("#shadow").after(a);$(".search_input").text($("#search input").val())}function chooseBigClass(a){$(".cdiv a").each(function(){if($(this).hasClass("on")){$(this).removeClass("on")}});$(a).addClass("on");if($(".zdybigmajorsave").hasClass("mark")){$(".zdybigmajorsave").removeClass("mark");$(".zdybigmajorsave").attr("onclick","savezidingyi()")}}function savezidingyi(){var a="";var b="";$(".cdiv a").each(function(){if($(this).hasClass("on")){a=$(this).attr("value");b=$(this).text()}});if(a==""){pop.ini([["请选择具体专业","warn"]],["确定"]);return false}$("#majordesc").val($("#search input").val());$("#majorname").removeClass("c_default").text($("#search input").val());$("#major").val(a);$("#bmajor .plist .i").each(function(){if($(this).hasClass("down")){$(this).removeClass("down")}if($(this).next().hasClass("clist")){$(this).next().children("a").each(function(){if($(this).hasClass("on")){$(this).removeClass("on")}});$(this).next().hide()}});closeSearchPop();$("#pageContent").show();$("#customize").remove()}function zidingyiback(){$("#customize").remove();$("#bmajor").show();$("#shadow").show();var a=new Swiper(".flist.swiper-container",{freeMode:true,slidesPerView:"auto",mousewheelControl:true,direction:"vertical"});a.update();$(".searchbox").on("touchmove",bodyScroll,false);ModalHelper.popShow()};