package com.ershiyi.controller;


import cn.hutool.crypto.SecureUtil;
import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.common.dto.PageDTO;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.SysRole;
import com.ershiyi.domain.SysUser;
import com.ershiyi.service.SysUserService;
import com.ershiyi.utils.TokenUtils;
import com.ershiyi.vo.SysUserRole;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


/**
 * sysUser 信息操作处理
 *
 * @author ly
 * @date 2020-06-04
 */
@Controller
@RequestMapping("/sysUser")
@Api(value = "用户管理", tags = {"用户管理"})
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    /**
     * 查询 SYS_USER 列表
     */
//    @GetMapping("/list")
//    @ResponseBody
//    @ApiOperation(value = "分页查询用户列表", notes = "pageNumber pageSize prisonid 必传")
//    public AbstractBaseResult<PageInfo<SysUser>> list(SysUser sysUser, PageDTO pageDto) {
////		if(sysUser==null||StringUtils.isEmpty(sysUser.getPrisonid())){
////			return RespEnum.ERROR.result(null);
////		}
//        return RespEnum.OK.result(sysUserService.selectSysUserList(sysUser, pageDto.getPageNumber(), pageDto.getPageSize()));
//    }


    /**
     * 新增保存 SYS_USER
     */
/*	@PostMapping("/add")
	@ResponseBody
    @ApiOperation("新增保存 SYS_USER")
	public RespVo addSave(@RequestBody SysUser sysUser)
	{
		return RespHandler.getSuccessInfo(sysUserService.insertSysUser(sysUser));
	}*/


    /**
     * 修改 前获取   SYS_USER
     */
    @GetMapping("/selectSysUserById")
    @ResponseBody
    @ApiOperation(value = "查看用户信息", notes = "只需传userid")
    public AbstractBaseResult selectSysUserById(SysUser sysUser) {
        SysUser sysUser1 = sysUserService.selectSysUserById(sysUser.getUserTypeId().toString());
        return RespEnum.OK.result(sysUser1);
    }


    /**
     * 修改保存 SYS_USER
     */
/*	@PostMapping("/edit")
	@ResponseBody
    @ApiOperation("修改保存 SYS_USER")
	public RespVo editSave(@RequestBody SysUser sysUser)
	{
		return RespHandler.getSuccessInfo(sysUserService.updateSysUser(sysUser));
	}*/

    /**
     * 启用用户
     *
     * @param sysUser
     * @return
     */
//    @PostMapping("/qy")
//    @ResponseBody
//    @ApiOperation(value = "启用用户", notes = "只需传userid")
//    public AbstractBaseResult qy(@RequestBody SysUser sysUser) {
//        if (sysUser == null || StringUtils.isEmpty(sysUser.getUsertypeid().toString())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        SysUser s = new SysUser();
//        s.setUsertypeid(sysUser.getUsertypeid());
//        s.setIfuse(1);
//        return RespEnum.OK.result(sysUserService.updateSysUser(s));
//    }
//
//    /**
//     * 停用用户
//     *
//     * @param sysUser
//     * @return
//     */
//    @PostMapping("/ty")
//    @ResponseBody
//    @ApiOperation(value = "停用用户", notes = "只需传userid")
//    public AbstractBaseResult ty(@RequestBody SysUser sysUser) {
//        if (sysUser == null || StringUtils.isEmpty(sysUser.getUsertypeid().toString())) {
//            return RespEnum.ERROR.result(null);
//        }
//        SysUser s = new SysUser();
//        s.setUsertypeid(sysUser.getUsertypeid());
//        s.setIfuse(0);
//        return RespEnum.OK.result(sysUserService.updateSysUser(s));
//    }
//
//    /**
//     * 修改用户密码
//     *
//     * @param sysUser
//     * @return
//     */
//    @PostMapping("/updatePwd")
//    @ResponseBody
//    @ApiOperation(value = "修改用户密码", notes = "只需传userid pwd")
//    public AbstractBaseResult updatePwd(@RequestBody SysUser sysUser) {
//        String userKey = TokenUtils.getUserKey();
////        if (sysUser.getUsertypeid().toString() == null) {
////            sysUser.setUsertypeid(userKey);
////        }
//        if (sysUser == null || StringUtils.isEmpty(sysUser.getUsertypeid().toString()) || StringUtils.isEmpty(sysUser.getPwd())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        SysUser s = new SysUser();
//        s.setUsertypeid(sysUser.getUsertypeid());
//        String pwd = SecureUtil.md5(sysUser.getPwd());
//        s.setPwd(pwd);
//        return RespEnum.OK.result(sysUserService.updateSysUser(s));
//    }
//
//
//    /**
//     * 删除用户
//     *
//     * @param sysUser
//     * @return
//     */
//    @PostMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value = "删除用户", notes = "只需传userid")
//    public AbstractBaseResult delete(@RequestBody SysUser sysUser) {
//        if (sysUser == null || StringUtils.isEmpty(sysUser.getUsertypeid().toString())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        SysUser s = new SysUser();
//        s.setUsertypeid(sysUser.getUsertypeid());
//        //s.setDeleted(BigDecimal.ONE);
//        return RespEnum.OK.result(sysUserService.updateSysUser(s));
//    }
//
//
//    /**
//     * 获取当前单位所有角色
//     *
//     * @param sysRole
//     * @return
//     */
//    @GetMapping("/getRoles")
//    @ResponseBody
//    @ApiOperation(value = "获取当前单位所有角色", notes = "prisonid")
//    public AbstractBaseResult getRoles(SysRole sysRole) {
//        if (sysRole == null || StringUtils.isEmpty(sysRole.getPrisonid())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        return RespEnum.OK.result(sysUserService.getRoles(sysRole));
//    }
//
//    /**
//     * 获取用户对应的角色list<String>
//     */
//    @GetMapping("/getRolesByUserId")
//    @ResponseBody
//    @ApiOperation(value = "获取用户对应的角色list<String>", notes = "userid")
//    public AbstractBaseResult getRolesByUserId(SysUser sysUser) {
//        if (sysUser == null || StringUtils.isEmpty(sysUser.getUsertypeid().toString())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        return RespEnum.OK.result(sysUserService.getRolesByUserId(sysUser));
//    }
//
//
//    /**
//     * 保存用户角色
//     */
//    @PostMapping("/saveUserRole")
//    @ResponseBody
//    @ApiOperation("保存用户角色")
//    public AbstractBaseResult saveUserRole(@RequestBody SysUserRole sysUserRole) {
//        if (sysUserRole == null || StringUtils.isEmpty(sysUserRole.getUserid().toString())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        return RespEnum.OK.result(sysUserService.saveUserRole(sysUserRole));
//    }


    /**
     * 删除 SYS_USER
     */
/*	@PostMapping( "/remove")
	@ResponseBody
    @ApiOperation("删除用户")
	public RespVo remove(String ids)
	{
		return RespHandler.getSuccessInfo(sysUserService.deleteSysUserByIds(ids));
	}*/


}
