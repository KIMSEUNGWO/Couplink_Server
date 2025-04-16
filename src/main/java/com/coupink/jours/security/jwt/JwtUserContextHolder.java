package com.coupink.jours.security.jwt;

import org.springframework.security.core.context.SecurityContextHolder;

public class JwtUserContextHolder {

    public static CustomJwtToken getJwtToken() {
        return (CustomJwtToken) SecurityContextHolder.getContext().getAuthentication();
    }
}
