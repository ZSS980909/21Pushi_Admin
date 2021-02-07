package com.ershiyi.service;

import com.ershiyi.domain.entity.CommonResult;
import com.ershiyi.dto.RequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: 验证码调用
 * @author: zss98
 * @date: 2020-09-09 16:31
 * @version: 1.0
 */
@FeignClient(value = "sys-service-provider")
public interface VerificationCodeService {

    @PostMapping(value = "/systemLogin/getVerificationCode",consumes = "application/json")
    public CommonResult sendVerificationCode(@RequestBody RequestDTO request);
}
