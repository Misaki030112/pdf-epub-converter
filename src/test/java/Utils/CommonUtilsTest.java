package Utils;

import center.misaki.Utils.CommonUtils;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

class CommonUtilsTest {

    @Test
    void dateFormat() {
        Calendar rightNow = Calendar.getInstance();
        String s = CommonUtils.dateFormat(rightNow);
        System.out.println(s);
    }
}