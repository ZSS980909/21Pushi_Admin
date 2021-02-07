package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.Integral_record;
import com.ershiyi.domain.entity.A_Integral_Common;
import com.ershiyi.domain.entity.A_Integral_Record;
import com.ershiyi.domain.entity.IntegralTask;
import com.ershiyi.service.SignInService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 签到controller类
 */
@RestController
@RequestMapping("/IsLogin")
@Api(value = "签到", tags = {"用户签到"})
public class SignInController {
    @Autowired
    private SignInService signInService;

    /**
     * 查询用户是否签到过,计算出页面积分
     */
    @PostMapping("/credit")
    @ResponseBody
    @ApiOperation(value = "查询用户是否签到过,计算出页面积分", notes = "查询用户是否签到过,计算出页面积分")
    public AbstractBaseResult credit(@RequestBody A_Integral_Common a_integral_common) {
        try{
            Integer result = signInService.credit(a_integral_common);
            if(result==-1){
                // 代表当前任务不存在
                return RespEnum.CHECK_FAILED.result("任务keyword不存在");
            }else{
                return RespEnum.OK.result(result);
            }
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙");
        }
    }

    /**
     * 计算总积分
     */
    @PostMapping("/gathersignIn")
    @ResponseBody
    @ApiOperation(value = "计算出页面积分", notes = "计算出页面积分")
    public AbstractBaseResult  gathersignIn(@RequestBody A_Integral_Record record) {
        try{
            return RespEnum.OK.result(signInService.gathersignIn(record));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("服务器繁忙");
        }
    }

    /**
     * 获取历史积分
     * liy
     */
    @PostMapping("/gethistory")
    @ResponseBody
    @ApiOperation(value = "获取历史积分", notes = "获取历史积分")
    public AbstractBaseResult  gethistory(@RequestBody IntegralTask integraltask) {
        try{
            return RespEnum.OK.result(signInService.gethistory(integraltask));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("服务器繁忙");
        }
    }
}
