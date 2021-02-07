package com.ershiyi.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.ershiyi.config.AlipayConfig;
import com.ershiyi.dto.AlipayInDTO;
import com.ershiyi.mapper.AlipayMapper;
import com.ershiyi.service.AlipayService;
import com.ershiyi.utils.CreateImageNameUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 */
@Service
public class AlipayServiceImpl extends BaseServiceImpl<AlipayInDTO, AlipayMapper> implements AlipayService {

    private static final Logger log = LoggerFactory.getLogger(AlipayServiceImpl.class);

    private static final String SUCCESS = "TRADE_SUCCESS";

    private static final String ONE = "1";

    @Autowired
    private AlipayConfig alipayConfig;


    @Override
    public void alipayOrderPay(HttpServletResponse response, AlipayInDTO inDTO) throws IOException {
        Gson gson = new Gson();
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGateway(), alipayConfig.getAppid(), alipayConfig.getRasPrivateKey(), "json", alipayConfig.getCharset(),alipayConfig.getAlipayPublicKey(), "RSA2");
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
        alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        //生成随机订单号且唯一
        String orderId = CreateImageNameUtil.createRandom();
        log.info("当前支付宝订单号为" + orderId);
        // 封装请求支付信息
        alipayRequest.setBizContent("{ \"out_trade_no\":" + orderId + ", \"total_amount\":" + inDTO.getRechargeAmount()
                + ", \"subject\":\"在线充值\", \"product_code\":\"QUICK_WAP_WAY\",\"type\":"+inDTO.getType()+",\"studenterId\":"+inDTO.getStudenterId()+",\"courseId\":"+inDTO.getCourseId()+",\"integralvalue\":"+inDTO.getRawIntegral()+"}");
        //订单号
        inDTO.setOrderId(orderId);
        //支付状态 -1：交易关闭，0：预充值（预退款），1：到帐
        inDTO.setStatus(0);
        inDTO.setKeyWord("PAY");
        inDTO.setOpenId( alipayConfig.getAppid());
        inDTO.setPaymentType(2);
        inDTO.setChangeIntegral(inDTO.getRechargeAmount()*10);
        String integral=mapper.selectIntegral(inDTO);
        inDTO.setRawIntegral(Double.valueOf(integral));
        inDTO.setIntegralValue(inDTO.getRawIntegral()+inDTO.getChangeIntegral());
        log.info("充值金额为" + inDTO.getRechargeAmount());
        //插入预充值订单信息
        //预生成订单
        String payJson = gson.toJson(inDTO);
        log.info("支付宝支付预订单信息==>" + payJson);
       Integer result= mapper.insertorder(inDTO);
       // 预订单生成失败
        if (null == result || "".equals(result) || result.equals("0")) {
            log.info("订单插入失败：" + result);
        } else {
            log.info("订单插入成功：" + result);
            String form = "";
            try {
                form = (alipayClient.pageExecute(alipayRequest)).getBody();
                log.info(form);
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            log.info(form);
            response.setContentType("text/html;charset=" + alipayConfig.getCharset());
            response.getWriter().write(form);
            response.getWriter().flush();
            response.getWriter().close();
        }
    }

    @Override
    public void alipayOrderNotify(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();
        Map<String, String> mapUpdate = new HashMap<>(6);
        boolean signVerified = false;
        log.info("支付宝回调中！");
        try {
            //获取支付宝POST过来反馈信息
            Map<String, String> map = new HashMap<>();
            Map requestParams = request.getParameterMap();
            log.info("map输出值====>" + requestParams);
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                map.put(name, valueStr);
            }
            String indto = gson.toJson(map);
         // String body = map.get("body");
         // Object body = requestParams.get("body");
            log.info("支付宝回调入参: " + indto);
            //商户订单号
            String outTradeNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            log.info("订单号" + outTradeNo);

            //支付宝交易号
            String tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            log.info("支付宝交易号" + tradeNo);

            //交易状态
            String tradeStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            log.info("交易状态" + tradeStatus);
            //验签
            signVerified = AlipaySignature.rsaCheckV1(map, alipayConfig.getPublicKey(), "UTF-8", "RSA2");
            log.info("验签结果======>" + signVerified);
            if (signVerified) {
                // TODO 验签成功后
                log.info("=============================验签成功==============================");
                log.info("已接收到支付成功状态的回调消息====>" + tradeStatus);
                //判断支付状态是否成功,只有成功的回调消息才进行状态更新处理
                if (tradeStatus.equals(SUCCESS)) {
                    log.info("支付人支付宝号" + map.get("buyer_logon_id"));
                    //修改订单状态(-1：支付失败，0：预充值（预退款），1：到帐)
                    //订单号
                    mapUpdate.put("outTradeNo", outTradeNo);
                    //订单状态
                    mapUpdate.put("payStatus", "1");
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String format = df.format(new Date());
                    mapUpdate.put("updateTime",format);
                  //  System.out.println(map);
//                    String url = commonConfig.getGonganUrl() + "/api/audit/pay/whechatPayNotify";
                   //String updateOrderJson = gson.toJson(mapUpdate);
                    //log.info("微信回调修改订单状态入参：" + updateOrderJson);
                  //  Integer result = mapper.updateOrder(mapUpdate);//HttpHelper.doHttpPost(url, updateOrderJson;
                    mapUpdate.put("type","2");
                    Integer result =0;
                    if(mapUpdate.get("type")=="1"){
                         result = mapper.updateOrder(mapUpdate);
                        //学生购买1
                        //家长赠送2
                    }else if(mapUpdate.get("type")=="2"){
                         result = mapper.updateOrderbyfather(mapUpdate);
                    }


                    if (result != null) {
                        //处理结束之后，返回success,支付宝系统将不再发送异步回调请求
                        if (result==1) {
                            log.info("订单完成");
                            log.info("订单号：" + outTradeNo);
                            response.getOutputStream().print("success");
                        }
                    }
                }
            } else {
                // TODO 验签失败则记录异常日志，并在response中返回failure.
                log.info("=============================验证失败,不去更新状态==============================");
                response.getOutputStream().print("fail");
            }
        } catch (Exception e) {
            log.info("学生端 支付宝支付回调错误!!!" + e);
        }
    }
}
