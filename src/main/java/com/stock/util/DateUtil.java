package com.stock.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author mk
 * @Date 2020/10/21 17:25
 * @Version 1.0
 */
public class DateUtil {

    //实现日期加一天的方法
    public static String addDay(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(Calendar.DATE, n);//增加一天
            //cd.add(Calendar.MONTH, n);//增加一个月

            return sdf.format(cd.getTime());

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Integer getNowDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String format = formatter.format(date);
        return Integer.parseInt(format);
    }

}
