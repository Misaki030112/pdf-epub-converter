package Utils;

import center.misaki.Utils.EpubUtil;
import center.misaki.Utils.PdfUtil;
import org.junit.Test;

import java.io.*;

public class PdfToEpubTest {


    //需要转换的pdf文件路径
    public static String pdfPath = "D:\\data\\pdf\\test01\\abc.pdf";
    //转换为Epub文件的路径
    public static String EpubPath = "D:\\data\\pdf\\test01\\abc.epub";
    //保存Html文件集合的路径
    public static String saveHtmlPath = "D:\\data\\pdf\\test01\\html";
    //保存Pdf文件集合的路径
    public static String savePdfPath = "D:\\data\\pdf\\test01\\pdf";


    /**
     * 测试将pdf按页分割成为一页页的pdf片段
     */
    @Test
    public void PdfSplit(){
        PdfUtil.splitPdfs(pdfPath,savePdfPath);
    }
    /**
     * 测试将分割后的pdf文件集合转为Html文件集合
     * @throws FileNotFoundException 异常未找到文件
     */
    @Test
    public void pdfToHtml() throws IOException {
        PdfUtil.toHtmls(savePdfPath,saveHtmlPath);
    }

    /**
     * 测试将html文件集合转换为单个epub文件
     */
    @Test
    public void htmlToEpub(){
        EpubUtil.toEpub(saveHtmlPath,EpubPath);
    }

}
