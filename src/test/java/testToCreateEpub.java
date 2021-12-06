import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import org.junit.jupiter.api.Test;
import util.EpubUtil;

import java.io.*;

public class testToCreateEpub {

    public static String text = "11,22,55,77,99,22,33";

    public static String Path = "F:\\epubtest\\Data\\11.html";

    public static String targetPath = "F:\\epubtest\\Data\\11.epub";








    @Test
    public void testCreateHtml(){
        String[] split = text.split(",");
        System.out.println(EpubUtil.textToHtml(Path, split));
    }

    @Test
    public void testCreateEpub(){
        EpubUtil.createEpub(Path,targetPath);
    }

}
