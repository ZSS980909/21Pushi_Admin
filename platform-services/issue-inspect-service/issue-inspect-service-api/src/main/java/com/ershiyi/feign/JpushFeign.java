package com.ershiyi.feign;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dto.JpushPojo;
import com.ershiyi.feign.fallback.JpushFeignFallBack;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(value = "issue-inspect-service-provider", fallback = JpushFeignFallBack.class)
@FeignClient(value = "issue-inspect-service-provider")
public interface JpushFeign {
    @GetMapping("/client")
    //@PathVariable("Sendtype")String Sendtype, @PathVariable("RegistrationId")String RegistrationId, @PathVariable("Msg") String Msg
    AbstractBaseResult JpushBytype();
}
