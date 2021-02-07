package com.ershiyi.mapper;

import com.ershiyi.domain.SysMenu;
import com.ershiyi.vo.SysMenuVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.AbstractMapper;

import java.util.List;

/**
 *   数据层
 * 
 * @author liy
 * @date 2020-06-06
 */
public interface SysMenuMapper extends AbstractMapper<SysMenu>
{
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
     * @param sysMenu SYS_MENU信息
     9* @return SYS_MENU集合
     */
	public List<SysMenu> selectSysMenuList(SysMenu sysMenu);
	
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
     * 删除SysMenu
     * 
     * @param guid SYS_MENUID
     * @return 结果
     */
	public int deleteSysMenuById(String guid);
	
	/**
     * 批量删除SysMenu
     * 
     * @param guids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSysMenuByIds(String[] guids);


	public List<SysMenuVo> allMenu();


	public List<SysMenuVo> allMenuList();



	public int  deleteMenuPrison(@Param("prisonid") String prisonid);

	public int insertMenuPrison(@Param("menuprison") List menuprison);

	public List<String> getMenusByPrisonid(@Param("prisonid") String prisonid);




	
}