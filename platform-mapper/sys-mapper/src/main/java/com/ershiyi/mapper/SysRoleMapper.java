package com.ershiyi.mapper;

import com.ershiyi.domain.SysRole;
import com.ershiyi.vo.SysMenuVo;
import com.ershiyi.vo.SysRoleMenu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.AbstractMapper;

import java.util.List;

/**
 *   数据层
 *
 * @author liy
 * @date 2020-06-06
 */
public interface SysRoleMapper  extends AbstractMapper<SysRole>
{
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
	public List<SysRole> selectSysRoleList(SysRole sysRole);
	
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
     * 删除SysRole
     * 
     * @param guid SYS_ROLEID
     * @return 结果
     */
	public int deleteSysRoleById(String guid);
	
	/**
     * 批量删除SysRole
     * 
     * @param guids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSysRoleByIds(String[] guids);







	/**
	 *获取当前单位所有菜单
	 */
	public List<SysMenuVo> getAllMenuByUnitId(SysRole sysRole);

	/**
	 * 获取角色对应菜单list<String>
	 */
	public List<String> getMenusByRole(SysRole sysRole);


	/**
	 * 保存角色菜单信息
	 * @param roleMenus
	 */
	public void saveRoleMenu(@Param("roleMenus") List roleMenus);
	/**
	 * 删除角色菜单信息
	 */
	public void delRoleMenu(SysRoleMenu sysRoleMenu);
	
}