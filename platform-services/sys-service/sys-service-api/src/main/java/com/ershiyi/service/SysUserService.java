package com.ershiyi.service;

import com.ershiyi.domain.Student_User;
import com.ershiyi.domain.SysRole;
import com.ershiyi.domain.SysUser;
import com.ershiyi.dto.SysMenuDeskDTO;
import com.ershiyi.vo.SysMenuVo;
import com.ershiyi.vo.SysUserRole;
import com.ershiyi.vo.SysUserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * SysUser 服务层
 *
 * @author LIY
 * @date 2020-05-30
 */
public interface SysUserService extends BaseService<SysUser> {
    /**
     * 获取菜单根据userid
     */
    public List<SysMenuVo> getMenuByUser(String userid);

    /**
     * 获取用户的桌面菜单
     */
    public List<SysMenuVo> getDeskMenuByUser(String userid);

    /**
     * 保存用户的桌面菜单
     */
    public int SaveDeskMenu(SysMenuDeskDTO sysMenuDeskDTO);

    /**
     * 登录查询用户信息
     *
     * @param sysUser
     * @return
     */
    List<SysUser> getUserMsgForLogin(SysUser sysUser);

    /**
     * 登录2查询用户信息
     *
     * @param sysUser
     * @return
     */
    SysUser getUserMsgForLogin2(SysUser sysUser);

    /**
     * 查询SysUser信息
     *
     * @param userId SYS_USERID
     * @return SYS_USER信息
     */
    SysUser selectSysUserById(String userId);

    /**
     * 根据登录id查询用户信息
     *
     * @param loginId
     * @return SYS_USER信息
     */
     SysUser selectSysUserByLoginId(String loginId);

    /**
     * 查询SysUser列表
     *
     * @param sysUser SYS_USER信息
     * @return SYS_USER集合
     */
    PageInfo<SysUser> selectSysUserList(SysUser sysUser, Integer pageNumber, Integer pageSize);

    /**
     * 新增SysUser
     *
     * @param sysUser SYS_USER信息
     * @return 结果
     */
    int insertSysUser(SysUser sysUser);

    /**
     * 修改SysUser
     *
     * @param sysUser SYS_USER信息
     * @return 结果
     */
    int updateSysUser(SysUser sysUser);

    /**
     * 删除SysUser信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSysUserByIds(String ids);

    /**
     * 获取当前单位所有的角色
     *
     * @param sysRole
     * @return
     */
    List<SysRole> getRoles(SysRole sysRole);

    /**
     * 获取用户对应的角色list<String>
     */
    List<String> getRolesByUserId(SysUser sysUser);

    /**
     * 保存用户角色
     */
    int saveUserRole(SysUserRole sysUserRole);


    SysUserVo findUserMsgById(String id);


    Integer Logs(String loginId,String uniquecode,String logincode,int usertypeid,int logintype);

    Student_User getUserKey(String guid,int type);

//    Integer InsertLogsByUser(Map map);
}
