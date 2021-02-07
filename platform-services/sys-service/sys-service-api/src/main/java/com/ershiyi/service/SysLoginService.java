package com.ershiyi.service;

import com.ershiyi.domain.*;

import java.util.List;
import java.util.Map;

/**
 * SysLoginService 服务层
 *
 * @author liy
 * @date 2020-06-06
 */
public interface SysLoginService extends BaseService<SysUser> {
    /**
     * 获取验证码
     * @param validatacode
     * @return
     */
    public ValidataCode getVerificationCode(ValidataCode validatacode);

    /**
     * 用户登录
     * @param sysuser
     * @return
     */
    int loginSysUser(SysUser sysuser);

    /**
     * 修改密码
     * @param sysuser
     * @return
     */
    public int updateSysUserByPhone(SysUser sysuser);

    /**
     * 查询用户是否被注册
     * @param sysuser
     */
    String userRegistered(SysUser sysuser);

    public List<City> allCity();

    public List<School> allCitybyName(School school);

    /**
     * 查询该用户是否存在
     * @param loginid
     * @return
     */
    List<String> queryStudent(String loginid);

}
