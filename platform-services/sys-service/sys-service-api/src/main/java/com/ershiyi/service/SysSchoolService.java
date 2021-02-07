package com.ershiyi.service;

import com.ershiyi.domain.School;

import java.util.List;
/**
 * SysSchoolService 服务层
 *
 * @author liy
 * @date 2020-06-06
 */
public interface SysSchoolService extends BaseService<School>{
    /**
     * 查询学校基本信息
     * @return
     */
    public List<School> allSchool();
}
