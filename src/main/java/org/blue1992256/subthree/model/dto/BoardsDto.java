package org.blue1992256.subthree.model.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.blue1992256.subthree.model.entity.Boards;
import org.blue1992256.subthree.oauth2.user.Users;

@Getter
@Setter
public class BoardsDto {

  private Long id;
  private String title;
  private String content;
  private String username;
  private List<CommentsDto> commentsList;
  private Integer likes;
  private Integer views;
  private String status;
  private String regDate;
  private Users user;
  public void getShortDto(Boards boards) {
    this.id = boards.getId();
    this.title = boards.getTitle();
    this.likes = boards.getLikes();
    this.views = boards.getViews();
    this.status = boards.getStatus();
    this.regDate = boards.getRegDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
    this.username = boards.getUsers().getUsername();
  }

  public void getFullDto(Boards boards) {
    getShortDto(boards);
    this.content = boards.getContent();
    this.commentsList = boards.getCommentsList().stream()
        .filter(comments -> comments.getStatus().equals("published"))
        .map(comments -> {
          CommentsDto commentsDto = new CommentsDto();
          commentsDto.setId(comments.getId());
          commentsDto.setUsername(comments.getUsers().getUsername());
          commentsDto.setContent(comments.getContent());
          commentsDto.setRegDate(comments.getRegDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
          return commentsDto;
        })
        .collect(Collectors.toList());
  }

}
