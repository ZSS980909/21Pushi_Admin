package com.ershiyi.mapper;

import com.ershiyi.domain.Student_User;
import com.ershiyi.domain.SysRole;
import com.ershiyi.domain.SysUser;
import com.ershiyi.vo.SysMenuVo;
import com.ershiyi.vo.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.AbstractMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   数据层
 *
 * @author liy
 * @date 2020-06-06
 */
public interface SysUserMapper extends AbstractMapper<SysUser>
{

	/**
	 * 根据用户id获取所有菜单
	 */
	public List<SysMenuVo> getMenuByUser(@Param("userid") String userid);


	/**
	 * 登录查询用户信息
	 * @param sysUser
	 * @return
     */
	public List<SysUser> getUserMsgForLogin(SysUser sysUser);



	/**
	 * 登录2查询用户信息
	 * @param sysUser
	 * @return
	 */
	public SysUser getUserMsgForLogin2(SysUser sysUser);


	/**
     * 查询SysUser信息
     *
     * @param userId SYS_USERID
     * @return SYS_USER信息
     */
	public SysUser selectSysUserById(String userId);

	/**
     * 查询SysUser列表
     *
     * @param sysUser SYS_USER信息
     * @return SYS_USER集合
     */
	public List<SysUser> selectSysUserList(SysUser sysUser);

	/**
     * 新增SysUser
     *
     * @param sysUser SYS_USER信息
     * @return 结果
     */
	public int insertSysUser(SysUser sysUser);

	/**
     * 修改SysUser
     *
     * @param sysUser SYS_USER信息
     * @return 结果
     */
	public int updateSysUser(SysUser sysUser);

	/**
     * 删除SysUser
     *
     * @param userid SYS_USERID
     * @return 结果
     */
	public int deleteSysUserById(String userid);

	/**
     * 批量删除SysUser
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteSysUserByIds(String[] userIds);

	/**
	 *获取当前单位所有角色
	 */
	public List<SysRole> getRoles(SysRole sysRole);

	/**
	 * 获取用户对应的角色list<String>
	 */
	public List<String> getRolesByUserId(SysUser sysUser);


	/**
	 * 保存用户角色信息
	 * @param userRoles
     */
	public void saveUserRole(@Param("userRoles") List<HashMap> userRoles);
	/**
	 * 删除用户角色信息
	 */
	public void delUserRole(SysUserRole sysUserRole);


	/**
	 * 查询用户对应的桌面菜单
	 * @param userid
	 * @return
	 */
	public List<SysMenuVo> getDeskMenuByUser(@Param("userid") String userid);

	/**
	 * 删除用户对应的桌面菜单
	 * @param userid
	 * @return
	 */
	public int delDeskMenu(@Param("userid") String userid);

	/**
	 * 保存用户桌面菜单
	 * @param deskMenu
	 * @return
	 */
	public int saveDeskMenu(@Param("deskMenu") List<HashMap> deskMenu);

	/**
	 *
	 * @param guid
	 * @return
	 */
	@Select("select studenterId,schoolid from common_student_user where  studentuserid = #{guid} and deleted = 0 ")
	Student_User findByStudenterId(@Param("guid")String guid);

	/**
	 * 根据id查询账户
	 * @param loginId
	 * @return
	 */
	public SysUser selectSysUserByLoginId(String loginId);

	/**
	 * 添加登陆日志
	 * @param map)
	 * @return
	 */
	Integer InsertLogsByUser(Map map);

    Integer Logs(@Param("loginId")String loginId,@Param("uniquecode")String uniquecode,@Param("logincode")String logincode);

	Integer Logsbyfather(@Param("loginId")String loginId,@Param("uniquecode")String uniquecode,@Param("logincode")String logincode);

	Integer UpdateCode(@Param("loginId")String loginId, @Param("uniquecode")String uniquecode);

	/**
	 * 获取家长编号
	 * @param guid
	 * @return
	 */
	@Select("select parenterId as studenterId,parentUserId as studentUserId from common_parent_user where parentUserId = #{guid}")
	Student_User findByParent(@Param("guid") String guid);

	Integer UpdateCodebyfather(@Param("loginId")String loginId, @Param("uniquecode")String uniquecode);


}
