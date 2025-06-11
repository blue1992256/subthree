package org.blue1992256.subthree.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blue1992256.subthree.model.dto.UserDto;
import org.blue1992256.subthree.oauth2.user.Users;
import org.blue1992256.subthree.repository.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public String completeSignup(UserDto userDto) {
    if (userRepository.countByUsername(userDto.getUsername()) > 0) {
      return "duplicated";
    }

    Optional<Users> optionalUser = userRepository.findByUserId(userDto.getUserId());
    if (optionalUser.isEmpty()) {
      return "fail";
    }
    Users user = optionalUser.get();
    user.setUsername(userDto.getUsername());
    user.setGender(userDto.getGender());
    user.setWeight(userDto.getWeight());
    user.setHeight(userDto.getHeight());
    user.setRunningYears(userDto.getRunningYears());
    user.set_signup_complete(true);
    userRepository.save(user);
    return "success";
  }

  public String modifyUserInfo(UserDto userDto) {
    Optional<Users> optionalUser = userRepository.findByUserId(userDto.getUserId());
    if (optionalUser.isEmpty()) {
      return "fail";
    }
    Users user = optionalUser.get();
    user.setGender(userDto.getGender());
    user.setWeight(userDto.getWeight());
    user.setHeight(userDto.getHeight());
    user.setRunningYears(userDto.getRunningYears());
    userRepository.save(user);
    return "success";
  }

}
