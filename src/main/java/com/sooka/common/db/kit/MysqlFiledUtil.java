package com.sooka.common.db.kit;

import com.sooka.common.db.impl.M;

/**
 * Description:Mysql db table filed utils
 *
 *
 * @create 2017-05-11
 **/
public class MysqlFiledUtil {

    public static boolean isAutoIncrementFiled(M FiledType){
        if(FiledType== M.BIG_INT_TYPE) {
            return true;
        }
         if(FiledType== M.INT_TYPE) {
             return true;
         }
         if(FiledType== M.TNY_INT_TYPE) {
             return true;
         }
         if(FiledType== M.SMALL_INT_TYPE) {
             return true;
         }
         if(FiledType== M.MEDIUM_INT_TYPE) {
             return true;
         }
         return false;
    }

    public static boolean isCharTextFiled(M FiledType){

        if(FiledType== M.DATE_TYPE) {
            return false;
        }
        if(FiledType== M.TIMESTAMP_TYPE) {
            return false;
        }
        if(FiledType== M.TEXT_TYPE) {
            return false;
        }
        if(FiledType== M.MEDIUM_TEXT_TYPE) {
            return false;
        }
        if(FiledType== M.LONG_TEXT_TYPE) {
            return false;
        }
        return true;
    }

    public static boolean isNotDefaultValue(M FiledType){
        if(FiledType== M.TEXT_TYPE) {
            return true;
        }
        if(FiledType== M.MEDIUM_TEXT_TYPE) {
            return true;
        }
        if(FiledType== M.LONG_TEXT_TYPE) {
            return true;
        }
        return false;
    }

    public static void main(String[] ARGS){
        System.out.println(isAutoIncrementFiled(M.INT_TYPE));

        System.out.println( isCharTextFiled(M.TEXT_TYPE));
    }

}
