package com.ershiyi.feign;

import com.ershiyi.domain.Student_User;
import com.ershiyi.domain.SysUser;
import com.ershiyi.domain.SysUser_Code;
import com.ershiyi.dto.AccountAuthDTO;
import com.ershiyi.feign.fallback.SysUserFeignFallback;
import com.ershiyi.vo.SysUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "sys-service-provider", fallback = SysUserFeignFallback.class)
public interface SysUserFeign {

    @GetMapping("/client/sys/user/login/{loginId}")
    SysUser findByLoginId(@PathVariable("loginId") String loginId);

    @GetMapping("/client/sys/user/{id}")
    SysUser findById(@PathVariable("id") String id);

    @GetMapping("/client/sys/userMsg/{id}")
    SysUserVo findUserMsgById(@PathVariable("id") String id);

    @GetMapping("/client/sys/userkey/{id}")
    Student_User findByStudenterId(@PathVariable("id") String guid);

    @GetMapping("/client/sys/user/Logs/{loginId}")
    Integer Logs(@PathVariable("loginId") String loginId, @RequestParam(value="uniquecode", required = false) String uniquecode, @RequestParam(value="logincode", required = false) String logincode,@RequestParam(value="usertypeid", required = false) int usertypeid,@RequestParam(value="logintype", required = false) int logintype);

    @GetMapping("/client/sys/parentInfo/{id}")
    String findParenterId(@PathVariable("id") String guid);
}
