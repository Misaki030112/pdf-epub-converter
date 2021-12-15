import center.misaki.PdfToEpubApplication_8081;
import center.misaki.dao.UserDao;
import center.misaki.pojo.User;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;


import javax.annotation.Resource;
import javax.transaction.Transactional;

@SpringBootTest(classes = PdfToEpubApplication_8081.class)
public class SpringData_JpaTestCase {

    @Resource
    UserDao userDao;

    /**
     * 测试SpringData Jpa事务操作
     */
    @Test
    @Transactional
    public void testInsert(){
        userDao.save(new User().setUsername("123").setPassword("123").setLastTime("2021"));
        userDao.save(new User().setUsername("1as23").setPassword("123").setLastTime("2021"));
        userDao.save(new User().setUsername("12dsa3").setPassword("1dsdas3").setLastTime("2021"));
        userDao.save(new User().setUsername("12ds3").setPassword("1dsad23").setLastTime("2021"));
        //异常数据会导致所有插入失败
//        userDao.save(new User().setUsername(null).setPassword("123").setLastTime("2021"));
//        userDao.save(new User().setUsername("123").setPassword("123").setLastTime("2021"));
//        userDao.save(new User().setUsername("123").setPassword("123").setLastTime("2021"));
    }
}
