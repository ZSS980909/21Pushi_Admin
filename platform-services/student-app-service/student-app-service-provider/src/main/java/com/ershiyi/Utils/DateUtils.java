package com.ershiyi.Utils;

import com.ershiyi.domain.entity.Correct;
import com.ershiyi.domain.entity.StudyKnowledge;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 获取附近时间的日期
 */

public class DateUtils {
    private static Date now = new Date();
    private String resultTime;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取几天前的时间
     * @return 当前时间几天前的时间
     * @param number 需要的天数时间
     * @param format 需要返回的样式
     */
    public static String getAddDay(String format,Integer number){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(calendar.DATE,+number);
        String date = sdf.format(calendar.getTime());
        return date;
    }

    public static Boolean isYesterday(String gainTime){
        if(gainTime==null||gainTime==""||gainTime.length()==0){
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(calendar.DATE,-1);
        return gainTime.equals(sdf.format(calendar.getTime()));
    }

    public static Date getTomorrowTime(){
        Calendar instance = Calendar.getInstance();
        instance.setTime(now);
        instance.add(Calendar.DATE,1);
        return instance.getTime();
    }
    /**
     * 获取当几天后的时间
     * @return 当前时间几天后的时间
     * @param number 需要的天数时间
     * @param format 需要返回的样式
     */
    public static String getDeleteDay(String format,Integer number){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(calendar.DATE,-number);
        String date = sdf.format(calendar.getTime());
        return date;
    }
    /**
     * 获取几个小时后的时间
     * @return 当前时间几天后的时间
     * @param date 当前的时间
     * @param number 需要的天数时间
     * @param format 需要返回的样式
     */
    public static String getAddHour(String date,String format,Integer number){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(sdf.parse(date));
            calendar.add(calendar.HOUR, number);
            date = sdf.format(calendar.getTime());
            return date;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询当前星期最近的时间+
     * @param week 星期
     * @return
     */
    public static String getDateByWeek(String week){
        String date = "";
        // 获取当前星期
        List<String> weekDays = Arrays.asList("Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        // 拿到目前的星期
        int res = cal.get(Calendar.DAY_OF_WEEK);
        int index = weekDays.indexOf(week)+1;
        if(index==res){
            // 当天就是对应的日期
            return sdf.format(now);
        }
        // 当前星期在下周
        do{
            // 向后推一天，直到星期数与所给星期数相同
            cal.add(Calendar.DAY_OF_MONTH,1);
        }while (index!=cal.get(Calendar.DAY_OF_WEEK));
        return sdf.format(cal.getTime());
    }

    /**
     * 获取今天的星期
     * @return
     */
    public static String getWeek(){
        List<String> weekDays = Arrays.asList("Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        Calendar cal = Calendar.getInstance();
        cal.setTime(getNow());
        int res = cal.get(Calendar.DAY_OF_WEEK)-1;
        return weekDays.get(res);
    }

    /**
     * 获取当前网络时间
     * @return
     */
    public static Date getNow(){
        try {
            URLConnection conn=new URL("http://www.baidu.com").openConnection();
            conn.connect();
            Date date=new Date(conn.getDate());
            return date;
        }catch (Exception e) {
            e.printStackTrace();
            return now;
        }
    }

    public static String getWeekDay(String time){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<String> weekDays = Arrays.asList("Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(time));
            int res = cal.get(Calendar.DAY_OF_WEEK)-1;
            return weekDays.get(res);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取往后七天的星期
     * @return
     */
    public static List<String> getWeekList() {
        List<String> weekDays = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday");
        // 获取今天的日期
        String nowWeek = getWeek();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < weekDays.size(); i++) {
            if(weekDays.get(i).equals(nowWeek)){
                for (int j = i; j <7 ; j++) {
                    result.add(weekDays.get(j));
                }
                for (int k = 0; k < i ; k++) {
                    result.add(weekDays.get(k));
                }
            }
        }
        return result;
    }

    /**
     * 获取题目所用时间
     * @param correct
     * @return
     */
    public static Long getUseTime(Correct correct){
        Long useTime = 0l;
        try {
            String startTime = correct.getStartdt();
            String endTime = correct.getEnddt();
            // 获取时间的差值
            Long useTimes = sdf.parse(endTime).getTime()-sdf.parse(startTime).getTime();
            useTime = useTimes/1000L;
        }catch (Exception e){
            e.printStackTrace();
        }
        return useTime;
    }

    /**
     * 获取题目所用时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public static Long getUseTime(String startTime,String endTime){
        Long useTime = 0l;
        try {
            // 获取时间的差值
            Long useTimes = sdf.parse(endTime).getTime()-sdf.parse(startTime).getTime();
            useTime = useTimes/1000L;
        }catch (Exception e){
            e.printStackTrace();
        }
        return useTime;
    }

    /**
     * 多少毫秒后的时间
     * @param time
     * @return
     */
    public static String addTime(Long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MILLISECOND,time.intValue());
        return sdf.format(calendar.getTime());
    }

    public static Long getUseTime(StudyKnowledge know) {
        Long useTime = 0l;
        try {
            String startTime = know.getStartTime();
            String endTime = know.getEndTime();
            // 获取时间的差值
            Long useTimes = sdf.parse(endTime).getTime()-sdf.parse(startTime).getTime();
            useTime = useTimes/1000L;
        }catch (Exception e){
            e.printStackTrace();
        }
        return useTime;
    }

    public static String getEndTime(String startTime,Long useTime) {
        now.setTime(Long.valueOf(startTime)+useTime);
        return sdf.format(now);
    }

    public static String getTime(String startTime) {
        now.setTime(Long.valueOf(startTime));
        return sdf.format(now);
    }


    /**
     * 获取当前时间到第二天还剩多少秒
     * @return
     */
    public static int daySurplusTime(){
        long current = System.currentTimeMillis();	//当前时间毫秒数
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long tomorrowZero = calendar.getTimeInMillis();
        long remainSecond = (tomorrowZero - current) / 1000;
        return (int)remainSecond;
    }

    public static int untilNow(String buyTime) {

        long startTime = 0;
        try {
            startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(buyTime).getTime();
        }catch (Exception e){
            e.printStackTrace();
        }
        long endTime  = System.currentTimeMillis();
        return (int)((endTime-startTime)/(1000*60*60*24));
    }
}
