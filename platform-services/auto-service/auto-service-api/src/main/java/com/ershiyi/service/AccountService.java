package com.ershiyi.service;

import com.ershiyi.domain.SysUser;
import com.ershiyi.domain.SysUser_Code;
import com.ershiyi.dto.AccountAuthDTO;
import com.ershiyi.vo.TokenVO;
public interface AccountService extends LoginService, UserInfoService, LogoutService  {

    /**
     * 用户账号密码登录
     *
     * @param accountAuthDTO
     * @return
     */
    TokenVO accountLogin(AccountAuthDTO accountAuthDTO);
}
