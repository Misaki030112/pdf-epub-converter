package center.misaki.service;


import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface SingleEpubService {


    public String pdfToEpub_Single(MultipartFile uploadFile, String username) throws IOException;


}
