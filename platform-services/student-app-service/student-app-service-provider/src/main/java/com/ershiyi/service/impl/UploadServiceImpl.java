package com.ershiyi.service.impl;

import com.ershiyi.domain.entity.AppUpload;
import com.ershiyi.mapper.UploadMapper;
import com.ershiyi.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: zss98
 * @date: 2020-11-07 17:05
 * @version: 1.0
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadMapper mapper;

    /**
     * 将上传的最新的版本信息插入到数据库
     * @param app
     * @return
     */
    @Override
    public Integer insertAppUpload(AppUpload app) {
        return mapper.insertAppUpload(app);
    }
}
