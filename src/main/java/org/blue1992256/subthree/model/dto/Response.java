package org.blue1992256.subthree.model.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

  private String status;
  private String message;
  private LocalDateTime timestamp = LocalDateTime.now();
  private T data;

}
