package org.blue1992256.subthree.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.blue1992256.subthree.oauth2.user.Users;

@Entity
@Data
@Table(name = "Board_likes")
@NoArgsConstructor
public class BoardLikes {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "boards_id")
  private Long boardsId;

  @ManyToOne
  @JoinColumn(name = "reg_id")
  private Users users;

}
