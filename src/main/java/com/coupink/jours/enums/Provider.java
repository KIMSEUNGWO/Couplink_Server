package com.coupink.jours.enums;

import com.coupink.jours.security.DefaultOAuth2User;
import com.coupink.jours.security.convert.OAuth2Converter;
import com.coupink.jours.security.convert.OAuth2GoogleConverter;
import com.coupink.jours.security.convert.OAuth2KakaoConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.function.Function;

@Getter
@AllArgsConstructor
public enum Provider {

    GOOGLE(new OAuth2GoogleConverter()),
    KAKAO(new OAuth2KakaoConverter());

    private final OAuth2Converter converter;

    public static Provider findByProvider(String clientName) {
        return Provider.valueOf(clientName.toUpperCase());
    }

    public DefaultOAuth2User convert(OAuth2User user) {
        return converter.convert(user);
    }

}