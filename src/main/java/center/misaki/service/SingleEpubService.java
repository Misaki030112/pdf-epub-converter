package center.misaki.service;

import center.misaki.pojo.HtmlInfo;
import center.misaki.pojo.PdfInfo;
import center.misaki.pojo.User;

import java.io.File;

public interface SingleEpubService {

    public PdfInfo savePdf(File uploadFile, User user);

    public void splitPdf(PdfInfo pdfInfo);

    public HtmlInfo saveHtml(PdfInfo pdfInfo,User user);

    public void createEpub(PdfInfo pdfInfo,HtmlInfo htmlInfo,User user);

    public boolean pdfToEpub_Single(File uploadFile,String username);
}
