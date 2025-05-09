package org.blue1992256.subthree.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

  private String username;
  private String userId;
  private String gender;
  private Integer height;
  private Integer weight;
  private String runningYears;
  private Boolean isSignupComplete;

}
