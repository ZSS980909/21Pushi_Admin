package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.dto.SysMenuDeskDTO;
import com.ershiyi.feign.SysFeign;
import com.ershiyi.service.SysUserService;
import com.ershiyi.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import com.ershiyi.domain.SysMenuDeskDTO;
//import com.ershiyi.feign.SysFeign;

@RestController
@RequestMapping("/system")
@Api(value = "系统管理", tags = {"系统管理"})
public class SysController implements SysFeign {

    @Autowired
    private SysUserService userService;

    /**
     * 获取用户对应的菜单
     */
    @GetMapping("/getMenuByUser")
    @ResponseBody
    @ApiOperation(value = "获取用户对应的菜单", notes = "")
    public AbstractBaseResult getMenuByUser() {
        String userKey = TokenUtils.getUserKey();
        return RespEnum.OK.result(userService.getMenuByUser(userKey));
    }


    @GetMapping("/deskMenu")
    @ResponseBody
    @ApiOperation(value = "获取用户桌面对应的菜单", notes = "")
    public AbstractBaseResult getDeskMenuByUser() {
        String userKey = TokenUtils.getUserKey();
        return RespEnum.OK.result(userService.getDeskMenuByUser(userKey));
    }

    @PostMapping("/deskMenu")
    @ResponseBody
    @ApiOperation(value = "保存用户桌面菜单", notes = "")
    public AbstractBaseResult saveDeskMenu(@RequestBody SysMenuDeskDTO sysMenuDeskDTO) {
        String userKey = TokenUtils.getUserKey();
        sysMenuDeskDTO.setUserid(userKey);
        return RespEnum.OK.result(userService.SaveDeskMenu(sysMenuDeskDTO));
    }
}