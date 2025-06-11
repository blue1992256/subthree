package org.blue1992256.subthree.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import org.blue1992256.subthree.model.entity.Notice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

  @Modifying
  @Transactional
  @Query("UPDATE Notice n SET n.views = n.views + 1 WHERE n.id = :id")
  void increaseViewCount(long id);

  @Query("SELECT n FROM Notice n WHERE n.status = 'published' ORDER BY n.regDate DESC")
  List<Notice> findAllBy(Pageable pageable);

  @Query("SELECT COUNT(n) FROM Notice n WHERE n.status = 'published'")
  Integer countAllBy();

}
