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
public class AlipayConfig {

    @ApiModelProperty(value = "支付宝appid")
    @Value("${alipay.appid}")
    private String appid;

    @ApiModelProperty(value = "支付宝网关地址")
    @Value("${alipay.gateway}")
    private String gateway;

    @ApiModelProperty(value = "私钥pkcs8格式的")
    @Value("${alipay.ras.private.key}")
    private String rasPrivateKey;

    @ApiModelProperty(value = "支付回调地址")
    @Value("${alipay.notify.url}")
    private String notifyUrl;

    @ApiModelProperty(value = "支付成功跳转地址")
    @Value("${alipay.return.url}")
    private String returnUrl;

    @ApiModelProperty(value = "字符编码格式")
    @Value("${alipay.charset}")
    private String charset;

    @ApiModelProperty(value = "支付宝公钥")
    @Value("${alipay.alipaypublic.key}")
    private String alipayPublicKey;

    @ApiModelProperty(value = "公钥")
    @Value("${alipay.public.key}")
    private String publicKey;

    @ApiModelProperty(value = "付款人")
    @Value("${alipay.payer.show.name}")
    private String payerShowName;

    @ApiModelProperty(value = "备注")
    @Value("${alipay.remark}")
    private String remark;

    @ApiModelProperty(value = "收款人类型")
    @Value("${alipay.payee.type}")
    private String payeeType;

    @ApiModelProperty(value = "设置应用公钥证书路径")
    @Value("${alipay.certPath}")
    private String certPath;
    @ApiModelProperty(value = "设置支付宝公钥证书路径")
    @Value("${alipay.AlipayPublicCertPath}")
    private String AlipayPublicCertPath;
    @ApiModelProperty(value = "收款人类型")
    @Value("${alipay.RootCertPath}")
    private String RootCertPath;

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    public String getAlipayPublicCertPath() {
        return AlipayPublicCertPath;
    }

    public void setAlipayPublicCertPath(String alipayPublicCertPath) {
        AlipayPublicCertPath = alipayPublicCertPath;
    }

    public String getRootCertPath() {
        return RootCertPath;
    }

    public void setRootCertPath(String rootCertPath) {
        RootCertPath = rootCertPath;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getRasPrivateKey() {
        return rasPrivateKey;
    }

    public void setRasPrivateKey(String rasPrivateKey) {
        this.rasPrivateKey = rasPrivateKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getPayerShowName() {
        return payerShowName;
    }

    public void setPayerShowName(String payerShowName) {
        this.payerShowName = payerShowName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPayeeType() {
        return payeeType;
    }

    public void setPayeeType(String payeeType) {
        this.payeeType = payeeType;
    }
}
