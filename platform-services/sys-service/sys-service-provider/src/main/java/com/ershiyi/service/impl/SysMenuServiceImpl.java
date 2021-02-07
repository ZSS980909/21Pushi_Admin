package com.ershiyi.service.impl;


import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.SysMenu;
import com.ershiyi.mapper.SysMenuMapper;
import com.ershiyi.service.SysMenuService;
import com.ershiyi.util.RandomGUID;
import com.ershiyi.vo.SysMenuPrison;
import com.ershiyi.vo.SysMenuVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * SysMenu 服务层实现
 *
 * @author liy
 * @date 2020-06-09 00:00:00
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, SysMenuMapper> implements SysMenuService {

    /**
     * 查询SysMenu信息
     *
     * @param guid SYS_MENUID
     * @return SysMenu信息
     */
    @Override
    public SysMenu selectSysMenuById(String guid) {
        return mapper.selectSysMenuById(guid);
    }

    /**
     * 查询SysMenu列表
     *
     * @param
     * @return SysMenu集合
     */
    @Override
    public List<SysMenuVo> selectSysMenuList() {
        return mapper.allMenu();
    }

    /**
     * 新增SysMenu
     * 弃用
     *
     * @param sysMenu SYS_MENU信息
     * @return 结果
     */
    @Override
    public int insertSysMenu(SysMenu sysMenu) {
        if (sysMenu.getGuid() == null) {
            sysMenu.setGuid(new RandomGUID().toString());
        }
        return mapper.insertSysMenu(sysMenu);
    }

    /**
     * 修改SysMenu
     *
     * @param sysMenu SYS_MENU信息
     * @return 结果
     */
    @Override
    public int updateSysMenu(SysMenu sysMenu) {
        return mapper.updateSysMenu(sysMenu);
    }

    /**
     * 删除SysMenu对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysMenuByIds(String ids) {
        if (ids != null && !("".equals(ids))) {
            String[] idss = ids.split(",");
            return mapper.deleteSysMenuByIds(idss);
        } else {
            return 0;
        }


    }

    @Override
    public List<SysMenuVo> allMenu() {
        List<SysMenuVo> rootMenu = mapper.allMenuList();
        // 最后的结果
        List<SysMenuVo> menuList = new ArrayList<SysMenuVo>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootMenu.size(); i++) {
            // 一级菜单没有parentId
            if ("-1".equals(rootMenu.get(i).getMenupid())) {
                menuList.add(rootMenu.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (SysMenuVo menu : menuList) {
            menu.setChildMenus(getChild(menu.getGuid(), rootMenu));
        }
        return menuList;
    }

    @Override
    public List<SysMenuVo> allMenu_sy() {
        List<SysMenuVo> rootMenu = mapper.allMenu();
        // 最后的结果
        List<SysMenuVo> menuList = new ArrayList<SysMenuVo>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootMenu.size(); i++) {
            // 一级菜单没有parentId
            if ("-1".equals(rootMenu.get(i).getMenupid())) {
                menuList.add(rootMenu.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (SysMenuVo menu : menuList) {
            menu.setChildMenus(getChild(menu.getGuid(), rootMenu));
        }
        return menuList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AbstractBaseResult saveMenuPrison(SysMenuPrison sysMenuPrison) {
        mapper.deleteMenuPrison(sysMenuPrison.getPrisonid());
        List MenusPrison = new ArrayList<>();
        if (sysMenuPrison.getMenuguids().length > 0) {
            for (String s : sysMenuPrison.getMenuguids()) {
                HashMap mm = new HashMap();
                mm.put("prisonid", sysMenuPrison.getPrisonid());
                mm.put("menuguid", s);
                MenusPrison.add(mm);
            }
            mapper.insertMenuPrison(MenusPrison);
        }

        return RespEnum.OK.result(null);
    }


    /**
     * 递归查找子菜单
     *
     * @param id       当前菜单id
     * @param rootMenu 要查找的列表
     * @return
     */
    private List<SysMenuVo> getChild(String id, List<SysMenuVo> rootMenu) {
        // 子菜单
        List<SysMenuVo> childList = new ArrayList<>();
        for (SysMenuVo menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (StringUtils.isNotBlank(menu.getMenupid())) {
                if (menu.getMenupid().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (SysMenuVo menu : childList) {// 没有url子菜单还有子菜单
            // 递归
            menu.setChildMenus(getChild(menu.getGuid(), rootMenu));
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    @Override
    public List<String> getMenusByPrisonid(String prisonid) {
        return mapper.getMenusByPrisonid(prisonid);
    }
}
