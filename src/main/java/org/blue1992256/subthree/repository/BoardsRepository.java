package org.blue1992256.subthree.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.blue1992256.subthree.model.entity.Boards;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardsRepository extends JpaRepository<Boards, Long> {

  @Modifying
  @Transactional
  @Query("UPDATE Boards b SET b.views = b.views + 1 WHERE b.id = :id")
  void increaseViewCount(long id);

  @Query("SELECT b FROM Boards b WHERE b.status = 'published' ORDER BY b.regDate DESC")
  List<Boards> findAllBy(Pageable pageable);

  @Query("SELECT COUNT(b) FROM Boards b WHERE b.status = 'published'")
  Integer countAllBy();

  @Query("SELECT b FROM Boards b WHERE b.title LIKE %:keyword% AND b.status = 'published' ORDER BY b.regDate DESC")
  List<Boards> findAllByTitle(String keyword, Pageable pageable);

  @Query("SELECT COUNT(b) FROM Boards b WHERE b.title LIKE %:keyword% AND b.status = 'published'")
  Integer countAllByTitle(String keyword);

  @Query("SELECT b FROM Boards b WHERE b.content LIKE %:keyword% AND b.status = 'published' ORDER BY b.regDate DESC")
  List<Boards> findAllByContent(String keyword, Pageable pageable);

  @Query("SELECT COUNT(b) FROM Boards b WHERE b.content LIKE %:keyword% AND b.status = 'published'")
  Integer countAllByContent(String keyword);

  @Query("SELECT b FROM Boards b WHERE (b.title LIKE %:keyword% OR b.content LIKE %:keyword%) AND b.status = 'published' ORDER BY b.regDate DESC")
  List<Boards> findAllByTitleAndContent(String keyword, Pageable pageable);

  @Query("SELECT COUNT(b) FROM Boards b WHERE (b.title LIKE %:keyword% OR b.content LIKE %:keyword%) AND b.status = 'published'")
  Integer countAllByTitleAndContent(String keyword);

  @Query(value = "SELECT * FROM Boards b WHERE b.reg_date > (SELECT reg_date FROM Boards WHERE id = :id) AND b.status = 'published' ORDER BY b.reg_date ASC LIMIT 1", nativeQuery = true)
  Optional<Boards> findNextBoard(@Param("id") Long id);

  @Query(value = "SELECT * FROM Boards b WHERE b.reg_date < (SELECT reg_date FROM Boards WHERE id = :id) AND b.status = 'published' ORDER BY b.reg_date DESC LIMIT 1", nativeQuery = true)
  Optional<Boards> findPreviousBoard(@Param("id") Long id);

}
