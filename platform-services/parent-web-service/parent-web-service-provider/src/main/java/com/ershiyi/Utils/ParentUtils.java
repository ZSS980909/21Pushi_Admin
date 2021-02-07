package com.ershiyi.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 家长端工具类
 * @author: zss98
 * @date: 2020-12-01 16:57
 * @version: 1.0
 */
public class ParentUtils {

    public static String replaceNameX(String str) {
        String reg = ".{1}";
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        int i = 0;
        while (m.find()) {
            i++;
            if (i == 1)
                continue;
            m.appendReplacement(sb, "*");
        }
        m.appendTail(sb);
        return sb.toString();

    }

    public static List<String> getListString(String str){
        try{
            String[] split = str.split(",");
            List<String> list = Arrays.asList(split);
            return list;
        }catch (Exception e){
            return null;
        }
    }
}
