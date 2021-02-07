package com.ershiyi.service;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.domain.SysMenu;
import com.ershiyi.vo.SysMenuPrison;
import com.ershiyi.vo.SysMenuVo;

import java.util.List;

/**
 * SysMenu 服务层
 *
 * @author liy
 * @date 2020-06-06
 */
public interface SysMenuService extends BaseService<SysMenu> {
    /**
     * 查询SysMenu信息
     *
     * @param guid SYS_MENUID
     * @return SYS_MENU信息
     */
    public SysMenu selectSysMenuById(String guid);

    /**
     * 查询SysMenu列表
     *
     * @param SYS_MENU信息
     * @return SYS_MENU集合
     */
    public List<SysMenuVo> selectSysMenuList();

    /**
     * 新增SysMenu
     *
     * @param sysMenu SYS_MENU信息
     * @return 结果
     */
    public int insertSysMenu(SysMenu sysMenu);

    /**
     * 修改SysMenu
     *
     * @param sysMenu SYS_MENU信息
     * @return 结果
     */
    public int updateSysMenu(SysMenu sysMenu);

    /**
     * 删除SysMenu信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysMenuByIds(String ids);


    /**
     * 获取所有菜单  用于配置所菜单
     *
     * @return
     */
    public List<SysMenuVo> allMenu();


    /**
     * 获取所有菜单  用于配置所菜单 -递归后的数据
     *
     * @return
     */
    public List<SysMenuVo> allMenu_sy();


    /**
     * 保存所菜单
     */
    public AbstractBaseResult saveMenuPrison(SysMenuPrison sysMenuPrison);

    /**
     * 获取所对应得菜单
     */
    public List<String> getMenusByPrisonid(String prisonid);


}
