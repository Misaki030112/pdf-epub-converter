import Utils.EpubUtil;
import Utils.PdfboxUtils;
import org.junit.jupiter.api.Test;

/**
 * v1.0版本测试将纯文本pdf转换为epub
 */
public class testPdfToEpub {

    //pdf路径
    public static String pdfPath="D:\\data\\pdf\\test01\\test01.pdf";
    //保存的Html路径
    public static String htmlPath="D:\\data\\pdf\\test01\\test01.html";
    //保存的Epub文件路径
    public static String EpubPath="D:\\data\\pdf\\test01\\test01.epub";

    /**
     * 测试将文本pdf,全部不分页，转换
     */
    @Test
    public void testRes(){
        //获取pdf文本
        String txt = PdfboxUtils.extractTXT(pdfPath);
        String[] split = txt.split("\n");
        EpubUtil.createEpub(EpubUtil.textToHtml(htmlPath,split),EpubPath);
    }

}
