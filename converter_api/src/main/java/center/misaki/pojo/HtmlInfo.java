package center.misaki.pojo;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "html")
public class HtmlInfo implements Serializable {
    //HtmlId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer htmlId;
    //保存时间
    private String saveTime;
    //保存的html集合路径(文件夹)
    private String savePath;
    //关联的用户Id
    private Integer userId;
}
