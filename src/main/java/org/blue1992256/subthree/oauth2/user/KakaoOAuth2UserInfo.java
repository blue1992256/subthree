package org.blue1992256.subthree.oauth2.user;

import java.util.Map;

public class KakaoOAuth2UserInfo implements OAuth2UserInfo {

  private final String providerId;
  private final String email;
  private final String profile_image;
  private final String connected_at;

  public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
    if (attributes.containsKey("kakao_account")) {
      Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
      if (kakaoAccount.containsKey("email")) {
        this.email = kakaoAccount.get("email").toString();
      } else {
        this.email = null;
      }
      if (kakaoAccount.containsKey("profile")) {
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        this.profile_image = profile.get("profile_image_url").toString();
      } else {
        this.profile_image = null;
      }
    } else {
      this.email = null;
      this.profile_image = null;
    }
    this.providerId = attributes.get("id").toString();
    this.connected_at = attributes.get("connected_at").toString();
  }

  @Override
  public OAuth2Provider getProvider() {
    return OAuth2Provider.KAKAO;
  }

  @Override
  public String getProviderId() {
    return providerId;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public String getName() {
    return null;
  }

  public String getProfileImage() {
    return profile_image;
  }

  public String getConnectedAt() {
    return connected_at;
  }
}
