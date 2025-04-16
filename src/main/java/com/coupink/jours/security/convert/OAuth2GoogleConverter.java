package com.coupink.jours.security.convert;

import com.coupink.jours.security.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public final class OAuth2GoogleConverter implements OAuth2Converter {

    @Override
    public DefaultOAuth2User convert(OAuth2User user) {
        return DefaultOAuth2User.builder()
                .providerId(user.getName())
                .email(user.getAttribute("email"))
                .build();
    }
}