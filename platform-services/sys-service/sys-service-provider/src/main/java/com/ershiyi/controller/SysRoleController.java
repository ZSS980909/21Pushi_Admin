//package com.ershiyi.controller;
//
//
//import com.ershiyi.common.dto.AbstractBaseResult;
//import com.ershiyi.common.dto.PageDTO;
//import com.ershiyi.dist.RespEnum;
//import com.ershiyi.domain.SysRole;
//import com.ershiyi.service.SysRoleService;
//import com.ershiyi.vo.SysRoleMenu;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//
//
///**
// * sysRole 信息操作处理
// *
// * @author ly
// * @date  2020-06-04
// */
//@Controller
//@Api(value = "角色管理", tags = {"角色管理"})
//public class SysRoleController {
//
//    @Autowired
//    private SysRoleService sysRoleService;
//
//
//    /**
//     * 查询 SYS_ROLE 列表
//     */
//    @GetMapping("/sysRole")
//    @ResponseBody
//    @ApiOperation(value = "查询角色列表", notes = "pageNumber pageSize prisonid 必传")
//    public AbstractBaseResult list(SysRole sysRole, PageDTO pageDTO) {
//        if (sysRole == null || StringUtils.isEmpty(sysRole.getPrisonid())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        return RespEnum.OK.result(sysRoleService.selectSysRoleList(sysRole, pageDTO.getPageNumber(), pageDTO.getPageSize()));
//    }
//
//
//    /**
//     * 新增保存 SYS_ROLE
//     */
//    @PostMapping("/sysRole")
//    @ResponseBody
//    @ApiOperation("新增保存角色信息")
//    public AbstractBaseResult addSave(@RequestBody SysRole sysRole) {
//        return RespEnum.OK.result(sysRoleService.createSelective(sysRole));
//    }
//
//
//    /**
//     * 修改 前获取   SYS_ROLE
//     */
//    @GetMapping("/sysRole/{guid}")
//    @ResponseBody
//    @ApiOperation(value = "修改前获角色信息", notes = "")
//    public AbstractBaseResult selectSysRoleById(@PathVariable String guid) {
//        SysRole sysRole1 = sysRoleService.selectSysRoleById(guid);
//        return RespEnum.OK.result(sysRole1);
//    }
//
//
//    /**
//     * 修改保存 SYS_ROLE
//     */
//    @PatchMapping("/sysRole")
//    @ResponseBody
//    @ApiOperation(value = "修改保存角色信息", notes = "guid必传")
//    public AbstractBaseResult editSave(@RequestBody SysRole sysRole) {
//        if (sysRole == null || StringUtils.isEmpty(sysRole.getGuid())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        return RespEnum.OK.result(sysRoleService.updateSelective(sysRole));
//    }
//
//
//    /**
//     * 删除角色
//     */
//    @DeleteMapping("/sysRole")
//    @ResponseBody
//    @ApiOperation(value = "删除角色", notes = "只需传guid")
//    public AbstractBaseResult delete(SysRole sysRole) {
//        if (sysRole == null || StringUtils.isEmpty(sysRole.getGuid())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        SysRole s = new SysRole();
//        s.setGuid(sysRole.getGuid());
//        s.setDeleted(BigDecimal.valueOf(1));
//        return RespEnum.OK.result(sysRoleService.updateSysRole(s));
//    }
//
//
//    /**
//     * 删除 SYS_ROLE
//     */
///*	@PostMapping( "/remove")
//	@ResponseBody
//    @ApiOperation("删除 SYS_ROLE")
//	public RespVo remove(String ids)
//	{
//		return RespHandler.getSuccessInfo(sysRoleService.deleteSysRoleByIds(ids));
//	}*/
//
//
//    /**
//     * 获取当前单位所有菜单
//     *
//     * @param sysRole
//     * @return
//     */
//    @GetMapping("/getAllMenuByUnitId")
//    @ResponseBody
//    @ApiOperation(value = "获取当前单位所有菜单", notes = "只需传prisonid")
//    public AbstractBaseResult getAllMenuByUnitId(SysRole sysRole) {
//        if (sysRole == null || StringUtils.isEmpty(sysRole.getPrisonid())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        return RespEnum.OK.result(sysRoleService.getAllMenuByUnitId(sysRole));
//    }
//    //
//
//    /**
//     * 获取当前单位所有菜单 --递归后数据   有层级
//     *
//     * @param sysRole
//     * @return
//     */
//    @GetMapping("/getAllMenuByUnitId_cj")
//    @ResponseBody
//    @ApiOperation(value = "获取当前单位所有菜单  --递归后数据   有层级", notes = "只需传prisonid")
//    public AbstractBaseResult getAllMenuByUnitId_cj(SysRole sysRole) {
//        if (sysRole == null || StringUtils.isEmpty(sysRole.getPrisonid())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        return RespEnum.OK.result(sysRoleService.getAllMenuByUnitId_cj(sysRole));
//    }
//
//
//    /**
//     * 获取角色对应菜单list<String>
//     */
//    @PostMapping("/getMenusByRoleGuids")
//    @ResponseBody
//    @ApiOperation(value = "获取角色对应菜单list<String>", notes = "只需传角色的guid")
//    public AbstractBaseResult getMenusByRoleGuids(@RequestBody SysRole sysRole) {
//        if (sysRole == null || StringUtils.isEmpty(sysRole.getGuid())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        return RespEnum.OK.result(sysRoleService.getMenusByRoleGuid(sysRole));
//    }
//
//
//    /**
//     * 保存角色菜单
//     */
//    @PostMapping("/saveRoleMenu")
//    @ResponseBody
//    @ApiOperation("保存角色菜单")
//    public AbstractBaseResult saveRoleMenu(@RequestBody SysRoleMenu sysRoleMenu) {
//        if (sysRoleMenu == null || StringUtils.isEmpty(sysRoleMenu.getRoleguid())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        return RespEnum.OK.result(sysRoleService.saveRoleMenu(sysRoleMenu));
//    }
//
//
//}
