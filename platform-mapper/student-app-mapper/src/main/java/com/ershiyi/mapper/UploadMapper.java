package com.ershiyi.mapper;

import com.ershiyi.domain.entity.AppUpload;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: zss98
 * @date: 2020-11-07 17:07
 * @version: 1.0
 */
@Repository
@Mapper
public interface UploadMapper {

    /**
     * 将上传的最新的版本信息插入到数据库
     * @param app
     * @return
     */
    @Insert("insert into sys_application_version(version,urgentUpdate,content,url,appType,size,type) " +
            "values(#{version},#{urgentUpdate},#{message},#{downUrl},#{appType},#{size},#{type})")
    int insertAppUpload(AppUpload app);

}
