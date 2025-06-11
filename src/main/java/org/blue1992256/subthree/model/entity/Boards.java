package org.blue1992256.subthree.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.blue1992256.subthree.oauth2.user.Users;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@Table(name = "Boards")
@NoArgsConstructor
public class Boards {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "type")
  private String type;

  @OneToMany(mappedBy = "boards")
  private List<Comments> commentsList = new ArrayList<>();

  @Transient
  private int likes;

  @Column(name = "views")
  private int views = 0;

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

  public void addComment(Comments comments) {
    this.commentsList.add(comments);
  }

}
