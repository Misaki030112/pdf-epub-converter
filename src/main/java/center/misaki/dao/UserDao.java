package center.misaki.dao;

import center.misaki.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,Integer> {
    /**
     * 通过用户名获取用户
     * @param name 用户名
     * @return
     */
    public User findByUsername(String name);
}
