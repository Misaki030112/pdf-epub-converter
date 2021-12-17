package center.misaki.service;


import java.util.List;

public interface UserInfoService {

    /**
     * 校验这个用户的此Epub文件路径是否存在
     * @param path Epub路径
     * @param username 用户名
     * @return 是否合法
     */
    boolean isPathValid(String path,String username);

    /**
     * 返回这个用户所有的Epub文件的路径
     * @param username 用户名
     * @return 返回所有路径集合
     */
    List<String> findAllPath(String username);

}
