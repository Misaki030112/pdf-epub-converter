package center.misaki.service.Impl;

import center.misaki.Utils.CommonUtils;
import center.misaki.Utils.EpubUtil;
import center.misaki.Utils.PdfUtil;
import center.misaki.dao.EpubDao;
import center.misaki.dao.HtmlInfoDao;
import center.misaki.dao.PdfInfoDao;
import center.misaki.dao.UserDao;
import center.misaki.pojo.*;
import center.misaki.service.SingleEpubService;
import com.spire.pdf.PdfDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Slf4j
@Service
public class SingleEpubServiceImpl implements SingleEpubService {

    @Resource
    UserDao userDao;

    @Resource
    PdfInfoDao pdfInfoDao;

    @Resource
    HtmlInfoDao htmlInfoDao;

    @Resource
    EpubDao epubDao;

    @Autowired
    PathTotal pathTotal;

    /**
     * 用于保存用户上传的pdf文件
     * @param uploadFile 用户上传的文件
     * @param user 用户信息
     * @return
     */
    @Override
    public PdfInfo savePdf(File uploadFile, User user) {
        try {
            //获取用户使用次数
            Integer times = user.getUseTimes();
            PdfDocument pdfDocument = new PdfDocument(new FileInputStream(uploadFile));
            //获取用户专属的pdf文件夹，若为新用户则创建文件夹
            File userFile = new File(pathTotal.getPdfPath() + "\\" + user.getUsername());
            if(!userFile.exists()){
                userFile.mkdir();
            }
            //根据用户的使用次数建立相应的文件夹，并且更新用户的使用次数
            times++;
            user.setUseTimes(times);
            String savaSplitPath = userFile.getAbsolutePath() + "\\" + times;
            File savaSplitPathFile = new File(savaSplitPath);//本次转换的pdf分离后所保存的文件夹
            if(!savaSplitPathFile.exists()){
                savaSplitPathFile.mkdir();
            }
            //将用户上传的pdf放入相应文件夹中
            InputStream inputStream = new FileInputStream(uploadFile);
            OutputStream outputStream = new FileOutputStream(userFile.getAbsolutePath() + "\\" + uploadFile.getName());
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, bytesRead);
            }
            //获取当前时间
            Calendar calendar = new GregorianCalendar();
            //将pdf信息保存到数据库中
            PdfInfo pdfInfo = pdfInfoDao.save(new PdfInfo()
                    .setPdfName(uploadFile.getName())
                    .setSize(uploadFile.length())
                    .setSavePath(userFile.getAbsolutePath())
                    .setPageNum(pdfDocument.getPages().getCount())
                    .setUserId(user.getUserId())
                    .setSaveTime(CommonUtils.dateFormat(calendar))
                    .setUpload(true)
                    .setSplitPath(savaSplitPath));
            inputStream.close();
            outputStream.close();
            return pdfInfo;
        } catch (IOException e) {
            log.info(e.toString());
        }
        return null;
    }

    /**
     * 用于分离pdf文件
     * @param pdfInfo 保存的pdf相关信息
     * @return
     */
    @Override
    public void splitPdf(PdfInfo pdfInfo) {
        //获取pdf文件所在位置
        String pdfFileName = pdfInfo.getSavePath() + "\\" + pdfInfo.getPdfName();
        //获取分离后所需放的文件夹
        String pdfSplitFileName = pdfInfo.getSplitPath();
        //调用方法类，实现分离
        PdfUtil.splitPdfs(pdfFileName,pdfSplitFileName);
    }

    /**
     * 用于将分离的pdf文件转换为Html文件
     * @param pdfInfo 保存的pdf相关信息
     * @param user 用户的信息
     * @return
     */
    @Override
    public HtmlInfo saveHtml(PdfInfo pdfInfo,User user) {
        //获取用户使用次数
        Integer times = user.getUseTimes();
        //获取pdf分离之后的路径(需要转换的pdf文件集合的路径)
        String splitPdfPath = pdfInfo.getSplitPath();
        //获得HtmlTotal文件下该用户专属的Html路径，若不存在则创建
        File userFile = new File(pathTotal.getHtmlPath() + "\\" + user.getUsername());
        if(!userFile.exists()){
            userFile.mkdir();
        }
        //根据使用次数创建文件夹，若不存在则创建一个新的文件夹
        String savaSplitPath = userFile.getAbsolutePath() + "\\" + times;
        File savaSplitPathFile = new File(savaSplitPath);//本次转换的pdf分离后所保存的文件夹
        if(!savaSplitPathFile.exists()){
            savaSplitPathFile.mkdir();
        }
        //获取当前时间
        Calendar calendar = new GregorianCalendar();
        PdfUtil.toHtmls(splitPdfPath,savaSplitPath);
        HtmlInfo htmlInfo = htmlInfoDao.save(new HtmlInfo().setSaveTime(CommonUtils.dateFormat(calendar)).setSavePath(savaSplitPath).setUserId(user.getUserId()));
        log.info(user.getUsername() + "," + user.getUseTimes() + ":" + "html创建成功");
        return htmlInfo;
    }

    /**
     * 用于创建Epub
     * @param pdfInfo 需要使用到的pdf信息
     * @param htmlInfo 需要使用到的html信息
     * @param user 用户信息
     */
    @Override
    public void createEpub(PdfInfo pdfInfo, HtmlInfo htmlInfo, User user) {
        //获取需要转换为Epub的html文件集合路径
        String htmlInfoSavePath = htmlInfo.getSavePath();
        //获取该用户下的专属Epub文件夹，若不存在则创建
        File userFile = new File(pathTotal.getEpubPath() + "\\" + user.getUsername());
        if(!userFile.exists()){
            userFile.mkdir();
        }
        //获取最终Epub的文件名
        String EpubName = pdfInfo.getPdfName().substring(0,pdfInfo.getPdfName().lastIndexOf(".")) + ".epub";
        //创建本次所需要保存的文件夹
        File saveFile = new File(userFile.getAbsolutePath() + "\\" + user.getUseTimes());
        if(!saveFile.exists()){
            saveFile.mkdir();
        }
        //获取此次需要存放Epub的位置
        String saveEpubPath = userFile.getAbsolutePath() + "\\" + user.getUseTimes() + "\\" + EpubName;
        //调用工具类生成Epub文件
        EpubUtil.toEpub(htmlInfoSavePath,saveEpubPath);
        //获取当前时间
        Calendar calendar = new GregorianCalendar();
        //获取文件大小
        File EpubFile = new File(saveEpubPath);
        long length = EpubFile.length();
        epubDao.save(new EpubInfo()
                .setEpubName(EpubName)
                .setSavePath(saveEpubPath)
                .setSaveTime(CommonUtils.dateFormat(calendar))
                .setUserId(user.getUserId())
                .setSize(length));
    }


}
