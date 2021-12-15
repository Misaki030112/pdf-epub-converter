package Utils;

import center.misaki.Utils.CommonUtils;
import center.misaki.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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