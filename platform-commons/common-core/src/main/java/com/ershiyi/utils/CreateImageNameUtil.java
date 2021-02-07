package com.ershiyi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 随机生成图片文件名工具类
 *
 */
public class CreateImageNameUtil {

    /**
     * 年月日时分秒生成图片名
     *
     * @return
     */
    public static String createFileName () {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String seconds = new SimpleDateFormat("HHmmss").format(new Date()) + "-";
        String uuid = UUID.randomUUID().toString();
        String str = uuid.replace("-", "").substring(0, 10);
        return date + seconds + str;
    }

    /**
     * 随机生成18位数支付订单号
     *
     * @return
     */
    public static String createRandom() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
        //生成4位随机数
        int random = (int) ((Math.random() * 9 + 1)*1000);
        return date + seconds + random;
    }

}
