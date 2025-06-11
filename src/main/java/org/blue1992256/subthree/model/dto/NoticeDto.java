package org.blue1992256.subthree.model.dto;

import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;
import org.blue1992256.subthree.model.entity.Notice;
import org.blue1992256.subthree.oauth2.user.Users;

@Getter
@Setter
public class NoticeDto {

  private Long id;
  private String title;
  private String content;
  private String username;
  private Integer views;
  private String status;
  private String regDate;
  private Users user;
  public void getShortDto(Notice notice) {
    this.id = notice.getId();
    this.title = notice.getTitle();
    this.views = notice.getViews();
    this.status = notice.getStatus();
    this.regDate = notice.getRegDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
    this.username = notice.getUsers().getUsername();
  }

  public void getFullDto(Notice notice) {
    getShortDto(notice);
    this.content = notice.getContent();
  }

}
