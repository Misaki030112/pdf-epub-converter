import center.misaki.PdfToEpubApplication_8081;
import center.misaki.dao.PdfInfoDao;
import center.misaki.dao.UserDao;
import center.misaki.pojo.HtmlInfo;
import center.misaki.pojo.PathTotal;
import center.misaki.pojo.PdfInfo;
import center.misaki.pojo.User;
import center.misaki.service.Impl.SingleEpubServiceImpl;
import center.misaki.service.MulEpubService;
import center.misaki.service.SingleEpubService;
import com.spire.pdf.PdfDocument;
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
    MulEpubService mulEpubService;

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
        userDao.save(new User().setUsername(null).setPassword("123").setLastTime("2021"));
        userDao.save(new User().setUsername("123").setPassword("123").setLastTime("2021"));
        userDao.save(new User().setUsername("123").setPassword("123").setLastTime("2021"));
    }

    public static String uploadPdf00 = "F:\\documents\\Data\\test01.pdf";
    public static String uploadPdf01 = "F:\\documents\\Data\\test02.pdf";
    public static String uploadPdf02 = "F:\\documents\\Data\\test03.pdf";


    /**
     * 测试将用户上传的pdf转为Epub
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testSaveAndSplitPdf(){
        singleEpubService.pdfToEpub_Single(new File(uploadPdf00),"admin");
    }

    /**
     * 测试批量Pdf转换为一个Epub
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testMulSave() throws IOException {
        File files[] = {new File(uploadPdf00),new File(uploadPdf01)};
        System.out.println(files);
        mulEpubService.MergePdfToEpub(files,"admin");
    }

}
