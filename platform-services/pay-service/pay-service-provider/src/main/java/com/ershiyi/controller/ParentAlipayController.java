package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.dto.AlipayInDTO;
import com.ershiyi.dto.ParentAlipayInDTO;
import com.ershiyi.service.ParentAlipayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 家长端app支付宝支付功能
 */
@Api(tags = "ParentAlipayController", description = "家长端支付宝模块")
@Controller
@RequestMapping("/ParentAlipay")
public class ParentAlipayController {
    @Autowired
    private ParentAlipayService parentalipayService;

    @ApiOperation("支付宝订单支付")
    @PostMapping("/alipayOrderPay")
    @ResponseBody
    public AbstractBaseResult parentalipayOrderPay(@RequestBody ParentAlipayInDTO inDTO) throws IOException {
        return RespEnum.OK.result(parentalipayService.parentalipayOrderPay(inDTO));
    }

    @ApiOperation("支付宝支付回调")
    @PostMapping("/alipayOrderNotify")
    public void parentalipayOrderNotify(HttpServletRequest request, HttpServletResponse response){
        parentalipayService.parentalipayOrderNotify(request,response);
    }

}
