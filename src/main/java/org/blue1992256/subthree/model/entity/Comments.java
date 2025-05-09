package org.blue1992256.subthree.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.blue1992256.subthree.oauth2.user.Users;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@Table(name = "Comments")
@NoArgsConstructor
public class Comments {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "content")
  private String content;

  @ManyToOne
  @JoinColumn(name = "boards_id")
  private Boards boards;

  @Column(name = "status")
  private String status;

  @ManyToOne
  @JoinColumn(name = "reg_id")
  private Users users;

  @CreationTimestamp
  @Column(name = "reg_date")
  private LocalDateTime regDate;

  @Column(name = "mod_date")
  private LocalDateTime modDate;

}
