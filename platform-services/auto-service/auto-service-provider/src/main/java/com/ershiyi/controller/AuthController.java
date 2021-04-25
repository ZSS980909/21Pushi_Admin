package com.ershiyi.controller;

import com.ershiyi.aspect.annotation.BasicAuth;
import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.dto.AccountAuthDTO;
import com.ershiyi.service.AccountService;
import com.ershiyi.vo.SysUserVo;
import com.ershiyi.vo.TokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器(网关调用)
 *
 * @author liy
 * @since 2020/06/06
 */
@RestController
@RequestMapping("/authlogin")
@Api(value = "用户登录管理", tags = {"用户登录授权获取令牌"})
public class AuthController {

    @Autowired
    private AccountService accountService;



    /**
     * 账号认证
     *
     * @param accountAuthDTO 认证信息
     * @return 令牌
     */
    @PostMapping("/account")
    @ApiOperation(value = "用户登录", notes = "只需要传用户帐号loginid,密码pwd")
    public AbstractBaseResult accountLogin(@RequestBody AccountAuthDTO accountAuthDTO) {
        try {
            if(accountAuthDTO.getLoginId().length()<1){
                // 没有手机号
                return RespEnum.CHECK_FAILED.result("手机号不能为空");
            }
            TokenVO tokenVO = accountService.accountLogin(accountAuthDTO);
            if("错误信息".equals(tokenVO.getUserKey())){
                // 代表错误信息
                return  RespEnum.CHECK_FAILED.result(tokenVO.getToken());
            }else{
                return RespEnum.OK.result(tokenVO);
            }
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙");
        }
    }

    /**
     * 刷新令牌
     *
     * @return
     */
    @PostMapping("refresh")
    @ApiOperation(value = "刷新令牌", notes = "不需要传参数")
    public AbstractBaseResult<TokenVO> refreshToken() {
        return RespEnum.OK.result(accountService.refresh());
    }

    /**
     * 登出系统
     *
     * @return
     */
    @GetMapping("exit")
    @ApiOperation(value = "用户退出", notes = "不需要传参数")
    public AbstractBaseResult<TokenVO> exit() {
        return RespEnum.OK.result(accountService.exit());
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("userInfo")
    @ApiOperation(value = "获取用户信息", notes = "不需要传参数")
    public AbstractBaseResult<SysUserVo> userInfo() {
        return RespEnum.OK.result(accountService.userInfo());
    }
}
