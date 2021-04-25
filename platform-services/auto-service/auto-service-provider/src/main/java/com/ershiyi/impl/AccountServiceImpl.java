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
        if(sysUser.getLoginId().length()<1){
            error.setToken("账号不存在！");
            return error;
        }
        if (sysUser.getIfuse() == 0) {
            error.setToken("账号已停用");
            return error;
        }
        if(sysUser.getUserTypeId()!=accountAuthDTO.getUserTypeId()){
            error.setToken("账号不存在！");
            return error;
        }
        int loginType = accountAuthDTO.getLoginType();
        int userType=accountAuthDTO.getUserTypeId();
        if(loginType==1){
            // 密码登录
            String pass = SecureUtil.md5(accountAuthDTO.getPassWord());
            if (!StringUtils.endsWithIgnoreCase(pass, sysUser.getPwd())) {
                error.setToken("用户名密码不正确");
                return error;
            }
        }else{
            // 登录方式为验证码登录。验证验证码的正确性
            if(accountAuthDTO.getValidataCode().length()<1){
                error.setToken("验证码不能为空！");
                return error;
            }
            if(!RedisUtils.hasKey(accountAuthDTO.getLoginId())){
                error.setToken("请重新发送验证码！");
                return error;
            }
            if(!accountAuthDTO.getValidataCode().equals(RedisUtils.get(accountAuthDTO.getLoginId()))){
                error.setToken("验证码不正确");
                return error;
            }
            // 验证码无误，销毁验证码
            RedisUtils.del(accountAuthDTO.getLoginId());
        }
        Student_User user = sysUserFeign.getUserKey(sysUser.getGuid(),userType);
        sysUserFeign.Logs(accountAuthDTO.getLoginId(), accountAuthDTO.getUniqueCode(), accountAuthDTO.getLoginCode(),userType,loginType);
        return createToken(user.getStudenterId(), "目前暂不使用",user.getDevicePassword(), "Unknown",user.getSchoolId());
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
            return createToken(userKey, sys, "",device,null);
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
