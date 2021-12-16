import center.misaki.PdfToEpubApplication_8081;
import center.misaki.dao.UserDao;
import center.misaki.domain.PathTotal;
import center.misaki.pojo.HtmlInfo;
import center.misaki.pojo.PdfInfo;
import center.misaki.pojo.User;

import center.misaki.service.SingleEpubService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;


@SpringBootTest(classes = PdfToEpubApplication_8081.class)
public class SpringData_JpaTestCase {

    @Resource
    UserDao userDao;

    @Autowired
    SingleEpubService singleEpubService;

    @Autowired
    PathTotal pathTotal;
    



    /**
     * 测试SpringData Jpa事务操作
     */
    @Test
    @Transactional
//    @Rollback(false)
    public void testInsert(){
        userDao.save(new User().setUsername("123").setPassword("123").setLastTime("2021").setUseTimes(0));
        userDao.save(new User().setUsername("1as23").setPassword("123").setLastTime("2021").setUseTimes(0));
        userDao.save(new User().setUsername("12dsa3").setPassword("1dsdas3").setLastTime("2021").setUseTimes(0));
        userDao.save(new User().setUsername("12ds3").setPassword("1dsad23").setLastTime("2021").setUseTimes(0));
        //异常数据会导致所有插入失败
//        userDao.save(new User().setUsername(null).setPassword("123").setLastTime("2021"));
//        userDao.save(new User().setUsername("123").setPassword("123").setLastTime("2021"));
//        userDao.save(new User().setUsername("123").setPassword("123").setLastTime("2021"));
    }

    public static String uploadPdf = "F:\\documents\\Data\\test00.pdf";


    /**
     * 测试将用户上传的pdf转为Epub
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testSaveAndSplitPdf(){
        User user = userDao.getById(1);
        PdfInfo info = singleEpubService.savePdf(new File(uploadPdf), user);
        singleEpubService.splitPdf(info);
        HtmlInfo htmlInfo = singleEpubService.saveHtml(info, user);
        singleEpubService.createEpub(info,htmlInfo, user);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void Mytest() throws IOException {
        singleEpubService.pdfToEpub_Single(new File(uploadPdf),"admin");
    }

}
