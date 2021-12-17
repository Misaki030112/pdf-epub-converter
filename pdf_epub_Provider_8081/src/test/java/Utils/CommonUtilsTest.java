package Utils;

import center.misaki.Utils.CommonUtils;


import org.junit.Test;

import java.util.Calendar;


class CommonUtilsTest {

    /**
     * 测试产生当前时间
     */
    @Test
    void dateFormat() {
        Calendar rightNow = Calendar.getInstance();
        String s = CommonUtils.dateFormat(rightNow);
        System.out.println(s);
    }
}