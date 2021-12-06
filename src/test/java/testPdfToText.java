import Utils.PdfboxUtils;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

/**
 * Pdf工具测试类
 */
public class testPdfToText {

    //测试保存图片时文件的路径
    public static String savePath="D:\\data\\pdf\\test01";

    //测试用pdf路径
    public static String pdfPath="D:\\data\\pdf\\test01\\test.pdf";


    /**
     * 测试pdf所有页面转文本
     */
    @Test
    public void testAllText(){
        String res = PdfboxUtils.extractTXT(pdfPath);
        System.out.println(res);
    }


    /**
     * 测试pdf中第一页转文本
     */
    @Test
    public void testText01(){
        String res=PdfboxUtils.extractTXT(pdfPath,1,1);
        System.out.println(res);
    }


    /**
     * 测试pdf中截取矩形区域转换为文本
     */
    @Test
    public void testTextRect(){
        String res = PdfboxUtils.readRectangleText(pdfPath, 0, new Rectangle(0, 0, 500, 800));
        System.out.println(res);
    }


    /**
     * 测试保存pdf中某一页所有的图片
     * 目前测试有点问题,仅在部分pdf中有效
     */
    @Test
    public void testSaveImage01(){
        try {
            PdfboxUtils.extractImage(pdfPath,0,savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
