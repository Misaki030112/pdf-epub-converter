package center.misaki.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface MergePdfService {

    /**
     * 单纯将上传的多个pdf合并为一个pdf
     * @param files 上传的多个pdf
     * @param username 用户名
     * @return 合并后的pdf在服务器中的路径
     * @throws IOException  IO异常
     */
    public String MergePdf(MultipartFile[] files, String username) throws IOException;
}
