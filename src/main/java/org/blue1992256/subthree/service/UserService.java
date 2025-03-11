package org.blue1992256.subthree.service;

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

  public void completeSignup(UserDto userDto) {
    Users user = userRepository.findByUserId(userDto.getUserId()).get();
    user.setGender(userDto.getGender());
    user.setWeight(userDto.getWeight());
    user.setHeight(userDto.getHeight());
    user.setRunningYears(userDto.getRunningYears());
    user.set_signup_complete(true);
    userRepository.save(user);
  }

}
