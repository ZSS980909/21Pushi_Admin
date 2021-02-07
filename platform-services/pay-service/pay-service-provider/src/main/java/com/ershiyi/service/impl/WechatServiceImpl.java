package com.ershiyi.service.impl;

import com.ershiyi.config.WechatAuthConfig;
import com.ershiyi.config.WechatConfig;
import com.ershiyi.dto.WechatPayInDTO;
import com.ershiyi.mapper.WechatMapper;
import com.ershiyi.service.WechatService;
import com.ershiyi.utils.CreateImageNameUtil;
import com.ershiyi.utils.HttpHelper;
import com.ershiyi.utils.WXPayUtil;
import com.google.gson.Gson;
import com.riversoft.weixin.common.oauth2.AccessToken;
import com.riversoft.weixin.common.oauth2.OpenUser;
import com.riversoft.weixin.mp.oauth2.MpOAuth2s;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 微信service
 *
 * @author : LIYONG
 */
@Service
public class WechatServiceImpl extends BaseServiceImpl<WechatPayInDTO, WechatMapper> implements WechatService {
    private static final Logger log = LoggerFactory.getLogger(WechatServiceImpl.class);

    /**
     * 微信返回成功字符串
     */
    private static final String SUCCESS = "SUCCESS";

    private static final String UNKNOWN = "unknown";


    private static final String ONE = "1";

    @Autowired
    private WechatAuthConfig wechatAuthConfig;

    @Autowired
    private WechatConfig wechatConfig;

    /**
     * 微信授权登录
     *
     * @return
     */
    @Override
    public String authorize() {
        //log.info("returnUrl: " + returnUrl);
        MpOAuth2s oatuh = MpOAuth2s.with(wechatAuthConfig.getAppSetting());
        String url = oatuh.authenticationUrl(wechatConfig.getGetUserInfoUrl(), "snsapi_userinfo", "");
        log.info("授权地址: " + url);
        return "redirect:" + url;
    }

    /**
     * 获取用户详情信息
     *
     * @return
     */
    @Override
    public void getUserInfo(String code, String state) {
        //重定向跳转地址
        String redirectUrls = "";
        Gson gson = new Gson();
       //UserInfoEntity userInfo = new UserInfoEntity();
        MpOAuth2s oatuh = MpOAuth2s.with(wechatAuthConfig.getAppSetting());
        AccessToken accessToken = oatuh.getAccessToken(code);
        OpenUser openUser = oatuh.userInfo(accessToken.getAccessToken(), accessToken.getOpenId());
        if (openUser != null) {
            log.info(openUser.getOpenId());
//            userInfo.setOpenid(openUser.getOpenId());
//            userInfo.setNickname(openUser.getNickName());
//            userInfo.setSex(openUser.getSex().getCode());
//            userInfo.setCountry(openUser.getCountry());
//            userInfo.setProvince(openUser.getProvince());
//            userInfo.setCity(openUser.getCity());
//            userInfo.setHeadimgurl(openUser.getHeadImgUrl());
//            //根据对应业务处理不同逻辑  1：跳转互联网充值主页
//            switch (state) {
//                case "1":
//                    //调用公安内网服务存储用户信息
//                    String url = commonConfig.getGonganUrl() + "/api/audit/pay/saveAuthMessage";
//                    String userJson = gson.toJson(userInfo);
//                    log.info("用户信息：" + userJson);
//                    String result = HttpHelper.doHttpPost(url, userJson);
//                    log.info("请求公安网返参：" + result);
//                    log.info("openId为：" + openUser.getOpenId());
//                    //重定向跳转
//                    redirectUrls = wechatConfig.getRedirectUrl() + "?openid=" + openUser.getOpenId();
//                    break;
//                default:
//                    break;
            }
           // return "redirect:" + redirectUrls;
     //   }
        //return redirectUrls;
    }

    @Override
    public Map wechatOrderPay(HttpServletRequest request, WechatPayInDTO inDTO) {
        //授权获取openid
//        MpOAuth2s oatuh = MpOAuth2s.with(wechatAuthConfig.getAppSetting());
//        String url = oatuh.authenticationUrl("", "snsapi_userinfo", "");
//        String code="";
//        AccessToken accessToken = oatuh.getAccessToken(code);
//        OpenUser openUser = oatuh.userInfo(accessToken.getAccessToken(), accessToken.getOpenId());
//        String  openid=openUser.getOpenId();
        Gson gson = new Gson();
        //生成随机订单号且唯一
        String outTradeNo = CreateImageNameUtil.createRandom();
        String xml = null;
        String sign = null;
        String prepayId = null;
        String paySign = null;
        Map<String, String> xmlMap = new HashMap<>();
        //微信金额处理（微信传入的金额是原本的100倍，需做处理后再插入库中）
        Integer rechargeAmount = inDTO.getRechargeAmount();
        int multiple = 100;
        Integer format = rechargeAmount / multiple;
        inDTO.setRechargeAmount(format);
        inDTO.setOrderId(outTradeNo);
        inDTO.setStatus(0);
        inDTO.setKeyWord("PAY");
        inDTO.setOpenId("123123");
        inDTO.setPaymentType(1);
        inDTO.setChangeIntegral(Double.valueOf(inDTO.getRechargeAmount()/10));
        String integral=mapper.selectIntegral(inDTO);
        inDTO.setRawIntegral(Double.valueOf(integral));
        inDTO.setIntegralValue(inDTO.getRawIntegral()+inDTO.getChangeIntegral());
        log.info("充值金额为" + inDTO.getRechargeAmount());
        //预生成订单
        String payJson = gson.toJson(inDTO);
        log.info("微信支付预订单信息==>" + payJson);
        Integer result = mapper.insertorder(inDTO);
        //预订单生成失败
        if (null == result || "".equals(result) || result.equals("0")) {
            return null;
        } else {
            //预订单生成成功
            //获取用户ip
            String ip = getUserIp(request);
            log.info("ip为" + ip);
            Map<String, String> paraMap = new HashMap<>(12);
            //appid
            paraMap.put("appid", wechatConfig.getAppid());
            //自定义所支付的名称
            paraMap.put("body", wechatConfig.getWechatbody());
            //商户ID
            paraMap.put("mch_id", wechatConfig.getMchid());
            //支付类型 咱们是公众号支付此处给 JSAPI
            paraMap.put("trade_type", wechatConfig.getWechattype());
            //随机字符串
            paraMap.put("nonce_str", WXPayUtil.generateNonceStr());
            //openid
            paraMap.put("openid", inDTO.getOpenId());
            //自己后台生成的订单号,只要保证唯一就好
            paraMap.put("out_trade_no", outTradeNo);
            //IP地址
            paraMap.put("spbill_create_ip", ip);
            //支付金额
            paraMap.put("total_fee", String.valueOf(rechargeAmount));
            //支付回调地址
            paraMap.put("notify_url", wechatConfig.getNotifyUrl());

            //签名生成
            try {
                sign = WXPayUtil.generateSignature(paraMap, wechatConfig.getWechatkey());
            } catch (Exception e) {
                log.info("微信支付签名生成异常~");
            }
            paraMap.put("sign", sign);
            //将所有参数(map)转xml格式调用微信统一支付接口
            try {
                xml = WXPayUtil.mapToXml(paraMap);
            } catch (Exception e) {
                log.info("微信支付map转xml异常~");
            }
            log.info("调用微信统一支付接口参数Map~~~" + gson.toJson(paraMap));
            log.info("生成xml为" + xml);
            //微信统一支付地址
            String xmlStr = HttpHelper.sendPost(wechatConfig.getPayUrl(), xml);
            log.info("微信支付post请求xml格式返参：" + xmlStr);
            if (xmlStr == null) {
                return null;
            }
            //微信支付返回xml格式参数转map
            try {
                xmlMap = WXPayUtil.xmlToMap(xmlStr);
            } catch (Exception e) {
                log.info("微信支付xml返参转map异常~");
            }
            String returnCode = xmlMap.get("return_code");
            if (returnCode.equals(SUCCESS)) {
                //微信返回的预支付id
                prepayId = xmlMap.get("prepay_id");
            }
            log.info("获取的prepay_id预支付id为" + prepayId);
            Map<String, String> payMap = new HashMap<>(6);
            payMap.put("appId", wechatConfig.getAppid());
            payMap.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
            payMap.put("nonceStr", WXPayUtil.generateNonceStr());
            payMap.put("signType", "MD5");
            //注意：它用到了prepay_id,但是还不是直接取值,还非要固定格式的,值的格式例如：prepay_id= wx2018…250…9981…666
            payMap.put("package", "prepay_id=" + prepayId);
                /*
                 paySign：签名算法 ,按照上面的方法,用WXPayUtil中的
                 publicstatic String generateSignature(final Map<String, String> data, Stringkey)方法,
                 data是将除了paySign外,其他5个参数放到map中,key是四大配置参数中的API秘钥（paternerKey）,
                 得到了paySign后,不要忘记再将paySign put到只有5个参数的map中,这样才能凑齐最后的第6个参数。
                */
            try {
                paySign = WXPayUtil.generateSignature(payMap, wechatConfig.getWechatkey());
            } catch (Exception e) {
                log.info("paySign签名算法生成异常~");
            }
            payMap.put("paySign", paySign);
            log.info("payMap值为：" + gson.toJson(payMap));
            return payMap;
            }
        }

        @Override
        public void whechatPayNotify (HttpServletRequest request, HttpServletResponse response){
            log.info("进入微信支付异步通知");
            Gson gson = new Gson();
            Map<String, String> notifyMap = null;
            String localSign = "";
            String notifyXml = "";
            String resXml;
            try {
                String inputLine;
                //获取回调XML信息
                while ((inputLine = request.getReader().readLine()) != null) {
                    notifyXml = notifyXml + inputLine;
                }
                request.getReader().close();
            } catch (Exception e) {
                log.info("xml获取失败：" + e);
            }
            log.info("接收到的回调xml数据：" + notifyXml);
            try {
                notifyMap = WXPayUtil.xmlToMap(notifyXml);
            } catch (Exception e) {
                log.info("回调xml消息转map异常~");
            }
            String outTradeNo = notifyMap.get("out_trade_no");
            String resultCode = notifyMap.get("result_code");
            String returnCode = notifyMap.get("return_code");
            String sign = notifyMap.get("sign");
            //注意回调消息必须按微信返的参数顺序进行拼接
            List<String> keys = new ArrayList<>(notifyMap.keySet());
            Collections.sort(keys);
            //拼接生成本地签名参数
            StringBuffer content = new StringBuffer();
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                String value = notifyMap.get(key);
                if (null != value && !"".equals(value) && !"sign".equals(key) && !"key".equals(key)) {
                    content.append(key + "=" + value + "&");
                }
            }
            content.append("key=" + wechatConfig.getWechatkey());
            try {
                //生成本地签名
                localSign = WXPayUtil.MD5(content.toString()).toUpperCase();
            } catch (Exception e) {
                log.info("微信回调本地签名生成失败~", e);
            }
            log.info("本地签名是：" + localSign);
            //签名验证
            if ((!sign.equals(localSign)) || (!SUCCESS.equals(resultCode)) || (!SUCCESS.equals(returnCode))) {
                log.info("验证签名失败：" + localSign + "-->" + resultCode + "-->" + returnCode);
                log.info("MD5签名生成拼接参数为：" + content.toString());
            } else {
                //修改订单状态(-1：支付失败，0：预充值（预退款），1：到帐)
                Map<String, String> map = new HashMap<>(6);
                //订单号
                map.put("outTradeNo", outTradeNo);
                //订单状态
                map.put("payStatus", "1");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = df.format(new Date());
                map.put("updateTime",format);
                System.out.println(map);
                //修改订单状态
               // String url = commonConfig.getGonganUrl() + "/api/audit/pay/whechatPayNotify";
                String updateOrderJson = gson.toJson(map);
                log.info("微信回调修改订单状态入参：" + updateOrderJson);
                //String result = HttpHelper.doHttpPost(url, updateOrderJson);
                Integer result= mapper.updateOrder(map);
                if (result != null) {
                    if (result.equals(ONE)) {
                        log.info("微信回调成功：" + outTradeNo);
                        //数据库状态修改成功通知微信不需要再推送回调消息了
                        resXml = "<xml>"
                                + "<return_code><![CDATA[SUCCESS]]></return_code>"
                                + "<return_msg><![CDATA[OK]]></return_msg>"
                                + "</xml> ";
                        //通知微信已收到回调消息停止重复推送
                        try {
                            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                            out.write(resXml.getBytes());
                            out.flush();
                            out.close();
                        } catch (IOException e) {
                            log.info("微信支付回调成功输出流异常~");
                        }
                    }
                }
            }
        }

//    @Override
//    public String getWechatInfo(String openid) {
//       // String url = commonConfig.getGonganUrl() + "/api/audit/pay/getWechatInfo?openid="+openid;
//        String result = HttpHelper.requestGetAPI(url);
//        log.info("用户授权信息返回：" + result);
//        return result;
//    }

    /**
     * 获取用户请求ip
     *
     * @param request
     * @return
     */
    private String getUserIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
