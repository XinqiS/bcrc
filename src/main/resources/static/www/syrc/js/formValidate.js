/********************************表单校验******************************/
/*
输入：str
返回：
如果全是空返回true,否则返回false
*/
function isNull(str) {
	if (str == "") 
		return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}
/*
输入：str
全是字母验证
如果全是字母返回true,否则返回false
*/
function checkZm(zm){ 
	var zmReg=/^[a-zA-Z]*$/; 
	if(zm!=""&&!zmReg.test(zm)){ 
		//alert("只能是英文字母！");
		return false; 
	}
	return true;
} 
/*
检查输入字符串是否只由英文字母和数字组成
输入：
s：字符串
返回：
如果通过验证返回true,否则返回false
*/
function checkZmOrNum(zmnum){ 
	var zmnumReg=/^[0-9a-zA-Z]*$/; 
	if(zmnum!=""&&!zmnumReg.test(zmnum)){ 
		//alert("只能输入是字母或者数字,请重新输入");
		return false; 
	}
	return true;
} 
/*
检查输入字符串是否由英文字母和数字和下划线组成
输入：
s：字符串
返回：
如果通过验证返回true,否则返回false
*/
function isNumberOr_Letter(s) {
	var regu = "^[0-9a-zA-Z\_]+$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

//检验时间大小(与当前时间比较) 
function checkDate(obj){ 
	var obj_value=obj.replace(/-/g,"/");//替换字符，变成标准格式(检验格式为：'2009-12-10') 
	// var obj_value=obj.replace("-","/");//替换字符，变成标准格式(检验格式为：'2010-12-10 11:12') 
	var date1=new Date(Date.parse(obj_value)); 
	var date2=new Date();//取今天的日期 
	if(date1>date2){ 
		//alert("不能大于当前时间！"); 
		return false; 
	} 
	return true;
} 

/*
检查输入对象的值是否符合E-Mail格式
输入：str 输入的字符串
返回：如果通过验证返回true,否则返回false
*/
function isEmail(str) {
	var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
	if (myReg.test(str)) 
		return true;
	return false;
}
/*//屏蔽指定关键字
function shieldKeywords(obj) { 
	if((obj.indexOf ("***") == 0)||(obj.indexOf ("****") == 0)){ 
		alert("屏蔽关键字(这里屏蔽***和****)！");
		return false;
	} 
	return true;
} */
/*
检查输入手机号码是否正确
输入：
s：字符串
返回：
如果通过验证返回true,否则返回false
*/
function checkMobile(s) {
	var regu = /^[1][0-9][0-9]{9}$/;
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

/*
检查输入字符串是否是数字
输入：
str：字符串
返回：
如果通过验证返回true,否则返回false
*/
function isNumber(str) 
{         
    var reg = /^\d+$/;
    if (reg.test(str))
       return true; 
    else  
       return false; 
}


/*
检查输入字符串是否符合正整数格式
输入：
s：字符串
返回：
如果通过验证返回true,否则返回false
*/
function isPositiveNumber(s){
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1) {
		return true;
	} else {
		return false;
	}
}

/* 检查输入字符串是否符合国内固话或者传真格式
输入： s：字符串  格式例如：020-87110252/0431-87110252
返回： 如果通过验证返回true,否则返回false */

function isTelephone(s){
  var reg=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
  if(!reg.test(s))    
	  return   false;
  return   true ;

}

/*检查输入字符串是否符合身份证格式

输入： s：字符串

返回： 如果通过验证返回true,否则返回false */

function isIDCard(strIDno) {      
	var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};  
    var iSum = 0;
    //var info = "";  
    var sBirthday="";
    //var strIDno = obj.value;  
    var idCardLength = strIDno.length;
    if(!/^\d{17}(\d|x)$/i.test(strIDno)&&!/^\d{15}$/i.test(strIDno)){ 
    //alert("非法身份证号");     
    	return false;   
    }  
    //在后面的运算中x相当于数字10,所以转换成a
    strIDno = strIDno.replace(/x$/i,"a"); 
    if(aCity[parseInt(strIDno.substr(0,2))]==null){   
    	//alert("非法地区");  
    	return false;    
    }   
    if (idCardLength==18){   
      sBirthday=strIDno.substr(6,4)+"-"+Number(strIDno.substr(10,2))+"-"+Number(strIDno.substr(12,2));  
      var d = new Date(sBirthday.replace(/-/g,"/"));
      if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){  
          //alert("非法生日"); 
    	  return false;         
      }    
      for(var i = 17;i>=0;i --)  
          iSum += (Math.pow(2,i) % 11) * parseInt(strIDno.charAt(17 - i),11);    

        if(iSum%11!=1){ 
            //alert("非法身份证号"); 
            return false;         
       }   

    } else if (idCardLength==15){ 
      sBirthday = "19" + strIDno.substr(6,2) + "-" + Number(strIDno.substr(8,2)) + "-" + Number(strIDno.substr(10,2));   
      var d = new Date(sBirthday.replace(/-/g,"/")); 
      var dd = d.getFullYear().toString() + "-" + (d.getMonth()+1) + "-" + d.getDate();     
      if(sBirthday != dd){  
    	  //alert("非法生日");   
    	  return false;         
     } 
   }    
   return true; 
}


/*
校验ip地址的格式
输入：strIP：ip地址
返回：如果通过验证返回true,否则返回false；
*/
function isIP(strIP) {
	if (isNull(strIP)) return false;
	var re = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g ;//匹配IP地址的正则表达式
	if (re.test(strIP)) {
		if (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256) return true;
	}
	return false;
}

/* 检查开始日期是否小于等于结束日期
输入： s：字符串 开始日期 格式：2014-3-23
e：字符串 结束日期 格式：2014-3-25
返回： 如果通过开始日期小于等于结束日期返回true,否则返回false */

function date_compare(s,e) { 
	var arr=s.split("-");
	var starttime=new Date(arr[0],arr[1],arr[2]);
	var starttimes=starttime.getTime();  
	var arrs=e.split("-");  
	var endtime=new Date(arrs[0],arrs[1],arrs[2]);
	var endtimes=endtime.getTime();  
	if(starttimes>=endtimes){
		//alert('开始时间大于离开时间，请检查'); 
		return false; 
	} else{
		return true;
	}
}

/* 检查输入字符串是否只由汉字组成

输入： s：字符串

返回： 如果通过验证返回true,否则返回false */

function isChinese(str){
	var reg = /^[\u4e00-\u9fa5]+$/;
	if (reg.test(str)) 
		return true;
	return false;
}


/*
检查输入字符串是否符合金额格式
格式定义为带小数的正数，小数点后最多两位
输入：
s：字符串
返回：
如果通过验证返回true,否则返回false
*/
function isMoney(s) {
	var regu = "^[0-9]+[\.][0-9]{0,2}$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}


/*
用途：检查输入对象的值是否符合端口号格式
输入：str 输入的字符串
返回：如果通过验证返回true,否则返回false
*/
function isPort(str) {
	return (isNumber(str) && str < 65536);
}
//对电子邮件的验证
function checkEmail(obj){
	var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(!myreg.test(obj)){
		//alert('请输入有效的邮箱！');
		return false;
	}
	return true;
}
 /*
用途：字符1是包含字符串2
输入：str1：字符串；str2：被包含的字符串
返回：如果通过验证返回true,否则返回false
*/
function isMatch(str1, str2) {
	var index = str1.indexOf(str2);
	if (index == -1) 
		return false;
	return true;
}
/**
 * add by hongpeng_dong  2007.05.12
 * 验证金额,成绩等可以带小数点数字的格式
 * @param str(要验证的字符串) num1(整数部分允许的最大长度) num2(小数点后允许的最大长度)
 * num1 num2 要求均为大于0的正整数 否则直接返回false
 * @return 格式正确true 不正确false 
 * (正确格式可以为不含小数点的正整数,长度最大为传入的数值;如果str为空,返回true)
 */
	function validateResult(str,num1,num2){
		if('' == trim(str))
			return true;
	if(num1 ==0 || num2 == 0)
		return false;
		var v1 = '';
		var v2 = '';
		if(num1 != 1)
			v1=',' + num1;
		if(num2 != 1)
			v2 = ','+num2;
	var re = new RegExp('\(^[0-9]{1'+v1+'}\\.[0-9]{1'+v2+'}$)|(^[0-9]{1'+v1+'}$)');
	return re.test(str);
}
	function LTrim(str)
	{ 
	    var whitespace = new String(" \t\n\r");
	    var s = new String(str);
	    if (whitespace.indexOf(s.charAt(0)) != -1)
	    {
	       var j=0, i = s.length;
	        while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
	        {
	           j++;
	        }
	        s = s.substring(j, i);
	    }
	    return s;
	}
	function RTrim(str)
	{
	    var whitespace = new String(" \t\n\r");
	    var s = new String(str);
	    if (whitespace.indexOf(s.charAt(s.length-1)) != -1)
	    {
	        var i = s.length - 1;
	        while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
	        {
	            i--;
	        }
	        s = s.substring(0, i+1);
	    }
	    return s;
	}
	function trim(str)
	{
	    return RTrim(LTrim(str));
	}
	function nullToEmptyStr(str) {
		if (str == null){
			return "";
		}else{
			return str;
		}
	
	}
 