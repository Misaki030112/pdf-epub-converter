import center.misaki.PdfToEpubApplication_8081;

import com.spire.pdf.PdfDocument;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



public class MyTest {




    @Test
    public void testForMerge(){
        String[] files = new String[]
                {
                        "C:\\Users\\18108\\Desktop\\test01.pdf",
                        "C:\\Users\\18108\\Desktop\\test02.pdf",
                        "C:\\Users\\18108\\Desktop\\test03.pdf",
                };
        String outputFile = "C:\\Users\\18108\\Desktop\\result.pdf";

        //创建PDFDocument示例并加载三个示例文档
        PdfDocument[] docs = new PdfDocument[files.length];
        PdfDocument doc = new PdfDocument();
        for (int i = 0; i < files.length; i++) {
            docs[i] = new PdfDocument();
            docs[i].loadFromFile(files[i]);
        }
        //添加第一页并写入第一个示例文档的数据
        docs[0].appendPage(docs[1]);

        //将另外两个文档的数据插入新的页面
        for (int i = 0; i < docs[2].getPages().getCount(); i = i + 2) {
            docs[0].insertPage(docs[2], i);
        }

        // 保存文档
        docs[0].saveToFile(outputFile);
        doc.close();
    }

}
