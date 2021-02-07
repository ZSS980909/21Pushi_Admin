package com.ershiyi.service.impl;

import com.ershiyi.dto.PlanCourseDTO;
import com.ershiyi.mapper.PlanCourseMapper;
import com.ershiyi.service.PlanCourseService;
import org.springframework.stereotype.Service;

@Service
public class PlanCourseServiceImpl extends BaseServiceImpl<PlanCourseDTO, PlanCourseMapper> implements PlanCourseService {
    /**
     * 计划中课程查询
     * @param plancourse
     * @return
     */
    @Override
    public PlanCourseDTO plancourse(PlanCourseDTO plancourse) {
        return mapper.plancourse(plancourse);
    }
}
