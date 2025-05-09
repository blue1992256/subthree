package org.blue1992256.subthree.repository;

import org.blue1992256.subthree.model.entity.BoardLikes;
import org.blue1992256.subthree.oauth2.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikesRepository extends JpaRepository<BoardLikes, Long> {

  Integer countByBoardsId(Long boardsId);

  boolean existsByBoardsIdAndUsers(Long boardsId, Users users);



}
