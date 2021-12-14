package center.misaki.dao;

import center.misaki.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserDao extends JpaRepository<User,Integer> {
}
