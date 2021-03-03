package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.dto.JpushPojo;
import com.ershiyi.dto.QuestionAndKnowledge;
import com.ershiyi.service.JpushService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 极光推送类
 * Created by ly on 2020/10/17.
 */
@RestController
@RequestMapping("/Jpush")
@Api(value = "极光推送", tags = {"极光推送"})
public class JPushController {
    @Autowired
    private JpushService jpushservice;
    /**
     * 极光推送
     */
    //极光推送>>Android
    //Map<String, String> parm是我自己传过来的参数,可以自定义参数
    @PostMapping("/jpushcall")
    @ResponseBody
    @ApiOperation(value = "极光调用(截屏,抓拍)", notes = "极光调用(截屏,抓拍)",httpMethod = "POST")
    public AbstractBaseResult jpushAndroid(@RequestBody JpushPojo jpush) {
        JpushPojo jpushPojo = jpushservice.sendJpush(jpush);
        if(jpushPojo==null){
            return RespEnum.OK.result("暂未获取到学生平板设备号,无法推送");
        }
        return RespEnum.OK.result(jpushservice.sendJpush(jpush));
        //return RespEnum.OK.result(jpushservice.sendJpush(jpush.getSendtype(),jpush.getRegistrationId(),jpush.getMsg()));
    }

    //推送知识点,题目
    @PostMapping("/jpushcallquestion")
    @ResponseBody
    @ApiOperation(value = "极光调用(知识点,题目推送)", notes = "极光调用(知识点,题目推送)",httpMethod = "POST")
    public AbstractBaseResult jpushAndroidcall(@RequestBody JpushPojo jpush) {
        return RespEnum.OK.result(jpushservice.sendJpush(jpush));
    }

    @PostMapping("/questionjpush")
    @ResponseBody
    @ApiOperation(value = "根据推送知识点出题", notes = "根据推送知识点出题",httpMethod = "POST")
    public AbstractBaseResult questionjpush(@RequestBody QuestionAndKnowledge question) {
        return RespEnum.OK.result(jpushservice.questionjpush(question));
    }

}
