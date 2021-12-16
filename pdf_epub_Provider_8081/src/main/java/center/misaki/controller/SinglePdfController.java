package center.misaki.controller;

import center.misaki.service.SingleEpubService;
import center.misaki.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 文件上传下载类
 */
@Slf4j
@RestController
@RequestMapping("/PdfToEpub")
public class SinglePdfController {

    @Autowired
    SingleEpubService singleEpubService;
    @Autowired
    UserInfoService userInfoService;

    /**
     * 上传接受单pdf文件进行处理
     * @param pdfFile pdf文件
     * @param username 用户名
     * @return 返回文件名
     */
    @PostMapping("/upload/one")
    public String httpUpload(MultipartFile pdfFile,String username){
        String msg;
        try {
            msg=singleEpubService.pdfToEpub_Single(pdfFile,username);
        } catch (IOException e) {
            log.error("无法创建上传的文件！");
            msg="error";
            e.printStackTrace();
        }
        log.info("用户："+username+"成功上传了文件："+pdfFile.getOriginalFilename());
        return msg;
    }

    @PostMapping("/download/one")
    public String httpDownload(String path, String username, HttpServletResponse response) {
        try {
            if(!userInfoService.isPathValid(path,username)){
                log.warn("有人试图通过不合法的请求下载！请求用户："+username+" 想要下载路径"+path);
                return "error_Download01";
            }
            // path是指想要下载的文件的路径
            File file = new File(path);
            // 获取文件名
            String filename = file.getName();
            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            log.error("该用户："+username+" 请求的下载资源不存在或者无法打开"+path);
            e.printStackTrace();
            return "error_Download02";

        } catch (IOException e) {
            log.error("此路径文件读写错误："+path);
            e.printStackTrace();
            return "error_Download03";
        }
        return "success";
    }

}
