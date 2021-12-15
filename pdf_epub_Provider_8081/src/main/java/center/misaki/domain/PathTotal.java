package center.misaki.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PathTotal {
    private String EpubPath;
    private String HtmlPath;
    private String PdfPath;
}