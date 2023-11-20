package com.sooka.common.utils;

import java.util.HashMap;
import java.util.Map;


public class DataDicUtil {
	
	public static String getGsgm(Integer key){
		Map<Integer,String> map=new HashMap<Integer,String>();
		map.put(0, "少于50人");
		map.put(1, "150-500人");
		map.put(2, "500-1000人");
		map.put(3, "5000-10000人");
		return map.get(key);
	}
	
	public static String getGsxz(Integer key){
		Map<Integer,String> map=new HashMap<Integer,String>();
		map.put(0, "外资（欧美）");
		map.put(1, "外资（非欧美）");
		map.put(2, "合资");
		map.put(3, "国企");
		map.put(4, "民营公司");
		map.put(5, "上市公司");
		map.put(6, "创业公司");
		map.put(7, "外企代表处");
		map.put(8, "政府机关");
		map.put(9, "事业单位");
		map.put(10, "非营利组织");
		return map.get(key);
	}
	
	public static String getXl(Integer key){
		Map<Integer,String> map=new HashMap<Integer,String>();
		map.put(0, "初中及以下");
		map.put(1, "高中");
		map.put(2, "中专");
		map.put(3, "大专");
		map.put(4, "本科");
		map.put(5, "硕士");
		map.put(6, "博士");
		map.put(7, "MBA");
		return map.get(key);
	}
	public static String getGzxz(Integer key){
		Map<Integer,String> map=new HashMap<Integer,String>();
		map.put(0, "全职");
		map.put(1, "兼职");
		map.put(2, "实习");
		return map.get(key);
	}
}
