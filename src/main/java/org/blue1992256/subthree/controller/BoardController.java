package org.blue1992256.subthree.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blue1992256.subthree.model.dto.BoardsDto;
import org.blue1992256.subthree.model.dto.UserDto;
import org.blue1992256.subthree.model.vo.PageVo;
import org.blue1992256.subthree.oauth2.user.PrincipalDetails;
import org.blue1992256.subthree.oauth2.user.Users;
import org.blue1992256.subthree.service.BoardService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/board")
  public String freeboard(Authentication authentication, PageVo pageVo, Model model) {
    model.addAttribute("boardList", boardService.getBoardsList(pageVo, "BOARD"));
    model.addAttribute("pageVo", pageVo);
    if (authentication != null) {
      model.addAttribute("user", new UserDto(((PrincipalDetails)authentication.getPrincipal()).getUser()));
    }
    return "boards";
  }

  @GetMapping("/board/write")
  public String freeboard(Authentication authentication, Model model) {
    if (authentication != null) {
      model.addAttribute("user", new UserDto(((PrincipalDetails)authentication.getPrincipal()).getUser()));
    }
    return "boards-write";
  }

  @ResponseBody
  @PostMapping("/board/submit")
  public String submit(Authentication authentication, BoardsDto boardsDto) {
    if (authentication == null) {
      return "fail";
    }
    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
    Users user = principalDetails.getUser();
    if (!user.is_signup_complete()) {
      return "signup not finished";
    }
    boardsDto.setUser(user);
    boardsDto.setType("BOARD");
    if (!boardService.addBoards(boardsDto)) {
      return "fail";
    }
    return "success";
  }

  @GetMapping("/board/{id}")
  public String getBoard(Authentication authentication, @PathVariable("id") Long id, Model model) {
    boardService.increaseViews(id);
    model.addAttribute("board", boardService.getBoards(id));
    if (authentication != null) {
      model.addAttribute("user", new UserDto(((PrincipalDetails)authentication.getPrincipal()).getUser()));
    }
    return "boards-view";
  }

  @GetMapping("/reviews")
  public String reviewBoard(Authentication authentication, PageVo pageVo, Model model) {
    model.addAttribute("boardList", boardService.getBoardsList(pageVo, "REVIEW"));
    model.addAttribute("pageVo", pageVo);
    if (authentication != null) {
      model.addAttribute("user", new UserDto(((PrincipalDetails)authentication.getPrincipal()).getUser()));
    }
    return "review";
  }

  @GetMapping("/reviews/write")
  public String reviewBoard(Authentication authentication, Model model) {
    if (authentication != null) {
      model.addAttribute("user", new UserDto(((PrincipalDetails)authentication.getPrincipal()).getUser()));
    }
    return "review-write";
  }

  @ResponseBody
  @PostMapping("/reviews/submit")
  public String submitReview(Authentication authentication, BoardsDto boardsDto) {
    if (authentication == null) {
      return "fail";
    }
    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
    Users user = principalDetails.getUser();
    if (!user.is_signup_complete()) {
      return "signup not finished";
    }
    boardsDto.setUser(user);
    boardsDto.setType("REVIEW");
    if (!boardService.addBoards(boardsDto)) {
      return "fail";
    }
    return "success";
  }

  @GetMapping("/reviews/{id}")
  public String getReview(Authentication authentication, @PathVariable("id") Long id, Model model) {
    boardService.increaseViews(id);
    model.addAttribute("board", boardService.getBoards(id));
    if (authentication != null) {
      model.addAttribute("user", new UserDto(((PrincipalDetails)authentication.getPrincipal()).getUser()));
    }
    return "review-view";
  }

  @ResponseBody
  @PostMapping("/board/likes/submit")
  public String boardLikesSubmit(Authentication authentication, BoardsDto boardsDto) {
    if (authentication == null) {
      return "fail";
    }
    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
    Users user = principalDetails.getUser();
    boardsDto.setUser(user);
    if (!user.is_signup_complete()) {
      return "signup not finished";
    }
    if (!boardService.increaseLikes(boardsDto)) {
      return "already liked";
    }
    return "success";
  }

  @ResponseBody
  @PostMapping("/board/comment/submit")
  public String boardCommentSubmit(Authentication authentication, BoardsDto boardsDto) {
    if (authentication == null) {
      return "fail";
    }
    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
    Users user = principalDetails.getUser();
    boardsDto.setUser(user);
    if (!user.is_signup_complete()) {
      return "signup not finished";
    }
    if (boardService.submitComment(boardsDto)) {
      return "success";
    }
    return "fail";
  }

  @ResponseBody
  @DeleteMapping("/board/comment")
  public String boardCommentDelete(Authentication authentication, BoardsDto boardsDto) {
    if (authentication == null) {
      return "fail";
    }
    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
    Users user = principalDetails.getUser();
    boardsDto.setUser(user);
    if (!user.is_signup_complete()) {
      return "signup not finished";
    }
    if (boardService.deleteComment(boardsDto)) {
      return "success";
    }
    return "fail";
  }

}
