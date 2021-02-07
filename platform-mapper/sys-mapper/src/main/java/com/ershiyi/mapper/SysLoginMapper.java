package com.ershiyi.mapper;

import com.ershiyi.domain.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 *   数据层
 *
 * @author liy
 * @date 2020-06-06
 */
public interface SysLoginMapper extends AbstractMapper<SysUser> {
    /**
     * 登录注册信息
     * @param sysUser
     * @return
     */
    public int loginSysUser(SysUser sysUser);

    @Select("select guid from sys_user where loginId = #{phone}")
    public List<String> queryStudent(@Param("phone") String phone);

    /**
     * 修改密码
     * @param sysUser
     * @return
     */
    public int updateSysUserByPhone(SysUser sysUser);

    /**
     * 查询用户是否注册
     * @param sysuser
     * @return
     */
    public  String userRegistered(SysUser sysuser);

    /**
     * 查询所有城市
     * @return
     */
    public List<City> allCity();

    public List<School> allCitybyName(School school);

    public String selectpassword(SysUser sysuser);

    public Integer UserByStudent(Student_User user);

    @Insert("insert into sys_user_integral(studenterid,schoolId,integralvalue) values(#{studentId},#{schoolId},0)")
    public Integer Account(@Param("studentId") String studentId,@Param("schoolId")String schoolId);

    /**
     * 插入家长信息表
     * @param user
     * @return
     */
    @Insert("insert into common_parent_user(parenterId,parentUserId,uniqueCode) values(#{studenterId},#{studentUserId},#{uniqueCode})")
    int userByParent(Student_User user);

    @Select("select realName from sys_user where guid = (select parentUserId from common_parent_user  where parenterId = #{userId})")
    String getName(String parenterId);
}
