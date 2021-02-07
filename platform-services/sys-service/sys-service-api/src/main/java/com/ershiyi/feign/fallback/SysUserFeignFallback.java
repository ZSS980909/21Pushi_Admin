package com.ershiyi.feign.fallback;

import com.ershiyi.domain.Student_User;
import com.ershiyi.domain.SysUser;
import com.ershiyi.domain.SysUser_Code;
import com.ershiyi.dto.AccountAuthDTO;
import com.ershiyi.feign.SysUserFeign;
import com.ershiyi.vo.SysUserVo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Component
public class SysUserFeignFallback implements SysUserFeign {

    @Override
    public SysUser findByLoginId(@PathVariable("loginId") String loginId) {
        return null;
    }

    @Override
    public SysUser findById(@PathVariable("id") String id) {
        return null;
    }

    @Override
    public SysUserVo findUserMsgById(@PathVariable("id") String id) {
        return null;
    }

    @Override
    public Student_User findByStudenterId(@PathVariable("id") String guid) {
        return null;
    }

    @Override
    public Integer Logs(@PathVariable("loginId") String loginId,@PathVariable("uniquecode") String uniquecode, @RequestParam(value="logincode", required = false) String logincode,@RequestParam(value="usertypeid", required = false) int usertypeid,@RequestParam(value="logintype", required = false) int logintype) {
        return null;
    }

    @Override
    public String findParenterId(@PathVariable("id") String guid) {
        return null;
    }

}
