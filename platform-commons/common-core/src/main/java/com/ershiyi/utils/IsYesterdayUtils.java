package com.ershiyi.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 判断是否是昨天
 */
public class IsYesterdayUtils {
//     public static void main(String[] args){
//         boolean b = compareOneAndYesterday("2020-06-12 14:33:20");
//         System.out.println(b);
//     }
    /***
     * 比较参数的值是否是昨天（是昨天返回 true）
     * @param oneTime
     * @return
     */
    public static boolean compareOneAndYesterday(String oneTime){
        //
        if(oneTime == null){
            return  false;
        }
        //获得昨天的值
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String twoTime = sdf.format(calendar.getTime());
        System.out.println(twoTime);
        System.out.println(oneTime);
        //比较
        return oneTime.equals(twoTime);
    }

    /**
     * 判断是否是当天
     * @param inputJudgeDate
     * @return
     */
    public static boolean isToday(Date inputJudgeDate) {
        boolean flag = false;
        // 获取当前系统时间
        long longDate = System.currentTimeMillis();
        Date nowDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(nowDate);
        String subDate = format.substring(0, 10);
        // 定义每天的24h时间范围
        String beginTime = subDate + " 00:00:00";
        String endTime = subDate + " 23:59:59";
        Date paseBeginTime = null;
        Date paseEndTime = null;
        try {
            paseBeginTime = dateFormat.parse(beginTime);
            paseEndTime = dateFormat.parse(endTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (inputJudgeDate.after(paseBeginTime) && inputJudgeDate.before(paseEndTime)) {
            flag = true;
        }
        return flag;
    }
}
