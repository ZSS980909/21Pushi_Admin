package com.ershiyi.service;

import com.ershiyi.dto.AlipayInDTO;
import com.ershiyi.dto.ParentAlipayInDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ParentAlipayService {
    String parentalipayOrderPay(ParentAlipayInDTO parentalipayindto);

    void parentalipayOrderNotify(HttpServletRequest request, HttpServletResponse response);
}
