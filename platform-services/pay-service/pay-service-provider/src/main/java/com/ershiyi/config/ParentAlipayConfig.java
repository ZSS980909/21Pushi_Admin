package com.ershiyi.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 支付宝支付配置
 *
 */
@Data
@Component
public class ParentAlipayConfig {

    @ApiModelProperty(value = "支付宝appid")
    @Value("${alipay.parentappid}")
    private String parentappid;

    @ApiModelProperty(value = "支付宝网关地址")
    @Value("${alipay.parentgateway}")
    private String parentgateway;

    @ApiModelProperty(value = "私钥pkcs8格式的")
    @Value("${alipay.ras.private.parentkey}")
    private String parentrasPrivateKey;

    @ApiModelProperty(value = "支付回调地址")
    @Value("${alipay.notify.parenturl}")
    private String parentnotifyUrl;

    @ApiModelProperty(value = "支付成功跳转地址")
    @Value("${alipay.return.parenturl}")
    private String parentreturnUrl;

    @ApiModelProperty(value = "字符编码格式")
    @Value("${alipay.parentcharset}")
    private String parentcharset;

//    @ApiModelProperty(value = "支付宝公钥")
//    @Value("${alipay.alipaypublic.parentkey}")
//    private String parentalipayPublicKey;

    @ApiModelProperty(value = "公钥")
    @Value("${alipay.public.parentkey}")
    private String parentpublicKey;

    @ApiModelProperty(value = "付款人")
    @Value("${alipay.payer.show.parentname}")
    private String parentpayerShowName;

    @ApiModelProperty(value = "备注")
    @Value("${alipay.parentremark}")
    private String parentremark;

    @ApiModelProperty(value = "收款人类型")
    @Value("${alipay.payee.parenttype}")
    private String parentpayeeType;

    @ApiModelProperty(value = "设置应用公钥证书路径")
    @Value("${alipay.certParentPath}")
    private String certParentPath;
    @ApiModelProperty(value = "设置支付宝公钥证书路径")
    @Value("${alipay.AlipayParentPublicCertPath}")
    private String AlipayParentPublicCertPath;
    @ApiModelProperty(value = "收款人类型")
    @Value("${alipay.RootCertParentPath}")
    private String RootCertParentPath;

    public String getParentrasPrivateKey() {
        return parentrasPrivateKey;
    }

    public void setParentrasPrivateKey(String parentrasPrivateKey) {
        this.parentrasPrivateKey = parentrasPrivateKey;
    }

    public String getCertParentPath() {
        return certParentPath;
    }

    public void setCertParentPath(String certParentPath) {
        this.certParentPath = certParentPath;
    }

    public String getAlipayParentPublicCertPath() {
        return AlipayParentPublicCertPath;
    }

    public void setAlipayParentPublicCertPath(String alipayParentPublicCertPath) {
        AlipayParentPublicCertPath = alipayParentPublicCertPath;
    }

    public String getRootCertParentPath() {
        return RootCertParentPath;
    }

    public void setRootCertParentPath(String rootCertParentPath) {
        RootCertParentPath = rootCertParentPath;
    }

    public String getParentappid() {
        return parentappid;
    }

    public void setParentappid(String parentappid) {
        this.parentappid = parentappid;
    }

    public String getParentgateway() {
        return parentgateway;
    }

    public void setParentgateway(String parentgateway) {
        this.parentgateway = parentgateway;
    }

    public String getParentnotifyUrl() {
        return parentnotifyUrl;
    }

    public void setParentnotifyUrl(String parentnotifyUrl) {
        this.parentnotifyUrl = parentnotifyUrl;
    }

    public String getParentreturnUrl() {
        return parentreturnUrl;
    }

    public void setParentreturnUrl(String parentreturnUrl) {
        this.parentreturnUrl = parentreturnUrl;
    }

    public String getParentcharset() {
        return parentcharset;
    }

    public void setParentcharset(String parentcharset) {
        this.parentcharset = parentcharset;
    }

    public String getParentpublicKey() {
        return parentpublicKey;
    }

    public void setParentpublicKey(String parentpublicKey) {
        this.parentpublicKey = parentpublicKey;
    }

    public String getParentpayerShowName() {
        return parentpayerShowName;
    }

    public void setParentpayerShowName(String parentpayerShowName) {
        this.parentpayerShowName = parentpayerShowName;
    }

    public String getParentremark() {
        return parentremark;
    }

    public void setParentremark(String parentremark) {
        this.parentremark = parentremark;
    }

    public String getParentpayeeType() {
        return parentpayeeType;
    }

    public void setParentpayeeType(String parentpayeeType) {
        this.parentpayeeType = parentpayeeType;
    }
}