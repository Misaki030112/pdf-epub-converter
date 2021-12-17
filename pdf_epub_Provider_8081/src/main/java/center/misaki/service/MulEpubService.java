package center.misaki.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface MulEpubService {

    /**
     * 将多个上传的pdf文件全部合并转为一个Epub文件
     * @param files 上传文件的集合
     * @param username 用户名
     * @return 合并好的在服务器上的路径
     * @throws IOException Io异常
     */
    public String MergePdfToEpub(MultipartFile[] files, String username) throws IOException;

}
