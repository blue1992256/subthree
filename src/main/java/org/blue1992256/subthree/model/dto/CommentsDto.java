package org.blue1992256.subthree.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsDto {

  private Long id;
  private String username;
  private String content;
  private String regDate;
}
