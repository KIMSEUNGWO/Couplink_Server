package com.coupink.jours.security.jwt;

import com.coupink.jours.enums.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenId {

    private final Provider provider;
    private final String providerId;
}
