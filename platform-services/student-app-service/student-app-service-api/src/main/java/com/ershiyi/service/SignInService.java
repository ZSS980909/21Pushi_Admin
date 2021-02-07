package com.ershiyi.service;

import com.ershiyi.domain.Integral_record;
import com.ershiyi.domain.entity.A_Integral_Common;
import com.ershiyi.domain.entity.A_Integral_Record;
import com.ershiyi.domain.entity.IntegralTask;
import com.ershiyi.domain.entity.StudentPoints;

import java.util.List;


/**
 * SignInService 服务层
 *
 * @author liy
 * @date 2020-06-06
 */
public interface SignInService extends BaseService<A_Integral_Record> {
    /**
     * 签到功能
     * 计算页面积分
     * @param a_integral_common
     * @return
     */
    public Integer credit(A_Integral_Common a_integral_common);

    public StudentPoints gathersignIn(A_Integral_Record record);

    public List<IntegralTask> gethistory(IntegralTask integraltask);
}
