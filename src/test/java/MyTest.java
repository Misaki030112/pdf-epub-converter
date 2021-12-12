import Utils.EpubUtil;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.bookmarks.PdfBookmark;
import com.spire.pdf.bookmarks.PdfBookmarkCollection;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

public class MyTest {

    public static String pdfPath = "C:\\Users\\18108\\Desktop\\test01.pdf";
    public static String pdfPath2 = "C:\\Users\\18108\\Desktop\\test02.pdf";
    public static String pdfPath3 = "C:\\Users\\18108\\Desktop\\test03.pdf";
    public static String txtPath = "C:\\Users\\18108\\Desktop\\test01.txt";
    public static String HtmlPath = "C:\\Users\\18108\\Desktop\\test01.html";
    public static String HtmlPath2 = "C:\\Users\\18108\\Desktop\\test02.html";
    public static String EpubPath = "C:\\Users\\18108\\Desktop\\test01.epub";
    public static String pdfSavePath = "C:\\Users\\18108\\Desktop\\PdfSplit";

    public static String savePath = "C:\\Users\\18108\\Desktop\\save";


    @Test
    public void pdfToHtml() throws FileNotFoundException {
        EpubUtil.createHtml(savePath,savePath);
    }

    @Test
    public void test(){
        EpubUtil.splitPdf(pdfPath3,savePath);
    }


    @Test
    public void htmlToEpub(){
        EpubUtil.createEpub(savePath,EpubPath);
    }

}
