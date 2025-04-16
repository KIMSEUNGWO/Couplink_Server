package com.coupink.jours.security.convert;


import com.coupink.jours.security.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public sealed interface OAuth2Converter permits
        OAuth2GoogleConverter,
        OAuth2KakaoConverter {

    DefaultOAuth2User convert(OAuth2User user);
}