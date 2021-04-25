package com.ershiyi.service.impl;


import com.ershiyi.domain.Student_User;
import com.ershiyi.domain.SysRole;
import com.ershiyi.domain.SysUser;
import com.ershiyi.domain.entity.StudentInformation;
import com.ershiyi.dto.SysMenuDeskDTO;
import com.ershiyi.mapper.SysUserMapper;
import com.ershiyi.service.SysUserService;
import com.ershiyi.util.RandomGUID;
import com.ershiyi.vo.SysMenuVo;
import com.ershiyi.vo.SysUserRole;
import com.ershiyi.vo.SysUserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * SysUser 服务层实现
 *
 * @author liy
 * @date 2020-06-06
 */
@Slf4j
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserMapper> implements SysUserService {


    @Override
    public List<SysMenuVo> getMenuByUser(String userid) {
        List<SysMenuVo> rootMenu = mapper.getMenuByUser(userid);
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



    /**
     * 登录查询用户信息
     *
     * @param sysUser
     * @return
     */
    @Override
    public List<SysUser> getUserMsgForLogin(SysUser sysUser) {
        return mapper.getUserMsgForLogin(sysUser);
    }

    @Override
    public SysUser getUserMsgForLogin2(SysUser sysUser) {
        return mapper.getUserMsgForLogin2(sysUser);
    }

    /**
     * 查询SysUser信息
     *
     * @param userId SYS_USERID
     * @return SysUser信息
     */
    @Override
    public SysUser selectSysUserById(String userId) {
        return mapper.selectSysUserById(userId);
    }

    /**
     * 根据登录id查询用户信息
     *
     * @param loginId
     * @return SYS_USER信息
     */
    @Override
    public SysUser selectSysUserByLoginId(String loginId) {
//        Example example = createExample();
//        //Map<String, EntityColumn> propertyMap = example.getPropertyMap();
//       // propertyMap.remove("validataCode");
//        example.createCriteria().andEqualTo("loginid", loginId);
//        return mapper.selectOneByExample(example);
        return mapper.selectSysUserByLoginId(loginId);
    }

    /**
     * 查询SysUser列表
     *
     * @param sysUser SYS_USER信息
     * @return SysUser集合
     * public AbstractBaseResult<List> findAll() {
     * RespEnum.ERROR.throwException();
     * return null;
     * }
     */
    @Override
    public PageInfo<SysUser> selectSysUserList(SysUser sysUser, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return new PageInfo(mapper.selectSysUserList(sysUser));
    }

    /**
     * 新增SysUser   //用户
     *
     * @param sysUser SYS_USER信息
     * @return 结果
     */
    @Override
    public int insertSysUser(SysUser sysUser) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
        // sysUser.setUserid(new RandomGUID().toString());
        return mapper.insertSysUser(sysUser);
    }

    /**
     * 修改SysUser
     *
     * @param sysUser SYS_USER信息
     * @return 结果
     */
    @Override
    public int updateSysUser(SysUser sysUser) {
        return mapper.updateSysUser(sysUser);
    }

    /**
     * 删除SysUser对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysUserByIds(String ids) {
        if (ids != null && !("".equals(ids))) {
            String[] idss = ids.split(",");
            return mapper.deleteSysUserByIds(idss);
        } else {
            return 0;
        }


    }

    /**
     * 获取当前单位所有的角色
     *
     * @param sysRole
     * @return
     */
    @Override
    public List<SysRole> getRoles(SysRole sysRole) {
        return mapper.getRoles(sysRole);
    }

    /**
     * 获取用户对应的角色list<String>
     */
    @Override
    public List<String> getRolesByUserId(SysUser sysUser) {
        return mapper.getRolesByUserId(sysUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveUserRole(SysUserRole sysUserRole) {

        mapper.delUserRole(sysUserRole);
        List userRoles = new ArrayList<>();
        if (sysUserRole.getRoleguids().length > 0) {
            for (String s : sysUserRole.getRoleguids()) {
                HashMap mm = new HashMap();
                mm.put("userid", sysUserRole.getUserid());
                mm.put("roleguid", s);
                userRoles.add(mm);
            }
            mapper.saveUserRole(userRoles);
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


    @Override
    public SysUserVo findUserMsgById(String id) {
        SysUser s = mapper.selectSysUserById(id);
        List<SysMenuVo> menus = getMenuByUser(id);
        SysUserVo sysUserVo = new SysUserVo();
        sysUserVo.setMenus(menus);
        sysUserVo.setUserInfo(s);
        return sysUserVo;
    }

    @Override
    public Integer Logs(String loginId,String uniquecode,String logincode,int usertypeid,int logintype) {
        Integer a=0;
        if(logintype==1){
             a = mapper.Logs(loginId,uniquecode,logincode);
        }else if (logintype==3){
             a = mapper.Logsbyfather(loginId,uniquecode,logincode);
        }

        if(a==1){
            /**
             *机型设备绑定
             */
            if(usertypeid==3){
                Integer updatecode = mapper.UpdateCodebyfather(loginId, uniquecode);
                return updatecode;
            }else if(usertypeid==1){
                Integer updatecode = mapper.UpdateCode(loginId, uniquecode);
                return updatecode;
            }

        }
        return null;
    }

    @Override
    public Student_User getUserKey(String guid,int type) {
        Student_User user = new Student_User();
        if(type==1){
            // 用户为学生
            user = mapper.findByStudenterId(guid);
        }else{
            // 用户为家长
            user = mapper.findByParent(guid);
        }
        user.setDevicePassword(mapper.getDevicePassword(guid));
        return user;
    }


    @Override
    public List<SysMenuVo> getDeskMenuByUser(String userid) {
        return mapper.getDeskMenuByUser(userid);
    }

    @Override
    @Transactional
    public int SaveDeskMenu(SysMenuDeskDTO sysMenuDeskDTO) {
        mapper.delDeskMenu(sysMenuDeskDTO.getUserid());
        List deskMenu = new ArrayList<>();
        if (sysMenuDeskDTO.getMenuguid().length > 0) {
            for (String s : sysMenuDeskDTO.getMenuguid()) {
                HashMap mm = new HashMap();
                mm.put("userid", sysMenuDeskDTO.getUserid());
                mm.put("menuguid", s);
                mm.put("guid", new RandomGUID().toString());
                deskMenu.add(mm);
            }
            mapper.saveDeskMenu(deskMenu);
        }
        return 1;
    }
}
