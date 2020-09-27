package main.java.com.example.bookstore.utils;

/**
 * Created by xieyongning on 2018/9/14.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 */
public class DateTimeUtil {

    private static SimpleDateFormat format;

    public static final String DEFAULT_TIMEZONE = "GMT+8";

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String FORMAT = "yyyyMMdd";

    public static final String UPLOAD_FILE_FORMAT = "yyyyMMddHHmm";

    static {
        format = new SimpleDateFormat(DEFAULT_FORMAT);
    }

    private DateTimeUtil() {
    }

    /**
     * 转换为默认时间格式
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format.format(date);
    }
    /**
     * 按照默认格式转换时间
     * @param time
     * @return
     */
    public static Date fromString(String time) {

        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * 自定义格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 按照指定格式转换时间
     * @param time
     * @param pattern
     * @return
     */
    public static Date fromString(String time, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}

