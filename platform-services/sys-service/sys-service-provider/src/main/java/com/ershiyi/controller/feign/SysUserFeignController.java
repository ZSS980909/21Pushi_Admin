package com.ershiyi.controller.feign;

import com.ershiyi.domain.Student_User;
import com.ershiyi.domain.SysUser;
import com.ershiyi.feign.SysUserFeign;
import com.ershiyi.service.SysUserService;
import com.ershiyi.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@ApiIgnore
@RestController
public class SysUserFeignController implements SysUserFeign {

    @Autowired
    private SysUserService sysUserService;

    @Override
   //@Cacheable(cacheNames = "sysUser", key = "#loginId")
    public SysUser findByLoginId(@PathVariable("loginId") String loginId) {
        SysUser sysUser = sysUserService.selectSysUserByLoginId(loginId);
        if(sysUser==null){
            SysUser sys=new SysUser();
            sys.setIfuse(-1);
            return  sys;
        }
        return sysUser;
    }

    @Override
    @Cacheable(cacheNames = "sysUser", key = "#id")
    public SysUser findById(@PathVariable("id") String id) {
        return sysUserService.selectSysUserById(id);
    }

    @Override
    @Cacheable(cacheNames = "sysUserVo", key = "#id")
    public SysUserVo findUserMsgById(@PathVariable("id") String id) {
        return sysUserService.findUserMsgById(id);
    }


    @Override
    public Integer Logs(@PathVariable("loginId") String loginId,@RequestParam("uniquecode") String uniquecode, @RequestParam(value="logincode", required = false) String logincode,@RequestParam(value="usertypeid", required = false) int usertypeid,@RequestParam(value="logintype", required = false) int logintype) {
        return sysUserService.Logs(loginId,uniquecode,logincode,usertypeid,logintype);
    }

    @Override
    public Student_User getUserKey(String guid,int type) {
        return sysUserService.getUserKey(guid,type);
    }


}
