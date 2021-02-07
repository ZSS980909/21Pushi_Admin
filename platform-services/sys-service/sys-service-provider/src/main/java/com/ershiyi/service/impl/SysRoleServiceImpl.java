package com.ershiyi.service.impl;

import com.ershiyi.domain.SysRole;
import com.ershiyi.mapper.SysRoleMapper;
import com.ershiyi.service.SysRoleService;
import com.ershiyi.util.RandomGUID;
import com.ershiyi.vo.SysMenuVo;
import com.ershiyi.vo.SysRoleMenu;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * SysRole 服务层实现
 *
 * @author zaz
 * @date 2020-03-06
 */
@Service
@Slf4j
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleMapper> implements SysRoleService {


    /**
     * 查询SysRole信息
     *
     * @param guid SYS_ROLEID
     * @return SysRole信息
     */
    @Override
    public SysRole selectSysRoleById(String guid) {
        return mapper.selectSysRoleById(guid);
    }

    /**
     * 查询SysRole列表
     *
     * @param sysRole SYS_ROLE信息
     * @return SysRole集合
     */
    @Override
    public PageInfo<SysRole> selectSysRoleList(SysRole sysRole, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<SysRole> lists = mapper.selectSysRoleList(sysRole);
        return new PageInfo<SysRole>(lists);
    }

    /**
     * 新增SysRole
     *
     * @param sysRole SYS_ROLE信息
     * @return 结果
     */
    @Override
    public int insertSysRole(SysRole sysRole) {
        if (sysRole.getGuid() == null) {
            sysRole.setGuid(new RandomGUID().toString());
        }

        return mapper.insertSysRole(sysRole);
    }

    /**
     * 修改SysRole
     *
     * @param sysRole SYS_ROLE信息
     * @return 结果
     */
    @Override
    public int updateSysRole(SysRole sysRole) {
        return mapper.updateSysRole(sysRole);
    }

    /**
     * 删除SysRole对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysRoleByIds(String ids) {
        if (ids != null && !("".equals(ids))) {
            String[] idss = ids.split(",");
            return mapper.deleteSysRoleByIds(idss);
        } else {
            return 0;
        }


    }

    @Override
    public List<SysMenuVo> getAllMenuByUnitId(SysRole sysRole) {
        return mapper.getAllMenuByUnitId(sysRole);
    }

    //
    @Override
    public List<SysMenuVo> getAllMenuByUnitId_cj(SysRole sysRole) {
        List<SysMenuVo> rootMenu = mapper.getAllMenuByUnitId(sysRole);
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
    public List<String> getMenusByRoleGuid(SysRole sysRole) {
        return mapper.getMenusByRole(sysRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveRoleMenu(SysRoleMenu sysUserRole) {
        mapper.delRoleMenu(sysUserRole);
        List roleMenus = new ArrayList<>();
        if (sysUserRole.getMenuguids().length > 0) {
            for (String s : sysUserRole.getMenuguids()) {
                HashMap mm = new HashMap();
                mm.put("roleguid", sysUserRole.getRoleguid());
                mm.put("menuguid", s);
                roleMenus.add(mm);
            }
            mapper.saveRoleMenu(roleMenus);
        }

        return 1;
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
}
