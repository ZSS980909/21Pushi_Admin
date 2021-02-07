package com.ershiyi.service.impl;

import com.ershiyi.domain.School;
import com.ershiyi.mapper.SysSchoolMapper;
import com.ershiyi.service.SysSchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysSchoolServiceImpl 服务层实现
 *
 * @author liy
 * @date 2020-06-06
 */
@Slf4j
@Service
public class SysSchoolServiceImpl extends BaseServiceImpl<School, SysSchoolMapper>  implements SysSchoolService  {
    @Override
    public List<School> allSchool() {
        return mapper.allSchool();
    }
}
