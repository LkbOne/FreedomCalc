package com.lkb.shoppingcart.common.time;
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
//     calc interval time for time
    // YEAR =1 MONTH=2 DAY_OF_MONTH=6 HOUR =10
    public static String calcFieldTime(int field,String timeFormat){
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        calendar.add(field,1);
        return format.format(calendar.getTime());

    }
}
