package com.ershiyi.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Description: 文件上传方法工具类
 * @author: zss98
 * @date: 2020-10-11 10:22
 * @version: 1.0
 */
public class UploadUtils {
    /**
     * 生成唯一的文件名
     * @return
     */
    public static String getFileName(String fileType){
        StringBuilder stb = new StringBuilder();
        fileType = fileType.toLowerCase();
        String prefix = "user/"+new SimpleDateFormat("yyyy-MM-dd/").format(new Date());
        if(fileType.equals("jpg")||fileType.equals("png")||fileType.equals("gif")||fileType.equals("tif")||fileType.equals("wmf")||fileType.equals("webp")){
            stb.append("21System/image/"+prefix);
        }else{
            stb.append("21System/file/"+prefix);
        }
        stb.append(UUID.randomUUID().toString().replace("-","").substring(0,19));
        stb.append(System.currentTimeMillis());
        stb.append("."+fileType);
        return stb.toString();
    }

    /**
     * 生成guid
     * @param
     */
    public static String getGuid(){
        StringBuilder stb = new StringBuilder();
        stb.append(UUID.randomUUID().toString().replace("-","").substring(0,19));
        stb.append(System.currentTimeMillis());
        return stb.toString();
    }
}
