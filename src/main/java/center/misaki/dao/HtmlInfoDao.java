package center.misaki.dao;

import center.misaki.pojo.HtmlInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface HtmlInfoDao extends JpaRepository<HtmlInfo,Integer> {
}
