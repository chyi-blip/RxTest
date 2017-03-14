package com.primb.rxtest.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Chen on 2016/12/13.
 * 功能描述：
 */

public class Utils {

    public static String parseWeek(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK) + days - 1);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        String weekDay = "";
        switch (i) {
            case 1:
                weekDay = "周一";
                break;
            case 2:
                weekDay = "周二";
                break;
            case 3:
                weekDay = "周三";
                break;
            case 4:
                weekDay = "周四";
                break;
            case 5:
                weekDay = "周五";
                break;
            case 6:
                weekDay = "周六";
                break;
            case 7:
                weekDay = "周日";
                break;
        }
        return weekDay;
    }

}
