package com.coupink.jours.security.convert;

import com.coupink.jours.security.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public final class OAuth2KakaoConverter implements OAuth2Converter {

    @Override
    public DefaultOAuth2User convert(OAuth2User user) {
        Map<String, Object> kakaoAccount = user.getAttribute("kakao_account");

        return DefaultOAuth2User.builder()
                .providerId(user.getName())
                .email((String) kakaoAccount.get("email"))
                .build();
    }
}