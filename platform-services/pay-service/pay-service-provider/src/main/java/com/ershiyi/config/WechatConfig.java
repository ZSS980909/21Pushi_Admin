package com.ershiyi.config;//package com.ershiyi.config;

import com.ershiyi.domain.AbstractBaseDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信公众号平台配置
 *
 */
@Data
@Component
public class  WechatConfig extends AbstractBaseDomain {

    @ApiModelProperty(value = "微信统一支付地址")
    @Value("${wechat.unifiedorder.url}")
    private String payUrl;

    @ApiModelProperty(value = "公众号平台Appid")
    @Value("${wechat.appid}")
    private String appid;

    @ApiModelProperty(value = "公众号平台秘钥")
    @Value("${wechat.appsecret}")
    private String appsecret;

    @ApiModelProperty(value = "商户平台商户号")
    @Value("${wechat.mchid}")
    private String mchid;

    @ApiModelProperty(value = "商户平台秘钥ID")
    @Value("${wechat.wechatkey}")
    private String wechatkey;

    @ApiModelProperty(value = "body")
    @Value("${wechat.body}")
    private String wechatbody;

    @ApiModelProperty(value = "微信支付类型")
    @Value("${wechat.type}")
    private String wechattype;

    @ApiModelProperty(value = "微信授权用户信息获取地址")
    @Value("${wechat.oatuh.getUserInfoUrl}")
    private String getUserInfoUrl;

    @ApiModelProperty(value = "支付回调地址")
    @Value("${wechat.notify.url}")
    private String notifyUrl;

//    @ApiModelProperty(value = "重定向地址")
//    @Value("${wechat.redirect.url}")
//    private String redirectUrl;

    public String getGetUserInfoUrl() {
        return getUserInfoUrl;
    }

    public void setGetUserInfoUrl(String getUserInfoUrl) {
        this.getUserInfoUrl = getUserInfoUrl;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getWechatkey() {
        return wechatkey;
    }

    public void setWechatkey(String wechatkey) {
        this.wechatkey = wechatkey;
    }

    public String getWechatbody() {
        return wechatbody;
    }

    public void setWechatbody(String wechatbody) {
        this.wechatbody = wechatbody;
    }

    public String getWechattype() {
        return wechattype;
    }

    public void setWechattype(String wechattype) {
        this.wechattype = wechattype;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

}
