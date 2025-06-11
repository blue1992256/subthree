package org.blue1992256.subthree.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blue1992256.subthree.model.dto.NoticeDto;
import org.blue1992256.subthree.model.vo.PageVo;
import org.blue1992256.subthree.oauth2.user.PrincipalDetails;
import org.blue1992256.subthree.oauth2.user.Users;
import org.blue1992256.subthree.service.NoticeService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NoticeController {

  private final NoticeService noticeService;

  @GetMapping("/notice")
  public String notice(PageVo pageVo, Model model) {
    model.addAttribute("boardList", noticeService.getNoticeList(pageVo));
    model.addAttribute("pageVo", pageVo);
    return "notice";
  }

  @GetMapping("/notice/write")
  public String notice(Authentication authentication) {
    if (authentication != null) {
      Users user = ((PrincipalDetails)authentication.getPrincipal()).getUser();
      if (!user.getRole().equals("ROLE_ADMIN")) {
        return "redirect:/notice";
      }
    }
    return "notice-write";
  }

  @ResponseBody
  @PostMapping("/notice/submit")
  public String submitNotice(Authentication authentication, NoticeDto noticeDto) {
    if (authentication == null) {
      return "fail";
    }
    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
    Users user = principalDetails.getUser();
    if (!user.getRole().equals("ROLE_ADMIN")) {
      return "unauthorized";
    }
    noticeDto.setUser(user);
    if (!noticeService.addNotice(noticeDto)) {
      return "fail";
    }
    return "success";
  }

  @GetMapping("/notice/{id}")
  public String getNotice(@PathVariable("id") Long id, Model model) {
    noticeService.increaseViews(id);
    model.addAttribute("board", noticeService.getNotice(id));
    return "notice-view";
  }

}
