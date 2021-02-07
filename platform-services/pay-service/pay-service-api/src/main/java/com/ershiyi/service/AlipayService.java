package com.ershiyi.service;

import com.ershiyi.dto.AlipayInDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 */
public interface AlipayService extends BaseService<AlipayInDTO>{


    /**
     * 支付宝支付
     * @param response
     * @param inDTO
     * @throws IOException
     *///HttpServletResponse response, AlipayInDTO inDTO
    void  alipayOrderPay(HttpServletResponse response, AlipayInDTO inDTO) throws IOException;

    /**
     * 支付宝支付回调
     * @param request
     * @param response
     */
    void alipayOrderNotify(HttpServletRequest request, HttpServletResponse response);
}
