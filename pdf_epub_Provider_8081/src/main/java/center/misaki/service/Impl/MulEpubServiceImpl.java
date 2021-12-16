package center.misaki.service.Impl;


import center.misaki.dao.UserDao;

import center.misaki.domain.PathTotal;
import center.misaki.pojo.User;
import center.misaki.service.MulEpubService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Slf4j
@Service
public class MulEpubServiceImpl implements MulEpubService {
    @Autowired
    SingleEpubServiceImpl singleEpubService;

    @Autowired
    UserDao userDao;

    @Autowired
    PathTotal total;

    @Override
    public void MergePdfToEpub(File[] files, String name) throws IOException {
        //获取用户信息
        User user = userDao.findByUsername(name);
        // pdf合并工具类
        PDFMergerUtility mergePdf = new PDFMergerUtility();
        // 合成文件
        for (File file : files) {
            try {
                mergePdf.addSource(file);
            } catch (FileNotFoundException e) {
                throw e;
            }
        }
        // 设置合并生成pdf文件
        String path = total.getEpubPath() + "\\" + user.getUsername() + "\\" + files[0].getName();
        mergePdf.setDestinationFileName(path);
        // 合并pdf
        try {
            mergePdf.mergeDocuments();
            singleEpubService.pdfToEpub_Single(new File(path),name);
        } catch (Exception e) {
            throw e;
        }

    }
}
