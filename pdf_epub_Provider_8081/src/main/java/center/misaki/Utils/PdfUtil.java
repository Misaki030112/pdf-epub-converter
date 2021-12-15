package center.misaki.Utils;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Pdf操作工具类
 * @author Misaki
 */
@Slf4j
public class PdfUtil {

    /**
     * 用于按页拆分pdf为一系列pdf集合
     * @param pdfFilePath 需要转换的pdf文件的路径
     * @param targetFilePath 用于保存分离出来的pdf文件集合的路径
     */
    public static void splitPdfs(String pdfFilePath,String targetFilePath){
        PdfDocument doc = new PdfDocument();
        doc.loadFromFile(pdfFilePath);
        //拆分为多个PDF文档
        doc.split(targetFilePath + "/{0}.pdf", 0);
    }

    /**
     * 将pdf文件集合转换为Html文件集合
     * @param pdfFilePath 需要转换的pdf文件集合的路径
     * @param targetFilePath 保存的html文件集合的路径
     * @throws FileNotFoundException 可能会抛出的异常（找不到文件）
     */
    public static void toHtmls(String pdfFilePath,String targetFilePath) {
        File file = new File(pdfFilePath);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            PdfDocument pdf = new PdfDocument();
            pdf .loadFromFile(files[i].getAbsolutePath());
            pdf .getConvertOptions().setPdfToHtmlOptions(true,true);
            File outFile = new File(targetFilePath +"\\"+ i + ".html");
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(outFile);
            } catch (FileNotFoundException e) {
                log.error("文件不存在，或者该文件无法被读取！");
                e.printStackTrace();
            }
            pdf.saveToStream(outputStream, FileFormat.HTML);
            pdf.close();
        }
    }



}
