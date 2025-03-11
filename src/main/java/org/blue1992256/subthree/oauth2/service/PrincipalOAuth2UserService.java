package org.blue1992256.subthree.oauth2.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blue1992256.subthree.repository.UserRepository;
import org.blue1992256.subthree.oauth2.user.KakaoOAuth2UserInfo;
import org.blue1992256.subthree.oauth2.user.OAuth2UserInfo;
import org.blue1992256.subthree.oauth2.user.PrincipalDetails;
import org.blue1992256.subthree.oauth2.user.Users;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2UserInfo oAuth2UserInfo;
    OAuth2User oAuth2User = super.loadUser(userRequest);
    if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
      oAuth2UserInfo = new KakaoOAuth2UserInfo(oAuth2User.getAttributes());
    } else {
      throw new OAuth2AuthenticationException(new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST));
    }

    String userId = oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId();
    Optional<Users> optionalUser = userRepository.findByUserId(userId);
    if (optionalUser.isEmpty()) {
      // 기존 유저 정보가 없는 경우 회원가입 처리
      Users user = Users.builder()
          .userId(userId)
          .email(oAuth2UserInfo.getEmail())
          .profile_image(oAuth2UserInfo.getProfileImage())
          .provider(oAuth2UserInfo.getProvider().getProvider())
          .provider_id(oAuth2UserInfo.getProviderId())
          .connected_at(oAuth2UserInfo.getConnectedAt())
          .build();
      userRepository.save(user);

      return new PrincipalDetails(user, oAuth2User.getAttributes());
    } else {
      // 유저 정보가 있는 경우 로그인

      return new PrincipalDetails(optionalUser.get(), oAuth2User.getAttributes());
    }
  }
}
