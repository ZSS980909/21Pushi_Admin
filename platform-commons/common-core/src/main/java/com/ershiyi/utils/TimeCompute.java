package com.ershiyi.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCompute {
    /*
    判读时间差距，两个时间相差多少天，时，分，秒
     */
    public static Long getDay(String Sdate,String Edate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long days = null;
        try {
            Date edate = dateFormat.parse(Edate);//过去时间
            Date sdate = dateFormat.parse(Sdate);//开始时间
            long diff = edate.getTime() - sdate.getTime();
            days = diff / (1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }
}
