package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.dto.AlipayInDTO;
import com.ershiyi.dto.ParentAlipayInDTO;
import com.ershiyi.service.AlipayService;
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
 * 支付宝操作
 *
 */
@Api(tags = "AlipayController", description = "支付宝模块")
@Controller
@RequestMapping("/alipay")
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

//    @ApiOperation("支付宝订单支付")
//    @PostMapping("/alipayOrderPay")
//    @ResponseBody//HttpServletResponse response, @RequestBody AlipayInDTO inDTO
//    public void alipayOrderPay(HttpServletResponse response, @RequestBody AlipayInDTO inDTO) throws IOException {
//          alipayService.alipayOrderPay(response,inDTO);
//    }
        @ApiOperation("支付宝订单支付")
        @PostMapping("/alipayOrderPay")
        @ResponseBody//HttpServletResponse response, @RequestBody AlipayInDTO inDTO
        public AbstractBaseResult alipayOrderPay(@RequestBody AlipayInDTO inDTO) throws IOException {
            return RespEnum.OK.result( alipayService.alipayOrderPay(inDTO));
        }

    @ApiOperation("支付宝支付回调")
    @PostMapping("/alipayOrderNotify")
    public void alipayOrderNotify(HttpServletRequest request, HttpServletResponse response){
        alipayService.alipayOrderNotify(request, response);
    }
}
