package com.coupink.jours.security;

import lombok.Builder;

@Builder
public record DefaultOAuth2User(String providerId,
                                String email) {
}