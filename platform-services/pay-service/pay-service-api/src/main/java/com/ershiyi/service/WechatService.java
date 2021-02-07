package com.ershiyi.service;

import com.ershiyi.dto.WechatPayInDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 微信操作service
 *
 * @author : liyong
 */
public interface WechatService {


    /**
     * 微信授权登录
     *
     * @param returnUrl
     * @return
     */
    String authorize();

    /**
     * 通过网页授权获取用户信息
     *
     * @param code  code码
     * @param state 请求类型
     * @return UserInfoEntity
     */
    void getUserInfo(String code, String state);

    /**
     * 微信支付
     * @param request
     * @param inDTO
     * @return
     */
    Map wechatOrderPay(HttpServletRequest request, WechatPayInDTO inDTO);

    /**
     * 微信支付回调
     *
     * @param request
     * @param response
     */
    void whechatPayNotify(HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据openid获取用户授权信息
     * @param openid
     * @return
     */
    //String getWechatInfo(String openid);

}
