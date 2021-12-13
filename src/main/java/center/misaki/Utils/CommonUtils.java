package center.misaki.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 普通工具类
 * @author Misaki
 */
public class CommonUtils {


    /**
     * 获取格式化后的时间信息 yyyy-MM-dd HH:mm:ss
     * @param calendar   时间信息
     * @return 返回一个格式化时间的字符串
     */
    public static String dateFormat( Calendar calendar ){
        if( null == calendar )
            return null;
        String date = null;
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat( pattern );
        date = format.format( calendar.getTime() );
        return date == null ? "" : date;
    }

    /**
     * 获取格式化当前时间 yyyy-MM-dd HH:mm:ss
     * @return 返回一个当前时间格式化的字符串
     */
    public static String dateFormatRightNow(){
        Calendar rightNow = Calendar.getInstance();
        return CommonUtils.dateFormat(rightNow);
    }
}
