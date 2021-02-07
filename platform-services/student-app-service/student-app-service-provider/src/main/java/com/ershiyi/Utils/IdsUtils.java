package com.ershiyi.Utils;

import java.util.*;

/**
 * @Description: 字符串转list集合工具
 * @author: zss98
 * @date: 2020-08-05 17:29
 * @version: 1.0
 */
public class IdsUtils {
    public static List<Integer> getLists(String str){
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        str = str.split("ids\":\"")[1];
        str = str.split("}",0)[0];
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)>=48 && str.charAt(i)<=57){
                // 如果是数字就添加到字符串数组
                sb.append(str.charAt(i));
                if(i==str.length()-1){
                    if(sb.length()>0){
                        // 如果字符数组长度大于0并且当前循环结束 就代表数字结束 将数字存入集合并清空数组
                        list.add(Integer.valueOf(sb.toString()));
                        sb.delete(0,sb.length());
                    }
                }
            }else{
                if(sb.length()>0){
                    // 如果字符数组长度大于0并且当前不是数字 就代表数字结束 将数字存入集合并清空数组
                    list.add(Integer.valueOf(sb.toString()));
                    sb.delete(0,sb.length());
                }
            }
        }
        return list;
    }
    public static List<Integer> getList(String str){
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)>=48 && str.charAt(i)<=57){
                // 如果是数字就添加到字符串数组
                sb.append(str.charAt(i));
                if(i==str.length()-1){
                    if(sb.length()>0){
                        // 如果字符数组长度大于0并且当前循环结束 就代表数字结束 将数字存入集合并清空数组
                        list.add(Integer.valueOf(sb.toString()));
                        sb.delete(0,sb.length());
                    }
                }
            }else{
                if(sb.length()>0){
                    // 如果字符数组长度大于0并且当前不是数字 就代表数字结束 将数字存入集合并清空数组
                    list.add(Integer.valueOf(sb.toString()));
                    sb.delete(0,sb.length());
                }
            }
        }
        return list;
    }

    /**
     * 获取id字符串集合
     * @param str
     * @return
     */
    public static List<String> getListString(String str){
        if(str.length()>1) {
            String[] split = str.split(",");
            List<String> list = Arrays.asList(split);
            return list;
        }
        return new ArrayList<>();
    }

    public static Set<Integer> getSet(String str){
        Set<Integer> list = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)>=48 && str.charAt(i)<=57){
                // 如果是数字就添加到字符串数组
                sb.append(str.charAt(i));
                if(i==str.length()-1){
                    if(sb.length()>0){
                        // 如果字符数组长度大于0并且当前循环结束 就代表数字结束 将数字存入集合并清空数组
                        list.add(Integer.valueOf(sb.toString()));
                        sb.delete(0,sb.length());
                    }
                }
            }else{
                if(sb.length()>0){
                    // 如果字符数组长度大于0并且当前不是数字 就代表数字结束 将数字存入集合并清空数组
                    list.add(Integer.valueOf(sb.toString()));
                    sb.delete(0,sb.length());
                }
            }
        }
        return list;
    }

    public static Integer getFirstId(String knowledge) {
        List<String> list = Arrays.asList(knowledge.split(","));
        Integer result = 0;
        if(list.get(0).equals("")){
            result = Integer.valueOf(list.get(1));
        }else{
            result = Integer.valueOf(list.get(0));
        }
        return result;
    }
}
