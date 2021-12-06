package services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class pdfToTxt {


    public void printPDFContext(String filePath,String fileName){
        try{
            File file = new File(filePath,fileName);
            PDDocument load = PDDocument.load(file);
            PDFTextStripper textStripper = new PDFTextStripper();
            String txt = textStripper.getText(load);
            System.out.println(txt);
            load.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
