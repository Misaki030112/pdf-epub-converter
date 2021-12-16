package center.misaki.service.Impl;

import center.misaki.dao.UserDao;
import center.misaki.domain.PathTotal;
import center.misaki.pojo.User;
import center.misaki.service.MergePdfService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


@Slf4j
@Service
public class MergePdfServiceImpl implements MergePdfService {
    @Autowired
    UserDao userDao;

    @Autowired
    PathTotal total;

    /**
     * 将pdf文件合并为一个pdf
     * @param files 需要合并的pdf
     * @param username 用户名
     * @return 返回值为保存的pdf地址,避免查表
     * @throws IOException
     */
    @Override
    public String MergePdf(File[] files, String username) throws IOException {
        //获取用户信息
        User user = userDao.findByUsername(username);
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
        File savePath = new File(total.getEpubPath() + "\\" + user.getUsername() + "\\" + (user.getUseTimes() + 1));
        if(!savePath.exists()){
            savePath.mkdir();
        }
        String path = savePath.getAbsolutePath() + "\\" + files[0].getName();
        mergePdf.setDestinationFileName(path);
        // 合并pdf
        try {
            mergePdf.mergeDocuments();
            user.setUseTimes(user.getUseTimes() + 1);
            userDao.save(user);
        } catch (Exception e) {
            throw e;
        }
        return path;
    }
}
