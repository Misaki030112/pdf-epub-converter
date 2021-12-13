package center.misaki.Utils;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import static center.misaki.Utils.HtmlUtil.updateHtml;

/**
 * Epub操作工具类
 * @author CHJ
 */
public class EpubUtil {


    /**
     * 根据html集合的路径创建单个epub文件
     * @param filePath 存放html文件集合的路径
     * @param targetFile 用于存放epub文件的路径
     */
    public static void toEpub(String filePath,String targetFile){
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
