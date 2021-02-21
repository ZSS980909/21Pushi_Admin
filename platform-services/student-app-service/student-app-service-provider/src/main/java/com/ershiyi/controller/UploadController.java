package com.ershiyi.controller;

import com.ershiyi.Utils.UploadManger;
import com.ershiyi.Utils.UploadUtils;
import com.ershiyi.domain.entity.AppUpload;
import com.ershiyi.domain.entity.CommonResult;
import com.ershiyi.service.UploadService;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 文件上传工具类
 * @author: zss98
 * @date: 2021-02-21 10:28
 * @version: 1.0
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    private static String prefix = "http://cdn.21hourspushl.com/";   //  七牛云cdn地址

    // 七牛云上传对象
    private UploadManager UPLOAD = UploadManger.getUpload(Zone.zone2());

    // 文件路径
    private static List<String> results = new ArrayList<>();

    @Autowired
    private UploadService service;

    /**
     * 文件上传
     *
     * @param file 文件
     * @return
     */
    @RequestMapping("/file")
    public CommonResult uploadFile(MultipartFile file) {
        if (file == null) {
            return new CommonResult(202, "文件不能为空");
        }
        try {
            // 获取文件后缀
            String fileName = file.getOriginalFilename();
            int lastIndexOf = fileName.lastIndexOf(".");
            String fileType = fileName.substring(lastIndexOf + 1);

            String newFileName = UploadUtils.getFileName(fileType);
            Response result = UPLOAD.put(file.getInputStream(), newFileName, getToken(), null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(result.bodyString(), DefaultPutRet.class);
            return new CommonResult(200, "成功","/"+putRet.key);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(201, "上传失败", e.getMessage());
        }
    }

    @RequestMapping("/applicationUpload")
    public CommonResult applicationUpload(MultipartFile file,String version,Integer type,Integer urgentUpdate,String message,int appType){
        if (file == null) {
            return new CommonResult(202, "文件不能为空");
        }
        try{
            // 获取文件后缀
            String fileName = file.getOriginalFilename();
            int lastIndexOf = fileName.lastIndexOf(".");
            String fileType = fileName.substring(lastIndexOf + 1);
            fileType = fileType.toLowerCase();
            if(!fileType.equals("apk")){
                return new CommonResult(203, "该文件不属于app文件");
            }
            InputStream input = file.getInputStream();
            // 获取文件的大小
            double sizes = Math.ceil((double) input.available()/1024/1024);
            String typeName = null;
            if(type==1){
                // 该更新文件是pad端
                typeName = "pad";
            }else if(type==0){
                // 该更新文件是手机端
                typeName = "android";
            }
            String newFileName = "21System/update/"+typeName+UploadUtils.getGuid()+"."+fileType;
            Response result = UPLOAD.put(input, newFileName, getToken(), null, null);
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(result.bodyString(), DefaultPutRet.class);
            // 将上传成功后的地址加入到数据库更新中
            String url = prefix+putRet.key;
            AppUpload app = new AppUpload();
            app.setMessage(message);
            app.setType(type);
            app.setUrgentUpdate(urgentUpdate);
            app.setVersion(version);
            app.setDownUrl(url);
            app.setSize(sizes);
            app.setAppType(appType);
            service.insertAppUpload(app);
            return new CommonResult(200, "成功",url);
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(201, "上传失败", e.getMessage());
        }
    }

    @RequestMapping("/files")
    public CommonResult filesUpload(@RequestBody HashMap<String, String> map) {
        try {
            // 遍历当前路径 获取所有的文件路径
            File file = new File(map.get("path"));
            List<String> filePaths = getFilePath(file);
            for (String filePath : filePaths) {
                // 遍历导入到七牛云
                // 得到当前文件路径文件的输入流
                FileInputStream fileInputStream = new FileInputStream(new File(filePath));
                String fileName = filePath.replace("\\", "/");
                fileName = fileName.replace("D:/qiniu/", "");
                UPLOAD.put(fileInputStream, fileName,getToken(), null, null);
            }
            return new CommonResult(200, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(201, "上传失败", e.getMessage());
        }
    }

    public static List<String> getFilePath(File filePath) {
        try {
            if (filePath.exists()) {
                if (filePath.isFile()) {
                    results.add(filePath.getAbsolutePath());
                } else {
                    File[] list = filePath.listFiles();//list获取的结果
                    if (list.length != 0) {
                        for (int i = 0; i < list.length; i++) {
                            getFilePath(list[i]);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public static String getToken(){
        return Auth.create(UploadManger.ACCESS_KEY,UploadManger.SECRET_KEY).uploadToken(UploadManger.PATH_NAME);
    }
}
