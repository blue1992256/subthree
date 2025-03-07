package org.blue1992256.subthree.config;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.blue1992256.subthree.oauth2.handler.OAuth2AuthenticationSuccessHandler;
import org.blue1992256.subthree.oauth2.service.PrincipalOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final PrincipalOAuth2UserService principalOAuth2UserService;
  private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        )
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
            .requestMatchers("/h2-console/**").permitAll()
            .anyRequest().permitAll()
        )
        .oauth2Login(oauth2Login ->
            oauth2Login
                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(principalOAuth2UserService))
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureUrl("/error-page")
        )
        .logout(logout -> logout
            .addLogoutHandler((request, response, authentication) -> {
              HttpSession session = request.getSession();
              session.invalidate();
            })
            .logoutSuccessHandler((request, response, authentication) -> {
              response.sendRedirect("/");
            })
        )
    ;
    return http.build();
  }

}
