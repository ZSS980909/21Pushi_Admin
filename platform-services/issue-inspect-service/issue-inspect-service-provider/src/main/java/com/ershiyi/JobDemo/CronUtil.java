package com.ershiyi.JobDemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CronUtil {
    private static final String CRON_DATE_FORMAT = "ss mm HH dd MM ? yyyy";
    /***
     *
     * @param date
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateByPattern(Date date, String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }
    /***
     * convert Date to cron ,eg.  "0 06 10 15 1 ? 2014"
     * @param date  : 时间点
     * @return
     */
    public static String getCron(java.util.Date  date){
        String dateFormat="ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

//    public static void main (String[] args){
//        try{
//            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = sf.parse("2021-01-20 17:34:03");
//            String cron=CronUtil.getCron(date);
//            System.out.println(cron);
//        }catch (Exception e){
//
//        }
//
//    }
    /***
     *
     * @param cron Quartz cron的类型的日期
     * @return  Date日期
     */

    public static Date getDate(final String cron) {


        if(cron == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(cron);
        } catch (ParseException e) {
            return null;// 此处缺少异常处理,自己根据需要添加
        }
        return date;
    }

}
