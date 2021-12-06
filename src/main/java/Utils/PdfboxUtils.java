package Utils;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import javax.imageio.ImageIO;


/**
 * 这是操作PDF文档的一个工具类
 * @author Misaki
 */
public class PdfboxUtils {

    public static final String REGION_NAME = "content";

    /**
     * 获取pdf文档中所有的文本
     * @param filePath  pdf文档路径
     */
    public static String extractTXT(String filePath){
        try{
            PDDocument document = PDDocument.load(new File(filePath));
            //获取一个PDFTextStripper文本剥离对象
            PDFTextStripper stripper = new PDFTextStripper();
            //对pdf文本进行排序
            stripper.setSortByPosition(true);
            //获取全部的pdf文本
            String content = stripper.getText(document);
            document.close();
            return content;
        } catch (IOException ex) {
            Logger.getLogger(PdfboxUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * 提取部分页面文本
     * @param filePath pdf文档路径
     * @param startPage 开始页数
     * @param endPage 结束页数
     */
    public static String extractTXT(String filePath,int startPage,int endPage){
        try{
            PDDocument document = PDDocument.load(new File(filePath));
            //获取一个PDFTextStripper文本剥离对象
            PDFTextStripper stripper = new PDFTextStripper();
            // 对pdf文档进行排序处理
            stripper.setSortByPosition(true);
            // 设置起始页
            stripper.setStartPage(startPage);
            // 设置结束页
            stripper.setEndPage(endPage);
            // 获取pdf文档的文本
            String content = stripper.getText(document);
            document.close();
            return content;
        } catch (IOException ex) {
            Logger.getLogger(PdfboxUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * 根据指定文件页码的指定区域读取文字
     * @param filePath PDF文件路径
     * @param iPage PDF页码
     * @param textReact 读取文字的区域
     * @return 文字内容
     */
    public static String readRectangleText(String filePath, int iPage, Rectangle textReact) {


        try{
            PDDocument document = PDDocument.load(new File(filePath));
            // 获取指定的PDF页
            PDPage pdfPage = document.getPage(iPage);

            // 获取指定位置的文字（文字剥离器）
            PDFTextStripperByArea textStripper = new PDFTextStripperByArea();
            textStripper.setSortByPosition(true);
            textStripper.addRegion(REGION_NAME, textReact);
            textStripper.extractRegions(pdfPage);

           String textContent = textStripper.getTextForRegion(REGION_NAME);

            // 释放资源
            document.close();
            return textContent;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }

    /**
     * 提取某一页的图片并保存
     * @param filePath PDF文档路径
     * @param imgSavePath 图片保存路径
     * @param iPage PDF文档当前页数
     */
    public static void extractImage(String filePath,int iPage,String imgSavePath) throws IOException {
        try{
            PDDocument document = PDDocument.load(new File(filePath));
            //获取文档页面
            PDPage page = document.getPage(iPage);
            PDResources resources = page.getResources();
            Iterable<COSName> cosNames = resources.getXObjectNames();

            if(cosNames!=null){
                Iterator<COSName> cosNamesIter = cosNames.iterator();
                while (cosNamesIter.hasNext()) {
                    COSName cosName = cosNamesIter.next();
                    if (resources.isImageXObject(cosName)) {
                        PDImageXObject IPdfImage = (PDImageXObject) resources.getXObject(cosName);
                        BufferedImage image = IPdfImage.getImage();
                        FileOutputStream out = new FileOutputStream(imgSavePath + UUID.randomUUID() + ".png");
                        ImageIO.write(image,"png",out);
                    }
                }
            }
            document.close();
        } catch (IOException ex) {
            Logger.getLogger(PdfboxUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * 根据指定文件页码的指定区域读取图片
     * @param filePath PDF文件路径
     * @param iPage PDF页码
     * @param imgReact 读取图片的区域
     * @return 图片内容
     */
    public static BufferedImage readRectangleImage(String filePath, int iPage, Rectangle imgReact) {

        BufferedImage bufImage = null;
        try{
            PDDocument pdfDoc = PDDocument.load(new File(filePath));
            // 获取渲染器，主要用来后面获取BufferedImage
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDoc);
            // 截取指定位置生产图片
            bufImage = pdfRenderer.renderImage(iPage).getSubimage(imgReact.x,imgReact.y,imgReact.width,imgReact.height);

            // 释放资源
            pdfDoc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bufImage;
    }



}
