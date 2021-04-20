package com.ershiyi.controller;

import com.ershiyi.Utils.DateUtils;
import twenty.alp.TimeCalculate;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class test
{
    public static  void main(String[] args){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Date afterDate = new Date(now .getTime() + 300000);
        System.out.println(sdf.format(afterDate ));
        //System.out.println(s);

    }
}
