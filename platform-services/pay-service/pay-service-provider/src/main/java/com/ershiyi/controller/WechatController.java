package com.ershiyi.controller;

import com.ershiyi.dto.WechatPayInDTO;
import com.ershiyi.service.WechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 微信操作
 *
 * @author : liyong
 */
@Api(tags = "WechatController", description = "微信模块")
@CrossOrigin
@Controller
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WechatService wechatService;

    @ApiOperation("微信授权登录")
    @GetMapping("/authorize")
    public String authorize(String returnUrl) {
        return wechatService.authorize();
    }

    @ApiOperation("使用code参数获取用户信息")
    @GetMapping("/getUserInfo")
    public void getUserInfo(@RequestParam("code") String code, @RequestParam("state") String state) {
         wechatService.getUserInfo(code, state);
    }

    @ApiOperation("微信订单支付")
    @PostMapping("/wechatOrderPay")
    @ResponseBody
    public Map wechatOrderPay(HttpServletRequest request, @RequestBody WechatPayInDTO inDTO) {
        return wechatService.wechatOrderPay(request, inDTO);
    }

    @ApiOperation("微信支付回调")
    @PostMapping("/whechatPayNotify")
    public void whechatPayNotify(HttpServletRequest request, HttpServletResponse response) {
        wechatService.whechatPayNotify(request, response);
    }

//    @ApiOperation("根据openid获取用户授权信息")
//    @GetMapping("/getWechatInfo")
//    @ResponseBody
//    public String getWechatInfo(@RequestParam("openid") String openid) {
//        return wechatService.getWechatInfo(openid);
//    }

}
