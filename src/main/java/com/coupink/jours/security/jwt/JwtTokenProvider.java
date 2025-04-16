package com.coupink.jours.security.jwt;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final SecretKey secretKey;
    public final int JWT_ACCESS_TIME = 60 * 60;
    public final int JWT_REFRESH_TIME = 60 * 60 * 24;

    public String generateAccessToken(TokenId tokenId, long userId,
                                      Collection<? extends GrantedAuthority> authorities) {
        return createToken(tokenId, userId, authorities, JWT_ACCESS_TIME);
    }
    public String generateRefreshToken(TokenId tokenId, long userId,
                                       Collection<? extends GrantedAuthority> authorities) {
        return createToken(tokenId, userId, authorities, JWT_REFRESH_TIME);
    }

    // 토큰 생성
    private String createToken(TokenId tokenId,
                               long userId,
                               Collection<? extends GrantedAuthority> authorities,
                               int expirationPeriod) {
        Date now = new Date(System.currentTimeMillis());

        return Jwts.builder()
                .header()
                .keyId(UUID.randomUUID().toString())
                .add("typ", "JWT")
                .and()
                .issuer("TEST_JWT")
                .claim("iss", tokenId.getProvider())
                .claim("sub", tokenId.getProviderId())
                .claim("userId", userId)
                .claim("scope", authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expirationPeriod * 1000L))
                .signWith(secretKey)
                .compact();
    }

}