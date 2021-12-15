package center.misaki.dao;

import center.misaki.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,Integer> {
}
