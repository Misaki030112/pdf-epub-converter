package center.misaki.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Accessors(chain = true)
@Entity
@Table(name = "user")
public class User implements Serializable {
    //用户Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    //用户名
    private String username;
    //密码
    private String password;
    //上一次操作时间
    private String lastTime;

}
