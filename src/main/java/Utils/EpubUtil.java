package Utils;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Epub操作工具类
 * @author CHJ
 */
public class EpubUtil {

    /**
     * 用于更新Html内容的函数
     * @param htmlFilePath 需要转换Html的路径
     */
    public static void updateHtml(String htmlFilePath) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(htmlFilePath));
            StringBuilder sb = new StringBuilder();
            String str = "";
            while ((str = in.readLine()) != null) {
                if(str.equals("<meta charset=\"utf-8\"><title></title>")){
                    str = "<meta charset=\"utf-8\"/><title></title>";
                }
                sb.append(str);
            }
            str = sb.toString();
            str = str.replaceAll("Evaluation Warning : The document was created with Spire.PDF for java.","Test");
            str = str.replaceAll("Evaluation Warning : The document was created with Spire.PDF for Java.","Test");
            File file = new File(htmlFilePath);
            if (!file.exists())
                file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(str.getBytes());
            fos.close();
        } catch (IOException e) {
        }
    }

    /**
     * 用于转换分离pdf
     * @param pdfFilePath 需要转换的pdf文件的路径
     * @param targetFilePath 用于保存分离出来的pdf文件集合的路径
     */
    public static void splitPdf(String pdfFilePath,String targetFilePath){
        PdfDocument doc = new PdfDocument();
        doc.loadFromFile(pdfFilePath);
        //拆分为多个PDF文档
        doc.split(targetFilePath + "/{0}.pdf", 0);
    }

    /**
     *
     * @param pdfFilePath 需要转换的pdf文件集合的路径
     * @param targetFilePath 保存的html文件集合的路径
     * @throws FileNotFoundException 可能会抛出的异常（找不到文件）
     */
    public static void createHtml(String pdfFilePath,String targetFilePath) throws FileNotFoundException {
        File file = new File(pdfFilePath);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            PdfDocument pdf = new PdfDocument();
            pdf .loadFromFile(files[i].getAbsolutePath());
            pdf .getConvertOptions().setPdfToHtmlOptions(true,true);
            File outFile = new File(targetFilePath +"\\"+ i + ".html");
            OutputStream outputStream = new FileOutputStream(outFile);
            pdf.saveToStream(outputStream, FileFormat.HTML);
            pdf.close();
        }
    }

    /**
     * 根据html路径创建epub
     * @param filePath 存放html文件集合的路径
     * @param targetFile 用于存放epub文件的路径
     */
    public static void createEpub(String filePath,String targetFile){
        try {
            File file = new File(filePath);
            File[] files = file.listFiles();
            Book book = new Book();
            ArrayList<String> fileNameArr = new ArrayList<String>();
            ArrayList<Integer> middle = new ArrayList<Integer>();
            for (int i = 0; i < files.length; i++) {
                if(files[i].getName().endsWith(".html")){
                    middle.add(Integer.parseInt(files[i].getName().substring(0,files[i].getName().lastIndexOf("."))));
                }
            }
            Collections.sort(middle);
            for (Integer integer : middle) {
                fileNameArr.add(filePath + "\\" + integer.toString() + ".html");
            }

            for (String s : fileNameArr) {
                //去除Html文件里面的水印
                updateHtml(s);
            }

            // Create EpubWriter
            EpubWriter epubWriter = new EpubWriter();
            for (int i = 0; i < fileNameArr.size(); i++) {
                String fileName = fileNameArr.get(i);
                String pageNum = "Page:" + i;
                String pageName = fileName.substring(fileName.lastIndexOf("\\"));
                book.addSection(pageNum, new Resource(new FileInputStream(
                        new File(fileNameArr.get(i))), pageName));
            }

            // Write the Book as Epub
            epubWriter.write(book, new FileOutputStream(targetFile));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
