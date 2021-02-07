package com.ershiyi.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 随机验证码生成
 */
public class RandomCode {
    public static String  randomcode(){
        String str = "0,1,2,3,4,5,6,7,8,9";
        String str2[] = str.split(",");//将字符串以,分割
        Random rand = new Random();//创建Random类的对象rand
        int index = 0;
        String randStr = "";//创建内容为空字符串对象randStr
        for (int i=0; i<4; ++i){
            index = rand.nextInt(str2.length-1);//在0到str2.length-1生成一个伪随机数赋值给index
            randStr += str2[index];//将对应索引的数组与randStr的变量值相连接
        }
        return randStr;
    }
        public static String getRandomNickname(int length) {
        String val = "";
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                val += String.valueOf(random.nextInt(10));
                }
            return val;
    }
}
