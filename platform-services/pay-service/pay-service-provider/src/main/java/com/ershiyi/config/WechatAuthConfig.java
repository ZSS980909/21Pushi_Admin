package com.ershiyi.config;
import com.riversoft.weixin.mp.base.AppSetting;
import com.ershiyi.domain.AbstractBaseDomain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * 微信授权登录配置
 */
@Component
public class WechatAuthConfig implements Serializable {

    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.appsecret}")
    private String appSecret;

    private AppSetting appSetting;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
     @PostConstruct
    public void init(){
        appSetting=new AppSetting();
        appSetting.setAppId(this.appid);
        appSetting.setSecret(this.appSecret);
    }

    public AppSetting getAppSetting(){
        return appSetting;
    }

}
