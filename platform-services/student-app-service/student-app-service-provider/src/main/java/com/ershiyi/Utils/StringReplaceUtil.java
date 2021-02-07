package com.ershiyi.Utils;

import com.ershiyi.domain.entity.KnowContent;

import java.util.List;

/**
 * @Description: 字符串替换工具类
 * @author: zss98
 * @date: 2020-09-21 10:47
 * @version: 1.0
 */
public class StringReplaceUtil {

    /**
     * 去掉字符串类的html的元素标签
     * @param str
     * @return
     */
    public static String replaceHtml(String str){
        str = str.replace("</br>","\n").replace("<p>","").replace("</p>","");
        return str;
    }

    /**
     * 替换去除掉内容上的html标签
     * @param str
     * @return
     */
    public static String parseString(String str){
        str = str.replace("<p>","").replace("</p>","");
        str = str.replace("</span>","").replace("<span>","").replace("<sub>","").replace("</sub>","");
        str = str.replace("</br>","\n").replace("<br>","\n").replace("<br/>","\n");
        return str;
    }
    /**
     * 替换到知识点内容里的html标签
     */
    public static List<KnowContent> parseKnowInfo(List<KnowContent> knowContents){
        for (KnowContent knowContent : knowContents) {
            knowContent.setKnowContentName(parseString(knowContent.getKnowContentName()));
            knowContent.setContent(parseString(knowContent.getContent()));
            knowContent.setPrologue(knowContent.getPrologue());
        }
        return knowContents;
    }
}
