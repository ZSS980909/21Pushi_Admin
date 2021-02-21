package com.ershiyi.Utils;

/**
 * @Description: 计算球大小工具类
 * @author: zss98
 * @date: 2021-02-20 11:31
 * @version: 1.0
 */
public class SizeUtils {
    public static int getSymbolSize(int level){
        if(level==1){
            return 40;
        }else if(level==2){
            return 35;
        }else if(level==3){
            return 30;
        }else{
            int count = level-3;
            return 30-(count*2);
        }
    }

    public static void main(String[] args) {
        System.out.println(getSymbolSize(4));
    }
}
