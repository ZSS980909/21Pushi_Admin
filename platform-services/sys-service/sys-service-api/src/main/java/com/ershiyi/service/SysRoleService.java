package com.ershiyi.service;

import com.ershiyi.domain.SysRole;
import com.ershiyi.vo.SysMenuVo;
import com.ershiyi.vo.SysRoleMenu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * SysRole 服务层
 *
 * @author zaz
 * @date 2020-03-06
 */
public interface SysRoleService extends BaseService<SysRole> {
    /**
     * 查询SysRole信息
     *
     * @param guid SYS_ROLEID
     * @return SYS_ROLE信息
     */
    public SysRole selectSysRoleById(String guid);

    /**
     * 查询SysRole列表
     *
     * @param sysRole SYS_ROLE信息
     * @return SYS_ROLE集合
     */
    public PageInfo<SysRole> selectSysRoleList(SysRole sysRole, Integer pageNumber, Integer pageSize);

    /**
     * 新增SysRole
     *
     * @param sysRole SYS_ROLE信息
     * @return 结果
     */
    public int insertSysRole(SysRole sysRole);

    /**
     * 修改SysRole
     *
     * @param sysRole SYS_ROLE信息
     * @return 结果
     */
    public int updateSysRole(SysRole sysRole);

    /**
     * 删除SysRole信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysRoleByIds(String ids);


    /**
     * 获取当前单位所有的菜单
     *
     * @param sysRole
     * @return
     */
    public List<SysMenuVo> getAllMenuByUnitId(SysRole sysRole);


    /**
     * 获取当前单位所有的菜单 -层级
     *
     * @param sysRole
     * @return
     */
    public List<SysMenuVo> getAllMenuByUnitId_cj(SysRole sysRole);


    /**
     * 获取角色对应菜单list<String>
     */
    public List<String> getMenusByRoleGuid(SysRole sysRole);


    /**
     * 保存角色菜单
     */
    public int saveRoleMenu(SysRoleMenu sysRoleMenu);


}
