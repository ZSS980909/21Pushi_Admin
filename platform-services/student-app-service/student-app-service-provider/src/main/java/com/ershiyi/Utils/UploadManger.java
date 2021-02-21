package com.ershiyi.Utils;

import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import org.springframework.stereotype.Component;

/**
 * @Description:  文件上传方法
 * @author: zss98
 * @date: 2020-11-08 09:36
 * @version: 1.0
 */
@Component
public class UploadManger {

    public static String ACCESS_KEY = "vcFKorNDqyNb8a2oIyH5IsXTbQTEbeNVt0CaruDD";
    public static String SECRET_KEY = "XwK4eA2WSZk_YFfgRXlnQqwIC4P6GZGW0Ggr-c6T";

    public static String PATH_NAME = "21platform";     // 七牛文件存储位置

    /**
     * 获取上传对象方法
     * @param zone 区域
     * @return
     */
    public static UploadManager getUpload(Zone zone){
        // 创建上传对象
        UploadManager uploadManager = new UploadManager(new Configuration(zone));
        return uploadManager;
    }


}
