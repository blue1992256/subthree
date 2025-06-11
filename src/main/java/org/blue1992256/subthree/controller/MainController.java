package org.blue1992256.subthree.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blue1992256.subthree.model.dto.UserDto;
import org.blue1992256.subthree.oauth2.user.PrincipalDetails;
import org.blue1992256.subthree.oauth2.user.Users;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

  @GetMapping("/")
  public String index(Authentication authentication, Model model) {
    if (authentication != null) {
      Users user = ((PrincipalDetails)authentication.getPrincipal()).getUser();
      UserDto userDto = new UserDto();
      userDto.setUserId(user.getUserId());
      userDto.setIsSignupComplete(user.is_signup_complete());
      model.addAttribute("user", userDto);
    }
    return "index";
  }

  @GetMapping("/reviews")
  public String reviews(Authentication authentication, Model model) {
    return "notice";
  }

}
