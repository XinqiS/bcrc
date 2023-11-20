package com.sooka.common.utils;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.*;

/**
 * Created by guo Cotter on 2019/3/12.
 */
public class IdealDicUtil {

    public static Map getDic(String dic) {
//        File file = new File("F:/idealDicConfig.js");
        File file = new File(PathUtil.getRootClassPath()+Const.DIC_PATH);
        String zs = "\\/\\*(\\s|.)*?\\*\\/";
        String str = txt2String(file).replaceAll("var dicConfig =","");//读取文件中的内容存为String并替换开头var dicConfig =
        str = str.replaceAll("dicInitData", "\"dicInitData\"");
        str = str.replaceAll(zs,"");//替换/**/注释
        str = str.replaceAll("\\/\\/[^\\n]*","");//替换//注释
        str = str.replaceAll("\\s{1,}", " ").trim();//删除空格
        str = str.replaceAll("[\\']", "\"");//替换单引号
        String temp1 = str.substring(0,str.length()-6);
        String temp2 = str.substring(str.length()-6).replaceAll(",","").replaceAll(";","");//替换结尾处;和可能存在的,
        str=temp1+temp2;
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(str);//转换为json对象
        JsonElement el = jsonObject.get("dicInitData");
        //把JsonElement对象转换成JsonObject
        JsonArray jsonArray = null;
        if(el.isJsonArray()){
            jsonArray = el.getAsJsonArray();
        }
        //查出对应id下的子集
        Map res = new HashMap();
        for (JsonElement s:jsonArray) {
            if(dic.equals(s.getAsJsonObject().get("id").toString().replaceAll("\"",""))){
                JsonArray childList =s.getAsJsonObject().get("child").getAsJsonArray();
                int i=0;
                for (JsonElement  child:childList) {
                    res.put(i,child.getAsJsonArray().get(1).toString().replaceAll("\"",""));
                    i++;
                }
            }
        }
        return res;
    }
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
    public static void main(String[] args) {
        //Example
        Map map =IdealDicUtil.getDic("cg001");
        for (Object key : map.keySet()) {
            String value = (String)map.get(key);
			System.out.println(key + " : " + value);
        }
    }
}
