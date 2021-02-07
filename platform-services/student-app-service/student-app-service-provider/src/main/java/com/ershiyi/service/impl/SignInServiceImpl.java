package com.ershiyi.service.impl;

import com.ershiyi.Utils.DateUtils;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.Integral_record;
import com.ershiyi.domain.entity.A_Integral;
import com.ershiyi.domain.entity.A_Integral_Common;
import com.ershiyi.domain.entity.IntegralTask;
import com.ershiyi.domain.entity.StudentPoints;
import com.ershiyi.domain.entity.A_Integral_Record;
import com.ershiyi.domain.entity.A_SignInWork;
import com.ershiyi.mapper.SignInMapper;
import com.ershiyi.service.SignInService;
import com.ershiyi.utils.IsYesterdayUtils;
import com.ershiyi.utils.SignDate;
import com.github.pagehelper.PageHelper;
import io.swagger.models.auth.In;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SignInServiceImpl extends BaseServiceImpl<A_Integral_Record, SignInMapper> implements SignInService {
    public static Log log = LogFactory.getLog(CourseServiceImpl.class);

    /**
     * 计算页面积分
     * @param a_integral_common
     * @return
     */
    @Override
    public  Integer credit(A_Integral_Common a_integral_common) {
        int result = 0;
        /**
         * 查询是哪个功能,然后查出该功能积分
         */
        if(a_integral_common.getStudenterId()==null||a_integral_common.getStudenterId().length()==0||a_integral_common.getStudenterId()==""){
            // 学生积分为空则不执行后续过程
            return result;
        }
        A_SignInWork a_integralwork =mapper.qtype(a_integral_common);
        if (a_integralwork == null) {
            return -1;
        }
        log.info("积分功能描述"+a_integralwork.getIntegraldescribe());
        log.info("积分值"+a_integralwork.getIntegralvalue());
        log.info("积分功能名称"+a_integralwork.getIntegralname());
        log.info("积分功能名称关键字"+a_integralwork.getKeyword());
        A_Integral_Record a_integral_record = mapper.credit(a_integral_common);
        A_SignInWork a_signinwork = new A_SignInWork();
        if("EVERYDAYLOGIN".equals(a_integralwork.getKeyword())) {
            /**
             * 每日登陆功能
             * 判断对象是否为NULL,第一次进来未签到都得从默认积分开始
             * 1.先判断是不是为null
             * 2.判断时间是不是昨天
             */
            a_signinwork.setIntegralname(a_integralwork.getIntegralname());
            a_signinwork.setKeyword(a_integral_common.getKeyWord());
            if (a_integral_record == null) {
                 // 说明没有签过到
                a_signinwork.setIntegralvalue("10");
            }else {
                 // 获取当天时间
                String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                boolean today = nowTime.equals(a_integral_record.getCreatedt());
                if (today) {
                    a_signinwork.setIntegralvalue("0");
                    // 如果当天已经签到直接return
                    return 0;
                    //return creditNo;
                }else {
                    // 判断上次签到时间是否为昨天
                    boolean b = DateUtils.isYesterday(a_integral_record.getCreatedt());
                    if (!b) {
                        a_signinwork.setIntegralvalue("10");
                        //  return a_signinwork;
                    } else {
                        /**
                         * 判断是否是第7天,积分不在增加
                         */
                        if ("70".equals(a_signinwork.getIntegralvalue())) {
                            a_signinwork.setIntegralvalue("70");
                        } else {
                            double a = Double.valueOf(a_integral_record.getChangeintegral());
                            int c = (int) a + 10;
                            a_signinwork.setIntegralvalue(String.valueOf(c));
                        }
                    }
                }
            }
        }
        log.info("用户增加积分开始");
        /**
         * 查询该用户积分值
         */
        A_Integral_Record record=new A_Integral_Record();
        record.setStudenterId(a_integral_common.getStudenterId());
        record.setChangeintegral(a_signinwork.getIntegralvalue());
        record.setIntegralnameid(a_integralwork.getId());
        Integer Singninvalue =mapper.SSingnin(record.getStudenterId());
        /**
         * 计算积分
         */
        log.info("任务积分为"+record.getChangeintegral());
        log.info("原始积分为"+Singninvalue);
        Integer ZSingninvalue= Integer.parseInt(record.getChangeintegral())+Singninvalue;
        log.info("做完该任务后的积分为"+ZSingninvalue);
        record.setRawintegral(Singninvalue.toString());
        record.setIntegralvalue(ZSingninvalue.toString());
        record.setKeyWord(a_signinwork.getKeyword());
        record.setSchoolId(a_integral_common.getSchoolId());
        Integer signin = mapper.signin(record);
        if(signin==1){
            log.info("积分计划表增加成功");
            A_Integral a_integral1= new A_Integral();
            a_integral1.setSchoolId(record.getSchoolId());
            a_integral1.setStudenterId(record.getStudenterId());
            a_integral1.setIntegralvalue(record.getChangeintegral());
            // 判断当前学生是否已经存在于积分表
            // 如果返回结果为空则代表不存在 新建 反之修改增加积分
            if(mapper.existStudent(record.getStudenterId()).isEmpty()){
                mapper.insertStudent(a_integral1);
            }else {
                mapper.updateStatus(a_integral1);
            }
            result  = Integer.parseInt(record.getChangeintegral());
        }
        return result;
    }


    @Override
    public StudentPoints gathersignIn(A_Integral_Record record) {
        StudentPoints integral = new StudentPoints();
        // 查询出当前所有的任务
        List<IntegralTask> tasks = mapper.queryAllIntegral();
        integral.setIntegralTasks(tasks);
        // 学生当前完成的所有任务
        List<Integral_record> signIns = mapper.gathersignIn(record);
        // 今日总加积分
        Integer addScore = 0;
        Integer loginScore = 0;
        if(signIns.isEmpty()){
            // 学生当日没有增加记录。 直接查询学生当前的总积分
            integral.setChangeIntegral(0);
            integral.setIntegralValue(mapper.queryIntegralValue(record.getStudenterId()));
        }else {
            for (Integral_record signIn : signIns) {
                addScore += Double.valueOf(signIn.getChangeintegral()).intValue();
                // 计算出登录所加积分
                if(signIn.getIntegralnameid()==1){
                    loginScore += Double.valueOf(signIn.getChangeintegral()).intValue();
                }
            }
            integral.setChangeIntegral(addScore);
            integral.setIntegralValue(mapper.getIntegral(record.getStudenterId()));
        }
        // 遍历任务表 将学生任务的次数和增加的积分值算出来
        for (IntegralTask task : tasks) {
            // 任务完成的次数
            int count = 0;
            // 任务增加的分值
            int value = 0;
            for (Integral_record signIn : signIns) {
                if(task.getIntegralId()==signIn.getIntegralnameid()){
                    if(task.getIntegralId()==1){
                        task.setIntegralValue(loginScore);
                    }
                    task.setIsFinish(1);
                    // 已完成任务中有登录 就更改增加积分分数为实际增加的分数
                    count++;
                    value = Double.valueOf(signIn.getChangeintegral()).intValue();
                }
            }
            task.setFinishCount(count);
            task.setIntegralValueAll(value);
        }

        return integral;
    }

    /**
     * 接口未完成
     * @param integraltask
     * @return
     */
    @Override
    public List<IntegralTask> gethistory(IntegralTask integraltask) {
        /**
         * 获取积分充值,购买历史记录,分页
         *
         */
        PageHelper.startPage(integraltask.getPageNumber(),integraltask.getPageSize());
        log.info("当前页码为"+integraltask.getPageNumber());
        log.info("当前每页数量为"+integraltask.getPageSize());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        String dateNow = sdf.format(new Date());
       // integraltask.setUpdatedt(dateNow+"%");
        //integraltask.setUpdatedt("2020-10-25%");
        /**
         * 拿当前时间的倒数第七天
         */
        String pastDate = SignDate.getPastDate(7)+" 00:00:00";
        integraltask.setUpdatedt(pastDate);
       // log.info("过去7天的时间为"+pastDate);

           List<IntegralTask> gethistory = mapper.gethistory(integraltask);
           log.info(gethistory);

        /**
         * 测试
         */
        String [] arr = new String[7];
        Calendar c = null;
        for (int i=0;i<6;i++){
            c=Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, - i-1);
            arr[6-i] =sdf.format(c.getTime());
        }
        System.out.println(arr);
        Map<String,List<IntegralTask>> map =new HashMap<String,List<IntegralTask>>();
        List<Map<String,List<IntegralTask>>> countmap=new ArrayList<Map<String,List<IntegralTask>>>();

        for(int i=0;i<arr.length;i++){
            if(gethistory.get(i).getUpdatedt().contains(arr[i+1])){
                /**
                 * 计算当前分值
                 */
                gethistory.get(i).getIntegralValue();
            }
        }
//        for(int i=0;i<gethistory.size();i++){
//            gethistory.get(i).setPageNumber(integraltask.getPageNumber());
//            gethistory.get(i).setPageSize(integraltask.getPageSize());
//        }
       // return gethistory;
        return null;
    }


}
