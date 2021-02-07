package com.ershiyi.Utils;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 字符操作工具类
 * @author: zss98
 * @date: 2020-12-28 10:57
 * @version: 1.0
 */
public class StrUtils {

    /**
     * 将非空集合转换为字符串
     * @param list
     * @return
     */
    public static String ArrayToString(List list){
        if(list.isEmpty()){
            return "";
        }
        String str = list.toString();
        return str.substring(1,str.length()-1);
    }

    /**
     * 将string转换成list
     * @param str
     * @return
     */
    public static List<String> StringToList(String str){
        return Arrays.asList(str.split(","));
    }
}
