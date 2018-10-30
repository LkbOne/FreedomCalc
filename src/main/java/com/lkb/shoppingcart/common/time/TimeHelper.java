package com.lkb.shoppingcart.common.time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeHelper {

    public String calcDiff(long now,long before){
        return String.valueOf((now-before)/1000);
    }
    public int calcMinDiff(long now,long before){
        int seconds = 60;
//        int seconds = 1;
        return Integer.parseInt(calcDiff(now, before))/seconds;
    }
    public String dealWithTimeAndDiff(String before,String timeFormat) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(timeFormat);
        Date beforeTime = df.parse(before);
        return calcDiff(systemTime2UTC(),beforeTime.getTime());
    }
    public String UTC2GMT8(SimpleDateFormat df,String before) throws ParseException {
        Date beforeTime = df.parse(before);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        format.setTimeZone(TimeZone.getTimeZone("GMT-8")) ;
        return format.format(new Date(beforeTime.getTime()));
    }
    public long systemTime2UTC(){
        Calendar cal = Calendar.getInstance() ;
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return cal.getTimeInMillis();
    }
    public String time2GMT8(String before,String timeFormat) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(timeFormat);
        return UTC2GMT8(df,before);
    }
    public static String timeStamp2Date(String millSeconde,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(millSeconde)));
    }

    public static String month2AbbreviateEnglish(String month){
        int a = Integer.parseInt(month);
        String[] abbreviateMonth =new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        return abbreviateMonth[a];
    }
    // timeFormat : "yyyy-MM-dd HH:mm" or "yyyy-MM-dd"
    public static String getNowTime(String timeFormat){
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        return format.format(calendar.getTime());
    }
    public static Date nowDateFor000(int amount){
        Calendar calendar=Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                0, 0, 0);
        calendar.add(6,amount);
        return calendar.getTime();
    }
//     calc interval time for time
    // YEAR =1 MONTH=2 DAY_OF_MONTH=6 HOUR =10
    public static String calcFieldTime(int field,String timeFormat){
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        calendar.add(field,1);
        return format.format(calendar.getTime());
    }
    public static java.sql.Timestamp time2SqlDate(String time,String format){
        SimpleDateFormat sdf2 = new SimpleDateFormat(format);
        java.sql.Timestamp sDate = null;
        try {
            java.util.Date date3 = sdf2.parse(time);
            sDate = new java.sql.Timestamp(date3.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sDate;
    }

    public static java.sql.Timestamp time2SqlDateFromFormatTime(String time,String format) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(time);
        SimpleDateFormat sdf2 = new SimpleDateFormat(format);
        java.sql.Timestamp sDate = null;
        sDate = new java.sql.Timestamp(date.getTime());
        return sDate;
    }

    public static Timestamp getTimeStamp(int amount){
        Date date = TimeHelper.nowDateFor000(amount);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Timestamp timestamp =  TimeHelper.time2SqlDate(format.format(date.getTime()),"yyyy-MM-dd");
        return timestamp;
    }
}
