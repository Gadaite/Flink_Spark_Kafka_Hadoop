package CETC10S.Main20220525;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateAndTime {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        /**
         * System.currentTimeMillis()，当前时间的时间戳
         */
        long l = System.currentTimeMillis();
        System.out.println(l);//    1653576203459
        /**
         * SimpleDateFormat转换常见格式时间
         */
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formater.format(date));
        /**
         * 日历，日期时间
         * 获取年月日，一周第几天，一年第几个月，星期几等等和日历相关的结果
         */
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date(System.currentTimeMillis()));
        System.out.println(calendar1.getTime());
        System.out.println(formater.format(calendar1.getTime()));
        System.out.println(calendar1.get(calendar1.WEEK_OF_MONTH));
        System.out.println(calendar1.get(calendar1.WEEK_OF_YEAR));
        System.out.println(calendar1.get(calendar1.DAY_OF_WEEK));
        /**
         *  localdate,localtime,localdatetime
         */
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        /**
         * ZonedDateTime:日期和时间时区信息
         */
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getDayOfWeek());
        System.out.println(now.getDayOfYear());
    }
}
