package com.jack;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 前端转来的string日期转换成Date
 */
public class myCustomerConvrtor implements Converter<String, Date> {
    @Override
    public Date convert (String s) {
        SimpleDateFormat format = new SimpleDateFormat ();
        if (s == null || s == "") {
            throw new NullPointerException ( "请输入日期，日期不能为空" );
        }
        if (s.contains ( "-" )) {
            format = new SimpleDateFormat ( "yyyy-MM-dd" );
            try {
                Date date = format.parse ( s );
                return date;
            } catch (ParseException e) {
                throw new RuntimeException ( "日期格式不正确" );
            }
        } else {
            format = new SimpleDateFormat ( "yyyy/MM/dd" );
            try {
                Date date = format.parse ( s );
                return date;
            } catch (ParseException e) {
                throw new RuntimeException ( "日期格式不正确" );
            }
        }
    }
}
