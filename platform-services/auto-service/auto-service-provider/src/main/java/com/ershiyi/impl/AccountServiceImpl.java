package com.ershiyi.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.Student_User;
import com.ershiyi.domain.SysUser;
import com.ershiyi.domain.UserLogins;
import com.ershiyi.dto.AccountAuthDTO;
import com.ershiyi.feign.SysUserFeign;
import com.ershiyi.service.AccountService;
import com.ershiyi.utils.RedisUtils;
import com.ershiyi.utils.TokenUtils;
import com.ershiyi.utils.WebUtils;
import com.ershiyi.vo.SysUserVo;
import com.ershiyi.vo.TokenVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private SysUserFeign sysUserFeign;

    @Override
    //@Cacheable(cacheNames = "auth-token", key = "#accountAuthDTO.loginName")
    public TokenVO accountLogin(AccountAuthDTO accountAuthDTO) {
        // 定义一个类来接收错误信息
        TokenVO error = new TokenVO();
        error.setUserKey("错误信息");
        // 获取当前手机用户的相信信息
        SysUser sysUser = sysUserFeign.findByLoginId(accountAuthDTO.getLoginId());
        //根据usertypeId判断是否是正确端口  家长端/学生端  对比一下usertypeid
       // System.out.println(sysUser);
        if(sysUser==null){
            error.setToken("账号不存在！");
            return error;
        }
        if (sysUser.getUserTypeId()==accountAuthDTO.getUserTypeId()){

        }else{
            error.setToken("账户用户或密码不对(友情提示:账户类型,密码大小写是否输入错误)！");
            return error;
        }
//        if(!sysUser.getUserTypeId().equals(accountAuthDTO.getUserTypeId())){
//            error.setToken("账号不存在！");
//            return error;
//        }
        String device="";
        String sys="";
        String parenterId="";
        Student_User bystudenterId = null;
        if(accountAuthDTO.getLoginType()==2){
            // 登录方式为验证码登录。验证验证码的正确性
            if(accountAuthDTO.getValidataCode()==null||accountAuthDTO.getValidataCode().isEmpty()){
                error.setToken("验证码不能为空！");
                return error;
            }
            boolean b = RedisUtils.hasKey(accountAuthDTO.getLoginId());
            if(!b){
                error.setToken("请重新发送验证码！");
                return error;
            }
            if(!accountAuthDTO.getValidataCode().equals(RedisUtils.get(accountAuthDTO.getLoginId()))){
                error.setToken("验证码不正确");
                return error;
            }
            // 验证码无误，销毁验证码
            RedisUtils.del(accountAuthDTO.getLoginId());
        }else if(accountAuthDTO.getLoginType()==1){
            if (sysUser == null) {
                error.setToken("网络阻塞,请稍后重试");
                return error;
            }
            if (sysUser.getIfuse() == -1) {
                error.setToken("用户不存在");
                return error;
            }
            if (sysUser.getIfuse() == 0) {
                error.setToken("账号已停用");
                return error;
            }
            String pass = SecureUtil.md5(accountAuthDTO.getPassWord());
            if (!StringUtils.endsWithIgnoreCase(pass, sysUser.getPwd())) {
                error.setToken("用户名密码不正确");
                return error;
            }
        }else{
            return error;
        }
        if (accountAuthDTO.getUserTypeId()==3){
            parenterId = sysUserFeign.findParenterId(sysUser.getGuid());
            sys = "目前暂不使用";
            device = UserAgentUtil.parse(WebUtils.getUa()).getPlatform().getName();
            int loginType=3;
            sysUserFeign.Logs(accountAuthDTO.getLoginId(), accountAuthDTO.getUniqueCode(), accountAuthDTO.getLoginCode(),accountAuthDTO.getUserTypeId(),loginType);
            return createToken(parenterId, sys, device, "");
        }else if(accountAuthDTO.getUserTypeId()==1){
            bystudenterId = sysUserFeign.findByStudenterId(sysUser.getGuid());
            sys = "目前暂不使用";
            int loginType=1;
            device = UserAgentUtil.parse(WebUtils.getUa()).getPlatform().getName();
            sysUserFeign.Logs(accountAuthDTO.getLoginId(), accountAuthDTO.getUniqueCode(), accountAuthDTO.getLoginCode(),accountAuthDTO.getUserTypeId(),loginType);
            return createToken(bystudenterId.getStudenterId(), sys, device, bystudenterId.getSchoolId());
        }
        return null;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public SysUserVo userInfo() {
        String userKey = TokenUtils.getUserKey();
        return sysUserFeign.findUserMsgById(userKey);
    }

    /**
     * 刷新令牌
     *
     * @return
     */
    @Override
    public TokenVO refresh() {
        String token = TokenUtils.getRequestToken();
        if (RedisUtils.hasKey(token)) {
            String userKey = TokenUtils.getUserKey(token);
            String sys = TokenUtils.getStr(token, TokenUtils.SYSTEM_KEY);
            String device = TokenUtils.getStr(token, TokenUtils.DEVICE_KEY);
            TokenUtils.clear(token);
            return createToken(userKey, sys, device,null);
        }
        throw RespEnum.AUTH.throwException();
    }

    /**
     * 登出
     *
     * @return
     */
    @Override
    public TokenVO exit() {
        TokenUtils.clear();
        return TokenVO.builder().token(TokenUtils.getRequestToken()).build();
    }

}
