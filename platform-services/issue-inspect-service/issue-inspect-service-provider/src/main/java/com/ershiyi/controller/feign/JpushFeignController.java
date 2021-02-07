package com.ershiyi.controller.feign;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.dto.JpushPojo;
import com.ershiyi.feign.JpushFeign;
import com.ershiyi.service.JpushService;
import com.google.common.net.HttpHeaders;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@ApiIgnore
@RestController
public class JpushFeignController implements JpushFeign, RequestInterceptor {
    @Autowired
    private JpushService jpushservice;
//String Sendtype,String RegistrationId,String Msg
    @Override
    public AbstractBaseResult JpushBytype() {
       // System.out.println("进入"+Sendtype+RegistrationId+Msg);
      //  return RespEnum.OK.result(jpushservice.sendJpush(Sendtype,RegistrationId,Msg));
        //return RespEnum.OK.result(jpushservice.sendJpush(JpushPojo jpush));
        return  null;
    }
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //添加token
        requestTemplate.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
    }
}
