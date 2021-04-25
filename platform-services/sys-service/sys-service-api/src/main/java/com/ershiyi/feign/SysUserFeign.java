package com.ershiyi.feign;

import com.ershiyi.domain.Student_User;
import com.ershiyi.domain.SysUser;
import com.ershiyi.vo.SysUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "sys-service-provider")
public interface SysUserFeign {

    @GetMapping("/client/sys/user/login/{loginId}")
    SysUser findByLoginId(@PathVariable("loginId") String loginId);

    @GetMapping("/client/sys/user/{id}")
    SysUser findById(@PathVariable("id") String id);

    @GetMapping("/client/sys/userMsg/{id}")
    SysUserVo findUserMsgById(@PathVariable("id") String id);

    @GetMapping("/client/sys/user/Logs/{loginId}")
    Integer Logs(@PathVariable("loginId") String loginId, @RequestParam(value="uniquecode", required = false) String uniquecode, @RequestParam(value="logincode", required = false) String logincode,@RequestParam(value="usertypeid", required = false) int usertypeid,@RequestParam(value="logintype", required = false) int logintype);

    @GetMapping("/client/sys/getUserKey")
    Student_User getUserKey(@RequestParam(value="guid", required = true) String guid, @RequestParam(value="type", required = true) int type);
}
