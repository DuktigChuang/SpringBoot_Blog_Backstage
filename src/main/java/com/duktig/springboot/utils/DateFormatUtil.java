package com.duktig.springboot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @Description: 时间格式化工具
 * @Creator: flanderschuang
 * @Date: 2021/3/16 11:58 上午
 * * @param 传入时间
 * @return: 返回设定的格式化时间样式
 */
public class DateFormatUtil {
    public static String getDateFormatString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
