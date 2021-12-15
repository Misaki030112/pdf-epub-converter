package center.misaki.pojo;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "com.path")
@Component
@Data
public class PathTotal {
    private String EpubPath;
    private String HtmlPath;
    private String PdfPath;
}
