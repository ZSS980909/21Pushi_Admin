package com.ershiyi.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.JsonObject;


import java.rmi.ServerException;
import java.util.Date;

public class LoginUtils {

    private static String  id="LTAI4G2CQtVRFydKEvtzSMyr";
    private static String password = "v7xbUZfd88vN8mqeEp7IKxvTIQAFBG";

    /**
     *  注册信息模板
     */
    public static String REGISTER = "SMS_191885713";
    /**
     * 忘记密码模板
     */
    public static String UPDATE_PW = "SMS_191885712";

    /**
     * 验证码登录模板
     */
    public static String PHONE_LOGIN = "SMS_191885715";

    /**
     * 绑定模板
     */
    public static String BINDING = "SMS_191885716";

    /**
     * 修改手机号
     */
    public static String MODIFY_PHONE = "SMS_191885711";


// response:格式说明
//    {
//        "Message":"OK",
//        "RequestId":"2184201F-BFB3-446B-B1F2-C746B7BF0657",
//            "BizId":"197703245997295588^0",
//            "Code":"OK"
//   }

    /**
     * 发送验证码短信
     * @param phoneNum
     * @param type
     * @param code
     * @return
     */
    public  static CommonResponse  sendSms(String phoneNum,String type,String code){

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",id ,password );
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("SignName", "21小时教育科技有限公司");
        request.putQueryParameter("TemplateCode", type);
        request.putQueryParameter("PhoneNumbers", phoneNum);

        // 模板参数json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);

        request.putQueryParameter("TemplateParam", jsonObject.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response;
        }  catch (ClientException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 发送验证码短信
     * @param phoneNum
     * @param type
     * @param code
     * @return
     */
    public  static CommonResponse  sendSms(String phoneNum,String type,String code,String name){

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",id ,password );
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("SignName", "21小时教育科技有限公司");
        request.putQueryParameter("TemplateCode", type);
        request.putQueryParameter("PhoneNumbers", phoneNum);

        // 模板参数json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("name",name);

        request.putQueryParameter("TemplateParam", jsonObject.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response;
        }  catch (ClientException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 查询短信发送情况，手机号，日期和分页查询
     * @param phoneNum
     * @param date
     * @param pageSize
     * @param currentPage
     * @return
     */
    public static CommonResponse QuerySendDetails(String phoneNum,String date,String pageSize,String currentPage){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("QuerySendDetails");

        request.putQueryParameter("RegionId", "cn-hangzhou");

        request.putQueryParameter("PhoneNumber", phoneNum);
        request.putQueryParameter("SendDate", date);
        request.putQueryParameter("PageSize", pageSize);
        request.putQueryParameter("CurrentPage", currentPage);
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response;
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
        return null;

    }
}
