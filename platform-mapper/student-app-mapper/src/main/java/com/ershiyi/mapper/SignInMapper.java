package com.ershiyi.mapper;

import com.ershiyi.domain.Integral_record;
import com.ershiyi.domain.entity.A_Integral;
import com.ershiyi.domain.entity.A_Integral_Common;
import com.ershiyi.domain.entity.IntegralTask;
import com.ershiyi.domain.entity.A_Integral_Record;
import com.ershiyi.domain.entity.A_SignInWork;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.AbstractMapper;

import java.util.List;

/**
 *   数据层
 *
 * @author liy
 * @date 2020-06-08
 */
public interface SignInMapper extends AbstractMapper<A_Integral_Record> {
    /**
     * 签到
     * @param a_integral_record
     * @return
     */
    public  Integer signin(A_Integral_Record a_integral_record);
    /*
     * 计算页面积分
     * @param a_integral_common
     * @return
     */
    public A_Integral_Record credit(A_Integral_Common a_integral_common);

    /**
     * 查询当前是哪个功能与相对应的积分
     * @param work
     */
    public A_SignInWork qtype(A_Integral_Common work);

    public List<Integral_record> gathersignIn(A_Integral_Record record);

    public Integer SSingnin(@Param("studenterId") String studenterId);

    public Integer updateStatus(A_Integral a_integral1);

    /**
     * 判断当前学生是否在积分表存在
     * @param studenterId 学生编号
     * @return
     */
    @Select("select id from sys_user_integral where studenterId = #{studenterId}")
    public List<Integer> existStudent(@Param("studenterId") String studenterId);

    /**
     * 积分表内插入学生数据
     * @param a_integral1
     * @return
     */
    public Integer insertStudent(A_Integral a_integral1);

    /**
     * 查询学生当前积分
     * @param studentId 学生编号
     * @return
     */
    @Select("select integralvalue from sys_user_integral where studenterId = #{studentId}")
    Integer queryIntegralValue(@Param("studentId") String studentId);

    // 查询出所有的积分任务
    @Select("select id as integralId,frequency as IntegralCount,IntegralName,IntegralDescribe,IntegralValue  from sys_user_integral_entry where deleted = 0")
    List<IntegralTask> queryAllIntegral();

    /**
     * 获取学生目前的积分
     * @param studenterId 学生编号
     * @return
     */
    @Select("select  integralvalue from sys_user_integral where studenterId = #{studenterId} limit 1")
    Integer getIntegral(@Param("studenterId") String studenterId);

    List<IntegralTask> gethistory(IntegralTask integraltask);
}
