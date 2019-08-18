package com.jack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    /**
     * 字符串日期转date
     * @param patten
     * @param stringDate
     * @return
     * @throws ParseException
     */
    public static Date StringToDate(String patten,String stringDate) throws ParseException {

        SimpleDateFormat dateFormat=new SimpleDateFormat ( patten );
        Date date = dateFormat.parse ( stringDate );
        return date;

    }

    /**
     * date日期转字符串
     * @param patten
     * @param date
     * @return
     * @throws ParseException
     */
    public static String DateToString(String patten,Date date) throws ParseException {

        SimpleDateFormat dateFormat=new SimpleDateFormat ( patten );
        String format = dateFormat.format ( date );
        return format;

    }
}
