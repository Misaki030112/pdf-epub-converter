package center.misaki.dao;

import center.misaki.pojo.EpubInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpubDao extends JpaRepository<EpubInfo,Integer> {
}
