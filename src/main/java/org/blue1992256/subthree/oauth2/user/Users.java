package org.blue1992256.subthree.oauth2.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@Table(name = "Users")
@NoArgsConstructor
public class Users {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "username")
  private String username;

  @Column(name = "role")
  private String role;

  @Column(name = "email")
  private String email;

  @Column(name = "gender")
  private String gender;

  @Column(name = "weight")
  private Integer weight;

  @Column(name = "height")
  private Integer height;

  @Column(name = "running_years")
  private String runningYears;

  @Column(name = "profile_image")
  private String profile_image;

  @Column(name = "provider")
  private String provider;

  @Column(name = "provider_id")
  private String provider_id;

  @Column(name = "connected_at")
  private String connected_at;

  @Column(name = "is_signup_complete")
  private boolean is_signup_complete = false;

  @CreationTimestamp
  @Column(name = "reg_date")
  private LocalDateTime reg_date;

  @Builder
  public Users(String userId, String email, String profile_image, String provider, String provider_id, String connected_at, String role) {
    this.userId = userId;
    this.email = email;
    this.profile_image = profile_image;
    this.provider = provider;
    this.provider_id = provider_id;
    this.connected_at = connected_at;
    this.role = role;
  }

}
