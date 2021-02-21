package com.ershiyi.service;

import com.ershiyi.domain.entity.AppUpload;

/**
 * @Description:
 * @author: zss98
 * @date: 2020-11-07 17:04
 * @version: 1.0
 */
public interface UploadService {

    /**
     * 将上传的最新的版本信息插入到数据库
     * @param app
     * @return
     */
    Integer insertAppUpload(AppUpload app);
}
