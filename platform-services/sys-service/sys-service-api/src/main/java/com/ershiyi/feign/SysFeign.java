package com.ershiyi.feign;

import com.ershiyi.domain.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("sys-service-provider")
public interface SysFeign {
//   @GetMapping("/sys/findAll")
//    String findAll(@RequestBody SysUser sysUser);
}
