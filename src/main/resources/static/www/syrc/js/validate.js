/*
非空校验  :                            isNull()
是否是数字:                       isNumber(field)
trim函数:                        trim() lTrim() rTrim()
校验字符串是否为空:                 checkIsNotEmpty(str)
校验字符串是否为整型:               checkIsInteger(str)
校验整型最小值:                    checkIntegerMinValue(str,val)
校验整型最大值:                    checkIntegerMaxValue(str,val) 
校验整型是否为非负数:               isNotNegativeInteger(str)
校验字符串是否为浮点型:             checkIsDouble(str) 
校验字符串是否为日期型:             checkIsValidDate(str)
校验两个日期的先后:                checkDateEarlier(strStart,strEnd)
校验字符串是否为email型:           checkEmail(str)
校验字符串是否为中文:               checkIsChinese(str)
计算字符串的长度，一个汉字两个字符:   fucCheckLength()
判断下拉列表框是否取值:             getComboValue(field) 
校验电话号(传真号)            	    validateTel(str)
校验手机号                     		validateMonoTel(str)
校验字符串由字母,数字或两者组合而成 	checkIsStrNum(str)
验证字符串是否只由字母组合而成		checkIsChar(str)
验证金额,成绩等可以带小数点数字的格式  validateResult(str,num1,num2)
计算两个日期之间的差值                dateDiff(interval, date1, date2)    
验证是否为，或最大长度中文两个符       isNotNull(arg1,arg2,arg3)
验证输入值是否是月份				isMonth(validateMonth)
验证输入值是否是年份				isYear(validateYear)
--------------------------------------------------------------------
验证小数和负数						chknbr(num,opt1,opt2)
//opt1 小数 opt2 负数
//当opt1为1时检查num是否是小数
//当opt2为1时检查num是否是负数
//返回true是正确的，false是错误的		
--------------------------------------------------------------------
四舍五入函数：						round(input,exact)//input 数值 exact 精确的位数
得到文件的后缀名:                    getFilePostfix(oFile)  
*/

//=================================================================
//function:isNull()
//非空校验
//==================================================================
function isNull(aForm,fieldArray)
{	
	if (confirm("您确认要进行此操作吗?"))
	{
		for (var i=0;i<(aForm.elements.length);i++)
		{
			 var el=aForm.elements[i];
			 for(var j=1;j<=fieldArray.length;j++)
			 {
			 	if (el.name==fieldArray[j])
	        	{
	        		
		 	 		if(trim(el.value)=="")
		 	 		{
		 	 			        		
						setMessage("焦点处的值不能为空:"+fieldArray[j]+"!");	
						el.focus();
						return true;
					}
	 	 	    }	
	 	 	}
	 	 }
	 	 return false; 
    }			
	else
	{
		return true;
	}	
}

//==================================================================
//  function isNumber(field)
//	判断input text 的是否是数字. 
//	return: true or false  field为空返回false
//==================================================================

function isNumber(field){
	//var re = new RegExp(/^\d+.*$/);
	var re = /^\d+(?=\.{0,1}\d+$|$)/;
	return re.test(trim(field.value));
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



/********************************** Empty **************************************/
/**
*校验字符串是否为空
*返回值：
*如果不为空，定义校验通过，返回true
*如果为空，校验不通过，返回false        参考提示信息：输入域不能为空！
*/
function checkIsNotEmpty(str)
{
    if(trim(str)== "")
        return false;
    else
        return true;
}
/*--------------------------------- Empty --------------------------------------*/
/********************************** Integer *************************************/
/**
*校验字符串是否为整型
*返回值：
*如果为空，定义校验通过，      返回true
*如果字串全部为数字，校验通过，返回true
*如果校验不通过，              返回false     参考提示信息：输入域必须为数字！
*/
function checkIsInteger(str)
{
    //如果为空，不通过校验
    if(trim(str) == "")
        return false;
    var strr=trim(str);
    if(new RegExp(/^(\d+)$/).test(strr))
        return true;
    else
        return false;
}//~~~
/**
*校验整型最小值
*str：要校验的串。  val：比较的值
*
*返回值：
*如果为空，定义校验通过，                返回true
*如果满足条件，大于等于给定值，校验通过，返回true
*如果小于给定值，                        返回false              参考提示信息：输入域不能小于给定值！
*/
function checkIntegerMinValue(str,val)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    if(typeof(val) != "string")
        val = val + "";
    if(checkIsInteger(str) == true)
    {
        if(parseInt(str,10)>=parseInt(val,10))
            return true;
        else
            return false;
    }
    else
        return false;
}//~~~
/**
*校验整型最大值
*str：要校验的串。  val：比较的值
*
*返回值：
*如果为空，定义校验通过，                返回true
*如果满足条件，小于等于给定值，校验通过，返回true
*如果大于给定值，                        返回false       参考提示信息：输入值不能大于给定值！
*/
function checkIntegerMaxValue(str,val)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    if(typeof(val) != "string")
        val = val + "";
    if(checkIsInteger(str) == true)
    {
        if(parseInt(str,10)<=parseInt(val,10))
            return true;
        else
            return false;
    }
    else
        return false;
}//~~~
/**
*校验整型是否为非负数
*str：要校验的串。
*
*返回值：
*如果为空，定义校验通过，返回true
*如果非负数，            返回true
*如果是负数，            返回false               参考提示信息：输入值不能是负数！
*/
function isNotNegativeInteger(str)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    if(checkIsInteger(str) == true)
    {
        if(parseInt(str,10) < 0)
            return false;
        else
            return true;
    }
    else
        return false;
}//~~~
/*--------------------------------- Integer --------------------------------------*/
/********************************** Double ****************************************/
/**
*校验字符串是否为浮点型
*返回值：
*如果为空，定义校验通过，      返回true
*如果字串为浮点型，校验通过，  返回true
*如果校验不通过，              返回false     参考提示信息：输入域不是合法的浮点数！
*/
function checkIsDouble(str)
{
    //如果为空，则通过校验
    if(trim(str) == "")
        return true;
    //如果是整数，则校验整数的有效性
    if(str.indexOf(".") == -1)
    {
        if(checkIsInteger(str) == true)
            return true;
        else
            return false;
    }
    else
    {
        if(new RegExp(/^(\-?)(\d+)(.{1})(\d+)$/g).test(trim(str)))
            return true;
        else
            return false;
    }
}//~~~

/*--------------------------------- Double ---------------------------------------*/
/********************************** date ******************************************/
/**
*校验字符串是否为日期型
*返回值：
*如果为空，定义校验通过，           返回false
*如果字串为日期型，校验通过，       返回true
*如果日期不合法，                   返回false    参考提示信息：输入域的时间不合法！（yyyy-MM-dd）
*/
function checkIsValidDate(str)
{
    //如果为空，则通过校验
    if(trim(str) == "")
        return false;
    var pattern = new RegExp(/^((\d{4})|(\d{2}))-(\d{1,2})-(\d{1,2})$/g);
    if(!pattern.test(str))
        return false;
    var arrDate = str.split("-");
    if(parseInt(arrDate[0],10) < 100)
        arrDate[0] = 2000 + parseInt(arrDate[0],10) + "";
    var date =  new Date(arrDate[0],(parseInt(arrDate[1],10) -1)+"",arrDate[2]);
   // date.getYear() == arrDate[0]
    //   &&
    if( date.getMonth() == (parseInt(arrDate[1],10) -1)+""
       && date.getDate() == arrDate[2])
        return true;
    else
        return false;
}
/**
*校验字符串是否为日期型 YYYY-MM-DD
*返回值：
*如果为空，定义校验通过，           返回true
*如果字串为日期型，校验通过，       返回true
*如果日期不合法，                   返回false    参考提示信息：输入域的时间不合法！（yyyy-MM-dd）
*/
function isDate(str)
{
    //如果为空，则通过校验
    if((null != str && "null" != str) && "" == str)
        return true;
    //校验日期 格式为YYYY-MM-DD
    var pattern = new RegExp(/^(\d{4})-(\d{1,2})-(\d{1,2})$/g);
    if(!pattern.test(str))
        return false;
    var arrDate = str.split("-");
    var date =  new Date(arrDate[0],(parseInt(arrDate[1],10) -1)+"",arrDate[2]);
    if( date.getMonth() == (parseInt(arrDate[1],10) -1)+""
       && date.getDate() == arrDate[2])
        return true;
    else
        return false;
}
/**
*校验字符串是否为日期型 YYYY-MM
*返回值：
*如果为空，定义校验通过，           返回true
*如果字串为日期型，校验通过，       返回true
*如果日期不合法，                   返回false    参考提示信息：输入域的时间不合法！（yyyy-MM-dd）
*/
function isDateYM(str)
{
    //如果为空，则通过校验
    if((null != str && "null" != str) && "" == str)
        return true;
    else
        return isDate(str+'-01');
}

function isYearValidate(str)
{ 

   if('' == str){
      return true;
   }else{
    var pattern = new RegExp(/^(\d{4})$/g);
     if(!pattern.test(str)){
       return false;
     }
     return true;
   }
   return false;
}
/*
function checkIsValidDate(aForms,fieldArray)
{
    //如果为空，则通过校验
    for (var i=0;i<(aForms.elements.length);i++)
	{
		 var el=aForms.elements[i];
		 for(var j=0;j<fieldArray.length;j++)
		 {
		 	if (el.name==fieldArray[j])
		 	 {
		 	 	if(el.value =="")
		 	 	{
					return true;
				}
				else
				{
					var pattern = /^((\d{4})|(\d{2}))-(\d{1,2})-(\d{1,2})$/g;
    				if(!pattern.test(el.value))
    				    return false;
    				var arrDate = el.value.split("-");
    				if(parseInt(arrDate[0],10) < 100)
    				    arrDate[0] = 2000 + parseInt(arrDate[0],10) + "";
    				var date =  new Date(arrDate[0],(parseInt(arrDate[1],10) -1)+"",arrDate[2]);
    				if(date.getYear() == arrDate[0]
    				   && date.getMonth() == (parseInt(arrDate[1],10) -1)+""
    				   && date.getDate() == arrDate[2])
    				    return true;
    				else
    				    return false;
				 }
			 }	
		 }		  
    }	
    
}*/
//~~~
/**
*校验两个日期的先后
*返回值：
*如果其中有一个日期为空，校验通过,          返回true
*如果起始日期早于等于终止日期，校验通过，   返回true
*如果起始日期晚于终止日期，                 返回false    参考提示信息： 起始日期不能晚于结束日期。
*/
function checkDateEarlier(strStart,strEnd)
{
    if(checkIsValidDate(strStart) == false || checkIsValidDate(strEnd) == false)
        return false;
    //如果有一个输入为空，则通过检验
    if (( strStart == "" ) || ( strEnd == "" ))
        return true;
    var arr1 = strStart.split("-");
    var arr2 = strEnd.split("-");
    var date1 = new Date(arr1[0],parseInt(arr1[1].replace(/^0/,""),10) - 1,arr1[2]);
    var date2 = new Date(arr2[0],parseInt(arr2[1].replace(/^0/,""),10) - 1,arr2[2]);
    if(arr1[1].length == 1)
        arr1[1] = "0" + arr1[1];
    if(arr1[2].length == 1)
        arr1[2] = "0" + arr1[2];
    if(arr2[1].length == 1)
        arr2[1] = "0" + arr2[1];
    if(arr2[2].length == 1)
        arr2[2]="0" + arr2[2];
    var d1 = arr1[0] + arr1[1] + arr1[2];
    var d2 = arr2[0] + arr2[1] + arr2[2];
    if(parseInt(d1,10) > parseInt(d2,10))
       return false;
    else
       return true;
}
//~~~
/*--------------------------------- date -----------------------------------------*/
/********************************** email *****************************************/
/**
*校验字符串是否为email型
*返回值：
*如果为空，定义校验通过，           返回true
*如果字串为email型，校验通过，      返回true
*如果email不合法，                  返回false    参考提示信息：Email的格式不正確！
*/
function checkEmail(str)
{
    //如果为空，则通过校验
    if(trim(str) == "")
        return true;
    if (str.charAt(0) == "." || str.charAt(0) == "@" || str.indexOf('@', 0) == -1
        || str.indexOf('.', 0) == -1 || str.lastIndexOf("@") == str.length-1 || str.lastIndexOf(".") == str.length-1)
        return false;
    else
        return true;
}//~~~
/*--------------------------------- email ----------------------------------------*/
/********************************** chinese ***************************************/
/**
*校验字符串是否为中文
*返回值：
*如果为空，定义校验通过，           返回true
*如果字串为中文，校验通过，         返回true
*如果字串为非中文，             返回false    参考提示信息：必须为中文！
*/
function checkIsChinese(str)
{
    //如果值为空，通过校验
    if (str == "")
        return true;
    var pattern = /^([\u4E00-\u9FA5]|[\uFE30-\uFFA0])*$/gi;
    if (pattern.test(str))
        return true;
    else
        return false;
}



//判断下拉列表框是否取值
function getComboValue(field)
{
	if (document.getElementById(field).value=="")
	{
		setMessage('请选择');
		document.forms[0].field.focus();
		return;
	}	
}
//是否为有效年
function isYear(aForm,field)
{
		for (var i=0;i<(aForm.elements.length);i++)
		{
			 var el=aForm.elements[i];
			  	if (el.name==field)
	        	{	        		
		 	 		if (document.getElementById(field).value>=1900 && document.getElementById(field).value<=2053)
					{
		
					}
					else
					{
						//setMessage('当前年份无效!');
						//aForm.field.focus();
						return false;
					}
	 	 	}	
	 	 	
	 	 }
	 	 return true;	 
}
function JHshNumberText()
{
  if ( !(((window.event.keyCode >= 48) && (window.event.keyCode <= 57))
  || (window.event.keyCode == 13) || (window.event.keyCode == 46)
  || (window.event.keyCode == 45)))
  {
          window.event.keyCode = 0 ;
   }
}


//函数名：fucCheckLength
//功能介绍：检查字符串的长度
//参数说明：要检查的字符串
//返回值：长度值

function fucCheckLength(strTemp)
{
 var i,sum;
 sum=0;
 for(i=0;i<strTemp.length;i++)
 {
  if ((strTemp.charCodeAt(i)>=0) && (strTemp.charCodeAt(i)<=255))
   sum=sum+1;
  else
   sum=sum+2;
 }
 return sum;
}
//add by hongxin_ma
//opt1 小数 opt2 负数
//当opt1为1时检查num是否是小数
//当opt2为1时检查num是否是负数
//返回true是正确的，false是错误的
function chknbr(num,opt1,opt2)
{
	//第一个字符为"." 返回false
	if(num.charAt(0)=='.')return false;
	var i=num.length;
	var staus;
	//staus用于记录.的个数
	status=0;
	if ((opt2!=1) && (num.charAt(0)=='-'))
	{
	//setMessage("You have enter a invalid number.");
	return false;
	}
	//当最后一位为.时出错
	if (num.charAt(i-1)=='.')
	{
	//setMessage("You have enter a invalid number.");
	return false;
	}

	for (j=0;j<num.length ; j++){
		if (num.charAt(j)=='.')
		{
			status++;
		}
		if (status>1) 
		{
			//setMessage("You have enter a invalid number.");
			return false; 
		}
		if (num.charAt(j)<'0' || num.charAt(j)>'9' )
		{
			if (((opt1==0) || (num.charAt(j)!='.')) && (j!=0)) 
			{
			//setMessage("You have enter a invalid number.");
			return false;
			}
		}
	}
		return true;
}
/*
 *add by hongxin_ma四舍五入函数
 *@param input 输入的原值
 *@param exact 精确的位数
 */
function round(input,exact){
	if(exact==0){
		if(new RegExp(/^\d+.*$/).test(input))
		{
			input=parseFloat(input)+0.5;
			return parseInt(input,'10');
		}else if(new RegExp(/^\-\d+.*$/).test(input)){
			input=parseFloat(input)-0.5;
			return parseInt(input,'10');
		}
		else
		{
			return input
		};
	}
	var substract = '0.';
	var result = '';
	for(var i=0;i<exact;i++){
		substract+='0';
	}
	substract+='5';
	try{
		if(parseFloat(input)>0)
		{
			result = parseFloat(input)+parseFloat(substract);
		}else
		{
			result = parseFloat(input)-parseFloat(substract);
		}
		result = result.toString();
		var end = parseInt(result.indexOf("."),"10")+parseInt(exact,"10")+1;
		result = result.substring(0,end);
	}catch(e){
		return result;
	}
	if(result==-0)
	{
		result = Math.abs(result);	
	}
	return result;
}

	/**
	 * add by hongpeng_dong  2007.05.11
	 * 验证字符串是否是电话号码,传真
	 * 区号为3-4位 可以不写 区号后可以带一个-号,号码为3-8位) 
	 * 允许空值
	 * @param 字符串
	 * @return 合理true 否则为false 空返回true
	 */
 	function validateTel(str){
		if('' == trim(str))
			return true;
		var pattern = new RegExp(/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)/);
		return pattern.exec(str);
	}
	/**
	 * add by hongpeng_dong  2007.12.11
	 * 验证字符串是否是电话号码,传真
	 * 格式为 区号(3-4位)-号码(3-8位) 如：0431－85667788 
	 * 允许空值
	 * @param 字符串
	 * @return 合理true 否则为false 空返回true
	 */
 	function validateTelToo(str){
		if('' == trim(str))
			return true;
		var pattern = new RegExp(/^[0-9]{3,4}\-[0-9]{3,8}$/);
		return pattern.exec(str);
	}
	
	/**
	 * add by hongpeng_dong  2007.05.11
	 * 验证字符串是否是手机号 (必须以1打头 前面可以加0 其它为10位) 
	 * 允许空值
	 * @param 字符串
	 * @return 合理true 否则为false 空返回true
	 */
 	function validateMonoTel(str){
		if('' == trim(str))
			return true;
		var pattern = new RegExp(/^0{0,1}1[0-9]{10}$/);
		return pattern.test(str);
	}
	
	/**
	 * add by hongpeng_dong  2007.04.03
	 * 验证字符串是否只由字母,数字或两者组合而成
	 * @param 字符串
	 * @return 是true 非false
	 */
	function checkIsStrNum(str){
		if(new RegExp(/^[a-zA-Z0-9]*$/g).test(str)){   
		    return true;
		 }
		 return false;
	}	
	
	/**
	 * add by chun_chang  2007.05.15
	 * 验证字符串是否只由字母组合而成
	 * @param 字符串
	 * @return 是true 非false
	 */
	function checkIsChar(str){
		if(new RegExp(/^[a-zA-Z]*$/g).test(str)){
		    return true;
		 }
		 return false;
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
	
	 /**
 	  *	计算两个日期之间的差值
	  * 使用方法：(eg): alert(dateDiff('h', '2007-4-14', '2007-4-19'));  
	  *	h 表示 HOUR， D 表示 day， M 表示 minute， s 表示 second 
 	  */
	function dateDiff(interval, date1, date2)
	 {
	  var objInterval = {'D' : 1000 * 60 * 60 * 24, 'H' : 1000 * 60 * 60, 'M' : 1000 * 60, 'S' : 1000};
	  interval = interval.toUpperCase();
	  var dt1 = Date.parse(date1.replace(/-/g, '/'));
	  var dt2 = Date.parse(date2.replace(/-/g, '/'));
	  try
	  {
	   return Math.round((dt2 - dt1) / eval('(objInterval.' + interval + ')'));
	  }
	  catch (e)
	  {
	   return e.message;
	  }
	  dt1 = null;
	  dt2 = null;
	  objInterval = null;
	 } 
	
/*
   add by sihua_yao
   校验网站url 
   只允许输入http:// https://的
   参数 :url 为对象
*/
function checkWebUrl(url)
   {
		if(url.value==""||url.value=="http://"||url.value=="https://")
		{
			setMessage ("请填写正确的网址！如http://www.ideal.com!");
			return false;
		}
		
		if(url.value.length<8)
		{
			setMessage("请填写正确的网址！如http://www.ideal.com!");
			//url.focus();
			return false;
		}
		 
	   if ( url.value.substring(0, 7)!="http://" && url.value.substring(0, 8)!="https://" )
	   {
	   	 setMessage ("请填写正确的网址！如http://www.ideal.com!");
	   	 return false;
	   }
		
		if(url.value.substring(0, 7)=="http://")
		{
			var s="";
			
			s=url.value.substring(7,url.value.length);
			
			var a=s.indexOf(".", 0); 
			
			if(a=="-1")
			{
				setMessage ("请填写正确的网址！如http://www.ideal.com!");
				//url.focus();
				return false;
			}
			
			var s1=s.substring(a+1,s.length)
			var b=s1.indexOf(".", 0);
			
			if(b=="-1")
			{
				setMessage ("请填写正确的网址！如http://www.ideal.com!");
				//url.focus();
				return false;
		    }
			
			return true;
		}
		
		if(url.value.substring(0, 8)=="https://")
		{
			var s="";
			s=url.value.substring(8,url.value.length);
			var a=s.indexOf(".", 0); 
			
			if(a=="-1")
			{
				setMessage ("请填写正确的网址！如http://www.ideal.com!");
				//url.focus();
				return false;
			}
			
			var s1=s.substring(a+1,s.length)
			var b=s1.indexOf(".", 0);
			
			if(b=="-1")
			{
				setMessage ("请填写正确的网址！如http://www.ideal.com!");
				//url.focus();
				return false;
			}
			return true;
		}
  }
  
/**
* added by hongpeng_dong 2007.11.21
* 得到文件的后缀名
* oFile为file控件对象
*/
function getFilePostfix(oFile)
{
     if(oFile == null)
         return null;
     var pattern = /(.*).(.*)$/gi;
     if(typeof(oFile) == "object")
     {
         if(oFile.value == null || oFile.value == "")
             return null;
         var arr = pattern.exec(oFile.value);
         return RegExp.$2;
     }
     else if(typeof(oFile) == "string")
     {
         var arr = pattern.exec(oFile);
         return RegExp.$2;
     }
     else
         return null;
}
/**
 * add by wei_liu at 2007-02-3
 * 验证是否为空
 * arg1为验证的文档的id
 * arg2为验证的文档名称
 * aarg3文档的最大长度,可以不用验证，只需传递两个前个就可以
 * example  funcion aa(){
 *   if(!isNotNull('voteTitle',"标题",20)){return ;}
 *    其它操作other action
 *   }
 * 也可以不用验证长度
 * example function aa(){
 * if(!isNotNull('voteTitle',"标题")){return ;}
 * }
 * ***********************验证通过返回true
 */
 function isNotNull(arg1,arg2,arg3)
   {
    if(document.getElementById(arg1)){
     if(trim(document.getElementById(arg1).value) =='')
     {
       setMessage('请填写'+arg2+'!');
       return false;
     }
     if(arg3 !='' && typeof(arg3)!='' && arg3 !=null && arg3 != 'undefined')
     {
        if(!maxLongById(arg1,arg3,arg2)){return false;}
     }
     return true;
     }else{
       setMessage('你要验证的字段不存在或ID重复定义');
       return false;
     }
   }
/**
 * add by wei_liu at 2007-02-3
 * 验证长度
 * arg1为验证的文档的id
 * arg2文档的最大长度
 * arg3为验证的文档名称
 * ***********************验证通过返回true
 */
 function maxLongById(arg1,arg2,arg3)
 //str.replace(/[^\x00-\xff]/g,"**").length
   {
    if(document.getElementById(arg1)){
     var str     =document.getElementById(arg1).value;
     var mLength =str.replace(/[^\x00-\xff]/g,"**").length;
     if(mLength>arg2)
     {
       setMessage(arg3+'不能超过'+arg2+'个字符或'+(arg2/2)+'个汉字!');
       return false;
     }
     return true;
    }else{
       setMessage('你要验证的字段不存在或ID重复定义');
       return false;
    }
  }
/**
 * add by wei_liu at 2007-02-3
 * 验证是否为空
 * arg1为验证的文档的value
 * arg2为验证的文档名称
 * aarg3文档的最大长度,可以不用验证，只需传递两个前个就可以
 * example  funcion aa(){
 *   if(!isNotNull('voteTitle',"标题",20)){return ;}
 *    其它操作other action
 *   }
 * 也可以不用验证长度
 * example function aa(){
 * if(!isNotNull('voteTitle',"标题")){return ;}
 * }
 * ***********************验证通过返回true
 */
  function isNotNullValue(arg1,arg2,arg3)
   {
  
     if(trim(arg1) =='' || typeof(arg1)=='undefined')
     {
       setMessage('请填写'+arg2+'!');
       return false;
     }
     if(arg3 !='' && typeof(arg3)!='' && arg3 !=null &&arg3 != 'undefined')
     {
        if(!maxLongByValue(arg1,arg3,arg2)){return false;}
     }
     return true;
   
  }
/**
 * add by wei_liu at 2007-02-3
 * 验证长度
 * val1为验证的文档的value
 * val2文档的最大长度
 * val3为验证的文档名称
 * ***********************验证通过返回true
 */
  function  maxLongByValue(val1,val2,val3){
    var mLength = val1.replace(/[^\x00-\xff]/g,"**").length;
     if(mLength>val2)
     {
       setMessage(val3+'不能超过'+val2+'个字符或'+(val2/2)+'个汉字!');
       return false;
     }
     return true;
  }
  
/**
* added by hongpeng_dong 2008.3.11
* 判断输入值是否是月份
* param:validateMonth 要验证的值 
* 是true 否false
*/
  function isMonth(validateMonth){
  	var pattern = new RegExp(/(^[0]{0,1}[1-9]$)|(^1[0-2]$)/);
  	return pattern.test(validateMonth);
  }

/**
* added by hongpeng_dong 2008.3.11
* 判断输入值是否是年份
* param:validateYear 要验证的值 
* 是true 否false
*/
  function isYear(validateYear){
  	var pattern = new RegExp(/^[1-9][0-9]{3}$/);
  	return pattern.test(validateYear);
  }
 

function checkIdCardNo(valueId){ 
	var obj = $("#"+valueId);//得到控件的Jquery对象
	var num = obj.val();//得到对象值
	var objType = obj.attr("type");
	if(num==null || num==""){
		return true; 
	}
	num = num.toUpperCase(); 
	//身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。 
	if (!(/(^\d{17}$)|(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))){ 
		return false;
	} 
	//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
	//下面分别分析出生日期和校验位 
	var len, re; 
	len = num.length; 
	if(len == 17){
		num = num = num.substr(0, 6) +  num.substr(8, num.length - 8);
		len = num.length;
	}
	
	if (len == 15){ 
		re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/); 
		var arrSplit = num.match(re); 
	
		//检查生日日期是否正确 
		var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]); 
		var bGoodDay; 
		bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
		if (!bGoodDay) 
		{ 
			return false;
		} 
		else 
		{ 
			//将15位身份证转成18位 
			//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
			var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
			var nTemp = 0, i; 
			num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6); 
			for(i = 0; i < 17; i ++) 
			{ 
				nTemp += num.substr(i, 1) * arrInt[i]; 
			} 
			num += arrCh[nTemp % 11]; 
			obj.val(num);
			return true; 
		} 
	} 
	if (len == 18){ 
		re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/); 
		var arrSplit = num.match(re); 
	
		//检查生日日期是否正确 
		var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]); 
		var bGoodDay; 
		bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
		if (!bGoodDay) 
		{ 
			return false; 
		} 
		else 
		{ 
			//检验18位身份证的校验码是否正确。 
			//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
			var valnum; 
			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
			var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
			var nTemp = 0, i; 
			for(i = 0; i < 17; i ++) 
			{ 
				nTemp += num.substr(i, 1) * arrInt[i]; 
			} 
			valnum = arrCh[nTemp % 11]; 
			if (valnum != num.substr(17, 1)) 
			{ 
				return false; 
			} 
			obj.val(num);
			return true; 
		} 
	} 
	return false; 
} 	

//mac地址正则表达式 
		function checkMac(str) 
		{ 
	    if(str == ""){
        return true;}
		//mac地址正则表达式 
		var reg_name=/[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}/; 
		if(!reg_name.test(str)){ 
		alert("mac地址格式不正确！mac地址格式为00:24:21:19:BD:E4"); 
		return false; 
		} 
		return true; 
		} 

function compDate(a,b) {
	var arr=a.split("-");
	var starttime=new Date(arr[0],arr[1],arr[2]);
	var starttimes=starttime.getTime();
	
	var arrs=b.split("-");
	var lktime=new Date(arrs[0],arrs[1],arrs[2]);
	var lktimes=lktime.getTime();
	
	if(starttimes>lktimes) {
		return false;
	} else
		return true;
}
	