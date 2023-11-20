/*----------------------------------------------------------------------------\
|                               IdealDic                                      |
|-----------------------------------------------------------------------------|
|                         Created by dongping_li                           	  |
|-----------------------------------------------------------------------------|
|  	  缓存系统字典项的处理文件													  |
|     系统如果字典项目特别多,可以使用此处提供的功能,在系统登录后将字典项缓存到页面内,	      |
|  在具体页面,直接调用此处提供的功能取得需要的字典即可.								  |
|-----------------------------------------------------------------------------|
| 2007-01-19 | Original Version Posted.                                       |
|-----------------------------------------------------------------------------|
| Created 2007-01-19 | All changes are in the log above. | Updated 2008-07-09 |
\----------------------------------------------------------------------------*/

/**
 *	页面字典项的容器
 */
var dicContainer={
    //初始化方法
    init:function(){
    	if(dicInit.length==0) return;
    	for(var i=0;i<dicInit.length;i++)
    	{
    		var dic=dicInit[i];
    		var dicObj=new IdealDic(dic.id,dic.name);
    		if(dic.child.length==0) return;
    		for(var j=0;j<dic.child.length;j++){
    			var dicItemObj=dic.child[j];
    			dicObj.add(new dicItem(dicItemObj[0],dicItemObj[1]),dic.child);
    		}
    	}
    },
    //得到某一字典中字典项的名称
    getDicItemName:function(dicCode,itemCode){
    	var dicObj=dicContainer[dicCode].children;
    	for(var i=0;i<dicObj.length;i++){
    		var itemObj=dicObj[i];
    		if(itemObj.code==itemCode){
    			return itemObj.name;
    		}
    	}
    },
    //将字典项渲染为checkobx
    renderToCheckBox:function(dicCode,checkBoxName,objRenderIds,objRenderNmaes){
    	var dicObj=dicContainer[dicCode].children;
    	var str="";//html
    	var ids="";
    	var names="";
    	if(dicObj.length==0) return;
    	for(var i=0;i<dicObj.length;i++){
    		var dicItemObj=dicObj[i];
    		str+="<li><label id=\"lab"+i+"\" ><input name=\""+checkBoxName+"\"  id=\"checkboxid"+i+"\"onclick=\"reRender('"+objRenderIds+"','"+objRenderNmaes+"','"+checkBoxName+"','"+dicCode+"')\"  type=\"checkbox\" value=\"" + dicItemObj.code +"\" title=\""+dicItemObj.name+"\"";
    		str+="/>"+ dicItemObj.name+"</label></li>";
    	}
    	return str;
    },
    //将字典项渲染为checkobx
    checkToCheckBox:function(dicCode,checkBoxName,objRenderIds,objRenderNames,valueArray){
    	if(valueArray.length==0) return;
    	var arr_rybs=new Array();
    	arr_rybs=valueArray.split(",");
    	var dicObj=dicContainer[dicCode].children;
    	var str="";//html
    	var ids="";
    	var names="";
    	if(dicObj.length==0) return;
    	for(var j=0;j<arr_rybs.length;j++){
    		var checkbox = document.getElementsByName(checkBoxName);
    	    for(var i = 0; i < checkbox.length; i++){
    	        if(checkbox[i].value==arr_rybs[j]){
    	        	checkbox[i].checked=true;
    	        	checkbox[i].parentNode.style.color="blue";//选中变色
    	        }
    	       }
    	}
    	document.getElementById(objRenderNames).value=renderValue(arr_rybs,dicCode);
    	}
   };
function reRender(renderIds,renderNames,checkBoxName,dicCode){
	var checkbox = document.getElementsByName(checkBoxName);
	var code = new Array();
	var value = new Array();
    for(var i = 0; i < checkbox.length; i++){
    	checkbox[i].parentNode.style.color="#333";//取消所有颜色
    	if(checkbox[i].checked){
    		code.push(checkbox[i].value);
    		checkbox[i].parentNode.style.color="blue";//选中变色
    	}
    }
    document.getElementById(renderIds).value=code;
    document.getElementById(renderNames).value=renderValue(code,dicCode);
}
function renderValue(array,dicCode){
	var arr=new Array();
	arr=array;
	var arr_text=new Array();
	for(var j=0;j<arr.length;j++){
		var dicObj=dicContainer[dicCode].children;
			for(var i=0;i<dicObj.length;i++){
			var itemObj=dicObj[i];
			if(itemObj.code==arr[j]){
				arr_text.push(itemObj.name);
			}
		}
	}
	return arr_text;
}
/*
 *  存储字典项目键值对的容器
 */
var dicNameContainer={};
/**
 * 字典初始化数据
 */
var dicInit=dicConfig.dicInitData;
/**
 *	字典类
 * @param:id  			数据库字典表中的内码
 * @param:name			字典名称
 */
function IdealDic(id,name,selectedCode)
{
	this.id=id;
	this.name=name;
	this.selectedCode=selectedCode;
	this.children=[];
	this.childrenJson=null;
	dicContainer[this.id]=this;
}
/**
 * 增加一个字典项目
 * @param:item	字典项目实例
 */
IdealDic.prototype.add=function(item,itemJson)
{
	item.parentNode=this;
	this.children[this.children.length]=item;
	this.childrenJson=itemJson;
}
/**
 * 设置选中字典项
 * @param selectedCode 选中字典项的code
 */
IdealDic.prototype.setSelected=function(selectedCode)
{
	this.selectedCode=selectedCode;
}
/**
 *	输出字典项目
 *
 */
IdealDic.prototype.toString=function(selectName)
{
	var str="";
	if(this.children.length > 0)
	{
		var sb=[];
		for(var i=0;i<this.children.length;i++)
		{
			sb[i]=this.children[i].toString(selectName);
		}
		return sb.join("");
	}else{
		//update by hongxin_ma 如果该字典存在上级编码，但是没有下级数据，默认加一个option
		return "<option value=\"-999\">无类别</option>";
	}
	
}
/**
 *	输出字典项目
 *
 */
IdealDic.prototype.toQueryString=function(selectName)
{
	var str="";
	if(this.children.length > 0)
	{
		var sb=[];
		var queryAll="<option value=\"\">全部</option>";
		for(var i=0;i<this.children.length;i++)
		{
			sb[i]=this.children[i].toString(selectName);
		}
		return queryAll+sb.join("");
	}else{
		//update by hongxin_ma 如果该字典存在上级编码，但是没有下级数据，默认加一个option
		return "<option value=\"-999\">无类别</option>";
	}
	
}
/**
 * 字典项目类
 * @param:code  字典项目代码 
 * @param:name	字典项目名称
 */
function dicItem(code,name)
{
	dicNameContainer[code]=name;
	this.code=code;
	this.name=name;
}

dicItem.prototype.toString=function(selectName)
{
	var str="<option value=\"" + this.code +"\" ";
	if(this.parentNode.selectedCode!=null||this.name==selectName)
	{
		if(this.code==this.parentNode.selectedCode||this.name==selectName)
		{	
			str+="selected=\"true\" ";
		}
	}
	str+=">"+ this.name +"</option>";
	return str;
}
/**
 * 显示复选框
 */
//dicItem.prototype.toString=function(selectName)
//{
//	var str="<li><label><input type=\"checkbox\" value=\"" + this.code +"\" ";
//	if(this.parentNode.selectedCode!=null||this.name==selectName)
//	{
//		if(this.code==this.parentNode.selectedCode||this.name==selectName)
//		{	
//			str+="checked=\"true\" ";
//		}
//	}
//	str+="/>"+ this.name+"</label></li>";
//	return str;
//}