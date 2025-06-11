package org.blue1992256.subthree.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.blue1992256.subthree.oauth2.user.Users;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

  private String username;
  private String userId;
  private String gender;
  private Integer height;
  private Integer weight;
  private String runningYears;
  private Boolean isSignupComplete;
  private String profileImage;

  public UserDto(Users user) {
    this.userId = user.getUserId();
    this.isSignupComplete = user.is_signup_complete();
    this.profileImage = user.getProfile_image();
  }

}
