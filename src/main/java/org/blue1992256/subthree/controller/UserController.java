package org.blue1992256.subthree.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blue1992256.subthree.model.dto.Response;
import org.blue1992256.subthree.model.dto.UserDto;
import org.blue1992256.subthree.oauth2.user.PrincipalDetails;
import org.blue1992256.subthree.oauth2.user.Users;
import org.blue1992256.subthree.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  // 회원가입 후 기본정보 등록
  @ResponseBody
  @PostMapping("/user/signup/info")
  public Response<?> completeSignup(Authentication authentication, @RequestBody UserDto userDto) {
    Response<?> response = new Response<>();

    Users user = ((PrincipalDetails)authentication.getPrincipal()).getUser();
    if (user.getUserId().equals(userDto.getUserId())) {
      String result = userService.completeSignup(userDto);
      response.setStatus(result);
      return response;
    }

    response.setStatus("fail");
    response.setMessage("유저 정보가 옳지 않습니다");
    return response;
  }

}
