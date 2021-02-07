package com.ershiyi.mapper;

import com.ershiyi.domain.School;
import tk.mybatis.mapper.AbstractMapper;

import java.util.List;

/**
 *   SysSchoolMapper数据层
 *
 * @author liy
 * @date 2020-06-06
 */
public interface SysSchoolMapper extends AbstractMapper<School> {

    /**
     * 查询学校信息
     */
    List<School> allSchool();
}
