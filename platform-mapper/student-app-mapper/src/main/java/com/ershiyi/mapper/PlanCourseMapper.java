package com.ershiyi.mapper;

import com.ershiyi.dto.PlanCourseDTO;
import tk.mybatis.mapper.AbstractMapper;

public interface PlanCourseMapper  extends AbstractMapper<PlanCourseDTO> {
    /**
     * 计划中课程查询
     * @param plancourse
     * @return
     */
    public PlanCourseDTO plancourse(PlanCourseDTO plancourse);
}
