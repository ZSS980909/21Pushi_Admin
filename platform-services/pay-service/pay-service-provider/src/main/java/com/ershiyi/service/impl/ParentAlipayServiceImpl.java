package com.ershiyi.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.ershiyi.config.ParentAlipayConfig;
import com.ershiyi.dto.AlipayInDTO;
import com.ershiyi.dto.ParentAlipayInDTO;
import com.ershiyi.mapper.ParentAlipayMapper;
import com.ershiyi.service.ParentAlipayService;
import com.ershiyi.utils.CreateImageNameUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service

public class ParentAlipayServiceImpl extends BaseServiceImpl<AlipayInDTO, ParentAlipayMapper> implements ParentAlipayService {
    private static final Logger log = LoggerFactory.getLogger(AlipayServiceImpl.class);
    @Autowired
    private ParentAlipayConfig parentalipayConfig;
    @Override
    public String parentalipayOrderPay(ParentAlipayInDTO parentalipayindto){
        Gson gson = new Gson();
        //构造client
        CertAlipayRequest certAlipayRequest   =   new   CertAlipayRequest ();
        //设置网关地址
        certAlipayRequest . setServerUrl ( parentalipayConfig.getParentgateway());
        //设置应用Id
        certAlipayRequest . setAppId ( parentalipayConfig.getParentappid() );
        //设置应用私钥
        certAlipayRequest . setPrivateKey ( parentalipayConfig.getParentrasPrivateKey() );
        //设置请求格式，固定值json
        certAlipayRequest . setFormat ( "json" );
        //设置字符集
        certAlipayRequest . setCharset (  parentalipayConfig.getParentcharset() );
        //设置签名类型
        certAlipayRequest . setSignType ( "RSA2" );
        //设置应用公钥证书路径
        certAlipayRequest . setCertPath ( parentalipayConfig.getCertParentPath() );
        //设置支付宝公钥证书路径
        certAlipayRequest . setAlipayPublicCertPath ( parentalipayConfig.getAlipayParentPublicCertPath() );
        //设置支付宝根证书路径
        certAlipayRequest . setRootCertPath ( parentalipayConfig.getRootCertParentPath() );
        //构造client
        AlipayClient   alipayClient   = null;
        try {
            alipayClient = new DefaultAlipayClient( certAlipayRequest );
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        String orderId = CreateImageNameUtil.createRandom();
        log.info("当前支付宝订单号为" + orderId);
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest   request   =   new   AlipayTradeAppPayRequest ();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
//        request.setBizContent("{\"out_trade_no\":"+orderId +" ,\"total_amount\":" + parentalipayindto.getRechargeAmount()
//                + ", \"subject\":"+ parentalipayindto.getShopName()+", \"product_code\":\"QUICK_WAP_WAY\",\"type\":"+parentalipayindto.getType()
//                +",\"studenterId\":"+parentalipayindto.getStudenterId()+",\"courseId\":"+
//                parentalipayindto.getCourseId()+",\"integralvalue\":"+parentalipayindto.getRawIntegral()+"}");
        AlipayTradeAppPayModel   model   =   new   AlipayTradeAppPayModel ();
       // model . setBody ( parentalipayindto.getShopBody() );
        model . setSubject ( parentalipayindto.getShopName() );
    //  String orderId = CreateImageNameUtil.createRandom();
        log.info("当前支付宝订单号为" + orderId);
        model.setOutTradeNo(orderId);
       // model . setOutTradeNo ( outtradeno );
        model . setTimeoutExpress ( "30m" );
       // model . setTotalAmount ( parentalipayindto.getRechargeAmount().toString() );
        model.setTotalAmount("0.01");
        model . setProductCode ( "QUICK_MSECURITY_PAY" );
        model.setBody("{\"out_trade_no\":"+orderId +" ,\"total_amount\":" + parentalipayindto.getRechargeAmount()
                + ", \"subject\":"+ parentalipayindto.getShopName()+", \"product_code\":\"QUICK_WAP_WAY\",\"type\":"+parentalipayindto.getType()
                +",\"studenterId\":"+parentalipayindto.getStudenterId()+",\"courseId\":"+
                parentalipayindto.getCourseId()+",\"integralvalue\":"+parentalipayindto.getRawIntegral()+"}");
        request . setBizModel ( model );
        request . setNotifyUrl ( parentalipayConfig.getParentnotifyUrl() );
        try  {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse   response   =   alipayClient . sdkExecute ( request );

            //插入预充值订单信息
            //预生成订单
            String payJson = gson.toJson(parentalipayindto);
            log.info("支付宝支付预订单信息==>" + payJson);
            parentalipayindto.setRawIntegral(0.0);
            parentalipayindto.setOrderId(orderId);
            //parentalipayindto.setIntegralValue(parentalipayindto.getRawIntegral()+0);
            //parentalipayindto.setIntegralValue(parentalipayindto.getChangeIntegral()+parentalipayindto.getRawIntegral());
            Integer result= mapper.parentInsertOrder(parentalipayindto);

            // 预订单生成失败
            if (null == result || "".equals(result) || result.equals("0")) {
                log.info("订单插入失败：" + result);
            } else {
                log.info("订单插入成功：" + result);
            }
            return response . getBody ();

           // System . out . println ( response . getBody ()); //就是orderString 可以直接给客户端请求，无需再做处理。
        }  catch  ( AlipayApiException   e ) {
            e . printStackTrace ();
        }

        return null;
    }

    @Override
    public void parentalipayOrderNotify(HttpServletRequest request, HttpServletResponse response) {
       // log.info("回调中~~");
        Gson gson = new Gson();
        //获取支付宝POST过来反馈信息
        Map< String , String > params   =   new HashMap< String , String >();
        Map   requestParams   =   request . getParameterMap ();
        for  (Iterator iter = requestParams . keySet (). iterator (); iter . hasNext ();) {
            String   name   =  ( String )  iter . next ();
            String []  values   =  ( String [])  requestParams . get ( name );
            String   valueStr   =   "" ;
            for  ( int   i   =   0 ;  i   <   values . length ;  i ++ ) {
                valueStr   =  ( i   ==   values . length   -   1 )  ?   valueStr   +   values [ i ]
                        :  valueStr   +   values [ i ]  +   "," ;
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params . put ( name ,  valueStr );
        }
        String indto = gson.toJson(params);

        log.info("支付宝回调入参: " + indto);
        try {
        //商户订单号
        String outTradeNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        log.info("订单号" + outTradeNo);

        //支付宝交易号
        String tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
        log.info("支付宝交易号" + tradeNo);

        //交易状态
        String tradeStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
        log.info("交易状态" + tradeStatus);

        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCertCheckV1(Map<String, String> params, String publicKeyCertPath, String charset,String signType)
                boolean   flag   =   AlipaySignature. rsaCertCheckV1 ( params ,  parentalipayConfig.getAlipayParentPublicCertPath() ,  parentalipayConfig.getParentcharset() , "RSA2" );
                if(flag){
                    log.info("=============================验签成功==============================");
                    log.info("已接收到支付成功状态的回调消息====>" + tradeStatus);
                        //判断支付状态是否成功,只有成功的回调消息才进行状态更新处理
                    log.info("支付人支付宝号" + params.get("buyer_logon_id"));
                    //修改订单状态(-1：支付失败，0：预充值（预退款），1：到帐)
                    //  System.out.println(map);
//                    String url = commonConfig.getGonganUrl() + "/api/audit/pay/whechatPayNotify";
                    //String updateOrderJson = gson.toJson(mapUpdate);
                    //log.info("微信回调修改订单状态入参：" + updateOrderJson);
                    Integer result = mapper.updateOrder(params);//HttpHelper.doHttpPost(url, updateOrderJson;
                    if (result != null) {
                        //处理结束之后，返回success,支付宝系统将不再发送异步回调请求
                        if (result==1) {
                            log.info("订单完成");
                            log.info("订单号：" + outTradeNo);
                            response.getOutputStream().print("success");
                        }
                    }

                }

            } catch (Exception e) {
            e.printStackTrace();
        }

        //mapper.parentalipayOrderNotify(request,response);
    }
}
