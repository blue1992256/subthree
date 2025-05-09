package org.blue1992256.subthree.repository;

import java.util.Optional;
import org.blue1992256.subthree.oauth2.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

  Optional<Users> findByUserId(String userId);

  Integer countByUsername(String username);

}
