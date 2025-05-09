package org.blue1992256.subthree.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@Table(name = "Daily_report")
@NoArgsConstructor
public class DailyReport {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "likes")
  private int likes = 0;

  @Column(name = "views")
  private int views = 0;

  @Column(name = "status")
  private String status;

  @Column(name = "reg_id")
  private Long regId;

  @CreationTimestamp
  @Column(name = "reg_date")
  private LocalDateTime regDate;

  @Column(name = "mod_date")
  private LocalDateTime modDate;

}
