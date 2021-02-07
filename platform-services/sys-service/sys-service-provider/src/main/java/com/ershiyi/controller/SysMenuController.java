//package com.ershiyi.controller;
//
//
//import com.ershiyi.common.dto.AbstractBaseResult;
//import com.ershiyi.dist.RespEnum;
//import com.ershiyi.domain.SysMenu;
//import com.ershiyi.domain.SysRole;
//import com.ershiyi.dto.SysMenuDTO;
//import com.ershiyi.mapstruct.MenuMapping;
//import com.ershiyi.service.SysMenuService;
//import com.ershiyi.vo.SysMenuPrison;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
///**
// * sysMenu 信息操作处理
// *
// * @author liy
// * @date  2020-06-04
// */
//@Controller
//@Api(value = "菜单管理", tags = {"菜单管理"})
//public class SysMenuController {
//
//    @Autowired
//    private SysMenuService sysMenuService;
//
//    /**
//     * 新增保存 SYS_MENU
//     */
//    @PostMapping("/sysMenu")
//    @ResponseBody
//    @ApiOperation("新增菜单信息")
//    public AbstractBaseResult addSave(@RequestBody SysMenuDTO sysMenuDTO) {
//        return RespEnum.OK.result(sysMenuService.createSelective(MenuMapping.MAPPER.toSysMenuList(sysMenuDTO)));
//    }
///*	@PostMapping("/ceshi")
//	@ResponseBody
//	@ApiOperation("ceshi")
//	public AbstractBaseResult ceshi(@RequestBody SysMenuDTO sysMenuDTO)
//	{
//		return RespEnum.OK.result(sysMenuService.createSelective(MenuMapping.MAPPER.toSysMenuList(sysMenuDTO)));
//	}*/
//
//    /**
//     * 查询 SYS_MENU 列表
//     */
//    @GetMapping("/sysMenu")
//    @ResponseBody
//    @ApiOperation(value = "查询菜单 ", notes = "未递归数据 （不包括未启用的菜单数据）")
//    public AbstractBaseResult list(SysMenu sysMenu) {
//        return RespEnum.OK.result(sysMenuService.selectSysMenuList());
//    }
//
//    /**
//     * 修改 前获取   SYS_MENU
//     */
//    @GetMapping("/sysMenu/{guid}")
//    @ResponseBody
//    @ApiOperation(value = "修改 前获取菜单信息", notes = "只需传guid")
//    public AbstractBaseResult selectSysMenuById(@PathVariable String guid) {
//        SysMenu sysMenu1 = sysMenuService.selectSysMenuById(guid);
//        return RespEnum.OK.result(sysMenu1);
//    }
//
//
//    /**
//     * 修改保存 SYS_MENU
//     */
//    @PatchMapping("/sysMenu")
//    @ResponseBody
//    @ApiOperation(value = "修改菜单信息", notes = "guid必传")
//    public AbstractBaseResult update(@RequestBody SysMenuDTO sysMenuDTO) {
//        return RespEnum.OK.result(sysMenuService.updateSelective(MenuMapping.MAPPER.toSysMenuList(sysMenuDTO)));
//    }
//
//    /**
//     * 删除菜单 软删
//     */
//    @DeleteMapping("/sysMenu")
//    @ResponseBody
//    @ApiOperation(value = "删除菜单", notes = "")
//    public AbstractBaseResult delete(String ids) {
//        return RespEnum.OK.result(sysMenuService.deleteLogic(ids.split(",")));
//    }
//
//
//    /**
//     * 查询 SYS_MENU 列表
//     */
//    @GetMapping("/sysMenu/allMenuList")
//    @ResponseBody
//    @ApiOperation(value = "获取所有菜单  菜单维护页面使用  递归后数据  有层级（包括未启用的菜单数据）  ", notes = "")
//    public AbstractBaseResult allMenu() {
//        return RespEnum.OK.result(sysMenuService.allMenu());
//    }
//
//
//    /**
//     * 查询 SYS_MENU 列表
//     */
//    @GetMapping("/sysMenu/allMenu_sy")
//    @ResponseBody
//    @ApiOperation(value = "获取所有菜单 用于配置所菜单 递归后数据  有层级（不包括未启用的菜单数据）", notes = "")
//    public AbstractBaseResult allMenu_sy() {
//        return RespEnum.OK.result(sysMenuService.allMenu_sy());
//    }
//
//
//    /**
//     * 保存所菜单
//     */
//    @PostMapping("/sysMenu/saveMenuPrison")
//    @ResponseBody
//    @ApiOperation("保存所菜单")
//    public AbstractBaseResult saveMenuPrison(@RequestBody SysMenuPrison sysMenuPrison) {
//        if (sysMenuPrison == null || StringUtils.isEmpty(sysMenuPrison.getPrisonid())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        return sysMenuService.saveMenuPrison(sysMenuPrison);
//    }
//
//
//    /**
//     * 获取当前单位所有菜单数组
//     *
//     * @param sysRole
//     * @return
//     */
//    @GetMapping("/getMenusByPrisonid")
//    @ResponseBody
//    @ApiOperation(value = "获取当前单位所有菜单数组", notes = "只需传prisonid")
//    public AbstractBaseResult getMenusByPrisonid(SysRole sysRole) {
//        if (sysRole == null || StringUtils.isEmpty(sysRole.getPrisonid())) {
//            throw RespEnum.ERROR.throwException("参数为空");
//        }
//        return RespEnum.OK.result(sysMenuService.getMenusByPrisonid(sysRole.getPrisonid()));
//    }
//
//
//    /**
//     * 删除 SYS_MENU
//     */
//	/*@PostMapping( "/remove")
//	@ResponseBody
//    @ApiOperation("删除 SYS_MENU")
//	public RespVo remove(String ids)
//	{
//		return RespHandler.getSuccessInfo(sysMenuService.deleteSysMenuByIds(ids));
//	}*/
//
//
//}
