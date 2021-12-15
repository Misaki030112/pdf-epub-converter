package center.misaki.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "pdf")
public class PdfInfo {
    //主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pdfId;
    //保存的pdf地址
    private String savePath;
    //文件大小
    private Long size;
    //保存时间
    private String saveTime;
    //页数
    private Integer pageNum;
    //文件名称
    private String pdfName;
    //是否是用户上传的
    private boolean isUpload;
    //用户Id
    private Integer userId;
    //分割后的pdf集合地址
    private String splitPath;

}
