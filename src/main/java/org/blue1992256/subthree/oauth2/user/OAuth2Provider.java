package org.blue1992256.subthree.oauth2.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OAuth2Provider {
  KAKAO("kakao");

  private final String provider;
}
