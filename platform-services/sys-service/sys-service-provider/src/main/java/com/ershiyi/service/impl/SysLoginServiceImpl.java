package com.ershiyi.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.*;
import com.ershiyi.genid.GenerateUUID;
import com.ershiyi.mapper.SysLoginMapper;
import com.ershiyi.mapper.SysUserMapper;
import com.ershiyi.service.SysLoginService;
import com.ershiyi.utils.LoginUtils;
import com.ershiyi.utils.RandomCode;
import com.ershiyi.utils.RandomString;
import com.ershiyi.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * SysLoginServiceImpl 服务层实现
 *
 * @author liy
 * @date 2020-06-06
 */
@Slf4j
@Service
public class SysLoginServiceImpl extends BaseServiceImpl<SysUser, SysLoginMapper>  implements SysLoginService {
    @Override
    public ValidataCode getVerificationCode(ValidataCode validatacode) {
//        boolean b = RedisUtils.hasKey(validatacode.getMobilePhone());
//        long expire = RedisUtils.getExpire(validatacode.getMobilePhone());
//        //验证redis
//        if(expire>60){
//            return null;
//        }else{
            String randomcode = RandomCode.randomcode();//验证码
            Date dd=new Date();
            //格式化
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=sim.format(dd);
            if (validatacode.getType() == 1) {
                /**
                 * 1为注册功能
                 */
                LoginUtils.sendSms(validatacode.getMobilePhone(), LoginUtils.REGISTER, randomcode);
            } else if (validatacode.getType() == 2) {
                /**
                 * 2为修改密码功能
                 */
                LoginUtils.sendSms(validatacode.getMobilePhone(), LoginUtils.UPDATE_PW, randomcode);
            }else if(validatacode.getType() == 3) {
                // 为登录功能
                LoginUtils.sendSms(validatacode.getMobilePhone(), LoginUtils.PHONE_LOGIN,randomcode);
            }else if(validatacode.getType()==4){
                // 绑定功能
                LoginUtils.sendSms(validatacode.getMobilePhone(),LoginUtils.BINDING,randomcode,mapper.getName(validatacode.getParenterId()));
            }else if(validatacode.getType()==5){
                // 修改信息
                LoginUtils.sendSms(validatacode.getMobilePhone(),LoginUtils.MODIFY_PHONE,randomcode);
            }
            /**
             * 存入redis ,设置有效时间 有效时间5分钟
             */
            validatacode.setValidataCode(randomcode);
            validatacode.setCreateTime(time);
            boolean set = RedisUtils.set(validatacode.getMobilePhone(), validatacode.getValidataCode(), 300);
            if(set){
                System.out.println("验证码存入redis成功"+validatacode.getValidataCode());
            }else{
                System.out.println("验证码存入redis失败");
            }
            return validatacode;
      //  }
    }

    @Override
    public int loginSysUser(SysUser sysUser) {
        //GUID主键赋值
        sysUser.setGuid(UUID.randomUUID().toString().replace("-", ""));
        //密码md5加密
        sysUser.setPwd(SecureUtil.md5(sysUser.getPwd()));
        //System.out.print(sysUser.getPwd()+"密码");
        //随机生成字符串
        String nickname = RandomString.getRandomString(10);
        sysUser.setNickname(nickname);
      //  System.out.println(sysUser+"日志输出~~~");
        int i = mapper.loginSysUser(sysUser);
        if (i==1) {
            String randomNickname = RandomCode.getRandomNickname(38);
            Student_User user = new Student_User();
            if(sysUser.getUserTypeId().equals(1)) {
                /**
                 * 绑定学生关系
                 *生成学生student编号
                 */
                String studenterId = sysUser.getSchoolId() + randomNickname;
                String schoolId = sysUser.getSchoolId();
                String schoolName=sysUser.getSchoolName();
                String guid = sysUser.getGuid();
                user.setSchoolId(schoolId);
                user.setSchoolName(schoolName);
                user.setStudenterId(studenterId);
                user.setStudentUserId(guid);
                Integer integer = mapper.UserByStudent(user);
                if (integer == 1) {
                    System.out.println("学生账户关系绑定成功");
                    /**
                     * 开户
                     */
                    Integer account = mapper.Account(studenterId, schoolId);
                    if (account == 1) {
                        System.out.println("学生账户开户成功");
                    }
                    /**
                     * 绑定设备
                     */
                    //sysUser.get
                    return account;
                }
            }else if(sysUser.getUserTypeId().equals(2)){
                // 用户为老师
            }else if(sysUser.getUserTypeId().equals(3)){
                // 用户为家长
                String parentId = System.currentTimeMillis()+randomNickname;
                user.setStudentUserId(sysUser.getGuid());
                user.setStudenterId(parentId);
                user.setUniqueCode(sysUser.getUniqueCode());
                return mapper.userByParent(user);
            }

        }
        return 0;
    }

    @Override
    public int updateSysUserByPhone(SysUser sysuser) {
        /**
         * 查询是否跟原密码一样
         */
        String  pwd =mapper.selectpassword(sysuser);
        if(pwd.equals(sysuser.getPwd())){
            return 2;
        }
        sysuser.setPwd(SecureUtil.md5(sysuser.getPwd()));
        return mapper.updateSysUserByPhone(sysuser);
    }

    @Override
    public String userRegistered(SysUser sysuser) {
        return mapper.userRegistered(sysuser);
    }

    @Override
    public List<City> allCity() {
        return mapper.allCity();
    }

    @Override
    public List<School> allCitybyName(School school) {
        return mapper.allCitybyName(school);
    }

    @Override
    public List<String> queryStudent(String loginid) {
        return mapper.queryStudent(loginid);
    }


}
