package Utils;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;

import java.io.*;

/**
 * Epub操作工具类
 * @author CHJ
 */
public class EpubUtil {

    /**
     * 用于将文字转换为html文件
     * @param targetPath 存入html文件的路径
     * @param text 用于解析的文本->html文件
     * @return 存入Html文件的路径
     */
    public static String textToHtml(String targetPath,String[] text) {
        try {
            String encoding = "UTF-8";
            FileOutputStream fos = new FileOutputStream(new File(targetPath));
            OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <title>Test</title>\n" +
                    "    <link href=\"book1.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                    "</head>\n" +
                    "<body>");
            for (String s : text) {
                bw.write("<p>" + s + "</p>");
                bw.write("\n");
            }
            bw.write("</body>\n" +
                    "</html>");
            bw.close();
            osw.close();
            fos.close();
            return targetPath;
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据html路径创建epub
     * @param filePath 存放html的路径
     * @param targetFile 用于存放epub文件的路径
     */
    public static void createEpub(String filePath,String targetFile){
        try {
            //获取文件名
            String fileName = filePath.substring(filePath.lastIndexOf('\\'));
            // Create new Book
            Book book = new Book();
            book.addSection("introduce", new Resource(new FileInputStream(
                    new File(filePath)), fileName));

            // Create EpubWriter
            EpubWriter epubWriter = new EpubWriter();

            // Write the Book as Epub
            epubWriter.write(book, new FileOutputStream(targetFile));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
