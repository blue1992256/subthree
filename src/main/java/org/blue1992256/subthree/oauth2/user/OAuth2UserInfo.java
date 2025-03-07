package org.blue1992256.subthree.oauth2.user;

public interface OAuth2UserInfo {
  OAuth2Provider getProvider();
  String getProviderId();
  String getEmail();
  String getName();
  String getProfileImage();
  String getConnectedAt();

}
