package com.ssmarc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：Angular
 * @date ：Created in 2020/12/22 10:45
 * @description：日期工具类
 * @modified By：
 * @version: v1.0.0$
 */
public class DateUtil {
        /**
         * title:DateUtil.java
         * description:日期对象转字符串
         * time:2017年1月16日 下午10:40:49
         * author:debug-steadyjack
         * @param date
         * @param format
         * @return String
         */
        public static String formatDate(Date date, String format){
            String result=null;
            try {
                SimpleDateFormat sdf=new SimpleDateFormat(format);
                if(date!=null){
                    result=sdf.format(date);
                }
            } catch (Exception e) {
                System.out.println("日期转字符串发生异常: "+e.getMessage());
            }
            return result;
        }

        /**
         * title:DateUtil.java
         * description:字符串转日期对象
         * time:2017年1月16日 下午10:43:14
         * author:debug-steadyjack
         * @param str
         * @param format
         * @throws Exception
         */
        public static Date formatString(String str,String format){
            Date date=null;
            try {
                if(str.isEmpty()){
                    SimpleDateFormat sdf=new SimpleDateFormat(format);
                    date=sdf.parse(str);
                }
            } catch (Exception e) {
                System.out.println("字符串转日期出错: "+e.getMessage());
            }
            return date;
        }

        /**
         * title:DateUtil.java
         * description: 获取当前年月日时分秒组成的串：可用于命名图片、文件
         * time:2017年1月16日 下午10:48:48
         * author:debug-steadyjack
         * @return String
         * @throws Exception
         */
        public static String getCurrentDateStr()throws Exception{
            Date date=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
            return sdf.format(date);
        }

}
