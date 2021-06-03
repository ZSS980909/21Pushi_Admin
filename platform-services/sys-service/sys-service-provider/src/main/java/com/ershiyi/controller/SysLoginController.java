package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.School;
import com.ershiyi.domain.SysUser;
import com.ershiyi.domain.ValidataCode;
import com.ershiyi.service.SysLoginService;
import com.ershiyi.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/systemLogin")
@Api(value = "登录管理", tags = {"用户登录详细"})
public class SysLoginController{
    @Autowired
    private SysLoginService loginService;
    /**
     * 获取验证码
     */
    @PostMapping("/getVerificationCode")
    @ResponseBody
    @ApiOperation(value = "获取验证码", notes = "只需要传验证码发送类型type,手机号码mobilePhone")
    public AbstractBaseResult getVerificationCode(@RequestBody ValidataCode validatacode) {
        // 如果类型为注册,先判断是否为已注册
        if(validatacode.getType()==1){
            String user = loginService.userRegistered(new SysUser(validatacode.getMobilePhone()));
            if(!"0".equals(user)){
                // 不为0代表已经注册
                return RespEnum.CHECK_FAILED.result("用户已存在！");
            }
        }
        // 如果类型为登录
        if(validatacode.getType()==3){
            if(loginService.queryStudent(validatacode.getMobilePhone()).isEmpty()){
                // 当前用户未注册
                return RespEnum.CHECK_FAILED.result("该用户不存在！");
            }
        }
        if (validatacode == null || StringUtils.isEmpty(validatacode.getMobilePhone().toString())) {
            return RespEnum.ERROR.result("手机号码参数为空");
        }
        if (validatacode == null || StringUtils.isEmpty(validatacode.getType().toString())) {
            return RespEnum.ERROR.result("验证类型为空");
        }
        loginService.getVerificationCode(validatacode);
        return RespEnum.OK.result("成功");

    }

    /**
     * 注册帐号
     * @param sysuser
     * @return
     */
    @PostMapping("/loginSysUser")
    @ResponseBody
    @ApiOperation(value = "新用户注册", notes = "只需要传用户帐号loginid,注册帐号类型usertypeid,密码pwd,学校idschool")
    public AbstractBaseResult loginSysUser(@RequestBody SysUser sysuser) {
        /**
         * 先验证验证码
         */
        if (sysuser == null || StringUtils.isEmpty(sysuser.getLoginId().toString())) {
            return RespEnum.ERROR.result("账户号为空");
        }
        if (sysuser == null || StringUtils.isEmpty(sysuser.getPwd().toString())) {
            return RespEnum.ERROR.result("密码为空");
        }
        if(sysuser.getRealName()==null||sysuser.getRealName().isEmpty()){
            return RespEnum.CHECK_FAILED.result("用户真实姓名不能为空");
        }
        boolean b = RedisUtils.hasKey(sysuser.getLoginId());
        if(!b){
            return  RespEnum.SYS_ERROR.result("请重新注册,发送验证码");
        }
        if(!sysuser.getValidataCode().equals(RedisUtils.get(sysuser.getLoginId()))){
            return RespEnum.SYS_ERROR.result("验证码不正确");
        }
        // 清除验证码
        RedisUtils.del(sysuser.getLoginId());
        /**
         * 查询账户是否被注册
         */
        String s = loginService.userRegistered(sysuser);
        if(s.isEmpty()||!"0".equals(s)){
            return RespEnum.CREATE_DATA_ERROR.result("用户已经存在");
        }
        return RespEnum.OK.result(loginService.loginSysUser(sysuser));

    }

    /**
     * 修改密码
     * @param sysuser
     * @return
     */
    @PostMapping("/updateSysUser")
    @ResponseBody
    @ApiOperation(value = "修改密码", notes = "2种方式,1根据手机号码短信,2根据邮箱,目前只有手机短信")
    public AbstractBaseResult updateSysUser(@RequestBody SysUser sysuser) {
        /**
         * 先验证验证码
         */
        // 根据手机号查询是否存在该用户
        List<String> user = loginService.queryStudent(sysuser.getLoginId());
        if(user.isEmpty()){
            // 当前用户不存在！
            return RespEnum.ERROR.result("该用户不存在！");
        }
        boolean b = RedisUtils.hasKey(sysuser.getLoginId());
        if(!b){
            return RespEnum.SYS_ERROR.result("请重新注册,发送验证码");
        }
        if(!sysuser.getValidataCode().equals(RedisUtils.get(sysuser.getLoginId()))){
            return RespEnum.SYS_ERROR.result("验证码不正确");
        }
        if (sysuser == null || StringUtils.isEmpty(sysuser.getLoginId().toString())) {
            return RespEnum.ERROR.result("账户号为空");
        }
        if (sysuser == null || StringUtils.isEmpty(sysuser.getPwd().toString())) {
            return RespEnum.ERROR.result("密码为空");
        }
        return RespEnum.OK.result(loginService.updateSysUserByPhone(sysuser));

    }

    /**
     * 获取城市
     * @return  已作废
     */
    @Deprecated
    @PostMapping("/allCity")
    @ResponseBody
    @ApiOperation(value = "获取所有城市", notes = "注册时获取所有城市")
    public AbstractBaseResult allCity() {
        return RespEnum.OK.result(loginService.allCity());

    }

    /**
     * 获取学校名称
     * @return
     */
    @PostMapping("/allCitybyName")
    @ResponseBody
    @ApiOperation(value = "获取学校名称", notes = "获取学校名称")
    public AbstractBaseResult allCitybyName(@RequestBody School school) {
        String[] split = school.getProvince().split("-");
        for(int i=0;i<split.length;i++){
            if(i==0){
                school.setProvince(split[i]);
            }else if(i==1){
                school.setCity(split[i]);
            }else if(i==2){
                school.setArea(split[i]);
            }
        }
        school.setSchool_name("%"+school.getSchool_name()+"%");
        return RespEnum.OK.result(loginService.allCitybyName(school));

    }

}
